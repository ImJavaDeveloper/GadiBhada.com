package com.spring.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spring.boot.security.utils.AppAuthenticationHandler;
import com.spring.boot.security.utils.UserLogOutHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true )
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AuthenticationSuccessHandler appAuthenticationHandler;
	@Autowired
	UserLogOutHandler logoutHandler;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		/*http.authorizeRequests()
		.antMatchers("/admin").hasAnyRole("ADMIN","USER")
		.antMatchers("/user").hasAnyRole("USER")
		.antMatchers("/").permitAll()
		.and()
		.formLogin();*/
		
		http
		.csrf().disable() //csrf->cross site request forgery
		.authorizeRequests()
		//.antMatchers("/login").permitAll()	 
		.antMatchers("/webjars/bootstrap/**").permitAll()
		.antMatchers("/webjars/jquery/**").permitAll()
		//.antMatchers("/resources/static/css/**").permitAll()
		//.antMatchers("/resourcses/**").permitAll()
		.anyRequest().authenticated()
		
		.and()
		.formLogin().loginPage("/login").permitAll().successHandler(appAuthenticationHandler)
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll().addLogoutHandler(logoutHandler);
		;
		
	}

	
	
	

}
