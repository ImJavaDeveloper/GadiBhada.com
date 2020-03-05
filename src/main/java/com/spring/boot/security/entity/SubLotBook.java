package com.spring.boot.security.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sub_lot_book")
public class SubLotBook {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sub_lot_id")
	private int subLotId;
	@Column(name="lot_id")
	private int lotId;
	@Column(name="total_qty")
	private int totalQty;
	@Column(name="agent_destination_id")
	private int agentDestinationId;
	@Column(name="crte_tms")
	private String createTimeStamp;
	@Column(name="session_id")
	private String sessionId;
	@Column(name="agent_id")
	private int agentId;
	@Column(name="receiving_date")
	private Date receivingDate;
	
	public int getSubLotId() {
		return subLotId;
	}
	public void setSubLotId(int subLotId) {
		this.subLotId = subLotId;
	}
	public int getLotId() {
		return lotId;
	}
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public int getAgentDestinationId() {
		return agentDestinationId;
	}
	public void setAgentDestinationId(int agentDestinationId) {
		this.agentDestinationId = agentDestinationId;
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
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public Date getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}
	
	
}
