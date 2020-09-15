package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_transfer")
public class BankTransferEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transfer_id")
	private int transferId;
	@Column(name="bank_name")
	private String bankName;
	@Column(name="account_holder")
	private String accountHolder;
	@Column(name="amount")
	private double transferAmt;
	@Column(name="refernce_desc")
	private String refDesc;
	@Column(name="ledger_dt")
	private Date ledgerDt;
	
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public double getTransferAmt() {
		return transferAmt;
	}
	public void setTransferAmt(double transferAmt) {
		this.transferAmt = transferAmt;
	}
	
	public Date getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	public String getRefDesc() {
		return refDesc;
	}
	public void setRefDesc(String refDesc) {
		this.refDesc = refDesc;
	}
	

}
