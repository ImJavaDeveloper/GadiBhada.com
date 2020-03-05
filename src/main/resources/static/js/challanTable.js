$(document).ready(function(){
	
	
	
	var i=1;
     
     $("#add_row").click(function(){
    	 var j;
    	 var trader = "[[${traders}]]";
    	 
    	 // var a=[[${traders}]] "Test";
    	// alert(traderss[i].trader_name);
    	// alert(traderss[i].trader_id);
    	 var traderList="";
    	 var itemList="";
    	 var boxTypesList="";
    	 
    	 for(j=0;j<traders.length;j++){
    		 
    		 
    		 traderList=traderList+"<option value=\""+ traders[j].trader_id +"\">"+traders[j].trader_name+"("+traders[j].trader_mark+")</option>";
 			
       		}
          for(j=0;j<items.length;j++){
    		 
    		 
        	  itemList=itemList+"<option value=\""+ items[j].item_id +"\">"+items[j].item_name+"</option>";
 			
       		}
          for(j=0;j<boxTypes.length;j++){
     		 
     		 
        	  boxTypesList=boxTypesList+"<option value=\""+ boxTypes[j].box_id +"\">"+boxTypes[j].box_name+"-"+boxTypes[j].total_wt+"</option>";
 			
       		}
    	 //alert(list);
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
     $("#delete_row").click(function(){
    	 if(i>1){
		 $("#addr"+(i-1)).html('');
		 i--;
		 }
	 });

});