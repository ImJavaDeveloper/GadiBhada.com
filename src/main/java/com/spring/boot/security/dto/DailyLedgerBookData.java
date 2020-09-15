package com.spring.boot.security.dto;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring.boot.security.repository.DailyLedgerBookRepository;
import com.spring.boot.security.repository.TruckPymtRepository;

@Service
public class DailyLedgerBookData {

	@Autowired
	DailyLedgerBookRepository dailyLedgerBookRepo;
	@Autowired
	DataSource datasource;
	
public double getTotalOpeningBal(String ledgerDt)	{
		
	double val;	
	JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		String sql="SELECT case when tot_ledger_bal is null or tot_ledger_bal=\"\" then 0 else tot_ledger_bal end as openingBal FROM daily_ledger_book where ledger_dt=(select max(ledger_dt) from daily_ledger_book\r\n" + 
				" where ledger_dt<?)";
		Object[] inputs = new Object[] {ledgerDt};
        try
        {
        	val=jdbcTemplate.queryForObject(sql, inputs, double.class); 		
        }
        catch(EmptyResultDataAccessException ex)
        {
        	val=0;
        }
		return val;
 
	}

}
