package com.spring.boot.security.forms.data;

import java.sql.Date;
import java.util.List;

import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.entity.LotBook;

public class ChallanBookForm {
	
	private ChallanBook challanBook;
	private List<LotBook> lotBook;
	
	public ChallanBook getChallanBook() {
		return challanBook;
	}
	public void setChallanBook(ChallanBook challanBook) {
		this.challanBook = challanBook;
	}
	public List<LotBook> getLotBook() {
		return lotBook;
	}
	public void setLotBook(List<LotBook> lotBook) {
		this.lotBook = lotBook;
	}

}
