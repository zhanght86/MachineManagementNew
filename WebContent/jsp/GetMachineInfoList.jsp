<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%
response.setBufferSize(Integer.MAX_VALUE-1) ;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />

<input type="hidden" id="userid" value="<%=request.getParameter("userid")%>" />
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript" src='/MachineManagement/js/GetMachineInfoList.js?randomId=<%=Math.random()%>'></script> 
<title>MachineInfoList</title>
<style>
.plaina
{
	color:#000000;
	text-decoration:none;
}
</style>
</head>
<body style="height:100%;">


<input type="hidden" id="searchcondition" value="" />
<input type="hidden" id="orderstring" value="" />
<input type="hidden" id="orderbuttonstyle" value="" />


<table class="table" style="margin-top:10px;">
<thead>
<tr>
<td style="width:15%;">

</td>
<td>



<div class="col-lg-6" style="width:500px;">
    <div class="input-group">
      <input type="text"  id="machinesearch" class="form-control" placeholder="多个关键字请用空格隔开"  style="height:34px;" onkeydown="FSubmit(event.keyCode||event.which);" />
      <span class="input-group-btn">
        <button class="btn btn-default" id='dosearch' type="button" onclick="reloadMachineInfoList(null,null);">搜索</button>
      </span>
    </div>
</div>


</td>
<td>
<button class="btn btn-default" onclick="newmachine();" style="float:right;margin-right:10px;"><span class="glyphicon glyphicon-plus" ></span>登记资产</button>
</td>
</tr>
<tr style="clear: both;">
  <td colspan="3" style="text-align:left;">
      
	  <div id="condition_div" >
	 	 <em> 条件：</em>
		<input type="checkbox" id="checkby_propertynumber_checkbox"  onclick="reloadMachineInfoList(null,null);" > 资产号</input>
		<input type="checkbox" id="checkby_machineLocation_checkbox"  onclick="reloadMachineInfoList(null,null);"> 机器位置</input>
		<input type="checkbox" id="checkby_ipaddr_checkbox"  onclick="reloadMachineInfoList(null,null);"> IP</input>
		<input type="checkbox" id="checkby_machineUsage_checkbox"  onclick="reloadMachineInfoList(null,null);"> 用途</input>
		<input type="checkbox" id="checkby_department_checkbox" onclick="reloadMachineInfoList(null,null);" >部门</input>
		<input type="checkbox" id="checkby_responsible_checkbox"  onclick="reloadMachineInfoList(null,null);"> 责任人</input>
		<div class="btn-group btn-group-xs" role="group" style="float:right;margin-right:10px;">
		<button onclick="exportWordMachineInfo1(null,null);"  class="btn btn-default"><span class="glyphicon glyphicon-download-alt" aria-hidden="true" ></span> 输出当前数据</button>
		</div>
	</div>
  </td>
</tr>
<tr style="clear: both;">
  <td colspan="3" style="text-align:left;">
	  <div id="machinetype_div" >
	    <em> 类型：</em>
		<input type="checkbox" id="checkby_machineType_checkbox_1"  onclick="reloadMachineInfoList(null,null);"> 电脑</input>
		<input type="checkbox" id="checkby_machineType_checkbox_2"  onclick="reloadMachineInfoList(null,null);"> 服务器</input>
		<input type="checkbox" id="checkby_machineType_checkbox_3"  onclick="reloadMachineInfoList(null,null);"> 笔记本</input>
		<input type="checkbox" id="checkby_machineType_checkbox_4"  onclick="reloadMachineInfoList(null,null);"> 软件</input>
		<input type="checkbox" id="checkby_machineType_checkbox_5"  onclick="reloadMachineInfoList(null,null);"> 硬件</input>
		<input type="checkbox" id="checkby_machineType_checkbox_6"  onclick="reloadMachineInfoList(null,null);"> 网络设备</input>
		<input type="checkbox" id="checkby_machineType_checkbox_7"  onclick="reloadMachineInfoList(null,null);"> 家具</input>
		<input type="checkbox" id="checkby_machineType_checkbox_8"  onclick="reloadMachineInfoList(null,null);"> 打印机</input>
		</div>
  </td>
</tr>
</thead>
</table>

<div id="machineinfolist_div" style="margin-top:10px;" >
</div>

</body>
<script>
init();


</script>
</html>