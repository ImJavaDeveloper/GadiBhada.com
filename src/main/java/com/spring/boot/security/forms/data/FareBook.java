package com.spring.boot.security.forms.data;

import java.sql.Date;

public class FareBook {
	
	private String challan_id;
	private String truck_no;
	private int lot_id;
	private String fromeToWhere;
	private String item_code;
	private int sub_lot_id;
	private int agent_id;
	private String agentName;
	private int agent_destination_id;
	private String agent_destination_name;
	private String receiving_date;
	private int total_qty;
	private double farePerBox;
	private double total_fare;
	/*private double tot_payment;
	private double debit_amt;
	private String pymt_dt;*/
	private double extra_fare;
	
	public String getChallan_id() {
		return challan_id;
	}
	public void setChallan_id(String challan_id) {
		this.challan_id = challan_id;
	}
	public String getTruck_no() {
		return truck_no;
	}
	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
	}
	public int getLot_id() {
		return lot_id;
	}
	public void setLot_id(int lot_id) {
		this.lot_id = lot_id;
	}
	public String getFromeToWhere() {
		return fromeToWhere;
	}
	public void setFromeToWhere(String fromeToWhere) {
		this.fromeToWhere = fromeToWhere;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public int getSub_lot_id() {
		return sub_lot_id;
	}
	public void setSub_lot_id(int sub_lot_id) {
		this.sub_lot_id = sub_lot_id;
	}
	public int getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(int agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public int getAgent_destination_id() {
		return agent_destination_id;
	}
	public void setAgent_destination_id(int agent_destination_id) {
		this.agent_destination_id = agent_destination_id;
	}
	public String getAgent_destination_name() {
		return agent_destination_name;
	}
	public void setAgent_destination_name(String agent_destination_name) {
		this.agent_destination_name = agent_destination_name;
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
	public double getFarePerBox() {
		return farePerBox;
	}
	public void setFarePerBox(double farePerBox) {
		this.farePerBox = farePerBox;
	}
	public double getTotal_fare() {
		return total_fare;
	}
	public void setTotal_fare(double total_fare) {
		this.total_fare = total_fare;
	}
	public double getExtra_fare() {
		return extra_fare;
	}
	public void setExtra_fare(double extra_fare) {
		this.extra_fare = extra_fare;
	}
	

}
