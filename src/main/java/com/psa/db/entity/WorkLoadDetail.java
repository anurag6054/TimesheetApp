package com.psa.db.entity;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.psa.db.WorkLoadDetailId;

@Entity
@Table(name="work_load_detail")
public class WorkLoadDetail{
	
@EmbeddedId
private WorkLoadDetailId wrkId;


    @Column(name="effort")
    private int effort;
     
    @Column(name="rec_category")
    private String recCategory;
    
    @Column(name="work_unit_desc")
    private String workUnitDesc;
    
    @JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="audit_timestamp")
    private Timestamp auditTimestamp;

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public String getRecCategory() {
		return recCategory;
	}

	public void setRecCategory(String recCategory) {
		this.recCategory = recCategory;
	}
	
	public String getWorkUnitDesc() {
		return workUnitDesc;
	}

	public void setWorkUnitDesc(String workUnitDesc) {
		this.workUnitDesc = workUnitDesc;
	}

	public Timestamp getAuditTimestamp() {
		return auditTimestamp;
	}

	public void setAuditTimestamp(Timestamp auditTimestamp) {
		/*Instant instant = Instant.now();
		long auditTimestamp1 = instant.getEpochSecond()*/;
		this.auditTimestamp = auditTimestamp;
	}

	public WorkLoadDetailId getWrkId() {
		return wrkId;
	}

	public void setWrkId(WorkLoadDetailId wrkId) {
		this.wrkId = wrkId;
	}

}
