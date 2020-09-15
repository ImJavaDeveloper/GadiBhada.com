package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.security.entity.TruckLedger;
import com.spring.boot.security.repository.TruckLedgerRespository;

@Service
public class TruckLedgerData {
	
	@Autowired
	TruckLedgerRespository truckLedgerRespository;
	
	public boolean checkTruckArrived( String truckNo, String startDt)
	{
		List<TruckLedger> list=truckLedgerRespository.findTruckByEndDt(truckNo, startDt);
		if(list == null || list.size() ==0 || list.get(0).getTruckEndDt() == null ) {
		 return false;
		}
		 else
		 {
			 return true;
		 }
	}

}
