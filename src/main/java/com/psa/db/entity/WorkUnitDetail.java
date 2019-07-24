package com.psa.db.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.psa.db.WorkUnitDetailId;

@Entity
@Table(name="work_unit_detail")

public class WorkUnitDetail {
	
	@EmbeddedId
	private WorkUnitDetailId unitId;
  
    @Column(name="psa_code")
    private String psaCode;
    
    @Column(name="user_id")
    private String userId;
    
    @Column(name="work_unit_desc")
    private String workUnitDesc;
    
    @Column(name="region")
    private String region;
    
    @JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="audit_timestamp")
    private Timestamp auditTimestamp;

	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
	 @JoinColumn(name = "psa_code", nullable = false,insertable=false,updatable=false),
	 @JoinColumn(name = "system_type", nullable = false,insertable=false,updatable=false),
	 @JoinColumn(name = "sub_system_type", nullable = false,insertable=false,updatable=false),
	 @JoinColumn(name = "rec_type", nullable = false,insertable=false,updatable=false)
	})
	 private PsaMapping psa;*/

	 /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "psaId.psaCode")
     @JsonProperty("psaMapping")
private Set<PsaMapping> psa = new HashSet<PsaMapping>();*/
	 
    /*public String getWorkUnit() {
		return unitId.getWorkUnit();
	}

	public void setWorkUnit(String workUnit) {
		this.unitId.setWorkUnit(workUnit);
	}

	public String getSystemType() {
		return unitId.getSystemType();
	}

	public void setSystemType(String systemType) {
		this.unitId.setSystemType(systemType);
	}
	
	public String getSubSystemType() {
		return unitId.getSubSystemType();
	}

	public void setSubSystemType(String subSystemType) {
		this.setSubSystemType(subSystemType);
	}

	public String getRecType() {
		return unitId.getRecType();
	}

	public void setRecType(String recType) {
		this.setRecType(recType);
	}*/
	public Timestamp getAuditTimestamp() {
		return auditTimestamp;
	}

	public void setAuditTimestamp(Timestamp auditTimestamp) {
		this.auditTimestamp = auditTimestamp;
	}

	public WorkUnitDetailId getUnitId() {
		return unitId;
	}

	public void setUnitId(WorkUnitDetailId unitId) {
		this.unitId = unitId;
	}

	public String getPsaCode() {
		return psaCode;
	}

	public void setPsaCode(String psaCode) {
		this.psaCode = psaCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkUnitDesc() {
		return workUnitDesc;
	}

	public void setWorkUnitDesc(String workUnitDesc) {
		this.workUnitDesc = workUnitDesc;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	

	/*public PsaMapping getPsa() {
		return psa;
	}

	public void setPsa(PsaMapping psa) {
		this.psa = psa;
	}*/

	/*public Set<PsaMapping> getPsa() {
		return psa;
	}

	public void setPsa(Set<PsaMapping> psa) {
		this.psa = psa;
	}*/

	
    
    

}
