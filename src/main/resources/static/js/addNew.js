//Calling Ajax to load the updated distribution page dynamically
$("#addNewTab").on("click", function() {
	// alert("Add Source Called")
	// callAjaxForFareCalcHome();
	$('#panelBody').html("")
	// $('#updateTable').DataTable();
	// loadjs("/js/dataTable.js");
});
$("#addSource").on("click", function() {
	$('#panelBody').load('/formcontent/addSource.html')

});

$("#addDest").on("click", function() {

	$('#panelBody').load('/formcontent/addDestination.html')

});

$("#addTrader").on("click", function() {

	$('#panelBody').load('/formcontent/addTrader.html')

});

$("#addAgent").on("click", function() {

	$('#panelBody').load('/formcontent/addAgent.html')

});

$("#addItem").on("click", function() {

	$('#panelBody').load('/formcontent/addItem.html')

});

$("#addBoxType").on("click", function() {

	$('#panelBody').load('/formcontent/addBoxType.html')

});

// Add Fare Tab, Populate the dropdown value
$("#addFare").on(
		"click",
		function() {

			$('#panelBody').load('/formcontent/addFare.html');
			
			$.ajax({
				type : "GET",
				url : '/management/getAllSource',
				success : function(data) {
					// alert(data);
					Source.buildSourceDropdown(jQuery.parseJSON(data),
							$('#allsource'), 'Select an option');
				}
			});
			// Calling Ajax to pop up destination
			$.ajax({
				type : "GET",
				url : '/management/getAllDestination',
				success : function(data) {
					// alert(data);
					Dest.buildDestDropdown(jQuery.parseJSON(data),
							$('#alldestination'), 'Select an option');
				}
			});

			// Calling Ajax to pop List
			$.ajax({
				type : "GET",
				url : '/management/getAllItems',
				success : function(data) {
					// alert(data);
					Items.buildItemsDropdown(jQuery.parseJSON(data),
							$('#allitem'), 'Select an option');
				}
			});

			// Calling Ajax to pop Box Type
			$.ajax({
				type : "GET",
				url : '/management/getAllBoxType',
				success : function(data) {
					// alert(data);
					BoxType.buildBoxTypeDropdown(jQuery.parseJSON(data),
							$('#boxType'), 'Select an option');
				}
			});

		});

function saveSource() {
	var sourceName = jQuery('input[name="sourceName"]').val()

	if (sourceName.trim().length == 0)
		alert("Please Enter Source Name");

	if (sourceName.trim().length != 0) {
		$.ajax({
			type : "GET",
			url : "/management/addnew/addsource",
			data : {
				"sourceName" : sourceName
			},
			success : function(data) {
				// alert(data)
				if (data === "Success !! Source Is Saved successfully") {
					$("input:text").val("");
					$('#addSourceMessage').html(
							"<div  class=\"alert alert-success\" <strong>"
									+ data + "!</strong> " + " </div>");
				} else {
					$('#addSourceMessage').html(
							"<strong>Failure!</strong> Operation failed !! Exception Occured :"
									+ data);
				}
			}
		});
	}

}

function saveDestination() {
	var destInd;
	var destName = jQuery('input[name="destName"]').val()
	if (destName.trim().length == 0)
		alert("Please Enter Destination")
	if (document.getElementById('agentDestRadio').checked) {
		destInd = "agentDest"
	}
	if (document.getElementById('truckDestRadio').checked) {
		destInd = "truckDest"
	}
	if (destName.trim().length != 0) {
		$.ajax({
			type : "GET",
			url : "/management/addnew/adddestination",
			data : {
				"destName" : destName,
				"destInd" : destInd,
			},
			success : function(data) {
				// alert(data)

				if (data === "Success !! Record Has Been Added Successfully") {
					$("input:text").val("");
					$('#addDestMessage').html(
							"<div  class=\"alert alert-success\" <strong>"
									+ data + "!</strong> " + " </div>");
				} else {
					$('#addDestMessage').html(
							"<strong>Failure!</strong> Operation failed !! Exception Occured :"
									+ data);
				}
			}
		});
	}
}

function saveTrader() {
	var traderName = jQuery('input[name="traderName"]').val()
	var tradeMark = jQuery('input[name="tradeMark"]').val()
	var traderAdd = jQuery('input[name="traderAdd"]').val()
	var traderMob = jQuery('input[name="traderMob"]').val()

	if (traderName.trim().length == 0) {
		$("#traderModal").modal('show');
		$('#tradermodelcontent').html("  Error!! Enter Trader Name ");
		return;
	}

	if (tradeMark.trim().length == 0) {
		$("#traderModal").modal('show');
		$('#tradermodelcontent').html("  Error!! Enter Trade Mark ");
		return;
	}

	if (traderMob.trim().length == 0) {
		$("#traderModal").modal('show');
		$('#tradermodelcontent').html("  Error!! Enter Trader Mobile ");
		return;
	}
	$.ajax({
		type : "GET",
		url : "/management/addnew/savetrader",
		data : {
			"traderName" : traderName,
			"tradeMark" : tradeMark,
			"traderAdd" : traderAdd,
			"traderMob" : traderMob,

		},
		success : function(data) {
			// alert(data)
			if (data === "Success !! Record Has Been Added Successfully") {
				$("input:text").val("");
				$("#traderModal").modal('show');
				$('#tradermodelcontent').html(
						"<div  class=\"alert alert-success\" <strong>" + data
								+ "!</strong> " + " </div>");
			} else {
				$("#traderModal").modal('show');
				$('#tradermodelcontent').html(
						"<strong>Failure!</strong> Operation failed !! Exception Occured :"
								+ data);
			}
		}
	});
}

function saveAgent() {
	var agentName = jQuery('input[name="agentName"]').val()
	var agentMark = jQuery('input[name="agentMark"]').val()
	var agentAdd = jQuery('input[name="agentAdd"]').val()
	var agentMob = jQuery('input[name="agentMob"]').val()

	if (agentName.trim().length == 0) {
		$("#agentModal").modal('show');
		$('#agentmodelcontent').html("  Error!! Enter Agent Name ");
		return;
	}

	if (agentMark.trim().length == 0) {
		$("#agentModal").modal('show');
		$('#agentmodelcontent').html("  Error!! Enter Agent Mark ");
		return;
	}
	if (agentAdd.trim().length == 0) {
		$("#agentModal").modal('show');
		$('#agentmodelcontent').html("  Error!! Enter Agent Address ");
		return;
	}
	if (agentMob.trim().length == 0) {
		$("#agentModal").modal('show');
		$('#agentmodelcontent').html("  Error!! Enter Agent Mobile ");
		return;
	}

	$.ajax({
		type : "GET",
		url : "/management/addnew/saveagent",
		data : {
			"agentName" : agentName,
			"agentMark" : agentMark,
			"agentAdd" : agentAdd,
			"agentMob" : agentMob,

		},
		success : function(data) {
			// alert(data)
			if (data === "Success !! Record Has Been Added Successfully") {
				$("input:text").val("");
				$("#agentModal").modal('show');
				$('#agentmodelcontent').html(
						"<div  class=\"alert alert-success\" <strong>" + data
								+ "!</strong> " + " </div>");
			} else {
				$("#agentModal").modal('show');
				$('#agentmodelcontent').html(
						"<strong>Failure!</strong> Operation failed !! Exception Occured :"
								+ data);
			}
		}
	});

}
function saveItem() {
	var itemName = jQuery('input[name="itemName"]').val()

	if (itemName.trim().length == 0) {
		$("#itemModal").modal('show');
		$('#itemmodelcontent').html("  Error!! Enter Item Name ");
		return;
	}

	if (itemName.trim().length != 0) {
		$.ajax({
			type : "GET",
			url : "/management/addnew/saveitem",
			data : {
				"itemName" : itemName
			},
			success : function(data) {
				// alert(data)
				if (data === "Success !! Record Is Saved successfully") {
					$("input:text").val("");
					$("#itemModal").modal('show');
					$('#itemmodelcontent').html(
							"<div  class=\"alert alert-success\" <strong>"
									+ data + "!</strong> " + " </div>");
				} else {
					$("#itemModal").modal('show');
					$('#itemmodelcontent').html(
							"<strong>Failure!</strong> Operation failed !! Exception Occured :"
									+ data);
				}
			}
		});
	}

}

function saveBox() {

	var boxName = jQuery('input[name="boxName"]').val()
	if (boxName.trim().length == 0) {
		$("#boxTypeModal").modal('show');
		$("#boxTypemodelcontent").html("  Error!! Enter Box Type ");
		return;
	}
	var totalWt = jQuery('input[name="totalWt"]').val()
	if (totalWt.trim().length == 0) {

		$("#boxTypeModal").modal('show');
		$("#boxTypemodelcontent").html("  Error!! Enter Box Wt ");
		return;
	}

	$.ajax({
		type : "GET",
		url : "/management/addnew/savebox",
		data : {
			"boxName" : boxName,
			"totalWt" : totalWt
		},
		success : function(data) {
			// alert(data)
			if (data === "Success !! Record Is Saved successfully") {
				$("input:text").val("");
				$("#boxTypeModal").modal('show');
				$('#boxTypemodelcontent').html(
						"<div  class=\"alert alert-success\" <strong>" + data
								+ "!</strong> " + " </div>");
			} else {
				$("#boxTypeModal").modal('show');
				$('#boxTypemodelcontent').html(
						"<strong>Failure!</strong> Operation failed !! Exception Occured :"
								+ data);
			}
		}
	});

}

function saveFareRule() {

	var source = $("#allsource").val()
	if (source.trim().length == 0) {
		$("#fareModal").modal('show');
		$("#faremodelcontent").html("  Error!! Select Source ");
		return;
	}
	var destination = $("#alldestination").val()
	if (destination.trim().length == 0) {
		$("#fareModal").modal('show');
		$("#faremodelcontent").html("  Error!! Select Destination ");
		return;
	}
	var item = $("#allitem").val()
	if (item.trim().length == 0) {
		$("#fareModal").modal('show');
		$("#faremodelcontent").html("  Error!! Select Item ");
		return;
	}
	var boxType = $("#boxType").val()
	if (boxType.trim().length == 0) {
		$("#fareModal").modal('show');
		$("#faremodelcontent").html("  Error!! Select Box Type ");
		return;
	}
	var fare = jQuery('input[name="fare"]').val()
	if (fare.trim().length == 0) {
		$("#fareModal").modal('show');
		$("#faremodelcontent").html("  Error!! Select Fare ");
		return;
	}
	$.ajax({
		type : "GET",
		url : "/management/addnew/saveaddfare",
		data : {
			"source" : source,
			"destination" : destination,
			"item" : item,
			"boxType" : boxType,
			"fare" : fare
		},
		success : function(data) {
			// alert(data)
			if (data === "Success !! Record Is Saved successfully") {
				$("input:text").val("");
				$("#fareModal").modal('show');
				$('#faremodelcontent').html(
						"<div  class=\"alert alert-success\" <strong>" + data
								+ "!</strong> " + " </div>");
			} else {
				$("#fareModal").modal('show');
				$('#faremodelcontent').html(
						"<strong>Failure!</strong> Operation failed !! Exception Occured :"
								+ data);
			}
		}
	});

}