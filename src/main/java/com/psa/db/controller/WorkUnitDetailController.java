package com.psa.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.psa.db.entity.WorkUnitDetail;
import com.psa.db.service.WorkUnitDetailService;

@Controller
@RequestMapping("unit")
public class WorkUnitDetailController {
	
	@Autowired
	private WorkUnitDetailService wrkUnitservice;
	
	/*@GetMapping("view/{userId}")
	public ResponseEntity<List<WorkUnitDetail>> getAllWorkUnitById(@PathVariable("userId") String userId) {
		List<WorkUnitDetail> unit = wrkUnitservice.getAllWorkUnitById(userId);
		return new ResponseEntity<List<WorkUnitDetail>>(unit, HttpStatus.OK);
	 
	}*/

	@CrossOrigin(origins = "http://localhost:8888")
	@PostMapping("/add")
	public ResponseEntity<Void> addWorkUnit(@RequestBody List<WorkUnitDetail> unit, UriComponentsBuilder builder) {
		wrkUnitservice.addWorkUnit(unit);
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/view/{id}/{workUnitdesc}")
	public ResponseEntity<List<WorkUnitDetail>> getWorkUnitDetailForSystem(@PathVariable("workUnitdesc") String workUnit,@PathVariable("id") String id) {
		List<WorkUnitDetail> unit = wrkUnitservice.getWorkUnitDetailForSystem(workUnit,id);
		return new ResponseEntity<List<WorkUnitDetail>>(unit, HttpStatus.OK);
	 
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/view/workunit/{workUnit}")
	public ResponseEntity<List<WorkUnitDetail>> getWorkUnitDetailByUnit(@PathVariable("workUnit") String workUnit) {
		List<WorkUnitDetail> unit = wrkUnitservice.getWorkUnitDetailByUnit(workUnit);
		return new ResponseEntity<List<WorkUnitDetail>>(unit, HttpStatus.OK);
	 
	}
    
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("all")
	public ResponseEntity<List<WorkUnitDetail>> getAllWorkUnitDetail() {
		List<WorkUnitDetail> unit = wrkUnitservice.getAllWorkUnitDetail();
		return new ResponseEntity<List<WorkUnitDetail>>(unit, HttpStatus.OK);
	 
	}
	/*@CrossOrigin
	@PutMapping("/update")
	public ResponseEntity<WorkUnitDetail> updateUser(@RequestBody WorkUnitDetail wrkUnit) {
		wrkUnitservice.updateWorkUpdate(wrkUnit);
		return new ResponseEntity<WorkUnitDetail>(wrkUnit, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<Void> deleteWorkUnit(@PathVariable("userId") String userId) {
		wrkUnitservice.deleteWorkUnit(userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

*/
}
