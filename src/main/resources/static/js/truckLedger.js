$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();   
});
//Calling Ajax to load the page dynamically
$("#truckLedgerTab").on("click", function() {

	$('#menu6').load('/formcontent/truckLedger.html',function()
			{
	
		//callAjaxForCollectionBook();
			});

});
function reloadPageCollection(){

	callAjaxForCollectionBook();
	 
}
function callAjaxForTruckLedger() {
	
	var tableBody="";
	var subLotIdArray=[];
	waitingDialog.show();
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/allcollections",
		
		success : function(data) {
			var i=0;
			var result=jQuery.parseJSON(data);
			
			$.each( result,function(k,v){
				subLotIdArray[i]=v.sub_lot_id;

				tableBody=tableBody+"<tr>"+		
				"<td><a href=\"#\">"+v.truck_no+"</a></td>"+
				"<td><a href=\"#\">"+v.sub_lot_id+"</a></td>"+
				"<td><a href=\"#\">"+v.item_code+"</a></td>"+
				"<td><a href=\"#\">"+v.agent_name+"</a></td>"+
				"<td><a href=\"#\">"+v.adest_name+"</a></td>"+
				"<td><a href=\"#\">"+v.receiving_date+"</a></td>"+
				"<td><a href=\"#\">"+v.total_qty+"</a></td>"+
				"<td><a href=\"#\">"+v.fare_per_box+"</a></td>"+
				"<td><a href=\"#\">"+v.tot_fare+"</a></td>"+
				"<td><a href=\"#\">"+v.totPymt+"</a></td>"+
				"<td><a href=\"#\">"+v.tot_bal_amt+"</a></td>"+
				"<td><a href=\"#\">"+v.pymt_dt+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#allCollectionsModal\"" +
				" onclick=\"updateCollection("+v.sub_lot_id+",'"+v.truck_no+"','"+v.item_code+"','"+v.agent_name+"','"+v.adest_name+"','"+v.receiving_date+"','"+v.total_qty+"','"+v.fare_per_box+"','"+v.fare+"','"+v.tot_fare+"','"+v.tot_bal_amt+"')\">Update</button></td>"+
				
				"</tr>"	
				
				i++;
			});
			
			 $('#tableBodyTruckLedger').html(tableBody);
			
			$('#truckLedgerTable').DataTable();
			waitingDialog.hide();
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.readyState == 4) {
				// HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
				waitingDialog.hide();
				alert("Error !! Server Is Not Responding Correctly-->\n"
						+ XMLHttpRequest.responseText
						+ "+ \nPlease contact server admin");
			} else if (XMLHttpRequest.readyState == 0) {
				// Network error (i.e. connection refused, access denied due to CORS, etc.)
				waitingDialog.hide();
				alert("Error !! Server Is Down. Please contact server admin");
			} else {
				// something weird is happening
			}

			//some stuff on failure
		}
	});
}

function updateCollection(subLotId,truckNo,itemCode,agentName,aDestName,receiveDt,totalQty,farePerBox,fare,totalFare,totalBal)
{

$('#allCollectionsModalContent').load('/formcontent/models/viewCollectionsModal.html',function()
		{
	      callAjaxForCollectionsDataBySubLotId(subLotId,truckNo,itemCode,agentName,aDestName,receiveDt,totalQty,farePerBox,fare,totalFare,totalBal);
		});
}

function callAjaxForCollectionsDataBySubLotId(subLotId,truckNo,itemCode,agentName,aDestName,receiveDt,totalQty,farePerBox,fare,totalFare,totalBal) {
	
	var viewCollectionModalTab1=""
	var viewCollectionModalTab2=""
	var viewCollectionModalTab3=""
	var collectionIdArray=[];
	
	
	viewCollectionModalTab1="<tr>"+
	
	"<td><a href=\"#\" id=\"truckNo\" >"+truckNo+"</a></td>"+
	"<td><a href=\"#\" id=\"itemCode\" >"+itemCode+"</a></td>"+
	"<td><a href=\"#\" id=\"agentName\" >"+agentName+"</a></td>"+
	"<td><a href=\"#\" id=\"aDestName\" >"+aDestName+"</a></td>"+
	"<td><a href=\"#\" id=\"receiveDt\" >"+receiveDt+"</a></td>"+
	"<td><a href=\"#\" id=\"totQty\" >"+totalQty+"</a></td>"+
	"</tr>"	
	
	$('#viewCollectionModalTab1').html(viewCollectionModalTab1);
     viewCollectionModalTab2="<tr>"+
    
	"<td><a href=\"#\" id=\"farePerBox\" >"+farePerBox+"</a></td>"+
	"<td><a href=\"#\" id=\"fare\" >"+fare+"</a></td>"+
	"<td><a href=\"#\" id=\"totalFare\" >"+totalFare+"</a></td>"+
	"<td><a href=\"#\" id=\"totalBal\" >"+totalBal+"</a></td>"+

	"</tr>"	
	$('#viewCollectionModalTab2').html(viewCollectionModalTab2);
	
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/collectionsBySubLotId",
		data : {
			"subLotId" : subLotId
		},
		success : function(data) {
			var result=jQuery.parseJSON(data);
			
			var i=0;
			var totPaymt=0;
			var totDebit=0;
			$.each( result,function(k,v){
				
				collectionIdArray[i]=v.collectionId;
				
				viewCollectionModalTab3=viewCollectionModalTab3+"<tr>"+
				
				"<td><a href=\"#\" id=\"payment"+v.collectionId+"\" data-type=\"text\" data-placement=\"right\"data-pk=\""+v.collectionId+"\" data-name=\"tot_payment\">"+v.paymentAmt+"</a></td>"+
				"<td><a href=\"#\" id=\"debit"+v.collectionId+"\" data-type=\"text\" data-placement=\"right\"data-pk=\""+v.collectionId+"\" data-name=\"debit_amt\">"+v.debitAmt+"</a></td>"+
				"<td><a href=\"#\" id=\"paymentDt"+v.collectionId+"\" data-type=\"text\" data-placement=\"right\"data-pk=\""+v.collectionId+"\" data-name=\"pymt_dt\">"+v.pymtDt+"</a></td>"+
				"</tr>"	;
				i++;
				totDebit=totDebit+v.debitAmt
				totPaymt=totPaymt+v.paymentAmt
			});
			viewCollectionModalTab3=viewCollectionModalTab3+"<tr>"+
			"<td><a href=\"#\" id=\"Totpayment\" >Total Payment:"+totPaymt+"</a></td>"+
			"<td><a href=\"#\" id=\"TotDebit\" >Total Debit:"+totDebit+"</a></td>"+
			"<td><a href=\"#\" id=\"Totpayment\" ></a></td>"+
			"</tr>"	;
			
			$('#viewCollectionModalTab3').html(viewCollectionModalTab3);
			
			
			$.fn.editable.defaults.mode = 'popup'; 
			
     
           
			for(i=0;i<collectionIdArray.length;i++)
				{
				
				 $('#payment'+collectionIdArray[i]).editable({
					 url: '/gadibhada/managedata/updateCollection',
				       
				    });
				 $('#payment'+collectionIdArray[i]).on('save', function(e, params) {
				    	var id=$(this).attr('id');	

				    	var collId=id.substring(7);
				    	
				    	var oldPymt=$('#payment'+collId).text();
				    	var oldTotPymt=$('#Totpayment').text().substring(14);
				    
				    	var newTotPymt=parseFloat(oldTotPymt)-parseFloat(oldPymt)+parseFloat(params.newValue);
				
				    	$('#Totpayment').text('Total Payment:'+newTotPymt)
				    	
				    	var totDebit=$('#TotDebit').text().substring(12);
				    	var totFare=$('#totalFare').text();
				    	var newBal=parseFloat(totFare)-parseFloat(newTotPymt)-parseFloat(totDebit)
				    	$('#totalBal').text(newBal)
				    });
				}
			for(i=0;i<collectionIdArray.length;i++)
			{
			
			 $('#debit'+collectionIdArray[i]).editable({
				 url: '/gadibhada/managedata/updateCollection',
			       
			    });
			 $('#debit'+collectionIdArray[i]).on('save', function(e, params) {
			    	var id=$(this).attr('id');	

			    	var collId=id.substring(5);
			    	
			    	var oldDebit=$('#debit'+collId).text();
			    	var oldtotDebit=$('#TotDebit').text().substring(12);
			    	var newTotDebit=parseFloat(oldtotDebit)-parseFloat(oldDebit)+parseFloat(params.newValue);
			       
			    	$('#TotDebit').text('Total Debit:'+newTotDebit)
			    	
			    	var TotPymt=$('#Totpayment').text().substring(14);
			    	var totFare=$('#totalFare').text();
			    	var newBal=parseFloat(totFare)-parseFloat(TotPymt)-parseFloat(newTotDebit)
			    	$('#totalBal').text(newBal)
			 });
			    	
			    	
			}
			for(i=0;i<collectionIdArray.length;i++)
			{
			
			 $('#paymentDt'+collectionIdArray[i]).editable({
				 url: '/gadibhada/managedata/updateCollection',
			       
			    });
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.readyState == 4) {
				// HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
				waitingDialog.hide();
				alert("Error !! Server Is Not Responding Correctly-->\n"
						+ XMLHttpRequest.responseText
						+ "+ \nPlease contact server admin");
			} else if (XMLHttpRequest.readyState == 0) {
				// Network error (i.e. connection refused, access denied due to CORS, etc.)
				waitingDialog.hide();
				alert("Error !! Server Is Down. Please contact server admin");
			} else {
				// something weird is happening
			}

			//some stuff on failure
		}
	});
		
}

