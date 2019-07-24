package com.psa.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="user_profile")
public class UserProfile implements Serializable{
private static final long serialVersionUID = 1L;	

@Id
@Column(name="user_id")
private String userId;

@Column(name="first_name")
private String firstName;

@Column(name="last_name")
private String lastName;

@Column(name="auth_code")
private String authCode;

@Column(name="role")
private String role;

@Column(name="region")
private String region;

@Column(name="system_type")
private String systemType;

@Column(name="email_id")
private String emailId;

@JsonFormat(timezone= "IST", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
@Column(name="audit_timestamp")
private Timestamp auditTimestamp;

public Timestamp getAuditTimestamp() {
	return auditTimestamp;
}

public void setAuditTimestamp(Timestamp auditTimestamp) {
	this.auditTimestamp = auditTimestamp;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getAuthCode() {
	return authCode;
}

public void setAuthCode(String authCode) {
	this.authCode = authCode;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

public String getSystemType() {
	return systemType;
}

public void setSystemType(String systemType) {
	this.systemType = systemType;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}



}
