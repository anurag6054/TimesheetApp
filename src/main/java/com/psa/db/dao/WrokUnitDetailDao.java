package com.psa.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psa.db.entity.UserProfile;
import com.psa.db.entity.WorkUnitDetail;

@Transactional
@Repository
public class WrokUnitDetailDao {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	private UserProfileDao userDao;
	
	@SuppressWarnings("unchecked")
	public List<WorkUnitDetail> getWorkUnitDetail(String workDesc) {
		 String hql="FROM WorkUnitDetail as unt WHERE unt.unitId.workUnitDesc=:desc";   
		 return (List<WorkUnitDetail>)entityManager.createQuery(hql).setParameter("desc",workDesc).getResultList();
	}
	
/*//	@SuppressWarnings("unchecked")
//	public List<WorkUnitDetail> getSimilarWorkUnitDetail(String workDesc, String id) {
//		String reg = userDao.userRegion(id);
//		 String hql="FROM WorkUnitDetail as unt WHERE  upper(unt.workUnitDesc) like :desc AND unt.region =:regn ";  
//		 List<WorkUnitDetail> ls = (List<WorkUnitDetail>)entityManager.createQuery(hql).setParameter("desc","%"+workDesc.toUpperCase()+"%").setParameter("regn",reg).getResultList();
//		 return ls;
//	}
*/	
	@SuppressWarnings("unchecked")
	public List<WorkUnitDetail> getWorkUnitDetailForSystem(String workDesc, String id) {
		String sys = userDao.userSystem(id);
		 String hql="FROM WorkUnitDetail as unt WHERE  upper(unt.workUnitDesc) like :desc AND unt.unitId.systemType =:systm ";  
		 List<WorkUnitDetail> ls = (List<WorkUnitDetail>)entityManager.createQuery(hql).setParameter("desc","%"+workDesc.toUpperCase()+"%").setParameter("systm",sys).getResultList();
		 return ls;
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkUnitDetail> getWorkUnitDetailByUnit(String unit) {
		 String hql="FROM WorkUnitDetail as unt WHERE unt.unitId.workUnit =:unit";   
		 return (List<WorkUnitDetail>)entityManager.createQuery(hql).setParameter("unit",unit).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkUnitDetail> getAllWorkUnitDetail() {
		 String hql="FROM WorkUnitDetail as unt ";   
		 return (List<WorkUnitDetail>)entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkUnitDetail> getAllWorkUnitById(String userId) {
		String hql="FROM WorkUnitDetail as unt  WHERE unt.userId=:user";   
		  return (List<WorkUnitDetail>)entityManager.createQuery(hql).setParameter("user",userId).getResultList();
	}
	
	public void addWorkUnit(List<WorkUnitDetail> workUnit) {
		for(int i=0; i<workUnit.size();i++){
		entityManager.persist(workUnit.get(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkUnitDetail> getAllWorkUnitPsa(String workUnit) {
		String hql="FROM WorkUnitDetail as unt join unt.PsaMapping as psa WHERE unt.workUnit=:unit";   
		  return (List<WorkUnitDetail>)entityManager.createQuery(hql).setParameter("unit",workUnit).getResultList();
	}
	
	/*public void updateWorkUpdate(WorkUnitDetail workUnit) {
		List<WorkUnitDetail> wrk = getAllWorkUnitById(workUnit.getUserId());
		wrk.setSystemType(workUnit.getSystemType());
		wrk.setSubSystemType(workUnit.getSubSystemType());
		wrk.setPsaCode(workUnit.getPsaCode());
		entityManager.flush();
	}
	*/

	public void deleteWorkUnit(String userId) {
		entityManager.remove(getAllWorkUnitById(userId));
	}
	
}
