package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="money_withdrawl")
public class MoneyWithdrawlEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="withdrawl_Id")
	private int withdrawlId;
	@Column(name="withdrawl_By")
	private String withdrawlBy;
	@Column(name="withdrawl_Amt")
    private double withdrawlAmt;
	@Column(name="withdrawl_Mode")
    private String withdrawlMode;
    @Column(name="payment_by")
    private String paymentBy;
	@Column(name="ledger_dt")
    private Date ledger_dt;
    
	public int getWithdrawlId() {
		return withdrawlId;
	}
	public void setWithdrawlId(int withdrawlId) {
		this.withdrawlId = withdrawlId;
	}
	public String getWithdrawlBy() {
		return withdrawlBy;
	}
	public void setWithdrawlBy(String withdrawlBy) {
		this.withdrawlBy = withdrawlBy;
	}
	public double getWithdrawlAmt() {
		return withdrawlAmt;
	}
	public void setWithdrawlAmt(double withdrawlAmt) {
		this.withdrawlAmt = withdrawlAmt;
	}
	public String getWithdrawlMode() {
		return withdrawlMode;
	}
	public void setWithdrawlMode(String withdrawlMode) {
		this.withdrawlMode = withdrawlMode;
	}
	public String getPaymentBy() {
		return paymentBy;
	}
	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}
	public Date getLedger_dt() {
		return ledger_dt;
	}
	public void setLedger_dt(Date ledger_dt) {
		this.ledger_dt = ledger_dt;
	}
	
}
