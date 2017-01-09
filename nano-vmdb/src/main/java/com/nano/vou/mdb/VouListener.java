package com.nano.vou.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;

import com.nano.vou.tools.DataExecutorManager;

/**
 * The listener interface for receiving CDRV events.
 * The class that is interested in processing a CDRV
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCDRVListener<code> method. When
 * the CDRV event occurs, that object's appropriate
 * method is invoked.
 *
 * @author segz
 */

@ResourceAdapter(value = "activemq")
@MessageDriven(mappedName = "queue/Vou", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/Vou"), 
		@ActivationConfigProperty(propertyName = "maxSessions", propertyValue = "200"), 
		@ActivationConfigProperty(propertyName = "enableBatch", propertyValue = "true"), 
		@ActivationConfigProperty(propertyName = "maxMessagesPerBatch", propertyValue = "500"), 
		@ActivationConfigProperty(propertyName = "useRAManagedTransaction", propertyValue = "true"), 
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class VouListener implements MessageListener {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private DataExecutorManager dataExecutorManager ;
	
	@Resource
	private MessageDrivenContext messageDrivenContext ;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		MapMessage mapMessage = ((MapMessage) message);
		
		try {
			Integer accessmethod = mapMessage.getInt("accessmethod");
			Integer brandid = mapMessage.getInt("brandid");
			Integer cardfacevalue = mapMessage.getInt("cardfacevalue");
			Integer cardvalidityadded = mapMessage.getInt("cardvalifityadded");
			Integer cardvalueadded = mapMessage.getInt("cardvalueadded");
			Integer loanindicator =  mapMessage.getInt("loanindicator");
			Integer postpaidbalance = mapMessage.getInt("postpaidbalance");
			Integer postpaidbalancebefore = mapMessage.getInt("postpaidbalancebefore");
			Integer prepaidbalance = mapMessage.getInt("prepaidbalance");
			Integer prepaidbalancebefore = mapMessage.getInt("prepaidbalancebefore");
			Integer rechargeareanumber = mapMessage.getInt("rechargeareanumber");
			Integer rechargeforpostpaid = mapMessage.getInt("rechargeforpostpaid");
			Integer rechargeforprepaid = mapMessage.getInt("rechargeforprepaid");
			Integer rechargepenalty = mapMessage.getInt("rechargepenalty");
			Integer rechargetax = mapMessage.getInt("rechargetax");
			Integer resultcode = mapMessage.getInt("resultcode");
			Integer rewardcycles = mapMessage.getInt("rewardcycles");
			Integer subcosid = mapMessage.getInt("subcosid");
			Integer validityadded = mapMessage.getInt("validityadded");
			Integer vouchercosid = mapMessage.getInt("vouchercosid");
			
			Long accounttype1 = mapMessage.getLong("accounttype1");
			Long accounttype2 = mapMessage.getLong("accounttype2");
			Long accounttype3 = mapMessage.getLong("accounttype3");
			Long accounttype4 = mapMessage.getLong("accounttype4");
			Long accounttype5 = mapMessage.getLong("accounttype5");
			Long accounttype6 = mapMessage.getLong("accounttype6");
			Long accounttype7 = mapMessage.getLong("accounttype7");
			Long accounttype8 = mapMessage.getLong("accounttype8");
			Long accounttype9 = mapMessage.getLong("accounttype9");
			Long accounttype10 = mapMessage.getLong("accounttype10");
			Long chargeamount1 = mapMessage.getLong("chargeamount1");
			Long chargeamount2 = mapMessage.getLong("chargeamount2");
			Long chargeamount3 = mapMessage.getLong("chargeamount3");
			Long chargeamount4 = mapMessage.getLong("chargeamount4");
			Long chargeamount5 = mapMessage.getLong("chargeamount5");
			Long chargeamount6 = mapMessage.getLong("chargeamount6");
			Long chargeamount7 = mapMessage.getLong("chargeamount7");
			Long chargeamount8 = mapMessage.getLong("chargeamount8");
			Long chargeamount9 = mapMessage.getLong("chargeamount9");
			Long chargeamount10 = mapMessage.getLong("chargeamount10");
			Long currencycode = mapMessage.getLong("currencycode");
			Long currentacctamount1 = mapMessage.getLong("currentacctamount1");
			Long currentacctamount2 = mapMessage.getLong("currentacctamount2");
			Long currentacctamount3 = mapMessage.getLong("currentacctamount3");
			Long currentacctamount4 = mapMessage.getLong("currentacctamount4");
			Long currentacctamount5 = mapMessage.getLong("currentacctamount5");
			Long currentacctamount6 = mapMessage.getLong("currentacctamount6");
			Long currentacctamount7 = mapMessage.getLong("currentacctamount7");
			Long currentacctamount8 = mapMessage.getLong("currentaactamount8");
			Long currentacctamount9 = mapMessage.getLong("currentacctamount9");
			Long currentacctamount10 = mapMessage.getLong("currentacctamount10");
			Long voutime = mapMessage.getLong("voutime");
			
			String additionalinfo = mapMessage.getString("additionalinfo");
			String agentname = mapMessage.getString("agentname");
			String billcycleid = mapMessage.getString("billcycleid");
			String bonusvalidity1 = mapMessage.getString("bonusvalidity1");
			String bonusvalidity2 = mapMessage.getString("bonusvalidity2");
			String bonusvalidity3 = mapMessage.getString("bonusvalidity3");
			String bonusvalidity4 = mapMessage.getString("bonusvalidity4");
			String bonusvalidity5 = mapMessage.getString("bonusvalidity5");
			String bonusvalidity6 = mapMessage.getString("bonusvalidity6");
			String bonusvalidity7 = mapMessage.getString("bonusvalidity7");
			String bonusvalidity8 = mapMessage.getString("bonusvalidity8");
			String bonusvalidity9 = mapMessage.getString("bonusvalidity9");
			String bonusvalidity10 = mapMessage.getString("bonusvalidity10");
			String callingcellid = mapMessage.getString("callingcellid");
			String currentuserstate = mapMessage.getString("currentuserstate");
			String chargingpartynumber = mapMessage.getString("chargingpartynumber");
			String disablestop = mapMessage.getString("disablestop");
			String accountid = mapMessage.getString("accountid");
			String newactivestop = mapMessage.getString("newactivestop");
			String olduserstate = mapMessage.getString("olduserstate");
			String operatedby = mapMessage.getString("operatedby");
			String paytype = mapMessage.getString("paytype");
			String previousactivestop = mapMessage.getString("previousactivestop");
			String previousdisablestop = mapMessage.getString("previousdisablestop");
			String previoussuspendstop = mapMessage.getString("previoussuspendstop");
			String productid = mapMessage.getString("productid");
			String rebateschemaid = mapMessage.getString("rebateschemaid");
			String serialno = mapMessage.getString("serialno");
			String servicetype = mapMessage.getString("servicetype");
			String subscriberid = mapMessage.getString("subscriberid");
			String suspendstop = mapMessage.getString("suspendstop");
			String thirdpartynumber = mapMessage.getString("thirdpartynumber");
			String timestamp = mapMessage.getString("timestamp");
			String tradetype = mapMessage.getString("tradetype");
			String transistionid = mapMessage.getString("transistionid");
			String voucherbatchnumber = mapMessage.getString("voucherbatchnumber");
			String voucherencryptionnumber = mapMessage.getString("voucherencryptionnumber");
			String voucherpinnumber = mapMessage.getString("voucherpinnumber");
			String vouchersequence = mapMessage.getString("vouchersequence");
			String voucherspid = mapMessage.getString("voucherspid");
			
			if (!dataExecutorManager.handleParallelVouDataHandler(accessmethod, accountid, 
					accounttype1, accounttype2, accounttype3, accounttype4, accounttype5, accounttype6, accounttype7, accounttype8, accounttype9, accounttype10, 
					additionalinfo, agentname, billcycleid, 
					bonusvalidity1, bonusvalidity2, bonusvalidity3, bonusvalidity4, bonusvalidity5, bonusvalidity6, bonusvalidity7, bonusvalidity8, bonusvalidity9, bonusvalidity10, 
					brandid, callingcellid, cardfacevalue, cardvalidityadded, cardvalueadded, 
					chargeamount1, chargeamount2, chargeamount3, chargeamount4, chargeamount5, chargeamount6, chargeamount7, chargeamount8, chargeamount9, chargeamount10, 
					currencycode, 
					currentacctamount1, currentacctamount2, currentacctamount3, currentacctamount4, currentacctamount5, currentacctamount6, currentacctamount7, currentacctamount8, currentacctamount9, currentacctamount10, 
					currentuserstate, chargingpartynumber, disablestop, 
					loanindicator != null ? loanindicator : -1, newactivestop, olduserstate, operatedby, paytype, 
					postpaidbalance, postpaidbalancebefore, prepaidbalance, prepaidbalancebefore, 
					previousactivestop, previousdisablestop, previoussuspendstop, 
					productid, rebateschemaid, rechargeareanumber, 
					rechargeforpostpaid, rechargeforprepaid, 
					rechargepenalty, rechargetax, resultcode, rewardcycles, serialno, servicetype, subcosid, subscriberid, suspendstop, 
					thirdpartynumber, timestamp, tradetype, transistionid, validityadded, 
					voucherbatchnumber, vouchercosid, voucherencryptionnumber, voucherpinnumber, vouchersequence, voucherspid, 
					voutime));
				messageDrivenContext.setRollbackOnly();
		} catch (IllegalStateException | JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

}