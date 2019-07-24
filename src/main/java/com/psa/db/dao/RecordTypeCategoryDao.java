package com.psa.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.psa.db.entity.RecordTypeCategory;

@Transactional
@Repository
public class RecordTypeCategoryDao {

	@PersistenceContext	
	private EntityManager entityManager;
	
	public List<RecordTypeCategory> getRec(String type) {
		  String hql="FROM RecordTypeCategory as rec WHERE rec.recId.recordType=:type ";  	
		  @SuppressWarnings("unchecked")
		List<RecordTypeCategory> ls=(List<RecordTypeCategory>) entityManager.createQuery(hql).setParameter("type",type).getResultList();
			return ls;
	}
}
