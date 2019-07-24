package com.psa.db.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psa.db.dao.PsaMappingDao;
import com.psa.db.entity.PsaMapping;


@Service
public class PsaMappingService {

	@Autowired
	private PsaMappingDao psaDao;
	
	 
	public PsaMapping getPsaByCode(String code) {
		return psaDao.getPsaByCode(code);
	}
	
	
	public void addPsa(List<PsaMapping> psa) {
		psaDao.addPsa(psa);
	}
	
	
	/*public void updatePsa(PsaMapping psa) {
		psaDao.updatePsa(psa);
	}*/
	
	
	public void deletePsa(String code) {
		psaDao.deletePsa(code);
	}
	
	public List<PsaMapping> getPsaCode(String system, String subSystem, String rec) {
		return  psaDao.getPsaCode(system, subSystem, rec);
	}
		
}
