package com.spring.boot.security.forms.data;

import java.sql.Date;

public class FareCalcVO {
	
	private int subLotId;
	private Date receivingDate;
	private String truckNo;
	private String sourceName;
	private String agentDestName;
	private String agentName;
	private String agentMark;
	private String itemName;
	private String boxName;
	private int boxWt;
	private int totQty;
	private double farePerBox
	;
	
	public int getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(int subLotId) {
		this.subLotId = subLotId;
	}
	public Date getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getAgentDestName() {
		return agentDestName;
	}
	public void setAgentDestName(String agentDestName) {
		this.agentDestName = agentDestName;
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
	public double getFarePerBox() {
		return farePerBox;
	}
	public void setFarePerBox(double farePerBox) {
		this.farePerBox = farePerBox;
	}

}
