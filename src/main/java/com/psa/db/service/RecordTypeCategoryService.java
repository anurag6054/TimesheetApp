package com.psa.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.db.dao.RecordTypeCategoryDao;
import com.psa.db.entity.RecordTypeCategory;



@Service
public class RecordTypeCategoryService {

	@Autowired
	private RecordTypeCategoryDao recDao;
	
	public List<RecordTypeCategory> getRec(String type) {
		 return recDao.getRec(type);
	}
}
