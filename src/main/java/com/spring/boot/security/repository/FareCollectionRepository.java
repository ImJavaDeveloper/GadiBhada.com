package com.spring.boot.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.FareCollection;
import com.spring.boot.security.entity.LotBook;

@Repository
public interface FareCollectionRepository extends JpaRepository<FareCollection, Integer> {
	
	@Query("From FareCollection where sub_lot_id=?1")
	public List<FareCollection> getFareCollectionBySubLotId(int sub_lot_id);

}
