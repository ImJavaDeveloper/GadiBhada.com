package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.AllChallanData;
import com.spring.boot.security.helper.DataHelper;

public class AllChallanDataMapper implements RowMapper<AllChallanData>  {

	@Override
	public AllChallanData mapRow(ResultSet rs, int rowNum) throws SQLException {

       int i=0;
       AllChallanData allChallanData=new AllChallanData();
       allChallanData.setChallanId(rs.getInt(++i));
     
	   try {
		allChallanData.setChallanDate(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       allChallanData.setTruckNo(rs.getString(++i));
       allChallanData.setSourceId(rs.getInt(++i));
       allChallanData.setSourceName(rs.getString(++i));
       allChallanData.setDestinationId(rs.getInt(++i));
       allChallanData.setDestination(rs.getString(++i));
       allChallanData.setDriverName(rs.getString(++i));
       allChallanData.setDriverMobile(rs.getString(++i));
		return allChallanData;
	}


}
