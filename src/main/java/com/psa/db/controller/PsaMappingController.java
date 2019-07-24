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
import org.springframework.web.util.UriComponentsBuilder;
import com.psa.db.entity.PsaMapping;
import com.psa.db.service.PsaMappingService;



@Controller
@RequestMapping("psa")
public class PsaMappingController {

	@Autowired
	private PsaMappingService psaservice;
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("/view/{code}")
	public ResponseEntity<PsaMapping> getPsaByCode(@PathVariable("code") String code) {
		PsaMapping psa = psaservice.getPsaByCode(code);
		return new ResponseEntity<PsaMapping>(psa, HttpStatus.OK);
	 
	}

	@CrossOrigin(origins = "http://localhost:8888")
	@PostMapping("/add")
	public ResponseEntity<Void> addPsa(@RequestBody List<PsaMapping> psa, UriComponentsBuilder builder) {
		psaservice.addPsa(psa);
	    HttpHeaders headers = new HttpHeaders();
	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/*@CrossOrigin
	@PutMapping("/update")
	public ResponseEntity<PsaMapping> updateUser(@RequestBody PsaMapping psa) {
		psaservice.updatePsa(psa);
		return new ResponseEntity<PsaMapping>(psa, HttpStatus.NO_CONTENT);
	}*/
	
	@CrossOrigin(origins = "http://localhost:8888")
	@DeleteMapping("delete/{code}")
	public ResponseEntity<Void> deleteUser(@PathVariable("code") String code) {
		psaservice.deletePsa(code);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "http://localhost:8888")
	@GetMapping("view/code/{sys}/{sub}/{rec}")
	public ResponseEntity<List<PsaMapping>> getPsaCode(@PathVariable("sys") String system, @PathVariable("sub")  String subSystem, @PathVariable("rec") String rec) {
		List<PsaMapping> psa = psaservice.getPsaCode(system,subSystem,rec);
		return new ResponseEntity<List<PsaMapping>>(psa, HttpStatus.OK);
	 
	}
}
