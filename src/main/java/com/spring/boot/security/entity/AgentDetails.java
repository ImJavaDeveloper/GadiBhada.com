package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agent_details")
public class AgentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="agent_id")
	private int agentId;
	@Column(name="agent_name")
	private String agentName;
	@Column(name="agent_mark")
	private String agentMark;
	@Column(name="agent_address")
	private String agentAddress;
	@Column(name="agent_mob")
	private String agentMobile;
	
	
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentMark() {
		return agentMark;
	}
	public void setAgentMark(String agentMark) {
		this.agentMark = agentMark;
	}
	public String getAgentAddress() {
		return agentAddress;
	}
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}
	public String getAgentMobile() {
		return agentMobile;
	}
	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	

}
