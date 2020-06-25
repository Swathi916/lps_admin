package com.javacloud.lps.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacloud.lps.entity.Loan;


public interface LoanRepository extends JpaRepository<Loan, Integer> {

	
}
