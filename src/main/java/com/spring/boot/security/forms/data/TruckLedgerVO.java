package com.spring.boot.security.forms.data;

public class TruckLedgerVO {
	

	private String truckNo;
	private String stratDt;
	private String FromToWhere;
	private String endDt;
	private double advFare;
	private double prizeFare;
	private double totFare;
	private double totPymt;
	private double totBal;
	
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getStratDt() {
		return stratDt;
	}
	public void setStratDt(String stratDt) {
		this.stratDt = stratDt;
	}
	public String getFromToWhere() {
		return FromToWhere;
	}
	public void setFromToWhere(String fromToWhere) {
		FromToWhere = fromToWhere;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public double getAdvFare() {
		return advFare;
	}
	public void setAdvFare(double advFare) {
		this.advFare = advFare;
	}
	public double getPrizeFare() {
		return prizeFare;
	}
	public void setPrizeFare(double prizeFare) {
		this.prizeFare = prizeFare;
	}
	public double getTotFare() {
		return totFare;
	}
	public void setTotFare(double totFare) {
		this.totFare = totFare;
	}
	public double getTotPymt() {
		return totPymt;
	}
	public void setTotPymt(double totPymt) {
		this.totPymt = totPymt;
	}
	public double getTotBal() {
		return totBal;
	}
	public void setTotBal(double totBal) {
		this.totBal = totBal;
	}

}
