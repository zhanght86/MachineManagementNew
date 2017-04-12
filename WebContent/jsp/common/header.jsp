<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title></title>

<style>
body {
     width:1024px;
    height:70px;
}

.systemheader
{
	/*
	background-image: url("/MachineManagement/image/headerbg1.jpg");
	*/
	background-image: url("/MachineManagement/image/loginbg.jpg");
}
</style>

</head>
<body style="overflow:hidden;height:90px;" class="systemheader">

<div style="align:center;text-align:center;margin-top:0px;height:70px;border-bottom:1px solid rgb(175, 168, 168);" id="systemheader_div" >
  <h2 style=""><B>中国科学院文献情报中心资产管理系统</B></h2>
 <div style="display:inline;margin-top:0px;margin-right:30px;float:right;">
 <a style="text-decoration:none;color:black;"><%=request.getParameter("displayname")%>(<%=request.getParameter("loginname")%>) /</a>
  <a href="javascript:window.parent.location.href='http://passport.escience.cn/logout?WebServerURL=http%3A%2F%2F159.226.100.85%2FMachineManagement%2Findex.do' " title="退出系统"><span class="glyphicon glyphicon-off" aria-hidden="true" ></span>注销</a></p>
  </div>
</div>

</body>
</html>