package com.psa.db.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.db.dao.UserProfileDao;
import com.psa.db.dao.WorkLoadDetailDao;
import com.psa.db.dto.UserWorkLoadMapping;
import com.psa.db.entity.UserProfile;


@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileDao uDao;
	
	@Autowired
	private WorkLoadDetailDao wDao;
	
	/*@Autowired
	private UserWorkLoadMapping uDto;*/
	
	
	public UserProfile getUserDetail(String userId) {
	 return uDao.getUserDetail(userId);
	}
	
	
	public void addUser(List<UserProfile> user) {
		uDao.addUser(user);
	}
	
	
	public void deleteUser(String userId) {
		uDao.deleteUser(userId);
	}
	
	
	public void updateUser(UserProfile user) {
		uDao.updateUser(user);
	}
	
	public String authentication(String userId, String pswd){
		return uDao.authentication(userId, pswd);
	}

	public String emailRemainder(){
		return uDao.emailRemainder();
	}
	
	public void emailRemainderFriday(String endDate) throws ParseException{
		Date en = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		Calendar dt = Calendar.getInstance();
		dt.setTime(en);
		dt.add(Calendar.DATE, -7);
		Date begDate = dt.getTime();
		List<String> ud = new ArrayList<String>();
		ud = uDao.getAllUserID();
		for(int i =0; i< ud.size(); i++){
	          String userId = ud.get(i);
		     if(!(wDao.EffortExists(userId,en,begDate))){
	              uDao.emailRemainderFriday(userId);
	           }
		  }
		
	}
}
