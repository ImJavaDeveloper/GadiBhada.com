package com.spring.boot.security.forms.data.reports;

import java.util.Date;

public class FareAndcollectionsData {
	
	private String AGENT_DESTINATION;
	private String AGENT_NAME;
	private String DELIVERY_DATE;
	private String ITEM_CODE;
	private double TOTAL_QTY;
	private double TOTAL_FARE;
	private double TOTAL_PAYMENT;
	private double TOTAL_DEBIT;
	private double TOTAL_BALANCE;
	
	
	public String getAGENT_DESTINATION() {
		return AGENT_DESTINATION;
	}
	public void setAGENT_DESTINATION(String aGENT_DESTINATION) {
		AGENT_DESTINATION = aGENT_DESTINATION;
	}
	public String getAGENT_NAME() {
		return AGENT_NAME;
	}
	public void setAGENT_NAME(String aGENT_NAME) {
		AGENT_NAME = aGENT_NAME;
	}
	public String getITEM_CODE() {
		return ITEM_CODE;
	}
	public void setITEM_CODE(String iTEM_CODE) {
		ITEM_CODE = iTEM_CODE;
	}
	public double getTOTAL_QTY() {
		return TOTAL_QTY;
	}
	public void setTOTAL_QTY(double tOTAL_QTY) {
		TOTAL_QTY = tOTAL_QTY;
	}
	public double getTOTAL_FARE() {
		return TOTAL_FARE;
	}
	public void setTOTAL_FARE(double tOTAL_FARE) {
		TOTAL_FARE = tOTAL_FARE;
	}
	public double getTOTAL_PAYMENT() {
		return TOTAL_PAYMENT;
	}
	public void setTOTAL_PAYMENT(double tOTAL_PAYMENT) {
		TOTAL_PAYMENT = tOTAL_PAYMENT;
	}
	public double getTOTAL_DEBIT() {
		return TOTAL_DEBIT;
	}
	public void setTOTAL_DEBIT(double tOTAL_DEBIT) {
		TOTAL_DEBIT = tOTAL_DEBIT;
	}
	public double getTOTAL_BALANCE() {
		return TOTAL_BALANCE;
	}
	public void setTOTAL_BALANCE(double tOTAL_BALANCE) {
		TOTAL_BALANCE = tOTAL_BALANCE;
	}
	public String getDELIVERY_DATE() {
		return DELIVERY_DATE;
	}
	public void setDELIVERY_DATE(String dELIVERY_DATE) {
		DELIVERY_DATE = dELIVERY_DATE;
	}
	

}
