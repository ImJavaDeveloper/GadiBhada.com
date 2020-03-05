package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.entity.AgentDetails;

@Repository
public interface AgentDetailsRespository extends JpaRepository<AgentDetails, Integer> {

	@Query("FROM AgentDetails where agent_name = ?1 and agent_mark = ?2")
	public AgentDetails findByAgentName(String agent_name,String agent_mark);
}
