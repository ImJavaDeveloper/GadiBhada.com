var call1;
var call2;
var call3;
var call4;
var call5;
var call6;
var call7;


$(document).ready(function(){
 // $('[data-toggle="tooltip"]').tooltip();   
	 $('[data-toggle="popover"]').popover()
	
});
//Calling Ajax to load the page dynamically
$("#dailyLedgerTab").on("click", function() {

	loadDailyLedger();

});

function loadDailyLedger()
{
	$('#menu7').load('/formcontent/dailyLedger.html',function()
			{
		
			});
		
}

$( '#truckPymtTot' ).change(function() {
	  alert( "Handler for .ledgerDt() called." );
	});


function fetchDailyLedgerData()
{
	waitingDialog.show()
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	//alert(ledgerDt)
	fetchOpeningBalance(ledgerDt)
	fetchTruckPaymentCollection(ledgerDt)
	fetchBnkTransferTot(ledgerDt)
	fetchTodayLocalFare(ledgerDt)
	fetchMoneyWithDrawlTot(ledgerDt)
	fetchOtherExpenseTot(ledgerDt)
	 $('#thekedariAmt').val(0)
	fetchDailyCollection(ledgerDt)	

	
	// $(document).trigger('fetchDailyLedgerData_Complete');
	
	
	$.when(call1,call2,call3,call4,call5,call6,call7).done(function(o1, o2, o3,o4,o5,o6,o7) {
		getTotalBalForToday()
	});
	
	waitingDialog.hide()
}
//$(document).bind('fetchDailyLedgerData_Complete', getTotalBalForBal);

function fetchOpeningBalance(ledgerDt)
{
	call1=$.ajax({
		type : "POST",
		url : "/management/totalOpeningBal",
		data : {
			"ledgerDt":ledgerDt			
		},			
		success : function(data) {
           
           $('#openingBal').val(data)
			
         },
		 error: function (XMLHttpRequest, textStatus, errorThrown)
		 {
				if (XMLHttpRequest.readyState == 4) {
			        // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
					alert("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
			    }
			    else if (XMLHttpRequest.readyState == 0) {
			        // Network error (i.e. connection refused, access denied due to CORS, etc.)
			    	
			    	alert("Error !! Server Is Down. Please contact server admin");
			    }
			    else {
			    	alert("Unknown Error !!")
			    }
			   
			  //some stuff on failure
			}
		    
	});
}

function fetchTruckPaymentCollection(ledgerDt)
{
	call2=$.ajax({
		type : "POST",
		url : "/management/totalTruckPymtByDt",
		data : {
			"ledgerDt":ledgerDt			
		},			
		success : function(data) {
           
			prevTodayBal=$('#todayBal').val()
			prevtruckPymtTot=$('#truckPymtTot').val()
			
			$('#truckPymtTot').val(data)
			$('#todayBal').val(parseFloat(prevTodayBal)-(parseFloat(data)-parseFloat(prevtruckPymtTot)))
			
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		alert("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	alert("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	alert("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}


function fetchBnkTransferTot(ledgerDt)
{
	call3=$.ajax({
		type : "POST",
		url : "/gaadibhada/dataentry/dailyledger/bnktrnsfrByLedgerDt",
		data : {
			"ledgerDt":ledgerDt			
		},			
		success : function(data) {
			prevTodayBal=$('#todayBal').val()
			prevbnkTransferTot=$('#bnkTransferTot').val()
			$('#bnkTransferTot').val(data)
           $('#todayBal').val(parseFloat(prevTodayBal)-(parseFloat(data)-parseFloat(prevbnkTransferTot)))
           
			
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		alert("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	alert("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	alert("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}
function fetchTodayLocalFare(ledgerDt)
{
	call4=$.ajax({
		type : "POST",
		url : "/management/fetchTodayLocalFare",
		data:{

			"ledgerDt"    :ledgerDt
	
		},
		success : function(data) {
			prevTodayBal=$('#todayBal').val()
			prevlocalFareTot=$('#localFareTot').val()
			$('#localFareTot').val(data)
           $('#todayBal').val(parseFloat(prevTodayBal)-(parseFloat(data)-parseFloat(prevlocalFareTot)))
			
		
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
function fetchMoneyWithDrawlTot(ledgerDt)
{
	call5=$.ajax({
		type : "POST",
		url : "/gaadibhada/dataentry/dailyledger/moneyWithdrwlByLedgerDt",
		data : {
			"ledgerDt":ledgerDt			
		},			
		success : function(data) {
           
         prevTodayBal=$('#todayBal').val()
			prevMoneyWithDrawlTot=$('#moneyWithDrawlTot').val()
			$('#moneyWithDrawlTot').val(data)
           $('#todayBal').val(parseFloat(prevTodayBal)-(parseFloat(data)-parseFloat(prevMoneyWithDrawlTot)))
			
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		alert("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	alert("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	alert("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}

function fetchOtherExpenseTot(ledgerDt)
{
	call6=$.ajax({
		type : "POST",
		url : "/gaadibhada/dataentry/dailyledger/otherExpnsByLedgerDt",
		data : {
			"ledgerDt":ledgerDt			
		},			
		success : function(data) {

           prevTodayBal=$('#todayBal').val()
			prevOtherExpenseTot=$('#otherExpenseTot').val()
			$('#otherExpenseTot').val(data)
           $('#todayBal').val(parseFloat(prevTodayBal)-(parseFloat(data)-parseFloat(prevOtherExpenseTot)))
			
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		alert("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	alert("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	alert("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}

function fetchDailyCollection(ledgerDt)
{
	call7=$.ajax({
		type : "POST",
		url : "/management/totalCollectionByDt",
		data : {
			"ledgerDt":ledgerDt			
		},			
		success : function(data) {

          
			prevTodayBal=$('#todayBal').val()
			prevTodayCollAmt=$('#todayCollAmt').val()
			$('#todayCollAmt').val(data)

           $('#todayBal').val(parseFloat(prevTodayBal)+(parseFloat(data)-parseFloat(prevTodayCollAmt)))
			
         },
		 error: function (XMLHttpRequest, textStatus, errorThrown)
		 {
				if (XMLHttpRequest.readyState == 4) {
			        // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
					alert("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
			    }
			    else if (XMLHttpRequest.readyState == 0) {
			        // Network error (i.e. connection refused, access denied due to CORS, etc.)
			    	
			    	alert("Error !! Server Is Down. Please contact server admin");
			    }
			    else {
			    	alert("Unknown Error !!")
			    }
			   
			  //some stuff on failure
			}
		    
	});
}

function loadCollectionsPage()
{
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	
	if(ledgerDt.length<=0)
	{
	$("#todayCollectionsModal").modal('hide');
	//$('#formMsgDL').html("Please Enter Ledger Date")
	alert("Please Enter Ledger Date")
	return;
     }
	waitingDialog.show()
	callAjaxForCollectionPage(ledgerDt);
	waitingDialog.hide()
}
function closeLocalFareEntryModelPage()
{
        var ledgerDt=jQuery('input[name="ledgerDt"]').val()
		fetchTodayLocalFare(ledgerDt);  
        $("#localFareEntryModal").modal('hide')

}
function closeTotalMoneyWithDraw()
{
        var ledgerDt=jQuery('input[name="ledgerDt"]').val()
		fetchMoneyWithDrawlTot(ledgerDt); 
        $("#moneyWithdrawlCollectionsModal").modal('hide')

}
function closeOtherExpense()
{
        var ledgerDt=jQuery('input[name="ledgerDt"]').val()
		fetchOtherExpenseTot(ledgerDt); 
        $("#otherExpenseCollectionsModal").modal('hide')

}

function closeCollectionModelPage()
{
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	
	$("#todayCollectionsModal").modal('hide');
	//window.location.reload();
	if(ledgerDt.length>0)
		{
		
		fetchDailyCollection(ledgerDt);  
		
		}
}
function closeCollectionPopUp()
{
	$("#collectionsModal").modal('hide');
	$("#todayCollectionsModal").modal('show');
}

function getTrucksLedger()
{
	clearformMsgDL()
	var tableContent=""
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	
	if(ledgerDt.length<=0)
	{
	//$('#formMsgDL').html("Please Enter Ledger Date")
		alert("Please Enter Ledger Date")
	$("#truckPaymentCollectionsModal").modal('hide');
	return;
     }
	
	
	var tableBody="<div id=\"errorMsgTLdgr\" style=\"color:red\"></div>" +
	"<div id=\"goodRespnsMsg\" style=\"color:green\"></div>" +
	"<style>"+
	"table thead tr th {font-size: 11px;}"+
	"table tfoot tr th {font-size: 11px;}"+
	"table tbody tr td { font-size: 12px;}"+
	"</style>"+
"<table id=\"truckPendingBalTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>Truck No</th>\r\n" + 
"			<th>Start Date</th>\r\n" + 
"			<th>From-Where</th>\r\n" + 
"			<th>Arrival Date</th>\r\n" + 
"			<th>Total Balance</th>\r\n" + 
"			<th>Action</th>\r\n" + 

"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"
 ;
	
	
	$.ajax({
		type : "POST",
		url : "/gadibhada/dataentry/truckLedger",
		
		success : function(data) {
		
			var i=0;
			var result=jQuery.parseJSON(data);
			
			$.each( result,function(k,v){

				tableContent=tableContent+
				"<tr>"+
			    "			<td>"+v.truckNo+"</td>\r\n" + 
			    "			<td>"+v.stratDt+"</td>\r\n" + 
                "			<td>"+v.FromToWhere+"</td>\r\n"+ 
			    "			<td>"+v.endDt+"</td>\r\n"+ 
			    "			<td id=\"truckTotBal\">"+v.totBal+"</td>\r\n" 
			    if(v.endDt=="" || v.totFare==0 || v.totBal<0 )
			    	{
			    	tableContent=tableContent+
			    	"<td>Missing Details</td>"+
			    	"	</tr>";
			    	}
			    else{
			    	tableContent=tableContent+
			    "<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#truckPaymentModal\"" +
				" onclick=\"updateTruckBalance('"+v.truckNo+"','"+v.stratDt+"','"+v.FromToWhere+"','"+v.endDt+"','"+v.advFare+"','"+v.prizeFare+"','"+v.totFare+"','"+v.totBal+"')\">View Details</button></td>"+
			 
				"	</tr>";
			    }
				i++;
			});
			

			var tableFooter=""+
                          "   </tbody>"+
                          "   </table>"
 

$('#truckPaymentCollModalContent').html(tableBody+tableContent+tableFooter);
$('#truckPendingBalTable1').DataTable();
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
				waitingDialog.hide();
				// something weird is happening
			}

			//some stuff on failure
		}
	});
}

function updateTruckBalance(truckNo,stratDt,FromToWhere,endDt,advFare,prizeFare,totFare,totBal)
{
	 $('#saveTruckPymtBtn').prop("disabled", false);
	$("#truckPaymentCollectionsModal").modal('hide');
	//$("#truckPaymentModal").modal('show');

		var pymtDt="<input type=\"date\" name=\"pymtDtDledger\" class=\"form-control\" required oninput=\"clearErrorMsg()\" >";
		var pymtAmt="<input type=\"text\" name=\"truckPymtAmtDledger\" class=\"form-control\" required placeholder=\"Enter Payment\"  oninput=\"clearErrorMsg()\" >";

	var table="<div id=\"errorMsgTrMdl\" style=\"color:red\"></div>" +
	"<div id=\"goodRespnsMsgTrMdl\" style=\"color:green\"></div>" +
	"<style>"+
	"table thead tr th {font-size: 11px;}"+
	"table tfoot tr th {font-size: 11px;}"+
	"table tbody tr td { font-size: 12px;}"+
	"</style>"+
"<table id=\"truckLedgerTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>Truck No</th>\r\n" + 
"			<th>Start Date</th>\r\n" + 
"			<th>From-Where</th>\r\n" + 
"			<th>Arrival Date</th>\r\n" + 
"			<th>Advance Fare</th>\r\n" + 
"			<th>Prize Fare</th>\r\n" + 
"			<th>Total Fare</th>\r\n" +


"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"+
    "<tr>" + 
    "			<td id=\"truckNoDledger\">"+truckNo+"</td>\r\n" + 
    "			<td id=\"truckStDtLedger\">"+stratDt+"</td>\r\n" + 
    "			<td>"+FromToWhere+"</td>\r\n"+ 
    "			<td>"+endDt+"</td>\r\n" + 
    "			<td id=\"truckEndDtLbl\">"+advFare+"</td>\r\n" + 
    "			<td id=\"truckPrizeFareLbl\">"+prizeFare+"</td>\r\n" + 
    "			<td id=\"truckTotFareLbl\">"+totFare+"</td>\r\n" + 


 "	</tr>" +  
"   </tbody>"+
"   </table>"+
 "<table id=\"truckLedgerTable2\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
 "	<thead>\r\n" + 
 "		<tr>\r\n" + 
 "			<th>Total Balance</th>\r\n" + 
 "			<th>Payment Amout</th>\r\n" + 
 "			<th>Payment Date</th>\r\n" + 
 "		</tr>\r\n" + 
 "	</thead>\r\n" + 
 "	<tbody>"+
     "<tr>" + 
     "			<td id=\"truckTotalBalLbl\">"+totBal+"</td>\r\n" + 
     "			<td>"+pymtAmt+"</td>\r\n" + 
     "			<td>"+pymtDt+"</td>\r\n" + 
  "	</tr>" +  
 "   </tbody>"
 "   </table>"
		
	$('#truckPaymentModalContent').html(table);
}

function closeTruckPaymentModal()
{
	getTrucksLedger()
	$("#truckPaymentCollectionsModal").modal('show');
}
function closeTruckPaymentCollModal()
{
var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	
	//window.location.reload();
	if(ledgerDt.length>0)
		{
		
		fetchTruckPaymentCollection(ledgerDt);  
		
		}
}

function saveTruckPayment()
{
	$('#goodRespnsMsgTrMdl').html("")
	 $('#errorMsgTrMdl').html("")
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	var truckNoDledger=$('#truckNoDledger').text();
	var truckStDtLedger=$('#truckStDtLedger').text();
	var truckTotBal=$('#truckTotalBalLbl').text();
	var pymtDt=jQuery('input[name="pymtDtDledger"]').val()
	var truckPymtAmtDledger=jQuery('input[name="truckPymtAmtDledger"]').val()
	
	truckStDtLedger = truckStDtLedger.split("/").reverse().join("-");
	
	if(truckPymtAmtDledger.length<=0){
		$("#errorMsgTrMdl").html("Enter Payment Amount!!")
	    return
	}
	
	if(parseFloat(truckPymtAmtDledger)>parseFloat(truckTotBal)){
		$("#errorMsgTrMdl").html("Payment Is Greater Than Balance !!")
	    return
	}
	
	if(pymtDt.length<=0){
		$("#errorMsgTrMdl").html("Please Enter Payment Date !!")
	    return
	}
	
	//alert("ledgerDt:"+ledgerDt+"\n"+"truckNoLedger:"+truckNoDledger+"truckStDtLedger:"+truckStDtLedger)
	//return
	$.ajax({
		type : "POST",
		url : "/gadibhada/dataentry/truckPayment",
		data : {
			"truckNoLedger" : truckNoDledger,
			"truckStDtLedger"  : truckStDtLedger,
			"pymtAmt"   : truckPymtAmtDledger,
			"pymtDt"    : pymtDt,
			"ledgerDt"  : ledgerDt
		},
		success : function(data) {
			if(data === "Success"){
             $('#goodRespnsMsgTrMdl').html("Truck Payment Is Saved Successfully")
             $('#saveTruckPymtBtn').prop("disabled", true);
			}
             else
            	 $('#errorMsgTrMdl').html(data)
      
		}
});

}

function clearformMsgDL()
{
	$("#formMsgDL").html(" ");

}
function clearErrorMsg()
{
	$("#errorMsgDLdgr").html(" ");

}



function loadLocalFareEntry()
{
	    clearformMsgDL()
	    var tableContent=""
	   
		var ledgerDt=jQuery('input[name="ledgerDt"]').val()
		
		if(ledgerDt.length<=0)
		{
		$("#localFareCollectionsModal").modal('hide');
		//$('#formMsgDL').html("Please Enter Ledger Date")
		alert("Please Enter Ledger Date")
		return;
	     }
		
		
		var tableBody="<div id=\"errorMsgDLdgr\" style=\"color:red\"></div>" +
		"<div id=\"goodRespnsMsg\" style=\"color:green\"></div>" +
		"<style>"+
		"table thead tr th {font-size: 11px;}"+
		"table tfoot tr th {font-size: 11px;}"+
		"table tbody tr td { font-size: 12px;}"+
		"</style>"+
	"<table id=\"localFareEntryTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
	"	<thead>\r\n" + 
	"		<tr>\r\n" + 
	"			<th>TruckNo</th>\r\n" + 
	"			<th>ArrivalDate</th>\r\n" + 
	"			<th>LocalDriver</th>\r\n" + 
	"			<th>From</th>\r\n" + 
	"			<th>To</th>\r\n" + 
	"			<th>TotalWt(Qtl)</th>\r\n" + 
	"			<th>TotalFare</th>\r\n" + 
	"			<th>Action</th>\r\n" + 
	"			<th>Action</th>\r\n" +

	"		</tr>\r\n" + 
	"	</thead>\r\n" + 
	"	<tbody>"
	 ;
		
		
		$.ajax({
			type : "POST",
			url : "/management/fetchLocalFareEntry",
			data:{
				"ledgerDt":ledgerDt
			},
			success : function(data) {
			
				var i=0;
				var result=jQuery.parseJSON(data);
				
				$.each( result,function(k,v){
					 var totaLocalFareTxtBox="<input type=\"text\" name=\"totalLocalFare"+i+"\"  id=\"totalLocalFare"+i+"\" class=\"form-control\" required value=\""+v.totLocalFare+"\"  oninput=\"clearErrorMsg()\">";
						
					tableContent=tableContent+
					"<tr >"+
				    "			<td>"+v.truckNo+"</td>\r\n" + 
				    "			<td>"+v.arrivalDt+"</td>\r\n" + 
	                "			<td>"+v.localDriver+"</td>\r\n"+ 
				    "			<td>"+v.source+"</td>\r\n"+ 
				    "			<td>"+v.destinations+"</td>\r\n"+ 
				    "			<td>"+(v.totalWt)/100+"</td>\r\n" 
				    if(v.arrivalDt=="")
				    	{
				    	tableContent=tableContent+
				    	"<td>Missing Arrival Date</td>"+
				    	"<td>Missing Arrival Date</td>"
				         ;
				    	}
				    else{
				    	tableContent=tableContent+
				    	"<td>"+totaLocalFareTxtBox+"</td>"+
				    "<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" id=\"button"+i+"\"" +
					" onclick=\"saveLocalFareEntry("+i+",'"+v.truckNo+"','"+v.arrivalDt+"','"+v.localDriver+"','"+v.source+"','"+v.destinations+"','"+v.totLocalFare+"','"+v.totalWt+"',"+i+")\">Save</button></td>"
				      ;
				    }
					tableContent=tableContent+
					"<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#localFareDetailModel\"" +
					" onclick=\"viewLocalFareDetails('"+v.truckNo+"','"+v.arrivalDt+"','"+v.localDriver+"','"+v.source+"','"+v.destinations+"','"+(v.totalWt)/100+"','"+v.totLocalFare+"')\" >Details</button></td>"+
					"	</tr>";
					i++;
				});
				

				var tableFooter= 
	                          "   </tbody>"+
	                          "   </table>"
	 

	$('#localFareEntryModalContent').html(tableBody+tableContent+tableFooter);
	$('#localFareEntryTable1').DataTable();
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
					waitingDialog.hide();
					// something weird is happening
				}

				//some stuff on failure
			}
		});
}


function viewLocalFareDetails(truckNo,arrivalDt,localDriver,source,destinations,totWt,totFare)
{
	//arrivalDt = arrivalDt.split("/").reverse().join("-");
	
	var truckNos=[]
	truckNos=truckNo.split(",");
	var formatedtruckNo = "";
	for( j=0;j<truckNos.length;j++)
	   {
		
		formatedtruckNo=formatedtruckNo+"'"+truckNos[j]+"'";
		if(j!=truckNos.length-1)
			formatedtruckNo=formatedtruckNo+","
				
	   }
	var arrivalDts=[]
	arrivalDts=arrivalDt.split(",");
	var formatedArrvlDt = "";
	for( j=0;j<arrivalDts.length;j++)
	   {
		
		formatedArrvlDt=formatedArrvlDt+"'"+arrivalDts[j].split("/").reverse().join("-")+"'";
		if(j!=arrivalDts.length-1)
			formatedArrvlDt=formatedArrvlDt+","
				
	   }
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	$('#localFareEntryModal').modal('hide')
	
	var totalWtOfVehicle=0
	var tableContent=""
	
 var tableBody=
"<style>"+
"table thead tr th {font-size: 11px;}"+
"table tfoot tr th {font-size: 11px;}"+
"table tbody tr td { font-size: 12px;}"+
"</style>"+
"<table id=\"lfareDetailTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>Truck No</th>\r\n" + 
"			<th>Arrival Date</th>\r\n" + 
"			<th>Local Driver</th>\r\n" + 
"			<th>From</th>\r\n" + 
"			<th>To</th>\r\n" + 
"			<th>Total Wt(Qtl)</th>\r\n" + 
"			<th>Total Fare</th>\r\n" + 
"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"+
"<tr>" +
"			<td>"+truckNo+"</td>\r\n" + 
"			<td>"+arrivalDt+"</td>\r\n" + 
"			<td>"+localDriver+"</td>\r\n" + 
"			<td>"+source+"</td>\r\n" + 
"			<td>"+destinations+"</td>\r\n" + 
"			<td>"+totWt+"</td>\r\n" + 
"			<td>"+totFare+"</td>\r\n" + 
"	</tr>" +  
"   </tbody>"+
"   </table>"+
"<table id=\"lfareDetailTable2\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>SubLotId</th>\r\n" +
"			<th>Item</th>\r\n" + 
"			<th>Box Type</th>\r\n" + 
"			<th>Qty</th>\r\n" + 
"			<th>Wt(Kg)</th>\r\n" + 
"			<th>Agent</th>\r\n" + 
"			<th>Destination</th>\r\n" + 
"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>";

	$.ajax({
	type : "POST",
	url : "/management/fetchLocalFareDetail",
	data:{
		"truckNo":formatedtruckNo,
		"arrivalDt":formatedArrvlDt,
		"localDriver":localDriver,
		"ledgerDt":ledgerDt
	},
	success : function(data) {

		var result=jQuery.parseJSON(data);
		
		$.each( result,function(k,v){
			console.log("subLotId:"+v.subLotId)
			tableContent=tableContent+
			"<tr>" + 
 "			<td>"+v.subLotId+"</td>\r\n" + 
 "			<td>"+v.itemName+"</td>\r\n" + 
 "			<td>"+v.boxType+"</td>\r\n" + 
 "			<td>"+v.totalQty+"</td>\r\n" + 
 "			<td>"+v.totalWt+"</td>\r\n" + 
 "			<td>"+v.agent+"</td>\r\n" + 
 "			<td>"+v.agentDest+"</td>\r\n" + 
 "	</tr>" ; 

totalWtOfVehicle=totalWtOfVehicle+parseInt(v.totalWt);
		});
		
		console.log(tableContent)
		var tableFooter=
			"<tr>" + 
			"<td colspan=\"4\"  align=\"right\">Total Goods Wt(Qtl)</td>"+
			"<td colspan=\"3\" align=\"left\">"+(totalWtOfVehicle/100)+"</td>"+
			"</tr>" 
	"   </tbody>"+
	"   </table>";

	$('#localFareDetailsModalContent').html(tableBody+tableContent+tableFooter);
	
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
function closeLocalFareDetailsModal()
{

	$('#localFareEntryModal').modal('show')	
}

function saveLocalFareEntry(seq,truckNo,arrivalDt,localDriver,source,destinations,totFare,totWt,seqNo)
{
	var arrivalDts=[]
	arrivalDts=arrivalDt.split(",");
	var formatedArrvlDt = "";
	for( j=0;j<arrivalDts.length;j++)
	   {
		
		formatedArrvlDt=formatedArrvlDt+arrivalDts[j].split("/").reverse().join("-");
		if(j!=arrivalDts.length-1)
			formatedArrvlDt=formatedArrvlDt+","
				
	   }
	   
		//var totalLocalFare=jQuery("input[name='totalLocalFare"+seq+"']\"").val()
	    var totalLocalFare=$('#totalLocalFare'+seq).val()
		
		if(totalLocalFare<=0 ||totalLocalFare.length<=0){
		$('#errorMsgDLdgr').html("Please Enter Total Fare !!")
		return
		}
		
        var ledgerDt=jQuery('input[name="ledgerDt"]').val()
		
		
	$.ajax({
		type : "POST",
		url : "/gadibhada/dataentry/saveLocalFareDetail",
		data:{
			"truckNo"     :truckNo,
			"arrivalDt"   :formatedArrvlDt,
			"localDriver" :localDriver,
			"ledgerDt"    :ledgerDt,
			"totFare"     :totalLocalFare,
			"source"      :source,
			"destinations":destinations,
			"totWt"       :totWt		
		},
		success : function(data) {
			if(data=="Success"){
				$("#goodRespnsMsg").html("Fare Has Been Saved Successfully")
			    var saveButton=$('#button'+seqNo)
			    $(saveButton).prop("disabled", true);
			}
				else{
					$("#errorMsgTLdgr").html("Error !! Fare Is Not Saved")
				}
			//loadLocalFareEntry()
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


function loadBankTransferForm()
{
	clearformMsgDL()
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	if(ledgerDt.length<=0)
	{
		//$('#formMsgDL').html("Please Enter Ledger Date")
		alert("Please Enter Ledger Date")
	return;
     }
	
	var bnkTransferTable="<div id=\"errorMsgBT\" style=\"color:red\"></div>" +
	"<div id=\"goodRespnsMsgBT\" style=\"color:green\"></div>" +
	"<style>"+
	"table thead tr th {font-size: 11px;}"+
	"table tfoot tr th {font-size: 11px;}"+
	"table tbody tr td { font-size: 12px;}"+
	"</style>"+
"<table id=\"bankTransferTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>#</th>\r\n" + 
"			<th>Bank Name</th>\r\n" + 
"			<th>Account Name</th>\r\n" + 
"			<th>Amount</th>\r\n" + 
"			<th>Reference(Comment)</th>\r\n" + 
"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"+
" <tr id='addrBnk0'>"+
"<td>1</td>"+
"<td><input type=\"text\" name='bankName0' id='bankName0' placeholder='Bank Name' class=\"form-control\" required onclick=\"clearErrorMsg()\" /></td>"+
"<td><input type=\"text\" name='accounHolder0' id='accounHolder0' placeholder='Account Holder' class=\"form-control\" required onclick=\"clearErrorMsg()\" /></td>"+
"<td><input type=\"text\" name='amount0' id='amount0' placeholder='Total Amount' class=\"form-control\" required onclick=\"clearErrorMsg()\" /></td>"+
"<td><input type=\"text\" name='referecne0' id='referecne0' placeholder='Comment' class=\"form-control\" /></td>"+
"</tr>"+
"<tr id=\"addrBnk1\"></tr>"+
"</tbody>"+
"</table>"+
"<a id=\"add_row_bnk_trsnfr\" class=\"btn btn-default pull-left\">Add Row</a>"+
"<a id='delete_row_bnk_trnsfr' class=\"btn btn-default pull-right\">Delete Row</a>"
 ;
	
$('#bankTransferModalContent').html(bnkTransferTable);

var i=1;   
$('#totalBnkTrnsfrEntry').val(i)
$("#add_row_bnk_trsnfr").click(function(){
	
 $('#addrBnk'+i).html("<td>"+ (i+1) +"</td>" +

 "<td><input type=\"text\" name='bankName"+i+"' id='bankName"+i+"' placeholder='Bank Name' class=\"form-control\" required onclick=\"clearErrorMsg()\" /></td>"+
 "<td><input type=\"text\" name='accounHolder"+i+"' id='accounHolder"+i+"' placeholder='Account Holder' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
 "<td><input type=\"text\" name='amount"+i+"' id='amount"+i+"' placeholder='Total Amount' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
 "<td><input type=\"text\" name='referecne"+i+"' id='referecne"+i+"' placeholder='Comment' class=\"form-control\" /></td>"
		);

$('#bankTransferTable1').append('<tr id="addrBnk'+(i+1)+'"></tr>');
 i++; 
 $('#totalBnkTrnsfrEntry').val(i)  
 //$('#bankTransferCollectionsModal').modal('show');
 
});
$("#delete_row_bnk_trnsfr").click(function(){
	 if(i>1){
	 $("#addrBnk"+(i-1)).html('');
	 i--;
	 }
});
}

function loadMoneyWithdrawlForm()
{
	clearformMsgDL()
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	if(ledgerDt.length<=0)
	{
		//$('#formMsgDL').html("Please Enter Ledger Date")
		alert ("Please Enter Ledger Date")
	return;
     }
	var moneyWithdrawlTable="<div id=\"errorMsgMW\" style=\"color:red\"></div>" +
	"<div id=\"goodRespnsMsgMW\" style=\"color:green\"></div>" +
	"<style>"+
	"table thead tr th {font-size: 11px;}"+
	"table tfoot tr th {font-size: 11px;}"+
	"table tbody tr td { font-size: 12px;}"+
	"</style>"+
"<table id=\"moneyWithdrawlTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>#</th>\r\n" + 
"			<th>Withdrawl By</th>\r\n" + 
"			<th>Amount</th>\r\n" + 
"			<th>Withdrawl Mode</th>\r\n" + 
"			<th>Payment By</th>\r\n" + 
"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"+
" <tr id='addrwithDrawl0'>"+
"<td>1</td>"+

"<td><input type=\"text\" name='withdrawlBy'  id='withdrawlBy0' placeholder='Withdrawl By' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"<td><input type=\"text\" name='totWithDrawlAmt' id='totWithDrawlAmt0' placeholder='Total Amount' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"<td><input type=\"text\" name='withdrawlMode' id='withdrawlMode0' placeholder='Mode(Cash/Online)' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"<td><input type=\"text\" name='paymentBy' id='paymentBy0' placeholder='Payment By' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"</tr>"+
"<tr id=\"addrwithDrawl1\"></tr>"+
"</tbody>"+
"</table>"+
"<a id=\"add_row_mony_withdrwl\" class=\"btn btn-default pull-left\">Add Row</a>"+
"<a id='delete_row_mony_withdrwl' class=\"btn btn-default pull-right\">Delete Row</a>"
 ;
	
$('#moneyWithdrawlModalContent').html(moneyWithdrawlTable);


var i=1;
$('#totalMnyWithdrwlEntry').val(i) 
$("#add_row_mony_withdrwl").click(function(){

 $('#addrwithDrawl'+i).html(
		 "<td>"+ (i+1) +"</td>" +
		 "<td><input type=\"text\" name='withdrawlBy'  id='withdrawlBy"+i+"' placeholder='Withdrawl By' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
		 "<td><input type=\"text\" name='totWithDrawlAmt' id='totWithDrawlAmt"+i+"' placeholder='Total Amount' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
		 "<td><input type=\"text\" name='withdrawlMode' id='withdrawlMode"+i+"' placeholder='Mode(Cash/Online)' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
		 "<td><input type=\"text\" name='paymentBy'  id='paymentBy"+i+"' placeholder='Payment By' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"
);

$('#moneyWithdrawlTable1').append('<tr id="addrwithDrawl'+(i+1)+'"></tr>');
 i++; 
 $('#totalMnyWithdrwlEntry').val(i) 
// $('#bankTransferCollectionsModal').modal('show');

});
$("#delete_row_mony_withdrwl").click(function(){
	 if(i>1){
	 $("#addrwithDrawl"+(i-1)).html('');
	 i--;
	 }
});
}

function loadOtherExpenseForm()
{
	clearformMsgDL()
	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	if(ledgerDt.length<=0)
	{
	alert ("Please Enter Ledger Date")
	return;
     }
	var otherExpenseTable="<div id=\"errorMsgOE\" style=\"color:red\"></div>" +
	"<div id=\"goodRespnsMsgOE\" style=\"color:green\"></div>" +
	"<style>"+
	"table thead tr th {font-size: 11px;}"+
	"table tfoot tr th {font-size: 11px;}"+
	"table tbody tr td { font-size: 12px;}"+
	"</style>"+
"<table id=\"otherExpenseTable1\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
"	<thead>\r\n" + 
"		<tr>\r\n" + 
"			<th>#</th>\r\n" + 
"			<th>Description</th>\r\n" + 
"			<th>Amount</th>\r\n" + 
"			<th>Expense By By</th>\r\n" + 
"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"+
" <tr id='addrExpense0'>"+
"<td>1</td>"+
"<td><input type=\"text\" name='expenseDesc0' id='expenseDesc0'  placeholder='Expense Description' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"<td><input type=\"text\" name='expenseAmt0' id='expenseAmt0' placeholder='Total Amount' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"<td><input type=\"text\" name='expenseBy0' id='expenseBy0' placeholder='Expense By' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
"</tr>"+
"<tr id=\"addrExpense1\"></tr>"+
"</tbody>"+
"</table>"+
"<a id=\"add_row_expense\" class=\"btn btn-default pull-left\">Add Row</a>"+
"<a id='delete_row_expense' class=\"btn btn-default pull-right\">Delete Row</a>"
 ;
	
$('#otherExpenseModalContent').html(otherExpenseTable);


var i=1;  
$('#totalOthrExpnsEntry').val(i)
$("#add_row_expense").click(function(){
	
 $('#addrExpense'+i).html(
		 "<td>"+ (i+1) +"</td>" +
		 "<td><input type=\"text\" name='expenseDesc' id='expenseDesc"+i+"'  placeholder='Expense Description' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
		 "<td><input type=\"text\" name='expenseAmt' id='expenseAmt"+i+"' placeholder='Total Amount' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"+
		 "<td><input type=\"text\" name='expenseBy' id='expenseBy"+i+"' placeholder='Expense By' class=\"form-control\" required onclick=\"clearErrorMsg()\"  /></td>"
          );

$('#otherExpenseTable1').append('<tr id="addrExpense'+(i+1)+'"></tr>');
 i++; 
 $('#totalOthrExpnsEntry').val(i) 
// $('#otherExpenseCollectionsModal').modal('show');
 
});
$("#delete_row_expense").click(function(){
	 if(i>1){
	 $("#addrExpense"+(i-1)).html('');
	 i--;
	 }
});
}






function saveBankTransfer()
{
	var bnkTrnsfrdata = {}
	var bankName=[]
	var acctHldr=[]
	var trnsfrAmt=[]
	var referecneDesc=[]
	var ledgerDt=[]
	
	var totEntry=$('#totalBnkTrnsfrEntry').val()

	for(i=0;i<totEntry;i++)
		{
		 bankName[i]=$('#bankName'+i).val()
		 acctHldr[i]=$('#accounHolder'+i).val()
		 trnsfrAmt[i]=$('#amount'+i).val()
		 referecneDesc[i]=$('#referecne'+i).val()	
		 ledgerDt[i]=jQuery('input[name="ledgerDt"]').val()
		}
	
	for(i=0;i<totEntry;i++)
	{

	     if(bankName[i].length<=0 || bankName[i]==""){
		 $("#errorMsgBT").html("Enter Bank Name!!")
	     return
	     }
		if(acctHldr[i].length<=0 || acctHldr[i]==""){
		 $("#errorMsgBT").html("Enter Account Holder Name !!")
		 return
		 }
		if(trnsfrAmt[i].length<=0|| trnsfrAmt[i]==""){
		 $("#errorMsgBT").html("Enter Transfer Amount !!")
		 return
		 }
		 
		    bnkTrnsfrdata["bankName"] =bankName
			bnkTrnsfrdata["acctHldr"] =acctHldr
			bnkTrnsfrdata["trnsfrAmt"] =trnsfrAmt
			bnkTrnsfrdata["referecneDesc"] =referecneDesc
			bnkTrnsfrdata["ledgerDt"] =ledgerDt
	}
	
	
	
	$.ajax({
		 type : "POST",
		 url : "/gaadibhada/dataentry/dailyledger/saveBankTrsnfrEntry",
		 contentType: "application/json",
		 dataType: "json",
		 data :JSON.stringify(bnkTrnsfrdata),		
		 success : function(data) {
           
			
			 if(data.msg=="success")	 
			 {
			 ledgerDt=jQuery('input[name="ledgerDt"]').val()
			 fetchBnkTransferTot(ledgerDt);
			 $("#bankTransferModal").modal('hide')
			 
			 }
		 else
			 $("#errorMsgDLdgr").html(v.msg) 
			
			 
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		$("#errorMsgDLdgr").html("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	$("#errorMsgDLdgr").html("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	$("#errorMsgDLdgr").html("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}

function saveTotalMoneyWithDraw()
{
	
	var moneyWithdrwldata = {}
	var withdrawlBy=[]
	var totWithDrawlAmt=[]
	var withdrawlMode=[]
	var paymentBy=[]
	var ledgerDt=[]
	
	var totEntry=$('#totalMnyWithdrwlEntry').val()

	for(i=0;i<totEntry;i++)
		{
		 withdrawlBy[i]=$('#withdrawlBy'+i).val()
		 totWithDrawlAmt[i]=$('#totWithDrawlAmt'+i).val()
		 withdrawlMode[i]=$('#withdrawlMode'+i).val()
		 paymentBy[i]=$('#paymentBy'+i).val()	
		 ledgerDt[i]=jQuery('input[name="ledgerDt"]').val()
		}
	
	for(i=0;i<totEntry;i++)
	{

	     if(withdrawlBy[i].length<=0 || withdrawlBy[i]==""){
		 $("#errorMsgMW").html("Enter Withdraw By !!")
	     return
	     }
		if(totWithDrawlAmt[i].length<=0 || totWithDrawlAmt[i]==""){
		 $("#errorMsgMW").html("Enter Amount !!")
		 return
		 }
		if(withdrawlMode[i].length<=0|| withdrawlMode[i]==""){
		 $("#errorMsgMW").html("Enter withdrawl Mode(CASH/Online) !!")
		 return
		 }
		if(paymentBy[i].length<=0|| paymentBy[i]==""){
		$("#errorMsgMW").html("Enter Payment By !!")
	    return
			 }
		 
		moneyWithdrwldata["withdrawlBy"] =withdrawlBy
		moneyWithdrwldata["totWithDrawlAmt"] =totWithDrawlAmt
		moneyWithdrwldata["withdrawlMode"] =withdrawlMode
		moneyWithdrwldata["paymentBy"] =paymentBy
		moneyWithdrwldata["ledgerDt"] =ledgerDt
	}
	
	$.ajax({
		 type : "POST",
		 url : "/gaadibhada/dataentry/dailyledger/saveMoneyWithdrawlEntry",
		 contentType: "application/json",
		 dataType: "json",
		 data :JSON.stringify(moneyWithdrwldata),		
		 success : function(data) {
           
			
			 if(data.msg=="success")	 
			 {
			 ledgerDt=jQuery('input[name="ledgerDt"]').val()
			 fetchMoneyWithDrawlTot(ledgerDt);
			 $("#moneyWithdrawlCollectionsModal").modal('hide')
			 }
		 else
			 $("#errorMsgMW").html(v.msg) 
			
			 
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		$("#errorMsgDLdgr").html("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	$("#errorMsgDLdgr").html("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	$("#errorMsgDLdgr").html("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}

function saveOtherExpenses()
{
	var otherExpnsdata = {}
	var expenseDesc=[]
	var expenseAmt=[]
	var expenseBy=[]
	var ledgerDt=[]

	var totEntry=$('#totalOthrExpnsEntry').val()
    
	for(i=0;i<totEntry;i++)
		{
		 expenseDesc[i]=$('#expenseDesc'+i).val()
		 expenseAmt[i]=$('#expenseAmt'+i).val()
		 expenseBy[i]=$('#expenseBy'+i).val()
		 ledgerDt[i]=jQuery('input[name="ledgerDt"]').val()
		}
	
	for(i=0;i<totEntry;i++)
	{

	     if(expenseDesc[i].length<=0 || expenseDesc[i]==""){
		 $("#errorMsgOE").html("Enter Expense Description !!")
	     return
	     }
		if(expenseAmt[i].length<=0 || expenseAmt[i]==""){
		 $("#errorMsgOE").html("Enter Expense Amount !!")
		 return
		 }
		if(expenseBy[i].length<=0|| expenseBy[i]==""){
		 $("#errorMsgOE").html("Enter Expense By !!")
		 return
		 }
		
		otherExpnsdata["expenseDesc"] =expenseDesc
		otherExpnsdata["expenseAmt"] =expenseAmt
		otherExpnsdata["expenseBy"] =expenseBy
		otherExpnsdata["ledgerDt"] =ledgerDt
	}
	
	$.ajax({
		 type : "POST",
		 url : "/gaadibhada/dataentry/dailyledger/saveOtherExpnsEntry",
		 contentType: "application/json",
		 dataType: "json",
		 data :JSON.stringify(otherExpnsdata),		
		 success : function(data) {
           
			
			 if(data.msg=="success")	 
			 {
			 ledgerDt=jQuery('input[name="ledgerDt"]').val()
			 fetchOtherExpenseTot(ledgerDt);
			 $("#otherExpenseCollectionsModal").modal('hide')
			 }
		 else
			 $("#errorMsgDLdgr").html(v.msg) 
			
			 
         },
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		$("#errorMsgDLdgr").html("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	
		        	$("#errorMsgDLdgr").html("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		        	$("#errorMsgDLdgr").html("Unknown Error !!")
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}

$(document).ready(function () {
	$('#thekedariAmt').on('click',function () {
	    alert($(this).attr('id') + ': ' + $(this).val());
	});
    $('#truckPymtTot').change(function () {
        alert($(this).attr('id') + ': ' + $(this).val());
    });
});

function getTotalBalForToday()
{

var openingBal= $('#openingBal').val()
var truckPymtTot= $('#truckPymtTot').val()
var bnkTransferTot= $('#bnkTransferTot').val()
var localFareTot= $('#localFareTot').val()
var moneyWithDrawlTot= $('#moneyWithDrawlTot').val()
var otherExpenseTot= $('#otherExpenseTot').val()
var thekedariAmt= $('#thekedariAmt').val()
if(Number.isNaN(parseFloat(thekedariAmt)))
	thekedariAmt=0
var todayCollAmt= $('#todayCollAmt').val()
//alert(openingBal+truckPymtTot+bnkTransferTot+localFareTot+moneyWithDrawlTot+otherExpenseTot+thekedariAmt+todayCollAmt)
 $('#todayBal').val(parseFloat(openingBal)-parseFloat(truckPymtTot)-parseFloat(bnkTransferTot)-parseFloat(localFareTot)-parseFloat(moneyWithDrawlTot)-parseFloat(otherExpenseTot)
		-parseFloat(thekedariAmt)+parseFloat(todayCollAmt))
}


function saveDailyLedger()
{

	var ledgerDt=jQuery('input[name="ledgerDt"]').val()
	if(ledgerDt.length<=0)
	{
	$("#todayCollectionsModal").modal('hide');
	//$('#formMsgDL').html("Please Enter Ledger Date")
	alert("Please Enter Ledger Date")
	return;
     }
	var openingBal= $('#openingBal').val()
	var truckPymtTot= $('#truckPymtTot').val()
	var bnkTransferTot= $('#bnkTransferTot').val()
	var localFareTot= $('#localFareTot').val()
	var moneyWithDrawlTot= $('#moneyWithDrawlTot').val()
	var otherExpenseTot= $('#otherExpenseTot').val()
	var thekedariAmt= $('#thekedariAmt').val()
	if(Number.isNaN(parseFloat(thekedariAmt)))
		thekedariAmt=0
	var todayCollAmt= $('#todayCollAmt').val()	
	var todayBal=$('#todayBal').val()	
	
	$.ajax({
		type : "POST",
		url : "/gaadibhada/dataentry/dailyledger/saveDailyLedger",
		data : {
			"ledgerDt" : ledgerDt,
			"truckPymtTot"  : truckPymtTot,
			"bnkTransferTot"   : bnkTransferTot,
			"localFareTot"    : localFareTot,
			"moneyWithDrawlTot"  : moneyWithDrawlTot,
			"otherExpenseTot"  : otherExpenseTot,
			"thekedariAmt"  : thekedariAmt,
			"todayCollAmt"  : todayCollAmt,
			"todayBal"     : todayBal
		},
		success : function(data) {
			if(data =="Success")
             alert("Data for daily ledger book is recorded")
             else
            	 $('#formMsgDL').html(data)
      
		}
});

}


