package com.spring.boot.security.dto;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.security.entity.BankTransferEntry;
import com.spring.boot.security.entity.MoneyWithdrawlEntry;
import com.spring.boot.security.entity.OtherExpenseEntry;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.repository.MoneyWithdrawlRepository;
import com.spring.boot.security.repository.OtherExpenseRespository;

@Service
public class OtherExpenseEntryData {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	OtherExpenseRespository otherExpnsRepo;
	
	public double getTotalOtherExpenseByDate(String ledgerDt)
	{
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select case when sum(expense_Amt) is null then 0.0 else sum(expense_Amt) end   from other_expense where ledger_dt=?";
		Object input[]=new Object[]{ledgerDt};
		return jdbc.queryForObject(sql,input, double.class);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	public void saveOtherExpnsEntry(List<OtherExpenseEntry> listOtherExpnsEntry) throws DataBaseException
	{
		for (int i = 0; i < listOtherExpnsEntry.size(); i++) {
			
			OtherExpenseEntry saveOtherExpnsVO=otherExpnsRepo.save(listOtherExpnsEntry.get(i));
			if(saveOtherExpnsVO==null) {
				
				throw new DataBaseException("Exception found while saving data into Other Expense Table ","management");
			
		}
		}
	}
	

}
