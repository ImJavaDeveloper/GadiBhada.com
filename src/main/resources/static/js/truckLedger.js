$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();   
});
//Calling Ajax to load the page dynamically
$("#truckLedgerTab").on("click", function() {


	loadPageTruckLedger()
	

});
function activateTruckLedger(activeTab)
{
	//alert(activeTab)
	$("#updateDistributionModal").modal('hide');
	$("#distributionModalPage").modal('hide');
	
	$('#menuTab a[href="' + activeTab + '"]').tab('show');	
	 loadPageTruckLedger()
}



function loadPageTruckLedger(){
	$('#menu6').load('/formcontent/truckLedger.html',function()
			{
	
		fetchTruckLedger();
			});
}
function reloadPageTruckLedger(){

	loadPageTruckLedger();
	 
}
function fetchTruckLedger() {
	
	var tableBody="";
	var subLotIdArray=[];
	waitingDialog.show();
	$.ajax({
		type : "POST",
		url : "/gadibhada/dataentry/truckLedger",
		
		success : function(data) {
		
			var i=0;
			var result=jQuery.parseJSON(data);
			
			$.each( result,function(k,v){
  
				tableBody=tableBody+"<tr>"+		
				"<td><a href=\"#\">"+v.truckNo+"</a></td>"+
				"<td><a href=\"#\">"+v.stratDt+"</a></td>"+
				"<td><a href=\"#\">"+v.FromToWhere+"</a></td>"+
				"<td><a href=\"#\">"+v.endDt+"</a></td>"+
				"<td><a href=\"#\">"+v.advFare+"</a></td>"+
				"<td><a href=\"#\">"+v.prizeFare+"</a></td>"+
				"<td><a href=\"#\">"+v.totFare+"</a></td>"+
				"<td><a href=\"#\">"+v.totPymt+"</a></td>"+
				"<td><a href=\"#\">"+v.totBal+"</a></td>"+
				"<td><button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#truckLedgerModal\"" +
				" onclick=\"updateTruckLedger('"+v.truckNo+"','"+v.stratDt+"','"+v.FromToWhere+"','"+v.endDt+"','"+v.advFare+"','"+v.prizeFare+"','"+v.totFare+"')\">Update</button></td>"+
				
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

function updateTruckLedger(truckNo,stratDt,FromToWhere,endDt,advFare,prizeFare,totFare)
{
	
	if(endDt=="")
		{
		endDt="<input type=\"date\" name=\"truckEndDtLedger\" class=\"form-control\" required oninput=\"clearErrorMsg()\" required pattern=\"\d{2}-\d{2}-\d{4}\" >"
		//endDt="<date-input date=\"{{date}}\" timezone=\"[[timezone]]\"></date-input>"
		}
	else{
		endDt = endDt.split("/").reverse().join("-");
		endDt="<input type=\"date\" name=\"truckEndDtLedger\" class=\"form-control\" required oninput=\"clearErrorMsg()\" value=\""+endDt+"\"  >"
			
	}
	
		prizeFare="<input type=\"number\" name=\"truckPrizeAmt\" class=\"form-control\" placeholder=\"Prize Fare\" oninput=\"clearErrorMsg()\" value=\""+prizeFare+"\">"
        totFare="<input type=\"text\" name=\"truckTotFare\" class=\"form-control\" required placeholder=\"Truck Total Fare\"  oninput=\"clearErrorMsg()\" value=\""+totFare+"\">"

	var table="<div id=\"errorMsgTLdgr\" style=\"color:red\"></div>" +
	"<div id=\"goodRespnsMsg\" style=\"color:green\"></div>" +
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
"			<th>Advance Fare</th>\r\n" + 

"		</tr>\r\n" + 
"	</thead>\r\n" + 
"	<tbody>"+
    "<tr>" + 
    "			<td id=\"truckNoLedger\">"+truckNo+"</td>\r\n" + 
    "			<td id=\"truckStDtLedger\">"+stratDt+"</td>\r\n" + 
    "			<td>"+FromToWhere+"</td>\r\n"+ 
    "			<td>"+advFare+"</td>\r\n" + 

 "	</tr>" +  
"   </tbody>"+
"   </table>"+
 "<table id=\"truckLedgerTable2\" class=\"table table-striped table-bordered\" style=\"width: 100%;background-color: #E2E2E2\">\r\n" + 
 "	<thead>\r\n" + 
 "		<tr>\r\n" + 
 "			<th>Arrival Date(mm/dd/yyyy)</th>\r\n" + 
 "			<th>Prize Fare</th>\r\n" + 
 "			<th>Total Fare</th>\r\n" + 
 "		</tr>\r\n" + 
 "	</thead>\r\n" + 
 "	<tbody>"+
     "<tr>" + 
     "			<td id=\"truckEndDtLbl\">"+endDt+"</td>\r\n" + 
     "			<td id=\"truckPrizeFareLbl\">"+prizeFare+"</td>\r\n" + 
     "			<td id=\"truckTotFareLbl\">"+totFare+"</td>\r\n" + 
  "	</tr>" +  
 "   </tbody>"
 "   </table>"
		
	$('#truckLedgerModalContent').html(table);
	
}

function clearErrorMsg()
{
	$("#errorMsgTLdgr").html(" ");
}
function saveTruckLedgerData()
{
var truckNoLedger=$("#truckNoLedger").text();
var truckStDtLedger=$("#truckStDtLedger").text();
var truckEndDtLedger=jQuery('input[name="truckEndDtLedger"]').val()
var truckPrizeAmt=jQuery('input[name="truckPrizeAmt"]').val()
var truckTotFare=jQuery('input[name="truckTotFare"]').val()

var truckStDtFormatted = truckStDtLedger.split("/").reverse().join("-");

var truckEndDtLedgerLbl =$('#truckEndDtLbl').text()
var truckPrizeFareLbl =$('#truckPrizeFareLbl').text()
var truckTotFareLbl =$('#truckTotFareLbl').text()

if(typeof truckEndDtLedger === "undefined"){
	truckEndDtLedger=truckEndDtLedgerLbl	
	truckEndDtLedger = truckEndDtLedger.split("/").reverse().join("-");
}

if(typeof truckPrizeAmt === "undefined"){
		truckPrizeAmt=truckPrizeFareLbl
}

if(typeof truckTotFare === "undefined"){
	truckTotFare=truckTotFareLbl
}

if(truckNoLedger.length<=0){
	$("#errorMsgTLdgr").html("Truck No Can't Be Empty !!")
    return
}
if(truckStDtLedger.length<=0){
	$("#errorMsgTLdgr").html("Truck Start Date Can't Be Empty!!")
    return
}
if(truckEndDtLedger.length<=0){
	$("#errorMsgTLdgr").html("Please Enter Arrival Date !!")
    return
}
if(truckPrizeAmt.length<=0){
	truckPrizeAmt=0
	
}
if(truckTotFare.length<=0){
	$("#errorMsgTLdgr").html("Please Enter Total Fare !!")
	return
}

$.ajax({
	type : "POST",
	url : "/gadibhada/dataentry/updateTruckLedger",
	data : {
		"truckNoLedger":truckNoLedger	,
		"truckStDtLedger":truckStDtFormatted	,
		"truckEndDtLedger":truckEndDtLedger	,
		"truckPrizeAmt":truckPrizeAmt	,
		"truckTotFare":truckTotFare
	},			
	success : function(data) {
       
       $('#goodRespnsMsg').html(data)
		
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
