package com.spring.boot.security.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.repository.ChallanBookRepository;

@Service
public class ChallanBookData {
	
	@Autowired
	ChallanBookRepository challanBookRepository;
	
	public ChallanBook saveChallanData(ChallanBook challanBook)	{
		return challanBookRepository.save(challanBook);
	}
	
	public boolean findIfChallanBookExist(Date challan_date,String truck_no)	{
		
		List<ChallanBook> challanBook=challanBookRepository.findExistingChallanBook(challan_date, truck_no);
		if(challanBook == null || challanBook.size()==0)
			return false;
		else
			return true;
		
	}

}
