package com.spring.boot.security.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.entity.FareCollection;
import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.entity.SourceDetails;
import com.spring.boot.security.entity.UserDetails;
import com.spring.boot.security.forms.data.AllChallanData;
import com.spring.boot.security.forms.data.AllDistributionData;
import com.spring.boot.security.forms.data.CollectionFareVO;
import com.spring.boot.security.forms.data.CollectionsBook;
import com.spring.boot.security.forms.data.CollectionsByIdVO;
import com.spring.boot.security.forms.data.FareBook;
import com.spring.boot.security.forms.data.LotBooksByChallanIdVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.mapper.AllChallanDataMapper;
import com.spring.boot.security.mapper.AllDistributionDataMapper;
import com.spring.boot.security.mapper.CollectionByIdMapper;
import com.spring.boot.security.mapper.CollectionsBookDataMapper;
import com.spring.boot.security.mapper.CollectionsDataMapper;
import com.spring.boot.security.mapper.FareBookMapper;
import com.spring.boot.security.mapper.LotBooksByChallanIdMapper;
import com.spring.boot.security.repository.ChallanBookRepository;
import com.spring.boot.security.repository.FareCollectionRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.UserDetailsRepository;
import com.spring.boot.security.utils.SecurityUtils;

import net.minidev.json.JSONArray;

@Controller
@RequestMapping("/gadibhada")
public class ManageDataPageController {

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
	
	@RequestMapping("/managedata")
	public String loadManageDataPage(Model model)
	{
		
		String username=SecurityUtils.getUser();
		model.addAttribute("username", username);
		UserDetails userDetails=userDetailsRepository.findByUserName(username);
		model.addAttribute("firstName", userDetails.getFirstName());
		return "manageData";
		
	}
	
	@RequestMapping(value="/managedata/allchallan",method=RequestMethod.POST)
	@ResponseBody
	public String allChallanData()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<AllChallanData> allChallanData=jdbcTemplate.query(TableQuery.getAllChallansQuery(), new AllChallanDataMapper());
		String challanBookJsonArray = JSONArray.toJSONString(allChallanData);		
		return challanBookJsonArray;
		
	}
	
	@RequestMapping(value="/managedata/viewchallan",method=RequestMethod.POST)
	@ResponseBody
	public String viewChallanById(@RequestParam int challanId)
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<LotBooksByChallanIdVO> lotBooksByChallanIdVO=jdbcTemplate.query(TableQuery.getLotBooksByChallanIdQuery(challanId), new LotBooksByChallanIdMapper());	
		 String lotBooksJsonArray = JSONArray.toJSONString(lotBooksByChallanIdVO);		
		 return lotBooksJsonArray;
		
	}
	
	@RequestMapping(value="/managedata/alldistribution",method=RequestMethod.POST)
	@ResponseBody
	public String allDistributionData()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<AllDistributionData> allDistributionData=jdbcTemplate.query(TableQuery.getAllDistributionDataQuery(), new AllDistributionDataMapper());	
		 String allDistribzutionDataArray = JSONArray.toJSONString(allDistributionData);		
		 return allDistribzutionDataArray;
		
	}
	
	@RequestMapping(value="/managedata/allfares",method=RequestMethod.POST)
	@ResponseBody
	public String fareBook()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<FareBook> fareBook=jdbcTemplate.query(TableQuery.getAllFareBookQuery(), new FareBookMapper());	
		String allFareDataArray = JSONArray.toJSONString(fareBook);		
		return allFareDataArray;
		
	}
	
	@RequestMapping(value="/managedata/allcollections",method=RequestMethod.POST)
	@ResponseBody
	public String collectionBook()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<CollectionsBook> collectionBook=jdbcTemplate.query(TableQuery.getCollectionBookDataQuery(), new CollectionsBookDataMapper());	
		String collectionBookArray = JSONArray.toJSONString(collectionBook);		
		
		return collectionBookArray;
		
	}
	
	@RequestMapping(value="/managedata/collectionsBySubLotId",method=RequestMethod.POST)
	@ResponseBody
	public String collectionsBySubLotId(@RequestParam int subLotId)
	{
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<CollectionsByIdVO> collectionByIdList=jdbcTemplate.query(TableQuery.getCollectionsById(subLotId), new CollectionByIdMapper());	
		String collectionBookArray = JSONArray.toJSONString(collectionByIdList);	
		return collectionBookArray;
		
	}
	
	
}
