package com.spring.boot.security.forms.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


public class OtherExpenseEntryReqVO {
	
	private String expenseDesc[];
	private double expenseAmt[];
	private String expenseBy[];
	private Date ledgerDt[];
	
	public String[] getExpenseDesc() {
		return expenseDesc;
	}
	public void setExpenseDesc(String[] expenseDesc) {
		this.expenseDesc = expenseDesc;
	}
	public double[] getExpenseAmt() {
		return expenseAmt;
	}
	public void setExpenseAmt(double[] expenseAmt) {
		this.expenseAmt = expenseAmt;
	}
	public String[] getExpenseBy() {
		return expenseBy;
	}
	public void setExpenseBy(String[] expenseBy) {
		this.expenseBy = expenseBy;
	}
	public Date[] getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date[] ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	
	
	
}
