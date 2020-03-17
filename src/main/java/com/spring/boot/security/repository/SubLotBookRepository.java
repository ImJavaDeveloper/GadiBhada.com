package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.SubLotBook;

@Repository
public interface SubLotBookRepository extends JpaRepository<SubLotBook, Integer> {
	@Query(value="select sum(total_qty) as tot_qty from sub_lot_book where lot_id=?1",nativeQuery = true)
	public Integer getTotalQtyForLotId(int lot_id);

}
