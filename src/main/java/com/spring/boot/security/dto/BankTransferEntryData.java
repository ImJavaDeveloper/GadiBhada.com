package com.spring.boot.security.dto;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.security.entity.BankTransferEntry;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.repository.BankTransferRespository;

@Service
public class BankTransferEntryData {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	BankTransferRespository bnkTrnsfrRepo;
	
	
	public double getTotBankTransferAmtByDate(String ledgerDt)
	{
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select case when sum(amount) is null then 0.0 else sum(amount)   end   from bank_transfer where ledger_dt=?";
		Object input[]=new Object[]{ledgerDt};
		return jdbc.queryForObject(sql,input, double.class);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	public void saveBankTransferEntry(List<BankTransferEntry> listBankTransferEntry) throws DataBaseException
	{
		for (int i = 0; i < listBankTransferEntry.size(); i++) {
			
			BankTransferEntry saveBnkTrsnfrVO=bnkTrnsfrRepo.save(listBankTransferEntry.get(i));
			if(saveBnkTrsnfrVO==null) {
				
				throw new DataBaseException("Exception found while saving data into Bank Transfer Table ","management");
			
		}
		}
	}

}
