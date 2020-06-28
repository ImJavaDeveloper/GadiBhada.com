package com.spring.boot.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.security.forms.data.CollectionsByIdVO;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.FormUtils;

public class CollectionByIdMapper implements RowMapper<CollectionsByIdVO> {

	@Override
	public CollectionsByIdVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int i=0;
		CollectionsByIdVO collectionsByIdVO=new CollectionsByIdVO();
		collectionsByIdVO.setCollectionId(rs.getInt(++i));
		collectionsByIdVO.setPaymentAmt(rs.getDouble(++i));
		collectionsByIdVO.setDebitAmt(rs.getDouble(++i));
		try {
			collectionsByIdVO.setPymtDt(FormUtils.nullToEmpty(DataHelper.formatDate(rs.getString(++i), "yyyy-MM-dd", "dd/MM/yyyy")));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return collectionsByIdVO;
	}

}
