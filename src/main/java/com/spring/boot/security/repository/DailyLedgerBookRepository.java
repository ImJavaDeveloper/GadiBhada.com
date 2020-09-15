package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.DailyLedgerBook;

@Repository
public interface DailyLedgerBookRepository extends JpaRepository<DailyLedgerBook, Integer> {
	
	@Query("FROM DailyLedgerBook where ledger_dt = ?1")
	public DailyLedgerBook findByledgerDt(String ledger_dt);
	
	@Modifying
	@Query(value="update daily_ledger_book set ledger_dt = ?1, tot_truck_pymt = ?2 , tot_bnk_trnsfr = ?3 , "
			+ "tot_local_fare = ?4 , tot_mny_withdrwl = ?5 , tot_othr_expns = ?6 , tot_thekedari = ?7, tot_collections = ?8"
			+ ", tot_ledger_bal = ?9 where ledger_id = ?10",nativeQuery = true)
	public Integer updateLedgerBook(String ledgerDt,double truckPymtTot,double bnkTransferTot,double localFareTot
			,double moneyWithDrawlTot,double otherExpenseTot,double thekedariAmt,double todayCollAmt,double todayBal, Integer ledgerId);

}
