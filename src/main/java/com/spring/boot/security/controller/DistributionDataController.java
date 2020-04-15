package com.spring.boot.security.controller;

import java.sql.Date;
import java.util.List;
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
import com.spring.boot.security.dto.SubLotBookData;
import com.spring.boot.security.mapper.UpdateModalMapper;
import com.spring.boot.security.entity.AgentDestination;
import com.spring.boot.security.entity.SubLotBook;
import com.spring.boot.security.entity.AgentDetails;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.DistributionUpdateVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.forms.data.UpdateModalVO;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.AgentDestinationRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.AgentDetailsRespository;

@Controller
public class DistributionDataController {


	private static final Logger LOGGER=LoggerFactory.getLogger(DistributionDataController.class);

	@Autowired
	DataSource dataSource;
	@Autowired
	LotBookRepository lotBookRepository;
	@Autowired
	AgentDetailsRespository traderAgentRespository;
	@Autowired
	AgentDestinationRepository agentDestinationRepository;

	@Autowired
	SubLotBookData subLotBookData;

	@RequestMapping(value="/updateDistribution" ,method=RequestMethod.GET)
	@ResponseBody
	public String updateDistribution(@RequestParam Integer id) {

		String sql=TableQuery.getUpdateDistributionQuery();
		LOGGER.debug(sql);
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		List<DistributionUpdateVO> list =jdbcTemplate.query(sql, new DistributionUpdateRowMapper());

		StringBuilder htmlHeader=new StringBuilder(" <h3>Distribution Page </h3> "
				+ "<table id=\"distributionTable\" class=\"table table-striped table-bordered\" style=\"width:100%;background-color: #E2E2E2\">\r\n" + 
				"        <thead>\r\n" + 
				"            <tr>\r\n" + 
				"                <th>Challan Id</th>\r\n" + 
				"                <th>Date</th>\r\n" + 
				"                <th>Lot Id</th>\r\n" + 
				"                <th>Truck No</th>\r\n" + 
				"                <th>From-To-Where</th>\r\n" + 
				"                <th>Sender</th>\r\n" + 
				"                 <th>Item</th>\r\n"+
				"                 <th>Box Type</th>\r\n"+
				"                 <th>Qty</th>\r\n"+
				"                 <th>Total Balance</th>\r\n"+
				"                <th>Action</th>\r\n" + 
				"            </tr>\r\n" + 
				"        </thead>\r\n" + 
				"        <tbody>\r\n" + 
				"            \r\n");
		StringBuilder Htmlbody=new StringBuilder();
		for(int i=0;i<list.size();i++) {
			DistributionUpdateVO distributionUpdateVO=list.get(i);
			Htmlbody.append(
					"            <tr>\r\n" + 
							"                <td>"+distributionUpdateVO.getChallan_id()+"</td>\r\n" + 
							"                <td>"+distributionUpdateVO.getChallan_date()+"</td>\r\n" + 
							"                <td>"+distributionUpdateVO.getLot_id()+"</td>\r\n" + 
							"                <td>"+distributionUpdateVO.getTruck_no()+"</td>\r\n" + 
							"                <td>"+distributionUpdateVO.getSource_name()+"-"+distributionUpdateVO.getDestination()+"</td>\r\n" + 
							"                <td>"+distributionUpdateVO.getTrader_name()+"("+distributionUpdateVO.getTrader_mark()+")</td>\r\n" +
							"                <td>"+distributionUpdateVO.getItem_name()+"</td>\r\n" +
							"                <td>"+distributionUpdateVO.getBox_name()+"-"+distributionUpdateVO.getTotal_wt()+"(Kg)</td>\r\n" +
							"                <td>"+distributionUpdateVO.getTotal_qty()+"</td>\r\n" + 
							"                <td>"+distributionUpdateVO.getTotBal()+"</td>\r\n" +
							"                <td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#updateDistributionModal\" "+
							"                 onclick=\"loadUpdateModal("+distributionUpdateVO.getLot_id()+",'"+distributionUpdateVO.getChallan_date()+"','"+distributionUpdateVO.getTruck_no()+"','"+distributionUpdateVO.getSource_name()+"','"+
							                  distributionUpdateVO.getDestination()+"','"+distributionUpdateVO.getTrader_name()+"','"+distributionUpdateVO.getTrader_mark()+"','"+distributionUpdateVO.getItem_name()+"','"+
							                  distributionUpdateVO.getBox_name()+"',"+distributionUpdateVO.getTotal_wt()+")\">Distribute</button></td>"+
					"            </tr>\r\n" );
		}
		StringBuilder htmlFooter=new StringBuilder(		"        </tbody>\r\n" + 
				"        <tfoot>\r\n" + 
				"            <tr>\r\n" + 
				"                <th>Challan Id</th>\r\n" + 
				"                <th>Date</th>\r\n" + 
				"                <th>Truck No</th>\r\n" + 
				"                <th>From-To-Where</th>\r\n" + 
				"                <th>Sender</th>\r\n" + 
				"                 <th>Item</th>\r\n"+
				"                 <th>Box Type</th>\r\n"+
				"                 <th>Qty</th>\r\n"+
				"                <th>Action</th>\r\n" +
				"            </tr>\r\n" + 
				"        </tfoot>\r\n" + 
				"    </table>");
		StringBuilder modalContent=new StringBuilder();
		modalContent.append(
				" <div id=\"updateDistributionModal\" class=\"modal fade\" role=\"dialog\">"
						+"<div class=\"modal-dialog modal-lg\" >"

		    +"<!-- Modal content-->"
		    +"<div class=\"modal-content\">"
		    +"<div class=\"modal-header\">"
		    +"<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>"
		    +"<h4 class=\"modal-title\">Distribution</h4>"
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


	@RequestMapping(value="/loadUpdateModal" ,method=RequestMethod.GET)
	@ResponseBody
	public String loadUpdateModalData(@RequestParam Integer lotId,@RequestParam String challanDate,@RequestParam String truckNo,@RequestParam String sourceName,@RequestParam String destination,
			@RequestParam String traderName,@RequestParam String traderMark,@RequestParam String itemName,@RequestParam String boxName,@RequestParam String boxWt) {


		List<AgentDetails> traderAgents =traderAgentRespository.findAll();

		List<AgentDestination> agentDestinations=agentDestinationRepository.findAll();
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		LOGGER.debug(TableQuery.getUpdateModalQuery());
		UpdateModalVO updateModalVO=jdbcTemplate.queryForObject(TableQuery.getUpdateModalQuery(),new Object[] {lotId}, new UpdateModalMapper());

		StringBuilder updatemodalForm=new StringBuilder(

				"<font size=\"2\"><form class=\"form-inline\"  action=\"#\" method=\"post\" name=\"updateModalForm\" data-toggle=\"validator\">"

				+"<div class=\"row clearfix\">"
				+"<div class=\"col-md-12 column\">"
				+"<table class=\"table table-bordered table-hover\" id=\"updateModalFormTab1\">"
				+"	<thead style=\"background-color: #E2E2E2\" >\r\n" 
				+"		<tr>\r\n"
				+"			<th>Challan Id</th>\r\n"  
				+"			<th>Date</th>\r\n"  
				+"			<th>Lot Id</th>\r\n"  
				+"			<th>Truck No</th>\r\n"  
				+"			<th>From-Where</th>\r\n" 
				+"			<th>Sender</th>\r\n"   
				+"			<th>Item Id </th>\r\n"   
				+"			<th>Lot Qty </th>\r\n"  
				+"			<th>Total Balance </th>\r\n" 
				+"			<th>Reciever </th>\r\n" 
				+"		</tr>\r\n" 
				+"	</thead>\r\n" 
				+"<tbody>"
				+"<tr >"

						
						+"<td><label for=\"challandId\">"+updateModalVO.getChallanId()+"</label></td>"
						+"<td id=\"modalChallnDate\"><label for=\"challnDate\">"+challanDate+"</label></td>"
						+"<td><label for=\"lotId\">"+updateModalVO.getLotId()+"</label></td>"
						+"<td><label for=\"truckNo\">"+truckNo+"</label></td>"
						+"<td><label for=\"FromWhere\">"+sourceName+"-"+destination+"</label></td>"
						+"<td><label for=\"trader\">"+traderName+"-"+traderMark+"</label></td>"
						+"<td><label for=\"itemCode\">"+itemName+"("+boxName+"-"+boxWt+")</label></td>"
						
						+"<td><label for=\"totQty\">"+updateModalVO.getTot_qty()+"</label></td>"
						+"<td><label for=\"qtyAvlVal\">"+updateModalVO.getTot_avl()+"</label></td>"
						+"<td><label for=\"reciever\">"+FormUtils.nullToEmpty(updateModalVO.getReceiver())+"</label></td>"
						
						+ "<input type=\"hidden\" name=\"modalLotId\" value="+updateModalVO.getLotId()+">"
						+ "<input type=\"hidden\" name=\"modalTotQty\" value="+updateModalVO.getTot_qty()+">"
						+ "<input type=\"hidden\" name=\"modalqtyAvl\" value="+updateModalVO.getTot_avl()+">"
						+ "<input type=\"hidden\" name=\"modalReciever\" value="+FormUtils.nullToEmpty(updateModalVO.getReceiver())+">"

				+"</tr>"
				+"</tbody>"
				+"</table>"
				+"</div>"
				+"</div>"

						+"<div class=\"row clearfix\">"
						+"<div class=\"col-md-12 column\">"
						+"<table class=\"table table-bordered table-hover\" id=\"updateModalFormTab2\">"

						+"<thead style=\"background-color: #E2E2E2\" >"
						+"<tr>"
						+"<th>Distribute To</th>"
						+"<th>Delivered At</th>"
						+"<th>Distribute Qty</th>"
						+"<th>Received On</th>"
						+ "</tr>"
						+"</thead>"

						+"<tbody>"
						+"<tr>"
						+"<td onchange=\"fetchAgentDestination()\"><select class=\"form-control\" id=\"agentId\" name=\"agentId\" required >"
						+"<option value=\"\">Agent</option>"
				);

		for(AgentDetails traderAgent:traderAgents)
			updatemodalForm.append("<option value=\""+traderAgent.getAgentId()+"\">"+traderAgent.getAgentName()+"("+traderAgent.getAgentMark()+")</option>");

		
		updatemodalForm.append( " </select></td> "
				+"<td id=\"agentDestination\"></td>"
				+"<td><input type=\"text\" name=\"totQtyDistributed\" placeholder='Distribute Qty' class=\"form-control\" required></td>"
				+"<td> <input type=\"date\" id=\"receivingDate\" name=\"receivingDate\" class=\"form-control\" required></td>"
				+" <input type=\"hidden\" id=\"aDestId\" name=\"aDestId\">"
				+"</tr>"

						 +"</tbody>"
						 +"</table>"
						 +"</div>"
						 +"</div>"
						 +"</form></font>"

				);



		return updatemodalForm.toString();	
	}

	@RequestMapping(value="/saveUpdateModal" ,method=RequestMethod.GET)
	@ResponseBody
	public String saveUpdateModalData(@RequestParam int modalLotId,@RequestParam int modalTotQty,
			@RequestParam int modalqtyAvl,@RequestParam(required = false) String modalReciever,@RequestParam int agentId
			,@RequestParam int aDestId,@RequestParam int totQtyDistributed, @RequestParam Date receivingDate ,HttpServletRequest request ) throws DataBaseException {

		HttpSession session=request.getSession(false);
		String sessionId=session.getId();
		String createTimeStamp=FormUtils.getCurrentTimeStamp();
		SubLotBook subLotBook=new SubLotBook();
		subLotBook.setLotId(modalLotId);
		subLotBook.setTotalQty(totQtyDistributed);
		subLotBook.setAgentDestinationId(agentId);
		subLotBook.setAgentId(agentId);
		subLotBook.setCreateTimeStamp(createTimeStamp);
		subLotBook.setSessionId(sessionId);
		subLotBook.setReceivingDate(receivingDate);

		SubLotBook saveSubLotBook=subLotBookData.saveSubLotBookData(subLotBook);
		if(saveSubLotBook==null) {

			throw new DataBaseException("Exception found while saving data into Challan Book ","management");

		}
		else
			return "success";

	}


}
