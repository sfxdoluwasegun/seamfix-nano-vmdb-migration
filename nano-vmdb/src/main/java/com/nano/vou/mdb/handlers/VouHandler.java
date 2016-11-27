package com.nano.vou.mdb.handlers;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import com.nano.vou.operations.DataExtractor;

/**
 * Handles crunching of VOU file data.
 * 
 * @author segz
 *
 */

@Stateless
public class VouHandler {
	
	@Inject
	private DataExtractor dataExtractor ;
	
	private Pattern nextLine ;
	
	@PostConstruct
	public void init(){
		
		nextLine = Pattern.compile("\n");
	}
	
	/**
	 * Receives {@link Message} containing byte[] of CDR VOU file.
	 * Writes byte to file and queues each transaction in file for further processing.
	 * 
	 * @param mapMessage
	 * @throws JMSException 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void processVouFileBytes(MapMessage mapMessage) throws JMSException{

		String filedata = mapMessage.getString("bytea");

		if (filedata == null)
			return;

		String[] linedata = nextLine.split(filedata); //filedata.split("\n");

		for (int i = 0; i < linedata.length ; i++){
			String[] cdrdata = linedata[i].split("\\|");
			dataExtractor.queueCDRLineForProcessing(cdrdata);
			//dataExtractor.queueCDRLineForProcessing(linedata[i]);
		}
	}

}