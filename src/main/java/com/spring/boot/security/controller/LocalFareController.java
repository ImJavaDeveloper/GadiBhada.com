package com.spring.boot.security.controller;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.constant.TableConstant;
import com.spring.boot.security.entity.LocalFare;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.LocalFareEntryVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.repository.LocalFareRepository;

@Controller
@RequestMapping("/gadibhada")
public class LocalFareController {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(LocalFareController.class);
	@Autowired
	LocalFareRepository localFareRepo;
	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value="/dataentry/saveLocalFareDetail",method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	@ResponseBody
	public String saveLocalFareDetail(@RequestParam String truckNo,@RequestParam String arrivalDt,@RequestParam String localDriver,@RequestParam String ledgerDt,@RequestParam double totFare,@RequestParam String source,@RequestParam String destinations,@RequestParam int totWt) throws Exception
	{

		String truck[]=truckNo.split(",");
		String trucks="";
		for(int i=0;i<truck.length;i++)
		{
			
			if(i!=truck.length-1)
				trucks=trucks+"'"+truck[i]+"',";
			else
				trucks=trucks+"'"+truck[i]+"'";
		}
		String arrvlDt[]=arrivalDt.split(",");
		String arrivalDts="";
		for(int i=0;i<arrvlDt.length;i++)
		{
			
			if(i!=arrvlDt.length-1)
				arrivalDts=arrivalDts+"'"+arrvlDt[i]+"',";
				
			else
				arrivalDts=arrivalDts+"'"+arrvlDt[i]+"'";
		}
			
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
		String sql = TableQuery.getLocalFareSubLotIdslQuery(trucks, arrivalDts, localDriver, ledgerDt);
		LOGGER.info(sql);
		List<Integer> subLotIdArray=jdbcTemplate.queryForList(sql, Integer.class);
	    if(subLotIdArray.size()==0)
	    	throw new DataBaseException("SubLotId Is Not Present: ", "errorPage");
	    
		String subLotIds=subLotIdArray.toString().substring(1, subLotIdArray.toString().length()-1);
		if(totFare>0) {
		LocalFare localFare=new LocalFare();
		localFare.setLedgerDt(ledgerDt);
		localFare.setLocalDriver(localDriver);
		localFare.setTotLocalFare(totFare);
		localFare.setFromToWhere(source+"-"+destinations);
		localFare.setTotWt(totWt);
		localFare.setSubLotIdArray(subLotIds);
		
		LocalFare savedLocalFare=localFareRepo.save(localFare);
		
		/*Updating local_fare_track as Y becuase fare has been logged for those subIds*/
		String updatedQuery="update  local_fare_track set indicator='Y' where sub_lot_id in("+subLotIds+")";
		int noOfRowsUpdated=jdbcTemplate.update(updatedQuery);
		if(savedLocalFare==null || noOfRowsUpdated !=subLotIdArray.size())
			throw new DataBaseException("Exception found while saving data into Local Fare ", "errorPage");
		}
		
		
		/*Updating local_fare_track as Y becuase fare has been logged for those subIds*/
		String updatedQuery="update  local_fare_track set indicator='Y' where sub_lot_id in("+subLotIds+")";
		int noOfRowsUpdated=jdbcTemplate.update(updatedQuery);
		
		if( noOfRowsUpdated !=subLotIdArray.size() )
			throw new DataBaseException("Exception found while saving data into Challan Book ", "errorPage");
		
		return "Success";
			
		
		
	}

}
