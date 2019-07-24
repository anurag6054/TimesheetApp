package com.psa.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.psa.db.entity.UserProfile;
import com.psa.db.service.EmailService;

@Transactional
@Repository
public class UserProfileDao {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	private EmailService emailService;
	
	public UserProfile getUserDetail(String userId) {
		return entityManager.find(UserProfile.class, userId);
	}

	@SuppressWarnings("unchecked")
	public UserProfile getUserById(String userId) {
		  String hql="FROM UserProfile as usr WHERE usr.userId=:user";   
			 List <UserProfile> ls=entityManager.createQuery(hql).setParameter("user",userId).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return ls.get(0);
			   else
		         return null;
	}
	
	public void addUser(List<UserProfile> user) {
		for(int i=0; i<user.size();i++){
		entityManager.persist(user.get(i));
		}
	}
	
	public void updateUser(UserProfile user) {
		UserProfile usr = getUserById(user.getUserId());
		usr.setAuthCode(user.getAuthCode());
		entityManager.flush();
	}
	

	public void deleteUser(String userId) {
		entityManager.remove(getUserById(userId));
	}
	
/*	@SuppressWarnings("unchecked")
	public boolean authentication(String userId, String pswd) {
		String hql="FROM UserProfile as usr WHERE usr.userId=:user and usr.authCode =:pswd";   
		 List <UserProfile> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("pswd",pswd).getResultList();
		  if(ls!=null && !ls.isEmpty())
			  return true;
		   else
	         return false;
	}*/
	
	public String authentication(String userId, String pswd) {
		String hql="FROM UserProfile as usr WHERE usr.userId=:user and usr.authCode =:pswd";   
		 List <UserProfile> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("pswd",pswd).getResultList();
		  if(ls!=null && !ls.isEmpty())
			  return ls.get(0).getRole();
		   else
	         return null;
	}
	
	public String userRegion(String userId) {
		String hql="FROM UserProfile as usr WHERE usr.userId=:user";   
		 List <UserProfile> ls=entityManager.createQuery(hql).setParameter("user",userId).getResultList();
		  if(ls!=null && !ls.isEmpty())
			  return ls.get(0).getRegion();
		   else
	         return null;
	}
	
	public String userSystem(String userId) {
		String hql="FROM UserProfile as usr WHERE usr.userId=:user";   
		 List <UserProfile> ls=entityManager.createQuery(hql).setParameter("user",userId).getResultList();
		  if(ls!=null && !ls.isEmpty())
			  return ls.get(0).getSystemType();
		   else
	         return null;
	}
	
	public String emailRemainder() {
		String hql="FROM UserProfile as usr ";   
		 List <UserProfile> ls=entityManager.createQuery(hql).getResultList();
		for(int i=0;i<ls.size();i++){
		String emailId = ls.get(i).getEmailId();
		   emailService.SendTimesheetRemainder(emailId);
		}
		return "email has been sent to all";
	}
	
	@SuppressWarnings("unchecked")
	public void emailRemainderFriday(String userId) throws ParseException {
			String ql="FROM UserProfile as usr WHERE usr.userId=:user";  
			List <UserProfile> ls=entityManager.createQuery(ql).setParameter("user",userId).getResultList();
		         for(int j =0; j<ls.size();j++){ 
					     String emailId = ls.get(j).getEmailId();    
					      emailService.SendTimesheetRemainder(emailId);
		         }
		
}

	public List<String> getAllUserID() throws ParseException {
		List<String> uid = new ArrayList<String>();
			String ql="FROM UserProfile as usr";  
			List <UserProfile> ls=entityManager.createQuery(ql).getResultList();
			for(int k=0; k < ls.size(); k++){
			   uid.add(ls.get(k).getUserId());	
	}
			return uid;	
	}
}

