 package com.carloan.oe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OEController {
	
	@Autowired
	RestTemplate rt;
	
	
	@GetMapping("/getenquiry/{id}")
	public ResponseEntity<Enquiry> getEnquiry(@PathVariable Integer id){
		String url ="http://localhost:7000/enquiry/getSingleEnquiry/"+ id;
		Enquiry enquiry = rt.getForObject(url, Enquiry.class);
		
		return new ResponseEntity<Enquiry>(enquiry, HttpStatus.OK);		
	}
	
	@GetMapping("/getallenquiries")
	public ResponseEntity<List<Enquiry>> getEnquiry(){
		String url = "http://localhost:7000/enquiry/enquirysenttooe";
		List<Enquiry> enquiry = rt.getForObject(url, List.class);
		
		return new ResponseEntity<List<Enquiry>>(enquiry, HttpStatus.OK);		
	}


}
