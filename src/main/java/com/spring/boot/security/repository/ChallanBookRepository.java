package com.spring.boot.security.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.ChallanBook;

@Repository
public interface ChallanBookRepository extends JpaRepository<ChallanBook, Integer> {
	
	@Query("FROM ChallanBook where challan_date = ?1 and truck_no=?2")
	public List<ChallanBook> findExistingChallanBook(Date challan_date,String truck_no);

}
