package com.spring.boot.security.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.security.mapper.DistributionUpdateRowMapper;
import com.spring.boot.security.mapper.FareCalcMapper;
import com.spring.boot.security.dto.SubLotBookData;
import com.spring.boot.security.entity.FareBook;
import com.spring.boot.security.entity.SubLotBook;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.DistributionUpdateVO;
import com.spring.boot.security.forms.data.FareCalcVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.AgentDestinationRepository;
import com.spring.boot.security.repository.FareBookRespository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.AgentDetailsRespository;

@Controller
public class FareCalcFormController {
	
private static final Logger LOGGER=LoggerFactory.getLogger(DistributionDataController.class);
	
	@Autowired
	DataSource dataSource;
	@Autowired
	FareBookRespository fareBookRespository;
	
	
	@RequestMapping(value="/farecalchome" ,method=RequestMethod.GET)
	@ResponseBody
	public String fareCalcHome(@RequestParam Integer id) {
		
		String sql=TableQuery.getFareCalculationQuery();
		LOGGER.debug(sql);
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		List<FareCalcVO> list =jdbcTemplate.query(sql, new FareCalcMapper());
		
StringBuilder htmlHeader=new StringBuilder(
		" <h3>Fare Calculation </h3> "
		+ "<table id=\"fareCalcTable\" class=\"table table-striped table-bordered\" style=\"width:100%;background-color: #E2E2E2\">\r\n" + 
		"        <thead>\r\n" + 
		"            <tr>\r\n" + 
		"                <th>Recieved Date</th>\r\n" + 
		"                 <th>Truck No</th>\r\n"+
		"                <th>From-To-Where</th>\r\n" + 
		"                <th>Receiver</th>\r\n" + 
		"                <th>Item</th>\r\n" + 
		"                <th>Box Type</th>\r\n" + 
		"                <th>Qty</th>\r\n" + 
		"                <th>Fare Per Box</th>\r\n" +
		"                 <th>Fare Calculated</th>\r\n"+
		"                <th>Action</th>\r\n" + 
		"            </tr>\r\n" + 
		"        </thead>\r\n" + 
		"        <tbody>\r\n" + 
		"            \r\n");
StringBuilder Htmlbody=new StringBuilder();
		for(int i=0;i<list.size();i++) {
			FareCalcVO fareCalcVO=list.get(i);
			double totFare=fareCalcVO.getFarePerBox()*fareCalcVO.getTotQty();
			Htmlbody.append(
		"            <tr>\r\n" + 
		"		<input type=\"hidden\" name=\"subLotIdFareCalc\"  value="+fareCalcVO.getSubLotId()+"></td>"+
		"                <td>"+fareCalcVO.getReceivingDate()+"</td>\r\n" + 
		"                <td>"+fareCalcVO.getTruckNo()+"</td>\r\n" + 
		"                <td>"+fareCalcVO.getSourceName()+"-"+fareCalcVO.getAgentDestName()+"</td>\r\n" + 
		"                <td>"+fareCalcVO.getAgentName()+"("+fareCalcVO.getAgentMark()+")</td>\r\n" + 
		"                <td>"+fareCalcVO.getItemName()+"</td>\r\n" +
		"                <td>"+fareCalcVO.getBoxName()+"-"+fareCalcVO.getBoxWt()+"(Kg)</td>\r\n" +
		"                <td id=\"fareCalcTotQty"+fareCalcVO.getSubLotId()+"\">"+fareCalcVO.getTotQty()+"</td>\r\n" + 
		"                 <td><input type=\"text\" id=\"farePerBox"+fareCalcVO.getSubLotId()+"\" value=\""+fareCalcVO.getFarePerBox()+"\" oninput=\"changeTotalFare("+fareCalcVO.getSubLotId()+")\"></td>"+
		"                 <td id=\"totFare"+fareCalcVO.getSubLotId()+"\">"+totFare+"</td>"+
		"                <td><button type=\"button\" class=\"btn btn-info btn-sm\" " +
		"                onclick=\"saveFare("+fareCalcVO.getSubLotId()+")\" id=\"button"+fareCalcVO.getSubLotId()+"\" >"+
		"                Confirm</button></td> </tr>\r\n" );
		}
		StringBuilder htmlFooter=new StringBuilder(		"        </tbody>\r\n" + 
		"        <tfoot>\r\n" + 
		"            <tr>\r\n" + 
		"                <th>Recieved Date</th>\r\n" + 
		"                 <th>Truck No</th>\r\n"+
		"                <th>From-To-Where</th>\r\n" + 
		"                <th>Receiver</th>\r\n" + 
		"                <th>Item</th>\r\n" + 
		"                <th>Box Type</th>\r\n" + 
		"                <th>Qty</th>\r\n" + 
		"                <th>Fare Per Box</th>\r\n" +
		"                 <th>Fare Calculated</th>\r\n"+
		"                <th>Action</th>\r\n" + 
		"            </tr>\r\n" + 
		"        </tfoot>\r\n" + 
		"    </table>");
StringBuilder modalContent=new StringBuilder();
modalContent.append(
		" <div id=\"confirmFareModal\" class=\"modal fade\" role=\"dialog\">"
		  +"<div class=\"modal-dialog modal-lg\" >"

		    +"<!-- Modal content-->"
		    +"<div class=\"modal-content\">"
		      +"<div class=\"modal-header\">"
		        +"<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>"
		        +"<h4 class=\"modal-title\">Update Distribution</h4>"
		     +" </div>"
		      +"<div class=\"modal-body\" id=\"modalContent\">"
		        +"<p>Some text in the modal.</p>"
		      +"</div>"
		      +"<div class=\"modal-footer\">"
		      +"<button id=\"save\" class=\"btn btn-width bkgrnd-cyan save-details\" type=\"button\" name=\"save-details\" onclick=\"saveUpdateModal()\" data-toggle=\"modal\"  >Save</button>"
		       +" <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>"
		      + " </div> </div> </div> </div>"
		    	  		
		
		);
	    return htmlHeader+Htmlbody.toString()+htmlFooter.toString()+modalContent.toString();
	
		
	}
	
	@RequestMapping(value="/savefareforsublotId",method=RequestMethod.GET)
	@ResponseBody
	public String saveFareForSubLotId(@RequestParam int subLotId,@RequestParam double totFare,HttpServletRequest request) throws DataBaseException
	{
		HttpSession session=request.getSession(false);
		String sessionId=session.getId();
		String createTimeStamp=FormUtils.getCurrentTimeStamp();
		FareBook fareBook=new FareBook();
		fareBook.setSubLotId(subLotId);
		fareBook.setTotFare(totFare);
		fareBook.setCreateTimeStamp(createTimeStamp);
		fareBook.setSessionId(sessionId);
		
		FareBook saveFareBook=fareBookRespository.save(fareBook);
		
		if(saveFareBook==null) {
			
			throw new DataBaseException("Exception found while saving data into Challan Book ","management");
		
	}
		else
			return "Fare Has Been Saved";
		
		
	}

}
