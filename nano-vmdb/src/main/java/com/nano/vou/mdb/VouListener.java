package com.nano.vou.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ClusteredSingleton;
import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;

import com.nano.vou.mdb.handlers.VouHandler;

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

@ClusteredSingleton
@ResourceAdapter(value = "activemq")
@MessageDriven(mappedName = "queue/Vou", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/Vou"), 
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class VouListener implements MessageListener {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private VouHandler vouHandler ;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		try {
			vouHandler.processVouData(((MapMessage) message));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

}
