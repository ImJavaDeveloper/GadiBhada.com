
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
				"<td id=\"lotChallanDt"+v.subLotId+"\">"+v.challanDate+"</td>"+
				"<td>"+v.truckNo+"</td>"+
				"<td>"+v.sourceName+"-"+v.destination+"</td>"+
				"<td>"+v.senderName+"</td>"+
				"<td>"+v.lotId+"</td>"+
				"<td>"+v.itemCode+"</td>"+
				"<td>"+v.subLotId+"</td>"+
				"<td><a href=\"#\" id=\"subLotreceiver"+v.subLotId+"\" data-type=\"select\" data-placement=\"right\" >"+v.agentMark+(v.distrubteTo != "" ? ("-"+v.distrubteTo):"")+"</a></td>"+
				"<td><a href=\"#\" id=\"subLotAgentDest"+v.subLotId+"\" data-type=\"text\" data-placement=\"right\" >"+v.agentDestination+"</a></td>"+
				"<td><a href=\"#\" id=\"subLotTotQty"+v.subLotId+"\" data-type=\"text\" data-placement=\"right\" data-pk=\""+v.subLotId+"\" data-name=\"total_qty\">"+v.totQty+"</a></td>"+
				"<td><a href=\"#\" id=\"subLotReceivedeDt"+v.subLotId+"\" data-type=\"text\" data-placement=\"right\" data-pk=\""+v.subLotId+"\" data-name=\"receiving_date\">"+v.receivedDate+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-danger btn-sm\" " +
				" onclick=\"DeleteSubLotFromChallan()\">Delete</button></td>"+
				"</tr>"	
				i++;
			});
			
			 $('#tableBodyDistribution').html(tableBody);
				
				
				
			$.fn.editable.defaults.mode = 'popup'; 
			
			for(i=0;i<subLotIdArray.length;i++)
			{
			
			$('#subLotreceiver'+subLotIdArray[i]).editable({
		        type: 'select',
		        title: 'Select Agent',
		        placement: 'right',
		        value: agentIdArray[i],
		        source: '/management/getAgentList'
		        ,pk: subLotIdArray[i] 
		        ,url: '/gadibhada/managedata/updateSubLotReceiver'
		        ,name:'agent_id'
		       
		    });
            $('#subLotreceiver'+subLotIdArray[i]).on('save', function(e, params) {
            	 var id=$(this).attr('id');	
            	 
            	 $.ajax({
            			type : "GET",
            			url : "/management/getAgentAddr",
            			data : {
            				"agentId" : params.newValue
            			},
            			success : function(data) {
            				var subLot=id.substring(14);
            				//alert(subLot)
            	            $('#subLotAgentDest'+subLot).text(data)
            	            //$('#aDestId').val(agentId)
            	      
            			}
            	});
			});
			
			}
			
		
			for(i=0;i<subLotIdArray.length;i++)
			{
			$('#subLotTotQty'+subLotIdArray[i]).editable(	   
	        		   {
	        			   
	        			   url:'/gadibhada/managedata/updateSubLotQty'
	        			  /* validate: function() {
	        	               var id=$(this).attr('id');	
	        				   var subLot=id.substring(14);
	        				  
				        	    if($.trim(subLot) != '') {
				        	    	 alert(subLot)
			        				   alert($('#lotChallanDt'+subLot).val())
				        	    }
				        	}*/
	                   });
			
			
			}
			for(i=0;i<subLotIdArray.length;i++)
			{
			$('#subLotReceivedeDt'+subLotIdArray[i]).editable(	   
	        		   {
	                	  url:'/gadibhada/managedata/updateSubLotRecievedDate'
	                   });
			
			}
					
			$('#allDistributionTable').DataTable();
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



