package com.spring.boot.security.dto;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.security.entity.BankTransferEntry;
import com.spring.boot.security.entity.MoneyWithdrawlEntry;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.repository.BankTransferRespository;
import com.spring.boot.security.repository.MoneyWithdrawlRepository;

@Service
public class MoneyWithdrawlEntryData {
	
	
	@Autowired
	DataSource dataSource;
	@Autowired
	MoneyWithdrawlRepository mnyWthDrwlRepo;
	
	public double getTotWithdrawlAmtByDate(String ledgerDt)
	{
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select case when sum(withdrawl_Amt) is null then 0.0 else sum(withdrawl_Amt) end   from money_withdrawl where ledger_dt=?";
		Object input[]=new Object[]{ledgerDt};
		return jdbc.queryForObject(sql,input, double.class);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	public void saveMoneyWithDrawlEntry(List<MoneyWithdrawlEntry> listMnyWthdawlEntry) throws DataBaseException
	{
		for (int i = 0; i < listMnyWthdawlEntry.size(); i++) {
			
			MoneyWithdrawlEntry saveMnyWithdrwlVO=mnyWthDrwlRepo.save(listMnyWthdawlEntry.get(i));
			if(saveMnyWithdrwlVO==null) {
				
				throw new DataBaseException("Exception found while saving data into Money Withdrawl Table ","management");
			
		}
		}
	}

}
