package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.DistributionUpdateVO;
import com.spring.boot.security.forms.data.UpdateModalVO;

public class UpdateModalMapper implements RowMapper<UpdateModalVO> {

	@Override
	public UpdateModalVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		UpdateModalVO updateModalVO=new UpdateModalVO();
		updateModalVO.setLotId(rs.getInt(++i));
		updateModalVO.setTot_qty(rs.getInt(++i));
		updateModalVO.setTot_avl(rs.getInt(++i));
		updateModalVO.setReceiver(rs.getString(++i));
		return updateModalVO;
	}

}
