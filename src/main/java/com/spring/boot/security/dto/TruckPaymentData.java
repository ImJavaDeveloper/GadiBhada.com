package com.spring.boot.security.dto;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.boot.security.repository.FareCollectionRepository;
import com.spring.boot.security.repository.TruckPymtRepository;

@Service
public class TruckPaymentData {
	
	@Autowired
	TruckPymtRepository truckPymtRepository;
	@Autowired
	DataSource datasource;
	
public double totalTruckPymtByDt(String ledgerDt)	{
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="Select case when sum(pymt_amt) is null then 0 else sum(pymt_amt) end from  truck_pymt  where ledger_dt=?";
		Object[] inputs = new Object[] {ledgerDt};
        return jdbcTemplate.queryForObject(sql, inputs, double.class); 		
 
	}

}
