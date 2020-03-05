package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.ItemDetails;
import com.spring.boot.security.entity.SourceDetails;
import com.spring.boot.security.repository.ItemDetailsRepository;

@Service
public class ItemDetailsData {

	@Autowired
	ItemDetailsRepository itemDetailsRepository;
	
	public List<ItemDetails> getAllItems()
	{
		
		return itemDetailsRepository.findAll();
	}
	
	public ItemDetails saveItem(ItemDetails itemDetails)
	{
		return itemDetailsRepository.save(itemDetails);
	}
	
	public String getNameByItem(String itemName)
	{
		String item=null;
		ItemDetails itemDetails=itemDetailsRepository.findByItemName(itemName);
		if(itemDetails!=null)
			item=itemDetails.getItem_name();
		
		return item;
	}
	
}
