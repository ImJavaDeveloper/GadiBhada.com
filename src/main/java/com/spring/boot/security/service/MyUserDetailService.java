package com.spring.boot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.security.repository.UserRepository;
import com.spring.boot.security.userdata.MyUserDetails;
import com.spring.boot.security.userdata.UserCredential;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//UserVO user=new UserVO();
		//Hard coded Value
		//user.setUsername("rahat");
		//user.setPassword("$2a$04$TkM6AJ1.78c1bu4dgEGhSuja1s5vlOW/hkVKiPXWojaH4YJPMQCA6");
		
		UserCredential user=userRepository.findByUsername(username);
		if(user==null)
	    	throw new UsernameNotFoundException("User Not Found:404");
		return new MyUserDetails(user);
	}

}
