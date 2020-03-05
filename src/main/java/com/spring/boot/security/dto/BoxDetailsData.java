package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.AgentDetails;
import com.spring.boot.security.entity.BoxDetails;
import com.spring.boot.security.repository.BoxDetailsRepository;

@Service
public class BoxDetailsData {

	@Autowired
	BoxDetailsRepository boxDetailsRepository;
	
	public List<BoxDetails> getAllBoxType()
	{
		
		return boxDetailsRepository.findAll();
	}
	
	public BoxDetails findByBoxAndWt(String boxName,int wt)
	{
		return  boxDetailsRepository.findByBoxAndWt(boxName, wt);
	}
	
	public BoxDetails saveBoxDetails(BoxDetails box)
	{
		return  boxDetailsRepository.save(box);
	}
	
}
