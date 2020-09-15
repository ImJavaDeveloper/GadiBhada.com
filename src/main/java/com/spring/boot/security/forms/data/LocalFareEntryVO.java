package com.spring.boot.security.forms.data;

public class LocalFareEntryVO {
	
	private String truckNo;
	private String arrivalDt;
	private String localDriver;
	private String source;
	private String destinations;
	private String totalWt;
	private String totLocalFare;
	
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getArrivalDt() {
		return arrivalDt;
	}
	public void setArrivalDt(String arrivalDt) {
		this.arrivalDt = arrivalDt;
	}
	public String getLocalDriver() {
		return localDriver;
	}
	public void setLocalDriver(String localDriver) {
		this.localDriver = localDriver;
	}
	
	public String getTotalWt() {
		return totalWt;
	}
	public void setTotalWt(String totalWt) {
		this.totalWt = totalWt;
	}
	public String getTotLocalFare() {
		return totLocalFare;
	}
	public void setTotLocalFare(String totLocalFare) {
		this.totLocalFare = totLocalFare;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestinations() {
		return destinations;
	}
	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}

}
