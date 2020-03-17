package com.spring.boot.security.forms.data;

public class LotBooksByChallanIdVO {
	
	private int lotId;
	private int traderId;
	private String traderName;
	private String traderMark;
	private int itemId;
	private String itemName;
	private int boxId;
	private String boxName;
	private int boxWt;
	private int totQty;
	private int totWt;
	private String receiver;
	
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
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
	public int getTotWt() {
		return totWt;
	}
	public void setTotWt(int totWt) {
		this.totWt = totWt;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getTraderId() {
		return traderId;
	}
	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBoxId() {
		return boxId;
	}
	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}
	public int getLotId() {
		return lotId;
	}
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}
	public String getTraderMark() {
		return traderMark;
	}
	public void setTraderMark(String traderMark) {
		this.traderMark = traderMark;
	}
	

}
