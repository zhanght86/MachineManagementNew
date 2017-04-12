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
 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript" src='/MachineManagement/js/checkinfoa.js?randomId=<%=Math.random()%>'></script> 
<title>操作类型</title>

</head>

<body >

<input id="checkinfoa_machineid" value='<%=request.getParameter("machineid")%>' style="display:none;" />

<input id="checkinfoaid" type="hidden"></input>

<input id="checkrecordaid" type="hidden"></input>

<input id="checkinfoa_machineInfoID" type="hidden"></input>

<input id="checkrecorda_checkinfoid" type="hidden"></input>

<input id="checkrecorda_monthnumber" type="hidden"></input>


<ol class="breadcrumb">
  <li><a href="/MachineManagement/GetMachineInfoList.do?&userid=<%=request.getParameter("userid")%>" >主页 </a></li>
  <li class="active" >操作类型管理</li>
</ol>
<div style="clear:both;"></div>


<div class="panel panel-info">
  <div class="panel-heading" style="height:40px;"><h4><p style="display:inline-block;">操作类型管理</div>
  <div class="panel-body">
  
<table class="table-bordered"  style="table-layout:fixed;width:100%">
<tr>
<th >流水号</th>
<td colspan="2" ><input class="form-control "aria-describedby="basic-addon1"  id="checkinfoamain_FlowNumber" type="text" value="" ></input></td>
<th>责任部门</th>
<td colspan="2" id="checkinfoamain_ResponsibilityDepartment"></td>
<th>机器位置</th>
<td colspan="2" id="checkinfoamain_MachineLocation"></td>
<th>IP地址</th>
<td colspan="2" id="checkinfoamain_IPAdd"></td>
</tr>



</table>
</div></div>

</body>



</html>