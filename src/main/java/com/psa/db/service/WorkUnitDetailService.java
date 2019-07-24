package com.psa.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.db.dao.WrokUnitDetailDao;
import com.psa.db.entity.WorkUnitDetail;

@Service
public class WorkUnitDetailService {
	
	@Autowired
	private WrokUnitDetailDao unitDao;
	
	public List<WorkUnitDetail> getAllWorkUnitById(String userId) {
		return unitDao.getAllWorkUnitById(userId);
	}
	
	
	
	public void addWorkUnit(List<WorkUnitDetail> wrkUnit) {
		unitDao.addWorkUnit(wrkUnit);
	}
	
	public List<WorkUnitDetail> getWorkUnitDetailForSystem(String desc,String id) {
		return unitDao.getWorkUnitDetailForSystem(desc,id);
	}
	
	public List<WorkUnitDetail> getWorkUnitDetailByUnit(String workUnit) {
		return unitDao.getWorkUnitDetailByUnit(workUnit);
	}
	
	public List<WorkUnitDetail> getAllWorkUnitDetail() {
		return unitDao.getAllWorkUnitDetail();
	}
	
	/*public void updateWorkUpdate(WorkUnitDetail workUnit) {
		unitDao.updateWorkUpdate(workUnit);
	}
	

	public void deleteWorkUnit(String userId) {
		unitDao.deleteWorkUnit(userId);
	}*/

}
