package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.spring.boot.security.forms.data.CollectionFareVO;

public class CollectionsDataMapper implements RowMapper<CollectionFareVO> {

	@Override
	public CollectionFareVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		int i=0;
		CollectionFareVO collectionFareVO=new CollectionFareVO();
		
		collectionFareVO.setFareId(rs.getInt(++i));
		collectionFareVO.setSubLotId(rs.getInt(++i));
		collectionFareVO.setTruckNo(rs.getString(++i));
		collectionFareVO.setRecievedDt(rs.getDate(++i));
		collectionFareVO.setItemName(rs.getString(++i));
		collectionFareVO.setBoxName(rs.getString(++i));
		collectionFareVO.setBoxWt(rs.getInt(++i));
		collectionFareVO.setTotQty(rs.getInt(++i));
		collectionFareVO.setAgentName(rs.getString(++i));
		collectionFareVO.setAgentMark(rs.getString(++i));
		collectionFareVO.setAgentDestName(rs.getString(++i));
		collectionFareVO.setTotFare(rs.getDouble(++i));
		collectionFareVO.setExtraFare(rs.getDouble(++i));
		collectionFareVO.setTotPymt(rs.getDouble(++i));
		collectionFareVO.setTotDebit(rs.getDouble(++i));
		collectionFareVO.setTotBal(rs.getDouble(++i));	
		return collectionFareVO;
	}

}
