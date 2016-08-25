package com.nano.vou.tools;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import com.nano.jpa.entity.Subscriber;
import com.nano.jpa.entity.SubscriberHistory;
import com.nano.jpa.entity.SubscriberHistory_;
import com.nano.jpa.entity.SubscriberState;
import com.nano.jpa.entity.SubscriberState_;
import com.nano.jpa.entity.Subscriber_;
import com.nano.jpa.entity.ras.SubscriberAssessment;
import com.nano.jpa.enums.ActiveStatus;
import com.nano.jpa.enums.LoanIndicator;
import com.nano.jpa.enums.PayType;
import com.nano.jpa.enums.TradeType;

/**
 * Manage database persistence and spooling via {@link PersistenceContext}.
 * 
 * @author segz
 *
 */

@Singleton
@AccessTimeout(unit = TimeUnit.MINUTES, value = 3)
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class QueryManager {
	
	private Logger log = Logger.getLogger(getClass());

	private CriteriaBuilder criteriaBuilder ;

	@PersistenceContext
	private EntityManager entityManager ;

	@PostConstruct
	public void init(){
		criteriaBuilder = entityManager.getCriteriaBuilder();
	}
	
	/**
	 * Fetch {@link SubscriberState} by MSISDN.
	 *
	 * @param msisdn
	 * @return {@link SubscriberState}
	 */
	public SubscriberState getSubscriberStateByMsisdn(String msisdn){
		
		CriteriaQuery<SubscriberState> criteriaQuery = criteriaBuilder.createQuery(SubscriberState.class);
		Root<SubscriberState> root = criteriaQuery.from(SubscriberState.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get(SubscriberState_.msisdn), msisdn));
		
		try {
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No subscriberState instance was found with msisdn:" + msisdn);
		}
		
		return null;
	}
	
	/**
	 * Fetch {@link Subscriber} by MSISDN property.
	 * 
	 * @param msisdn
	 * @return {@link Subscriber}
	 */
	public Subscriber getSubscriberByMsisdn(String msisdn){
		
		CriteriaQuery<Subscriber> criteriaQuery = criteriaBuilder.createQuery(Subscriber.class);
		Root<Subscriber> root = criteriaQuery.from(Subscriber.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get(Subscriber_.msisdn), formatMisisdn(msisdn)));
		
		try {
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No subscriber instance was found with msisdn:" + msisdn);;
		}
		
		return null;
	}
	
	/**
	 * Fetch {@link SubscriberHistory} by MSISDN and rechargeTime properties.
	 *
	 * @param msisdn
	 * @param timestamp
	 * @return {@link SubscriberHistory}
	 */
	public SubscriberHistory getSubscriberHistoryBySubscriberAndRechargeTime(String msisdn, 
			Timestamp timestamp){
		
		CriteriaQuery<SubscriberHistory> criteriaQuery = criteriaBuilder.createQuery(SubscriberHistory.class);
		Root<SubscriberHistory> root = criteriaQuery.from(SubscriberHistory.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.and(
				criteriaBuilder.equal(root.get(SubscriberHistory_.msisdn), msisdn), 
				criteriaBuilder.equal(root.get(SubscriberHistory_.rechargeTime), timestamp)
				));
		
		try {
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No subscriberHistory instance found for msisdn:" + msisdn + " and rechargeTime:" + timestamp);
		}
		
		return null;
	}
	
	/**
	 * create new {@link SubscriberHistory} using optimized map message object.
	 * 
	 * @param cardFaceValue
	 * @param postpaidBalance
	 * @param postpaidBalanceBefore
	 * @param prepaidBalance
	 * @param prepaidBalanceBefore
	 * @param rechargeForPostpaid
	 * @param rechargeForPrepaid
	 * @param mapMessage
	 * @param tradeType
	 * @param subscriber
	 * @return created {@link SubscriberHistory} data
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SubscriberHistory createNewSubscriberHistoryData(BigDecimal cardFaceValue, 
			BigDecimal postpaidBalance, BigDecimal postpaidBalanceBefore, BigDecimal prepaidBalance, BigDecimal prepaidBalanceBefore, 
			BigDecimal rechargeForPostpaid, BigDecimal rechargeForPrepaid, MapMessage mapMessage, TradeType tradeType, Subscriber subscriber) {

		SubscriberHistory subscriberHistory = null;
		try {
			subscriberHistory = new SubscriberHistory();
			subscriberHistory.setCardFaceValue(cardFaceValue);
			subscriberHistory.setCurrentUserState(mapMessage.getString("currentuserstate"));
			subscriberHistory.setLoanIndicator(LoanIndicator.fromCode(mapMessage.getInt("loanindicator")));
			subscriberHistory.setMsisdn(subscriber.getMsisdn());
			subscriberHistory.setOldUserState(mapMessage.getString("olduserstate"));
			subscriberHistory.setPayType(PayType.fromCode(mapMessage.getString("paytype")));
			subscriberHistory.setPostpaidBalance(postpaidBalance);
			subscriberHistory.setPostpaidBalanceBefore(postpaidBalanceBefore);
			subscriberHistory.setPrepaidBalance(prepaidBalance);
			subscriberHistory.setPrepaidBalanceBefore(prepaidBalanceBefore);
			subscriberHistory.setRechargeForPostpaid(rechargeForPostpaid);
			subscriberHistory.setRechargeForPrepaid(rechargeForPrepaid);
			subscriberHistory.setRechargeTime(new Timestamp(mapMessage.getLong("voutime")));
			subscriberHistory.setTradeType(tradeType);
			subscriberHistory.setTransactionId(mapMessage.getString("transistionid"));
			subscriberHistory.setVoucherBatchNumber(mapMessage.getString("voucherbatchnumber"));
			subscriberHistory.setVoucherSequence(mapMessage.getString("vouchersequence"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.warn("", e);
		}

		return (SubscriberHistory) create(subscriberHistory);
	}
	
	/**
	 * Creates or updates the state of the subscriber.
	 * 
	 * @param subscriberState
	 * @param subscriber
	 * @param existingCurrentState
	 */
	public void createSubscriberSubscriberState(SubscriberState subscriberState, 
			Subscriber subscriber, SubscriberState existingCurrentState) {

		if (subscriberState == null || subscriber == null)
			return;

		if (existingCurrentState != null){
			existingCurrentState.setActiveStatus(subscriberState.getActiveStatus());
			existingCurrentState.setBlacklisted(subscriberState.isBlacklisted());
			existingCurrentState.setCurrentBalance(subscriberState.getCurrentBalance());
			existingCurrentState.setLastUpdated(subscriberState.getLastUpdated());
			existingCurrentState.setPayType(subscriberState.getPayType());

			update(existingCurrentState);
		}else{
			subscriberState.setMsisdn(subscriber.getMsisdn());
			create(subscriberState);
		}
	}
	
	/**
	 * Update existing instance of {@link SubscriberState}.
	 * 
	 * @param subscriberState
	 * @param activeStatus
	 * @param blacklisted
	 * @param currentBalance
	 * @param payType
	 * @param msisdn
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateSubscriberState(SubscriberState subscriberState, 
			ActiveStatus activeStatus, boolean blacklisted, 
			BigDecimal currentBalance, PayType payType, 
			String msisdn){
		
		subscriberState.setActiveStatus(activeStatus);
		subscriberState.setBlacklisted(blacklisted);
		subscriberState.setCurrentBalance(currentBalance);
		subscriberState.setLastUpdated(new Timestamp(Calendar.getInstance().getTime().getTime()));
		subscriberState.setPayType(payType);
		
		update(subscriberState);
	}
	
	/**
	 * Creates new {@link SubscriberState} and add to {@link PersistenceContext}.
	 * 
	 * @param activeStatus
	 * @param blacklisted
	 * @param currentBalance
	 * @param payType
	 * @param msisdn
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createSubscriberState(ActiveStatus activeStatus, 
			boolean blacklisted, BigDecimal currentBalance, 
			PayType payType, String msisdn){
		
		SubscriberState subscriberState = new SubscriberState();
		subscriberState.setActiveStatus(activeStatus);
		subscriberState.setBlacklisted(blacklisted);
		subscriberState.setCurrentBalance(currentBalance);
		subscriberState.setLastUpdated(new Timestamp(Calendar.getInstance().getTime().getTime()));
		subscriberState.setPayType(payType);
		subscriberState.setMsisdn(msisdn);
		
		create(subscriberState);
	}
	
	/**
	 * Creates or fetches a unique {@link Subscriber} record.
	 *
	 * @param msisdn
	 * @return {@link Subscriber}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Subscriber createSubscriber(String msisdn){
		
		Subscriber subscriber = getSubscriberByMsisdn(formatMisisdn(msisdn));
		
		if (subscriber != null)
			return subscriber;

		subscriber = new Subscriber();
		subscriber.setInDebt(false);
		subscriber.setAutoRecharge(false);
		subscriber.setMsisdn(formatMisisdn(msisdn));

		return (Subscriber) create(subscriber);
	}
	
	/**
	 * Reset {@link SubscriberAssessment} initialization time to indicate Subscriber is due for assessment.
	 * 
	 * @param subscriber
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void resetSubscriberAssessmentInitTime(Subscriber subscriber) {
		// TODO Auto-generated method stub
		
		SubscriberAssessment subscriberAssessment = subscriber.getAssessment();
		if (subscriberAssessment == null)
			return;
		
		subscriberAssessment.setAssessmentInitTime(Timestamp.valueOf(LocalDateTime.now()));
		update(subscriberAssessment);
	}
	
	/**
	 * Format MSISDN.
	 *
	 * @param msisdn
	 * @return formatted MSISDN
	 */
	public String formatMisisdn(String msisdn){
		
		if (msisdn.startsWith("234"))
			msisdn = "0" + msisdn.substring(3, msisdn.length());
		
		if (msisdn.startsWith("+234"))
			msisdn = "0" + msisdn.substring(4, msisdn.length());
		
		if (!msisdn.startsWith("0"))
			msisdn = "0" + msisdn;
		
		return msisdn;
	}
	
	/**
	 * Fetch persisted entity instance by it {@link PrimaryKey}.
	 * 
	 * @param clazz
	 * @param pk
	 * @return persisted entity instance
	 */
	public <T> Object getByPk(Class<T> clazz, 
			long pk){

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("pk"), pk)), 
				criteriaBuilder.equal(root.get("deleted"), false));

		try {
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No " + clazz.getCanonicalName() + " exists with the pk:" + pk);
		}

		return null;
	}

	/**
	 * Fetches all persisted entity instance by class.
	 * 
	 * @param clazz
	 * @return List of all class entities
	 */
	public <T> List<T> getListByClass(Class<T> clazz){

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));

		try {
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No " + clazz.getCanonicalName() + " exists ", e);
		}

		return null;
	}

	/**
	 * Fetches all persisted entity instance by class and orders List by {@link Property}.
	 * 
	 * @param clazz
	 * @param property
	 * @return ordered list of Class entities
	 */
	public <T> List<T> getListByClassOrderByProperty(Class<T> clazz, 
			String property){

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));

		try {
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No " + clazz.getCanonicalName() + " exists or the property:" + property + " is invalid");
		}

		return null;
	}

	/**
	 * Fetch persisted entity by {@link PrimaryKey} and load {@link JoinColumn} eagerly
	 * @param clazz
	 * @param pk
	 * @param properties
	 * @return persisted entity instance
	 */
	public <T> Object getByPkWithEagerLoading(Class<T> clazz, 
			long pk, String ...properties){

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);

		for (String property : properties){
			root.fetch(property, JoinType.LEFT);
		}

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("pk"), pk)), 
				criteriaBuilder.equal(root.get("deleted"), false));

		try {
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No " + clazz.getCanonicalName() + " exists with the pk:" + pk + " or property arguments don't exist for entity");
		}

		return null;
	}

	/**
	 * Fetches all persisted entity instance by class, load specified properties eagerly.
	 *  
	 * @param clazz
	 * @param properties
	 * @return {@link List}
	 */
	public <T> List<T> getListByClassWithEagerProperties(Class<T> clazz, 
			String ... properties){

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);

		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));

		for (String property : properties){
			root.fetch(property, JoinType.LEFT);
		}

		try {
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("No " + clazz.getCanonicalName() + " exists or one of the specified properties is invalid");
		}

		return null;
	}

	/**
	 * Execute SQL statement.
	 * 
	 * @param statement
	 */
	public void runSqlScript(String statement){

		Query query = entityManager.createNativeQuery(statement);
		query.executeUpdate();
	}

	/**
	 * Persist entity and add entity instance to {@link EntityManager}.
	 * 
	 * @param entity
	 * @return persisted entity instance
	 */
	public <T> Object create(T entity){

		entityManager.persist(entity);

		try {
			return entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return null;
	}

	/**
	 * Merge the state of the given entity into the current {@link PersistenceContext}.
	 * 
	 * @param entity
	 * @return the managed instance that the state was merged to
	 */
	public <T> Object update(T entity){

		entityManager.merge(entity);
		try {
			return entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}

		return null;
	}

	/**
	 * Remove the entity instance.
	 * 
	 * @param entity
	 */
	public <T> void delete(T entity){
		try {
			entityManager.remove(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

}
