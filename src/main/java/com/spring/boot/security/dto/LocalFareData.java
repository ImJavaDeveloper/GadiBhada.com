package com.spring.boot.security.dto;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.LocalFare;
import com.spring.boot.security.repository.LocalFareRepository;

@Service
public class LocalFareData {
	
	
	@Autowired
	LocalFareRepository localFareRepository;
	@Autowired
	DataSource datasource;
	
public double getTodayLocalFare(String ledgerDt)	{
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="Select case when sum(tot_local_fare) is null then 0 else sum(tot_local_fare) end from  local_fare  where ledger_dt=?";
		Object[] inputs = new Object[] {ledgerDt};
		
        return jdbcTemplate.queryForObject(sql, inputs, double.class); 		
 
	}
public boolean checkLocalVehicleBooked(String ledger_dt,String local_driver)
{
	LocalFare localFare=localFareRepository.findByLocalDriverByDt(ledger_dt, local_driver);
	
	if(localFare == null)
		return false;
		else
			return true;
}

}
