package com.nano.vou.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Enum GeneralSettings.
 *
 * @author segz
 */

public enum GeneralSettings {
	
	ARAS("AGILE RAS", "true", "Confirm if agile RAS should be called on VOU read completion");
	
	/**
	 * Instantiates a new general settings.
	 *
	 * @param name the name
	 * @param value the value
	 * @param description the description
	 */
	private GeneralSettings(String name, 
			String value, String description){
		
		this.setDescription(description);
		this.setName(name);
		this.setValue(value);
	}
	
	/**
	 * From name.
	 *
	 * @param name the name
	 * @return the general settings
	 */
	public static GeneralSettings fromName(String name){
		if (name != null && !name.isEmpty())
			for (GeneralSettings generalSettings : GeneralSettings.values()){
				if (generalSettings.getName().equalsIgnoreCase(name))
					return generalSettings;
			}
		
		return null;
	}
	
	/**
	 * Literals.
	 *
	 * @return the list
	 */
	public static List<String> literals(){
		List<String> literals = new ArrayList<String>();

		for (GeneralSettings generalSettings : GeneralSettings.values()){
			literals.add(generalSettings.getName());
		}
		
		Collections.sort(literals, new Comparator<String>() {
			public int compare(String a, String b){
				return a.compareToIgnoreCase(b);
			}
		});
		
		return literals;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/** The name. */
	private String name ;
	
	/** The description. */
	private String description ;
	
	/** The value. */
	private String value ;

}