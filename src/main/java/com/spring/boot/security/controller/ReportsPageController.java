package com.spring.boot.security.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.entity.UserDetails;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.forms.data.reports.FareAndcollectionsData;
import com.spring.boot.security.repository.ChallanBookRepository;
import com.spring.boot.security.repository.FareCollectionRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.UserDetailsRepository;
import com.spring.boot.security.utils.SecurityUtils;

import net.minidev.json.JSONArray;

@Controller
@RequestMapping("/gadibhada")
public class ReportsPageController {

	@Autowired
	DataSource datasource;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	ChallanBookRepository challanBookRepository;
	
	@Autowired
	LotBookRepository lotBookRepository;
	@Autowired
	FareCollectionRepository fareCollectionRepository;
	
	@RequestMapping("/reports")
	public String loadManageDataPage(Model model)
	{
		
		String username=SecurityUtils.getUser();
		model.addAttribute("username", username);
		UserDetails userDetails=userDetailsRepository.findByUserName(username);
		model.addAttribute("firstName", userDetails.getFirstName());
		return "reports";
		
	}
	
	@RequestMapping(value="/reports/fareAndcollReport",method=RequestMethod.POST)
	@ResponseBody
	public String fareAndcollAreaWiseData()
	{
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<FareAndcollectionsData> fareAndCollDataList=jdbcTemplate.query(TableQuery.fareAndcollDataQuery(), new RowMapper<FareAndcollectionsData>() {

			
			@Override
			public FareAndcollectionsData mapRow(ResultSet rs, int rowNum) throws SQLException {
				int i=0;
				FareAndcollectionsData fareAndCollData=new FareAndcollectionsData();
				fareAndCollData.setAGENT_DESTINATION(rs.getString(++i));
				fareAndCollData.setAGENT_NAME(rs.getString(++i));
				fareAndCollData.setDELIVERY_DATE(rs.getString(++i));
				fareAndCollData.setITEM_CODE(rs.getString(++i));
				fareAndCollData.setTOTAL_QTY(rs.getDouble(++i));
				fareAndCollData.setTOTAL_FARE(rs.getDouble(++i));
				fareAndCollData.setTOTAL_PAYMENT(rs.getDouble(++i));
				fareAndCollData.setTOTAL_DEBIT(rs.getDouble(++i));
				fareAndCollData.setTOTAL_BALANCE(rs.getDouble(++i));
				// TODO Auto-generated method stub
				return fareAndCollData;
			}
		});	
		
		String fareAndCollDataListArray = JSONArray.toJSONString(new LinkedList<>(fareAndCollDataList));	
		return fareAndCollDataListArray;
		
	}
	
}
