<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link href="css/common.css" rel="stylesheet"  type="text/css" />-->
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<title>MachineInfoList</title>

</head>
<style>
.commonTable {
	border-collapse: collapse;
	border: none;
	width: 100%;
	height:100%;
	border: solid #000 1px;
}
.commonTable tr th
{
 border: solid #000 1px;
 text-align:center;
 align:middle;
 background: #E3EFFF; 
 font-weight:bold;
 white-space:nowrap;
 
}
.commonTable tr{
	border: solid #000 1px;
}
.commonTable tr td{
 border: solid #000 1px;
 text-align:center;
 background: #fff; 
 align:middle;
}

.tdselect
{
  background: #fff; 
}
</style>
<body onload="initpage();">

<input id="machineid" value='<%=request.getParameter("machineid")%>' style="display:none;" />

<input id="checkinfobid" type="hidden"></input>

<input id="checkrecordbid" type="hidden"></input>

<input id="checkinfob_machineInfoID" type="hidden"></input>

<input id="checkrecordb_checkinfoid" type="hidden"></input>

<input id="checkrecordb_monthnumber" type="hidden"></input>

<table class="commonTable"  style="table-layout:fixed;">
<tr><td colspan="12"><p style="display:inline-block;">国家科学图书馆<%=request.getParameter("year")%>年服务器系统检查登记表(B)</p></td><tr>
<tr>
<th >流水号</th>
<td colspan="2"><input id="checkinfobmain_FlowNumber" type="text" value="" style="width:140px;"></input></td>
<th >责任部门</th>
<td colspan="2" id="checkinfobmain_ResponsibilityDepartment"></td>
<th>机器位置</th>
<td colspan="2" id="checkinfobmain_MachineLocation"></td>
<th>IP地址</th>
<td colspan="2" id="checkinfobmain_IPAdd"></td>
</tr>


<tr>
<th style="with-space:nowrap;" >资产号</th>
<td colspan="3" id="checkinfobmain_PropertyNumber"></td>
<th>机器型号</th>
<td colspan="3" id="checkinfobmain_Model"></td>
<th>用途</th>
<td colspan="3" id="checkinfobmain_MachineUsage"></td>
</tr>

<tr>
<th style="with-space:nowrap;">序列号</th>
<td colspan="3"><input id="checkinfobmain_SerialNumber" type="text" value="" style="width:210px;"></input></td>
<th>系统信息</th>
<td colspan="3" id="checkinfobmain_SystemInfo"></td>
<th>维护人员</th>
<td colspan="3"><input id="checkinfobmain_MantainceStaff" type="text" value="" style="width:210px;"></input></td>
</tr>

<tr>
<th >备注</th>
<td colspan="11"><input id="checkinfobmain_Comments" type="text" value="" style="width:800px;"></input></td>
</tr>

</table>

<table id="checkrecorda" class="commonTable" style="table-layout:fixed;">
<tr><td colspan="12"><p>服务器检查登记(B) - 月度检查情况</p></td><tr>
<tr>
<th ><button id="month1" onclick="loadCheckRecordbByMonth(1)">01月</button></th>
<th ><button id="month2" onclick="loadCheckRecordbByMonth(2)">02月</button></th>
<th ><button id="month3" onclick="loadCheckRecordbByMonth(3)">03月</button></th>
<th ><button id="month4" onclick="loadCheckRecordbByMonth(4)">04月</button></th>
<th ><button id="month5" onclick="loadCheckRecordbByMonth(5)">05月</button></th>
<th ><button id="month6" onclick="loadCheckRecordbByMonth(6)">06月</button></th>
<th ><button id="month7" onclick="loadCheckRecordbByMonth(7)">07月</button></th>
<th ><button id="month8" onclick="loadCheckRecordbByMonth(8)">08月</button></th>
<th ><button id="month9" onclick="loadCheckRecordbByMonth(9)">09月</button></th>
<th ><button id="month10" onclick="loadCheckRecordbByMonth(10)">10月</button></th>
<th ><button id="month11" onclick="loadCheckRecordbByMonth(11)">11月</button></th>
<th ><button id="month12" onclick="loadCheckRecordbByMonth(12)">12月</button></th>
</tr>

<tr>
<th colspan="3">网络备份（系统数据）</th>
<td colspan="9"><input id="checkrecordb_networkBackup" type="text" style="width:655px;"/></td>
</tr>
<tr>
<th colspan="3">光盘备份（系统数据）</th>
<td colspan="9"><input id="checkrecordb_harddriverBackup" type="text" style="width:655px;"/></td>
</tr>
<tr>
<th colspan="3">日志上载分析</th>
<td colspan="9"><input id="checkrecordb_logUploadAnalysis" type="text" style="width:655px;"/></td>
</tr>
<tr>
<th colspan="3">防火墙检测</th>
<td colspan="9"><input id="checkrecordb_firewallCheck" type="text" style="width:655px;"/></td>
</tr>
<tr>
<th colspan="3">本月流量</th>
<td colspan="9"><input id="checkrecordb_monthlyFloatAmount" type="text" style="width:655px;"/></td>
</tr>
<tr>
<th colspan="3">停止服务情况</th>
<td colspan="9"><input id="checkrecordb_serverStoppedInfo" type="text" style="width:655px;"/></td>
</tr>
<tr>
<th colspan="3">签字</th>
<td colspan="9"><input id="checkrecordb_signature" type="text" style="width:655px;"/></td>
</tr>

</table>

<script>
var machineid=<%=request.getParameter("machineid")%>;
var year;
var month;
var day;
var currentmonth;

//页面载入，初始化函数
 function initpage()
 {
	var myDate = new Date();
	year=myDate.getFullYear();
	month=myDate.getMonth()+1;
	currentmonth=month;
	day=myDate.getDate();
	
	//标示出当月
	//document.getElementById("month"+month).innerHTML="*"+document.getElementById("month"+month).innerHTML+"*";
	
	//设置表头年份
	//document.getElementById("year_identifier").innerHTML=year;
	
	//载入检查表基本信息
    loadChenkInfoB(machineid);
	
    //载入当月的检查信息
	loadCheckRecordbByMonth(1);
 }
 
 //检查表A基本信息
function loadChenkInfoB(machineid)
{
    var url="/MachineManagement/checkinfobmain.do";
	var pars="&id="+machineid;
	var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	asynchronous:true,
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
}
 
 //检查表A月度检查
 function loadCheckRecordbByMonth(monthnumber)
 {
	 this.monthselect(monthnumber);
      this.month=monthnumber
	  var url="/MachineManagement/CheckRecordB.do";
	  var pars="&id="+machineid+"&year="+year+"&month="+month;
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
	           "&year="+year+
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
                  "&monthNumber="+month+
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
	 loadCheckRecordbByMonth(month);
 }
 
 function monthselect(monthnumber)
 {
	 //更改样式
	 for(var i=1;i<13;i++)
	{
		 document.getElementById("month"+i).style.color="black";
		 if(i==monthnumber)
		 {
			 document.getElementById("month"+i).style.color="red";
		 }
    }
	 //设置保存按钮是否可用
// 	if(currentmonth==monthnumber)
// 		{
// 		document.getElementById("button_monthsave").disabled=false;
// 		}
// 	else
// 		{
// 		document.getElementById("button_monthsave").disabled=true;
// 		}
 }
 
</script>
</body>
</html>