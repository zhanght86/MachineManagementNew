/**
 * 
 */

function initpage_menu()
{
	loadFunctions();
}


function loadFunctions()
{
	var userid=document.getElementById("userid").value;
	var url="/MachineManagement/loadFunctions.do";
	var pars="&userid="+userid;
		 var ajax=new Ajax.Updater(
				    "functionlist_div" ,
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(){onComplete_loadFunctions()}
				    }
				  );
}

function onComplete_loadFunctions()
{
	document.getElementById("propertymanagement").click();
}