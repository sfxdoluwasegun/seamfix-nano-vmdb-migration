package com.nano.vou.jaxb;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class CDRVou.
 *
 * @author segz
 */

@SuppressWarnings("serial")
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class CDRVou implements Serializable {
	
	private int brandid ;
	private int subcosid ;
	private int vouchercosid ;
	private int cardfacevalue ;
	private int cardvalueadded ;
	private int cardvalidityadded ;
	private int accessmethod ;
	private int rechargeareanumber ;
	private int validityadded ;
	private int rewardcycles ;
	private int loanindicator ;
	private int resultcode ;
	private int rechargetax ;
	private int rechargepenalty ;
	private int currencycode ;
	private int prepaidbalancebefore ;
	private int rechargeforprepaid ;
	private int prepaidbalance ;
	private int postpaidbalancebefore ;
	private int rechargeforpostpaid ;
	private int postpaidbalance ;
	private int accounttype1 ;
	private int chargeamount1 ;
	private int currentacctamount1 ;
	private int accounttype2 ;
	private int chargeamount2 ;
	private int currentacctamount2 ;
	private int accounttype3 ;
	private int chargeamount3 ;
	private int currentacctamount3 ;
	private int accounttype4 ;
	private int chargeamount4 ;
	private int currentacctamount4 ;
	private int accounttype5 ;
	private int chargeamount5 ;
	private int currentacctamount5 ;
	private int accounttype6 ;
	private int chargeamount6 ;
	private int currentacctamount6 ;
	private int accounttype7 ;
	private int chargeamount7 ;
	private int currentacctamount7 ;
	private int accounttype8 ;
	private int chargeamount8 ;
	private int currentacctamount8 ;
	private int accounttype9 ;
	private int chargeamount9 ;
	private int currentacctamount9 ;
	private int accounttype10 ;
	private int chargeamount10 ;
	private int currentacctamount10 ;
	
	private String serialno ;
	private String timestamp ;
	private String chargingpartynumber ;
	private String paytype ;
	private String voucherpinnumber ;
	private String voucherbatchnumber ;
	private String vouchersequence ;
	private String voucherspid ;
	private String operatedby ;
	private String thirdpartynumber ;
	private String tradetype ;
	private String transistionid ;
	private String voucherencryptionnumber ;
	private String callingcellid ;
	private String productid ;
	private String servicetype ;
	private String olduserstate ;
	private String currentuserstate ;
	private String previousactivestop ;
	private String newactivestop ;
	private String suspendstop ;
	private String disablestop ;
	private String rebateschemaid ;
	private String billcycleid ;
	private String subscriberid ;
	private String accountid ;
	private String bonusvalidity1 ;
	private String bonusvalidity2 ;
	private String bonusvalidity3 ;
	private String bonusvalidity4 ;
	private String bonusvalidity5 ;
	private String bonusvalidity6 ;
	private String bonusvalidity7 ;
	private String bonusvalidity8 ;
	private String bonusvalidity9 ;
	private String bonusvalidity10 ;
	private String previoussuspendstop ;
	private String previousdisablestop ;
	private String agentname ;
	private String additionalinfo ;
	
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp voutime ;

	/**
	 * Gets the serialno.
	 *
	 * @return the serialno
	 */
	public String getSerialno() {
		return serialno;
	}

	/**
	 * Sets the serialno.
	 *
	 * @param serialno the new serialno
	 */
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the brandid.
	 *
	 * @return the brandid
	 */
	public int getBrandid() {
		return brandid;
	}

	/**
	 * Sets the brandid.
	 *
	 * @param brandid the new brandid
	 */
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	/**
	 * Gets the subcosid.
	 *
	 * @return the subcosid
	 */
	public int getSubcosid() {
		return subcosid;
	}

	/**
	 * Sets the subcosid.
	 *
	 * @param subcosid the new subcosid
	 */
	public void setSubcosid(int subcosid) {
		this.subcosid = subcosid;
	}

	/**
	 * Gets the vouchercosid.
	 *
	 * @return the vouchercosid
	 */
	public int getVouchercosid() {
		return vouchercosid;
	}

	/**
	 * Sets the vouchercosid.
	 *
	 * @param vouchercosid the new vouchercosid
	 */
	public void setVouchercosid(int vouchercosid) {
		this.vouchercosid = vouchercosid;
	}

	/**
	 * Gets the cardfacevalue.
	 *
	 * @return the cardfacevalue
	 */
	public int getCardfacevalue() {
		return cardfacevalue;
	}

	/**
	 * Sets the cardfacevalue.
	 *
	 * @param cardfacevalue the new cardfacevalue
	 */
	public void setCardfacevalue(int cardfacevalue) {
		this.cardfacevalue = cardfacevalue;
	}

	/**
	 * Gets the cardvalueadded.
	 *
	 * @return the cardvalueadded
	 */
	public int getCardvalueadded() {
		return cardvalueadded;
	}

	/**
	 * Sets the cardvalueadded.
	 *
	 * @param cardvalueadded the new cardvalueadded
	 */
	public void setCardvalueadded(int cardvalueadded) {
		this.cardvalueadded = cardvalueadded;
	}

	/**
	 * Gets the cardvalidityadded.
	 *
	 * @return the cardvalidityadded
	 */
	public int getCardvalidityadded() {
		return cardvalidityadded;
	}

	/**
	 * Sets the cardvalidityadded.
	 *
	 * @param cardvalidityadded the new cardvalidityadded
	 */
	public void setCardvalidityadded(int cardvalidityadded) {
		this.cardvalidityadded = cardvalidityadded;
	}

	/**
	 * Gets the chargingpartynumber.
	 *
	 * @return the chargingpartynumber
	 */
	public String getChargingpartynumber() {
		return chargingpartynumber;
	}

	/**
	 * Sets the chargingpartynumber.
	 *
	 * @param chargingpartynumber the new chargingpartynumber
	 */
	public void setChargingpartynumber(String chargingpartynumber) {
		this.chargingpartynumber = chargingpartynumber;
	}

	/**
	 * Gets the paytype.
	 *
	 * @return the paytype
	 */
	public String getPaytype() {
		return paytype;
	}

	/**
	 * Sets the paytype.
	 *
	 * @param paytype the new paytype
	 */
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	/**
	 * Gets the voucherpinnumber.
	 *
	 * @return the voucherpinnumber
	 */
	public String getVoucherpinnumber() {
		return voucherpinnumber;
	}

	/**
	 * Sets the voucherpinnumber.
	 *
	 * @param voucherpinnumber the new voucherpinnumber
	 */
	public void setVoucherpinnumber(String voucherpinnumber) {
		this.voucherpinnumber = voucherpinnumber;
	}

	/**
	 * Gets the voucherbatchnumber.
	 *
	 * @return the voucherbatchnumber
	 */
	public String getVoucherbatchnumber() {
		return voucherbatchnumber;
	}

	/**
	 * Sets the voucherbatchnumber.
	 *
	 * @param voucherbatchnumber the new voucherbatchnumber
	 */
	public void setVoucherbatchnumber(String voucherbatchnumber) {
		this.voucherbatchnumber = voucherbatchnumber;
	}

	/**
	 * Gets the vouchersequence.
	 *
	 * @return the vouchersequence
	 */
	public String getVouchersequence() {
		return vouchersequence;
	}

	/**
	 * Sets the vouchersequence.
	 *
	 * @param vouchersequence the new vouchersequence
	 */
	public void setVouchersequence(String vouchersequence) {
		this.vouchersequence = vouchersequence;
	}

	/**
	 * Gets the voucherspid.
	 *
	 * @return the voucherspid
	 */
	public String getVoucherspid() {
		return voucherspid;
	}

	/**
	 * Sets the voucherspid.
	 *
	 * @param voucherspid the new voucherspid
	 */
	public void setVoucherspid(String voucherspid) {
		this.voucherspid = voucherspid;
	}

	/**
	 * Gets the accessmethod.
	 *
	 * @return the accessmethod
	 */
	public int getAccessmethod() {
		return accessmethod;
	}

	/**
	 * Sets the accessmethod.
	 *
	 * @param accessmethod the new accessmethod
	 */
	public void setAccessmethod(int accessmethod) {
		this.accessmethod = accessmethod;
	}

	/**
	 * Gets the rechargeareanumber.
	 *
	 * @return the rechargeareanumber
	 */
	public int getRechargeareanumber() {
		return rechargeareanumber;
	}

	/**
	 * Sets the rechargeareanumber.
	 *
	 * @param rechargeareanumber the new rechargeareanumber
	 */
	public void setRechargeareanumber(int rechargeareanumber) {
		this.rechargeareanumber = rechargeareanumber;
	}

	/**
	 * Gets the operatedby.
	 *
	 * @return the operatedby
	 */
	public String getOperatedby() {
		return operatedby;
	}

	/**
	 * Sets the operatedby.
	 *
	 * @param operatedby the new operatedby
	 */
	public void setOperatedby(String operatedby) {
		this.operatedby = operatedby;
	}

	/**
	 * Gets the thirdpartynumber.
	 *
	 * @return the thirdpartynumber
	 */
	public String getThirdpartynumber() {
		return thirdpartynumber;
	}

	/**
	 * Sets the thirdpartynumber.
	 *
	 * @param thirdpartynumber the new thirdpartynumber
	 */
	public void setThirdpartynumber(String thirdpartynumber) {
		this.thirdpartynumber = thirdpartynumber;
	}

	/**
	 * Gets the tradetype.
	 *
	 * @return the tradetype
	 */
	public String getTradetype() {
		return tradetype;
	}

	/**
	 * Sets the tradetype.
	 *
	 * @param tradetype the new tradetype
	 */
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	/**
	 * Gets the transistionid.
	 *
	 * @return the transistionid
	 */
	public String getTransistionid() {
		return transistionid;
	}

	/**
	 * Sets the transistionid.
	 *
	 * @param transistionid the new transistionid
	 */
	public void setTransistionid(String transistionid) {
		this.transistionid = transistionid;
	}

	/**
	 * Gets the voucherencryptionnumber.
	 *
	 * @return the voucherencryptionnumber
	 */
	public String getVoucherencryptionnumber() {
		return voucherencryptionnumber;
	}

	/**
	 * Sets the voucherencryptionnumber.
	 *
	 * @param voucherencryptionnumber the new voucherencryptionnumber
	 */
	public void setVoucherencryptionnumber(String voucherencryptionnumber) {
		this.voucherencryptionnumber = voucherencryptionnumber;
	}

	/**
	 * Gets the callingcellid.
	 *
	 * @return the callingcellid
	 */
	public String getCallingcellid() {
		return callingcellid;
	}

	/**
	 * Sets the callingcellid.
	 *
	 * @param callingcellid the new callingcellid
	 */
	public void setCallingcellid(String callingcellid) {
		this.callingcellid = callingcellid;
	}

	/**
	 * Gets the productid.
	 *
	 * @return the productid
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * Sets the productid.
	 *
	 * @param productid the new productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * Gets the servicetype.
	 *
	 * @return the servicetype
	 */
	public String getServicetype() {
		return servicetype;
	}

	/**
	 * Sets the servicetype.
	 *
	 * @param servicetype the new servicetype
	 */
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	/**
	 * Gets the olduserstate.
	 *
	 * @return the olduserstate
	 */
	public String getOlduserstate() {
		return olduserstate;
	}

	/**
	 * Sets the olduserstate.
	 *
	 * @param olduserstate the new olduserstate
	 */
	public void setOlduserstate(String olduserstate) {
		this.olduserstate = olduserstate;
	}

	/**
	 * Gets the validityadded.
	 *
	 * @return the validityadded
	 */
	public int getValidityadded() {
		return validityadded;
	}

	/**
	 * Sets the validityadded.
	 *
	 * @param validityadded the new validityadded
	 */
	public void setValidityadded(int validityadded) {
		this.validityadded = validityadded;
	}

	/**
	 * Gets the rewardcycles.
	 *
	 * @return the rewardcycles
	 */
	public int getRewardcycles() {
		return rewardcycles;
	}

	/**
	 * Sets the rewardcycles.
	 *
	 * @param rewardcycles the new rewardcycles
	 */
	public void setRewardcycles(int rewardcycles) {
		this.rewardcycles = rewardcycles;
	}

	/**
	 * Gets the loanindicator.
	 *
	 * @return the loanindicator
	 */
	public int getLoanindicator() {
		return loanindicator;
	}

	/**
	 * Sets the loanindicator.
	 *
	 * @param loanindicator the new loanindicator
	 */
	public void setLoanindicator(int loanindicator) {
		this.loanindicator = loanindicator;
	}

	/**
	 * Gets the resultcode.
	 *
	 * @return the resultcode
	 */
	public int getResultcode() {
		return resultcode;
	}

	/**
	 * Sets the resultcode.
	 *
	 * @param resultcode the new resultcode
	 */
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}

	/**
	 * Gets the rechargetax.
	 *
	 * @return the rechargetax
	 */
	public int getRechargetax() {
		return rechargetax;
	}

	/**
	 * Sets the rechargetax.
	 *
	 * @param rechargetax the new rechargetax
	 */
	public void setRechargetax(int rechargetax) {
		this.rechargetax = rechargetax;
	}

	/**
	 * Gets the rechargepenalty.
	 *
	 * @return the rechargepenalty
	 */
	public int getRechargepenalty() {
		return rechargepenalty;
	}

	/**
	 * Sets the rechargepenalty.
	 *
	 * @param rechargepenalty the new rechargepenalty
	 */
	public void setRechargepenalty(int rechargepenalty) {
		this.rechargepenalty = rechargepenalty;
	}

	/**
	 * Gets the currencycode.
	 *
	 * @return the currencycode
	 */
	public int getCurrencycode() {
		return currencycode;
	}

	/**
	 * Sets the currencycode.
	 *
	 * @param currencycode the new currencycode
	 */
	public void setCurrencycode(int currencycode) {
		this.currencycode = currencycode;
	}

	/**
	 * Gets the prepaidbalancebefore.
	 *
	 * @return the prepaidbalancebefore
	 */
	public int getPrepaidbalancebefore() {
		return prepaidbalancebefore;
	}

	/**
	 * Sets the prepaidbalancebefore.
	 *
	 * @param prepaidbalancebefore the new prepaidbalancebefore
	 */
	public void setPrepaidbalancebefore(int prepaidbalancebefore) {
		this.prepaidbalancebefore = prepaidbalancebefore;
	}

	/**
	 * Gets the rechargeforprepaid.
	 *
	 * @return the rechargeforprepaid
	 */
	public int getRechargeforprepaid() {
		return rechargeforprepaid;
	}

	/**
	 * Sets the rechargeforprepaid.
	 *
	 * @param rechargeforprepaid the new rechargeforprepaid
	 */
	public void setRechargeforprepaid(int rechargeforprepaid) {
		this.rechargeforprepaid = rechargeforprepaid;
	}

	/**
	 * Gets the prepaidbalance.
	 *
	 * @return the prepaidbalance
	 */
	public int getPrepaidbalance() {
		return prepaidbalance;
	}

	/**
	 * Sets the prepaidbalance.
	 *
	 * @param prepaidbalance the new prepaidbalance
	 */
	public void setPrepaidbalance(int prepaidbalance) {
		this.prepaidbalance = prepaidbalance;
	}

	/**
	 * Gets the postpaidbalancebefore.
	 *
	 * @return the postpaidbalancebefore
	 */
	public int getPostpaidbalancebefore() {
		return postpaidbalancebefore;
	}

	/**
	 * Sets the postpaidbalancebefore.
	 *
	 * @param postpaidbalancebefore the new postpaidbalancebefore
	 */
	public void setPostpaidbalancebefore(int postpaidbalancebefore) {
		this.postpaidbalancebefore = postpaidbalancebefore;
	}

	/**
	 * Gets the currentuserstate.
	 *
	 * @return the currentuserstate
	 */
	public String getCurrentuserstate() {
		return currentuserstate;
	}

	/**
	 * Sets the currentuserstate.
	 *
	 * @param currentuserstate the new currentuserstate
	 */
	public void setCurrentuserstate(String currentuserstate) {
		this.currentuserstate = currentuserstate;
	}

	/**
	 * Gets the previousactivestop.
	 *
	 * @return the previousactivestop
	 */
	public String getPreviousactivestop() {
		return previousactivestop;
	}

	/**
	 * Sets the previousactivestop.
	 *
	 * @param previousactivestop the new previousactivestop
	 */
	public void setPreviousactivestop(String previousactivestop) {
		this.previousactivestop = previousactivestop;
	}

	/**
	 * Gets the newactivestop.
	 *
	 * @return the newactivestop
	 */
	public String getNewactivestop() {
		return newactivestop;
	}

	/**
	 * Sets the newactivestop.
	 *
	 * @param newactivestop the new newactivestop
	 */
	public void setNewactivestop(String newactivestop) {
		this.newactivestop = newactivestop;
	}

	/**
	 * Gets the suspendstop.
	 *
	 * @return the suspendstop
	 */
	public String getSuspendstop() {
		return suspendstop;
	}

	/**
	 * Sets the suspendstop.
	 *
	 * @param suspendstop the new suspendstop
	 */
	public void setSuspendstop(String suspendstop) {
		this.suspendstop = suspendstop;
	}

	/**
	 * Gets the disablestop.
	 *
	 * @return the disablestop
	 */
	public String getDisablestop() {
		return disablestop;
	}

	/**
	 * Sets the disablestop.
	 *
	 * @param disablestop the new disablestop
	 */
	public void setDisablestop(String disablestop) {
		this.disablestop = disablestop;
	}

	/**
	 * Gets the rebateschemaid.
	 *
	 * @return the rebateschemaid
	 */
	public String getRebateschemaid() {
		return rebateschemaid;
	}

	/**
	 * Sets the rebateschemaid.
	 *
	 * @param rebateschemaid the new rebateschemaid
	 */
	public void setRebateschemaid(String rebateschemaid) {
		this.rebateschemaid = rebateschemaid;
	}

	/**
	 * Gets the billcycleid.
	 *
	 * @return the billcycleid
	 */
	public String getBillcycleid() {
		return billcycleid;
	}

	/**
	 * Sets the billcycleid.
	 *
	 * @param billcycleid the new billcycleid
	 */
	public void setBillcycleid(String billcycleid) {
		this.billcycleid = billcycleid;
	}

	/**
	 * Gets the subscriberid.
	 *
	 * @return the subscriberid
	 */
	public String getSubscriberid() {
		return subscriberid;
	}

	/**
	 * Sets the subscriberid.
	 *
	 * @param subscriberid the new subscriberid
	 */
	public void setSubscriberid(String subscriberid) {
		this.subscriberid = subscriberid;
	}

	/**
	 * Gets the accountid.
	 *
	 * @return the accountid
	 */
	public String getAccountid() {
		return accountid;
	}

	/**
	 * Sets the accountid.
	 *
	 * @param accountid the new accountid
	 */
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 * Gets the rechargeforpostpaid.
	 *
	 * @return the rechargeforpostpaid
	 */
	public int getRechargeforpostpaid() {
		return rechargeforpostpaid;
	}

	/**
	 * Sets the rechargeforpostpaid.
	 *
	 * @param rechargeforpostpaid the new rechargeforpostpaid
	 */
	public void setRechargeforpostpaid(int rechargeforpostpaid) {
		this.rechargeforpostpaid = rechargeforpostpaid;
	}

	/**
	 * Gets the postpaidbalance.
	 *
	 * @return the postpaidbalance
	 */
	public int getPostpaidbalance() {
		return postpaidbalance;
	}

	/**
	 * Sets the postpaidbalance.
	 *
	 * @param postpaidbalance the new postpaidbalance
	 */
	public void setPostpaidbalance(int postpaidbalance) {
		this.postpaidbalance = postpaidbalance;
	}

	/**
	 * Gets the accounttype1.
	 *
	 * @return the accounttype1
	 */
	public int getAccounttype1() {
		return accounttype1;
	}

	/**
	 * Sets the accounttype1.
	 *
	 * @param accounttype1 the new accounttype1
	 */
	public void setAccounttype1(int accounttype1) {
		this.accounttype1 = accounttype1;
	}

	/**
	 * Gets the chargeamount1.
	 *
	 * @return the chargeamount1
	 */
	public int getChargeamount1() {
		return chargeamount1;
	}

	/**
	 * Sets the chargeamount1.
	 *
	 * @param chargeamount1 the new chargeamount1
	 */
	public void setChargeamount1(int chargeamount1) {
		this.chargeamount1 = chargeamount1;
	}

	/**
	 * Gets the currentacctamount1.
	 *
	 * @return the currentacctamount1
	 */
	public int getCurrentacctamount1() {
		return currentacctamount1;
	}

	/**
	 * Sets the currentacctamount1.
	 *
	 * @param currentacctamount1 the new currentacctamount1
	 */
	public void setCurrentacctamount1(int currentacctamount1) {
		this.currentacctamount1 = currentacctamount1;
	}

	/**
	 * Gets the accounttype2.
	 *
	 * @return the accounttype2
	 */
	public int getAccounttype2() {
		return accounttype2;
	}

	/**
	 * Sets the accounttype2.
	 *
	 * @param accounttype2 the new accounttype2
	 */
	public void setAccounttype2(int accounttype2) {
		this.accounttype2 = accounttype2;
	}

	/**
	 * Gets the chargeamount2.
	 *
	 * @return the chargeamount2
	 */
	public int getChargeamount2() {
		return chargeamount2;
	}

	/**
	 * Sets the chargeamount2.
	 *
	 * @param chargeamount2 the new chargeamount2
	 */
	public void setChargeamount2(int chargeamount2) {
		this.chargeamount2 = chargeamount2;
	}

	/**
	 * Gets the currentacctamount2.
	 *
	 * @return the currentacctamount2
	 */
	public int getCurrentacctamount2() {
		return currentacctamount2;
	}

	/**
	 * Sets the currentacctamount2.
	 *
	 * @param currentacctamount2 the new currentacctamount2
	 */
	public void setCurrentacctamount2(int currentacctamount2) {
		this.currentacctamount2 = currentacctamount2;
	}

	/**
	 * Gets the accounttype3.
	 *
	 * @return the accounttype3
	 */
	public int getAccounttype3() {
		return accounttype3;
	}

	/**
	 * Sets the accounttype3.
	 *
	 * @param accounttype3 the new accounttype3
	 */
	public void setAccounttype3(int accounttype3) {
		this.accounttype3 = accounttype3;
	}

	/**
	 * Gets the chargeamount3.
	 *
	 * @return the chargeamount3
	 */
	public int getChargeamount3() {
		return chargeamount3;
	}

	/**
	 * Sets the chargeamount3.
	 *
	 * @param chargeamount3 the new chargeamount3
	 */
	public void setChargeamount3(int chargeamount3) {
		this.chargeamount3 = chargeamount3;
	}

	/**
	 * Gets the currentacctamount3.
	 *
	 * @return the currentacctamount3
	 */
	public int getCurrentacctamount3() {
		return currentacctamount3;
	}

	/**
	 * Sets the currentacctamount3.
	 *
	 * @param currentacctamount3 the new currentacctamount3
	 */
	public void setCurrentacctamount3(int currentacctamount3) {
		this.currentacctamount3 = currentacctamount3;
	}

	/**
	 * Gets the accounttype4.
	 *
	 * @return the accounttype4
	 */
	public int getAccounttype4() {
		return accounttype4;
	}

	/**
	 * Sets the accounttype4.
	 *
	 * @param accounttype4 the new accounttype4
	 */
	public void setAccounttype4(int accounttype4) {
		this.accounttype4 = accounttype4;
	}

	/**
	 * Gets the chargeamount4.
	 *
	 * @return the chargeamount4
	 */
	public int getChargeamount4() {
		return chargeamount4;
	}

	/**
	 * Sets the chargeamount4.
	 *
	 * @param chargeamount4 the new chargeamount4
	 */
	public void setChargeamount4(int chargeamount4) {
		this.chargeamount4 = chargeamount4;
	}

	/**
	 * Gets the currentacctamount4.
	 *
	 * @return the currentacctamount4
	 */
	public int getCurrentacctamount4() {
		return currentacctamount4;
	}

	/**
	 * Sets the currentacctamount4.
	 *
	 * @param currentacctamount4 the new currentacctamount4
	 */
	public void setCurrentacctamount4(int currentacctamount4) {
		this.currentacctamount4 = currentacctamount4;
	}

	/**
	 * Gets the accounttype5.
	 *
	 * @return the accounttype5
	 */
	public int getAccounttype5() {
		return accounttype5;
	}

	/**
	 * Sets the accounttype5.
	 *
	 * @param accounttype5 the new accounttype5
	 */
	public void setAccounttype5(int accounttype5) {
		this.accounttype5 = accounttype5;
	}

	/**
	 * Gets the chargeamount5.
	 *
	 * @return the chargeamount5
	 */
	public int getChargeamount5() {
		return chargeamount5;
	}

	/**
	 * Sets the chargeamount5.
	 *
	 * @param chargeamount5 the new chargeamount5
	 */
	public void setChargeamount5(int chargeamount5) {
		this.chargeamount5 = chargeamount5;
	}

	/**
	 * Gets the currentacctamount5.
	 *
	 * @return the currentacctamount5
	 */
	public int getCurrentacctamount5() {
		return currentacctamount5;
	}

	/**
	 * Sets the currentacctamount5.
	 *
	 * @param currentacctamount5 the new currentacctamount5
	 */
	public void setCurrentacctamount5(int currentacctamount5) {
		this.currentacctamount5 = currentacctamount5;
	}

	/**
	 * Gets the accounttype6.
	 *
	 * @return the accounttype6
	 */
	public int getAccounttype6() {
		return accounttype6;
	}

	/**
	 * Sets the accounttype6.
	 *
	 * @param accounttype6 the new accounttype6
	 */
	public void setAccounttype6(int accounttype6) {
		this.accounttype6 = accounttype6;
	}

	/**
	 * Gets the chargeamount6.
	 *
	 * @return the chargeamount6
	 */
	public int getChargeamount6() {
		return chargeamount6;
	}

	/**
	 * Sets the chargeamount6.
	 *
	 * @param chargeamount6 the new chargeamount6
	 */
	public void setChargeamount6(int chargeamount6) {
		this.chargeamount6 = chargeamount6;
	}

	/**
	 * Gets the currentacctamount6.
	 *
	 * @return the currentacctamount6
	 */
	public int getCurrentacctamount6() {
		return currentacctamount6;
	}

	/**
	 * Sets the currentacctamount6.
	 *
	 * @param currentacctamount6 the new currentacctamount6
	 */
	public void setCurrentacctamount6(int currentacctamount6) {
		this.currentacctamount6 = currentacctamount6;
	}

	/**
	 * Gets the accounttype7.
	 *
	 * @return the accounttype7
	 */
	public int getAccounttype7() {
		return accounttype7;
	}

	/**
	 * Sets the accounttype7.
	 *
	 * @param accounttype7 the new accounttype7
	 */
	public void setAccounttype7(int accounttype7) {
		this.accounttype7 = accounttype7;
	}

	/**
	 * Gets the chargeamount7.
	 *
	 * @return the chargeamount7
	 */
	public int getChargeamount7() {
		return chargeamount7;
	}

	/**
	 * Sets the chargeamount7.
	 *
	 * @param chargeamount7 the new chargeamount7
	 */
	public void setChargeamount7(int chargeamount7) {
		this.chargeamount7 = chargeamount7;
	}

	/**
	 * Gets the currentacctamount7.
	 *
	 * @return the currentacctamount7
	 */
	public int getCurrentacctamount7() {
		return currentacctamount7;
	}

	/**
	 * Sets the currentacctamount7.
	 *
	 * @param currentacctamount7 the new currentacctamount7
	 */
	public void setCurrentacctamount7(int currentacctamount7) {
		this.currentacctamount7 = currentacctamount7;
	}

	/**
	 * Gets the accounttype8.
	 *
	 * @return the accounttype8
	 */
	public int getAccounttype8() {
		return accounttype8;
	}

	/**
	 * Sets the accounttype8.
	 *
	 * @param accounttype8 the new accounttype8
	 */
	public void setAccounttype8(int accounttype8) {
		this.accounttype8 = accounttype8;
	}

	/**
	 * Gets the chargeamount8.
	 *
	 * @return the chargeamount8
	 */
	public int getChargeamount8() {
		return chargeamount8;
	}

	/**
	 * Sets the chargeamount8.
	 *
	 * @param chargeamount8 the new chargeamount8
	 */
	public void setChargeamount8(int chargeamount8) {
		this.chargeamount8 = chargeamount8;
	}

	/**
	 * Gets the currentacctamount8.
	 *
	 * @return the currentacctamount8
	 */
	public int getCurrentacctamount8() {
		return currentacctamount8;
	}

	/**
	 * Sets the currentacctamount8.
	 *
	 * @param currentacctamount8 the new currentacctamount8
	 */
	public void setCurrentacctamount8(int currentacctamount8) {
		this.currentacctamount8 = currentacctamount8;
	}

	/**
	 * Gets the accounttype9.
	 *
	 * @return the accounttype9
	 */
	public int getAccounttype9() {
		return accounttype9;
	}

	/**
	 * Sets the accounttype9.
	 *
	 * @param accounttype9 the new accounttype9
	 */
	public void setAccounttype9(int accounttype9) {
		this.accounttype9 = accounttype9;
	}

	/**
	 * Gets the chargeamount9.
	 *
	 * @return the chargeamount9
	 */
	public int getChargeamount9() {
		return chargeamount9;
	}

	/**
	 * Sets the chargeamount9.
	 *
	 * @param chargeamount9 the new chargeamount9
	 */
	public void setChargeamount9(int chargeamount9) {
		this.chargeamount9 = chargeamount9;
	}

	/**
	 * Gets the currentacctamount9.
	 *
	 * @return the currentacctamount9
	 */
	public int getCurrentacctamount9() {
		return currentacctamount9;
	}

	/**
	 * Sets the currentacctamount9.
	 *
	 * @param currentacctamount9 the new currentacctamount9
	 */
	public void setCurrentacctamount9(int currentacctamount9) {
		this.currentacctamount9 = currentacctamount9;
	}

	/**
	 * Gets the accounttype10.
	 *
	 * @return the accounttype10
	 */
	public int getAccounttype10() {
		return accounttype10;
	}

	/**
	 * Sets the accounttype10.
	 *
	 * @param accounttype10 the new accounttype10
	 */
	public void setAccounttype10(int accounttype10) {
		this.accounttype10 = accounttype10;
	}

	/**
	 * Gets the chargeamount10.
	 *
	 * @return the chargeamount10
	 */
	public int getChargeamount10() {
		return chargeamount10;
	}

	/**
	 * Sets the chargeamount10.
	 *
	 * @param chargeamount10 the new chargeamount10
	 */
	public void setChargeamount10(int chargeamount10) {
		this.chargeamount10 = chargeamount10;
	}

	/**
	 * Gets the currentacctamount10.
	 *
	 * @return the currentacctamount10
	 */
	public int getCurrentacctamount10() {
		return currentacctamount10;
	}

	/**
	 * Sets the currentacctamount10.
	 *
	 * @param currentacctamount10 the new currentacctamount10
	 */
	public void setCurrentacctamount10(int currentacctamount10) {
		this.currentacctamount10 = currentacctamount10;
	}

	/**
	 * Gets the bonusvalidity1.
	 *
	 * @return the bonusvalidity1
	 */
	public String getBonusvalidity1() {
		return bonusvalidity1;
	}

	/**
	 * Sets the bonusvalidity1.
	 *
	 * @param bonusvalidity1 the new bonusvalidity1
	 */
	public void setBonusvalidity1(String bonusvalidity1) {
		this.bonusvalidity1 = bonusvalidity1;
	}

	/**
	 * Gets the bonusvalidity2.
	 *
	 * @return the bonusvalidity2
	 */
	public String getBonusvalidity2() {
		return bonusvalidity2;
	}

	/**
	 * Sets the bonusvalidity2.
	 *
	 * @param bonusvalidity2 the new bonusvalidity2
	 */
	public void setBonusvalidity2(String bonusvalidity2) {
		this.bonusvalidity2 = bonusvalidity2;
	}

	/**
	 * Gets the bonusvalidity3.
	 *
	 * @return the bonusvalidity3
	 */
	public String getBonusvalidity3() {
		return bonusvalidity3;
	}

	/**
	 * Sets the bonusvalidity3.
	 *
	 * @param bonusvalidity3 the new bonusvalidity3
	 */
	public void setBonusvalidity3(String bonusvalidity3) {
		this.bonusvalidity3 = bonusvalidity3;
	}

	/**
	 * Gets the bonusvalidity4.
	 *
	 * @return the bonusvalidity4
	 */
	public String getBonusvalidity4() {
		return bonusvalidity4;
	}

	/**
	 * Sets the bonusvalidity4.
	 *
	 * @param bonusvalidity4 the new bonusvalidity4
	 */
	public void setBonusvalidity4(String bonusvalidity4) {
		this.bonusvalidity4 = bonusvalidity4;
	}

	/**
	 * Gets the bonusvalidity5.
	 *
	 * @return the bonusvalidity5
	 */
	public String getBonusvalidity5() {
		return bonusvalidity5;
	}

	/**
	 * Sets the bonusvalidity5.
	 *
	 * @param bonusvalidity5 the new bonusvalidity5
	 */
	public void setBonusvalidity5(String bonusvalidity5) {
		this.bonusvalidity5 = bonusvalidity5;
	}

	/**
	 * Gets the bonusvalidity6.
	 *
	 * @return the bonusvalidity6
	 */
	public String getBonusvalidity6() {
		return bonusvalidity6;
	}

	/**
	 * Sets the bonusvalidity6.
	 *
	 * @param bonusvalidity6 the new bonusvalidity6
	 */
	public void setBonusvalidity6(String bonusvalidity6) {
		this.bonusvalidity6 = bonusvalidity6;
	}

	/**
	 * Gets the bonusvalidity7.
	 *
	 * @return the bonusvalidity7
	 */
	public String getBonusvalidity7() {
		return bonusvalidity7;
	}

	/**
	 * Sets the bonusvalidity7.
	 *
	 * @param bonusvalidity7 the new bonusvalidity7
	 */
	public void setBonusvalidity7(String bonusvalidity7) {
		this.bonusvalidity7 = bonusvalidity7;
	}

	/**
	 * Gets the bonusvalidity8.
	 *
	 * @return the bonusvalidity8
	 */
	public String getBonusvalidity8() {
		return bonusvalidity8;
	}

	/**
	 * Sets the bonusvalidity8.
	 *
	 * @param bonusvalidity8 the new bonusvalidity8
	 */
	public void setBonusvalidity8(String bonusvalidity8) {
		this.bonusvalidity8 = bonusvalidity8;
	}

	/**
	 * Gets the bonusvalidity9.
	 *
	 * @return the bonusvalidity9
	 */
	public String getBonusvalidity9() {
		return bonusvalidity9;
	}

	/**
	 * Sets the bonusvalidity9.
	 *
	 * @param bonusvalidity9 the new bonusvalidity9
	 */
	public void setBonusvalidity9(String bonusvalidity9) {
		this.bonusvalidity9 = bonusvalidity9;
	}

	/**
	 * Gets the bonusvalidity10.
	 *
	 * @return the bonusvalidity10
	 */
	public String getBonusvalidity10() {
		return bonusvalidity10;
	}

	/**
	 * Sets the bonusvalidity10.
	 *
	 * @param bonusvalidity10 the new bonusvalidity10
	 */
	public void setBonusvalidity10(String bonusvalidity10) {
		this.bonusvalidity10 = bonusvalidity10;
	}

	/**
	 * Gets the previoussuspendstop.
	 *
	 * @return the previoussuspendstop
	 */
	public String getPrevioussuspendstop() {
		return previoussuspendstop;
	}

	/**
	 * Sets the previoussuspendstop.
	 *
	 * @param previoussuspendstop the new previoussuspendstop
	 */
	public void setPrevioussuspendstop(String previoussuspendstop) {
		this.previoussuspendstop = previoussuspendstop;
	}

	/**
	 * Gets the previousdisablestop.
	 *
	 * @return the previousdisablestop
	 */
	public String getPreviousdisablestop() {
		return previousdisablestop;
	}

	/**
	 * Sets the previousdisablestop.
	 *
	 * @param previousdisablestop the new previousdisablestop
	 */
	public void setPreviousdisablestop(String previousdisablestop) {
		this.previousdisablestop = previousdisablestop;
	}

	/**
	 * Gets the agentname.
	 *
	 * @return the agentname
	 */
	public String getAgentname() {
		return agentname;
	}

	/**
	 * Sets the agentname.
	 *
	 * @param agentname the new agentname
	 */
	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	/**
	 * Gets the additionalinfo.
	 *
	 * @return the additionalinfo
	 */
	public String getAdditionalinfo() {
		return additionalinfo;
	}

	/**
	 * Sets the additionalinfo.
	 *
	 * @param additionalinfo the new additionalinfo
	 */
	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	/**
	 * Gets the voutime.
	 *
	 * @return the voutime
	 */
	public Timestamp getVoutime() {
		return voutime;
	}

	/**
	 * Sets the voutime.
	 *
	 * @param voutime the new voutime
	 */
	public void setVoutime(Timestamp voutime) {
		this.voutime = voutime;
	}

}