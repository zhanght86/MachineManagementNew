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
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript" src='/MachineManagement/js/checkinfob.js?randomId=<%=Math.random()%>'></script> 
 <link href="../bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>MachineInfoList</title>

</head>

<body>

<input id="checkinfob_machineid" value='<%=request.getParameter("machineid")%>' style="display:none;" />

<input id="checkinfobid" type="hidden"></input>

<input id="checkrecordbid" type="hidden"></input>

<input id="checkinfob_machineInfoID" type="hidden"></input>

<input id="checkrecordb_checkinfoid" type="hidden"></input>

<input id="checkrecordb_monthnumber" type="hidden"></input>

<% 
if( request.getParameter("ischeck").equals("1")){
%>
<div>
<div>
<a href="/MachineManagement/GetMachineInfoList.do?&userid=<%=request.getParameter("userid")%>" style="float:left;">主页 </a>
<p style="float:left;"> / </p>
<a href="#" style="float:left;">检查表 (B) </a>
</div>
<div style="clear:both;"></div>
<%}%>

<div class="panel panel-info">
  <div class="panel-heading"><h3><p style="display:inline-block;">中国科学院文献情报中心</p><p style="display:inline-block;" id="checkinfob_year_identifier"></p><p style="display:inline-block;">年服务器系统检查登记表(B)</p></h3></div>
  <div class="panel-body">
<table class="table-bordered"  style="table-layout:fixed;width:100%">
<tr>
<th >流水号</th>
<td colspan="2"><input class="form-control "aria-describedby="basic-addon1"id="checkinfobmain_FlowNumber" type="text" value="" ></input></td>
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
<td colspan="3"><input class="form-control "aria-describedby="basic-addon1"id="checkinfobmain_SerialNumber" type="text" value="" ></input></td>
<th>系统信息</th>
<td colspan="3" id="checkinfobmain_SystemInfo"></td>
<th>维护人员</th>
<td colspan="3"><input class="form-control "aria-describedby="basic-addon1"id="checkinfobmain_MantainceStaff" type="text" value="" ></input></td>
</tr>

<tr>
<th >备注</th>
<td colspan="11"><input class="form-control "aria-describedby="basic-addon1"id="checkinfobmain_Comments" type="text" value="" ></input></td>
</tr>


<tr>
<td colspan="12"><button class="btn btn-primary"  style="display:inline-block;float:right;" onclick="updateCheckInfoB();" ><span class="glyphicon glyphicon-floppy-save" aria-hidden="true" ></span> 保存检查表(B)</button></td>
</tr>
</table>

<table id="checkrecorda" class="table-bordered"style="table-layout:fixed;width:100%;">
<tr>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month1" onclick="loadCheckRecordbByMonth(1)">01月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month2" onclick="loadCheckRecordbByMonth(2)">02月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month3" onclick="loadCheckRecordbByMonth(3)">03月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month4" onclick="loadCheckRecordbByMonth(4)">04月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month5" onclick="loadCheckRecordbByMonth(5)">05月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month6" onclick="loadCheckRecordbByMonth(6)">06月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month7" onclick="loadCheckRecordbByMonth(7)">07月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month8" onclick="loadCheckRecordbByMonth(8)">08月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month9" onclick="loadCheckRecordbByMonth(9)">09月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month10" onclick="loadCheckRecordbByMonth(10)">10月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month11" onclick="loadCheckRecordbByMonth(11)">11月</button></th>
<th ><button class="form-control "aria-describedby="basic-addon1"id="checkinfob_month12" onclick="loadCheckRecordbByMonth(12)">12月</button></th>
</tr>

<tr>
<th colspan="3">网络备份（系统数据）</th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1"id="checkrecordb_networkBackup" type="text" /></td>
</tr>
<tr>
<th colspan="3">光盘备份（系统数据）</th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1"id="checkrecordb_harddriverBackup" type="text" /></td>
</tr>
<tr>
<th colspan="3">日志上载分析</th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1"id="checkrecordb_logUploadAnalysis" type="text" /></td>
</tr>
<tr>
<th colspan="3">防火墙检测</th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1" id="checkrecordb_firewallCheck" type="text" /></td>
</tr>
<tr>
<th colspan="3">本月流量</th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1"id="checkrecordb_monthlyFloatAmount" type="text" /></td>
</tr>
<tr>
<th colspan="3">停止服务情况</th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1"id="checkrecordb_serverStoppedInfo" type="text" /></td>
</tr>
<tr>
<th colspan="3">签字<img src="/MachineManagement/image/redstar.png" /></th>
<td colspan="9"><input class="form-control "aria-describedby="basic-addon1"id="checkrecordb_signature" type="text" /></td>
</tr>

<tr>
<td colspan="12" ><i style="float:left;align:bottom;margin: 12px auto 0px 1px;color:#6C6C6C;">(红色星号标记项请务必填写完整)</i><button class="btn btn-primary"  onclick="updateCheckRecordB()" style="float:right;" id="checkinfob_button_monthsave"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true" ></span> 保存本月检查记录</button></td>
</tr>
</table>
</div></div>
<script>
var checkinfob_machineid="<%=request.getParameter("machineid")%>";
var checkinfob_year;
var checkinfob_month;
var checkinfob_day;
var checkinfob_currentmonth;


initpage_checkinfob();
 
</script>
</body>
</html>