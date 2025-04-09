package com.carloan.oe.entity;

import com.carloan.oe.enums.PersonalDocumentStatusEnum;

import lombok.Data;

@Data
public class PersonalDocuments {
	
	private Integer documentId;
	
	private byte[] addressProof;
	
	private byte[] panCard;
	
	private byte[] incomeTax;
	
	private byte[] aadharCard;
	
	private byte[] photo;
	
	private byte[] signature;
	
	private byte[] bankCheque;
	
	private PersonalDocumentStatusEnum documentStatus;

}