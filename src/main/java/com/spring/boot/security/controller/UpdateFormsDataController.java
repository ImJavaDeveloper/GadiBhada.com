package com.spring.boot.security.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.constant.TableConstant;
import com.spring.boot.security.exception.CustomGenericException;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.DataValidator;
import com.spring.boot.security.repository.SubLotBookRepository;

@Controller
@RequestMapping("/gadibhada")
public class UpdateFormsDataController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UpdateFormsDataController.class);
	
	@Autowired
	DataSource datasource;
	@Autowired
	SubLotBookRepository subLotBookRepository;
	
	@RequestMapping(value="/managedata/updateChallanDate",method=RequestMethod.POST)
	@ResponseBody
	public String updateChallanDate(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			if(!DataHelper.isValidDateFormat(value, "dd/MM/yyyy"))
		           throw new Exception("Date format is not correct !!");
			
			JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			String sql="update challan_book set "+name+"='"+DataHelper.formatDate(value, "dd/MM/yyyy", "yyyy-MM-dd")+"' where challan_id="+pk;
			int noOfRowsUpdated=jdbcTemplate.update(sql);
			System.out.println(noOfRowsUpdated);
			return value;
		
	}
	@RequestMapping(value="/managedata/updateChallanTruck",method=RequestMethod.POST)
	@ResponseBody
	public String updateChallanTruck(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			if(value.trim().length()==0 ||value.trim().length()<8 )
				throw new CustomGenericException("Truck no must not be empty And must have proper");
			
			JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			String sql="update challan_book set "+name+"='"+value+"' where challan_id="+pk;
			int noOfRowsUpdated=jdbcTemplate.update(sql);
			System.out.println(noOfRowsUpdated);
			return value;
		
	}
	
	@RequestMapping(value="/managedata/updateChallanSource",method=RequestMethod.POST)
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public String updateChallanSource(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			
		try {	
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			String updateChallanBooksql="update challan_book set "+name+"='"+value+"' where challan_id="+pk;
			//String deleteSubLotFromFareBook="delete from fare_book where sub_lot_id in(select sub_lot_id from sub_lot_book where lot_id in(select lot_id from lot_book where challan_id="
			
			String deleteSubLotFromFareBook="delete from fare_book where sub_lot_id in(select sub_lot_id "
					+ "from sub_lot_book where lot_id in(select lot_id from lot_book where challan_id="+pk+"))";
			String deleteSubLotFromCollectionBook="delete from fare_collection where fare_id in(select fare_id "
					+ " from fare_book where sub_lot_id in(select sub_lot_id "
					+ "from sub_lot_book where lot_id in(select lot_id from lot_book where challan_id="+pk+")))";
			int noOfRowsUpdated=jdbcTemplate.update(updateChallanBooksql);
			int noOfRowsDeleted2=jdbcTemplate.update(deleteSubLotFromCollectionBook);
			int noOfRowsDeleted1=jdbcTemplate.update(deleteSubLotFromFareBook);
			
			System.out.println("noOfRowsUpdated:"+noOfRowsUpdated);
			System.out.println("noOfRowsDeleted1:"+noOfRowsDeleted1);
			System.out.println("noOfRowsDeleted2:"+noOfRowsDeleted2);
		}
			catch(Exception e)
			{
				
			}
			return value;
		
	}
	
	@RequestMapping(value="/managedata/updateChallanDestination",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanDestination(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			
		return updateTable(TableConstant.CHALLAN_BOOK_TABLE,name,value,TableConstant.CHALLAN_BOOK_PK_Column,pk);	
		
	}
	
	@RequestMapping(value="/managedata/updateChallanDriverName",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanDriverName(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
		int noOfRowsUpdate= updateTable2(TableConstant.CHALLAN_BOOK_TABLE,name,value,TableConstant.CHALLAN_BOOK_PK_Column,pk);				
		return noOfRowsUpdate;
	}
	
	@RequestMapping(value="/managedata/updateChallanDriverMob",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanDriverMob(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			
		if(!DataValidator.isValidMobileNumer(value) )
			throw new CustomGenericException("Mobile no is not correct !! It must have ten digit and only decimal value");
		    
			return updateTable(TableConstant.CHALLAN_BOOK_TABLE,name,value,TableConstant.CHALLAN_BOOK_PK_Column,pk);				
	}
	
	@RequestMapping(value="/managedata/updateChallanLotTrader",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanTrader(@RequestParam String name,@RequestParam int pk,@RequestParam String value)
	{
			
		return updateTable(TableConstant.LOT_BOOK_TABLE,name,value,TableConstant.LOT_BOOK_PK_Column,pk);			
		
	}
	@RequestMapping(value="/managedata/updateChallanLotItem",method=RequestMethod.POST)
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int updateChallanLotItem(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			int noOfRowsUpdated=updateTable(TableConstant.LOT_BOOK_TABLE,name,value,TableConstant.LOT_BOOK_PK_Column,pk);			
			
			String deleteSubLotFromFareBook="delete from fare_book where sub_lot_id in(select sub_lot_id "
					+ "from sub_lot_book where lot_id ="+pk+")";
			String deleteSubLotFromCollectionBook="delete from fare_collection where fare_id in(select fare_id "
					+ " from fare_book where sub_lot_id in(select sub_lot_id "
					+ "from sub_lot_book where lot_id="+pk+"))";
			
			int noOfRowsDeleted2=deleteTableRecords(deleteSubLotFromCollectionBook);
			int noOfRowsDeleted1=deleteTableRecords(deleteSubLotFromFareBook);
			
			return noOfRowsUpdated;
		
	}
	
	@RequestMapping(value="/managedata/updateChallanLotBox",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanLotBox(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			int noOfRowsUpdated=updateTable(TableConstant.LOT_BOOK_TABLE,name,value,TableConstant.LOT_BOOK_PK_Column,pk);			
			
			String deleteSubLotFromFareBook="delete from fare_book where sub_lot_id in(select sub_lot_id "
					+ "from sub_lot_book where lot_id ="+pk+")";
			String deleteSubLotFromCollectionBook="delete from fare_collection where fare_id in(select fare_id "
					+ " from fare_book where sub_lot_id in(select sub_lot_id "
					+ "from sub_lot_book where lot_id="+pk+"))";
			
			int noOfRowsDeleted2=deleteTableRecords(deleteSubLotFromCollectionBook);
			int noOfRowsDeleted1=deleteTableRecords(deleteSubLotFromFareBook);
			
			return noOfRowsUpdated;
		
	}
	@RequestMapping(value="/managedata/updateChallanLotTotQty",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanLotTotQty(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			
			
			
			Integer total_qty=subLotBookRepository.getTotalQtyForLotId(pk);
			
			if(total_qty !=null && total_qty>Integer.parseInt(value))
			{
				String deleteSubLotFromLotBook="delete from sub_lot_book where lot_id="+pk;
				String deleteSubLotFromFareBook="delete from fare_book where sub_lot_id in(select sub_lot_id "
						+ "from sub_lot_book where lot_id ="+pk+")";
				String deleteSubLotFromCollectionBook="delete from fare_collection where fare_id in(select fare_id "
						+ " from fare_book where sub_lot_id in(select sub_lot_id "
						+ "from sub_lot_book where lot_id="+pk+"))";
				
				int noOfRowsDeleted3=deleteTableRecords(deleteSubLotFromCollectionBook);
				int noOfRowsDeleted2=deleteTableRecords(deleteSubLotFromFareBook);
				int noOfRowsDeleted1=deleteTableRecords(deleteSubLotFromLotBook);
					
			}
			
			int noOfRowsUpdated=updateTable(TableConstant.LOT_BOOK_TABLE,name,value,TableConstant.LOT_BOOK_PK_Column,pk);			
			
			return noOfRowsUpdated;
		
	}
	
	@RequestMapping(value="/managedata/updateChallanLotReceiver",method=RequestMethod.POST)
	@ResponseBody
	public int updateChallanLotReceiver(@RequestParam String name,@RequestParam int pk,@RequestParam String value) throws Exception
	{
			int noOfRowsUpdated=updateTable2(TableConstant.LOT_BOOK_TABLE,name,value,TableConstant.LOT_BOOK_PK_Column,pk);			
			
			return noOfRowsUpdated;
		
	}
	
	public int updateTable(String table,String columnName,String value,String primaryKey,int primaryKeyVal)
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="update "+table+" set "+columnName+"="+value+" where "+primaryKey+"="+primaryKeyVal;
		return jdbcTemplate.update(sql);
	}
	
	public int updateTable2(String table,String columnName,String value,String primaryKey,int primaryKeyVal)
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="update "+table+" set "+columnName+"='"+value+"' where "+primaryKey+"="+primaryKeyVal;
		return jdbcTemplate.update(sql);
	}
	
	public int deleteTableRecords(String sql)
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		return jdbcTemplate.update(sql);
	}
	
	

}
