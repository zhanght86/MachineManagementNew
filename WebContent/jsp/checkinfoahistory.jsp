<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link href="css/common.css" rel="stylesheet"  type="text/css" />-->
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<title>机器检查表（A）</title>

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

<input id="checkinfoaid" type="hidden"></input>

<input id="checkrecordaid" type="hidden"></input>

<input id="checkinfoa_machineInfoID" type="hidden"></input>

<input id="checkrecorda_checkinfoid" type="hidden"></input>

<input id="checkrecorda_monthnumber" type="hidden"></input>

<table class="commonTable"  style="table-layout:fixed;">
<tr><td colspan="12"><p style="display:inline-block;">国家科学图书馆<%=request.getParameter("year")%>年服务器系统检查登记表(A)</p></td><tr>
<tr>
<th >流水号</th>
<td colspan="2" ><input id="checkinfoamain_FlowNumber" type="text" value="" style="width:140px;"></input></td>
<th>责任部门</th>
<td colspan="2" id="checkinfoamain_ResponsibilityDepartment"></td>
<th>机器位置</th>
<td colspan="2" id="checkinfoamain_MachineLocation"></td>
<th>IP地址</th>
<td colspan="2" id="checkinfoamain_IPAdd"></td>
</tr>


<tr>
<th style="with-space:nowrap;" >资产号</th>
<td colspan="3" id="checkinfoamain_PropertyNumber"></td>
<th>机器型号</th>
<td colspan="3" id="checkinfoamain_Model"></td>
<th>用途</th>
<td colspan="3" id="checkinfoamain_MachineUsage"></td>
</tr>

<tr>
<th style="with-space:nowrap;">序列号</th>
<td colspan="3"><input id="checkinfoamain_SerialNumber" type="text" value="" style="width:210px;"></input></td>
<th>系统信息</th>
<td colspan="3" id="checkinfoamain_SystemInfo"></td>
<th>维护人员</th>
<td colspan="3" ><input id="checkinfoamain_MantainceStaff" type="text" value="" style="width:210px;"></input></td>
</tr>

<tr>
<th >备份内容</th>
<td colspan="3" ><input id="checkinfoamain_BackupContent" type="text" value="" style="width:210px;"></input></td>
<th >变更1</th>
<td colspan="3" ><input id="checkinfoamain_BackupContentChange1" type="text" value="" style="width:210px;"></input></td>
<th >变更2</th>
<td colspan="3" ><input id="checkinfoamain_BackupContentChange2" type="text" value="" style="width:210px;"></input></td>
</tr>

<tr>
<th >文件目录</th>
<td colspan="3" ><input id="checkinfoamain_FileDirectory" type="text" value="" style="width:210px;"></input></td>
<th >变更1</th>
<td colspan="3" ><input id="checkinfoamain_FileDirectoryChange1" type="text" value="" style="width:210px;"></input></td>
<th >变更2</th>
<td colspan="3" ><input id="checkinfoamain_FileDirectoryChange2" type="text" value="" style="width:210px;"></input></td>
</tr>

<tr>
<th >备份周期</th>
<td colspan="3" ><input id="checkinfoamain_BackupPeriod" type="text" value="" style="width:210px;"></td>
<th >变更1</th>
<td colspan="3" ><input id="checkinfoamain_BackupPeriodChange1" type="text" value="" style="width:210px;"></td>
<th >变更2</th>
<td colspan="3" ><input id="checkinfoamain_BackupPeriodChange2" type="text" value="" style="width:210px;"></td>
</tr>

</table>


<table id="checkrecorda" class="commonTable" style="table-layout:fixed;">
<tr><td colspan="12"><p>月度检查记录</p></td><tr>
<tr>
<th ><button id="month1" onclick="loadCheckRecordaByMonth(1)">01月</button></th>
<th ><button id="month2" onclick="loadCheckRecordaByMonth(2)">02月</button></th>
<th ><button id="month3" onclick="loadCheckRecordaByMonth(3)">03月</button></th>
<th ><button id="month4" onclick="loadCheckRecordaByMonth(4)">04月</button></th>
<th ><button id="month5" onclick="loadCheckRecordaByMonth(5)">05月</button></th>
<th ><button id="month6" onclick="loadCheckRecordaByMonth(6)">06月</button></th>
<th ><button id="month7" onclick="loadCheckRecordaByMonth(7)">07月</button></th>
<th ><button id="month8" onclick="loadCheckRecordaByMonth(8)">08月</button></th>
<th ><button id="month9" onclick="loadCheckRecordaByMonth(9)">09月</button></th>
<th ><button id="month10" onclick="loadCheckRecordaByMonth(10)">10月</button></th>
<th ><button id="month11" onclick="loadCheckRecordaByMonth(11)">11月</button></th>
<th ><button id="month12" onclick="loadCheckRecordaByMonth(12)">12月</button></th>
</tr>
<tr><td colspan="12" style="text-align:left;">补丁更新</td></tr>
<tr>
<th colspan="2">操作系统</th>
<td colspan="2" ><input id="checkrecorda_os" type="text" /></td>
<th colspan="2">应用程序</th>
<td colspan="2" ><input id="checkrecorda_application" type="checkbox" /></td>
<th colspan="2">数据库</th>
<td colspan="2" ><input id="checkrecorda_dataBaseCheck" type="checkbox" /></td>
</tr>

<tr><td colspan="12" style="text-align:left;">病毒查杀</td></tr>
<tr>
<th colspan="2">360安全卫士</th>
<td colspan="4" ><input id="checkrecorda_check360" type="checkbox" /></td>
<th colspan="2">杀毒软件</th>
<td colspan="4" ><input id="checkrecorda_antivirus" type="checkbox" /></td>
</tr>

<tr><td colspan="12" style="text-align:left;">用户账号</td></tr>
<tr>
<th colspan="2">正常使用账号</th>
<td colspan="4" ><input id="checkrecorda_accountNormal" type="text"></input></td>
<th colspan="2">非正常使用账号</th>
<td colspan="4" ><input id="checkrecorda_accountAbnormal" type="text"></input></td>
</tr>

<tr><td colspan="12" style="text-align:left;">日志情况</td></tr>
<tr>
<th colspan="2">事件日志</th>
<td colspan="2" ><input id="checkrecorda_eventLog" type="checkbox" /></td>
<th colspan="2">web日志</th>
<td colspan="2" ><input id="checkrecorda_webLog" type="checkbox" /></td>
<th colspan="2">数据库日志</th>
<td colspan="2" ><input id="checkrecorda_dataBaseLog" type="checkbox"/></td>
</tr>

<tr><td colspan="12" style="text-align:left;">磁盘情况</td></tr>
<tr>
<th colspan="2">硬盘空间情况</th>
<td colspan="10" ><input id="checkrecorda_hardDriverUsage" type="checkbox" /></td>
</tr>

<tr>
<th colspan="2">系统责任人签字</th>
<td colspan="4" ><input id="checkrecorda_osResponsibleSingnature" type="text"></input></td>
<th colspan="2">系统管理员签字</th>
<td colspan="4" ><input id="checkrecorda_osAdminsitratorSignature" type="text"></input></td>
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
    loadChenkInfoA(machineid);
    
	//载入当月的检查信息
	loadCheckRecordaByMonth(1);
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
	 
}
 
 //检查表A月度检查
 function loadCheckRecordaByMonth(monthnumber)
 {
	  this.monthselect(monthnumber)
      this.month=monthnumber
	  var url="/MachineManagement/CheckRecordA.do";
	  var pars="&id="+machineid+"&year="+year+"&month="+month;
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
	 document.getElementById("checkrecorda_os").value=objJson["os"];
	 document.getElementById("checkrecorda_monthnumber").value=objJson["monthNumber"];
	 
	 objJson["application"]=="1"?document.getElementById("checkrecorda_application").checked=true:document.getElementById("checkrecorda_application").checked=false;
	 objJson["dataBaseCheck"]=="1"?document.getElementById("checkrecorda_dataBaseCheck").checked=true:document.getElementById("checkrecorda_dataBaseCheck").checked=false;
	 objJson["check360"]=="1"?document.getElementById("checkrecorda_check360").checked=true:document.getElementById("checkrecorda_check360").checked=false;
	 objJson["antivirus"]=="1"?document.getElementById("checkrecorda_antivirus").checked=true:document.getElementById("checkrecorda_antivirus").checked=false;
	 
	 document.getElementById("checkrecorda_accountNormal").value=objJson["accountNormal"];
	 document.getElementById("checkrecorda_accountAbnormal").value=objJson["accountAbnormal"];
	 
	 objJson["eventLog"]=="1"?document.getElementById("checkrecorda_eventLog").checked=true:document.getElementById("checkrecorda_eventLog").checked=false;
	 objJson["webLog"]=="1"?document.getElementById("checkrecorda_webLog").checked=true:document.getElementById("checkrecorda_webLog").checked=false;
	 objJson["dataBaseLog"]=="1"?document.getElementById("checkrecorda_dataBaseLog").checked=true:document.getElementById("checkrecorda_webLog").checked=false;
	 objJson["hardDriverUsage"]=="1"?document.getElementById("checkrecorda_hardDriverUsage").checked=true:document.getElementById("checkrecorda_hardDriverUsage").checked=false;
	 
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
               "&year="+year+
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
                  "&monthNumber="+month+
                  "&application="+(document.getElementById("checkrecorda_application").checked==true?"1":"0")+
                  "&dataBaseCheck="+(document.getElementById("checkrecorda_dataBaseCheck").checked==true?"1":"0")+
                  "&check360="+(document.getElementById("checkrecorda_check360").checked==true?"1":"0")+
                  "&antivirus="+(document.getElementById("checkrecorda_antivirus").checked==true?"1":"0")+
                  "&accountNormal="+document.getElementById("checkrecorda_accountNormal").value+
                  "&accountAbnormal="+document.getElementById("checkrecorda_accountAbnormal").value+
                  "&eventLog="+(document.getElementById("checkrecorda_eventLog").checked==true?"1":"0")+
                  "&webLog="+(document.getElementById("checkrecorda_webLog").checked==true?"1":"0")+
                  "&dataBaseLog="+(document.getElementById("checkrecorda_dataBaseLog").checked==true?"1":"0")+
                  "&hardDriverUsage="+(document.getElementById("checkrecorda_hardDriverUsage").checked==true?"1":"0")+
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