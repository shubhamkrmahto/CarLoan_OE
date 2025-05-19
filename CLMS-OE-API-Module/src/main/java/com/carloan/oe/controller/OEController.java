 package com.carloan.oe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.carloan.oe.entity.Cibil;
import com.carloan.oe.entity.Enquiry;

@CrossOrigin
@RestController
@RequestMapping("OE")
public class OEController {
	
	@Autowired
	RestTemplate rt;
	
	private static final Logger log = LoggerFactory.getLogger(OEController.class);
	
	@GetMapping("/getenquiry/{id}")
	public ResponseEntity<Enquiry> getEnquiry(@PathVariable Integer id){
		String url ="http://localhost:7000/enquiry/getSingleEnquiry/"+ id;
		Enquiry enquiry = rt.getForObject(url, Enquiry.class);
		log.info("OE get Single Enquiry has been Successfully by id :- "+enquiry.getEnquiryId());
		return new ResponseEntity<Enquiry>(enquiry, HttpStatus.OK);		
	}
	
	
	@GetMapping("/getallenquiries")
	public ResponseEntity<List<Enquiry>> getEnquiry(){
		String url = "http://localhost:7000/enquiry/enquirysenttooe";
		List<Enquiry> enquiry = rt.getForObject(url, List.class);
		log.info("OE get All Enquiries for Check Cibil");
		return new ResponseEntity<List<Enquiry>>(enquiry, HttpStatus.OK);		
	}
	

	@GetMapping("/updateCibil/{id}")
	public ResponseEntity<String> UpdateCibil(@PathVariable("id") Integer id){
		
		String url = "http://localhost:7002/enquiry/getSingleEnquiry/"+id;
		Enquiry enq = rt.getForObject(url, Enquiry.class);
		
		String url1 = "http://localhost:9001/cibil/generateCibil";
		Integer genCibil = rt.getForObject(url1, Integer.class);
		
		String url2 = "http://localhost:7002/enquiry/updateStatus/"+enq.getEnquiryId()+"/"+genCibil;
		String msg = rt.getForObject(url2, String.class);
		
		log.info("Genrated Cibil is : "+genCibil +" for this id :- "+enq.getEnquiryId() +" from OE");
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);		
	}
	
	@GetMapping("/updateLoanApplicationDocumentsToVerified/{id}")
	public ResponseEntity<String> updateLoanApplicationDocumentsToVerified(@PathVariable("id") Integer id ){
		
		String url = "http://localhost:7002/loanApplication/updateStatusToDocumentVerified/"+id;
		String msg =rt.getForObject(url, String.class);
		log.info("LoanApplication Document Varified from OE  id is :- "+id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);		
	}
	
	@GetMapping("/updateLoanApplicationDocumentsToRejected/{id}")
	public ResponseEntity<String> updateLoanApplicationDocumentsToRejected(@PathVariable("id") Integer id ){
		
		String url = "http://localhost:7002/loanApplication/updateStatusToDocumentRejected/"+id;
		String msg =rt.getForObject(url, String.class);
		log.info("LoanApplication Document Rejected from OE  id is :- "+id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);		
	}

}
