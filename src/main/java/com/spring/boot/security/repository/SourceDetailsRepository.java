package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.entity.SourceDetails;

@Repository
public interface SourceDetailsRepository extends JpaRepository<SourceDetails, Integer> {

	@Query("FROM SourceDetails where source_name = ?1")
	public SourceDetails findBySourceName(String source_name);
}
