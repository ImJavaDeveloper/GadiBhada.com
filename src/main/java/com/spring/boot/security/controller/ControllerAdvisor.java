package com.spring.boot.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.boot.security.exception.AppHttpException;
import com.spring.boot.security.exception.DataBaseException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER=LoggerFactory.getLogger(ControllerAdvisor.class);
	
	@ExceptionHandler(value= {DataBaseException.class})
	public ModelAndView handleDataBaseException(DataBaseException ex)
	{
		
		LOGGER.error(ex.getMessage(), ex);
		ModelAndView model=new ModelAndView();
		model.addObject("message", "Failed");
		model.addObject("exception", ex.getMessage());
		model.setViewName(ex.getPage());
		return model;
		
		
	}
	
	@ExceptionHandler(value= {AppHttpException.class})
	public ModelAndView handleAppHttpException(AppHttpException ex)
	{
		
		LOGGER.error(ex.getMessage(), ex);
		ModelAndView model=new ModelAndView();
		model.addObject("message", "Failed");
		model.addObject("exception", ex.getMessage());
		model.setViewName("showerror");
		return model;
		
		
	}
}
