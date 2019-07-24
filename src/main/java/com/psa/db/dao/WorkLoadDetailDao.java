package com.psa.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.psa.db.EffortDetails;
import com.psa.db.WorkLoad;
import com.psa.db.WorkLoadDetailId;
import com.psa.db.WorkLoadView;
import com.psa.db.entity.UserProfile;
import com.psa.db.entity.WorkLoadDetail;
import com.psa.db.entity.WorkUnitDetail;
import com.psa.db.service.EmailService;

@Transactional
@Repository
public class WorkLoadDetailDao {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private EmailService emailService;
	
	
	
	@SuppressWarnings("unchecked")
	public WorkLoadDetail getWorkLoadById(String userId) {
		  String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user";   
			 List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return ls.get(0);
			  else
		   return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkLoadDetail> getAllWorkLoadById(String userId) {
		  String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user";   
		  return (List<WorkLoadDetail>)entityManager.createQuery(hql).setParameter("user",userId).getResultList();
			
	}
	
	@SuppressWarnings("unchecked")
	public WorkLoadDetail getWorkLoadByIdDate(String userId , Date endDate) {
		   String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate=:endDate";   
			 List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return ls.get(0);
			  else
		   return null;
	}
	
	public boolean WorkLoadExists(String userId , String endDate) throws ParseException {
		 Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			cal.add(Calendar.DATE, -7);
			Date begDate = cal.getTime();
			String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate <=:endDate AND wrk.wrkId.effortDate >= :begDate";   
			 List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", end).setParameter("begDate",begDate).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return true;
			  else
		   return false;
	}
	
	public boolean EffortExists(String userId , Date endDate,Date begDate) throws ParseException {
		   String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate<=:endDate AND wrk.wrkId.effortDate >= :begDate";    
			 @SuppressWarnings("unchecked")
			List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).setParameter("begDate", begDate).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return true;
			  else
		   return false;
	}
	
	@SuppressWarnings("unchecked")
	public List <WorkLoadDetail> getWorkLoadByDate(String userId , Date endDate) {
		   String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate <=:endDate";   
			 List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return ls;
			  else
		   return null;
	}
	
	public int addEfforts(String userId , Date endDate){
		int hour = 0;
		String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate <=:endDate";
		List<WorkLoadDetail> ls =  entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).getResultList();
		if(ls!=null && !ls.isEmpty())
		{
		hour = ls.get(0).getEffort();
		return hour;
		}
		else {
			return 0;
		}
	}
	
	
	
// METHODS TO ADD WORKlOAD TO THE WORKLOADDETAIL TABLE -- ADD STARTS HERE//	
	public void addWorkLoad(String id,String eDate, List<WorkLoad> workLoad) throws ParseException {
		
		List<EffortDetails> effort= new ArrayList<EffortDetails>();
		List<WorkLoadDetail> detail = new ArrayList<WorkLoadDetail>();
		Date effortDate = new Date();
		int hour = 0;
		for(int i=0; i<workLoad.size();i++){
			  for(int j=0; j<7;j++){
				  WorkLoadDetail wrk = new WorkLoadDetail();
				  WorkLoadDetailId wrkId = new WorkLoadDetailId();
				if(j==0){
					 effortDate = workLoad.get(i).getSunday().getEffortDate();
					hour = workLoad.get(i).getSunday().getEffort();
				}
				else if(j==1){
					effortDate = workLoad.get(i).getMonday().getEffortDate();
					hour = workLoad.get(i).getMonday().getEffort();
				}
				else if(j==2){
				    effortDate = workLoad.get(i).getTuesday().getEffortDate();
					hour = workLoad.get(i).getTuesday().getEffort();
				}
				else if(j==3){
					effortDate = workLoad.get(i).getWednesday().getEffortDate();
					hour = workLoad.get(i).getWednesday().getEffort();
				}
				else if(j==4){
					effortDate = workLoad.get(i).getThursday().getEffortDate();
					hour = workLoad.get(i).getThursday().getEffort();
				}
				else if(j==5){
					effortDate = workLoad.get(i).getFriday().getEffortDate();
					hour = workLoad.get(i).getFriday().getEffort();
				}
				else if(j==6){
					effortDate = workLoad.get(i).getSaturday().getEffortDate();
					hour = workLoad.get(i).getSaturday().getEffort();
				}
				
			wrkId.setUserId(id);
			wrkId.setWorkUnit(workLoad.get(i).getWorkUnit());
			wrkId.setEffortDate(effortDate);
			wrkId.setPsaCode(workLoad.get(i).getPsaCode());
			wrkId.setSystemType(workLoad.get(i).getSystemType());
			wrkId.setSubSystemType(workLoad.get(i).getSubSystemType());
			wrkId.setRecType(workLoad.get(i).getRecType());
			wrk.setWorkUnitDesc(workLoad.get(i).getWorkUnitDesc());
			wrk.setRecCategory(workLoad.get(i).getRecCategory());
			wrkId.setEffortType(workLoad.get(i).getEffortType());
			wrk.setEffort(hour);
			wrk.setWrkId(wrkId);
			detail.add(wrk);
		}
	}
		addToWorkLoadDetail(detail,eDate,id);
	}
		
	public void addToWorkLoadDetail(List<WorkLoadDetail> workLoad,String eDate,String id) throws ParseException {
		List<WorkLoadDetail>wld=new ArrayList<WorkLoadDetail>();		
		for(int k=0;k<workLoad.size();k++){
		     for(int i=k+1; i<workLoad.size();i++){
			  if ((workLoad.get(i).getWrkId().getUserId()==workLoad.get(k).getWrkId().getUserId()) && (workLoad.get(i).getWrkId().getWorkUnit()==workLoad.get(k).getWrkId().getWorkUnit()) &&
					  (workLoad.get(i).getWrkId().getEffortDate().equals(workLoad.get(k).getWrkId().getEffortDate()))){  
				workLoad.get(k).setEffort(workLoad.get(k).getEffort() + workLoad.get(i).getEffort());
			    workLoad.remove(i);
		          }
			   else{
				  
				   workLoad.get(k).setEffort(workLoad.get(k).getEffort());
			     }
			  
		    }
	   }
		
		for(int b=0;b<workLoad.size();b++){
			wld.add(workLoad.get(b));
		}
		
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, -7);
		Date begDate = cl.getTime();
		if(EffortExists(id,endDate,begDate)){
			   deleteWorkLoadForDate(id,endDate,begDate);
		       }
		enterWorkLoadDetail(wld,eDate);
	}
	
	public void enterWorkLoadDetail(List<WorkLoadDetail> wrkld,String eDate) throws ParseException {
		for(int w=0;w<wrkld.size();w++){
			String userId = wrkld.get(w).getWrkId().getUserId();
		 if(wrkld.get(w).getEffort()>0){
		                 entityManager.persist(wrkld.get(w));
		               }
             }

	   }

	
	
	 @SuppressWarnings("unchecked")
		public List <WorkLoadDetail> getWorkLoadDetailDelete(String userId,Date endDate, Date begDate) throws ParseException {
		 String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate<=:endDate AND wrk.wrkId.effortDate >= :begDate";    
		 @SuppressWarnings("unchecked")
		List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).setParameter("begDate", begDate).getResultList();
		  if(ls!=null && !ls.isEmpty())
			  return ls;
		  else
	   return null;
		}
	 
	 
		@SuppressWarnings("unchecked")
		public boolean getEfforts(String userId,Date effortDate) {
			String hql1="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate =:effDate";
		    @SuppressWarnings("unchecked")
			List <WorkLoadDetail> wld=entityManager.createQuery(hql1).setParameter("user",userId).setParameter("effDate", effortDate)
		    		 .getResultList();
		    
				  if(wld!=null && !wld.isEmpty())
					  return true;
				  else
			       return false;
		}
		
  public void deleteWorkLoadForDate(String userId,Date endDate, Date begDate) throws ParseException {
		 List<WorkLoadDetail> dt = new ArrayList<WorkLoadDetail>();
		 dt = getWorkLoadDetailDelete(userId,endDate,begDate);
		 for (int d=0;d<dt.size();d++){
			entityManager.remove(dt.get(d));
		 }
		}
	
//ADD METHOD ENDS HERE//
	
	@SuppressWarnings("unchecked")
	public WorkLoadDetail getWorkLoadByIdDateUnit(String userId , Date endDate , String unit) {
		   String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate=:endDate AND wrk.wrkId.workUnit =:unit";   
			 List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).setParameter("unit", unit).getResultList();
			  if(ls!=null && !ls.isEmpty())
				  return ls.get(0);
			  else
		   return null;
	}
	
	
	
	
	//METHODS TO VIEW WORKLOADDETAIL TABLE
	
	@SuppressWarnings("unchecked")
	public List <WorkLoadView> getWorkLoadDetailForWeek(String userId , String eDate) throws ParseException {
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, -7);
		Date begDate = cal.getTime();
		   String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate <=:endDate AND wrk.wrkId.effortDate >= :begDate ORDER BY wrk.wrkId.effortDate ASC";   
			 List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", endDate).setParameter("begDate",begDate).getResultList();
			  if(ls!=null && !ls.isEmpty()){
				  
				 List<WorkLoadView> wd= getWorkLoadForWeek(ls,endDate,begDate);
				  return wd;  
			  }
				 
			  else
		   return null;
	}

	public List<WorkLoadView> getWorkLoadForWeek(List<WorkLoadDetail> wrkld,Date endDate, Date begDate){
		
		List<WorkLoadView> wl = new ArrayList<WorkLoadView>();
		boolean flag = false;
		String unit = null;
		String desc = null;
		String type = null;
		String categ = null;
		String psa = null;
		String sub = null;
		String sys = null;
		String recType = null;
		
		for(int i=0;i<wrkld.size();i++){
			//String hql1="From WorkUnitDetail as wu WHERE wu.unitId.workUnit=:unit AND wu.workUnitDesc=:desc";
		    //List<WorkUnitDetail> un=entityManager.createQuery(hql1).setParameter("desc", wrkld.get(i).getWorkUnitDesc()).setParameter("id",wrkld.get(i).getWrkId().getUserId()).getResultList();
			
			if(i==0){
				
			unit = wrkld.get(i).getWrkId().getWorkUnit();
			desc = wrkld.get(i).getWorkUnitDesc();
			type = wrkld.get(i).getWrkId().getEffortType();
			categ = wrkld.get(i).getRecCategory();
			psa = wrkld.get(i).getWrkId().getPsaCode();
			recType = wrkld.get(i).getWrkId().getRecType();
			sub = wrkld.get(i).getWrkId().getSubSystemType();
			sys = wrkld.get(i).getWrkId().getSystemType();
			flag =true;
			}else if (wl != null){
				
				for(int a=0; a<wl.size();a++){
					String unit1 = wl.get(a).getWorkUnit();
					String type1 = wrkld.get(a).getWrkId().getEffortType();
					String psa1 = wrkld.get(a).getWrkId().getPsaCode();
					String recType1 = wrkld.get(a).getWrkId().getRecType();
					String sub1 = wrkld.get(a).getWrkId().getSubSystemType();
					String sys1 = wrkld.get(a).getWrkId().getSystemType();
					if(!( (unit1.equals(wrkld.get(i).getWrkId().getWorkUnit()))
							&& (type1.equals(wrkld.get(i).getWrkId().getEffortType())) 
							&& (psa1.equals(wrkld.get(i).getWrkId().getPsaCode())) 
							&& (recType1.equals(wrkld.get(i).getWrkId().getRecType())) 
							&& (sub1.equals(wrkld.get(i).getWrkId().getSubSystemType())) 
							&& (sys1.equals(wrkld.get(i).getWrkId().getSystemType()))
					))
					
					{
						flag = true;
						
					}else{
						flag = false;
						break;
					}
				}
			}else{
				flag = false;
			   }
		if (flag){	
		     String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate <=:endDate AND "
		     		+ "wrk.wrkId.effortDate >= :begDate AND wrk.wrkId.workUnit =:unit AND wrkId.effortType =:type AND wrk.wrkId.psaCode =:code "
		     		+ "AND wrk.wrkId.systemType =:sys AND wrk.wrkId.subSystemType =:sub AND wrk.wrkId.recType =:rec";
		     		   
		     @SuppressWarnings("unchecked")
			List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",wrkld.get(i).getWrkId().getUserId()).setParameter("endDate", endDate).setParameter("begDate",begDate)
		    		 .setParameter("unit",wrkld.get(i).getWrkId().getWorkUnit())
		    		 .setParameter("type",wrkld.get(i).getWrkId().getEffortType())
		    		 .setParameter("code",wrkld.get(i).getWrkId().getPsaCode())
		    		 .setParameter("sys",wrkld.get(i).getWrkId().getSystemType())
		    		 .setParameter("sub",wrkld.get(i).getWrkId().getSubSystemType())
		    		 .setParameter("rec",wrkld.get(i).getWrkId().getRecType())
		    		 .getResultList();
		     
		     WorkLoadView wk = new WorkLoadView();
		     
		     unit = wrkld.get(i).getWrkId().getWorkUnit();
				desc = wrkld.get(i).getWorkUnitDesc();
				type = wrkld.get(i).getWrkId().getEffortType();
				categ = wrkld.get(i).getRecCategory();
				psa = wrkld.get(i).getWrkId().getPsaCode();
				recType = wrkld.get(i).getWrkId().getRecType();
				sub = wrkld.get(i).getWrkId().getSubSystemType();
				sys = wrkld.get(i).getWrkId().getSystemType();
				
		        EffortDetails sun = new EffortDetails();
	    	 	EffortDetails mon = new EffortDetails();
	    	 	EffortDetails tue = new EffortDetails();
	    	 	EffortDetails wed = new EffortDetails();
	    	 	EffortDetails thu = new EffortDetails();
	    	 	EffortDetails fri = new EffortDetails();
	    	 	EffortDetails sat = new EffortDetails();
	    	 	//defaulting efforts and dates
	    	 	Calendar defaults = Calendar.getInstance();
	    	 	defaults.setTime(endDate);
	    	 	
	    	 	Date saDate = endDate;
	    	 	
	    	 	defaults.add(Calendar.DATE, -1);
	    	 	Date fDate = defaults.getTime();
	    	 	
	    	 	defaults.add(Calendar.DATE, -1);
	    	 	Date thDate = defaults.getTime();
	    	 	
	    	 	defaults.add(Calendar.DATE, -1);
	    	 	Date wDate = defaults.getTime();
	    	 	
	    	 	defaults.add(Calendar.DATE, -1);
	    	 	Date tuDate = defaults.getTime();
	    	 	
	    	 	defaults.add(Calendar.DATE, -1);
	    	 	Date mDate = defaults.getTime();
	    	 	
	    	 	defaults.add(Calendar.DATE, -1);
	    	 	Date suDate = defaults.getTime();
	    	 	 	
	    	 	sun.setEffortDate(suDate);
	    	 	sun.setEffort(0);
	    	 	
	    	 	mon.setEffortDate(mDate);
	    	 	mon.setEffort(0);
	    	 	
	    	 	tue.setEffortDate(tuDate);
	    	 	tue.setEffort(0);
	    	 	
	    	 	wed.setEffortDate(wDate);
	    	 	wed.setEffort(0);
	    	 	
	    	 	thu.setEffortDate(thDate);
	    	 	thu.setEffort(0);
	    	 	
	    	 	fri.setEffortDate(fDate);
	    	 	fri.setEffort(0);
	    	 	
	    	 	sat.setEffortDate(saDate);
	    	 	sat.setEffort(0);
	    	 	
	    	 	wk.setMonday(mon);
    	 		wk.setTuesday(tue);
    	 		wk.setSunday(sun);
    	 		wk.setWednesday(wed);
    	 		wk.setThursday(thu);
    	 		wk.setFriday(fri);
    	 		wk.setSaturday(sat);
	    	 
//defaulting done
	    	 	
		     for(int j=0;j<ls.size();j++){
		    	 
		    	 Calendar d = Calendar.getInstance();
		    	 Calendar c= Calendar.getInstance();
		    	 c.setTime(ls.get(j).getWrkId().getEffortDate());
		    	 int day = c.get(Calendar.DAY_OF_WEEK);
		    	 	
		    	 	d.setTime(ls.get(j).getWrkId().getEffortDate());
		    	 	

		    	 	if(day == 1){

		    	 		sun.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		sun.setEffort(ls.get(j).getEffort());
		    	 		wk.setSunday(sun);		
		    	 	}
			
		    	 	else if (day==2){
				
		    	 		mon.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		mon.setEffort(ls.get(j).getEffort());
		    	 		wk.setMonday(mon);
				
		    	 	}
			
		    	 	else if (day == 3){
		    	 		tue.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		tue.setEffort(ls.get(j).getEffort());
		    	 		wk.setTuesday(tue);
		    	 	}
			
		    	 	else if (day == 4){
		    	 		wed.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		wed.setEffort(ls.get(j).getEffort());
		    	 		wk.setWednesday(wed);
		    	 	}
			
		    	 	else if (day == 5){
		    	 		thu.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		thu.setEffort(ls.get(j).getEffort());
		    	 		wk.setThursday(thu);
		    	 	}
			
		    	 	else if (day == 6){
		    	 		fri.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		fri.setEffort(ls.get(j).getEffort());
		    	 		wk.setFriday(fri);
		    	 	}
			
		    	 	else{
		    	 		sat.setEffortDate(ls.get(j).getWrkId().getEffortDate());
		    	 		sat.setEffort(ls.get(j).getEffort());
		    	 		wk.setSaturday(sat);
		    	 	}
		    	 	
		    	 	wk.setWorkUnit(unit);
		    	 	wk.setWorkUnitDesc(desc);
		    	 	wk.setEffortType(type);
		    	 	wk.setRecCategory(categ);
		    	 	wk.setPsaCode(psa);
		    	 	wk.setRecType(recType);
		    	 	wk.setSystemType(sys);
		    	 	wk.setSubSystemType(sub);
		 
		     }
		     
		    
		     
		     wl.add(wk); 
		}
         
}
		
	
		return wl;
	
}
		
//METHOD TO MODIFY TIMESHEET
	 /*@SuppressWarnings("unchecked")
	public void updateWorkLoad(String userId,List<WorkLoad> workLoad, String edt) throws ParseException {
		 Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(edt);
			Calendar upd = Calendar.getInstance();
			upd.setTime(endDate);
			upd.add(Calendar.DATE, -7);
			Date begDate = upd.getTime();
		 for(int i=0;i<workLoad.size();i++){
			 Date eDate = new Date();
			 int eff = 0;
			 for(int j=0; j<7;j++){
		  	 
		  	 if(j==0){
		  		eDate = workLoad.get(i).getSunday().getEffortDate();
		  		eff = workLoad.get(i).getSunday().getEffort();
		  	 }
		  	 else if(j==1){
		  		eDate = workLoad.get(i).getMonday().getEffortDate();
		  		eff = workLoad.get(i).getMonday().getEffort();
		  	 }
		  	 else if(j==2){
		  		eDate = workLoad.get(i).getTuesday().getEffortDate();
		  		eff = workLoad.get(i).getTuesday().getEffort();
		  	 }
		  	 else if(j==3){
		  		eDate = workLoad.get(i).getWednesday().getEffortDate();
		  		eff = workLoad.get(i).getWednesday().getEffort();
		  	 }
		  	 else if(j==4){
		  		eDate = workLoad.get(i).getThursday().getEffortDate();
		  		eff = workLoad.get(i).getThursday().getEffort();
		  	 }
		  	 else if(j==5){
		  		eDate = workLoad.get(i).getFriday().getEffortDate();
		  		eff = workLoad.get(i).getFriday().getEffort();
		  	 }
		  	 else if(j==6){
		  		eDate = workLoad.get(i).getSaturday().getEffortDate();
		  		eff = workLoad.get(i).getSaturday().getEffort();
		  	 }
		  	String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate =:endDate AND "
		     		+ "wrk.wrkId.workUnit =:unit AND wrkId.effortType =:type AND wrk.wrkId.psaCode =:code "
		     		+ "AND wrk.wrkId.systemType =:sys AND wrk.wrkId.subSystemType =:sub AND wrk.wrkId.recType =:rec";
		    @SuppressWarnings("unchecked")
			List <WorkLoadDetail> ls=entityManager.createQuery(hql).setParameter("user",userId).setParameter("endDate", eDate)
		    		 .setParameter("unit",workLoad.get(i).getWorkUnit())
		    		 .setParameter("type",workLoad.get(i).getEffortType())
		    		 .setParameter("code",workLoad.get(i).getPsaCode())
		    		 .setParameter("sys",workLoad.get(i).getSystemType())
		    		 .setParameter("sub",workLoad.get(i).getSubSystemType())
		    		 .setParameter("rec",workLoad.get(i).getRecType())
		    		 .getResultList();
		    
			if(ls!=null && !ls.isEmpty()&& eff>0){
				ls.get(0).setEffort(eff);
				entityManager.flush();
			}
			else if(ls!=null && !ls.isEmpty() && eff<=0){
				String wUnit = ls.get(0).getWrkId().getWorkUnit();
				String id = ls.get(0).getWrkId().getUserId();
				Date ed = ls.get(0).getWrkId().getEffortDate();
				String psa = ls.get(0).getWrkId().getPsaCode();
				String sys = ls.get(0).getWrkId().getSystemType();
			    String sub = ls.get(0).getWrkId().getSubSystemType();
			    String rec = ls.get(0).getWrkId().getRecType();
			    String type = ls.get(0).getWrkId().getEffortType();
				deleteWorkLoad(id,ed,wUnit,type, psa,sys,sub,rec);
			}
			else if (eff > 0){
				WorkLoadDetail dl = new WorkLoadDetail();
				WorkLoadDetailId wi = new WorkLoadDetailId();
				wi.setUserId(userId);
				wi.setWorkUnit(workLoad.get(i).getWorkUnit());
				wi.setEffortDate(eDate);
				wi.setPsaCode(workLoad.get(i).getPsaCode());
				wi.setSystemType(workLoad.get(i).getSystemType());
				wi.setSubSystemType(workLoad.get(i).getSubSystemType());
				wi.setRecType(workLoad.get(i).getRecType());
				wi.setEffortType(workLoad.get(i).getEffortType());
				dl.setWrkId(wi);
				dl.setEffort(eff);
				dl.setRecCategory(workLoad.get(i).getRecCategory());
				
				dl.setWorkUnitDesc(workLoad.get(i).getWorkUnitDesc());
				
				entityManager.persist(dl);
			}
		}
		 	
        }
	 }
	
	 public void deleteWorkLoad(String userId,Date effortDate, String unit ,String type, String psa , String sys , String sub , String rec) {
			entityManager.remove(getUniqueEffort(userId,effortDate,unit,type,psa,sys,sub,rec));
		}
	 
	
		@SuppressWarnings("unchecked")
		public WorkLoadDetail getEffortByUnit(String userId,Date effortDate, String unit) {
			  String hql="FROM WorkLoadDetail as wrk WHERE wrk.wrkId.userId=:user AND wrk.wrkId.effortDate=:eDate AND wrk.wrkId.workUnit=:unit";   
				 List <WorkLoadDetail> wld=entityManager.createQuery(hql).setParameter("user",userId).setParameter("eDate",effortDate).setParameter("unit",unit).getResultList();
				  if(wld!=null && !wld.isEmpty())
					  return wld.get(0);
				  else
			       return null;
		}
		
*/
		
		
//view previous timesheets grouped by period end date
	
//Email Service
	
	/*public void EmailRemainder() {
		for(int i=0;i<)
		String emailId = 
		   emailService.SendTimesheetRemainder(emailId);
	}*/

	
}
