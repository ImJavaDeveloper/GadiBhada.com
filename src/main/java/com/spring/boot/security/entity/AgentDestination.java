package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agent_destination")
public class AgentDestination {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="agent_destination_id")
	private int agentDestinationId;
	@Column(name="agent_destination_name")
	private String agentDestinationName;
	
	
	public int getAgentDestinationId() {
		return agentDestinationId;
	}
	public void setAgentDestinationId(int agentDestinationId) {
		this.agentDestinationId = agentDestinationId;
	}
	public String getAgentDestinationName() {
		return agentDestinationName;
	}
	public void setAgentDestinationName(String agentDestinationName) {
		this.agentDestinationName = agentDestinationName;
	}
	
	
	
}
