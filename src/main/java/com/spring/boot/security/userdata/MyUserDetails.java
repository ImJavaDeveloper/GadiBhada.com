	package com.spring.boot.security.userdata;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.hibernate.mapping.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class MyUserDetails extends UserCredential implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8377828918964975774L;
	private UserCredential user;
	
	public MyUserDetails(UserCredential user) {
		super(user);
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		return getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
		         .collect(Collectors.toList());			
		
		//return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	    
	/*	//return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_ADMIN"));
		// configure below for user
		if(user.getRole().equals("ADMIN"))
		grantedAuthority=Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		if(user.getRole().equals("USER"))
			grantedAuthority=Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		return  grantedAuthority;*/
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
