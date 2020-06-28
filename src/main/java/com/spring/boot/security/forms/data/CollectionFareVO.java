package com.spring.boot.security.forms.data;

import java.sql.Date;

public class CollectionFareVO {
	
	private int fareId;
	private int subLotId;
	private String truckNo;
	private Date recievedDt;
	private String itemName;
	private String boxName;
	private int boxWt;
	private int totQty;
	private String agentName;
	private String agentMark;
	private String agentDestName;
	private double totBal;
	private double totFare;
	private double totPymt;
	private double totDebit;
	
	public int getFareId() {
		return fareId;
	}
	public void setFareId(int fareId) {
		this.fareId = fareId;
	}
	public int getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(int subLotId) {
		this.subLotId = subLotId;
	}

	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public Date getRecievedDt() {
		return recievedDt;
	}
	public void setRecievedDt(Date recievedDt) {
		this.recievedDt = recievedDt;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public int getBoxWt() {
		return boxWt;
	}
	public void setBoxWt(int boxWt) {
		this.boxWt = boxWt;
	}
	public int getTotQty() {
		return totQty;
	}
	public void setTotQty(int totQty) {
		this.totQty = totQty;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentMark() {
		return agentMark;
	}
	public void setAgentMark(String agentMark) {
		this.agentMark = agentMark;
	}
	public String getAgentDestName() {
		return agentDestName;
	}
	public void setAgentDestName(String agentDestName) {
		this.agentDestName = agentDestName;
	}
	
	public double getTotFare() {
		return totFare;
	}
	public void setTotFare(double totFare) {
		this.totFare = totFare;
	}
	

	public double getTotBal() {
		return totBal;
	}
	public void setTotBal(double totBal) {
		this.totBal = totBal;
	}

	public double getTotPymt() {
		return totPymt;
	}
	public void setTotPymt(double totPymt) {
		this.totPymt = totPymt;
	}
	public double getTotDebit() {
		return totDebit;
	}
	public void setTotDebit(double totDebit) {
		this.totDebit = totDebit;
	}

}
