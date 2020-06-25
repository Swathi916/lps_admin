package com.javacloud.lps.service;

import java.util.List;

import com.javacloud.lps.entity.Applicant;

public interface ApplicantService {
public Applicant saveApplicant(Applicant applicant);
	
	public Applicant getById(int id);
	
	public List<Applicant> findAll();
	
	public Applicant setApproved(int id);
	
	public Applicant setRejected(int id);
	
	public void deleteById(int theId);
	
	

}
