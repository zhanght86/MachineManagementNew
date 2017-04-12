<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%
response.setBufferSize(Integer.MAX_VALUE-1) ;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <link href="bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中国科学院文献情报中心资产管理系统</title>
<style>
body {
	background-image: url("/MachineManagement/image/loginbg.jpg");
}

.login_table
{
background-image: url("/MachineManagement/image/logintablebg.jpg");
z-index:10;

}
</style>
</head>
<body onload="ShowMessage();">

<input id='hidden_PreLoginResult' value="${result}" style="display:none;" />

<table width="100%" height="550px;" >
<tr style="height:100px;"><td colspan="3"><h2 style="margin-left:20px;">中国科学院文献情报中心资产管理系统</h2></td></tr>
<tr style="width:100%;" height="100%">
<td width="30%;" height="20%"></td>
<td style="align:middle;text-align:middle;height:60%;">

<IFRAME ID="Frame1" allowTransparency="true" style="width:100%; padding:0;margin-top:0px;margin-left:5px; border:none;height:380px;*height:380px;width:380px;_height:350px;_margin-top:-18px;_margin-left:10px;_background-repeat:no-repeat;" target="_blank" frameborder="0" SRC="https://passport.escience.cn/oauth2/authorize?response_type=code&redirect_uri=http://159.226.100.85/MachineManagement/token&client_id=71477&theme=embed" ></IFRAME>
<!--  
<form action="login.do" method="post">

 <table class="table table-bordered" style="width:600px;" >
 <tr><td colspan="2" style="text-align:center;align:middle;"></td></tr>

 <tr style="width:300px;">
 <td width="100px;">用户名:</td>
 <td style="width:200px;" ><input style="width:200px;"  type="text" name="username" /></td>
 </tr>
 <tr style="width:300px;">
  <td width="100px;">密码:</td>
  <td style="width:200px;"><input style="width:200px;"  type="password" name="password"/></td>
 </tr>
 <tr style="width:300px;">
 <td colspan="2"><input class="btn btn-default"  type="submit" value="登陆" style="float:right;"/></td>
 </tr>
 </table>
</form>

</td>
<td width="" height="20%"></td>
</tr>
</table>
-->
<script>

if (window != top) 
{
	top.location.href = location.href; 
}

 function ShowMessage()
 {
  var hidden_PreLoginResult=document.getElementById("hidden_PreLoginResult").value;
  if(hidden_PreLoginResult&&hidden_PreLoginResult!='')
  {
	alert(hidden_PreLoginResult);
  }
 }
</script>
</body>
</html>