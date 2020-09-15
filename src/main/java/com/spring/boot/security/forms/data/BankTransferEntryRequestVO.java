package com.spring.boot.security.forms.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


public class BankTransferEntryRequestVO {
	
	private String bankName[];
	private String acctHldr[];
	private double trnsfrAmt[];
	private String referecneDesc[];
	private Date ledgerDt[];
	
	public String[] getBankName() {
		return bankName;
	}
	public void setBankName(String[] bankName) {
		this.bankName = bankName;
	}
	public String[] getAcctHldr() {
		return acctHldr;
	}
	public void setAcctHldr(String[] acctHldr) {
		this.acctHldr = acctHldr;
	}
	public double[] getTrnsfrAmt() {
		return trnsfrAmt;
	}
	public void setTrnsfrAmt(double[] trnsfrAmt) {
		this.trnsfrAmt = trnsfrAmt;
	}
	public String[] getReferecneDesc() {
		return referecneDesc;
	}
	public void setReferecneDesc(String[] referecneDesc) {
		this.referecneDesc = referecneDesc;
	}
	public Date[] getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date[] ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	
	
}
