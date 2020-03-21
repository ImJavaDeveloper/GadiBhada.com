$(document).ready(function() {
  
});

function warning()
{
alert("Warining !! Changing source will delete the fare book as well as collection book for all lot distributed for this challan\n"+"You have to re-enter fare and collection data under data entry section manullay ");
return;
}

function warningForItemChange()
{
	alert("Warining !! Changing Item will delete the fare book as well as collection book for all lot distributed for this lot\n"+"You have to re-enter fare and collection data under data entry section manullay ");

}

function warningForBoxChange()
{
	alert("Warining !! Changing Box will delete the fare book as well as collection book for all lot distributed for this lot\n"+"You have to re-enter fare and collection data under data entry section manullay ");

}

function warningForQtyChange()
{
	alert("Warining !! Changing Qty less than earlier will delete the distribution book,fare book as well as collection book for all lot distributed for this lot\n"+"You have to re-enter fare and collection data under data entry section manullay ");

}


function reloadPageChallan(){

	 callAjaxForAllChallan();
	 
}
//Calling Ajax to load the updated distribution page dynamically
$("#distributionBook").on("click", function() {

	$('#alldistribution').load('/formcontent/distributionbook.html',function()
			{
	
		callAjaxForAllDistribution();
			});

});

function callAjaxForAllDistribution() {
	
	
	var agentIdArray=[];
	var agentDestIdArray=[];
	var subLotIdArray=[];
	var tableBody="";
	waitingDialog.show();
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/alldistribution",
		
		success : function(data) {
			var i=0;
			var result=jQuery.parseJSON(data);
			$.each( result,function(k,v){
				agentIdArray[i]=v.agentId;
				agentDestIdArray[i]=v.agentDestinationId;
				subLotIdArray[i]=v.subLotId;
				//console.log(v.subLotId);
				
				tableBody=tableBody+"<tr>"+
				"<td>"+v.challanId+"</td>"+
				"<td>"+v.challanDate+"</td>"+
				"<td>"+v.truckNo+"</td>"+
				"<td>"+v.sourceName+"-"+v.destination+"</td>"+
				"<td>"+v.senderName+"</td>"+
				"<td>"+v.lotId+"</td>"+
				"<td>"+v.itemCode+"</td>"+
				"<td><a href=\"#\" id=\"subLotreceiver"+v.subLotId+"\" data-type=\"select\" data-placement=\"right\" >"+v.distrubteTo+"</a></td>"+
				"<td>"+v.subLotId+"</td>"+
				"<td><a href=\"#\" id=\"subLotAgentDest"+v.subLotId+"\" data-type=\"select\" data-placement=\"right\" >"+v.agentDestination+"</a></td>"+
				"<td><a href=\"#\" id=\"subLotTotQty"+v.subLotId+"\" data-type=\"text\" data-placement=\"right\" data-pk=\""+v.subLotId+"\" data-name=\"total_qty\">"+v.totQty+"</a></td>"+
				"<td><a href=\"#\" id=\"subLotReceivedeDt"+v.subLotId+"\" data-type=\"text\" data-placement=\"right\" data-pk=\""+v.subLotId+"\" data-name=\"receiving_date\">"+v.receivedDate+"</a></td>"+
				"</tr>"	
				i++;
			});
			
			 $('#tableBodyDistribution').html(tableBody);
				
				$('#allDistributionTable').DataTable();
				
			$.fn.editable.defaults.mode = 'popup'; 
			console.log("subLOtArrayLenght:"+subLotIdArray.length)
			for(i=0;i<subLotIdArray.length;i++)
			{
			
			$('#subLotreceiver'+subLotIdArray[i]).editable({
		        type: 'select',
		        title: 'Select Agent',
		        placement: 'right',
		        value: agentIdArray[i],
		        source: '/management/getAgentList'
		        ,pk: subLotIdArray[i] 
		        ,url: '/gadibhada/managedata/updateAgent'
		        ,name:'agent_id'
		       
		    });
			
			}
			for(i=0;i<subLotIdArray.length;i++)
			{
			$('#subLotAgentDest'+subLotIdArray[i]).editable({
		        type: 'select',
		        title: 'Delivery Location ',
		        placement: 'right',
		        value: agentDestIdArray[i],
		        source: '/management/getAgentDestList'
		        ,pk: subLotIdArray[i] 
		        ,url: '/gadibhada/managedata/updateAgentDestination'
		        ,name:'agent_destination_id'
		       
		    });
			}
			for(i=0;i<subLotIdArray.length;i++)
			{
			$('#subLotTotQty'+subLotIdArray[i]).editable(	   
	        		   {
	                	  //url:'/gadibhada/managedata/updateChallanDate'
	                   });
			
			}
			for(i=0;i<subLotIdArray.length;i++)
			{
			$('#subLotReceivedeDt'+subLotIdArray[i]).editable(	   
	        		   {
	                	  //url:'/gadibhada/managedata/updateChallanDate'
	                   });
			
			}
			
			
			
			
           
			
			
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



