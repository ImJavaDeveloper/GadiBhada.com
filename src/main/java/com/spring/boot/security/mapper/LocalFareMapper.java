package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.LocalFareEntryVO;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.FormUtils;

public class LocalFareMapper implements RowMapper<LocalFareEntryVO> {

	@Override
	public LocalFareEntryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		int i=0;
		LocalFareEntryVO lfareVo=new LocalFareEntryVO();
		lfareVo.setTruckNo(rs.getString(++i));
		try {
			String arrivalDts[]=rs.getString(++i).split(",");
			String formatedArrvlDt = "";
			for(int j=0;j<arrivalDts.length;j++)
		    {
				formatedArrvlDt=formatedArrvlDt+FormUtils.nullToEmpty(DataHelper.formatDate(arrivalDts[j], "yyyy-MM-dd", "dd/MM/yyyy"));	
	            if(j!=arrivalDts.length-1)
	            	formatedArrvlDt=formatedArrvlDt+",";
		    }
			
			if(arrivalDts.length==1)
			lfareVo.setArrivalDt(FormUtils.nullToEmpty(DataHelper.formatDate(arrivalDts[0], "yyyy-MM-dd", "dd/MM/yyyy")));
			else
				lfareVo.setArrivalDt(formatedArrvlDt);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lfareVo.setLocalDriver(rs.getString(++i));
		lfareVo.setSource(rs.getString(++i));
		lfareVo.setDestinations(rs.getString(++i));
		lfareVo.setTotalWt(rs.getString(++i));
		lfareVo.setTotLocalFare(rs.getString(++i));
		
		return lfareVo;
	}

}
