/**
 * 
 */
$("#fareAndCollection").on("click", function() {
	// alert("Add Source Called")
	// callAjaxForFareCalcHome();
	$('#panelBodyFareAndColl').html("")
	// $('#updateTable').DataTable();
	// loadjs("/js/dataTable.js");
});
$("#FareAndColl_Areawise").on("click", function() {
	waitingDialog.show();
	$('#panelBodyFareAndColl').load('/formcontent/reports/FareAndColl_Areawise.html',function()
	{
		var jsonData;
		$.ajax({
			type : "POST",
			url : "/gadibhada/reports/fareAndcollReport",
			
			success : function(jsonData) {
           // alert(jsonData)
				var pivot = $("#areaWiseReport_FareAndColl").webdatarocks({
            toolbar: true,
            
            //report: "https://cdn.webdatarocks.com/reports/report.json"
           // report: "reports/CPUmatrix.csv"
            report: {
        		dataSource: {
        			//filename: "/reports/test.json"
        			data: jQuery.parseJSON(jsonData)
        		},
        		"slice": {
    		        "rows": [
    		            {
    		                "uniqueName": "AGENT_DESTINATION",
    		                
    		            },
    		            
    		            {
    		                "uniqueName": "AGENT_NAME",
    		               
    		            },
    		            
    		            {
    		                "uniqueName": "DELIVERY_DATE.Month",
    		               
    		            },
    		            {
    		                "uniqueName": "DELIVERY_DATE.Day",
    		               
    		            },
    		            {
    		                "uniqueName": "ITEM_CODE",
    		               
    		            }
    		        ],
    		        
    		        "columns": [
    		        	
    		            {
    		                "uniqueName": "Measures"
    		            }
    		        ],
    		        "measures": [
    		            {
    		                "uniqueName": "TOTAL_QTY",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_FARE",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_PAYMENT",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_DEBIT",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_BALANCE",
    		                "aggregation": "sum",
    		                "sort": "asc"
    		            }
    		        ],
    		        "options": {
    		            "grid": {
    		                "type": "classic"
    		            }
    		        },
		        "sorting": {
		            "column": {
		                "type": "desc",
		                "tuple": [],
		                "measure": "TOTAL_BALANCE"
		            }
		        }
    			}
        	}
			
           // data: "/reports/test.json"
		    });
	
				
	         }
			    
		});
		
		
	});
	
	waitingDialog.hide()

});

$("#FareAndColl_Partywise").on("click", function() {
	waitingDialog.show();
	$('#panelBodyFareAndColl').load('/formcontent/reports/FareAndColl_Partywise.html',function()
	{
		var jsonData;
		$.ajax({
			type : "POST",
			url : "/gadibhada/reports/fareAndcollReport",
			
			success : function(jsonData) {
           // alert(jsonData)
				var pivot = $("#partyWiseReport_FareAndColl").webdatarocks({
            toolbar: true,
            
            //report: "https://cdn.webdatarocks.com/reports/report.json"
           // report: "reports/CPUmatrix.csv"
            report: {
        		dataSource: {
        			//filename: "/reports/test.json"
        			data: jQuery.parseJSON(jsonData)
        		},
        		"slice": {
    		        "rows": [
    		        	
    		        	{
    		                "uniqueName": "AGENT_NAME",
    		               
    		            },
    		            {
    		                "uniqueName": "AGENT_DESTINATION",
    		                "sort": "asc"
    		            },
    		            {
    		                "uniqueName": "DELIVERY_DATE.Month",
    		               
    		            },
    		            {
    		                "uniqueName": "DELIVERY_DATE.Day",
    		               
    		            },
    		            {
    		                "uniqueName": "ITEM_CODE",
    		               
    		            }
    		        ],
    		        
    		        "columns": [
    		        	
    		            {
    		                "uniqueName": "Measures"
    		            }
    		        ],
    		        "measures": [
    		            {
    		                "uniqueName": "TOTAL_QTY",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_FARE",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_PAYMENT",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_DEBIT",
    		                "aggregation": "sum"
    		            },
    		            {
    		                "uniqueName": "TOTAL_BALANCE",
    		                "aggregation": "sum",
    		                "sort": "desc"
    		            }
    		        ],
    		        "options": {
    		            "grid": {
    		                "type": "classic"
    		            }
    		        },
    		        "sorting": {
    		            "column": {
    		                "type": "desc",
    		                "tuple": [],
    		                "measure": "TOTAL_BALANCE"
    		            }
    		        }
    			}
        	}
			
          
		    });
	
				
	         }
			    
		});
		
		
	});
	waitingDialog.hide()
});