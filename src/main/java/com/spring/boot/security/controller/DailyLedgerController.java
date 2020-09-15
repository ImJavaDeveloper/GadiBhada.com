package com.spring.boot.security.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.dto.BankTransferEntryData;
import com.spring.boot.security.dto.MoneyWithdrawlEntryData;
import com.spring.boot.security.dto.OtherExpenseEntryData;
import com.spring.boot.security.entity.BankTransferEntry;
import com.spring.boot.security.entity.DailyLedgerBook;
import com.spring.boot.security.entity.MoneyWithdrawlEntry;
import com.spring.boot.security.entity.OtherExpenseEntry;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.BankTransferEntryRequestVO;
import com.spring.boot.security.forms.data.MoneyWithdrawlEntryReqVO;
import com.spring.boot.security.forms.data.OtherExpenseEntryReqVO;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.repository.DailyLedgerBookRepository;

@Controller
@RequestMapping(value="/gaadibhada")
public class DailyLedgerController {
	
	@Autowired
	BankTransferEntryData bankTrnsfrData;
	@Autowired
	MoneyWithdrawlEntryData moneyWithDrwlData;
	@Autowired
	OtherExpenseEntryData othrExpnsData;
	
	@Autowired
	DailyLedgerBookRepository dailyLedgerBookRepo;
	
	@RequestMapping(value="/dataentry/dailyledger/bnktrnsfrByLedgerDt",method=RequestMethod.POST)
	@ResponseBody
	public double getTotBankTransferAmtByDate(@RequestParam String ledgerDt)
	{
		return bankTrnsfrData.getTotBankTransferAmtByDate(ledgerDt);
		
	}
	
	@RequestMapping(value="/dataentry/dailyledger/moneyWithdrwlByLedgerDt",method=RequestMethod.POST)
	@ResponseBody
	public double getMoneyWithdrwlByDate(@RequestParam String ledgerDt)
	{
		return moneyWithDrwlData.getTotWithdrawlAmtByDate(ledgerDt);
		
	}
	@RequestMapping(value="/dataentry/dailyledger/otherExpnsByLedgerDt",method=RequestMethod.POST)
	@ResponseBody
	public double getOtherExpnsByDate(@RequestParam String ledgerDt)
	{
		return othrExpnsData.getTotalOtherExpenseByDate(ledgerDt);
		
	}
	
	@RequestMapping(value="/dataentry/dailyledger/saveBankTrsnfrEntry",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String saveBankTrsnfrEntry(@RequestBody BankTransferEntryRequestVO bnkTrnsfrdata)
	{
		List<BankTransferEntry> listBnkTrnsfrEntry=new ArrayList<>();
		for(int i=0;i<bnkTrnsfrdata.getBankName().length;i++)
		{
			BankTransferEntry bankTrsnfrEntry=new BankTransferEntry();
			bankTrsnfrEntry.setBankName(bnkTrnsfrdata.getBankName()[i]);
			bankTrsnfrEntry.setAccountHolder(bnkTrnsfrdata.getAcctHldr()[i]);
            bankTrsnfrEntry.setTransferAmt(bnkTrnsfrdata.getTrnsfrAmt()[i]);
			bankTrsnfrEntry.setRefDesc(bnkTrnsfrdata.getReferecneDesc()[i]);
			bankTrsnfrEntry.setLedgerDt(bnkTrnsfrdata.getLedgerDt()[i]);
			listBnkTrnsfrEntry.add(bankTrsnfrEntry);
		}
		try {
			bankTrnsfrData.saveBankTransferEntry(listBnkTrnsfrEntry);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
			return "{\"msg\":\"Failure !! Date Is Not Saved\"}";
		}
		return "{\"msg\":\"success\"}";
	
	}
	
	@RequestMapping(value="/dataentry/dailyledger/saveMoneyWithdrawlEntry",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String saveMoneyWithdrawlEntry(@RequestBody MoneyWithdrawlEntryReqVO mnyWthdrwldata)
	{
		List<MoneyWithdrawlEntry> listMnyWthdawlEntry=new ArrayList<>();
		for(int i=0;i<mnyWthdrwldata.getWithdrawlBy().length;i++)
		{
			MoneyWithdrawlEntry mnyWithDrwlEntry=new MoneyWithdrawlEntry();
			mnyWithDrwlEntry.setWithdrawlBy(mnyWthdrwldata.getWithdrawlBy()[i]);
			mnyWithDrwlEntry.setWithdrawlAmt(mnyWthdrwldata.getTotWithDrawlAmt()[i]);
			mnyWithDrwlEntry.setWithdrawlMode(mnyWthdrwldata.getWithdrawlMode()[i]);
			mnyWithDrwlEntry.setPaymentBy(mnyWthdrwldata.getPaymentBy()[i]);
			mnyWithDrwlEntry.setLedger_dt(mnyWthdrwldata.getLedgerDt()[i]);
			
			listMnyWthdawlEntry.add(mnyWithDrwlEntry);
		}
		try {
			moneyWithDrwlData.saveMoneyWithDrawlEntry(listMnyWthdawlEntry);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
			return "{\"msg\":\"Failure !! Date Is Not Saved\"}";
		}
		return "{\"msg\":\"success\"}";
	
	}
	
	@RequestMapping(value="/dataentry/dailyledger/saveOtherExpnsEntry",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public String saveOtherExpnsEntry(@RequestBody OtherExpenseEntryReqVO otherExpnsEntries)
	{
		List<OtherExpenseEntry> listOthrExpnsntry=new ArrayList<>();
		for(int i=0;i<otherExpnsEntries.getExpenseDesc().length;i++)
		{
			OtherExpenseEntry otherExpnsEntry=new OtherExpenseEntry();
			otherExpnsEntry.setExpense_Desc(otherExpnsEntries.getExpenseDesc()[i]);
			otherExpnsEntry.setExpense_Amt(otherExpnsEntries.getExpenseAmt()[i]);
			otherExpnsEntry.setExpense_By(otherExpnsEntries.getExpenseBy()[i]);
			otherExpnsEntry.setLedger_dt(otherExpnsEntries.getLedgerDt()[i]);
			
			listOthrExpnsntry.add(otherExpnsEntry);
		}
		try {
			othrExpnsData.saveOtherExpnsEntry(listOthrExpnsntry);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
			return "{\"msg\":\"Failure !! Date Is Not Saved\"}";
		}
		return "{\"msg\":\"success\"}";
	
	}
	
	@RequestMapping(value="/dataentry/dailyledger/saveDailyLedger",method=RequestMethod.POST)
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public String saveDailyLedger(@RequestParam String ledgerDt,@RequestParam double truckPymtTot,@RequestParam double bnkTransferTot,@RequestParam double localFareTot
			,@RequestParam double moneyWithDrawlTot,@RequestParam double otherExpenseTot,@RequestParam double thekedariAmt,@RequestParam double todayCollAmt,@RequestParam double todayBal) throws Exception
	{
		
		String msg="";
		DailyLedgerBook isDailyLedgerAvailable=dailyLedgerBookRepo.findByledgerDt(ledgerDt);
		
		if(isDailyLedgerAvailable !=null)
		{
			int ledgerId=isDailyLedgerAvailable.getLedgerId();
			System.out.println(ledgerId);
			int noOfRowsUpdated=dailyLedgerBookRepo.updateLedgerBook(ledgerDt, truckPymtTot, bnkTransferTot, localFareTot, moneyWithDrawlTot, otherExpenseTot, thekedariAmt, todayCollAmt, todayBal, ledgerId);
	        if(noOfRowsUpdated>0)
	        	msg="Daily Ledger Has Been Updated";
		}
		else {
		DailyLedgerBook dailyLedger=new DailyLedgerBook();
		dailyLedger.setLedgerDt(DataHelper.stringToDate(ledgerDt));
		dailyLedger.setTotTruckPymt(truckPymtTot);
		dailyLedger.setTotBnkTrnsfr(bnkTransferTot);
		dailyLedger.setTotLocalFare(localFareTot);
		dailyLedger.setTotLocalFare(localFareTot);
		dailyLedger.setTotMnyWithdrwl(moneyWithDrawlTot);
		dailyLedger.setTotOthrExpns(otherExpenseTot);
		dailyLedger.setTotThekedari(thekedariAmt);
		dailyLedger.setTotCollections(todayCollAmt);
		dailyLedger.setTotLedger_bal(todayBal);
		
		DailyLedgerBook savedDailyLedgerBook=new DailyLedgerBook();
		savedDailyLedgerBook=dailyLedgerBookRepo.save(dailyLedger);
		if(savedDailyLedgerBook==null) {
	
			msg="Exception found while saving data into Daily Ledger";
			throw new DataBaseException("Exception found while saving data into Daily Ledger","management");
		
		}
		else
			msg="Success";
		}
		
		return msg;


		
			
		
		
	}

}
