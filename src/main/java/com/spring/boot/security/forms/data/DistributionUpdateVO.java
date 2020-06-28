package com.spring.boot.security.forms.data;

import java.sql.Date;

public class DistributionUpdateVO {
	
	private int challan_id;
	private Date  challan_date;
	private String truck_no;
	private int source_id;
	private String source_name;
	private String destination;
	private String trader_name;
	private String trader_mark;
	private int item_id;
	private String item_name;
	private int box_id;
	private String box_name;
	private int total_wt;
	private int total_qty;
	private int lot_id;
	private String reciever;
	private int totBal;
	
	public int getChallan_id() {
		return challan_id;
	}
	public void setChallan_id(int challan_id) {
		this.challan_id = challan_id;
	}
	public Date getChallan_date() {
		return challan_date;
	}
	public void setChallan_date(Date challan_date) {
		this.challan_date = challan_date;
	}
	public String getTruck_no() {
		return truck_no;
	}
	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTrader_name() {
		return trader_name;
	}
	public void setTrader_name(String trader_name) {
		this.trader_name = trader_name;
	}
	public String getTrader_mark() {
		return trader_mark;
	}
	public void setTrader_mark(String trader_mark) {
		this.trader_mark = trader_mark;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getBox_name() {
		return box_name;
	}
	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}
	public int getTotal_wt() {
		return total_wt;
	}
	public void setTotal_wt(int total_wt) {
		this.total_wt = total_wt;
	}
	public int getTotal_qty() {
		return total_qty;
	}
	public void setTotal_qty(int total_qty) {
		this.total_qty = total_qty;
	}
	public int getLot_id() {
		return lot_id;
	}
	public void setLot_id(int lot_id) {
		this.lot_id = lot_id;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public int getTotBal() {
		return totBal;
	}
	public void setTotBal(int totBal) {
		this.totBal = totBal;
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getBox_id() {
		return box_id;
	}
	public void setBox_id(int box_id) {
		this.box_id = box_id;
	}

}
