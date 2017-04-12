<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript"  src='/MachineManagement/js/common.js'></script>
<script Language="JavaScript" src="/MachineManagement/js/transferrecord.js"></script> 


<title>设备调转记录</title>
</head>
<body>
<input id="transferrecord_machineid" value='<%=request.getParameter("machineid")%>' type="hidden" />
<div class="panel panel-info">
  <div class="panel-heading"><h3>设备调转记录</h3></div>
  <div class="panel-body">
<table class="table" style="width:100%;display:block;">
<tr>
<div id="transferrecord_content_div" ></div>
</tr>
</table>


<table class="" style="width:100%;">
<tr style="display:none;"><td>设备编号</td><td><input class="input1" id="input_transferrecord_machineinfoid" type="text" value='<%=request.getParameter("machineid")%>' disabled="disabled"></input></td></tr>
<tr>
<td>
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:120px;color:red;text-align:right;">选择责任人</span>
  <div id="responsible_list_div_transferrecord"  style="width:200px;"> </div>
  </div>
  </td>
  </tr>
<tr>
<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:120px;text-align:right;">新所有者<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="新所有者" aria-describedby="basic-addon1" id="input_transferrecord_curowner" style="width:200px;"  readonly="readonly"/>
</div>
</td>
</tr>

<tr>

<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:120px;text-align:right;">新所有者邮箱<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="新所有者邮箱" aria-describedby="basic-addon1" id="input_transferrecord_curownerEmail" style="width:200px;"  readonly="readonly"/>
</div>
</td>
</tr>
<tr>
<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:120px;text-align:right;">部门<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="部门" aria-describedby="basic-addon1" id="input_transferrecord_department" style="width:200px;"  readonly="readonly"/>
</div>
</td>
</tr>
<tr>
<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:120px;text-align:right;">原因</span>
  <input type="text" class="form-control" placeholder="原因" aria-describedby="basic-addon1" id="input_transferrecord_reason" style="width:200px;"/>
</div>
</td>

</tr>
<tr><td ><div style="margin-top:5px;"><button class="btn btn-primary"  type="button" onclick="transferrecord_save()" value="" style="float:right;"><span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增</button></div></td></tr>
</table>
</div>
</div>
</body>

<script>
var transferrecord_machineid="<%=request.getParameter("machineid")%>";

initpage_transferrecord();


</script>
</html>