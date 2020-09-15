package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.TruckLedgerVO;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.FormUtils;

public class TruckLedgerMapper implements RowMapper<TruckLedgerVO> {

	@Override
	public TruckLedgerVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		TruckLedgerVO truckLedgerVO=new TruckLedgerVO();
		truckLedgerVO.setTruckNo(rs.getString(++i));
		try {
			truckLedgerVO.setStratDt(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		truckLedgerVO.setFromToWhere(rs.getString(++i));
		try {
			truckLedgerVO.setEndDt(FormUtils.nullToEmpty(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		truckLedgerVO.setAdvFare(rs.getDouble(++i));
		truckLedgerVO.setTotFare(rs.getDouble(++i));
		truckLedgerVO.setPrizeFare(rs.getDouble(++i));	
		truckLedgerVO.setTotPymt(rs.getDouble(++i));
		truckLedgerVO.setTotBal(rs.getDouble(++i));
		
		return truckLedgerVO;
	}

}
