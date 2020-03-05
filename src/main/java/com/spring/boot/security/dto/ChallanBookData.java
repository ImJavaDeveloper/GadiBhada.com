package com.spring.boot.security.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.repository.ChallanBookRepository;

@Service
public class ChallanBookData {
	
	@Autowired
	ChallanBookRepository challanBookRepository;
	
	public ChallanBook saveChallanData(ChallanBook challanBook)	{
		return challanBookRepository.save(challanBook);
	}

}
