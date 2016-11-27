package com.nano.vou.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;

import com.nano.vou.jaxb.CDRVouMesh;
import com.nano.vou.mdb.handlers.VouDataHandler;

/**
 * The listener interface for receiving CDRVMesh events.
 * The class that is interested in processing a CDRVMesh
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCDRVMeshListener<code> method. When
 * the CDRVMesh event occurs, that object's appropriate
 * method is invoked.
 *
 * @author segz
 */

@ResourceAdapter(value = "activemq")
@MessageDriven(mappedName = "queue/VouMesh", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/VouMesh"), 
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class MeshListener implements MessageListener {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private VouDataHandler vouDataHandler ;

	@SuppressWarnings("deprecation")
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		CDRVouMesh cdrVouMesh;
		
		try {
			cdrVouMesh = (CDRVouMesh) ((ObjectMessage) message).getObject();
			vouDataHandler.processVouMeshData(cdrVouMesh);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
	}

}
