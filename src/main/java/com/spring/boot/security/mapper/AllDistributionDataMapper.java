package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.AllDistributionData;

public class AllDistributionDataMapper implements RowMapper<AllDistributionData> {

	@Override
	public AllDistributionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		AllDistributionData allDistributionData=new AllDistributionData();
		
		return allDistributionData;
	}

}
