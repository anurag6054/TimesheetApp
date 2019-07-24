package com.psa.db;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class WorkLoadDetailId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private String userId;
	
    @Column(name="effort_type")
	private String effortType;
	
    @Column(name="work_unit")
    private String workUnit;
    
    @Column(name="psa_code")
	private String psaCode;

	@Column(name="system_type")
	private String systemType;

	@Column(name="sub_system_type")
	private String subSystemType;

	@Column(name="rec_type")
	private String recType;
	
	@JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="effort_date")
    private  Date effortDate;
    
    public String getPsaCode() {
		return psaCode;
	}

	public void setPsaCode(String psaCode) {
		this.psaCode = psaCode;
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

	
    
    
    
   /* public WorkLoadDetailId(){
    	
    }
    
public WorkLoadDetailId(String userId, String workUnit,Date effortDate){
    	this.userId = userId;
    	this.workUnit = workUnit;
    	this.effortDate = effortDate;
    }*/

    
	public String getWorkUnit() {
		return workUnit;
	}

	/*public WorkLoadDetailIdPrimary getWkp() {
		return wkp;
	}

	public void setWkp(WorkLoadDetailIdPrimary wkp) {
		this.wkp = wkp;
	}*/

	
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getEffortDate() {
		return effortDate;
	}

	public void setEffortDate(Date effortDate) {
		this.effortDate = effortDate;
	}

	public String getEffortType() {
		return effortType;
	}

	public void setEffortType(String effortType) {
		this.effortType = effortType;
	}
	
	
    
}
