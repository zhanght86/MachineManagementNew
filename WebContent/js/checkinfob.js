/**
 * 
 */


//页面载入，初始化函数
 function initpage_checkinfob()
 {
	var myDate = new Date();
	checkinfob_year=myDate.getFullYear();
	checkinfob_month=myDate.getMonth()+1;
	checkinfob_currentmonth=checkinfob_month;
	checkinfob_day=myDate.getDate();
	
	//标示出当月
	document.getElementById("checkinfob_month"+checkinfob_month).innerHTML="*"+document.getElementById("checkinfob_month"+checkinfob_month).innerHTML+"*";
	
	//设置表头年份
	document.getElementById("checkinfob_year_identifier").innerHTML=checkinfob_year;
	
	//载入检查表基本信息
    loadChenkInfoB(checkinfob_machineid);
	

 }
 
 //检查表B基本信息
function loadChenkInfoB(machineid)
{
    var url="/MachineManagement/checkinfobmain.do";
	var pars="&id="+machineid;
	var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_loadChenkInfoB(request)}
			    }
			  );
}

function onComplete_loadChenkInfoB(request)
{
	 //alert(request.responseText);
	 var objJson = request.responseText.evalJSON();
	
	 document.getElementById("checkinfobid").value=objJson["id"];
	 document.getElementById("checkinfobmain_FlowNumber").value=objJson["flowNumber"];
	 document.getElementById("checkinfobmain_PropertyNumber").innerHTML=objJson["propertyNumber"];
	 document.getElementById("checkinfobmain_SerialNumber").value=objJson["serialNumber"];
	 document.getElementById("checkinfobmain_ResponsibilityDepartment").innerHTML=objJson["responsibilityDepartment"];
	 document.getElementById("checkinfobmain_MachineLocation").innerHTML=objJson["machineLocation"];
	 document.getElementById("checkinfobmain_Model").innerHTML=objJson["model"];
	 document.getElementById("checkinfobmain_SystemInfo").innerHTML=objJson["systemInfo"];
	 document.getElementById("checkinfobmain_IPAdd").innerHTML=objJson["ipAdd"];
	 document.getElementById("checkinfobmain_MachineUsage").innerHTML=objJson["machineUsage"];
	 document.getElementById("checkinfobmain_MantainceStaff").value=objJson["mantainceStaff"];
	 document.getElementById("checkinfobmain_Comments").value=objJson["comments"];
	 document.getElementById("checkinfob_machineInfoID").value=objJson["machineInfoID"];
	 
	 
	    //载入当月的检查信息
		loadCheckRecordbByMonth(checkinfob_month);
}
 
 //检查表A月度检查
 function loadCheckRecordbByMonth(monthnumber)
 {
	 this.checkinfob_monthselect(monthnumber);
      this.checkinfob_month=monthnumber
	  var url="/MachineManagement/CheckRecordB.do";
	  var pars="&id="+checkinfob_machineid+"&year="+checkinfob_year+"&month="+checkinfob_month;
	  var ajax=new Ajax.Request(
	   			    url, 
	   			    {
	   			    	method: "POST",
	   			    	parameters:pars,
	   			    	asynchronous:true,
	   			    	encoding:'UTF-8',
	   			        onComplete: function(request){onComplete_loadCheckRecordbByMonth(request)}
	   			    }
	   			  );
 }
 
 function onComplete_loadCheckRecordbByMonth(request)
 {

	 var objJson = request.responseText.evalJSON();
	 
	 
	 document.getElementById("checkrecordbid").value=objJson["id"];
	 document.getElementById("checkrecordb_checkinfoid").value=objJson["checkInfoID"];
	 document.getElementById("checkrecordb_monthnumber").value=objJson["monthNumber"];
	 
	 document.getElementById("checkrecordb_networkBackup").value=objJson["networkBackup"];
	 document.getElementById("checkrecordb_harddriverBackup").value=objJson["harddriverBackup"];
	 document.getElementById("checkrecordb_logUploadAnalysis").value=objJson["logUploadAnalysis"];
	 document.getElementById("checkrecordb_firewallCheck").value=objJson["firewallCheck"];
	 document.getElementById("checkrecordb_monthlyFloatAmount").value=objJson["monthlyFloatAmount"];
	 document.getElementById("checkrecordb_serverStoppedInfo").value=objJson["serverStoppedInfo"];
	 document.getElementById("checkrecordb_signature").value=objJson["signature"];
	 


 }


 function updateCheckInfoB()
 {

	 var url="/MachineManagement/updatecheckinfobmain.do"
	 var pars= "&id="+document.getElementById("checkinfobid").value+
	           "&flowNumber="+document.getElementById("checkinfobmain_FlowNumber").value+
	           "&responsibilityDepartment="+document.getElementById("checkinfobmain_ResponsibilityDepartment").innerHTML+
	           "&machineLocation="+document.getElementById("checkinfobmain_MachineLocation").innerHTML+
	           "&ipAdd="+document.getElementById("checkinfobmain_IPAdd").innerHTML+
	           "&propertyNumber="+document.getElementById("checkinfobmain_PropertyNumber").innerHTML+
	           "&model="+document.getElementById("checkinfobmain_Model").innerHTML+
	           "&machineUsage="+document.getElementById("checkinfobmain_MachineUsage").innerHTML+
	           "&serialNumber="+ document.getElementById("checkinfobmain_SerialNumber").value+
	           "&systemInfo="+document.getElementById("checkinfobmain_SystemInfo").innerHTML+
	           "&mantainceStaff="+document.getElementById("checkinfobmain_MantainceStaff").value+
	           "&year="+checkinfob_year+
	           "&comments="+document.getElementById("checkinfobmain_Comments").value+
	           "&machineInfoID="+document.getElementById("checkinfob_machineInfoID").value;
	 
	 var ajax=new Ajax.Request(
	   			    url, 
	   			    {
	   			    	method: "POST",
	   			    	parameters:pars,
	   			    	asynchronous:true,
	   			    	encoding:'UTF-8',
	   			        onComplete: function(request){onComplete_updateCheckInfoB(request)}
	   			    });
 }
 
 function onComplete_updateCheckInfoB(request)
 {
	 alert(request.responseText);
	 loadChenkInfoB(machineid)
 }
 

 function updateCheckRecordB()
 {
	     var url="/MachineManagement/updatecheckrecordb.do";
		 var pars="&id="+ document.getElementById("checkrecordbid").value+
		          "&checkInfoID="+document.getElementById("checkrecordb_checkinfoid").value+
                  "&networkBackup="+document.getElementById("checkrecordb_networkBackup").value+
                  "&monthNumber="+checkinfob_month+
                  "&harddriverBackup="+document.getElementById("checkrecordb_harddriverBackup").value+
                  "&logUploadAnalysis="+document.getElementById("checkrecordb_logUploadAnalysis").value+
                  "&firewallCheck="+document.getElementById("checkrecordb_firewallCheck").value+
                  "&monthlyFloatAmount="+document.getElementById("checkrecordb_monthlyFloatAmount").value+
                  "&serverStoppedInfo="+document.getElementById("checkrecordb_serverStoppedInfo").value+
                  "&signature="+document.getElementById("checkrecordb_signature").value;
                  
		 var ajax=new Ajax.Request(
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(request){onComplete_updateCheckRecordB(request)}
				    }
				  );
 }
 
 function onComplete_updateCheckRecordB(request)
 {
	 alert(request.responseText);
	 loadCheckRecordbByMonth(checkinfob_month);
 }
 
 function checkinfob_monthselect(monthnumber)
 {
	 //更改样式
	 for(var i=1;i<13;i++)
	{
		 document.getElementById("checkinfob_month"+i).style.color="black";
		 if(i==monthnumber)
		 {
			 document.getElementById("checkinfob_month"+i).style.color="red";
		 }
		 if(i>checkinfob_currentmonth)
		 {
			 document.getElementById("checkinfob_month"+i).style.color="";
			 document.getElementById("checkinfob_month"+i).disabled=true;
	     }
    }
	 //设置保存按钮是否可用
	if(checkinfob_currentmonth==monthnumber)
		{
		document.getElementById("checkinfob_button_monthsave").disabled=false;
		}
	else
		{
		document.getElementById("checkinfob_button_monthsave").disabled=true;
		}
 }