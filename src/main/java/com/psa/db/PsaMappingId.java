package com.psa.db;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PsaMappingId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="psa_code")
	private String psaCode;
	
	@Column(name="system_type")
	private String systemType;
	
	@Column(name="sub_system_type")
	private String subSystemType;
	
	@Column(name="rec_type")
	private String recType;
	
	   public PsaMappingId(){
	    	
	    }
	    
	public PsaMappingId(String psaCode, String systemType, String subSystemType, String recType){
	    	this.psaCode = psaCode;
	    	this.systemType = systemType;
	    	this.subSystemType = subSystemType;
	    	this.recType = recType;
	    }


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
	
	
	 /* @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof PsaMappingId)) return false;
	        PsaMappingId that = (PsaMappingId) o;
	        return Objects.equals(getPsaCode(), that.getPsaCode()) &&
	                Objects.equals(getSystemType(), that.getSystemType()) &&
	                Objects.equals(getSubSystemType(), that.getSubSystemType()) &&
	                Objects.equals(getRecType(), that.getRecType());
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(getPsaCode(), getSystemType(), getSubSystemType(), getRecType() );
	    }

*/

}
