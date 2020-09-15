package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="other_expense")
public class OtherExpenseEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="expense_Id")
	private int expense_Id;
	@Column(name="expense_Desc")
	private String expense_Desc;
	@Column(name="expense_Amt")
    private double expense_Amt;
	@Column(name="expense_By")
    private String expense_By;
	@Column(name="ledger_dt")
    private Date ledger_dt;
	public int getExpense_Id() {
		return expense_Id;
	}
	public void setExpense_Id(int expense_Id) {
		this.expense_Id = expense_Id;
	}
	public String getExpense_Desc() {
		return expense_Desc;
	}
	public void setExpense_Desc(String expense_Desc) {
		this.expense_Desc = expense_Desc;
	}
	public double getExpense_Amt() {
		return expense_Amt;
	}
	public void setExpense_Amt(double expense_Amt) {
		this.expense_Amt = expense_Amt;
	}
	public String getExpense_By() {
		return expense_By;
	}
	public void setExpense_By(String expense_By) {
		this.expense_By = expense_By;
	}
	public Date getLedger_dt() {
		return ledger_dt;
	}
	public void setLedger_dt(Date ledger_dt) {
		this.ledger_dt = ledger_dt;
	}
    
	
	
}
