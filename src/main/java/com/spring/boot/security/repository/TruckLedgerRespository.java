package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.TruckLedger;
import com.spring.boot.security.entity.id.TruckLedgerId;

@Repository
public interface TruckLedgerRespository extends JpaRepository<TruckLedger, TruckLedgerId> {

	
}
