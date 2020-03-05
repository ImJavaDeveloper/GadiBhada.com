//Calling Ajax to load the updated distribution page dynamically
$("#fareCalcTab").on("click", function() {
	
	callAjaxForFareCalcHome();
	
	//$('#updateTable').DataTable();
	
});

function loadjs(file) {
    var script = document.createElement("script");
    script.type = "application/javascript";
    script.src = file;
    document.body.appendChild(script);
}

function callAjaxForFareCalcHome()
{
	$.ajax({
	    type : "GET",
	    url : "/farecalchome",
	    data : {
	    "id" : 1
	    },
	    success: function(data){
	    	$('#menu3').html(data);
	    	
	    	loadjs("/js/dataTable.js");
	    	$('#fareCalcTable').DataTable();
	    	
	    }
	});
}

function changeTotalFare(subLotId)
{
	var farePerBox=$('#farePerBox'+subLotId).val();
	//console.log(Number.isInteger(farePerBox))
	var totQty=$('#fareCalcTotQty'+subLotId).text()
	
	$('#totFare'+subLotId).text(farePerBox*totQty)
	
}
function saveFare(subLotId)
{
var totFare=$('#totFare'+subLotId).text()


if(isNaN(totFare))
	{
	alert("Please Enter Correct Fare");
	return
	}
var answer=window.confirm("Do you want to save fare");
if(answer){
		saveFareForSubLotId(subLotId,totFare);
}

}

function saveFareForSubLotId(subLotId,totFare)
{
	var button=$('#button'+subLotId)

	$.ajax({
	    type : "GET",
	    url : "/savefareforsublotId",
	    data : {
	    "subLotId" : subLotId,
	    "totFare" : totFare
	    },
	    success: function(data){
	    	alert(data);
	    	$(button).prop("disabled", true);
	    	
	    }
	});
}
