package com.spring.boot.security.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.dto.AgentDestinationData;
import com.spring.boot.security.dto.BoxDetailsData;
import com.spring.boot.security.dto.ItemDetailsData;
import com.spring.boot.security.dto.SourceDetailsData;
import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.BoxDetails;
import com.spring.boot.security.entity.ItemDetails;
import com.spring.boot.security.entity.SourceDetails;

@Controller
public class TableDataController {
	
	@Autowired
	SourceDetailsData sourceDetailsData;
	@Autowired
	AgentDestinationData agentDestinationData;
	@Autowired
	ItemDetailsData itemDetailsData;
	@Autowired
	BoxDetailsData boxDetailsData;
	
	@RequestMapping(value="/management/getAllSource")
	@ResponseBody
	public String getAllSource()
	{
		List<SourceDetails> sourceList=sourceDetailsData.getAllSource();
		 String jsonStr = net.minidev.json.JSONArray.toJSONString(sourceList);
		return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllDestination")
	@ResponseBody
	public String getAllAgentDestination()
	{
		List<AgentDestination> agenDestList=agentDestinationData.getAllAgentDestination();
		 String jsonStr = net.minidev.json.JSONArray.toJSONString(agenDestList);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllItems")
	@ResponseBody
	public String getAllItems()
	{
		List<ItemDetails> itemList=itemDetailsData.getAllItems();
		 String jsonStr = net.minidev.json.JSONArray.toJSONString(itemList);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllBoxType")
	@ResponseBody
	public String getAllBoxType()
	{
		List<BoxDetails> boxList=boxDetailsData.getAllBoxType();
		 String jsonStr = net.minidev.json.JSONArray.toJSONString(boxList);
		 return jsonStr;
		
	}

}
