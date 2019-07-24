package com.psa.db;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EffortDetails {

	private int effort;

	@JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date effortDate;

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public Date getEffortDate() {
		return effortDate;
	}

	public void setEffortDate(Date effortDate) {
		this.effortDate = effortDate;
	}

	

	
	
}
