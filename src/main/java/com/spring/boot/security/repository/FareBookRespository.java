package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.security.entity.FairBook;

public interface FareBookRespository extends JpaRepository<FairBook, Integer> {

}
