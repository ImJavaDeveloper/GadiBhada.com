package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.DestinationDetails;

@Repository
public interface DestinationDetailsRepository extends JpaRepository<DestinationDetails, Integer>  {

	
	@Query("FROM DestinationDetails where destination = ?1")
	public DestinationDetails findByDestinationName(String destination);
}
