package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="daily_ledger_book")
public class DailyLedgerBook {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ledger_id")
	private int ledgerId;
	@Column(name="ledger_dt")
	private Date ledgerDt;
	@Column(name="tot_truck_pymt")
	private double totTruckPymt;
	@Column(name="tot_bnk_trnsfr")
	private double totBnkTrnsfr;
	@Column(name="tot_local_fare")
	private double totLocalFare;
	@Column(name="tot_mny_withdrwl")
	private double totMnyWithdrwl;
	@Column(name="tot_othr_expns")
	private double totOthrExpns;
	@Column(name="tot_thekedari")
	private double totThekedari;
	@Column(name="tot_collections")
	private double totCollections;
	@Column(name="tot_ledger_bal")
	private double totLedger_bal;
	
	
	public int getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(int ledgerId) {
		this.ledgerId = ledgerId;
	}
	public Date getLedgerDt() {
		return ledgerDt;
	}
	public void setLedgerDt(Date ledgerDt) {
		this.ledgerDt = ledgerDt;
	}
	public double getTotTruckPymt() {
		return totTruckPymt;
	}
	public void setTotTruckPymt(double totTruckPymt) {
		this.totTruckPymt = totTruckPymt;
	}
	public double getTotBnkTrnsfr() {
		return totBnkTrnsfr;
	}
	public void setTotBnkTrnsfr(double totBnkTrnsfr) {
		this.totBnkTrnsfr = totBnkTrnsfr;
	}
	public double getTotLocalFare() {
		return totLocalFare;
	}
	public void setTotLocalFare(double totLocalFare) {
		this.totLocalFare = totLocalFare;
	}
	public double getTotMnyWithdrwl() {
		return totMnyWithdrwl;
	}
	public void setTotMnyWithdrwl(double totMnyWithdrwl) {
		this.totMnyWithdrwl = totMnyWithdrwl;
	}
	public double getTotOthrExpns() {
		return totOthrExpns;
	}
	public void setTotOthrExpns(double totOthrExpns) {
		this.totOthrExpns = totOthrExpns;
	}
	public double getTotThekedari() {
		return totThekedari;
	}
	public void setTotThekedari(double totThekedari) {
		this.totThekedari = totThekedari;
	}
	public double getTotCollections() {
		return totCollections;
	}
	public void setTotCollections(double totCollections) {
		this.totCollections = totCollections;
	}
	public double getTotLedger_bal() {
		return totLedger_bal;
	}
	public void setTotLedger_bal(double totLedger_bal) {
		this.totLedger_bal = totLedger_bal;
	}
	
}
