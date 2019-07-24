package com.psa.db.controller;

import java.text.ParseException;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.psa.db.WorkLoad;
import com.psa.db.WorkLoadView;
import com.psa.db.entity.WorkLoadDetail;
import com.psa.db.service.WorkLoadDetailService;

@Controller
@RequestMapping("workload")

public class WorkLoadDetailController {
@Autowired
private WorkLoadDetailService wrkservice;

@CrossOrigin(origins = "http://localhost:8888")
@GetMapping("view/{userId}")
public ResponseEntity<List<WorkLoadDetail>> getAllWorkLoadById(@PathVariable("userId") String userId) {
	List<WorkLoadDetail> wrkLoad = wrkservice.getAllWorkLoadById(userId);
	return new ResponseEntity<List<WorkLoadDetail>>(wrkLoad, HttpStatus.OK);
 
}

@CrossOrigin(origins = "http://localhost:8888")
@PostMapping("/add/{id}/{endDate}")
public ResponseEntity<Void> addWorkLoad(@PathVariable("id") String userId,@PathVariable("endDate") String endDate,@RequestBody List<WorkLoad> wrkLoad, UriComponentsBuilder builder) throws ParseException {
    wrkservice.addWorkLoad(userId,endDate,wrkLoad);
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
}

@CrossOrigin(origins = "http://localhost:8888")
@SuppressWarnings("unused")
@GetMapping("/view/{id}/{date}")
public ResponseEntity<Boolean> viewWorkLoad(@PathVariable ("id")String userId, @PathVariable ("date")String endDate) throws ParseException {
	boolean wrk = wrkservice.WorkLoadExists(userId, endDate);
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<Boolean>(wrk, HttpStatus.OK);
}

@CrossOrigin(origins = "http://localhost:8888")
@SuppressWarnings("unused")
@GetMapping("/view/week/{id}/{date}")
public ResponseEntity<List<WorkLoadView>> getWorkLoadDetailForWeek(@PathVariable ("id")String userId, @PathVariable ("date")String endDate) throws ParseException {
	List<WorkLoadView> wrk = wrkservice.getWorkLoadDetailForWeek(userId, endDate);
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<List<WorkLoadView>>(wrk, HttpStatus.OK);
}

/*@CrossOrigin
@PutMapping("/update/{id}/{eDate}")
public ResponseEntity<List<WorkLoad>>updateUser(@PathVariable("id") String userId,@RequestBody List<WorkLoad> wrkld,@PathVariable String eDate) throws ParseException {
	wrkservice.updateWorkLoad(userId,wrkld,eDate);
	return new ResponseEntity<List<WorkLoad>>(wrkld, HttpStatus.NO_CONTENT);
}*/


/*@DeleteMapping("delete/{userId}")
public ResponseEntity<Void> deleteWorkUnit(@PathVariable("userId") String userId) {
	wrkservice.deleteWorkLoad(userId);
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}*/
}
