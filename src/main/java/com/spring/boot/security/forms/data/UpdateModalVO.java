package com.spring.boot.security.forms.data;

public class UpdateModalVO {
	
	private int lotId;
	private int tot_qty;
	private int tot_avl;
	private String receiver;
	public int getLotId() {
		return lotId;
	}
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}
	public int getTot_qty() {
		return tot_qty;
	}
	public void setTot_qty(int tot_qty) {
		this.tot_qty = tot_qty;
	}
	public int getTot_avl() {
		return tot_avl;
	}
	public void setTot_avl(int tot_avl) {
		this.tot_avl = tot_avl;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}
