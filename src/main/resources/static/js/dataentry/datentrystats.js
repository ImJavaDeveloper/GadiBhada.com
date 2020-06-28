$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();   
});
//Calling Ajax to load the page dynamically
$("#dataEntryHome").on("click", function() {

	$('#home').load('/formcontent/stats/dataentrystats.html',function()
			{
	
		//callAjaxForFareBook();
			});

});