package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.spring.boot.security.entity.id.TruckLedgerId;

@Entity 
@IdClass(TruckLedgerId.class)
@Table(name="truck_ledger")
public class TruckLedger {
	
	@Id
	@Column(name="truck_no")
	private String  truckNo;
	
	@Id
	@Column(name="truck_start_dt")
	private Date  truckStratDt;
	@Column(name="truck_end_dt")
	private Date  truckEndDt;
	@Column(name="source_id")
	private int  source_id;
	@Column(name="destination_id")
	private int  destinationId;
	@Column(name="adv_fare")
	private double  advFare;
	@Column(name="tot_fare")
	private double  totFare;
	@Column(name="prize_amt")
	private double  prizeAmt;
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public Date getTruckStratDt() {
		return truckStratDt;
	}
	public void setTruckStratDt(Date truckStratDt) {
		this.truckStratDt = truckStratDt;
	}
	public Date getTruckEndDt() {
		return truckEndDt;
	}
	public void setTruckEndDt(Date truckEndDt) {
		this.truckEndDt = truckEndDt;
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public double getAdvFare() {
		return advFare;
	}
	public void setAdvFare(double advFare) {
		this.advFare = advFare;
	}
	public double getTotFare() {
		return totFare;
	}
	public void setTotFare(double totFare) {
		this.totFare = totFare;
	}
	public double getPrizeAmt() {
		return prizeAmt;
	}
	public void setPrizeAmt(double prizeAmt) {
		this.prizeAmt = prizeAmt;
	}
	
	
	

}
