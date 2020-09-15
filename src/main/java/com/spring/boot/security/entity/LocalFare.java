package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="local_fare")
public class LocalFare {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lfare_id")
	private int lFareId;
	@Column(name="ledger_dt")
	private String ledgerDt;
	@Column(name="local_driver")
	private String localDriver;
	@Column(name="tot_local_fare")
	private double totLocalFare;
	@Column(name="from_To_Where")
	private String fromToWhere;
	@Column(name="tot_wt")
	private int totWt;
	@Column(name="sub_lot_id_array")
	private String subLotIdArray;
	
	
	public int getlFareId() {
		return lFareId;
	}
	public void setlFareId(int lFareId) {
		this.lFareId = lFareId;
	}
	public String getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(String ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	public String getLocalDriver() {
		return localDriver;
	}
	public void setLocalDriver(String localDriver) {
		this.localDriver = localDriver;
	}
	public double getTotLocalFare() {
		return totLocalFare;
	}
	public void setTotLocalFare(double totLocalFare) {
		this.totLocalFare = totLocalFare;
	}
	public String getFromToWhere() {
		return fromToWhere;
	}
	public void setFromToWhere(String fromToWhere) {
		this.fromToWhere = fromToWhere;
	}
	public int getTotWt() {
		return totWt;
	}
	public void setTotWt(int totWt) {
		this.totWt = totWt;
	}
	public String getSubLotIdArray() {
		return subLotIdArray;
	}
	public void setSubLotIdArray(String subLotIdArray) {
		this.subLotIdArray = subLotIdArray;
	}

}
