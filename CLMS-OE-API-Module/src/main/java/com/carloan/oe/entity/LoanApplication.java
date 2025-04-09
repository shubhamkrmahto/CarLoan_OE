package com.carloan.oe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanApplication {
	
	 private Integer applicationId;
	
	 private String loanStatus;
	 
	 private PersonalDocuments documents;
	 
	 
}