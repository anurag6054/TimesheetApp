package com.psa.db.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.psa.db.dao.UserProfileDao;
import com.psa.db.dao.WorkLoadDetailDao;
import com.psa.db.entity.UserProfile;
import com.psa.db.entity.WorkLoadDetail;
import com.psa.db.service.EmailService;

public class UserWorkLoadMapping {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private WorkLoadDetailDao wDao;
	
	@Autowired
	private UserProfileDao usrDao;
	
	
	
}
