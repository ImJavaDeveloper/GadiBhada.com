package com.spring.boot.security.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fare_collection")
public class FareCollection {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="collection_id")
	private int collectionId;
	@Column(name="fare_id")
	private int fareId;
	@Column(name="sub_lot_id")
	private int subLotId;
	@Column(name="tot_payment")
	private double totPayment;
	@Column(name="pymt_dt")
	private Date pymtDt;
	@Column(name="debit_amt")
	private double debitAmt;
	@Column(name="extra_fare")
	private double extraFare;
	
	
	public int getFareId() {
		return fareId;
	}
	public void setFareId(int fareId) {
		this.fareId = fareId;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	
	public int getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(int subLotId) {
		this.subLotId = subLotId;
	}
	public double getTotPayment() {
		return totPayment;
	}
	public void setTotPayment(double totPayment) {
		this.totPayment = totPayment;
	}
	public Date getPymtDt() {
		return pymtDt;
	}
	public void setPymtDt(Date pymtDt) {
		this.pymtDt = pymtDt;
	}
	public double getDebitAmt() {
		return debitAmt;
	}
	public void setDebitAmt(double debitAmt) {
		this.debitAmt = debitAmt;
	}
	public double getExtraFare() {
		return extraFare;
	}
	public void setExtraFare(double extraFare) {
		this.extraFare = extraFare;
	}
	
	
	
}
