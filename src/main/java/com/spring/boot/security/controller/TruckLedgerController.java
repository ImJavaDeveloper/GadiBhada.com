package com.spring.boot.security.controller;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.constant.TableConstant;
import com.spring.boot.security.dto.FareCollectionData;
import com.spring.boot.security.entity.TruckPymt;
import com.spring.boot.security.forms.data.CollectionsBook;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.forms.data.TruckLedgerVO;
import com.spring.boot.security.mapper.CollectionsBookDataMapper;
import com.spring.boot.security.mapper.TruckLedgerMapper;
import com.spring.boot.security.repository.TruckPymtRepository;

import net.minidev.json.JSONArray;

@Controller
@RequestMapping("/gadibhada")
public class TruckLedgerController {

	
private static final Logger LOGGER=LoggerFactory.getLogger(TruckLedgerController.class);
	
	@Autowired
	DataSource datasource;

	@Autowired
	FareCollectionData fareCollectionData;
	
	@Autowired
	TruckPymtRepository truckPymtRepository;

	@RequestMapping(value="/dataentry/truckLedger",method=RequestMethod.POST)
	@ResponseBody
	public String truckLedger() throws Exception
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<TruckLedgerVO> truckLedgerEntries=jdbcTemplate.query(TableQuery.getTruckLedgerQuery(), new TruckLedgerMapper());	
		String truckLedgerEntriesArray = JSONArray.toJSONString(truckLedgerEntries);
		return truckLedgerEntriesArray;
		
	}
	
	@RequestMapping(value="/dataentry/updateTruckLedger",method=RequestMethod.POST)
	@ResponseBody
	public String updateTruckLedger(@RequestParam String truckNoLedger,@RequestParam String truckStDtLedger,@RequestParam String truckEndDtLedger,@RequestParam double truckPrizeAmt,@RequestParam double truckTotFare) throws Exception
	{
		int noOfRowUpdated=-1;
		String msg="";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="update "+TableConstant.TRUCK_LEDGER_TABLE+" set truck_end_dt='"+truckEndDtLedger+"',prize_amt="+truckPrizeAmt+",tot_fare="+truckTotFare+" where truck_no='"+truckNoLedger+"' And truck_start_dt='"+truckStDtLedger+"'";
		noOfRowUpdated=jdbcTemplate.update(sql);
		if(noOfRowUpdated>=0)
			msg="Data has been updated successfully";
		else
			msg="Data has not been updated !!";
		return msg;
		
	}
	
	@RequestMapping(value="/dataentry/truckPayment",method=RequestMethod.POST)
	@ResponseBody
	public String truckPayment(@RequestParam String truckNoLedger,@RequestParam Date truckStDtLedger,@RequestParam double pymtAmt,@RequestParam Date pymtDt,@RequestParam Date ledgerDt) throws Exception
	{
		String returnMsg=null;
		TruckPymt truckPymt=new TruckPymt();
		truckPymt.setTruckNo(truckNoLedger);
		truckPymt.settStartDt(truckStDtLedger);
		truckPymt.setPymtAmt(pymtAmt);
		truckPymt.setPymtDt(pymtDt);
		truckPymt.setLedgerDt(ledgerDt);
		TruckPymt truckPymtSaved=truckPymtRepository.save(truckPymt);
		if(truckPymtSaved !=null)
			returnMsg="Success";
		else
			returnMsg="Failed To Save Payment";
		
		return returnMsg;
		
		
		
	}
}
