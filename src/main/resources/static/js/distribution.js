//Calling Ajax to load the updated distribution page dynamically
$("#distributionTab").on("click", function() {

	//callAjaxForUpdateDistribution();
	  callAjaxForDistribution()
	//$('#updateTable').DataTable();

});


function calulateTotalFare()
{
	var farePerBox = jQuery('input[name="farePerBoxCalc"]').val()
	var totQtyDistributed = jQuery('input[name="totQtyDistributed"]').val()
	$('#totalCalcFare').text(parseFloat(farePerBox)*parseInt(totQtyDistributed))
}


function  fetchFarePerBox(aDestId)
{
jQuery('input[name="aDestinationId"]').val(aDestId)
var sourceId = jQuery('input[name="sourceId"]').val()
var itemId = jQuery('input[name="itemId"]').val()
var boxId = jQuery('input[name="boxId"]').val()

$.ajax({
		type : "GET",
		url : "/management/getFarePerBox",
		data : {
			"sourceId" : sourceId,
			"aDestId"  : aDestId,
			"itemId"   : itemId,
			"boxId"    : boxId
		},
		success : function(data) {
			
            $('#farePerBoxCalc').val(data)
           var totQtyDistributed = jQuery('input[name="totQtyDistributed"]').val()
           //alert(parseInt(totQtyDistributed))
         if (parseInt(totQtyDistributed)> 0) {
	     calulateTotalFare()
	     }
            //$('#totalFare').val(data*totQtyDistributed)
      
		}
});
}
function fetchAgentDestination()
{
	
var agentId = $("#agentId").val()

var call1=$.ajax({
		type : "GET",
		url : "/management/getAgentAddr",
		data : {
			"agentId" : agentId
		},
		success : function(data) {
			
            $('#agentDestination').text(data)
          
      
		}
});
$.when(call1).done(function(o1){
var aDestName= $('#agentDestination').text()
$.ajax({
	type : "GET",
	url : "/management/getAgentDestId",
	data : {
		"aDestName" : aDestName
	},
	success : function(data) {
		console.log("DestId="+data)
		//$('input[id="aDestinationId"]').val(data);
		//$('#aDestinationId').attr('value', data);
		//jQuery('input[name="aDestinationId"]').val(data)
       // $('#aDestinationId').text(data)
		fetchFarePerBox(data)
		}
});
});


}
function closeDistibutionPageModel()
{
	$("#distributionModalPage").modal('hide');
	window.location.reload();
}
function  callAjaxForDistribution()
{
	var distributionPage=""
	var ditributionModelPage=" <div id=\"distributionModalPage\" class=\"modal fade\" role=\"dialog\">"
	+"<div class=\"modal-dialog modal-lg\" >"

    +"<!-- Modal content-->"
    +"<div class=\"modal-content\">"
    +"<div class=\"modal-header\">"
    +"<button type=\"button\" class=\"close\" data-dismiss=\"modal\" onclick=\"closeDistibutionPageModel()\">&times;</button>"
    +"<h4 class=\"modal-title\">Distribution For Challan</h4>"
    +" </div>"
    +"<div class=\"modal-body\" id=\"distributionPagemodalContent\">"
    +"<p>Some text in the modal.</p>"
    +"</div>"
    +"<div class=\"modal-footer\">"
    //+"<button id=\"save\" class=\"btn btn-width bkgrnd-cyan save-details\" type=\"button\" name=\"save-details\" onclick=\"saveUpdateModal()\" data-toggle=\"modal\"  >Save</button>"
    +" <button type=\"button\" class=\"btn btn-default\" onclick=\"closeDistibutionPageModel()\" >Close</button>"
    + " </div> </div> </div> </div>"
	
    var updateDistributionModal=
    "<div id=\"updateDistributionModal\" class=\"modal fade\" role=\"dialog\">"
	+"<div class=\"modal-dialog modal-lg\" >"
    +"<!-- Modal content-->"
    +"<div class=\"modal-content\">"
    +"<div class=\"modal-header\">"
   // +"<button type=\"button\" class=\"close\" >&times;</button>"
    +"<h4 class=\"modal-title\">Distribution</h4>"
    +" </div>"
    +"<div class=\"modal-body\" id=\"modalContent\">"
    +"<p>Some text in the modal.</p>"
    +"</div>"
    +"<div class=\"modal-footer\">"
    +"<button id=\"save\" class=\"btn btn-width bkgrnd-cyan save-details\" type=\"button\" name=\"save-details\" onclick=\"saveUpdateModal()\" data-toggle=\"modal\"  >Save</button>"
    +" <button type=\"button\" class=\"btn btn-default\" onclick=\"closeUpdateModal()\">Close</button>"
    + " </div> </div> </div> </div>"
    
    var tableHeader=""
    var tableBody="";
	var tableFooter=""
		tableHeader=tableHeader+" <h3>Distribution Page </h3> "
		+ "<table id=\"distributionPageTable\" class=\"table table-striped table-bordered\" style=\"width:100%;background-color: #E2E2E2\">\r\n" + 
		"        <thead>\r\n" + 
		"            <tr>\r\n" + 
		"                <th>Challan Id</th>\r\n" + 
		"                <th>Date</th>\r\n" + 
		"                <th>Truck No</th>\r\n" + 
		"                <th>From-To-Where</th>\r\n" + 
		"                 <th>Driver Name</th>\r\n"+
		"                 <th>Driver Mobile</th>\r\n"+
		"                <th>Action</th>\r\n" + 
		"            </tr>\r\n" + 
		"        </thead>\r\n" + 
		"        <tbody>\r\n" + 
	waitingDialog.show();
	$.ajax({
		type : "GET",
		url : "/allChallanList",
		success : function(data) {
			var i=0;
			var result=jQuery.parseJSON(data);
			$.each( result,function(k,v){
				
				tableBody=tableBody+"<tr>"+		
				"<td><a href=\"#\">"+v.challanId+"</a></td>"+
				"<td><a href=\"#\">"+v.challanDate+"</a></td>"+
				"<td><a href=\"#\">"+v.truckNo+"</a></td>"+
				"<td><a href=\"#\">"+v.sourceName+"-"+v.destination+"</a></td>"+
				"<td><a href=\"#\">"+v.driverName+"</a></td>"+
				"<td><a href=\"#\">"+v.driverMobile+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#distributionModalPage\"" +
				" onclick=\"getAllLotForChallan('"+v.challanId+"')\">Detail</button></td>"+
				
				"</tr>"	
				
				i++;
			});
			 tableFooter=tableFooter+"        </tbody>\r\n" + 
			"        <tfoot>\r\n" + 
			"            <tr>\r\n" + 
			"                <th>Challan Id</th>\r\n" + 
			"                <th>Date</th>\r\n" + 
			"                <th>Truck No</th>\r\n" + 
			"                <th>From-To-Where</th>\r\n" + 
			"                 <th>Driver Name</th>\r\n"+
			"                 <th>Driver Mobile</th>\r\n"+
			"                <th>Action</th>\r\n" + 
			"            </tr>\r\n" + 
			"        </tfoot>\r\n" + 
			"    </table>"
	        distributionPage=ditributionModelPage+updateDistributionModal+tableHeader+tableBody+tableFooter
	        $('#menu2').html(distributionPage);
			loadjs("/js/dataTable.js");
			$('#distributionTable').DataTable();
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

function getAllLotForChallan(challanId)
{
	
	callAjaxForUpdateDistribution(challanId)	
}
//below function required to load js functions after page is dynamically loaded

function callAjaxForUpdateDistribution(challandId) {
	waitingDialog.show();
	$.ajax({
		type : "GET",
		url : "/updateDistribution",
		data : {
			"challanId" : challandId
		},
		success : function(data) {
			$('#distributionPagemodalContent').html(data);

			//loadjs("/js/dataTable.js");
			//$('#distributionPagemodalContent').DataTable();
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


function loadUpdateModal(lotId,challanDate,truckNo,sourceId,sourceName,destination,traderName,traderMark,itemId,itemName,boxId,boxName,boxWt) {
	//alert("Coming from modal");
	//alert(lotId);
	$.ajax({
		type : "GET",
		url : "/loadUpdateModal",
		data : {
			"lotId" : lotId,
			"challanDate" : challanDate,
			"truckNo" : truckNo,
			"sourceId" : sourceId,
			"sourceName" : sourceName,
			"destination" : destination,
			"traderName" : traderName,
			"traderMark" : traderMark,
			"itemId" : itemId,
			"itemName" : itemName,
			"boxId" : boxId,
			"boxName" : boxName,
			"boxWt" : boxWt
			
		},
		success : function(data) {
			//alert(data)
			//loadjs("/js/dataTable.js");
			$('#modalContent').html(data);

			//loadjs("/js/dataTable.js");
			//$('#updateTable').DataTable();

		}
	});
}

function closeUpdateModal()
{
	
	$("#updateDistributionModal").modal('hide');

}
function checkAvailablity()
{
	$("#errorMsgDisMdl").html("");
	var receivingDateLbl =$('#receivingDate').text()
	var receivingDate = jQuery('input[name="receivingDate"]').val()
	if(typeof receivingDate === "undefined")
	receivingDate=receivingDateLbl
	var localDriver = jQuery('input[name="localDriver"]').val()

	if(localDriver.length>0)
		checkDriverAvailablity();
}
function checkDriverAvailablity()
{
	$("#errorMsgDisMdl").html("");
	var receivingDateLbl =$('#receivingDate').text()
	var receivingDate = jQuery('input[name="receivingDate"]').val()
	if(typeof receivingDate === "undefined")
	receivingDate=receivingDateLbl
	var localDriver = jQuery('input[name="localDriver"]').val()
	
	if (receivingDate.length <= 0) {
		$("#errorMsgDisMdl").html("Please Enter Recieving Date");
		return 10;
	}
		$.ajax({
			type : "POST",
			url : "/management/checkLocalVehicleBooked",
			data : {
				"receivingDate" : receivingDate,
				"localDriver" : localDriver
		
			},
			success : function(data) {
               
				if (data === "Record Is Present") {
					$("#errorMsgDisMdl").html("For Date:"+receivingDate +" Local Fare Entry Is Already Done For "+localDriver)
				    
				} 
			}
		});
	
}
 function checkTruckArrived()
{
	return new Promise((resolve, reject) => {
		var startDt = $('#modalChallnDate').text()
		var truckNo = $('#modalChallanTruck').text()
         startDt=startDt.split("/").reverse().join("-");
          setTimeout(() => {
        	  $.ajax({
        			type : "POST",
        			url : "/management/checkTruckArrived",
        			data : {
        				"truckNo" : truckNo,
        				"startDt" : startDt
        		
        			},
        			success : function(data) {
        	           
        				if (data === "Record Is Present") {
        					
        				   
        				resolve(true)
        				} else{
        					$("#errorMsgDisMdl").html("Can not Save !! Reason:-For Date:"+startDt+ " And Truck No:"+truckNo+ " Has Not Reached <br> Please Update Arrival Date Under <a href=\"javascript:activateTruckLedger('#menu6')\">Truck Ledger</a>")
        					resolve(false)
        				
        				}
        			}
        		});
             console.log('loop completed')  
             
          }, 2000)
      })
	
	

}

async function saveUpdateModal() {
	waitingDialog.show("Saving The Data");
	var isTruckArrived=false;
	
    var challanId=$('#lotChallanId').text()
	var modalLotId=jQuery('input[name="modalLotId"]').val()
	var modalqtyAvl = jQuery('input[name="modalqtyAvl"]').val()
	var totQtyDistributed = jQuery('input[name="totQtyDistributed"]').val()
	var aDestId=jQuery('input[name="aDestinationId"]').val()
	var agentId = $("#agentId").val()
	var agentDestination = $('#agentDestination').text()
	var challanDate = $('#modalChallnDate').text()
    var truckNo = $('#modalChallanTruck').text()
	var receivingDateLbl =$('#receivingDate').text()
	var receivingDate = jQuery('input[name="receivingDate"]').val()
	if(typeof receivingDate === "undefined")
	receivingDate=receivingDateLbl
	var farePerBoxCalc = jQuery('input[name="farePerBoxCalc"]').val()
	var totalCalcFare = $('#totalCalcFare').text() 
	var localDriver = jQuery('input[name="localDriver"]').val()
	
	
	var newchallanDate = challanDate.split("/").reverse().join("-");
	
    var cdt=new Date(newchallanDate)
	var rdt=new Date(receivingDate)
    
	var miliSecPerDay=1000*60*60*24
	if(cdt.getTime()>rdt.getTime())
		{
		alert("Receving Date Is Back Date Than Challan Date")
		waitingDialog.hide();
		rerun;
		}
	delay=(rdt.getTime()-cdt.getTime())/miliSecPerDay
	//alert("delay:"+delay)
	if(delay>4)
		{
		var confirmation=confirm("Warning !! Was Truck Late ?? Total Duration="+delay+" Days. Do You Want To Proceed")
		if(confirmation==false){
			return;
			waitingDialog.hide();
		}
		}
	if(delay==0 )
	{
	var confirmation=confirm("Warning !! Challan Date Is Same As Receiving Date. \n  Do You Want To Proceed")
	if(confirmation==false){
		waitingDialog.hide();
		return;
	}
	}
	
	//alert(documnet.getElementById("totQty"))
	//alert(d)

	//alert("Selected :"+agentId)

	if (parseInt(agentId.length) <= 0) {
		alert("Please Select Distribute To");
		waitingDialog.hide();
		return 10;

	}
	if (totQtyDistributed.length <= 0) {
		alert("Please Enter Distribute Qty");
		waitingDialog.hide();
		return 10;

	}
	if (agentDestination.length <= 0) {
		alert("Please Select Destination");
		waitingDialog.hide();
		return 10;
	}
	if (receivingDate.length <= 0) {
		alert("Please Enter Recieving Date");
		waitingDialog.hide();
		return 10;
	}
	if (parseInt(farePerBoxCalc.length) <= 0) {
		alert("Fare Per Box Should Not Be Empty");
		waitingDialog.hide();
		return 10;

	}
	if (parseInt(totalCalcFare.length) <= 0) {
		alert("Total Fare Must Be calculated");
		waitingDialog.hide();
		return 10;

	}
	if (parseInt(totQtyDistributed) == 0 || parseInt(modalqtyAvl) < parseInt(totQtyDistributed)) {
		alert("You Entered More Than Available");
		waitingDialog.hide();
		return 10;
	} 
	var isTruckArrived=await checkTruckArrived();
	if(!isTruckArrived){
		waitingDialog.hide();
		return
		
	}
	else {
		//alert("modalqtyAvl:"+modalqtyAvl+" totQtyDistributed:"+totQtyDistributed+" agentId:"+agentId.length+" agentDestination:"+agentDestination.length)
		$.ajax({
			type : "POST",
			url : "/management/checkLocalVehicleBooked",
			data : {
				"receivingDate" : receivingDate,
				"localDriver" : localDriver
		
			},
			success : function(data) {
               
				if (data === "Record Is Present") {
					$("#errorMsgDisMdl").html("Can not Save !! Reason-For Date:"+receivingDate +" Local Fare Entry Is Already Done For "+localDriver)
				   
					waitingDialog.hide();
				} else{
					userJson = $("form").serializeArray();
					$.ajax({
						type : "POST",
						url : "/saveUpdateModal",
						data : {
							"modalLotId" : modalLotId,
							"agentId" : agentId,
							"aDestId" : aDestId,
							"totQtyDistributed" : totQtyDistributed,
							"receivingDate" : receivingDate,
							"farePerBoxCalc" : farePerBoxCalc,
							"totalCalcFare" : totalCalcFare,
							"localDriver" : localDriver
					
						},
						success : function(data) {

							if (data === "success") {
								$("#goodRespnsMsgDisMdl").html("Data Is Saved Successfully")
								$("#updateDistributionModal").modal('hide');
								//window.location.reload();
								callAjaxForUpdateDistribution(challanId);
								waitingDialog.hide();
							} else{
								$("#errorMsgDisMdl").html("Error !! Data Is Not Saved")
								waitingDialog.hide();	
							}
						}
					});
					
				}
			}
		});
		
	}
	//alert("Called save");	
}
