package com.spring.boot.security.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.repository.LotBookRepository;

@Service
public class LotBookData {

	@Autowired
	LotBookRepository lotBookRepository;
	
	public LotBook saveLotBook(LotBook lotBook)
	{
		return lotBookRepository.save(lotBook);
	}
	
}
