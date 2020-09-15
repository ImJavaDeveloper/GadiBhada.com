package com.spring.boot.security.forms.data;

public class LocalFareDetailsVO {

	private String subLotId;
	private String itemName;
	private String boxType;
	private String totalQty;
	private String totalWt;
	private String agent;
	private String agentDest;

	public String getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(String subLotId) {
		this.subLotId = subLotId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	public String getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}
	public String getTotalWt() {
		return totalWt;
	}
	public void setTotalWt(String totalWt) {
		this.totalWt = totalWt;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getAgentDest() {
		return agentDest;
	}
	public void setAgentDest(String agentDest) {
		this.agentDest = agentDest;
	}

}
