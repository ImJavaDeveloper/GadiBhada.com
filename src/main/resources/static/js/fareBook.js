$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();   
});
//Calling Ajax to load the page dynamically
$("#fareBook").on("click", function() {

	$('#allfarebook').load('/formcontent/fareBook.html',function()
			{
	
		callAjaxForFareBook();
			});

});

function callAjaxForFareBook() {
	
	var tableBody="";
	var subLotIdArray=[];
	waitingDialog.show();
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/allfares",
		
		success : function(data) {
			var i=0;
			var result=jQuery.parseJSON(data);
			$.each( result,function(k,v){
				subLotIdArray[i]=v.sub_lot_id;	
				
				tableBody=tableBody+"<tr>"+		
				"<td><a href=\"#\">"+v.truck_no+"</a></td>"+
				"<td><a href=\"#\">"+v.sub_lot_id+"</a></td>"+
				"<td><a href=\"#\">"+v.item_code+"</a></td>"+
				"<td><a href=\"#\">"+v.agentName+"</a></td>"+
				"<td><a href=\"#\">"+v.agent_destination_name+"</a></td>"+
				"<td><a href=\"#\">"+v.receiving_date+"</a></td>"+
				"<td><a href=\"#\" id=\"totQty"+v.sub_lot_id+"\" >"+v.total_qty+"</a></td>"+
				"<td><a href=\"#\" id=\"farePerBox"+v.sub_lot_id+"\" data-type=\"text\" data-placement=\"right\" data-pk=\""+v.sub_lot_id+"\" data-name=\"fare_per_box\">"+v.farePerBox+"</a></td>"+
				"<td><a href=\"#\" id=\"totFare"+v.sub_lot_id+"\" data-type=\"text\" data-placement=\"right\" data-pk=\""+v.sub_lot_id+"\" data-name=\"farePerBox\">"+v.total_fare+"</a></td>"+
				"<td><a href=\"#\" id=\"totalFareCalc"+v.sub_lot_id+"\" data-type=\"text\" data-placement=\"right\" >"+(v.total_fare)+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-danger btn-sm\" " +
				" onclick=\"DeleteFareEntry()\">Delete</button></td>"+
				"</tr>"	
				
				i++;
			});
			
			 $('#tableBodyFareBook').html(tableBody);
			
			
			
			$.fn.editable.defaults.mode = 'popup'; 
			
			for(i=0;i<subLotIdArray.length;i++)
			{
				
			$('#farePerBox'+subLotIdArray[i]).editable(  
		 		   {
		         	  url:'/gadibhada/managedata/updateFarePerBox'
		            });
		    $('#farePerBox'+subLotIdArray[i]).on('save', function(e, params) {
		    	var id=$(this).attr('id');	

		    	var subLotId=id.substring(10);
		        //alert("subLotId:"+subLotId)
			    var totQty=$('#totQty'+subLotId).text();
			    
		    	var farePerBox=params.newValue;
		    	
		    	
		    	$('#totFare'+subLotId).text(farePerBox*totQty)
		    	$('#totalFareCalc'+subLotId).text(farePerBox*totQty)
		    
		    	var updatedTotFare=farePerBox*totQty
		    	
		    	$.ajax({
		    		type : "POST",
		    		url : "/gadibhada/managedata/updateFareBookTotFare",
		    		data : {
		    			"name" : 'total_fare',
		    			"value" : updatedTotFare,
		    			"pk" : subLotId
		    		},
		    		success : function(data) {
		    			
		    		}
		    });
		       
			});
			}
			
			for(i=0;i<subLotIdArray.length;i++)
			{

			}
			$('#fareBookTable').DataTable();
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



