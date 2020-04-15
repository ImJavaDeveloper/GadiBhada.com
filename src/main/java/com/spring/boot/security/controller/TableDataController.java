package com.spring.boot.security.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.dto.AgentDestinationData;
import com.spring.boot.security.dto.AgentDetailsData;
import com.spring.boot.security.dto.BoxDetailsData;
import com.spring.boot.security.dto.ItemDetailsData;
import com.spring.boot.security.dto.SourceDetailsData;
import com.spring.boot.security.dto.TraderDetailsData;
import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.AgentDetails;
import com.spring.boot.security.entity.BoxDetails;
import com.spring.boot.security.entity.ItemDetails;
import com.spring.boot.security.entity.SourceDetails;
import com.spring.boot.security.entity.TraderDetails;
import com.spring.boot.security.forms.data.AgentListVO;
import com.spring.boot.security.forms.data.BoxListVO;
import com.spring.boot.security.forms.data.ComboListVO;
import com.spring.boot.security.forms.data.DestinationListVO;
import com.spring.boot.security.forms.data.ItemListVO;
import com.spring.boot.security.forms.data.SourceListVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.forms.data.TraderListVO;

import net.minidev.json.JSONArray;

@Controller
public class TableDataController {
	
	@Autowired
	DataSource datasource;
	@Autowired
	SourceDetailsData sourceDetailsData;
	@Autowired
	AgentDestinationData agentDestinationData;
	@Autowired
	ItemDetailsData itemDetailsData;
	@Autowired
	BoxDetailsData boxDetailsData;
	@Autowired
	TraderDetailsData traderDetailsData;
	@Autowired
	AgentDetailsData agentDetailsData;
	
	@RequestMapping(value="/management/getAllSource")
	@ResponseBody
	public String getAllSource()
	{
		List<SourceDetails> sourceList=sourceDetailsData.getAllSource();
		 String jsonStr = JSONArray.toJSONString(sourceList);
		return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllTrader")
	@ResponseBody
	public String getAllTrader()
	{
		List<TraderDetails> traderDetails=traderDetailsData.getAllTradersList();
		 String jsonStr = JSONArray.toJSONString(traderDetails);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllDestination")
	@ResponseBody
	public String getAllAgentDestination()
	{
		List<AgentDestination> agenDestList=agentDestinationData.getAllAgentDestination();
		 String jsonStr =JSONArray.toJSONString(agenDestList);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllItems")
	@ResponseBody
	public String getAllItems()
	{
		List<ItemDetails> itemList=itemDetailsData.getAllItems();
		 String jsonStr = JSONArray.toJSONString(itemList);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAllBoxType")
	@ResponseBody
	public String getAllBoxType()
	{
		List<BoxDetails> boxList=boxDetailsData.getAllBoxType();
		 String jsonStr =JSONArray.toJSONString(boxList);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getTradersList")
	@ResponseBody
	public String getAllTraderList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<TraderListVO> traderListVO=jdbcTemplate.query(TableQuery.getAllTraderListQuery(), new RowMapper<TraderListVO>() {

			@Override
			public TraderListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				TraderListVO traders=new TraderListVO();
				traders.setValue(rs.getInt(++i));
				traders.setText(rs.getString(++i));
				return traders;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(traderListVO);
		 return jsonStr;
		
	}
	@RequestMapping(value="/management/getSourceList")
	@ResponseBody
	public String getAllSourceList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<SourceListVO> sourceListVOs=jdbcTemplate.query(TableQuery.getAllSourceListQuery(), new RowMapper<SourceListVO>() {

			@Override
			public SourceListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				SourceListVO sourceListVO=new SourceListVO();
				sourceListVO.setValue(rs.getInt(++i));
				sourceListVO.setText(rs.getString(++i));
				return sourceListVO;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(sourceListVOs);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getDestinationList")
	@ResponseBody
	public String getAllDestinationList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<DestinationListVO> destinationListVOs=jdbcTemplate.query(TableQuery.getAllDestinationListQuery(), new RowMapper<DestinationListVO>() {

			@Override
			public DestinationListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				DestinationListVO destinationListVO=new DestinationListVO();
				destinationListVO.setValue(rs.getInt(++i));
				destinationListVO.setText(rs.getString(++i));
				return destinationListVO;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(destinationListVOs);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getItemList")
	@ResponseBody
	public String getAllItemsList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<ItemListVO> itemListVOs=jdbcTemplate.query(TableQuery.getAllItemsListQuery(), new RowMapper<ItemListVO>() {

			@Override
			public ItemListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				ItemListVO itemListVO=new ItemListVO();
				itemListVO.setValue(rs.getInt(++i));
				itemListVO.setText(rs.getString(++i));
				return itemListVO;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(itemListVOs);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getBoxList")
	@ResponseBody
	public String getAllIBoxList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<BoxListVO> boxListVOs=jdbcTemplate.query(TableQuery.getAllBoxListQuery(), new RowMapper<BoxListVO>() {

			@Override
			public BoxListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				BoxListVO boxListVO=new BoxListVO();
				boxListVO.setValue(rs.getInt(++i));
				boxListVO.setText(rs.getString(++i));
				return boxListVO;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(boxListVOs);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAgentList")
	@ResponseBody
	public String getAgentList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<AgentListVO> boxListVOs=jdbcTemplate.query(TableQuery.getAllAgentListQuery(), new RowMapper<AgentListVO>() {

			@Override
			public AgentListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				AgentListVO agentListVO=new AgentListVO();
				agentListVO.setValue(rs.getInt(++i));
				agentListVO.setText(rs.getString(++i));
				return agentListVO;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(boxListVOs);
		 return jsonStr;
		
	}
	
	@RequestMapping(value="/management/getAgentDestList")
	@ResponseBody
	public String getAgentDestList()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<ComboListVO> comboListVOs=jdbcTemplate.query(TableQuery.getAllAgentDestListQuery(), new RowMapper<ComboListVO>() {

			@Override
			public ComboListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int i=0;
				ComboListVO comboListVO=new ComboListVO();
				comboListVO.setValue(rs.getInt(++i));
				comboListVO.setText(rs.getString(++i));
				return comboListVO;
			}
		});
				
		 String jsonStr = JSONArray.toJSONString(comboListVOs);
		 return jsonStr;
		
	}

	@RequestMapping(value="/management/getAgentAddr")
	@ResponseBody
	public String getTraderById(@RequestParam int agentId)
	{
		AgentDetails agentDetails=agentDetailsData.getAgentById(agentId);
		 
		 return agentDetails.getAgentAddress();
		
	}

}
