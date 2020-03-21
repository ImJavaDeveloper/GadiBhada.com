package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.AllDistributionData;
import com.spring.boot.security.helper.DataHelper;

public class AllDistributionDataMapper implements RowMapper<AllDistributionData> {

	@Override
	public AllDistributionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		AllDistributionData allDistributionData=new AllDistributionData();
		allDistributionData.setChallanId(rs.getInt(++i));
		
		try {
			allDistributionData.setChallanDate(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		allDistributionData.setTruckNo(rs.getString(++i));
		allDistributionData.setLotId(rs.getInt(++i));
		allDistributionData.setSourceName(rs.getString(++i));
		allDistributionData.setDestination(rs.getString(++i));
		allDistributionData.setSenderName(rs.getString(++i));
		allDistributionData.setItemCode(rs.getString(++i));
		allDistributionData.setAgentId(rs.getInt(++i));
		allDistributionData.setDistrubteTo(rs.getString(++i));
		allDistributionData.setSubLotId(rs.getInt(++i));
		allDistributionData.setAgentDestinationId(rs.getInt(++i));
		allDistributionData.setAgentDestination(rs.getString(++i));
		allDistributionData.setTotQty(rs.getInt(++i));
		try {
			allDistributionData.setReceivedDate(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allDistributionData;
	}

}
