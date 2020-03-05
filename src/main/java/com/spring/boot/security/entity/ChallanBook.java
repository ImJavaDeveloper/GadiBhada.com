package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="challan_book")

public class ChallanBook {
	
	@Id
	@Column(name="challan_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int challanId;
	@Column(name="challan_date")
	private Date date;
	@Column(name="truck_no")
	private String truckNo;
	@Column(name="driver_name")
	private String driverName;
	@Column(name="driver_mobile")
	private String driverMobile;
	@Column(name="destination_id")
	private int destinationId;
	@Column(name="crte_tms")
	private String createTimeStamp;
	@Column(name="session_id")
	private String sessionId;
	@Column(name="source_id")
	private int sourceId;
	
	public int getChallanId() {
		return challanId;
	}
	public void setChallanId(int challanId) {
		this.challanId = challanId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public String getCreateTimeStamp() {
		return createTimeStamp;
	}
	public void setCreateTimeStamp(String createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}


}
