package com.nano.vou.jaxb;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class CDRVouMesh.
 *
 * @author segz
 */

@SuppressWarnings("serial")
@XmlRootElement(name = "mesh")
@XmlAccessorType(XmlAccessType.FIELD)
public class CDRVouMesh implements Serializable {
	
	/** The cdr vous. */
	@XmlElement(name = "request")
	private List<CDRVou> cdrVous ;

	/**
	 * Gets the cdr vous.
	 *
	 * @return the cdr vous
	 */
	public List<CDRVou> getCdrVous() {
		return cdrVous;
	}

	/**
	 * Sets the cdr vous.
	 *
	 * @param cdrVous the new cdr vous
	 */
	public void setCdrVous(List<CDRVou> cdrVous) {
		this.cdrVous = cdrVous;
	}

}
