<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setBufferSize(Integer.MAX_VALUE-1) ;
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>新增设备信息</title>
<style>
.input1
{
 width:100px;
}
</style>

<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript"  src='/MachineManagement/js/WdatePicker.js'></script>
<script Language="JavaScript"  src='/MachineManagement/js/common.js'></script>
<script Language="JavaScript"  src='/MachineManagement/js/newmachine.js?randomId=<%=Math.random()%>'></script>

</head>
<body >
<input id="newmachine_responsibleuserid" value=">" style="display:none"></input>
<div>
<a href="/MachineManagement/GetMachineInfoList.do?&userid=<%=request.getParameter("userid")%>" style="float:left;">主页 </a>
<p style="float:left;"> / </p>
<a href="#" style="float:left;">机器登记 </a>
</div>
<div style="clear:both;"></div>

<input type="hidden" id="userid" value="<%=request.getParameter("userid")%>" />
<center  style="margin-top:30px;"">
<table style="width:460px;align:center;">
<tr>
<td>

<div class="input-group">
  <span class="input-group-addon " id="basic-addon1" style="width:100px;">资产名称</span>
  <input type="text" class="form-control " placeholder="资产名称 " aria-describedby="basic-addon1" id="input_machineinfo_propertyName"  style="width:350px;" />
</div>

<div class="input-group">
  <span class="input-group-addon " id="basic-addon1" style="width:100px;" >资产号  </span>
  <input type="text" class="form-control " placeholder="资产号" aria-describedby="basic-addon1" id="input_machineinfo_propertyNumber"  style="width:350px;" />
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1"  style="width:100px;">机器位置<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="机器位置" aria-describedby="basic-addon1" id="input_machineinfo_machineLocation" style="width:350px;"/>
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">机型</span>
  <input type="text" class="form-control" placeholder="机型" aria-describedby="basic-addon1" id="input_machineinfo_model" style="width:350px;" />
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">IP地址</span>
  <input type="text" class="form-control" placeholder="IP地址" aria-describedby="basic-addon1" id="input_machineinfo_ipAdd" style="width:350px;"/>
</div>
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">用途</span>
  <input type="text" class="form-control" placeholder="用途" aria-describedby="basic-addon1" id="input_machineinfo_machineUsage" style="width:350px;"/>
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">设备类型<img src="/MachineManagement/image/redstar.png" /></span>
  <select id="input_machineinfo_machineType" style="width:350px;height:25px;">
   <option value="">(请选择类型)</option>
   <option value="1">个人电脑</option>
   <option value="2">服务器</option>
   <option value="3">笔记本</option>
   <option value="4">软件</option>
   <option value="5">硬件</option>
   <option value="6">网络设备</option>
   <option value="7">家具</option>
   <option value="8">打印机</option>
  </select>
</div>
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;color:red;">选择责任人</span>
  <div id="responsible_list_div"  style="width:350px;"> </div>
  </div>
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">责任人<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="责任人" aria-describedby="basic-addon1"  id="input_machineinfo_responsible" style="width:350px;" readonly="readonly"/>
</div>
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">部门<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="部门" aria-describedby="basic-addon1"  id="input_machineinfo_department" style="width:350px;" readonly="readonly" />
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">邮箱<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="邮箱" aria-describedby="basic-addon1" id="input_machineinfo_responsibleEmail" style="width:350px;" readonly="readonly"/>
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">电话<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="电话" aria-describedby="basic-addon1" id="input_machineinfo_responsibleContactNumber" style="width:350px;" readonly="readonly"/>
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">购买人</span>
  <input type="text" class="form-control" placeholder="购买人" aria-describedby="basic-addon1" id="input_machineinfo_purchaser" style="width:350px;" />
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">购买方式</span>
  <input type="text" class="form-control" placeholder="购买方式" aria-describedby="basic-addon1" id="input_machineinfo_purchaseMethod" style="width:350px;" />
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">供应商</span>
  <input type="text" class="form-control" placeholder="供应商" aria-describedby="basic-addon1" id="input_machineinfo_supplier" style="width:350px;" />
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;padding:1px;">供应商联系人</span>
  <input type="text" class="form-control" placeholder="供应商联系人" aria-describedby="basic-addon1" id="input_machineinfo_supplierContact" style="width:350px;" />
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">供应商电话</span>
  <input type="text" class="form-control" placeholder="供应商电话" aria-describedby="basic-addon1" id="input_machineinfo_supplierContactNumber" style="width:350px;" />
</div>





<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">系统信息</span>
  <textarea type="text" class="form-control" placeholder="系统信息" aria-describedby="basic-addon1" id="input_machineinfo_systemInfo" style="width:350px;height:100px;" ></textarea>
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">归属课题</span>
  <textarea type="text" class="form-control" placeholder="归属课题" aria-describedby="basic-addon1" id="input_machineinfo_project" style="width:350px;height:100px;"></textarea>
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">购买时间</span>
  <input type="text" class="form-control " placeholder="购买时间" aria-describedby="basic-addon1" id="input_machineinfo_purchaseTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:350px;"/>
</div>
<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">价格</span>
  <input type="text" class="form-control" placeholder="价格" aria-describedby="basic-addon1"  id="input_machineinfo_price" style="width:350px;"/>
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">备注</span>
  <textarea type="text" class="form-control" placeholder="备注" aria-describedby="basic-addon1" id="input_machineinfo_comments" style="width:350px;height:100px;"></textarea>
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">入机房时间</span>
  <input type="text" class="form-control " placeholder="入机房时间" aria-describedby="basic-addon1" id="input_machineinfo_moveInTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:350px;"/>
</div>
<div class="input-group" style="float:right;margin-right:10px;margin-top:10px;">
<button class="btn btn-primary" type="button"  onclick="save()"><span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>登记</button>
    <div style="width:100%;height:50px;display:block"></div>
</div>
 
 </td>
 </tr>
 </table>
 
 </center>
 
</body>
<script>
loadUserList_newmachine("responsible_list_div");

// 对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) 
{ //author: meizz 
var o = { 
 "M+" : this.getMonth()+1,                 //月份 
 "d+" : this.getDate(),                    //日 
 "h+" : this.getHours(),                   //小时 
 "m+" : this.getMinutes(),                 //分 
 "s+" : this.getSeconds(),                 //秒 
 "q+" : Math.floor((this.getMonth()+3)/3), //季度 
 "S"  : this.getMilliseconds()             //毫秒 
}; 
if(/(y+)/.test(fmt)) 
 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
for(var k in o) 
 if(new RegExp("("+ k +")").test(fmt)) 
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
return fmt; 
}




 
</script>
</html>