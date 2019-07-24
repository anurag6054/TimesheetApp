package com.psa.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.psa.db.entity.RecordTypeCategory;
import com.psa.db.service.RecordTypeCategoryService;

@Controller
@RequestMapping("rec")
public class RecordTypeCategoryController {
	
	@Autowired
	private RecordTypeCategoryService recService;
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/view/{rec}")
	public ResponseEntity<List<RecordTypeCategory>> getRec(@PathVariable("rec") String type) {
		List<RecordTypeCategory> rec = recService.getRec(type);
		return new ResponseEntity<List<RecordTypeCategory>>(rec, HttpStatus.OK); 
	}
 
}
