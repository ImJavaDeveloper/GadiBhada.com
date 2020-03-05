package com.spring.boot.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="box_details")
public class BoxDetails {
	
	@Id
	private int box_id;
	private String box_name;
	private int total_wt;
	
	
	public int getBox_id() {
		return box_id;
	}
	public void setBox_id(int box_id) {
		this.box_id = box_id;
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
	

}
