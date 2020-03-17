package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.security.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	
	@Query("From UserDetails where user_name=?1")
	public UserDetails findByUserName(String user_name);

}
