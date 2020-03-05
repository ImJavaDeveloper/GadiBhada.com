package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.repository.AgentDestinationRepository;

@Service
public class AgentDestinationData {
	
	@Autowired
	AgentDestinationRepository agentDestinationRepository;
	
	public List<AgentDestination> getAllAgentDestination()
	{
		return agentDestinationRepository.findAll();
	}
	
	public AgentDestination saveAgentDestination(AgentDestination agentDes)
	{
		return agentDestinationRepository.save(agentDes);	
	}
	public String getNameByAgentDestination(String agentDest)
	{
		String agentDestination=null;
		if(agentDestinationRepository.findByAgentDestName(agentDest)!=null)
           agentDestination=agentDestinationRepository.findByAgentDestName(agentDest).getAgentDestinationName();
		
		return agentDestination;
	}

}
