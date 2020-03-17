package com.spring.boot.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.security.dto.BoxDetailsData;
import com.spring.boot.security.dto.DestinationDetailsData;
import com.spring.boot.security.dto.ItemDetailsData;
import com.spring.boot.security.dto.SourceDetailsData;
import com.spring.boot.security.dto.TraderDetailsData;
import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.entity.UserDetails;
import com.spring.boot.security.repository.UserDetailsRepository;
import com.spring.boot.security.utils.SecurityUtils;

@Controller
@RequestMapping(value="/gadibhada")
public class DataEntryPageController {
	
	@Autowired
	TraderDetailsData traderData;
	@Autowired
	ItemDetailsData itemDetailsData;
	@Autowired
	BoxDetailsData boxDetailsData;
	@Autowired
	DestinationDetailsData destinationDetailsData;
	@Autowired
	SourceDetailsData sourceDetailsData;
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@RequestMapping(value="/dataentry")
	public String loadDataEntryPage(Model model)
	{

		String username=SecurityUtils.getUser();
        List<TraderDetails> traders=traderData.getAllTradersList();
		model.addAttribute("username", username);

		UserDetails userDetails=userDetailsRepository.findByUserName(username);
		  model.addAttribute("firstName", userDetails.getFirstName());
	      model.addAttribute("traders", traders);
	      model.addAttribute("items", itemDetailsData.getAllItems());
	      model.addAttribute("boxTypes", boxDetailsData.getAllBoxType());
	      model.addAttribute("destinations", destinationDetailsData.getDestinations());
	      model.addAttribute("sources", sourceDetailsData.getAllSource());	
	     
	      return "dataentry";	
		
	}

}
