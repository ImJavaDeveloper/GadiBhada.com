package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="truck_pymt")
public class TruckPymt {
	
	@Id
	@Column(name="pymt_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  pymtID;
    @Column(name="truck_no")
	private String  truckNo;
	@Column(name="t_start_dt")
	private Date tStartDt;
	@Column(name="pymt_amt")
	private double pymtAmt;
	@Column(name="pymt_dt")
	private Date pymtDt;
	@Column(name="ledger_dt")
	private Date ledgerDt;
	
	
	public int getPymtID() {
		return pymtID;
	}
	public void setPymtID(int pymtID) {
		this.pymtID = pymtID;
	}
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public Date gettStartDt() {
		return tStartDt;
	}
	public void settStartDt(Date tStartDt) {
		this.tStartDt = tStartDt;
	}
	public double getPymtAmt() {
		return pymtAmt;
	}
	public void setPymtAmt(double pymtAmt) {
		this.pymtAmt = pymtAmt;
	}
	public Date getPymtDt() {
		return pymtDt;
	}
	public void setPymtDt(Date pymtDt) {
		this.pymtDt = pymtDt;
	}
	public Date getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	

}
