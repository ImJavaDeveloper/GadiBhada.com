package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.LotBooksByChallanIdVO;
import com.spring.boot.security.helper.FormUtils;

public class LotBooksByChallanIdMapper implements RowMapper<LotBooksByChallanIdVO> {

	@Override
	public LotBooksByChallanIdVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		int i=0;
		LotBooksByChallanIdVO lotBooksByChallanIdVO=new LotBooksByChallanIdVO();
		lotBooksByChallanIdVO.setLotId(rs.getInt(++i));
		lotBooksByChallanIdVO.setTraderId(rs.getInt(++i));
		lotBooksByChallanIdVO.setTraderName(rs.getString(++i));
		lotBooksByChallanIdVO.setTraderMark(rs.getString(++i));
		lotBooksByChallanIdVO.setItemId(rs.getInt(++i));
		lotBooksByChallanIdVO.setItemName(rs.getString(++i));
		lotBooksByChallanIdVO.setBoxId(rs.getInt(++i));
		lotBooksByChallanIdVO.setBoxName(rs.getString(++i));
		lotBooksByChallanIdVO.setBoxWt(rs.getInt(++i));
		lotBooksByChallanIdVO.setTotQty(rs.getInt(++i));
		lotBooksByChallanIdVO.setTotWt(rs.getInt(++i));
		lotBooksByChallanIdVO.setReceiver(FormUtils.nullToEmpty(rs.getString(++i)));
		return lotBooksByChallanIdVO;
	}

}
