package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.userdata.UserVO;

@Repository
public interface LotBookRepository extends JpaRepository<LotBook, Integer> {
	
	LotBook findByLotId(int lotId);

}
