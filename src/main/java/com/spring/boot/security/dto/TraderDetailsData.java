package com.spring.boot.security.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.repository.TraderDetailsRepository;


@Service
public class TraderDetailsData {

	@Autowired
	TraderDetailsRepository traderDetailsRepository;
	
	
	public List<TraderDetails> getAllTradersList()
	{
		//return traderRepository.findByTrader_id(1);
		return  traderDetailsRepository.findAll();
	}
	public TraderDetails saveTrader(TraderDetails trader)
	{
		return  traderDetailsRepository.save(trader);
	}
	
	public TraderDetails findByTraderName(String trader_name,String trader_mark)
	{
		return  traderDetailsRepository.findByTraderName(trader_name, trader_mark);
	}
}
