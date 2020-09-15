package com.spring.boot.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.TruckLedger;
import com.spring.boot.security.entity.id.TruckLedgerId;

@Repository
public interface TruckLedgerRespository extends JpaRepository<TruckLedger, TruckLedgerId> {

	
	@Query("From TruckLedger where truck_no=?1 and  truck_start_dt=?2")
	public List<TruckLedger> findTruckByEndDt(String truckNo, String startDt);
	
}
