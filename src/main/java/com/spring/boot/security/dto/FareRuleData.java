package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.FareRule;
import com.spring.boot.security.repository.FareRuleRepository;

@Service
public class FareRuleData {
	
	@Autowired
	FareRuleRepository fareRuleRepository;
	
	public List<FareRule> getAllFareRule()
	{
		
		return fareRuleRepository.findAll();
	}
	
	public FareRule findDuplicateFareRule(int sourceId,int agentDestinationId,int boxId,int itemId)
	{
		return  fareRuleRepository.findDuplicateFareRule( sourceId, agentDestinationId, boxId, itemId);
	}
	
	public FareRule saveBoxDetails(FareRule fareRule)
	{
		return  fareRuleRepository.save(fareRule);
	}

}
