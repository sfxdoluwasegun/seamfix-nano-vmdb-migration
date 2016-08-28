package com.nano.vou.mdb.handlers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.jboss.ejb3.annotation.Clustered;
import org.jboss.logging.Logger;

import com.nano.jpa.entity.Subscriber;
import com.nano.jpa.entity.SubscriberState;
import com.nano.jpa.enums.ActiveStatus;
import com.nano.jpa.enums.PayType;
import com.nano.jpa.enums.TradeType;
import com.nano.vou.jaxb.CDRVou;
import com.nano.vou.jaxb.CDRVouMesh;
import com.nano.vou.tools.JmsManager;
import com.nano.vou.tools.QueryManager;

/**
 * Handles crunching of VOU file data.
 * 
 * @author segz
 *
 */

@Clustered
@Stateless
public class VouHandler {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private QueryManager queryManager ;
	
	@Inject
	private JmsManager jmsManager ;

	/**
	 * Process VOU data with optimized map message object
	 * 
	 * @param mapMessage
	 * @return true if operation is successful
	 */
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void processVouData(MapMessage mapMessage){

		Subscriber subscriber = null;

		BigDecimal cardFaceValue = null;
		BigDecimal postpaidBalance = null;
		BigDecimal postpaidBalanceBefore = null;
		BigDecimal prepaidBalance = null;
		BigDecimal prepaidBalanceBefore = null;
		BigDecimal rechargeForPostpaid = null;
		BigDecimal rechargeForPrepaid = null;
		
		TradeType tradeType = null;

		try {
			subscriber = queryManager.createSubscriber(mapMessage.getString("chargingpartynumber"));

			if (queryManager.getSubscriberHistoryBySubscriberAndRechargeTime(subscriber.getMsisdn(), new Timestamp(mapMessage.getLong("voutime"))) != null)
				return ;

			log.info("vouProcessing:" + mapMessage.getString("chargingpartynumber") + " time:" + new Timestamp(mapMessage.getLong("voutime")));

			cardFaceValue = BigDecimal.valueOf(mapMessage.getInt("cardfacevalue")).divide(BigDecimal.valueOf(100));

			postpaidBalance = mapMessage.getInt("postpaidbalance") != 0 
					? BigDecimal.valueOf(mapMessage.getInt("postpaidbalance")).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

			postpaidBalanceBefore = mapMessage.getInt("postpaidbalancebefore") != 0 
					? BigDecimal.valueOf(mapMessage.getInt("postpaidbalancebefore")).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

			prepaidBalance = mapMessage.getInt("prepaidbalance") != 0 
					? BigDecimal.valueOf(mapMessage.getInt("prepaidbalance")).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

			prepaidBalanceBefore = mapMessage.getInt("prepaidbalancebefore") != 0 
					? BigDecimal.valueOf(mapMessage.getInt("prepaidbalancebefore")).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

			rechargeForPostpaid = mapMessage.getInt("rechargeforpostpaid") != 0 
					? BigDecimal.valueOf(mapMessage.getInt("rechargeforpostpaid")).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

			rechargeForPrepaid = mapMessage.getInt("rechargeforprepaid") != 0 
					? BigDecimal.valueOf(mapMessage.getInt("rechargeforprepaid")).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

			Integer trade = null;
			boolean other = false;

			trade = Integer.valueOf(mapMessage.getInt("tradetype"));

			if (trade >= new Integer(1000))
				other = true;

			tradeType = other ? TradeType.OTHER : TradeType.fromCode(mapMessage.getString("tradetype"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}

		updateSubscriberState(prepaidBalance, postpaidBalance, mapMessage, subscriber);

		queryManager.createNewSubscriberHistoryData(cardFaceValue, 
				postpaidBalance, postpaidBalanceBefore, prepaidBalance, prepaidBalanceBefore, rechargeForPostpaid, rechargeForPrepaid, mapMessage, tradeType, subscriber);
		
		queryManager.resetSubscriberAssessmentInitTime(subscriber);
		jmsManager.queueMsisdnForRiskAssessment(subscriber.getMsisdn());
	}
	
	/**
	 * Process VOU mesh data.
	 *
	 * @param cdrVouMesh
	 * @return true if successful
	 */
	public boolean processVouMeshData(CDRVouMesh cdrVouMesh) {
		
		List<CDRVou> cdrVous = cdrVouMesh.getCdrVous();
		
		if (cdrVous != null && !cdrVous.isEmpty())
			for (CDRVou cdrVou : cdrVous){
				cdrVou.getAccessmethod();
				//processVouData(cdrVou);
			}
		
		return true;
	}
	
	/**
	 * Update subscriber state using optimized map message object
	 * 
	 * @param prepaidBalance
	 * @param postpaidBalance
	 * @param mapMessage
	 * @return updated {@link Subscriber} information
	 */
	private void updateSubscriberState(BigDecimal prepaidBalance, 
			BigDecimal postpaidBalance, MapMessage mapMessage, Subscriber subscriber) {

		PayType payType = null;
		try {
			payType = PayType.fromCode(mapMessage.getString("paytype"));
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			log.error("", e1);
		}
		
		BigDecimal currentBalance = payType != null && payType.equals(PayType.PREPAID) ? prepaidBalance : postpaidBalance ;

		ActiveStatus activeStatus = null;

		String currentUserState = null;
		try {
			currentUserState = mapMessage.getString("currentuserstate");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}

		boolean blacklisted = false;

		if (currentUserState != null && !currentUserState.isEmpty()){
			activeStatus = ActiveStatus.fromCode(currentUserState.substring(0, 1));

			if (currentUserState.substring(currentUserState.length() - 1, currentUserState.length()).equalsIgnoreCase("1"))
				blacklisted = true ;
		}

		activeStatus = activeStatus != null ? activeStatus : ActiveStatus.ACTIVE;

		SubscriberState subscriberState = queryManager.getSubscriberStateByMsisdn(subscriber.getMsisdn());
		if (subscriberState != null)
			queryManager.updateSubscriberState(subscriberState, activeStatus, blacklisted, currentBalance, payType, subscriber.getMsisdn());
		else
			queryManager.createSubscriberState(activeStatus, blacklisted, currentBalance, payType, subscriber.getMsisdn());
	}

}