package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fare_rule")
public class FareRule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="fare_id")
	private int fareId;
	@Column(name="source_id")
	private int sourceId;
	@Column(name="agent_destination_id")
	private int agentDestinationId;
	@Column(name="box_id")
	private int boxId;
	@Column(name="item_id")
	private int itemId;

	private double fare;

	public int getFareId() {
		return fareId;
	}

	public void setFareId(int fareId) {
		this.fareId = fareId;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getAgentDestinationId() {
		return agentDestinationId;
	}

	public void setAgentDestinationId(int agentDestinationId) {
		this.agentDestinationId = agentDestinationId;
	}

	public int getBoxId() {
		return boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
	
	
}
