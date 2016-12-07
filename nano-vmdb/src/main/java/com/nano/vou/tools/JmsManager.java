package com.nano.vou.tools;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
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

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.MINUTES, value = 5)
public class JmsManager {

	private Logger log = Logger.getLogger(getClass());

	private Connection connection ;
	private Session session ;


	@Resource(lookup = "java:/AmqConnectionFactory")
	private ConnectionFactory connectionFactory ;

	
	@Resource(lookup = "java:/jms/queue/AgileRas")
	private Queue rasQueue ;

	@PostConstruct
	public void start(){
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

	@PreDestroy
	public void end(){
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
		}
	}

}