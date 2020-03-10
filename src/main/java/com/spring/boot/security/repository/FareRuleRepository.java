package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.FareRule;

@Repository
public interface FareRuleRepository extends JpaRepository<FareRule, Integer>  {
	
	
	@Query("FROM FareRule where source_id = ?1 and agent_destination_id=?2 and box_id=?3 and item_id=?4")
	public FareRule findDuplicateFareRule(int source_id,int agent_destination_id,int box_id,int item_id);

}
