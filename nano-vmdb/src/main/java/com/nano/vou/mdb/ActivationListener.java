package com.nano.vou.mdb;

import java.io.File;
import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.commons.io.FileUtils;
import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;

import com.nano.vou.mdb.handlers.ActivationHandler;

/**
 * 
 * @author segz
 *
 */

@ResourceAdapter(value = "activemq")
@MessageDriven(mappedName = "queue/Activation", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/Activation"), 
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class ActivationListener implements MessageListener {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private ActivationHandler activationHandler ;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		File file = new File(System.getProperty("jboss.server.log.dir") + "/activation/msisdn_activationerr.txt");
		
		String msisdn = null;
		String date = null;
		
		try {
			MapMessage mapMessage = (MapMessage) message;
			
			msisdn = mapMessage.getString("msisdn");
			date = mapMessage.getString("date");
			
			if (msisdn == null 
					|| msisdn.isEmpty() 
					|| date == null 
					|| date.isEmpty()){
				FileUtils.writeStringToFile(file, new StringBuilder(msisdn).append("^").append(date).append("^").append("Empty date or msisdn column\n").toString(), "UTF-8", true);
				return;
			}
			
			activationHandler.crunchData(msisdn, date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
			try {
				FileUtils.writeStringToFile(file, new StringBuilder(msisdn).append("^").append(date).append("^").append(e.getMessage()).append("\n").toString(), "UTF-8", true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				log.error(new StringBuilder(msisdn).append("^").append(date).toString(), e1);
			}
		}
	}

}
