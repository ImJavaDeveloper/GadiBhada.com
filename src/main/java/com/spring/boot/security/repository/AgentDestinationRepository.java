package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.SourceDetails;

@Repository
public interface AgentDestinationRepository extends JpaRepository<AgentDestination, Integer> {

	@Query("FROM AgentDestination where agent_destination_name = ?1")
	public AgentDestination findByAgentDestName(String agent_destination_name);
}
