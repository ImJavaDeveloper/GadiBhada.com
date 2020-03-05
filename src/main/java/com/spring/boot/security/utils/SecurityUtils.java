package com.spring.boot.security.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.boot.security.constant.TableConstant;

public class SecurityUtils {
	

	public static String getUserName()
	{
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		return username;
	}
	
	public static String getUser()
	{
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	public static List <String> getRoles(String username,DataSource dataSource)
	{
		List<String> list=new ArrayList<String>();
		String sql="select role from "+ TableConstant.USER_ROLE_TABLE+" where username=?";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		list=jdbcTemplate.queryForList(sql, new Object[] {username}, String.class);
		return list;
		
	}
	
	public static String getUserLastLogin(String username,DataSource dataSource)
	{
		String sql="select max(lastLogin) as lastLogin from "+ TableConstant.USER_EVENT_TABLE+" where username=?";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		String lastLogin=jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);		
		return lastLogin;
		
	}
	
	public static void logUserEvent(String username,String sessionId,DataSource dataSource)
	{
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate=sdf.format(dt);
		String sql="insert into "+ TableConstant.USER_EVENT_TABLE+" (lastLogin,username,session_id) values('"+currentDate+"','"+username+"','"+sessionId+"')";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		jdbcTemplate.execute(sql);		
		
		
	}
	public static void logUserLogout(String username,DataSource dataSource)
	{
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastLogin=getUserLastLogin(username,dataSource);
		String currentDate=sdf.format(dt);
		String sql="update "+ TableConstant.USER_EVENT_TABLE+" set lastLogout='"+currentDate+"' where username='"+username+"' and lastLogin='"+getUserLastLogin(username,dataSource)+"'";
		System.out.println("lastLogin:"+lastLogin+" And Sql:"+sql);
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		jdbcTemplate.execute(sql);		
		
		
	}

}
