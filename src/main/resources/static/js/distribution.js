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
//below function required to load js functions after page is dynamically loaded

function callAjaxForUpdateDistribution() {
	waitingDialog.show();
	$.ajax({
		type : "GET",
		url : "/updateDistribution",
		data : {
			"id" : 1
		},
		success : function(data) {
			$('#menu2').html(data);

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
//Calling Ajax to load the updated distribution page dynamically
$("#distributionTab").on("click", function() {

	callAjaxForUpdateDistribution();

	//$('#updateTable').DataTable();

});

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

function saveUpdateModal() {

	var modalLotId=jQuery('input[name="modalLotId"]').val()
	var modalqtyAvl = jQuery('input[name="modalqtyAvl"]').val()
	var totQtyDistributed = jQuery('input[name="totQtyDistributed"]').val()
	var aDestId=jQuery('input[name="aDestinationId"]').val()
	var agentId = $("#agentId").val()
	var agentDestination = $('#agentDestination').text()
	var challanDate = $('#modalChallnDate').text()
	var receivingDateLbl =$('#receivingDate').text()
	var receivingDate = jQuery('input[name="receivingDate"]').val()
	if(typeof receivingDate === "undefined")
	receivingDate=receivingDateLbl
	var farePerBoxCalc = jQuery('input[name="farePerBoxCalc"]').val()
	var totalCalcFare = $('#totalCalcFare').text() 
	var localDriver = jQuery('input[name="localDriver"]').val()
	
	var cdt=new Date(challanDate)
	var rdt=new Date(receivingDate)
	var miliSecPerDay=1000*60*60*24
	if(cdt.getTime()>rdt.getTime())
		{
		alert("Receving Date Is Back Date Than Challan Date")
		rerun;
		}
	delay=(rdt.getTime()-cdt.getTime())/miliSecPerDay
	//alert("delay:"+delay)
	if(delay>4)
		{
		var confirmation=confirm("Warning !! Was Truck Late ?? Total Duration="+delay+" Days. Do You Want To Proceed")
		if(confirmation==false)
			return;
		}
	if(delay==0 )
	{
	var confirmation=confirm("Warning !! Challan Date Is Same As Receiving Date. \n  Do You Want To Proceed")
	if(confirmation==false)
		return;
	}
	
	//alert(documnet.getElementById("totQty"))
	//alert(d)

	//alert("Selected :"+agentId)

	if (parseInt(agentId.length) <= 0) {
		alert("Please Select Distribute To");
		return 10;

	}
	if (totQtyDistributed.length <= 0) {
		alert("Please Enter Distribute Qty");
		return 10;

	}
	if (agentDestination.length <= 0) {
		alert("Please Select Destination");
		return 10;
	}
	if (receivingDate.length <= 0) {
		alert("Please Enter Recieving Date");
		return 10;
	}
	if (parseInt(farePerBoxCalc.length) <= 0) {
		alert("Fare Per Box Should Not Be Empty");
		return 10;

	}
	if (parseInt(totalCalcFare.length) <= 0) {
		alert("Total Fare Must Be calculated");
		return 10;

	}
	if (parseInt(totQtyDistributed) == 0 || parseInt(modalqtyAvl) < parseInt(totQtyDistributed)) {
		alert("You Entered More Than Available");
		return 10;
	} else {
		//alert("modalqtyAvl:"+modalqtyAvl+" totQtyDistributed:"+totQtyDistributed+" agentId:"+agentId.length+" agentDestination:"+agentDestination.length)

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
					alert("Data is saved successfully");
					$("#updateDistributionModal").modal('hide');
					window.location.reload();
					//callAjaxForUpdateDistribution();
				} else
					alert("Error !!")
			}
		});
	}
	//alert("Called save");	
}
