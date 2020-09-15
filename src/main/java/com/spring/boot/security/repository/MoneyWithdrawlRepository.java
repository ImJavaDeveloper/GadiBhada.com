package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.security.entity.MoneyWithdrawlEntry;

public interface MoneyWithdrawlRepository extends JpaRepository<MoneyWithdrawlEntry, Integer> {

}
