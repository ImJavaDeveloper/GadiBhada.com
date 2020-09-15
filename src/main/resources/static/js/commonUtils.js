//wait for loading the all JavaScript and then call the other function
$(window).on('load', function() {
   
    var activeTab = localStorage.getItem('activeTab');

  //alert(activeTab !== "#menu2")
  //Since menu2 is dynamic page it should be loaded runtime
  if(activeTab /*&& activeTab !== "#menu2"*/){
      $('#menuTab a[href="' + activeTab + '"]').tab('show');
     // alert(activeTab)
      if(activeTab=="#menu2")
    	  callAjaxForDistribution();
      if(activeTab=="#menu4")
    	  callAjaxForCollectionPage();
      if(activeTab=="#menu6")
    	  {
    	  loadPageTruckLedger();
    	  }
      if(activeTab=="#menu7")
    	  loadDailyLedger();
    	  
  }

});
//For redirecting same tab when page is refreshed  
$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
    localStorage.setItem('activeTab', $(e.target).attr('href'));
});


function loadjs(file) {
    var script = document.createElement("script");
    script.type = "application/javascript";
    script.src = file;
    document.body.appendChild(script);
}

var Source =
{
    buildSourceDropdown: function(result, dropdown, emptyMessage)
    {
        // Remove current options
        dropdown.html('');
        // Add the empty option with the empty message
        dropdown.append('<option value="">' + emptyMessage + '</option>');
        // Check result isnt empty
        if(result != '')
        {
            // Loop through each of the results and append the option to the dropdown
            $.each(result, function(k, v) {
                dropdown.append('<option value="' + v.sourceId + '">' + v.sourceName + '</option>');
            });
        }
    }
}

/// All Destination
var Dest =
	{
buildDestDropdown: function(result, dropdown, emptyMessage)
{
    // Remove current options
    dropdown.html('');
    // Add the empty option with the empty message
    dropdown.append('<option value="">' + emptyMessage + '</option>');
    // Check result isnt empty
    if(result != '')
    {
        // Loop through each of the results and append the option to the dropdown
        $.each(result, function(k, v) {
            dropdown.append('<option value="' + v.agentDestinationId + '">' + v.agentDestinationName + '</option>');
        });
    }
}
}
/// All Items
var Items =
{
buildItemsDropdown: function(result, dropdown, emptyMessage)
{
// Remove current options
dropdown.html('');
// Add the empty option with the empty message
dropdown.append('<option value="">' + emptyMessage + '</option>');
// Check result isnt empty
if(result != '')
{
    // Loop through each of the results and append the option to the dropdown
    $.each(result, function(k, v) {
        dropdown.append('<option value="' + v.item_id + '">' + v.item_name + '</option>');
    });
}
}
}

/// All Items
var BoxType =
{
buildBoxTypeDropdown: function(result, dropdown, emptyMessage)
{
// Remove current options
dropdown.html('');
// Add the empty option with the empty message
dropdown.append('<option value="">' + emptyMessage + '</option>');
// Check result isnt empty
if(result != '')
{
    // Loop through each of the results and append the option to the dropdown
    $.each(result, function(k, v) {
        dropdown.append('<option value="' + v.box_id + '">' + v.box_name +'-'+v.total_wt+'(Kg)'+ '</option>');
    });
}
}
}