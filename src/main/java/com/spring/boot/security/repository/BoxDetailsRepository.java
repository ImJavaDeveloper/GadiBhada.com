package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.AgentDetails;
import com.spring.boot.security.entity.BoxDetails;
import com.spring.boot.security.entity.ItemDetails;

@Repository
public interface BoxDetailsRepository extends JpaRepository<BoxDetails,Integer> {
	
	@Query("FROM BoxDetails where box_name = ?1 and total_wt = ?2")
	public BoxDetails findByBoxAndWt(String box_name,int total_wt);
	
}

