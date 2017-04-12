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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
 <script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
 <script Language="JavaScript" src="/MachineManagement/js/user.js"></script> 

<title></title>
</head>
<body>

<div class="panel panel-info">
  <div class="panel-heading"><h2>系统用户</h2></div>
  <div class="panel-body">

<div id="usercontent_div"></div>

</div>

<table class="" style="width:100%;">
<tr>
<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;'">用户姓名<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="用户姓名" aria-describedby="basic-addon1" id="input_users_name" style="width:200px;"/>
</div>
</td>
</tr>


<tr>
<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;'">登录名<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="登录名" aria-describedby="basic-addon1" id="input_users_username" style="width:200px;"/>
</div>
</td>
</tr>

<tr>

<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;">密码<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="密码" aria-describedby="basic-addon1" id="input_users_password" style="width:200px;"/>
</div>
</td>
</tr>

<tr>
<td>
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;">邮箱<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="邮箱" aria-describedby="basic-addon1" id="input_user_email" style="width:200px;" ></input>
</div>
</td>
</tr>

<tr>
<td>
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;">部门<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="部门 " aria-describedby="basic-addon1" id="input_user_department" style="width:200px;"></input>
</div>
</td>
</tr>

<tr>
<td>
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;">电话<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="电话 " aria-describedby="basic-addon1" id="input_user_contactnumber" style="width:200px;"></input>
</div>
</td>
</tr>

<tr>
<td >
<div class="input-group" style="float:right;">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;text-align:right;">用户角色</span>
   <select value="2" class="selectpicker"  id="input_users_userrole" style="width:200px;height:25px;">普通用户
   <option value="2" selected="selected">普通用户</option>
   <option value="1">系统管理员</option>
   </select>
</div>
</td>

</tr>
<tr>
<td >
<div style="margin-top:5px;"><button class="btn btn-primary"  type="button" onclick="newuser_save()" value="" style="float:right;"><span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增</button></div>
<div style="width:100%;height:50px;display:block"></div>
</td>
</tr>
</table>


</div>


<script>
initpage();
</script>

</body>
</html>