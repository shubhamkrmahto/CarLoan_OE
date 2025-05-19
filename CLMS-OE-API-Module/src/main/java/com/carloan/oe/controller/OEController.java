package com.carloan.oe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.carloan.oe.entity.Enquiry;

@CrossOrigin("*")
@RestController
@RequestMapping("/oe")
public class OEController {

	@Autowired
	RestTemplate rt;

	@GetMapping("/getenquiry/{id}")
	public ResponseEntity<Enquiry> getEnquiry(@PathVariable Integer id){
		String url ="http://localhost:9090/crm/enquiry/getSingleEnquiry/"+ id;
		Enquiry enquiry = rt.getForObject(url, Enquiry.class);
		
		return new ResponseEntity<Enquiry>(enquiry, HttpStatus.OK);		

	@GetMapping("/getallenquiries")
	public ResponseEntity<List<Enquiry>> getEnquiry(){
		String url = "http://localhost:9090/crm/enquiry/enquirysenttooe";
		List<Enquiry> enquiry = rt.getForObject(url, List.class);

		return new ResponseEntity<List<Enquiry>>(enquiry, HttpStatus.OK);
	}

	@GetMapping("/updateCibil/{id}")
	public ResponseEntity<String> UpdateCibil(@PathVariable("id") Integer id){
		
		String url = "http://localhost:9090/crm/enquiry/getSingleEnquiry/"+id;
		Enquiry enq = rt.getForObject(url, Enquiry.class);
		String url1 = "http://localhost:9090/cibil/cibil/generateCibil";
		Integer genCibil = rt.getForObject(url1, Integer.class);
		String url2 = "http://localhost:9090/crm/enquiry/updateStatus/"+enq.getEnquiryId()+"/"+genCibil;
		String msg = rt.getForObject(url2, String.class);

		System.out.println(genCibil);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/updateLoanApplicationDocumentsToVerified/{id}")
	public ResponseEntity<String> updateLoanApplicationDocumentsToVerified(@PathVariable("id") Integer id ){
		
		String url = "http://localhost:9090/crm/loanApplication/updateStatusToDocumentVerified/"+id;
		String msg =rt.getForObject(url, String.class);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/updateLoanApplicationDocumentsToRejected/{id}")
	public ResponseEntity<String> updateLoanApplicationDocumentsToRejected(@PathVariable("id") Integer id ){
		
		String url = "http://localhost:9090/crm/loanApplication/updateStatusToDocumentRejected/"+id;
		String msg =rt.getForObject(url, String.class);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
