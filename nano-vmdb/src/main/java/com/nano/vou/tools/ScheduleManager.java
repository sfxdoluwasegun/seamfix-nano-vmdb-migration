package com.nano.vou.tools;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.jboss.logging.Logger;

import com.nano.jpa.entity.Settings;
import com.nano.jpa.enums.SettingType;
import com.nano.vou.enums.GeneralSettings;
import com.nano.vou.jbeans.ApplicationBean;

@Startup
@DependsOn(value = "StartupManager")
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ScheduleManager {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Inject
	private QueryManager queryManager ;
	
	@Inject
	private ApplicationBean applicationBean ;
	
	@Schedule(minute = "*/5", hour = "*")
	public void refreshQuickSettings(){
		
		Settings settings = queryManager.createSettings(GeneralSettings.ARAS.getName(), 
				GeneralSettings.ARAS.getValue(), GeneralSettings.ARAS.getDescription(), SettingType.GENERAL);
		
		applicationBean.setAgileRas(Boolean.parseBoolean(settings.getValue()));
		log.info("successfully refreshed cached settings");
	}
	
}
