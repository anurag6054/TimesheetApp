package com.psa.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecordTypeCategoryId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="record_type")
	private String recordType;
	
	@Column(name="record_category")
	private String recordCategory;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordCategory() {
		return recordCategory;
	}

	public void setRecordCategory(String recordCategory) {
		this.recordCategory = recordCategory;
	}
	

}
