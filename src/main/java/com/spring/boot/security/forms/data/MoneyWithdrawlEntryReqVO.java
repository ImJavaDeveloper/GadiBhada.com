package com.spring.boot.security.forms.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


public class MoneyWithdrawlEntryReqVO {
	
	private String withdrawlBy[];
	private double totWithDrawlAmt[];
	private String withdrawlMode[];
	private String paymentBy[];
	private Date ledgerDt[];
	
	public String[] getWithdrawlBy() {
		return withdrawlBy;
	}
	public void setWithdrawlBy(String[] withdrawlBy) {
		this.withdrawlBy = withdrawlBy;
	}
	public double[] getTotWithDrawlAmt() {
		return totWithDrawlAmt;
	}
	public void setTotWithDrawlAmt(double[] totWithDrawlAmt) {
		this.totWithDrawlAmt = totWithDrawlAmt;
	}
	public String[] getWithdrawlMode() {
		return withdrawlMode;
	}
	public void setWithdrawlMode(String[] withdrawlMode) {
		this.withdrawlMode = withdrawlMode;
	}
	public String[] getPaymentBy() {
		return paymentBy;
	}
	public void setPaymentBy(String[] paymentBy) {
		this.paymentBy = paymentBy;
	}
	public Date[] getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date[] ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	
	
	
}
