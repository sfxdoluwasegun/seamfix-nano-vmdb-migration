package com.nano.vou.mdb.handlers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nano.jpa.entity.Subscriber;
import com.nano.vou.tools.QueryManager;

@Stateless
public class ActivationHandler {
	
	@Inject
	private QueryManager queryManager ;

	/**
	 * Create/Update {@link Subscriber} with activation property.
	 * 
	 * @param msisdn
	 * @param date
	 * @throws Exception
	 */
	public void crunchData(String msisdn, 
			String date) throws Exception {
		
		Subscriber subscriber = queryManager.getSubscriberByMsisdn(msisdn);
		
		if (subscriber == null)
			subscriber = queryManager.createSubscriber(msisdn);
		
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy"));
		
		subscriber.setActivation(Date.valueOf(localDate));
		queryManager.update(subscriber);
	}
}
