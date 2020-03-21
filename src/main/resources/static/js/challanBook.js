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
$("#challanBook").on("click", function() {

	
	$('#allchallan').load('/formcontent/allchallan.html',function()
			{
	
		callAjaxForAllChallan();
			});

});

function callAjaxForAllChallan() {
	
	var tableBody="";
	waitingDialog.show();
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/allchallan",
		
		success : function(data) {
			var result=jQuery.parseJSON(data);
			$.each( result,function(k,v){
				
				tableBody=tableBody+"<tr>"+
				"<td>"+v.challanId+"</td>"+
				"<td>"+v.challanDate+"</td>"+
				"<td>"+v.truckNo+"</td>"+
				"<td>"+v.sourceName+"</td>"+
				"<td>"+v.destination+"</td>"+
				"<td>"+v.driverName+"</td>"+
				"<td>"+v.driverMobile+"</td>"+
				"<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#allChallansModal\"" +
				" onclick=\"viewChallanEntry("+v.challanId+",'"+v.challanDate+"','"+v.truckNo+"',"+v.sourceId+",'"+v.sourceName+"',"+v.destinationId+",'"+v.destination+"','"+v.driverName+"','"+v.driverMobile+"')\">View</button></td>"+
				"</tr>"	
			});
			
			$('#allChallnTableBody').html(tableBody);
			//loadjs("/js/dataTable.js");
			$('#allChallanTable').DataTable();
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

function viewChallanEntry(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile)
{

$('#allChallansModalContent').load('/formcontent/models/viewChallanModal.html',function()
		{
	      callAjaxForViewChallanById(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile);
		});
}

function callAjaxForViewChallanById(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile) {
	
	var viewChallanModalFormTable2="";
	var lotIdArray=[];
	var traderIdArray=[];
	var itemIdArray=[];
	var boxIdArray=[];
	var boxWtArray=[];
	var traderList="";
	
	var viewChallanModalFormTable1="<tr>"+
	"<td>"+challanId+"</td>"+
	"<td><a href=\"#\" id=\"challanDate\" data-type=\"text\" data-placement=\"right\" data-pk=\""+challanId+"\" data-name=\"challan_date\">"+challanDate+"</a></td>"+
	"<td><a href=\"#\" id=\"truckNo\" data-type=\"text\" data-placement=\"right\" data-pk=\""+challanId+"\" data-name=\"truck_no\">"+truckNo+"</a></td>"+
	"<td onchange=\"warning()\"><a href=\"#\" id=\"sourceName\" data-type=\"select\" data-placement=\"right\" data-pk=\""+challanId+"\" data-name=\"source_id\">"+sourceName+"</a></td>"+
	"<td><a href=\"#\" id=\"destination\" data-type=\"select\" data-placement=\"right\" data-pk=\""+challanId+"\" data-name=\"destination_id\">"+destination+"</a></td>"+
	"<td><a href=\"#\" id=\"driverName\" data-type=\"text\" data-placement=\"right\" data-pk=\""+challanId+"\" data-name=\"driver_name\">"+driverName+"</a></td>"+
	"<td><a href=\"#\" id=\"driverMobile\" data-type=\"text\" data-placement=\"right\" data-pk=\""+challanId+"\" data-name=\"driver_mobile\">"+driverMobile+"</a></td>"+
	"</tr>"	
	
	$('#viewChallanModalTab1').html(viewChallanModalFormTable1);
	
	waitingDialog.show();
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/viewchallan",
		data : {
			"challanId" : challanId
		},
		success : function(data) {
			var result=jQuery.parseJSON(data);
			
			var i=0;
			$.each( result,function(k,v){
				lotIdArray[i]=v.lotId;
				traderIdArray[i]=v.traderId;
				itemIdArray[i]=v.itemId;
				boxIdArray[i]=v.boxId;
				boxWtArray[i]=v.boxWt;
				
				viewChallanModalFormTable2=viewChallanModalFormTable2+"<tr>"+
				"<td>"+v.lotId+"</td>"+
				//"<td>"+v.traderName+"</td>"+
				"<td><a href=\"#\" id=\"tradername"+v.lotId+"\" data-type=\"select\" data-placement=\"right\">"+v.traderName+"("+v.traderMark+")</a></td>"+
				"<td onchange=\"warningForItemChange()\"><a href=\"#\" id=\"itemName"+v.lotId+"\" data-type=\"select\" data-placement=\"right\">"+v.itemName+"</a></td>"+
				"<td onchange=\"warningForBoxChange()\"><a href=\"#\" id=\"boxName"+v.lotId+"\" data-type=\"select\" data-placement=\"right\">"+v.boxName+"-"+v.boxWt+"</a></td>"+
				"<td onchange=\"warningForQtyChange()\"><a href=\"#\" id=\"totQty"+v.lotId+"\"  data-type=\"text\" data-placement=\"right\" data-pk=\""+v.lotId+"\" data-name=\"total_qty\">"+v.totQty+"</a></td>"+
				"<td><a href=\"#\" id=\"totWt"+v.lotId+"\"  data-type=\"text\" data-placement=\"right\" >"+v.totWt+"</a></td>"+
				"<td><a href=\"#\" id=\"receiver"+v.lotId+"\"  data-type=\"text\" data-placement=\"right\" data-pk=\""+v.lotId+"\" data-name=\"receiver\">"+v.receiver+"</a></td>"+
				"</tr>"	;
				i++;
			});
			
			$('#viewChallanModalTab2').html(viewChallanModalFormTable2);
			
			
			$.fn.editable.defaults.mode = 'popup'; 
			
           $('#challanDate').editable(	   
        		   {
                	  url:'/gadibhada/managedata/updateChallanDate'
                   });
           
           $('#truckNo').editable(	   
        		   {
                	  url:'/gadibhada/managedata/updateChallanTruck'
                   });
           
           $('#sourceName').editable({
		        type: 'select',
		        title: 'Select Source',
		        placement: 'right',
		        value: sourceId,
		        source: '/management/getSourceList'
		        ,pk: challanId 
		        ,url: '/gadibhada/managedata/updateChallanSource'
		        ,name:'source_id'
		       
		    });
           $('#destination').editable({
		        type: 'select',
		        title: 'Select Destination',
		        placement: 'right',
		        value: destinationId,
		        source: '/management/getDestinationList'
		        ,pk: challanId 
		        ,url: '/gadibhada/managedata/updateChallanDestination'
		        ,name:'destination_id'
		       
		    });
           $('#driverName').editable(	   
        		   {
                	  url:'/gadibhada/managedata/updateChallanDriverName'
                   });
           $('#driverMobile').editable(	   
        		   {
                	  url:'/gadibhada/managedata/updateChallanDriverMob'
                   });
           /////Challn Table Editable
           
			for(i=0;i<lotIdArray.length;i++)
				{
				
				 $('#tradername'+lotIdArray[i]).editable({
				        type: 'select',
				        title: 'Select Trader',
				        placement: 'right',
				        value: traderIdArray[i],
				        source: '/management/getTradersList'
				        ,pk: lotIdArray[i] 
				        ,url: '/gadibhada/managedata/updateChallanLotTrader'
				        ,name:'trader_id'
				       
				    });
				}
			for(i=0;i<lotIdArray.length;i++)
			{
			
			 $('#itemName'+lotIdArray[i]).editable({
			        type: 'select',
			        title: 'Select Item',
			        placement: 'right',
			        value: itemIdArray[i],
			        source: '/management/getItemList'
			        ,pk: lotIdArray[i] 
			        ,url: '/gadibhada/managedata/updateChallanLotItem'
			        ,name:'item_id'
			       
			    });
			}
		
			for(i=0;i<lotIdArray.length;i++)
			{
			
			 $('#boxName'+lotIdArray[i]).editable({
			        type: 'select',
			        title: 'Select Box',
			        placement: 'right',
			        value: boxIdArray[i],
			        source: '/management/getBoxList'
			        ,pk: lotIdArray[i] 
			        ,url: '/gadibhada/managedata/updateChallanLotBox'
			        ,name:'box_id'
			       
			    });
			}
			
			for(i=0;i<lotIdArray.length;i++)
			{
			
			 $('#totQty'+lotIdArray[i]).editable({
			       
			    url: '/gadibhada/managedata/updateChallanLotTotQty',
				 
				 success: function () {
				 //alert("Success")
				// $('#totWt'+lotId).text(boxWt*totQty)	
				 
				 }
				    
			       
			    });
			 
			
			}
			for(i=0;i<lotIdArray.length;i++)
			{
				 $('#totQty'+lotIdArray[i]).on('save', function(e, params) {
					    var id=$(this).attr('id');
					    var lotId=id.substring(6);
					    var totQty=$('#totQty'+lotId).text();
	                    var boxName=$('#boxName'+lotId).text();
	                    var wtperBox=boxName.substring(boxName.indexOf('-')+1)
	                    $('#totWt'+lotId).text(params.newValue*wtperBox)	
	                   
	                   
					});
			}
			
			
			
			for(i=0;i<lotIdArray.length;i++)
			{
			
			 $('#receiver'+lotIdArray[i]).editable({
			        
			        url: '/gadibhada/managedata/updateChallanLotReceiver'
			      
			       
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

