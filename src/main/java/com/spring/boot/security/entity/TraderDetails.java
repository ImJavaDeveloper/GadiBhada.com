package com.spring.boot.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trader_details")
public class TraderDetails {

	
	@Id
	private int trader_id;
	private String trader_name;
	private String trader_address;
	private String trader_mobile;
	private String trader_mark;
	
	public int getTrader_id() {
		return trader_id;
	}
	public void setTrader_id(int trader_id) {
		this.trader_id = trader_id;
	}
	public String getTrader_name() {
		return trader_name;
	}
	public void setTrader_name(String trader_name) {
		this.trader_name = trader_name;
	}
	public String getTrader_address() {
		return trader_address;
	}
	public void setTrader_address(String trader_address) {
		this.trader_address = trader_address;
	}
	public String getTrader_mobile() {
		return trader_mobile;
	}
	public void setTrader_mobile(String trader_mobile) {
		this.trader_mobile = trader_mobile;
	}
	public String getTrader_mark() {
		return trader_mark;
	}
	public void setTrader_mark(String trader_mark) {
		this.trader_mark = trader_mark;
	}

	
}
