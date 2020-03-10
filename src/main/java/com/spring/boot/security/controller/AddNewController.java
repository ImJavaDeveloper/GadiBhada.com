package com.spring.boot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.dto.AgentDestinationData;
import com.spring.boot.security.dto.AgentDetailsData;
import com.spring.boot.security.dto.BoxDetailsData;
import com.spring.boot.security.dto.DestinationDetailsData;
import com.spring.boot.security.dto.FareRuleData;
import com.spring.boot.security.dto.ItemDetailsData;
import com.spring.boot.security.dto.SourceDetailsData;
import com.spring.boot.security.dto.TraderDetailsData;
import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.AgentDetails;
import com.spring.boot.security.entity.BoxDetails;
import com.spring.boot.security.entity.DestinationDetails;
import com.spring.boot.security.entity.FareRule;
import com.spring.boot.security.entity.ItemDetails;
import com.spring.boot.security.entity.SourceDetails;
import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.helper.DataValidator;

@Controller
public class AddNewController {

	@Autowired
	SourceDetailsData sourceDetailsData;

	@Autowired
	AgentDestinationData agentDestinationData;

	@Autowired
	DestinationDetailsData destinationDetailsData;

	@Autowired
	TraderDetailsData traderDetailsData;

	@Autowired
	AgentDetailsData agentDetailsData;
	
	@Autowired
	ItemDetailsData itemDetailsData;
	
	@Autowired
	BoxDetailsData boxDetailsData;
	
	@Autowired
	FareRuleData fareRuleData;
	
	boolean isSourcePresent;

	@RequestMapping(value = "/management/addnew/addsource", method = RequestMethod.GET)
	@ResponseBody
	public String addSource(@RequestParam String sourceName) throws DataBaseException {
		String message = null;

		String sourceNm = sourceDetailsData.getNameBySource(sourceName);
		if (sourceNm == null)

		{
			SourceDetails sourceDetails = new SourceDetails();
			sourceDetails.setSourceName(sourceName);
			SourceDetails source = sourceDetailsData.saveSource(sourceDetails);
			if (source == null) {
				message = "Exception found while saving source details into source";
				throw new DataBaseException("Exception found while saving source details into source ", "");

			} else
				message = "Success !! Source Is Saved successfully";
		}
		if (sourceNm != null && sourceNm.equalsIgnoreCase(sourceName)) {
			message = "Source Is Already Found";
		}

		return message;

	}

	@RequestMapping(value = "/management/addnew/adddestination", method = RequestMethod.GET)
	@ResponseBody
	public String addDestination(@RequestParam String destName, @RequestParam String destInd) throws DataBaseException {
		String message = null;
		if (destInd.equals("agentDest")) {
			String destinationName = agentDestinationData.getNameByAgentDestination(destName);
			if (destinationName == null) {
				AgentDestination agentDestination = new AgentDestination();
				agentDestination.setAgentDestinationName(destName.trim());
				AgentDestination agentDestinationSaved = agentDestinationData.saveAgentDestination(agentDestination);
				if (agentDestinationSaved == null) {
					message = "Exception found while saving destination details into Agent Destination";
					throw new DataBaseException("Exception found while saving source details into source ", "");

				} else
					message = "Success !! Record Has Been Added Successfully";
			}

			if (destinationName != null && destinationName.equalsIgnoreCase(destName)) {
				message = "Record Is Already Found";
			}

		}

		if (destInd.equals("truckDest")) {
			String destinationName = destinationDetailsData.getNameByDestination(destName);
			if (destinationName == null) {
				DestinationDetails destinationDetails = new DestinationDetails();
				destinationDetails.setDestination(destName.trim());
				DestinationDetails destinationDetailsSaved = destinationDetailsData
						.saveDestinationDetails(destinationDetails);
				if (destinationDetailsSaved == null) {
					message = "Exception found while saving destination into Destination Table";
					throw new DataBaseException("Exception found while saving source details into source ", "");

				} else
					message = "Success !! Record Has Been Added Successfully";
			}

			if (destinationName != null && destinationName.equalsIgnoreCase(destName)) {
				message = "Record Is Already Found";
			}
		}

		return message;

	}

	@RequestMapping(value = "/management/addnew/savetrader", method = RequestMethod.GET)
	@ResponseBody
	public String saveTradeDetails(@RequestParam String traderName, @RequestParam String tradeMark,
			@RequestParam(required = false) String traderAdd, @RequestParam String traderMob) throws DataBaseException {
		String message = null;

		if (!DataValidator.isValidMobileNumer(traderMob)) {
			message = traderMob + " Mobile no Must have only digit";
			return message;
		}
		if (DataValidator.checkStringLenght(traderMob) != 10) {
			message = traderMob + ";Mobile no must contain 10 digit";
			return message;
		}

		TraderDetails traderAlreadyPresent = traderDetailsData.findByTraderName(traderName, tradeMark);
		if (traderAlreadyPresent == null) {
			TraderDetails trader = new TraderDetails();
			trader.setTrader_name(traderName);
			trader.setTrader_address(traderAdd);
			trader.setTrader_mark(tradeMark);
			trader.setTrader_mobile(traderMob);

			TraderDetails savedTrader = traderDetailsData.saveTrader(trader);
			if (savedTrader == null) {
				message = "Exception found while saving trader into Trader Table";
				throw new DataBaseException("Exception found while saving source details into source ", "");

			} else
				message = "Success !! Record Has Been Added Successfully";
		}

		else {
			message = "Record Is Already Found";
		}
		return message;

	}

	@RequestMapping(value = "/management/addnew/saveagent", method = RequestMethod.GET)
	@ResponseBody
	public String saveAgentDetails(@RequestParam String agentName, @RequestParam String agentMark,
			@RequestParam String agentAdd, @RequestParam String agentMob) throws DataBaseException {
		String message = null;

		if (!DataValidator.isValidMobileNumer(agentMob)) {
			message = agentMob + " Mobile no Must have only digit";
			return message;
		}
		if (DataValidator.checkStringLenght(agentMob) != 10) {
			message = agentMob + ";Mobile no must contain 10 digit";
			return message;
		}

		AgentDetails agentAlreadyPresent = agentDetailsData.findByAgentName(agentName, agentMark);
		if (agentAlreadyPresent == null) {
			AgentDetails agent = new AgentDetails();
			agent.setAgentName(agentName);
			agent.setAgentMark(agentMark);
			agent.setAgentAddress(agentAdd);
			agent.setAgentMobile(agentMob);

			AgentDetails savedAgent = agentDetailsData.saveAgent(agent);
			if (savedAgent == null) {
				message = "Exception found while saving trader into Agent Details Table";
				throw new DataBaseException("Exception found while saving source details into source ", "");

			} else
				message = "Success !! Record Has Been Added Successfully";
		}

		else {
			message = "Record Is Already Found";
		}
		return message;

	}
	
	@RequestMapping(value = "/management/addnew/saveitem", method = RequestMethod.GET)
	@ResponseBody
	public String addItemDetails(@RequestParam String itemName) throws DataBaseException {
		String message = null;

		String itemNameAvl = itemDetailsData.getNameByItem(itemName);
		if (itemNameAvl == null)

		{
			ItemDetails itemDetails = new ItemDetails();
			itemDetails.setItem_name(itemName);
			ItemDetails savedItemDetails = itemDetailsData.saveItem(itemDetails);
			if (savedItemDetails == null) {
				message = "Exception found while saving item details into item";
				throw new DataBaseException("Exception found while saving item details into item ", "");

			} else
				message = "Success !! Record Is Saved successfully";
		}
		if (itemNameAvl != null && itemNameAvl.equalsIgnoreCase(itemName)) {
			message = "Record Is Already Found";
		}

		return message;

	}

	@RequestMapping(value = "/management/addnew/savebox", method = RequestMethod.GET)
	@ResponseBody
	public String addBoxDetails(@RequestParam String boxName,@RequestParam String totalWt ) throws DataBaseException {
		String message = null;
        int wt=0;
		if (!DataValidator.isValidMobileNumer(totalWt)) {
			message = totalWt + " Total Wt Is Not Correct";
			return message;
		}
		else
			wt=Integer.parseInt(totalWt);
		
		BoxDetails isBoxDetailsAvl = boxDetailsData.findByBoxAndWt(boxName, Integer.parseInt(totalWt));
		if (isBoxDetailsAvl == null)

		{
			BoxDetails boxDetails = new BoxDetails();
			boxDetails.setBox_name(boxName);
			boxDetails.setTotal_wt(wt);
			BoxDetails savedBoxDetails = boxDetailsData.saveBoxDetails(boxDetails);
			if (savedBoxDetails == null) {
				message = "Exception found while saving Box details Table";
				throw new DataBaseException("Exception found while saving Box details Table ", "");

			} else
				message = "Success !! Record Is Saved successfully";
		}
		else {
			message = "Record Is Already Found";
		}

		return message;

	}
	
	@RequestMapping(value = "/management/addnew/saveaddfare", method = RequestMethod.GET)
	@ResponseBody
	public String addFareRule(@RequestParam int source,@RequestParam int destination,@RequestParam int item,
			@RequestParam int boxType,@RequestParam String fare ) throws DataBaseException {
		String message = null;
        double fareVal=0;
		if (!DataValidator.isNumber(fare)) {
			message = fare + " Fare Enter Is Not Correct";
			return message;
		}
		else
			fareVal=Double.valueOf(fare);
		
		FareRule isFareRuleAvl = fareRuleData.findDuplicateFareRule(source, destination, boxType, item);
		if (isFareRuleAvl == null)

		{
			FareRule fareRule = new FareRule();
			fareRule.setSourceId(source);
			fareRule.setAgentDestinationId(destination);
			fareRule.setBoxId(boxType);
			fareRule.setItemId(item);
			fareRule.setFare(fareVal);
			FareRule savedFareRule = fareRuleData.saveBoxDetails(fareRule);
			if (savedFareRule == null) {
				message = "Exception found while saving Fare Rule  Table";
				throw new DataBaseException("Exception found while saving Fare Rule  Table", "");

			} else
				message = "Success !! Record Is Saved successfully";
		}
		else {
			message = "Record Is Already Found";
		}

		return message;

	}
	
	
}
