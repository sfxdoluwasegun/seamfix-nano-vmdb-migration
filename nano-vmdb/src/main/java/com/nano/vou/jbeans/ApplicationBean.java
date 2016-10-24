package com.nano.vou.jbeans;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 * Application scoped bean for managing global Get and Set methods.
 * 
 * @author segz
 *
 */

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ApplicationBean {
	
	private boolean agileRas ;

	public boolean isAgileRas() {
		return agileRas;
	}

	public void setAgileRas(boolean agileRas) {
		this.agileRas = agileRas;
	}
	
}
