/**
 * 
 */

//页面载入，初始化函数
 function initpage_checkinfoa()
 {
	var myDate = new Date();
	checkinfoa_year=myDate.getFullYear();
	checkinfoa_month=myDate.getMonth()+1;
	checkinfoa_currentmonth=checkinfoa_month;
	checkinfoa_day=myDate.getDate();
	
	//标示出当月
	document.getElementById("checkinfoa_month"+checkinfoa_month).innerHTML="*"+document.getElementById("checkinfoa_month"+checkinfoa_month).innerHTML+"*";
	
	
	//设置表头年份
	document.getElementById("checkinfoa_year_identifier").innerHTML=checkinfoa_year;
	
	//载入检查表基本信息
    loadChenkInfoA(checkinfoa_machineid);
    

 }
 
 //检查表A基本信息
function loadChenkInfoA(machineid)
{
    var url="/MachineManagement/checkinfoamain.do";
	var pars="&id="+machineid;
	var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	asynchronous:true,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_loadChenkInfoA(request)}
			    }
			  );
}

function onComplete_loadChenkInfoA(request)
{
	 //alert(request.responseText);
	 var objJson = request.responseText.evalJSON();
	 
	 document.getElementById("checkinfoaid").value=objJson["id"];
	 document.getElementById("checkinfoamain_FlowNumber").value=objJson["flowNumber"];
	 document.getElementById("checkinfoamain_ResponsibilityDepartment").innerHTML=objJson["responsibilityDepartment"];
	 document.getElementById("checkinfoamain_MachineLocation").innerHTML=objJson["machineLocation"];
	 document.getElementById("checkinfoamain_IPAdd").innerHTML=objJson["ipAdd"];
	 document.getElementById("checkinfoamain_PropertyNumber").innerHTML=objJson["propertyNumber"];
	 document.getElementById("checkinfoamain_Model").innerHTML=objJson["model"];
	 document.getElementById("checkinfoamain_MachineUsage").innerHTML=objJson["machineUsage"];
	 document.getElementById("checkinfoamain_SerialNumber").value=objJson["serialNumber"];
	 document.getElementById("checkinfoamain_SystemInfo").innerHTML=objJson["systemInfo"];
	 document.getElementById("checkinfoamain_MantainceStaff").value=objJson["mantainceStaff"];
	 document.getElementById("checkinfoamain_BackupContent").value=objJson["backupContent"];
	 document.getElementById("checkinfoamain_BackupContentChange1").value=objJson["backupContentChange1"];
	 document.getElementById("checkinfoamain_BackupContentChange2").value=objJson["backupContentChange2"];
	 document.getElementById("checkinfoamain_FileDirectory").value=objJson["fileDirectory"];
	 document.getElementById("checkinfoamain_FileDirectoryChange1").value=objJson["fileDirectoryChange1"];
	 document.getElementById("checkinfoamain_FileDirectoryChange2").value=objJson["fileDirectoryChange2"];
	 document.getElementById("checkinfoamain_BackupPeriod").value=objJson["backupPeriod"];
	 document.getElementById("checkinfoamain_BackupPeriodChange1").value=objJson["backupPeriodChange1"];
	 document.getElementById("checkinfoamain_BackupPeriodChange2").value=objJson["backupPeriodChange2"];
	 document.getElementById("checkinfoa_machineInfoID").value=objJson["machineInfoID"];
	 
	 
		//载入当月的检查信息
		loadCheckRecordaByMonth(checkinfoa_month);
}
 
 //检查表A月度检查
 function loadCheckRecordaByMonth(monthnumber)
 {
	  this.checkinfoa_monthselect(monthnumber)
      this.checkinfoa_month=monthnumber
	  var url="/MachineManagement/CheckRecordA.do";
	  var pars="&id="+checkinfoa_machineid+"&year="+checkinfoa_year+"&month="+checkinfoa_month;
	  var ajax=new Ajax.Request(
	   			    url, 
	   			    {
	   			    	method: "POST",
	   			    	parameters:pars,
	   			    	asynchronous:true,
	   			    	encoding:'UTF-8',
	   			        onComplete: function(request){onComplete_loadCheckRecordaByMonth(request)}
	   			    }
	   			  );
 }
 
 function onComplete_loadCheckRecordaByMonth(request)
 {
	 
	 var objJson = request.responseText.evalJSON();
	 
	 
	 document.getElementById("checkrecordaid").value=objJson["id"];
	 document.getElementById("checkrecorda_checkinfoid").value=objJson["checkInfoID"];
	 document.getElementById("checkrecorda_monthnumber").value=objJson["monthNumber"];
	 
	 
	 document.getElementById("checkrecorda_os").value=objJson["os"];
	 objJson["application"]=="1"?document.getElementById("checkrecorda_application").checked=true:document.getElementById("checkrecorda_application").checked=false;
	 objJson["patch"]=="1"?document.getElementById("checkrecorda_patch").checked=true:document.getElementById("checkrecorda_patch").checked=false;
	 objJson["dataBaseCheck"]=="1"?document.getElementById("checkrecorda_dataBaseCheck").checked=true:document.getElementById("checkrecorda_dataBaseCheck").checked=false;
	 objJson["check360"]=="1"?document.getElementById("checkrecorda_check360").checked=true:document.getElementById("checkrecorda_check360").checked=false;
	 objJson["antivirus"]=="1"?document.getElementById("checkrecorda_antivirus").checked=true:document.getElementById("checkrecorda_antivirus").checked=false;
	 
	 document.getElementById("checkrecorda_accountNormal").value=objJson["accountNormal"];
//	 document.getElementById("checkrecorda_accountAbnormal").value=objJson["accountAbnormal"];
	 document.getElementById("checkrecorda_passwordStrength").value=objJson["passwordStrength"];
	 objJson["eventLog"]=="1"?document.getElementById("checkrecorda_eventLog").checked=true:document.getElementById("checkrecorda_eventLog").checked=false;
	 objJson["webLog"]=="1"?document.getElementById("checkrecorda_webLog").checked=true:document.getElementById("checkrecorda_webLog").checked=false;
	 objJson["dataBaseLog"]=="1"?document.getElementById("checkrecorda_dataBaseLog").checked=true:document.getElementById("checkrecorda_dataBaseLog").checked=false;
	 
	 document.getElementById("checkrecorda_hardDriverUsage").value=objJson["hardDriverUsage"];
	 document.getElementById("checkrecorda_pageException").value=objJson["pageException"];
	 document.getElementById("checkrecorda_reactionaryException").value=objJson["reactionaryException"];
	 document.getElementById("checkrecorda_patchScanExist").value=objJson["patchScanExist"];
	 document.getElementById("checkrecorda_patchScanHandle").value=objJson["patchScanHandle"];
	 document.getElementById("checkrecorda_patchScanOperator").value=objJson["patchScanOperator"];
	 document.getElementById("checkrecorda_service").value=objJson["service"];
	 document.getElementById("checkrecorda_osResponsibleSingnature").value=objJson["osResponsibleSingnature"];
	 document.getElementById("checkrecorda_osAdminsitratorSignature").value=objJson["osAdminsitratorSignature"];

 }


 function updateCheckInfoA()
 {

	 var url="/MachineManagement/updatecheckinfoamain.do"
	 var pars= "&id="+document.getElementById("checkinfoaid").value+
	           "&flowNumber="+document.getElementById("checkinfoamain_FlowNumber").value+
	           "&responsibilityDepartment="+document.getElementById("checkinfoamain_ResponsibilityDepartment").innerHTML+
	           "&machineLocation="+document.getElementById("checkinfoamain_MachineLocation").innerHTML+
	           "&ipAdd="+document.getElementById("checkinfoamain_IPAdd").innerHTML+
	           "&propertyNumber="+document.getElementById("checkinfoamain_PropertyNumber").innerHTML+
	           "&model="+document.getElementById("checkinfoamain_Model").innerHTML+
	           "&machineUsage="+document.getElementById("checkinfoamain_MachineUsage").innerHTML+
	           "&serialNumber="+ document.getElementById("checkinfoamain_SerialNumber").value+
	           "&systemInfo="+document.getElementById("checkinfoamain_SystemInfo").innerHTML+
	           "&mantainceStaff="+document.getElementById("checkinfoamain_MantainceStaff").value+
	           "&backupContent="+document.getElementById("checkinfoamain_BackupContent").value+
	           "&backupContentChange1="+document.getElementById("checkinfoamain_BackupContentChange1").value+
	           "&backupContentChange2="+document.getElementById("checkinfoamain_BackupContentChange2").value+
	           "&fileDirectory="+document.getElementById("checkinfoamain_FileDirectory").value+
	           "&fileDirectoryChange1="+document.getElementById("checkinfoamain_FileDirectoryChange1").value+
	           "&fileDirectoryChange2="+document.getElementById("checkinfoamain_FileDirectoryChange2").value+
               "&backupPeriod="+document.getElementById("checkinfoamain_BackupPeriod").value+
               "&backupPeriodChange1="+document.getElementById("checkinfoamain_BackupPeriodChange1").value+
               "&backupPeriodChange2="+document.getElementById("checkinfoamain_BackupPeriodChange2").value+
               "&year="+checkinfoa_year+
               "&machineInfoID="+document.getElementById("checkinfoa_machineInfoID").value;
	 
	 var ajax=new Ajax.Request(
	   			    url, 
	   			    {
	   			    	method: "POST",
	   			    	parameters:pars,
	   			    	asynchronous:true,
	   			    	encoding:'UTF-8',
	   			        onComplete: function(request){onComplete_updateCheckInfoA(request)}
	   			    });
 }
 
 function onComplete_updateCheckInfoA(request)
 {
	 alert(request.responseText);
	 loadChenkInfoA(machineid)
 }
 

 function updateCheckRecordA()
 {
	     var url="/MachineManagement/updatecheckrecorda.do";
		 var pars="&id="+ document.getElementById("checkrecordaid").value+
		          "&checkInfoID="+document.getElementById("checkrecorda_checkinfoid").value+
                  "&os="+document.getElementById("checkrecorda_os").value+
                  "&patch="+(document.getElementById("checkrecorda_patch").checked==true?"1":"0")+
                  "&monthNumber="+checkinfoa_month+
                  "&application="+(document.getElementById("checkrecorda_application").checked==true?"1":"0")+
                  "&dataBaseCheck="+(document.getElementById("checkrecorda_dataBaseCheck").checked==true?"1":"0")+
                  "&check360="+(document.getElementById("checkrecorda_check360").checked==true?"1":"0")+
                  "&antivirus="+(document.getElementById("checkrecorda_antivirus").checked==true?"1":"0")+
                  "&passwordStrength="+document.getElementById("checkrecorda_passwordStrength").value+
                  "&pageException="+document.getElementById("checkrecorda_pageException").value+
                  "&reactionaryException="+document.getElementById("checkrecorda_reactionaryException").value+
                  "&patchScanExist="+document.getElementById("checkrecorda_patchScanExist").value+
                  "&patchScanHandle="+document.getElementById("checkrecorda_patchScanHandle").value+
                  "&patchScanOperator="+document.getElementById("checkrecorda_patchScanOperator").value+
                  "&service="+document.getElementById("checkrecorda_service").value+
                  "&accountNormal="+document.getElementById("checkrecorda_accountNormal").value+
//                  "&accountAbnormal="+document.getElementById("checkrecorda_accountAbnormal").value+
                  "&accountAbnormal="+
                  "&eventLog="+(document.getElementById("checkrecorda_eventLog").checked==true?"1":"0")+
                  "&webLog="+(document.getElementById("checkrecorda_webLog").checked==true?"1":"0")+
                  "&dataBaseLog="+(document.getElementById("checkrecorda_dataBaseLog").checked==true?"1":"0")+
                  "&hardDriverUsage="+encodeURIComponent(document.getElementById("checkrecorda_hardDriverUsage").value)+
                  "&osResponsibleSingnature="+document.getElementById("checkrecorda_osResponsibleSingnature").value+
                  "&osAdminsitratorSignature="+document.getElementById("checkrecorda_osAdminsitratorSignature").value;
                  
		 var ajax=new Ajax.Request(
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(request){onComplete_updateCheckRecordA(request)}
				    }
				  );
 }
 
 function onComplete_updateCheckRecordA(request)
 {
	 alert(request.responseText);
 }
 
 function checkinfoa_monthselect(monthnumber)
 {
	 //更改样式
	 for(var i=1;i<13;i++)
	{
		 document.getElementById("checkinfoa_month"+i).style.color="black";
		 if(i==monthnumber)
		 {
			 document.getElementById("checkinfoa_month"+i).style.color="red";
		 }
		 
		 if(i>checkinfoa_currentmonth)
		 {
			 document.getElementById("checkinfoa_month"+i).style.color="";
			 document.getElementById("checkinfoa_month"+i).disabled=true;
	     }
    }
	 //设置保存按钮是否可用
	if(checkinfoa_currentmonth==monthnumber)
		{
		document.getElementById("checkinfoa_button_monthsave").disabled=false;
		}
	else
		{
		document.getElementById("checkinfoa_button_monthsave").disabled=true;
		}
 }