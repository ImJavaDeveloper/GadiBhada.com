/*//Calling Ajax to load the updated distribution page dynamically
$("#farecollectionTab").on("click", function() {

	waitingDialog.show()
	callAjaxForCollectionPage();
	waitingDialog.hide()
	//callAjaxForFareCalcHome();

	//$('#updateTable').DataTable();

});*/

function callAjaxForCollectionPage(ledgerDt) {
	
	waitingDialog.show();
	$.ajax({
		type : "GET",
		url : "/management/getcollections",
		data : {
			"ledgerDt" : ledgerDt
		},
		success : function(data) {
			$('#todayCollectionsModalContent').html(data);
			//loadjs("/js/dataTable.js");
			$('#collectionTable').DataTable();
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

function loadCollectionsModal(subLotId,ledgerDt) {
	$("#todayCollectionsModal").modal('hide');
	$.ajax({
		type : "GET",
		url : "/management/loadCollectionsModal",
		data : {
			"subLotId" : subLotId,
			"ledgerDt" : ledgerDt
		},
		success : function(data) {

			$('#collectionModelContent').html(data);

		},
		 error: function(XMLHttpRequest, textStatus, errorThrown)
	        {
		    	if (XMLHttpRequest.readyState == 4) {
		            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
		    		
		    		$('#collectionModelContent').html("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
		        }
		        else if (XMLHttpRequest.readyState == 0) {
		            // Network error (i.e. connection refused, access denied due to CORS, etc.)
		        	 $('#collectionModelContent').html("Error !! Server Is Down. Please contact server admin");
		        }
		        else {
		            // something weird is happening
		        }
	           
	          //some stuff on failure
	        }
		    
	});
}

function saveCollectionsData() {
	
	var subLotId = jQuery('input[name="subLotId"]').val()
	var fareIdC = jQuery('input[name="fareIdC"]').val()
	var totPayable = jQuery('input[name="totPayable"]').val()
	var totalPymt = jQuery('input[name="totalPymt"]').val()
	var totalDebit = jQuery('input[name="totalDebit"]').val()
    var paymentDate = jQuery('input[name="paymentDate"]').val()
    var ledgerDt = jQuery('input[name="ledgerDt"]').val()
    
    
	if (parseInt(totalPymt.length) <= 0) {
		$('#collectionModelmMessage').html("<strong style=\"color:red\">Failure!</strong> Enter Payment Amount :");
		return ;

	}
	if (paymentDate.length <= 0) {
		$('#collectionModelmMessage').html("<strong style=\"color:red\">Failure!</strong> Enter Payment Date :");
		return ;

	} 
	else {
		
         if(totalDebit.trim().length<=0)
        	 totalDebit=0;
         
         if(parseFloat(totPayable) <parseFloat(totalPymt))
        	 {
        	 $('#collectionModelmMessage').html("<strong style=\"color:red\">Failure!</strong> Total Payment Is Greater Than Actual Fare:");
        	return;
        	 }
        
         if(parseFloat(totPayable)<(parseFloat(totalPymt)+parseFloat(totalDebit)))
        	 {
        	 $('#collectionModelmMessage').html("<strong style=\"color:red\">Failure!</strong> Sum of total payment and debit is greater than actual fare:");
         	 return;
        	 }
         
		$.ajax({
			type : "GET",
			url : "/management/savecollection",
			data : {
				"subLotId":subLotId,
				"fareIdC":fareIdC,
				"totalPymt":totalPymt,
				"totalDebit":totalDebit,
				"paymentDate":paymentDate,
				"ledgerDt":ledgerDt
				
			},			
			success : function(data) {

				if (data === "success") {
					alert("Data is saved successfully");
					$("#collectionsModal").modal('hide');
					$("#todayCollectionsModal").modal('show');
					waitingDialog.show();
					callAjaxForCollectionPage(ledgerDt);
					waitingDialog.hide();
					//window.location.reload();
					//callAjaxForUpdateDistribution();
				} else
					$('#collectionModelmMessage').html("<strong style=\"color:red\">Failure!</strong> Record is not saved !! Error:"+data);
			},
			 error: function(XMLHttpRequest, textStatus, errorThrown)
		        {
			    	if (XMLHttpRequest.readyState == 4) {
			            // HTTP error (can be checked by XMLHttpRequest.status and XMLHttpRequest.statusText)
			    		$('#collectionModelmMessage').html("Error !! Server Is Not Responding Correctly-->\n"+XMLHttpRequest.responseText+"+ \nPlease contact server admin");
			        }
			        else if (XMLHttpRequest.readyState == 0) {
			            // Network error (i.e. connection refused, access denied due to CORS, etc.)
			        	
			        	$('#collectionModelmMessage').html("Error !! Server Is Down. Please contact server admin");
			        }
			        else {
			        	$('#collectionModelmMessage').html("Unknown Error !!")
			        }
		           
		          //some stuff on failure
		        }
			    
		});
	}
	//alert("Called save");	
}