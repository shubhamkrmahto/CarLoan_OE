 package com.carloan.oe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.carloan.oe.entity.Cibil;
import com.carloan.oe.entity.Enquiry;
import com.carloan.oe.entity.LoanApplication;
import com.carloan.oe.entity.PersonalDocuments;

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

	@GetMapping("/UpdateCibil")
	public ResponseEntity<Integer> UpdadteCibil(){
		
		String url = "http://localhost:9001/cibil/generateCibil";
		Integer genCibil = rt.getForObject(url, Integer.class);
		
		System.out.println(genCibil);
		
		return new ResponseEntity<Integer>(genCibil, HttpStatus.OK);		
	}

	@GetMapping("/getloanappliction")
	public ResponseEntity<List<LoanApplication>> getloanApplication()
	{
		String url="http://localhost:7002/loanApplication/getloanappssenttooe";
		List<LoanApplication> applications=rt.getForObject(url,List.class);
		return new ResponseEntity<List<LoanApplication>>(applications,HttpStatus.OK);
	}
	
    @PatchMapping("/statuschange/{id}")
    public ResponseEntity<String> updatesatus(@PathVariable("id")Integer id,
    		                                       @RequestBody PersonalDocuments status)
    {
    	String url="http://localhost:7002/loanApplication/statusUpdate/"+id;
    	
    	Map<String, Object> updates = new HashMap<>();
        updates.put("documentStatus", status.getDocumentStatus());
    	
          String msg=rt.patchForObject(url,updates,String.class);
          
          
    	return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
    		
    @GetMapping("/statusUpdatetoVerified/{id}")
    public ResponseEntity<String> updatesatus(@PathVariable("id")Integer id)
    {
    	String url="http://localhost:7002/loanApplication/statusUpdatetoVerified/"+id;
    	
    	
          String msg=rt.getForObject(url,String.class);
          
          
    	return new ResponseEntity<String>(msg,HttpStatus.OK);
    }	

	
}
