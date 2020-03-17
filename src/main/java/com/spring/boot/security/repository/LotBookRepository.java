package com.spring.boot.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.boot.security.entity.LotBook;

@Repository
public interface LotBookRepository extends JpaRepository<LotBook, Integer> {
	
	//LotBook findByLotId(int lotId);
	
	@Query("From LotBook where challan_id=?1")
	public List<LotBook> getLotBooksByChallanId(int challan_id);

}
