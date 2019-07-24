package com.psa.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="subsystem_table")

public class SubSystemTable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="sub_system_type")
    private String subSystemType;
    
    @Column(name="sub_system_description")
    private String subSystemDescription;
    
    @Column(name="user_id")
    private String userId;

    @JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="audit_timestamp")
    private Timestamp auditTimestamp;
    
	public Timestamp getAuditTimestamp() {
		return auditTimestamp;
	}

	public void setAuditTimestamp(Timestamp auditTimestamp) {
		this.auditTimestamp = auditTimestamp;
	}

	public String getSubSystemType() {
		return subSystemType;
	}

	public void setSubSystemType(String subSystemType) {
		this.subSystemType = subSystemType;
	}

	public String getSubSystemDescription() {
		return subSystemDescription;
	}

	public void setSubSystemDescription(String subSystemDescription) {
		this.subSystemDescription = subSystemDescription;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    

}
