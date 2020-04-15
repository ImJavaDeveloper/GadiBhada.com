package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.spring.boot.security.forms.data.CollectionFareVO;
import com.spring.boot.security.forms.data.CollectionsBook;

public class CollectionsBookDataMapper implements RowMapper<CollectionsBook> {

	@Override
	public CollectionsBook mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int i=0;
		CollectionsBook collectionBookVO=new CollectionsBook();
		
		collectionBookVO.setFare_id(rs.getInt(++i));
		collectionBookVO.setSub_lot_id(rs.getInt(++i));
		collectionBookVO.setTruck_no(rs.getString(++i));
		collectionBookVO.setItem_code(rs.getString(++i));
		collectionBookVO.setAgent_name(rs.getString(++i));
		collectionBookVO.setAdest_name(rs.getString(++i));
		collectionBookVO.setReceiving_date(rs.getString(++i));
		collectionBookVO.setTotal_qty(rs.getInt(++i));
		collectionBookVO.setFare_per_box(rs.getDouble(++i));
		collectionBookVO.setFare(rs.getDouble(++i));
		collectionBookVO.setExtra_fare (rs.getDouble(++i));
		collectionBookVO.setTot_fare(rs.getDouble(++i));
		collectionBookVO.setTotPymt(rs.getDouble(++i));
		collectionBookVO.setTotDebit(rs.getDouble(++i));
		collectionBookVO.setTot_bal_amt(rs.getDouble(++i));

		return collectionBookVO;
	}

}
