package com.nano.vou.tools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.nano.vou.jbeans.ApplicationBean;
import com.nano.vou.mdb.handlers.VouDataThread;

@Stateless
public class DataExecutorManager {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private ManagedExecutorService managedExecutorService ;
	
	@Resource(lookup = "java:jboss/ee/concurrency/executor/vouexecutors")
	private ManagedExecutorService vouDataExecutors ;
	
	@Inject
	private QueryManager queryManager ;
	
	@Inject
	private ApplicationBean applicationBean ;
	
	@Inject
	private JmsManager jmsManager ;
	
	/**
	 * Handles parallel execution of VouDataHandler via a single EJB invocation.
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
	public boolean handleParallelVouDataHandler(int accessmethod, 
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
		
		boolean task = false;
		
		VouDataThread vouDataThread = new VouDataThread(queryManager, applicationBean, jmsManager, 
				accessmethod, accountid, 
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
				voutime);
		
		try {
			Future<Boolean> job =  vouDataExecutors.submit(vouDataThread);
			if (job.get())
				task = true;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		
		return task;
	}

}