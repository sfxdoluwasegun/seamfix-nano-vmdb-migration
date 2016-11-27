package com.nano.vou.operations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.nano.jpa.entity.SubscriberHistory;
import com.nano.jpa.entity.SubscriberState;
import com.nano.vou.tools.JmsManager;

/**
 * 
 * @author segz
 *
 */

@Stateless
public class DataExtractor {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private JmsManager jmsManager ;
	
	/**
	 * Initialize {@link VouCDR} with data from file and 
	 * handle {@link SubscriberState} and {@link SubscriberHistory} capture.
	 * 
	 * @param cdrdata
	 */
	public void queueCDRLineForProcessing(String[] cdrdata) {
		// TODO Auto-generated method stub
		
		if (cdrdata == null)
			return;
		
		LocalDateTime localDateTime = LocalDateTime.parse(cdrdata[1], DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		LocalDateTime localDateTime2 = LocalDateTime.parse(cdrdata[14], DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		String previoussuspendstop = "";
		String previousdisablestop = "";
		String agentname = "";
		String additionalinfo = "";
		
		long serialno = (cdrdata[0] == null || cdrdata[0].isEmpty()) ? 0 : Long.parseLong(cdrdata[0]);
		Timestamp timestamp = localDateTime == null ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(localDateTime);
		int brandid = (cdrdata[2] == null || cdrdata[2].isEmpty()) ? 0 : Integer.parseInt(cdrdata[2]);
		int subcosid = (cdrdata[3] == null || cdrdata[3].isEmpty()) ? 0 : Integer.parseInt(cdrdata[3]); 
		String chargingpartynumber = cdrdata[4];
		String paytype = cdrdata[5];
		String voucherpinnumber = cdrdata[6]; 
		String voucherbatchnumber = cdrdata[7];
		String vouchersequence = cdrdata[8]; 
		Integer vouchercosid = (cdrdata[9] == null || cdrdata[9].isEmpty()) ? 0 : Integer.parseInt(cdrdata[9]); 
		String voucherspid = cdrdata[10];
		int cardfacevalue = (cdrdata[11] == null || cdrdata[11].isEmpty()) ? 0 : Integer.parseInt(cdrdata[11]); 
		int cardvalueadded = (cdrdata[12] == null || cdrdata[12].isEmpty()) ? 0 : Integer.parseInt(cdrdata[12]);
		int cardvalidityadded = (cdrdata[13] == null || cdrdata[13].isEmpty()) ? 0 : Integer.parseInt(cdrdata[13]);
		Timestamp tradetime = localDateTime2 == null ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(localDateTime2); 
		String operatedby = cdrdata[15];
		String msisdn = cdrdata[16];
		int accessmethod = (cdrdata[17] == null || cdrdata[17].isEmpty()) ? 0 : Integer.parseInt(cdrdata[17]);
		String tradetype = cdrdata[18];
		Integer rechargeareanumber = (cdrdata[19] == null || cdrdata[19].isEmpty()) ? 0 : Integer.parseInt(cdrdata[19]);
		String trasitionid = cdrdata[20];
		String voucherencryptionnumber = cdrdata[21];
		String callingcellid = cdrdata[22];
		String productid = cdrdata[25]; 
		String servicetype = cdrdata[26];
		String olduserstate = cdrdata[27];
		String currentuserstate = cdrdata[28];
		String previousactivestop = cdrdata[29];
		String newactivestop = cdrdata[30];
		String suspendstop = cdrdata[31]; 
		String disablestop = cdrdata[32];
		int validityadded = (cdrdata[33] == null || cdrdata[33].isEmpty()) ? 0 : Integer.parseInt(cdrdata[33]);
		int rewardcycles = (cdrdata[34] == null || cdrdata[34].isEmpty()) ? 0 : Integer.parseInt(cdrdata[34]);
		String rebateschemaid = cdrdata[35];
		String billcycleid = cdrdata[36];
		String subscriberid = cdrdata[37];
		int loanindicator = (cdrdata[38] == null || cdrdata[38].isEmpty()) ? 0 : Integer.parseInt(cdrdata[38]);
		Integer resultcode = (cdrdata[39] == null || cdrdata[39].isEmpty()) ? 0 : Integer.parseInt(cdrdata[39]);
		String accountid = cdrdata[40];
		Integer rechargetax = (cdrdata[41] == null || cdrdata[41].isEmpty()) ? 0 : Integer.parseInt(cdrdata[41]);
		Integer rechargepenalty = (cdrdata[42] == null || cdrdata[42].isEmpty()) ? 0 : Integer.parseInt(cdrdata[42]);
		Integer currencycode = (cdrdata[43] == null || cdrdata[43].isEmpty()) ? 0 : Integer.parseInt(cdrdata[43]);
		int prepaidbalancebefore = (cdrdata[45] == null || cdrdata[45].isEmpty()) ? 0 : Integer.parseInt(cdrdata[45]);
		int rechargeforprepaid = (cdrdata[46] == null || cdrdata[46].isEmpty()) ? 0 : Integer.parseInt(cdrdata[46]);
		int prepaidbalance = (cdrdata[47] == null || cdrdata[47].isEmpty()) ? 0 : Integer.parseInt(cdrdata[47]); 
		int postpaidbalancebefore = (cdrdata[48] == null || cdrdata[48].isEmpty()) ? 0 : Integer.parseInt(cdrdata[48]);
		int rechargeforpostpaid = (cdrdata[49] == null || cdrdata[49].isEmpty()) ? 0 : Integer.parseInt(cdrdata[49]);
		int postpaidbalance = (cdrdata[50] == null || cdrdata[50].isEmpty()) ? 0 : Integer.parseInt(cdrdata[50]);
		int accounttype1 = (cdrdata[51] == null || cdrdata[51].isEmpty()) ? 0 : Integer.parseInt(cdrdata[51]);
		long chargeamount1 = (cdrdata[52] == null || cdrdata[52].isEmpty()) ? 0 : Long.parseLong(cdrdata[52]);
		long currentacctamount1 = (cdrdata[53] == null || cdrdata[53].isEmpty()) ? 0 : Long.parseLong(cdrdata[53]);
		long accounttype2 = (cdrdata[54] == null || cdrdata[54].isEmpty()) ? 0 : Long.parseLong(cdrdata[54]);
		long chargeamount2 = (cdrdata[55] == null || cdrdata[55].isEmpty()) ? 0 : Long.parseLong(cdrdata[55]);
		long currentacctamount2 = (cdrdata[56] == null || cdrdata[56].isEmpty()) ? 0 : Long.parseLong(cdrdata[56]);
		long accounttype3 = (cdrdata[57] == null || cdrdata[55].isEmpty()) ? 0 : Long.parseLong(cdrdata[57]);
		long chargeamount3 = (cdrdata[58] == null || cdrdata[58].isEmpty()) ? 0 : Long.parseLong(cdrdata[58]);
		long currentacctamount3 = (cdrdata[59] == null || cdrdata[59].isEmpty()) ? 0 : Long.parseLong(cdrdata[59]);
		long accounttype4 = (cdrdata[60] == null || cdrdata[60].isEmpty()) ? 0 : Long.parseLong(cdrdata[60]);
		long chargeamount4 = (cdrdata[61] == null || cdrdata[61].isEmpty()) ? 0 : Long.parseLong(cdrdata[61]);
		long currentacctamount4 = (cdrdata[62] == null || cdrdata[62].isEmpty()) ? 0 : Long.parseLong(cdrdata[62]);
		long accounttype5 = (cdrdata[63] == null || cdrdata[63].isEmpty()) ? 0 : Long.parseLong(cdrdata[63]);
		long chargeamount5 = (cdrdata[64] == null || cdrdata[64].isEmpty()) ? 0 : Long.parseLong(cdrdata[64]);
		long currentacctamount5 = (cdrdata[65] == null || cdrdata[65].isEmpty()) ? 0 : Long.parseLong(cdrdata[65]);
		long accounttype6 = (cdrdata[66] == null || cdrdata[66].isEmpty()) ? 0 : Long.parseLong(cdrdata[66]);
		long chargeamount6 = (cdrdata[67] == null || cdrdata[67].isEmpty()) ? 0 : Long.parseLong(cdrdata[67]);
		long currentacctamount6 = (cdrdata[68] == null || cdrdata[68].isEmpty()) ? 0 : Long.parseLong(cdrdata[68]);
		long accounttype7 = (cdrdata[69] == null || cdrdata[69].isEmpty()) ? 0 : Long.parseLong(cdrdata[69]);
		long chargeamount7 = (cdrdata[70] == null || cdrdata[70].isEmpty()) ? 0 : Long.parseLong(cdrdata[70]);
		long currentacctamount7 = (cdrdata[71] == null || cdrdata[71].isEmpty()) ? 0 : Long.parseLong(cdrdata[71]);
		long accounttype8 = (cdrdata[72] == null || cdrdata[72].isEmpty()) ? 0 : Long.parseLong(cdrdata[72]);
		long chargeamount8 = (cdrdata[73] == null || cdrdata[73].isEmpty()) ? 0 : Long.parseLong(cdrdata[73]);
		long currentacctamount8 = (cdrdata[74] == null || cdrdata[74].isEmpty()) ? 0 : Long.parseLong(cdrdata[74]);
		long accounttype9 = (cdrdata[75] == null || cdrdata[75].isEmpty()) ? 0 : Long.parseLong(cdrdata[75]);
		long chargeamount9 = (cdrdata[76] == null || cdrdata[76].isEmpty()) ? 0 : Long.parseLong(cdrdata[76]);
		long currentacctamount9 = (cdrdata[77] == null || cdrdata[77].isEmpty()) ? 0 : Long.parseLong(cdrdata[77]);
		long accounttype10 = (cdrdata[78] == null || cdrdata[78].isEmpty()) ? 0 : Long.parseLong(cdrdata[78]);
		long chargeamount10 = (cdrdata[79] == null || cdrdata[79].isEmpty()) ? 0 : Long.parseLong(cdrdata[79]);
		long currentacctamount10 = (cdrdata[80] == null || cdrdata[80].isEmpty()) ? 0 : Long.parseLong(cdrdata[80]);
		String bonusvalidity1 = cdrdata[81];
		String bonusvalidity2 = cdrdata[82];
		String bonusvalidity3 = cdrdata[83]; 
		String bonusvalidity4 = cdrdata[84];
		String bonusvalidity5 = cdrdata[85];
		String bonusvalidity6 = cdrdata[86]; 
		String bonusvalidity7 = cdrdata[87];
		String bonusvalidity8 = cdrdata[88];
		String bonusvalidity9 = cdrdata[89]; 
		String bonusvalidity10 = cdrdata[90];
		
		try {
			previoussuspendstop = cdrdata[91];
			previousdisablestop = cdrdata[92];
			agentname = cdrdata[93];
			additionalinfo = cdrdata[94];
		} catch (ArrayIndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
			//log.error("", e1);
		}
		
		try {
			jmsManager.queueVouCDRData(accessmethod, accountid, 
					accounttype1, accounttype2, accounttype3, accounttype4, accounttype5, 
					accounttype6, accounttype7, accounttype8, accounttype9, accounttype10, 
					additionalinfo, agentname, billcycleid, 
					bonusvalidity1, bonusvalidity2, bonusvalidity3, bonusvalidity4, bonusvalidity5, 
					bonusvalidity6, bonusvalidity7, bonusvalidity8, bonusvalidity9, bonusvalidity10, 
					brandid, callingcellid, cardfacevalue, cardvalidityadded, cardvalueadded, 
					chargeamount1, chargeamount2, chargeamount3, chargeamount4, chargeamount5, 
					chargeamount6, chargeamount7, chargeamount8, chargeamount9, chargeamount10, 
					currencycode, 
					currentacctamount1, currentacctamount2, currentacctamount3, currentacctamount4, currentacctamount5, 
					currentacctamount6, currentacctamount7, currentacctamount8, currentacctamount9, currentacctamount10, 
					currentuserstate, chargingpartynumber, disablestop, loanindicator, newactivestop, olduserstate, 
					operatedby, paytype, postpaidbalance, postpaidbalancebefore, prepaidbalance, prepaidbalancebefore, 
					previousactivestop, previousdisablestop, previoussuspendstop, productid, rebateschemaid, 
					rechargeareanumber, rechargeforpostpaid, rechargeforprepaid, rechargepenalty, rechargetax, 
					resultcode, rewardcycles, String.valueOf(serialno), servicetype, subcosid, subscriberid, suspendstop, 
					msisdn, timestamp.toString(), tradetype, trasitionid, validityadded, voucherbatchnumber, 
					vouchercosid, voucherencryptionnumber, voucherpinnumber, vouchersequence, voucherspid, 
					tradetime.getTime());
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}
	
}