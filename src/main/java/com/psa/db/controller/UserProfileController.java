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
import com.psa.db.entity.UserProfile;
import com.psa.db.service.UserProfileService;


@Controller
@RequestMapping("user")
public class UserProfileController {
	
	@Autowired
	private UserProfileService usrservice;
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("view/{userId}")
	
	public ResponseEntity<UserProfile> getUserDetail(@PathVariable("userId") String userId) {
		UserProfile usr = usrservice.getUserDetail(userId);
		return new ResponseEntity<UserProfile>(usr, HttpStatus.OK);
	 
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@PostMapping("/add")
	public ResponseEntity<Void> addUser(@RequestBody List<UserProfile> user, UriComponentsBuilder builder) {
		usrservice.addUser(user);
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@PutMapping("/update")
	public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile user) {
		usrservice.updateUser(user);
		return new ResponseEntity<UserProfile>(user, HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
		usrservice.deleteUser(userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/authenticate/{id}/{pswd}")
	public ResponseEntity<String> authentication(@PathVariable ("id")String userId, @PathVariable ("pswd")String pswd) throws ParseException {
		String usr = usrservice.authentication(userId, pswd);
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<String>(usr, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/remainder/thursday")
	public ResponseEntity<String> emailRemainder() {
		String usr = usrservice.emailRemainder();
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<String>(usr, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/remainder/friday/{end}")
	public ResponseEntity<String> emailRemainderFriday( @PathVariable ("end")String endDate) throws ParseException {
		 usrservice.emailRemainderFriday(endDate);
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
}
