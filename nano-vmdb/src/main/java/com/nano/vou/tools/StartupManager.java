package com.nano.vou.tools;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.nano.jpa.entity.Settings;
import com.nano.jpa.enums.SettingType;
import com.nano.vou.enums.GeneralSettings;
import com.nano.vou.jbeans.ApplicationBean;

/**
 * Manages application startup processes.
 * 
 * @author segz
 *
 */

@Startup
@Singleton(description = "Nano VMDB Startup Manager")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StartupManager {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private QueryManager queryManager ;
	
	@Inject
	private ApplicationBean applicationBean ;
	
	@PostConstruct
	public void startup(){
		
		log.info("Starting up Manager");
		loadQuickSettings();
	}
	
	private void loadQuickSettings() {
		// TODO Auto-generated method stub
		
		Settings settings = queryManager.createSettings(GeneralSettings.ARAS.getName(), 
				GeneralSettings.ARAS.getValue(), GeneralSettings.ARAS.getDescription(), SettingType.GENERAL);
		
		applicationBean.setAgileRas(Boolean.parseBoolean(settings.getValue()));
	}
	
	

}
