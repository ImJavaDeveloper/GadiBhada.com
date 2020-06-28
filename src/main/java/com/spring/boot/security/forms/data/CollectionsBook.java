package com.spring.boot.security.forms.data;

import java.sql.Date;

public class CollectionsBook {
	
	private int fare_id;
	private int sub_lot_id;
	private String truck_no;
	private String item_code;
	private String agent_name;
	private String  adest_name;
	private String receiving_date;
	private int total_qty;
	private double fare_per_box;
	private double fare;
	private double tot_fare;
	private double totPymt;
	private double totDebit;
	private double tot_bal_amt;
    private String pymt_dt;
	
	public int getFare_id() {
		return fare_id;
	}
	public void setFare_id(int fare_id) {
		this.fare_id = fare_id;
	}
	public int getSub_lot_id() {
		return sub_lot_id;
	}
	public void setSub_lot_id(int sub_lot_id) {
		this.sub_lot_id = sub_lot_id;
	}
	public String getTruck_no() {
		return truck_no;
	}
	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	
	public String getReceiving_date() {
		return receiving_date;
	}
	public void setReceiving_date(String receiving_date) {
		this.receiving_date = receiving_date;
	}
	public int getTotal_qty() {
		return total_qty;
	}
	public void setTotal_qty(int total_qty) {
		this.total_qty = total_qty;
	}
	public double getFare_per_box() {
		return fare_per_box;
	}
	public void setFare_per_box(double fare_per_box) {
		this.fare_per_box = fare_per_box;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}

	public double getTot_fare() {
		return tot_fare;
	}
	public void setTot_fare(double tot_fare) {
		this.tot_fare = tot_fare;
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
	public double getTot_bal_amt() {
		return tot_bal_amt;
	}
	public void setTot_bal_amt(double tot_bal_amt) {
		this.tot_bal_amt = tot_bal_amt;
	}
	public String getAdest_name() {
		return adest_name;
	}
	public void setAdest_name(String adest_name) {
		this.adest_name = adest_name;
	}
	public String getPymt_dt() {
		return pymt_dt;
	}
	public void setPymt_dt(String pymt_dt) {
		this.pymt_dt = pymt_dt;
	}
	



}
