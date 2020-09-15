package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="local_fare_track")
public class LocalFareTrack {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lfare_track_id")
	private int lFareId;
	@Column(name="sub_lot_id")
	private int subLotId;
	@Column(name="indicator")
	private String indicator;
	public int getlFareId() {
		return lFareId;
	}
	public void setlFareId(int lFareId) {
		this.lFareId = lFareId;
	}
	public int getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(int subLotId) {
		this.subLotId = subLotId;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	
	

}
