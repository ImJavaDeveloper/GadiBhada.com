package com.spring.boot.security.forms.data;

public class CollectionsByIdVO {
	
	private int collectionId;
	private double paymentAmt;
	private double debitAmt;
	private String pymtDt;
	
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public double getPaymentAmt() {
		return paymentAmt;
	}
	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	public double getDebitAmt() {
		return debitAmt;
	}
	public void setDebitAmt(double debitAmt) {
		this.debitAmt = debitAmt;
	}
	public String getPymtDt() {
		return pymtDt;
	}
	public void setPymtDt(String pymtDt) {
		this.pymtDt = pymtDt;
	}
	
	

}
