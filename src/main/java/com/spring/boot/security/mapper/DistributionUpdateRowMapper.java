package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.DistributionUpdateVO;

public class DistributionUpdateRowMapper implements RowMapper<DistributionUpdateVO>{

	@Override
	public DistributionUpdateVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		DistributionUpdateVO distributionUpdateVO=new DistributionUpdateVO();
		distributionUpdateVO.setChallan_id(rs.getInt(++i));
		distributionUpdateVO.setChallan_date(rs.getDate(++i));
		distributionUpdateVO.setTruck_no(rs.getString(++i));
		distributionUpdateVO.setSource_id(rs.getInt(++i));
		distributionUpdateVO.setSource_name(rs.getString(++i));
		distributionUpdateVO.setDestination(rs.getString(++i));
		distributionUpdateVO.setTrader_name(rs.getString(++i));
		distributionUpdateVO.setTrader_mark(rs.getString(++i));
		distributionUpdateVO.setItem_id(rs.getInt(++i));
		distributionUpdateVO.setItem_name(rs.getString(++i));
		distributionUpdateVO.setBox_id(rs.getInt(++i));
		distributionUpdateVO.setBox_name(rs.getString(++i));
		distributionUpdateVO.setTotal_wt(rs.getInt(++i));
		distributionUpdateVO.setTotal_qty(rs.getInt(++i));
		distributionUpdateVO.setLot_id(rs.getInt(++i));
		distributionUpdateVO.setTotBal(rs.getInt(++i));

		return distributionUpdateVO;
	}

}
