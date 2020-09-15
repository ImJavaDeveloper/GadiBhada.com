package com.spring.boot.security.forms.data;

import java.util.Date;

public class CollectionPymtPerLedgerDt {
	
	private int subLotId;
	private Date ledgerDt;
	private double totAmt;
	
	public int getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(int subLotId) {
		this.subLotId = subLotId;
	}
	public Date getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	public double getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(double totAmt) {
		this.totAmt = totAmt;
	}
	

}
