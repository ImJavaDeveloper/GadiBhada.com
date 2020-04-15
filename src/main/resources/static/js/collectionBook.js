$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();   
});
//Calling Ajax to load the page dynamically
$("#collectionBook").on("click", function() {

	$('#allcollectionbook').load('/formcontent/collectionBook.html',function()
			{
	
		callAjaxForCollectionBook();
			});

});

function callAjaxForCollectionBook() {
	
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
				subLotIdArray[i]=v.subLotId;

				tableBody=tableBody+"<tr>"+		
				"<td><a href=\"#\">"+v.truck_no+"</a></td>"+
				"<td><a href=\"#\">"+v.item_code+"</a></td>"+
				"<td><a href=\"#\">"+v.agent_name+"</a></td>"+
				"<td><a href=\"#\">"+v.adest_name+"</a></td>"+
				"<td><a href=\"#\">"+v.receiving_date+"</a></td>"+
				"<td><a href=\"#\">"+v.total_qty+"</a></td>"+
				"<td><a href=\"#\">"+v.fare_per_box+"</a></td>"+
				"<td><a href=\"#\">"+v.tot_fare+"</a></td>"+
				"<td><a href=\"#\">"+v.totPymt+"</a></td>"+
				"<td><a href=\"#\">"+v.tot_bal_amt+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#allCollectionsModal\"" +
				" onclick=\"updateCollection("+v.subLotId+")\">Update</button></td>"+
				
				"</tr>"	
				
				i++;
			});
			
			 $('#tableBodyCollectionBook').html(tableBody);
			
			$('#collectionBookTable').DataTable();
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

function updateCollection(subLotId)
{

$('#allCollectionsModalContent').load('/formcontent/models/viewCollectionsModal.html',function()
		{
	     // callAjaxForCollectionsDataBySubLotId(subLotId);
		});
}

function callAjaxForFareCollectionBySubLotId(truckNo,itemCode,agentName,aDestName,receiveDt,subLotId,totQty,farePerBox,totPymt,debitAmt,totFare,extraFare,pymtDt) {
	
	var FareCollectionFormTable1=""
	var FareCollectionFormTable2=""
	var lotIdArray=[];
	var traderIdArray=[];
	var itemIdArray=[];
	var boxIdArray=[];
	var boxWtArray=[];
	var traderList="";
	
	 FareCollectionFormTable1="<tr>"+
	
	"<td><a href=\"#\" id=\"truckNo\" >"+truckNo+"</a></td>"+
	"<td><a href=\"#\" id=\"itemCode\" >"+itemCode+"</a></td>"+
	"<td><a href=\"#\" id=\"agentName\" >"+agentName+"</a></td>"+
	"<td><a href=\"#\" id=\"aDestName\" >"+aDestName+"</a></td>"+
	"<td><a href=\"#\" id=\"receiveDt\" >"+receiveDt+"</a></td>"+
	"<td><a href=\"#\" id=\"totQty\" >"+totQty+"</a></td>"+
	"</tr>"	
	
	$('#FareCollectionModalTab1').html(FareCollectionFormTable1);
	
	FareCollectionModalTab2="<tr>"+
	"<input type=\"hidden\" name=\"subLotId\" id=\"subLotId\" value=\""+subLotId+"\" >"+
	"<td><a href=\"#\" id=\"farePerBox\" data-type=\"text\" data-placement=\"right\" data-pk=\""+subLotId+"\" data-name=\"fare_per_box\">"+farePerBox+"</a></td>"+
	"<td><a href=\"#\" id=\"totFare\" data-type=\"text\" data-placement=\"right\" data-pk=\""+subLotId+"\" data-name=\"farePerBox\">"+totFare+"</a></td>"+
	"<td><a href=\"#\" id=\"extraFare\" data-type=\"text\" data-placement=\"right\" data-pk=\""+subLotId+"\" data-name=\"extra_fare\">"+extraFare+"</a></td>"+
	"<td><a href=\"#\" id=\"totPymt\" data-type=\"text\" data-placement=\"right\" data-pk=\""+subLotId+"\" data-name=\"tot_payment\">"+totPymt+"</a></td>"+
	"<td><a href=\"#\" id=\"totDebit\" data-type=\"text\" data-placement=\"right\" data-pk=\""+subLotId+"\" data-name=\"debit_amt\">"+debitAmt+"</a></td>"+
	"<td><a href=\"#\" id=\"totBal\" data-type=\"text\" data-placement=\"right\" data-pk=\""+subLotId+"\" data-name=\"totBal\">"+(totFare+extraFare-totPymt-debitAmt)+"</a></td>"+
	"<td><a href=\"#\" id=\"pymtDt\" data-type=\"text\" data-placement=\"right\" data-pk=\""+pymtDt+"\" data-name=\"totPymt\">"+pymtDt+"</a></td>"+
	"</tr>"	
	
	$('#FareCollectionModalTab2').html(FareCollectionModalTab2);
	
	$.fn.editable.defaults.mode = 'popup'; 
	
    $('#farePerBox').editable(	   
 		   {
         	  url:'/gadibhada/managedata/updateFarePerBox'
            });
    $('#farePerBox').on('save', function(e, params) {
	    
        var subLotId=$('#subLotId').val();
        //alert("subLotId:"+subLotId)
	    var totQty=$('#totQty').text();
    	var farePerBox=params.newValue;
    	$('#totFare').text(farePerBox*totQty)
    	
    	var extraFare=$('#extraFare').text();
    	var totPymt=$('#totPymt').text();
    	var debitAmt=$('#debitAmt').text();
    	var updatedTotFare=farePerBox*totQty
    	$('#totBal').text((farePerBox*totQty)+(extraFare-totPymt-debitAmt))
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
   
    $('#extraFare').editable(	   
  		   {
          	 // url:'/gadibhada/managedata/updateFareCollExtraFare'
             });
    $('#totPymt').editable(	   
  		   {
          	 // url:'/gadibhada/managedata/updateChallanTruck'
             });
    $('#totDebit').editable(	   
  		   {
          	 // url:'/gadibhada/managedata/updateChallanTruck'
             });
    $('#pymtDt').editable(	   
   		   {
           	 // url:'/gadibhada/managedata/updateChallanTruck'
              });
	/*waitingDialog.show();
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
		*/
}

