//below function required to load js functions after page is dynamically loaded


function callAjaxForUpdateDistribution()
{
	$.ajax({
	    type : "GET",
	    url : "/updateDistribution",
	    data : {
	    "id" : 1
	    },
	    success: function(data){
	    	$('#menu2').html(data);
	    	
	    	loadjs("/js/dataTable.js");
	    	$('#distributionTable').DataTable();
	    	
	    }
	});
}
//Calling Ajax to load the updated distribution page dynamically
$("#distributionTab").on("click", function() {
	
	callAjaxForUpdateDistribution();
	
	//$('#updateTable').DataTable();
	
});
 
 



function saveUpdateModal()
{

	var modalqtyAvl=jQuery('input[name="modalqtyAvl"]').val()
	var totQtyDistributed=jQuery('input[name="totQtyDistributed"]').val()
	var agentId=$( "#agentId" ).val()
	var agentDestination=$( "#agentDestination" ).val()
	var receivingDate=jQuery('input[name="receivingDate"]').val()
	
	//alert(documnet.getElementById("totQty"))
	//alert(d)
	
	//alert("Selected :"+agentId)
	
	if(parseInt(agentId.length)<=0){
		alert("Please Select Distribute To");
		return 10;
	    
	}
	if(totQtyDistributed.length<=0){
		alert("Please Enter Distribute Qty");
		return 10;
		
	}
	if(agentDestination.length<=0){
		alert("Please Select Destination");
		return 10;
	}
	if(receivingDate.length<=0){
		alert("Please Enter Recieving Date");
		return 10;
	}
	
	
	if(totQtyDistributed==0 || modalqtyAvl<totQtyDistributed){
		alert("You Entered More Than Available");
		return 10;
		}
	else{
		//alert("modalqtyAvl:"+modalqtyAvl+" totQtyDistributed:"+totQtyDistributed+" agentId:"+agentId.length+" agentDestination:"+agentDestination.length)
		
		userJson = $("form").serializeArray();
		$.ajax({
	         type: "GET",
	         url: "/saveUpdateModal",
	         data: userJson,
	         success: function (data) {
	        	
	            if(data ==="success"){
	        	 alert("Data is saved successfully");
	             $("#updateDistributionModal").modal('hide');
	             window.location.reload();
	             //callAjaxForUpdateDistribution();
	        	 }
	            else
	            	alert("Error !!")
	         }
	     });
	}
//alert("Called save");	
}

function loadUpdateModal(lotId){
		//alert("Coming from modal");
		//alert(lotId);
		$.ajax({
		    type : "GET",
		    url : "/loadUpdateModal",
		    data : {
		    "id" : lotId
		    },
		    success: function(data){
		    	//alert(data)
		    	//loadjs("/js/dataTable.js");
		    	$('#modalContent').html(data);
		    	
		    	//loadjs("/js/dataTable.js");
		    	//$('#updateTable').DataTable();
		    	
		    }
		});
		}  


