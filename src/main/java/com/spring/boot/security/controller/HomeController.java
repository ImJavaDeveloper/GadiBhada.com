package com.spring.boot.security.controller;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.boot.security.utils.SecurityUtils;

@Controller
@RequestMapping(value="/gadibhada/home")
@SessionAttributes("firstName")
public class HomeController {
	
	@Autowired
	DataSource datasource;
	
	@RequestMapping(value="/")
	public String defaultHome(Model model)
	{
		
		
		String username=SecurityUtils.getUser();
		List<String> roles=SecurityUtils.getRoles(username,datasource);

		if(roles.contains("ADMIN")) {

			return "redirect:admin";
		}
		if(roles.contains("USER"))
		{
		    return "redirect:user";
		}
		
		return "redirect:home";
		
		
	}
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(Model model) throws SQLException
	{
		
		
		return "home";
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String user(Model model) throws SQLException
	{
		
		return "userHome";
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/admin",method=RequestMethod.GET)	
	public String admin(Model model) throws SQLException
	{
		
		return "adminHome";
		
	}
	
	@RequestMapping(value="/login")
	public String loginPage(Model model)
	{
		
		String username=SecurityUtils.getUser();
		if(!username.contains("anonymousUser"))
		return "redirect:";
		else
		return "login";	
		
	}
	
	@RequestMapping(value="/logout-success")
	public String logoutPage()
	{
		
		return "logout";
		
	}
	
	@RequestMapping(value="/showerror")
	public String errorPage()
	{
		
		return "errorPage";
		
	}
	
	@RequestMapping(value="/test")
	public String testPage(Model model)
	{
		
		String username=SecurityUtils.getUser();
        
		model.addAttribute("username", username);

		return "test";
		
		
	}
	
	
}
