package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.AgentDetails;
import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.repository.AgentDetailsRespository;

@Service
public class AgentDetailsData {
	
	@Autowired
	AgentDetailsRespository agentDetailsRespository;
	
	public List<AgentDetails> getAllTraderAgent()
	{
		
		return agentDetailsRespository.findAll();
	}
	public AgentDetails findByAgentName(String agentName,String agentTradeMark)
	{
		return  agentDetailsRespository.findByAgentName(agentName, agentTradeMark);
	}
	
	public AgentDetails saveAgent(AgentDetails agent)
	{
		return  agentDetailsRespository.save(agent);
	}
}
