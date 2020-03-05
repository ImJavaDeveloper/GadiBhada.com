package com.spring.boot.security.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.SubLotBook;
import com.spring.boot.security.repository.SubLotBookRepository;

@Service
public class SubLotBookData {
	
	@Autowired
	SubLotBookRepository subLotBookRepository;
	
	public SubLotBook saveSubLotBookData(SubLotBook subLotBook)
	{
		return subLotBookRepository.save(subLotBook);
	}
	

}
