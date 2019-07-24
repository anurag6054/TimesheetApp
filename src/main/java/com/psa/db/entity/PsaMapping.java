package com.psa.db.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.psa.db.PsaMappingId;
@Entity
@Table(name="psa_mapping")

public class PsaMapping {
	@EmbeddedId
	private PsaMappingId psaId;
	
    @Column(name="psa_desc")
    private String psaDesc;
    
    @Column(name="user_id")
    private String userId;
    
    @JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="audit_timestamp")
    private Timestamp auditTimestamp;
    
    
   /* public String getPsaCode() {
		return psaId.getPsaCode();
	}

	public void setPsaCode(String psaCode) {
		psaId.setPsaCode(psaCode);
	}

	public String getSystemType() {
		return psaId.getSystemType();
	}

	public void setSystemType(String systemType) {
		psaId.setSystemType(systemType);
	}
	
	public String getSubSystemType() {
		return psaId.getSubSystemType();
	}

	public void setSubSystemType(String subSystemType) {
		psaId.setSubSystemType(subSystemType);
	}

	public String getRecType() {
		return psaId.getRecType();
	}

	public void setRecType(String recType) {
		psaId.setRecType(recType);
	}*/
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getAuditTimestamp() {
		return auditTimestamp;
	}

	public void setAuditTimestamp(Timestamp auditTimestamp) {
		this.auditTimestamp = auditTimestamp;
	}

	public String getPsaDesc() {
		return psaDesc;
	}

	public void setPsaDesc(String psaDesc) {
		this.psaDesc = psaDesc;
	}

	public PsaMappingId getPsaId() {
		return psaId;
	}

	public void setPsaId(PsaMappingId psaId) {
		this.psaId = psaId;
	}
    
	
    
    

}
