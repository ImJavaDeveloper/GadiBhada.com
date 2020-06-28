package com.spring.boot.security.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.spring.boot.security.entity.FareBook;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.DistributionUpdateVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.forms.data.UpdateModalVO;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.repository.AgentDestinationRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.repository.AgentDetailsRespository;
import com.spring.boot.security.repository.FareBookRespository;

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
	@Autowired
	FareBookRespository fareBookRespository;
	
	@RequestMapping(value="/updateDistribution" ,method=RequestMethod.GET)
	@ResponseBody
	public String updateDistribution(@RequestParam Integer id) throws Exception {

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
			DistributionUpdateVO distrUpdtVO=list.get(i);
			
		    String Sender=distrUpdtVO.getTrader_name()==null || distrUpdtVO.getTrader_name().length()==0  ? "": "("+distrUpdtVO.getTrader_name()+")";
			Htmlbody.append(
					"            <tr>\r\n" + 
							"                <td>"+distrUpdtVO.getChallan_id()+"</td>\r\n" + 
							"                <td>"+DataHelper.formatDate(distrUpdtVO.getChallan_date(), "yyyy-MM-dd", "dd/MM/yyyy")+"</td>\r\n" + 
							"                <td>"+distrUpdtVO.getLot_id()+"</td>\r\n" + 
							"                <td>"+distrUpdtVO.getTruck_no()+"</td>\r\n" + 
							"                <td>"+distrUpdtVO.getSource_name()+"-"+distrUpdtVO.getDestination()+"</td>\r\n" + 
							"                <td>"+distrUpdtVO.getTrader_mark()+Sender+"</td>\r\n" +
							"                <td>"+distrUpdtVO.getItem_name()+"</td>\r\n" +
							"                <td>"+distrUpdtVO.getBox_name()+"-"+distrUpdtVO.getTotal_wt()+"(Kg)</td>\r\n" +
							"                <td>"+distrUpdtVO.getTotal_qty()+"</td>\r\n" + 
							"                <td>"+distrUpdtVO.getTotBal()+"</td>\r\n" +
							"                <td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#updateDistributionModal\" "+
							"                 onclick=\"loadUpdateModal("+distrUpdtVO.getLot_id()+",'"+distrUpdtVO.getChallan_date()+"','"+distrUpdtVO.getTruck_no()+"','"+distrUpdtVO.getSource_id()+"','"+distrUpdtVO.getSource_name()+"','"+
							distrUpdtVO.getDestination()+"','"+distrUpdtVO.getTrader_name()+"','"+distrUpdtVO.getTrader_mark()+"','"+distrUpdtVO.getItem_id()+"','"+distrUpdtVO.getItem_name()+"','"+distrUpdtVO.getBox_id()+"','"+
							distrUpdtVO.getBox_name()+"',"+distrUpdtVO.getTotal_wt()+")\">Distribute</button></td>"+
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
	public String loadUpdateModalData(@RequestParam Integer lotId,@RequestParam String challanDate,@RequestParam String truckNo,@RequestParam String sourceId,@RequestParam String sourceName,@RequestParam String destination,
			@RequestParam String traderName,@RequestParam String traderMark,@RequestParam String itemId,@RequestParam String itemName,@RequestParam String boxId,@RequestParam String boxName,@RequestParam String boxWt) throws Exception {


		List<AgentDetails> traderAgents =traderAgentRespository.findAll();
        String trader=traderName==null || traderName.toCharArray().length==0 ?  traderMark:traderMark+"-"+traderName;
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		LOGGER.debug(TableQuery.getUpdateModalQuery());
		UpdateModalVO updateModalVO=jdbcTemplate.queryForObject(TableQuery.getUpdateModalQuery(),new Object[] {lotId}, new UpdateModalMapper());
        String receivedDate=jdbcTemplate.query(TableQuery.getReceivingDtQuery(lotId), new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				
				return rs.next()?rs.getString(2):null;
			}
		});
        //System.out.println("receivedDate:"+receivedDate);
		StringBuilder updatemodalForm=new StringBuilder(

				"<font size=\"2\">"
				+ "<form class=\"form-inline\"  action=\"#\" method=\"post\" name=\"updateModalForm\" data-toggle=\"validator\">"

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
						+"<td id=\"modalChallnDate\"><label for=\"challnDate\">"+DataHelper.formatDate(challanDate, "yyyy-MM-dd", "dd/MM/yyyy")+"</label></td>"
						+"<td><label for=\"lotId\">"+updateModalVO.getLotId()+"</label></td>"
						+"<td><label for=\"truckNo\">"+truckNo+"</label></td>"
						+"<td><label for=\"FromWhere\">"+sourceName+"-"+destination+"</label></td>"
						+"<td><label for=\"trader\">"+trader+"</label></td>"
						+"<td><label for=\"itemCode\">"+itemName+"("+boxName+"-"+boxWt+")</label></td>"
						
						+"<td><label for=\"totQty\">"+updateModalVO.getTot_qty()+"</label></td>"
						+"<td><label for=\"qtyAvlVal\">"+updateModalVO.getTot_avl()+"</label></td>"
						+"<td><label for=\"reciever\">"+FormUtils.nullToEmpty(updateModalVO.getReceiver())+"</label></td>"
						
						+ "<input type=\"hidden\" name=\"modalLotId\" value="+updateModalVO.getLotId()+">"
						+ "<input type=\"hidden\" name=\"modalTotQty\" value="+updateModalVO.getTot_qty()+">"
						+ "<input type=\"hidden\" name=\"modalqtyAvl\" value="+updateModalVO.getTot_avl()+">"
						+ "<input type=\"hidden\" name=\"modalReciever\" value="+FormUtils.nullToEmpty(updateModalVO.getReceiver())+">"
						+ "<input type=\"hidden\" name=\"sourceId\" value="+sourceId+">"
						+ "<input type=\"hidden\" name=\"itemId\" value="+itemId+">"
						+ "<input type=\"hidden\" name=\"boxId\" value="+boxId+">"
						
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

		for(AgentDetails traders:traderAgents) {
			String traderName2=traders.getAgentName()==null || traders.getAgentName().toCharArray().length==0 ? "" :"("+traders.getAgentName()+")"; 
			updatemodalForm.append("<option value=\""+traders.getAgentId()+"\">"+traders.getAgentMark()+traderName2+"</option>");
		}
		
		updatemodalForm.append( " </select></td> "
				+"<td id=\"agentDestination\"></td>"
				+"<td><input type=\"text\" name=\"totQtyDistributed\" placeholder='Distribute Qty' class=\"form-control\" required oninput=\"calulateTotalFare()\"></td>"
				);
		if(receivedDate!=null)
		{
			updatemodalForm.append("<td id=\"receivingDate\">"
					+  	receivedDate
					+"</td>");
		}
		else {updatemodalForm.append( 
				"<td> <input type=\"date\" id=\"receivingDate\" name=\"receivingDate\" class=\"form-control\" required></td>"
				);
				
		}
		updatemodalForm.append(
		
				        "</tr>"
				        +" <input type=\"hidden\" name=\"aDestinationId\" name=\"aDestinationId\" />"
						+"</tbody>"
						+"</table>"
						+"</div>"
						+"</div>"
						
						+"<div class=\"row clearfix\">"
						+"<div class=\"col-md-12 column\">"
						+"<table class=\"table table-bordered table-hover\" id=\"updateModalFormTab3\">"

						+"<thead style=\"background-color: #E2E2E2\" >"
						+"<tr>"
						+"<th>Fare Per Box</th>"
						+"<th>Total Fare</th>"
						+"<th>Local Driver</th>"
						+ "</tr>"
						+"</thead>"
						+"<tbody>"
						+"<tr>"
						+"<td><input type=\"text\" name=\"farePerBoxCalc\" id=\"farePerBoxCalc\"  class=\"form-control\" required oninput=\"calulateTotalFare()\"></td>"				
						+"<td id=\"totalCalcFare\"></td>"
						+"<td><input type=\"text\" name=\"localDriver\" class=\"form-control\"></td>"
						+"<tr>"
						+"</tbody>"
						+"</table>"
						+"</div>"
						+"</div>"
						+"</form></font>"

				);



		return updatemodalForm.toString();	
	}

	@RequestMapping(value="/saveUpdateModal" ,method=RequestMethod.POST)
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = DataBaseException.class)
	public String saveUpdateModalData(@RequestParam int modalLotId,@RequestParam int agentId
			,@RequestParam int aDestId,@RequestParam int totQtyDistributed, @RequestParam Date receivingDate
			, @RequestParam double farePerBoxCalc, @RequestParam double totalCalcFare, @RequestParam String localDriver,HttpServletRequest request ) throws DataBaseException {

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
		subLotBook.setLocalDriver(localDriver);

		SubLotBook saveSubLotBook=subLotBookData.saveSubLotBookData(subLotBook);
		
		FareBook fareBook=new FareBook();
		fareBook.setSubLotId(subLotBook.getSubLotId());
		fareBook.setTotFare(totalCalcFare);
		fareBook.setCreateTimeStamp(createTimeStamp);
		fareBook.setSessionId(sessionId);
		fareBook.setFarePerBox(farePerBoxCalc);
		FareBook saveFareBook=fareBookRespository.save(fareBook);
		
		if(saveSubLotBook==null || saveFareBook==null) {

			throw new DataBaseException("Exception found while saving data into Challan Book ","management");

		}
		else
			return "success";

	}


}
