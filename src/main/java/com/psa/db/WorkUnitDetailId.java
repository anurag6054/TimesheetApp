package com.psa.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WorkUnitDetailId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="work_unit")
	private String workUnit;
	
	@Column(name="system_type")
	private String systemType;
	
	@Column(name="sub_system_type")
	private String subSystemType;
	
	@Column(name="rec_type")
	private String recType;

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	
	public String getSubSystemType() {
		return subSystemType;
	}

	public void setSubSystemType(String subSystemType) {
		this.subSystemType = subSystemType;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}
	

}
