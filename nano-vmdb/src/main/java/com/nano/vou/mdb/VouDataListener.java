package com.nano.vou.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;

import com.nano.vou.mdb.handlers.VouHandler;

/**
 * {@link JMSConsumer} implementation for VOU data queue.
 * 
 * @author segz
 *
 */

@ResourceAdapter(value = "activemq")
@MessageDriven(mappedName = "vouData", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName="destination", propertyValue="vouData"), 
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class VouDataListener implements MessageListener {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private VouHandler vouHandler ;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		try {
			vouHandler.processVouFileBytes((MapMessage) message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

}
