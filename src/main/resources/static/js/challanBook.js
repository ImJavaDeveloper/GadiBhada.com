$(document).ready(function() {
  
});

function warning()
{
alert("Warining !! Changing source will delete the fare book as well as collection book for all lot distributed for this challan\n"+"You have to re-enter fare and collection data under data entry section manullay \n Collected amount will also adjusted from daily ledger automatically");
$("#edtiFareAndCollectionModal").modal('show');
$("#allChallansModal").modal('hide');

//return;
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
//Calling Ajax to load the updated challan book page dynamically
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
	
	var viewChallanHeader="<tr >"+
	"<td colspan=\"4\" align=\"left\"><button type=\"button\" class=\"btn btn-danger btn-sm\" " +
	" onclick=\"DeleteChallan("+challanId+")\">Delete Challan</button></td>"+
	
	
	"<td colspan=\"3\" align=\"right\"><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#addNewLotModal\"" +
	" onclick=\"AddLotInExistingChallan(" +
	""+challanId+",'"+challanDate+"','"+truckNo+"',"+sourceId+",'"+sourceName+"',"+destinationId+",'"+destination+"','"+driverName+"','"+driverMobile+"')\">Add New Lot</button></td>"+
	"</tr>"
	
	
    $('#viewChallanHeader').html(viewChallanHeader);
	
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
				"<td><a href=\"#\" id=\"tradername"+v.lotId+"\" data-type=\"select\" data-placement=\"right\">"+v.traderMark+(v.traderName !="" ? "("+v.traderName+")" : "")+"</a></td>"+
				"<td onchange=\"warningForItemChange()\"><a href=\"#\" id=\"itemName"+v.lotId+"\" data-type=\"select\" data-placement=\"right\">"+v.itemName+"</a></td>"+
				"<td onchange=\"warningForBoxChange()\"><a href=\"#\" id=\"boxName"+v.lotId+"\" data-type=\"select\" data-placement=\"right\">"+v.boxName+"-"+v.boxWt+"</a></td>"+
				"<td onchange=\"warningForQtyChange()\"><a href=\"#\" id=\"totQty"+v.lotId+"\"  data-type=\"text\" data-placement=\"right\" data-pk=\""+v.lotId+"\" data-name=\"total_qty\">"+v.totQty+"</a></td>"+
				"<td><a href=\"#\" id=\"totWt"+v.lotId+"\"  data-type=\"text\" data-placement=\"right\" >"+v.totWt+"</a></td>"+
				"<td><a href=\"#\" id=\"receiver"+v.lotId+"\"  data-type=\"text\" data-placement=\"right\" data-pk=\""+v.lotId+"\" data-name=\"receiver\">"+v.receiver+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-danger btn-sm\" " +
				" onclick=\"DeleteLotFromChallan("+v.lotId+","+challanId+",'"+challanDate+"','"+truckNo+"',"+sourceId+",'"+sourceName+"',"+destinationId+",'"+destination+"','"+driverName+"','"+driverMobile+"')\">Delete</button></td>"+"</tr>"	;
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
			        
			        url: '/gadibhada/managedata/updateChallanLotReceiver',
			        	
			       
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

function AddLotInExistingChallan(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile)	
{
	 var traderList="";
	 var itemList="";
	 var boxTypesList="";
	 
	 
	var call1= $.ajax({
			type : "GET",
			url : '/management/getAllTrader',
			success : function(data) {
				// alert(data);
				 result=jQuery.parseJSON(data);
				
				 $.each(result, function(k, v) {
					
				 traderList=traderList+"<option value=\""+ v.trader_id +"\">"+v.trader_mark+"("+v.trader_name+")</option>";
			
				 });
				 }
		});
	
	var call2= $.ajax({
			type : "GET",
			url : '/management/getAllItems',
			success : function(data) {
				// alert(data);
				 result=jQuery.parseJSON(data);
				
				 $.each(result, function(k, v) {
		 
		 
    	  itemList=itemList+"<option value=\""+ v.item_id +"\">"+v.item_name+"</option>";
			
				 });
			 }
	});
	 var call3=$.ajax({
			type : "GET",
			url : '/management/getAllBoxType',
			success : function(data) {
				// alert(data);
				 result=jQuery.parseJSON(data);
				
				 $.each(result, function(k, v) {
		          	  boxTypesList=boxTypesList+"<option value=\""+ v.box_id +"\">"+v.box_name+"-"+v.total_wt+"</option>";
			
				 });
			 }
	});
	 //alert(list);
 // $(document).ajaxStop(function () {
	 $.when(call1,call2,call3).done(function(o1, o2, o3) {
	
  $('#addNewLotModalContent').html(
  		"<td><select class='form-control' id='traders' name='traderId' required><option value=''>Select Trader</option>"+
  		traderList
       +"</select> </td>" +
       "<td><select class='form-control' id='items' name='itemId' required ><option value=''>Select Item</option>"+
       itemList
       +"</select> </td>" +
       "<td><select class='form-control' id='boxTypes' name='boxId' required><option value=''>Select Type</option>"+
       boxTypesList
       +"</select> </td>" +
       
		"<td><input  name='totalQty' type='text' placeholder='Total Quantity'  class='form-control input-md' required ></td>"+
		"<td><input  name='receiver' type='text' placeholder='Receiver'  class='form-control input-md' ></td>"+
		"<td><button type=\"submit\" class=\"btn btn-info btn-sm\"" +
		" onclick=\"SaveLotForChallan("+challanId+",'"+challanDate+"','"+truckNo+"',"+sourceId+",'"+sourceName+"',"+destinationId+",'"+destination+"','"+driverName+"','"+driverMobile+"')\">Save</button></td>"
		);

      
      });  
	 
	 //viewChallanEntry(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile)
}
function SaveLotForChallan(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile)
{
	var trader=$("#traders option:selected").val();
	var item=$("#items option:selected").val();
	var boxType=$("#boxTypes option:selected").val();
	var totQty = jQuery('input[name="totalQty"]').val()
	var receiver = jQuery('input[name="receiver"]').val()
	
	//alert("traders:"+traders+"\nitems:"+items+"\nboxTypes:"+boxTypes+"\ntotQty:"+totQty+"\nreceiver:"+receiver)
	
	if (trader.trim().length == 0) {
		alert("Please Select Trader")
		return;
	}
	if (item.trim().length == 0) {
		alert("Please Select Item")
		return;
	}
	if (boxType.trim().length == 0) {
		alert("Please Select Box Type")
		return;
	}
	if (totQty.trim().length == 0) {
		alert("Please Enter Total Qty")
		return;
	}
	
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/addLotInChallan",
		data : {
			"trader" : trader,
			"item" : item,
			"boxType" : boxType,
			"totQty" : totQty,
			"receiver" : receiver,
			"challanId" : challanId
		},
		success : function(data) {
			// alert(data)
			if (data === "Success") {
				alert("Success !! Record Has Been Added Successfully");
				$("#addNewLotModal").modal('hide');
				viewChallanEntry(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile);
			} else {
				alert("Operation failed !! Exception Occured \n"+data)
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.readyState == 4) {
				// HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
				
				alert("Error !! Server Is Not Responding Correctly-->\n"
						+ XMLHttpRequest.responseText
						+ "+ \nPlease contact server admin");
			} else if (XMLHttpRequest.readyState == 0) {
				// Network error (i.e. connection refused, access denied due to CORS, etc.)
				
				alert("Error !! Server Is Down. Please contact server admin");
			} else {
				// something weird is happening
			}

			//some stuff on failure
		}
	});
}
function DeleteLotFromChallan(lotId,challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile)	
{
	var confirmation=confirm("Do You Want To Delete The Lot Id:"+lotId+" of challanId: "+challanId)
	if(confirmation==false)
		return;
	
	$.ajax({
		type : "POST",
		url : "/gadibhada/managedata/deleteLotFromChallan",
		data : {
			"lotId" : lotId
			
		},
		success : function(data) {
			// alert(data)
			if (data === "Success") {
				//alert("Success !! Record Has Been Added Successfully");
				viewChallanEntry(challanId,challanDate,truckNo,sourceId,sourceName,destinationId,destination,driverName,driverMobile);
			} 
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (XMLHttpRequest.readyState == 4) {
				// HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
				
				alert("Error !! Server Is Not Responding Correctly-->\n"
						+ XMLHttpRequest.responseText
						+ "+ \nPlease contact server admin");
			} else if (XMLHttpRequest.readyState == 0) {
				// Network error (i.e. connection refused, access denied due to CORS, etc.)
				
				alert("Error !! Server Is Down. Please contact server admin");
			} else {
				alert("Operation failed !! Exception Occured \n"+data)
			}

			//some stuff on failure
		}
	});
}
function DeleteChallan(challanId)	
{
	alert("DeleteChallan")
}

