package com.psa.db.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.psa.db.RecordTypeCategoryId;

@Entity
@Table(name ="record_type_category")
public class RecordTypeCategory {
	
	@EmbeddedId
	private RecordTypeCategoryId recId;
	
	@Column(name="record_description")
	private String recordDescription;

	public RecordTypeCategoryId getRecId() {
		return recId;
	}

	public void setRecId(RecordTypeCategoryId recId) {
		this.recId = recId;
	}
	
/*	public String getRecordType() {
		return recId.getRecordType();
	}

	public void setRecordType(String recordType) {
		this.recId.setRecordType(recordType);
	}

	public String getRecordCategory() {
		return recId.getRecordCategory();
	}

	public void setRecordCategory(String recordCategory) {
		this.recId.setRecordCategory(recordCategory);
	}*/

	public String getRecordDescription() {
		return recordDescription;
	}

	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}
	
	

}
