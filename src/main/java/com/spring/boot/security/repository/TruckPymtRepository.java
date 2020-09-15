package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.TruckPymt;

@Repository
public interface TruckPymtRepository extends JpaRepository<TruckPymt, Integer> {

}
