package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.security.entity.OtherExpenseEntry;

public interface OtherExpenseRespository extends JpaRepository<OtherExpenseEntry, Integer> {

}
