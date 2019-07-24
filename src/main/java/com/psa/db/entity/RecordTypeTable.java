package com.psa.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="record_type_table")

public class RecordTypeTable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="record_type")
    private String recordType;
    
    @Column(name="record_type_description")
    private String recordTypeDescription;
    
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

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordTypeDescription() {
		return recordTypeDescription;
	}

	public void setRecordTypeDescription(String recordTypeDescription) {
		this.recordTypeDescription = recordTypeDescription;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
    

}
