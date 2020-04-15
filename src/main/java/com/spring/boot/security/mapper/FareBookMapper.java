package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.FareBook;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.FormUtils;

public class FareBookMapper implements RowMapper<FareBook> {

	@Override
	public FareBook mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		FareBook mapper=new FareBook();
		mapper.setChallan_id(rs.getString(++i));
		mapper.setTruck_no(rs.getString(++i));
		mapper.setLot_id(rs.getInt(++i));
		mapper.setFromeToWhere(rs.getString(++i));
		mapper.setItem_code(rs.getString(++i));
		mapper.setSub_lot_id(rs.getInt(++i));
		mapper.setAgent_id(rs.getInt(++i));
		mapper.setAgentName(rs.getString(++i));
		mapper.setAgent_destination_id(rs.getInt(++i));
		mapper.setAgent_destination_name(rs.getString(++i));
		try {
			mapper.setReceiving_date(FormUtils.nullToEmpty(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy")));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mapper.setTotal_qty(rs.getInt(++i));
		mapper.setFarePerBox(rs.getDouble(++i));
		mapper.setTotal_fare(rs.getDouble(++i));
		mapper.setExtra_fare(rs.getDouble(++i));
		
		
		return mapper;
	}

}
