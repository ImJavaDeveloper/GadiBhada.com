package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.LocalFare;

@Repository
public interface LocalFareRepository extends JpaRepository<LocalFare, Integer> {
	
	@Query("FROM LocalFare where ledger_dt = ?1 and local_driver=?2")
	public LocalFare findByLocalDriverByDt(String ledger_dt,String local_driver);

}
