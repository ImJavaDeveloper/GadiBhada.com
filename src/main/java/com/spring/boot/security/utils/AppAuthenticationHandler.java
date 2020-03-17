package com.spring.boot.security.utils;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.boot.security.entity.UserDetails;
import com.spring.boot.security.repository.UserDetailsRepository;

@Component
public class AppAuthenticationHandler implements AuthenticationSuccessHandler {

	@Autowired
	DataSource datasource;
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//do some logic here if you want something to be done whenever
        //the user successfully logs in.
		
		System.out.println(request.getRequestedSessionId());
		HttpSession session = request.getSession();
		 if (session != null ) {
		System.out.println("Session ID:"+session.getId());
		System.out.println("Session Creation Time:"+session.getCreationTime());
		System.out.println("Session Max Inactive Time:"+session.getMaxInactiveInterval());
		System.out.println("Session Servlet Context:"+session.getServletContext());
		String username = SecurityUtils.getUserName();
		UserDetails userDetails=userDetailsRepository.findByUserName(username);
		session.setAttribute("firstName",userDetails.getFirstName());
        session.setAttribute("username", userDetails.getUserName());
        session.setAttribute("firstName", userDetails.getFirstName());
        session.setAttribute("sessionId", session.getId());
        SecurityUtils.logUserEvent(username,session.getId(),datasource);
		 }
        //set our response to OK status
		 
		 response.setStatus(HttpServletResponse.SC_OK);
        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
		 
		 Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	     System.out.println("Role:"+roles.toString());   
		 if (roles.contains("ROLE_ADMIN")) {
	           
			 //redirectStrategy.sendRedirect(request, response, "/adminHome");
			  response.sendRedirect("/gadibhada/home/admin");
	           // response.setStatus(HttpServletResponse.SC_OK);
	        }
		 else if (roles.contains("ROLE_USER")) {
	            
	        	 //redirectStrategy.sendRedirect(request, response, "/userHome");
	        	response.sendRedirect("/gadibhada/home/user");
	            //response.setStatus(HttpServletResponse.SC_OK);
	        }
		
	}


}
