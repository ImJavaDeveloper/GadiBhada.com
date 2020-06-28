package com.spring.boot.security.controller;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.constant.TableConstant;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.repository.FareBookRespository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.SubLotBookRepository;

@Controller
@RequestMapping("/gadibhada")
public class DeleteFormsDataController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(DeleteFormsDataController.class);
	
	@Autowired
	LotBookRepository lotBookRepository;
	@Autowired
	SubLotBookRepository subLotBookRepository;
	@Autowired
	FareBookRespository fareBookRespository;
	@Autowired
	DataSource datasource;
	
	@RequestMapping(value="/managedata/deleteLotFromChallan",method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	@ResponseBody
	public String deleteLotFromChallan(@RequestParam int lotId) 
	{
		//SubLotBook
		
		try {
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		
		String deletFareForLotsql="delete from "+TableConstant.FARE_BOOK_TABLE+" where sub_lot_id in(select sub_lot_id from "+TableConstant.LOT_BOOK_TABLE+" "
				+ "Where lot_id=?"+")";
		String deletCollForLotsql="delete from "+TableConstant.COLLECTION_BOOK_TABLE+" where sub_lot_id in(select sub_lot_id from "+TableConstant.LOT_BOOK_TABLE+" "
				+ "Where lot_id=?"+")";
		int n1=jdbcTemplate.update(deletFareForLotsql,lotId);
		int n2=jdbcTemplate.update(deletCollForLotsql,lotId);
		System.out.println("n1="+n1);
		System.out.println("n2="+n2);
		lotBookRepository.deleteById(lotId);
		subLotBookRepository.deletebyLotId(lotId);
		
		}
		catch(DataAccessException e)
		{
			LOGGER.error("Error while deleting the data from table: "+e.getMessage());
		}
		
		return "Success";
		
	}
	


}
