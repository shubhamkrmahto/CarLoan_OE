package com.carloan.oe.controller;

import java.time.LocalDate;

import lombok.Data;


@Data
public class Enquiry {
	
	private Integer enquiryId;
	private String customerName;
	private LocalDate dateOfBirth;
	private String gender;
	private String customerEmailId;
	private Long customerContactNumber;
	private Long customerAlternateNumber;
	private Long aadharNo;
	private String panCardNo;
	private String enquiryStatus;
	private Cibil cibil;
	private LocalDate enquiryDateTime;
}
