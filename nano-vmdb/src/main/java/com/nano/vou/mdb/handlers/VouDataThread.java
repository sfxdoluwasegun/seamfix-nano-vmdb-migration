package com.nano.vou.mdb.handlers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.concurrent.Callable;

import org.apache.commons.lang.time.StopWatch;
import org.jboss.logging.Logger;

import com.nano.jpa.entity.Subscriber;
import com.nano.jpa.entity.SubscriberState;
import com.nano.jpa.enums.ActiveStatus;
import com.nano.jpa.enums.PayType;
import com.nano.jpa.enums.TradeType;
import com.nano.vou.jbeans.ApplicationBean;
import com.nano.vou.tools.JmsManager;
import com.nano.vou.tools.QueryManager;

public class VouDataThread implements Callable<Boolean> {
	
	private Logger log = Logger.getLogger(getClass());
	
	private QueryManager queryManager ;
	private ApplicationBean applicationBean ;
	private JmsManager jmsManager ;

	private String chargingpartynumber;
	private String tradetype;
	private String paytype;
	private String currentuserstate;
	private String transistionid;
	private String voucherbatchnumber;
	private String vouchersequence;
	private String olduserstate;

	private Long voutime;

	private int cardfacevalue;
	private int postpaidbalance;
	private int postpaidbalancebefore;
	private int prepaidbalance;
	private int prepaidbalancebefore;
	private int rechargeforpostpaid;
	private int rechargeforprepaid;

	private Integer loanindicator;
	
	public VouDataThread(QueryManager queryManager, 
			ApplicationBean applicationBean, JmsManager jmsManager, 
			int accessmethod, 
			String accountid, 
			long accounttype1, long accounttype2, long accounttype3, long accounttype4, long accounttype5, 
			long accounttype6, long accounttype7, long accounttype8, long accounttype9, long accounttype10, 
			String additionalinfo, String agentname, String billcycleid, 
			String bonusvalidity1, String bonusvalidity2, String bonusvalidity3, String bonusvalidity4, String bonusvalidity5, 
			String bonusvalidity6, String bonusvalidity7, String bonusvalidity8, String bonusvalidity9, String bonusvalidity10, 
			int brandid, 
			String callingcellid, 
			int cardfacevalue, int cardvalidityadded, int cardvalueadded, 
			long chargeamount1, long chargeamount2, long chargeamount3, long chargeamount4, long chargeamount5, 
			long chargeamount6, long chargeamount7, long chargeamount8, long chargeamount9, long chargeamount10, 
			long currencycode, 
			long currentacctamount1, long currentacctamount2, long currentacctamount3, long currentacctamount4, long currentacctamount5, 
			long currentacctamount6, long currentacctamount7, long currentacctamount8, long currentacctamount9, long currentacctamount10, 
			String currentuserstate, String chargingpartynumber, String disablestop, 
			Integer loanindicator, 
			String newactivestop, String olduserstate, String operatedby, String paytype, 
			int postpaidbalance, int postpaidbalancebefore, int prepaidbalance, int prepaidbalancebefore, 
			String previousactivestop, String previousdisablestop, String previoussuspendstop, String productid, String rebateschemaid, 
			int rechargeareanumber, int rechargeforpostpaid, int rechargeforprepaid, int rechargepenalty, int rechargetax, int resultcode, int rewardcycles, 
			String serialno, String servicetype, 
			int subcosid, 
			String subscriberid, String suspendstop, String thirdpartynumber, String timestamp, String tradetype, String transistionid, 
			int validityadded, 
			String voucherbatchnumber, 
			int vouchercosid, 
			String voucherencryptionnumber, String voucherpinnumber, String vouchersequence, String voucherspid, 
			long voutime) {
		// TODO Auto-generated constructor stub
		
		this.queryManager = queryManager;
		this.applicationBean = applicationBean;
		this.jmsManager = jmsManager;
		this.cardfacevalue = cardfacevalue;
		this.chargingpartynumber = chargingpartynumber;
		this.currentuserstate = currentuserstate;
		this.loanindicator = loanindicator;
		this.olduserstate = olduserstate;
		this.paytype = paytype;
		this.postpaidbalance = postpaidbalance;
		this.postpaidbalancebefore = postpaidbalancebefore;
		this.prepaidbalance = prepaidbalance;
		this.prepaidbalancebefore = prepaidbalancebefore;
		this.rechargeforpostpaid = rechargeforpostpaid;
		this.rechargeforprepaid = rechargeforprepaid;
		this.tradetype = tradetype;
		this.transistionid = transistionid;
		this.voucherbatchnumber = voucherbatchnumber;
		this.vouchersequence = vouchersequence;
		this.voutime = voutime;
	}

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Subscriber subscriber = queryManager.createSubscriber(chargingpartynumber);

		if (queryManager.getSubscriberHistoryBySubscriberAndRechargeTime(subscriber.getMsisdn(), new Timestamp(voutime)) != null)
			return true;

		log.info("vouProcessing:" + chargingpartynumber + " time:" + new Timestamp(voutime));

		BigDecimal cardFaceValue = BigDecimal.valueOf(cardfacevalue).divide(BigDecimal.valueOf(100));
		
		BigDecimal postpaidBalance = postpaidbalance != 0 
				? BigDecimal.valueOf(postpaidbalance).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

		BigDecimal postpaidBalanceBefore = postpaidbalancebefore != 0 
				? BigDecimal.valueOf(postpaidbalancebefore).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

		BigDecimal prepaidBalance = prepaidbalance != 0 
				? BigDecimal.valueOf(prepaidbalance).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

		BigDecimal prepaidBalanceBefore = prepaidbalancebefore != 0 
				? BigDecimal.valueOf(prepaidbalancebefore).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

		BigDecimal rechargeForPostpaid = rechargeforpostpaid != 0 
				? BigDecimal.valueOf(rechargeforpostpaid).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

		BigDecimal rechargeForPrepaid = rechargeforprepaid != 0 
				? BigDecimal.valueOf(rechargeforprepaid).divide(BigDecimal.valueOf(100)) : BigDecimal.valueOf(0);

		Integer trade = null;
		boolean other = false;

		trade = Integer.valueOf(tradetype);

		if (trade >= new Integer(1000))
			other = true;

		TradeType tradeType = other ? TradeType.OTHER : TradeType.fromCode(tradetype);

		updateSubscriberState(prepaidBalance, postpaidBalance, subscriber);

		queryManager.createNewSubscriberHistoryData(cardFaceValue, 
				postpaidBalance, postpaidBalanceBefore, prepaidBalance, prepaidBalanceBefore, rechargeForPostpaid, rechargeForPrepaid, tradeType, subscriber, 
				currentuserstate, voutime, 
				transistionid, voucherbatchnumber, vouchersequence, olduserstate, paytype, 
				loanindicator);
		
		queryManager.resetSubscriberAssessmentInitTime(subscriber);
		
		if (applicationBean.isAgileRas())
			jmsManager.queueMsisdnForRiskAssessment(subscriber.getMsisdn());
		
		stopWatch.stop();
		log.info("Time taken to comlete VOU data crunching:" + stopWatch.getTime() + "ms");
		
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
			BigDecimal postpaidBalance, Subscriber subscriber) {

		PayType payType = PayType.fromCode(paytype);
		
		BigDecimal currentBalance = payType != null && payType.equals(PayType.PREPAID) ? prepaidBalance : postpaidBalance ;

		ActiveStatus activeStatus = null;

		String currentUserState = currentuserstate;

		boolean blacklisted = false;

		if (currentUserState != null && !currentUserState.isEmpty()){
			activeStatus = ActiveStatus.fromCode(currentUserState.substring(0, 1));

			if (currentUserState.substring(currentUserState.length() - 1, currentUserState.length()).equalsIgnoreCase("1"))
				blacklisted = true ;
		}

		activeStatus = activeStatus != null ? activeStatus : ActiveStatus.ACTIVE;

		SubscriberState subscriberState = queryManager.getSubscriberStateByMsisdn(subscriber.getMsisdn());
		if (subscriberState == null)
			queryManager.createSubscriberState(activeStatus, blacklisted, currentBalance, payType, subscriber.getMsisdn());
		else
			queryManager.updateSubscriberState(subscriberState, activeStatus, blacklisted, currentBalance, payType, subscriber.getMsisdn());
		
		//queryManager.createOrUpdateSubscriberState(activeStatus, blacklisted, currentBalance, payType, subscriber.getMsisdn());
	}

}