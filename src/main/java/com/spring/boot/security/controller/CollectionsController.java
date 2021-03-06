package com.spring.boot.security.controller;

import java.sql.Date;
import java.util.List;

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

import com.spring.boot.security.dto.FareCollectionData;
import com.spring.boot.security.entity.FareCollection;
import com.spring.boot.security.exception.DataBaseException;
import com.spring.boot.security.forms.data.CollectionFareVO;
import com.spring.boot.security.forms.data.TableQuery;
import com.spring.boot.security.helper.DataHelper;
import com.spring.boot.security.helper.DataValidator;
import com.spring.boot.security.helper.FormUtils;
import com.spring.boot.security.mapper.CollectionsDataMapper;

@Controller
public class CollectionsController {

	
	private static final Logger LOGGER=LoggerFactory.getLogger(CollectionsController.class);
	
	@Autowired
	DataSource datasource;

	@Autowired
	FareCollectionData fareCollectionData;

	@RequestMapping(value="/management/getcollections",method=RequestMethod.GET)
	@ResponseBody
	public String getCollectionPage(@RequestParam Date ledgerDt) throws Exception
	{
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<CollectionFareVO> collectionFareVOList=jdbcTemplate.query(TableQuery.getCollectionDataQuery(), new CollectionsDataMapper());
		
		StringBuilder htmlHeader=new StringBuilder(
				"<style>"+
						"table thead tr th {font-size: 11px;}"+
						"table tfoot tr th {font-size: 11px;}"+
						"table tbody tr td { font-size: 12px;}"+
						"</style>"+
				" <h3>Fare Collection</h3>\r\n" + 
				"<table id=\"collectionTable\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
				"	<thead>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>TruckNo</th>\r\n" + 
				"			<th>RecievedOn</th>\r\n" + 
				"			<th>ItemCode</th>\r\n" + 
				"			<th>AgentName</th>\r\n" + 
				"			<th>Address</th>\r\n" + 
				"			<th>Qty</th>\r\n" + 
				"			<th>TotalFare</th>\r\n" + 
				"			<th>TotalPayment</th>\r\n" + 
				"			<th>TotalDebit</th>\r\n" + 
				"			<th>Balance</th>\r\n" + 
				"            <th>Action</th>\r\n" + 
				"		</tr>\r\n" + 
				"	</thead>\r\n" + 
				"	<tbody>");
		StringBuilder Htmlbody=new StringBuilder();
		
		for(int i=0;i<collectionFareVOList.size();i++) {
			CollectionFareVO collectionFareVO=collectionFareVOList.get(i);
			String agentName=collectionFareVO.getAgentName()==null ? "":"-"+collectionFareVO.getAgentName();
			Htmlbody.append(

					"            <tr>\r\n" + 
							"                <td>"+collectionFareVO.getTruckNo()+"</td>\r\n" + 
							"                <td>"+DataHelper.formatDate(collectionFareVO.getRecievedDt(), "yyyy-MM-dd", "dd/MM/yyyy")+"</td>\r\n" + 
							"                <td>"+collectionFareVO.getItemName().toUpperCase()+"-"+collectionFareVO.getBoxName().toUpperCase()+"-"+collectionFareVO.getBoxWt()+"</td>\r\n" + 
							"                <td>"+collectionFareVO.getAgentMark()+agentName+"</td>\r\n" +
							"                <td>"+collectionFareVO.getAgentDestName()+"</td>\r\n" +
							"                <td>"+collectionFareVO.getTotQty()+"</td>\r\n" +
							"                <td>"+collectionFareVO.getTotFare()+"</td>\r\n" +
							"                <td>"+collectionFareVO.getTotPymt()+"</td>\r\n" +
							"                <td>"+collectionFareVO.getTotDebit()+"</td>\r\n" +
							"                <td>"+collectionFareVO.getTotBal()+"</td>\r\n" + 
							"                <td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#collectionsModal\" onclick=\"loadCollectionsModal("+collectionFareVO.getSubLotId()+",'"+ledgerDt+"')\">Collect</button></td>"+
					"            </tr>\r\n" );
		}
		StringBuilder htmlFooter=new StringBuilder(		"        </tbody>\r\n" + 
				"        <tfoot>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>TruckNo</th>\r\n" + 
				"			<th>RecievedOn</th>\r\n" + 
				"			<th>ItemCode</th>\r\n" + 
				"			<th>AgentName</th>\r\n" + 
				"			<th>Address</th>\r\n" + 
				"			<th>Qty</th>\r\n" + 
				"			<th>TotalFare</th>\r\n" + 
				"			<th>TotalPayment</th>\r\n" + 
				"			<th>TotalDebit</th>\r\n" + 
				"			<th>Balance</th>\r\n" + 
				"            <th>Action</th>\r\n" + 
				"		</tr>\r\n" +
				"        </tfoot>\r\n" + 
				"    </table>");
		StringBuilder modalContent=new StringBuilder();
		/*modalContent.append(
				" <div id=\"collectionsModal\" class=\"modal fade\" role=\"dialog\">"
						+"<div class=\"modal-dialog modal-lg\" >"

				    +"<!-- Modal content-->"
				    +"<div class=\"modal-content\">"
				    +"<div class=\"modal-header\">"
				    +"<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>"
				    +"<h4 class=\"modal-title\">Collections Entry</h4>"
				    +" </div>"
				    +"<div class=\"modal-body\" id=\"collectionModelContent\">"
				    +"<p>Some text in the modal.</p>"
				    +"</div>"
				    +"<div class=\"modal-footer\">"
				    +"<button id=\"save\" class=\"btn btn-primary btn-width bkgrnd-cyan save-details\" type=\"button\" name=\"save-details\" onclick=\"saveCollectionsData()\" data-toggle=\"modal\"  >Save</button>"
				    +" <button type=\"button\" class=\"btn btn-default\" onclick=\"closeCollectionPopUp()\">Close</button>"
				    + " </div> </div> </div> </div>"


				);*/
		return htmlHeader+Htmlbody.toString()+htmlFooter.toString()+modalContent.toString();

	}

	@RequestMapping(value="/management/loadCollectionsModal" ,method=RequestMethod.GET)
	@ResponseBody
	public String loadCollectionsModal(@RequestParam Integer subLotId,@RequestParam String ledgerDt) throws Exception {
		LOGGER.info(TableQuery.getCollectionDataQuery(subLotId));
		CollectionFareVO collectionVO;
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		List<CollectionFareVO> collectionFareVOList=jdbcTemplate.query(TableQuery.getCollectionDataQuery(subLotId), new CollectionsDataMapper());
		if(collectionFareVOList.size()>1)
			return "Duplicate Data found !!";
		else
			collectionVO=collectionFareVOList.get(0);
		StringBuilder collectionsModalForm=new StringBuilder(

				"<div id=\"collectionModelmMessage\"></div>"
						+ "<form class=\"form-inline\"  action=\"#\" method=\"post\" name=\"updateModalForm\" data-toggle=\"validator\">"
						+"<div class=\"row clearfix\">"
						+"<div class=\"col-md-12 column\">"
						+"<table class=\"table table-bordered table-hover\" id=\"collectionModalFormTab1\">"
						+"	<thead style=\"background-color: #E2E2E2\">\r\n" 
						+"		<tr>\r\n"
						+"			<th>Truck No</th>\r\n"  
						+"			<th>Recieved On</th>\r\n" 
						+"			<th>Item Code</th>\r\n"  
						+"			<th>Agent Name</th>\r\n"  
						+"			<th>Delivered To</th>\r\n"  
						+"			<th>Qty</th>\r\n"  
						+"			<th>Total Fare</th>\r\n"  
						+"			<th>Total Bal</th>\r\n" 
						+"		</tr>\r\n" 
						+"	</thead>\r\n" 
						+"<tbody>"
						+"<tr  >"
						+ "<input type=\"hidden\" name=\"subLotId\" value=\""+collectionVO.getSubLotId()+"\">"
						+ "<input type=\"hidden\" name=\"fareIdC\" value=\""+collectionVO.getFareId()+"\">"
						+ "<input type=\"hidden\" name=\"ledgerDt\" value=\""+ledgerDt+"\">"

				+"<td><label for=\"Truck No\" >"+collectionVO.getTruckNo()+"</label></td>"
				+"<td><label for=\"receivedDt\">"+DataHelper.formatDate(collectionVO.getRecievedDt(), "yyyy-MM-dd", "dd/MM/yyyy")+"</label></td>"
				+"<td><label for=\"itemCode\">"+collectionVO.getItemName()+"-"+collectionVO.getBoxName()+"-"+collectionVO.getBoxWt()+"</label></td>"
				+"<td><label for=\"agentName\">"+collectionVO.getAgentName()+"-"+collectionVO.getAgentMark()+"</label></td>"
				+"<td><label for=\"deliveryLoc\">"+collectionVO.getAgentDestName()+"</td>"
				+"<td><label for=\"totQty\">"+collectionVO.getTotQty()+"</label></td>"
				+"<td><label for=\"totFare\">"+collectionVO.getTotFare()+"</label></td>"
				+"<td><label for=\"totFare\">"+collectionVO.getTotBal()+"</label></td>"
				
				+ "<input type=\"hidden\" name=\"totPayable\" value=\""+collectionVO.getTotBal()+"\">"
				+"</tr>"
				+"</tbody>"
				+"</table>"
				+"</div>"
				+"</div>"
				+"<div class=\"row clearfix\">"
				+"<div class=\"col-md-12 column\">"
				+"<table class=\"table table-bordered table-hover\" id=\"collectionModalFormTab2\">"

						+"<thead style=\"background-color: #E2E2E2\">"
						+"<tr>"
						+"<td><label for=\"extraFareAmt\">Tot Payment</label></td>"
						+"<td><label for=\"extraFareAmt\">Tot Debit</label></td>"
						+"<td><label for=\"totaPymt\">Payable Amt</label></td>"
						+"<td><label for=\"totDebit\">Total Debit</label></td>"
						+"<td><label for=\"pymtDt\">Payment Date</label></td>"
						
						+ "</tr>"
						+"</thead>"

						+"<tbody>"
						+"<tr>"

                        +"<td><label for=\"totFare\">"+collectionVO.getTotPymt()+"</label></td>"
                        +"<td><label for=\"totFare\">"+collectionVO.getTotDebit()+"</label></td>"
						+ "<td><input type=\"text\" name=\"totalPymt\" placeholder='Payment Amount(Rs)' class=\"form-control\" required  ></td>"
						+"<td><input type=\"text\" name=\"totalDebit\" placeholder='Total Debit' class=\"form-control\" required></td>"
						+"<td> <input type=\"date\" id=\"paymentDate\" name=\"paymentDate\" class=\"form-control\" required></td>"
						+"</tr>"

						 +"</tbody>"
						 +"</table>"
						 +"</div>"
						 +"</div>"
						 +"</form>"

				);



		return collectionsModalForm.toString();	
	}


	@RequestMapping(value="/management/savecollection" ,method=RequestMethod.GET)
	@ResponseBody
	public String saveCollection(@RequestParam Integer subLotId,@RequestParam Integer fareIdC,@RequestParam String totalPymt
			,@RequestParam(required=false) String totalDebit,@RequestParam Date paymentDate ,@RequestParam Date ledgerDt) throws DataBaseException {

		String message=null; 
		if(totalPymt!=null && !DataValidator.isNumber(totalPymt))
			return "Please Enter Correct Payment";
		if(totalDebit.trim().length()>0 && !DataValidator.isNumber(totalDebit))
			return "Please Enter Correct Total Debit";
		else
		{
			FareCollection fareCollection=new FareCollection();
			fareCollection.setFareId(fareIdC);
			fareCollection.setSubLotId(subLotId);
			fareCollection.setTotPayment(FormUtils.getDouble(totalPymt));
			fareCollection.setDebitAmt(FormUtils.getDouble(totalDebit));
			fareCollection.setPymtDt(paymentDate);
			fareCollection.setLedgerDt(ledgerDt);
			FareCollection savedFareCollection=fareCollectionData.saveFareCollectionData(fareCollection);
			if(savedFareCollection==null) {

				message="Exception found while saving data into Fare Collection";
				throw new DataBaseException("Exception found while saving data into Fare Collection ","management");

			}
			else
			{
				message="success";
			}

			return message;

		}

	}

}
