package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.security.entity.FareBook;

public interface FareBookRespository extends JpaRepository<FareBook, Integer> {

}
