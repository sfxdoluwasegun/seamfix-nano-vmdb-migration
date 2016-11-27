package com.nano.vou.tools;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.jboss.logging.Logger;

/**
 * Manages application {@link Connection} and {@link JMSProducer}.
 * 
 * @author segz
 *
 */

@Stateless
public class JmsManager {
	
	private Logger log = Logger.getLogger(getClass());

	private Connection connection ;
	private Session session ;

	@Resource(lookup = "java:/AmqConnectionFactory")
	private ConnectionFactory connectionFactory ;
	
	@Resource(lookup = "java:/jms/queue/AgileRas")
	private Queue rasQueue ;
	
	@Resource(lookup = "java:/jms/queue/Vou")
	private Queue vouQueue ;

	public void init(){
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

	public void cleanup(){
		closeSession(session);
		closeConnection(connection);
	}

	/**
	 * Close a used {@link JMSProducer}.
	 * 
	 * @param messageProducer
	 */
	private void closeProducer(MessageProducer messageProducer){

		if (messageProducer != null)
			try {
				messageProducer.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
	}

	/**
	 * Close an opened JMSSession.
	 * 
	 * @param session
	 */
	private void closeSession(Session session){

		if (session != null)
			try {
				session.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
	}

	/**
	 * Close an opened JMSConnection.
	 * 
	 * @param connection
	 */
	private void closeConnection(Connection connection){

		if (connection != null)
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
	}
	
	/**
	 * Queue MSISDN data for spontaneous RAS processing.
	 * 
	 * @param msisdn
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void queueMsisdnForRiskAssessment(String msisdn){
		
		init();
		MessageProducer messageProducer = null;

		try {
			messageProducer = session.createProducer(rasQueue);
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setString("msisdn", msisdn);

			messageProducer.send(mapMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} finally {
			closeProducer(messageProducer);
			cleanup();
		}
	}
	
	/**
	 * Queue VOU info using <code>MapMessage</code> for optimization
	 * 
	 * @param accessmethod 
	 * @param accountid 
	 * @param accounttype1 
	 * @param accounttype2 
	 * @param accounttype3 
	 * @param accounttype4 
	 * @param accounttype5 
	 * @param accounttype6 
	 * @param accounttype7 
	 * @param accounttype8 
	 * @param accounttype9 
	 * @param accounttype10 
	 * @param additionalinfo 
	 * @param agentname 
	 * @param billcycleid 
	 * @param bonusvalidity1 
	 * @param bonusvalidity2 
	 * @param bonusvalidity3 
	 * @param bonusvalidity4 
	 * @param bonusvalidity5 
	 * @param bonusvalidity6 
	 * @param bonusvalidity7 
	 * @param bonusvalidity8 
	 * @param bonusvalidity9 
	 * @param bonusvalidity10 
	 * @param brandid 
	 * @param callingcellid 
	 * @param cardfacevalue 
	 * @param cardvalidityadded 
	 * @param cardvalueadded 
	 * @param chargeamount1 
	 * @param chargeamount2 
	 * @param chargeamount3 
	 * @param chargeamount4 
	 * @param chargeamount5 
	 * @param chargeamount6 
	 * @param chargeamount7 
	 * @param chargeamount8 
	 * @param chargeamount9 
	 * @param chargeamount10 
	 * @param currencycode 
	 * @param currentacctamount1 
	 * @param currentacctamount2 
	 * @param currentacctamount3 
	 * @param currentacctamount4 
	 * @param currentacctamount5 
	 * @param currentacctamount6 
	 * @param currentacctamount7 
	 * @param currentacctamount8 
	 * @param currentacctamount9 
	 * @param currentacctamount10 
	 * @param currentuserstate 
	 * @param chargingpartynumber 
	 * @param disablestop 
	 * @param loanindicator 
	 * @param newactivestop 
	 * @param olduserstate 
	 * @param operatedby 
	 * @param paytype 
	 * @param postpaidbalance 
	 * @param postpaidbalancebefore 
	 * @param prepaidbalance 
	 * @param prepaidbalancebefore 
	 * @param previousactivestop 
	 * @param previousdisablestop 
	 * @param previoussuspendstop 
	 * @param productid 
	 * @param rebateschemaid 
	 * @param rechargeareanumber 
	 * @param rechargeforpostpaid 
	 * @param rechargeforprepaid 
	 * @param rechargepenalty 
	 * @param rechargetax 
	 * @param resultcode 
	 * @param rewardcycles 
	 * @param serialno 
	 * @param servicetype 
	 * @param subcosid 
	 * @param subscriberid 
	 * @param suspendstop 
	 * @param thirdpartynumber 
	 * @param timestamp 
	 * @param tradetype 
	 * @param transistionid 
	 * @param validityadded 
	 * @param voucherbatchnumber 
	 * @param vouchercosid 
	 * @param voucherencryptionnumber 
	 * @param voucherpinnumber 
	 * @param vouchersequence 
	 * @param voucherspid 
	 * @param voutime 
	 */
	public void queueVouCDRData(int accessmethod, 
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
			long voutime){

		init();
		MessageProducer messageProducer = null;

		try {
			messageProducer = session.createProducer(vouQueue);
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setInt("accessmethod", accessmethod);
			mapMessage.setString("accountid", accountid);
			mapMessage.setLong("accounttype1", accounttype1);
			mapMessage.setLong("accounttype2", accounttype2);
			mapMessage.setLong("accounttype3", accounttype3);
			mapMessage.setLong("accounttype4", accounttype4);
			mapMessage.setLong("accounttype5", accounttype5);
			mapMessage.setLong("accounttype6", accounttype6);
			mapMessage.setLong("accounttype7", accounttype7);
			mapMessage.setLong("accounttype8", accounttype8);
			mapMessage.setLong("accounttype9", accounttype9);
			mapMessage.setLong("accounttype10", accounttype10);
			mapMessage.setString("additionalinfo", additionalinfo);
			mapMessage.setString("agentname", agentname);
			mapMessage.setString("billcycleid", billcycleid);
			mapMessage.setString("bonusvalidity1", bonusvalidity1);
			mapMessage.setString("bonusvalidity2", bonusvalidity2);
			mapMessage.setString("bonusvalidity3", bonusvalidity3);
			mapMessage.setString("bonusvalidity4", bonusvalidity4);
			mapMessage.setString("bonusvalidity5", bonusvalidity5);
			mapMessage.setString("bonusvalidity6", bonusvalidity6);
			mapMessage.setString("bonusvalidity7", bonusvalidity7);
			mapMessage.setString("bonusvalidity8", bonusvalidity8);
			mapMessage.setString("bonusvalidity9", bonusvalidity9);
			mapMessage.setString("bonusvalidity10", bonusvalidity10);
			mapMessage.setInt("brandid", brandid);
			mapMessage.setString("callingcellid", callingcellid);
			mapMessage.setInt("cardfacevalue", cardfacevalue);
			mapMessage.setInt("cardvalifityadded", cardvalidityadded);
			mapMessage.setInt("cardvalueadded", cardvalueadded);
			mapMessage.setLong("chargeamount1", chargeamount1);
			mapMessage.setLong("chargeamount2", chargeamount2);
			mapMessage.setLong("chargeamount3", chargeamount3);
			mapMessage.setLong("chargeamount4", chargeamount4);
			mapMessage.setLong("chargeamount5", chargeamount5);
			mapMessage.setLong("chargeamount6", chargeamount6);
			mapMessage.setLong("chargeamount7", chargeamount7);
			mapMessage.setLong("chargeamount8", chargeamount8);
			mapMessage.setLong("chargeamount9", chargeamount9);
			mapMessage.setLong("chargeamount10", chargeamount10);
			mapMessage.setLong("currencycode", currencycode);
			mapMessage.setLong("currentacctamount1", currentacctamount1);
			mapMessage.setLong("currentacctamount2", currentacctamount2);
			mapMessage.setLong("currentacctamount3", currentacctamount3);
			mapMessage.setLong("currentacctamount4", currentacctamount4);
			mapMessage.setLong("currentacctamount5", currentacctamount5);
			mapMessage.setLong("currentacctamount6", currentacctamount6);
			mapMessage.setLong("currentacctamount7", currentacctamount7);
			mapMessage.setLong("currentaactamount8", currentacctamount8);
			mapMessage.setLong("currentacctamount9", currentacctamount9);
			mapMessage.setLong("currentacctamount10", currentacctamount10);
			mapMessage.setString("currentuserstate", currentuserstate);
			mapMessage.setString("chargingpartynumber", chargingpartynumber);
			mapMessage.setString("disablestop", disablestop);
			mapMessage.setInt("loanindicator", loanindicator != null ? loanindicator : -1);
			mapMessage.setString("newactivestop", newactivestop);
			mapMessage.setString("olduserstate", olduserstate);
			mapMessage.setString("operatedby", operatedby);
			mapMessage.setString("paytype", paytype);
			mapMessage.setInt("postpaidbalance", postpaidbalance);
			mapMessage.setInt("postpaidbalancebefore", postpaidbalancebefore);
			mapMessage.setInt("prepaidbalance", prepaidbalance);
			mapMessage.setInt("prepaidbalancebefore", prepaidbalancebefore);
			mapMessage.setString("previousactivestop", previousactivestop);
			mapMessage.setString("previousdisablestop", previousdisablestop);
			mapMessage.setString("previoussuspendstop", previoussuspendstop);
			mapMessage.setString("productid", productid);
			mapMessage.setString("rebateschemaid", rebateschemaid);
			mapMessage.setInt("rechargeareanumber", rechargeareanumber);
			mapMessage.setInt("rechargeforpostpaid", rechargeforpostpaid);
			mapMessage.setInt("rechargeforprepaid", rechargeforprepaid);
			mapMessage.setInt("rechargepenalty", rechargepenalty);
			mapMessage.setInt("rechargetax", rechargetax);
			mapMessage.setInt("resultcode", resultcode);
			mapMessage.setInt("rewardcycles", rewardcycles);
			mapMessage.setString("serialno", serialno);
			mapMessage.setString("servicetype", servicetype);
			mapMessage.setInt("subcosid", subcosid);
			mapMessage.setString("subscriberid", subscriberid);
			mapMessage.setString("suspendstop", suspendstop);
			mapMessage.setString("thirdpartynumber", thirdpartynumber);
			mapMessage.setString("timestamp", timestamp);
			mapMessage.setString("tradetype", tradetype);
			mapMessage.setString("transistionid", transistionid);
			mapMessage.setInt("validityadded", validityadded);
			mapMessage.setString("voucherbatchnumber", voucherbatchnumber);
			mapMessage.setInt("vouchercosid", vouchercosid);
			mapMessage.setString("voucherencryptionnumber", voucherencryptionnumber);
			mapMessage.setString("voucherpinnumber", voucherpinnumber);
			mapMessage.setString("vouchersequence", vouchersequence);
			mapMessage.setString("voucherspid", voucherspid);
			mapMessage.setLong("voutime", voutime);

			messageProducer.send(mapMessage);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} finally {
			closeProducer(messageProducer);
			cleanup();
		}
	}

}