package com.spring.boot.security.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class UserLogOutHandler implements LogoutHandler {

	@Autowired
	DataSource datasource;
	
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		System.out.println("Do some thing before logout....");
		System.out.println(SecurityUtils.getUser());
		
		try {
			System.out.println("Logging Out....");
			HttpSession session = request.getSession(false);
			System.out.println("Session is "+session.getId());
			String username=SecurityUtils.getUser();
			SecurityUtils.logUserLogout(username, datasource);
			request.logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//authentication.clearAuthentication(true)
	}

}
