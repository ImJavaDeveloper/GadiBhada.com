package com.spring.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.userdata.UserCredential;


@Repository
public interface UserRepository extends JpaRepository<UserCredential, Integer> {

	UserCredential findByUsername(String username);
}
