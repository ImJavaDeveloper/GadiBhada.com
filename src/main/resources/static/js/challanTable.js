$(document).ready(function(){
	
	var i=1;     
     $("#add_row").click(function(){
    	
    	 var traderList="";
    	 var itemList="";
    	 var boxTypesList="";
    	 
    	 
    	var call1= $.ajax({
				type : "GET",
				url : '/management/getAllTrader',
				success : function(data) {
					// alert(data);
					 result=jQuery.parseJSON(data);
					
					 $.each(result, function(k, v) {
						
					 traderList=traderList+"<option value=\""+ v.trader_id +"\">"+v.trader_mark+"("+v.trader_name+")</option>";
				
					 });
					 }
			});
    	
    	var call2= $.ajax({
				type : "GET",
				url : '/management/getAllItems',
				success : function(data) {
					// alert(data);
					 result=jQuery.parseJSON(data);
					
					 $.each(result, function(k, v) {
    		 
    		 
        	  itemList=itemList+"<option value=\""+ v.item_id +"\">"+v.item_name+"</option>";
 			
					 });
				 }
		});
    	 var call3=$.ajax({
				type : "GET",
				url : '/management/getAllBoxType',
				success : function(data) {
					// alert(data);
					 result=jQuery.parseJSON(data);
					
					 $.each(result, function(k, v) {
 		          	  boxTypesList=boxTypesList+"<option value=\""+ v.box_id +"\">"+v.box_name+"-"+v.total_wt+"</option>";
 			
					 });
				 }
		});
    	 //alert(list);
     // $(document).ajaxStop(function () {
    	 $.when(call1,call2,call3).done(function(o1, o2, o3) {
    	
      $('#addr'+i).html("<td>"+ (i+1) +"</td>" +
      		"<td><select class='form-control' id='traders' name='traderId' required><option value=''>Select Trader</option>"+
      		traderList
           +"</select> </td>" +
           "<td><select class='form-control' id='items' name='itemId' required ><option value=''>Select Item</option>"+
           itemList
           +"</select> </td>" +
           "<td><select class='form-control' id='boxTypes' name='boxId' required><option value=''>Select Type</option>"+
           boxTypesList
           +"</select> </td>" +
           
    		"<td><input  name='totalQty' type='text' placeholder='Total Quantity'  class='form-control input-md' required onclick='getTotalWt()'></td>"+
    		"<td><input  name='receiver' type='text' placeholder='Receiver'  class='form-control input-md' ></td>"
    		);

  $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
      i++; 
          
          });   
      
  });
     $("#delete_row").click(function(){
    	 if(i>1){
		 $("#addr"+(i-1)).html('');
		 i--;
		 }
	 });

});