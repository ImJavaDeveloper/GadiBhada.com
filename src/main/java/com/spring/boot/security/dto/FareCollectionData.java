package com.spring.boot.security.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.FareCollection;
import com.spring.boot.security.repository.FareCollectionRepository;


@Service
public class FareCollectionData {

	@Autowired
	FareCollectionRepository fareCollectionRepository;
	
	public FareCollection saveFareCollectionData(FareCollection fareCollection)	{
		return fareCollectionRepository.save(fareCollection);
	}
}
