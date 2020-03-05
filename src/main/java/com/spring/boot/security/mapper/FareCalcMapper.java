package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.FareCalcVO;

public class FareCalcMapper implements RowMapper<FareCalcVO> {

	@Override
	public FareCalcVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		FareCalcVO fareCalcVo=new FareCalcVO();
		fareCalcVo.setSubLotId(rs.getInt(++i));
		fareCalcVo.setReceivingDate(rs.getDate(++i));
		fareCalcVo.setTruckNo(rs.getString(++i));
		fareCalcVo.setSourceName(rs.getString(++i));
		fareCalcVo.setAgentDestName(rs.getString(++i));
		fareCalcVo.setAgentName(rs.getString(++i));
		fareCalcVo.setAgentMark(rs.getString(++i));
		fareCalcVo.setItemName(rs.getString(++i));
		fareCalcVo.setBoxName(rs.getString(++i));
		fareCalcVo.setBoxWt(rs.getInt(++i));
		fareCalcVo.setTotQty(rs.getInt(++i));
		fareCalcVo.setFarePerBox(rs.getDouble(++i));
		
		return fareCalcVo;
	}

}
