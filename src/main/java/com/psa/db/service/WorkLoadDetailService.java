package com.psa.db.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.db.WorkLoad;
import com.psa.db.WorkLoadView;
import com.psa.db.dao.WorkLoadDetailDao;
import com.psa.db.entity.WorkLoadDetail;



@Service
public class WorkLoadDetailService {
	
	@Autowired
	private WorkLoadDetailDao wrkDao;
	
	public WorkLoadDetail getWorkLoadById(String userId) {
		return wrkDao.getWorkLoadById(userId);
	}
	
	public List<WorkLoadDetail> getAllWorkLoadById(String userId) {
		return wrkDao.getAllWorkLoadById(userId);
	}
	
	/*public void updateWorkLoad(String userId,List<WorkLoad> workLoad,String eDate) throws ParseException {
		 wrkDao.updateWorkLoad(userId,workLoad,eDate);
	}*/
	
	public WorkLoadDetail getWorkLoadByIdDate(String userId , Date endDate) {
		   return wrkDao.getWorkLoadByIdDate(userId, endDate);
	}
	
	public List<WorkLoadView> getWorkLoadDetailForWeek(String userId , String endDate) throws ParseException {
		   return wrkDao.getWorkLoadDetailForWeek(userId, endDate);
	}
	public boolean WorkLoadExists(String userId , String endDate) throws ParseException {
		   return wrkDao.WorkLoadExists(userId, endDate);
	}
	
	public boolean viewWork (String userId,Date effortDate){
		WorkLoadDetail wrk = wrkDao.getWorkLoadByIdDate(userId, effortDate);
		if(wrk == null ){
			return true;
		}
		else{
			
		}
		return false;
	}
	
	public void addWorkLoad(String id,String endDate, List<WorkLoad> workLoad) throws ParseException {
		wrkDao.addWorkLoad(id,endDate,workLoad);
	}
	
	
	/*public void deleteWorkLoad(String userId,Date effortDate, String unit) {
		wrkDao.deleteWorkLoad(userId,effortDate,unit);
	}*/
		
}
