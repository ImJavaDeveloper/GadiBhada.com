package com.spring.boot.security.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.FareCollection;
import com.spring.boot.security.repository.FareCollectionRepository;


@Service
public class FareCollectionData {

	@Autowired
	FareCollectionRepository fareCollectionRepository;
	@Autowired
	DataSource datasource;
	
	public FareCollection saveFareCollectionData(FareCollection fareCollection)	{
		return fareCollectionRepository.save(fareCollection);
	}
	
	public double totalCollectionsAmtByLedgerDt(String ledgerDt)	{
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="Select case when sum(tot_Payment) is null then 0 else sum(tot_Payment) end from  fare_collection  where ledger_dt=?";
		Object[] inputs = new Object[] {ledgerDt};
        return jdbcTemplate.queryForObject(sql, inputs, double.class); 		
 
	}
}
