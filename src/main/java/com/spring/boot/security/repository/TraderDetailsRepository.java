package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.userdata.UserVO;

@Repository
public interface TraderDetailsRepository extends JpaRepository<TraderDetails, Integer> {
	
	@Query("FROM TraderDetails where trader_name = ?1 and trader_mark = ?2")
	public TraderDetails findByTraderName(String trader_name,String trader_mark);
	
	
}
