package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.DestinationDetails;
import com.spring.boot.security.repository.DestinationDetailsRepository;



@Service
public class DestinationDetailsData {
	
	@Autowired
	DestinationDetailsRepository destinationDetailsRepository;
	
	public List<DestinationDetails> getDestinations()
	{
		return destinationDetailsRepository.findAll();
	}

	public DestinationDetails saveDestinationDetails(DestinationDetails destinationDetails)
	{
		return destinationDetailsRepository.save(destinationDetails);	
	}
	
	public String getNameByDestination(String destination)
	{
		String dest=null;
		DestinationDetails destinationDetails=destinationDetailsRepository.findByDestinationName(destination);
		if(destinationDetails!=null)
			dest=destinationDetails.getDestination();
		
		return dest;
	}
}
