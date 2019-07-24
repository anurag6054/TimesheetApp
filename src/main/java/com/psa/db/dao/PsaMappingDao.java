package com.psa.db.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psa.db.entity.PsaMapping;


@Transactional
@Repository
public class PsaMappingDao {

	@PersistenceContext	
	private EntityManager entityManager;
	
	 @SuppressWarnings("unchecked")
	public PsaMapping getPsaByCode(String code) {
		  String hql="FROM PsaMapping as psa WHERE psa.psaId.psaCode=:code";  	
			List <PsaMapping> ls=entityManager.createQuery(hql).setParameter("code",code).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return ls.get(0);
			  else
		   return null;
	}
	
	 public List<PsaMapping> getPsaCode(String system, String subSystem, String rec) {
		  String hql="FROM PsaMapping as psa WHERE psa.psaId.systemType=:sys and psa.psaId.subSystemType=:sub and psa.psaId.recType =:rec";  	
			List<PsaMapping> ls=(List<PsaMapping>) entityManager.createQuery(hql).setParameter("sys",system).setParameter("sub",subSystem).setParameter("rec",rec).getResultList();
			return ls;
	}
	 
	public void addPsa(List<PsaMapping> psa) {
		for(int i=0; i<psa.size();i++){
		entityManager.persist(psa.get(i));
		}
	}
	
	/*public void updatePsa(PsaMapping psa) {
		PsaMapping ps = getPsaByCode(psa.getPsaCode());
		ps.setPsaDesc(psa.getPsaDesc());
		ps.setRecType(psa.getRecType());
		ps.setSubSystemType(psa.getSubSystemType());
		ps.setSystemType(psa.getSystemType());
		entityManager.flush();
	}
	*/

	public void deletePsa(String code) {
		entityManager.remove(getPsaByCode(code));
	}
	
}
