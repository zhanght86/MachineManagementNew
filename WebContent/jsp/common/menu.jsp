<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" autoFlush="False" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
 <script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
  <script Language="JavaScript" src="/MachineManagement/js/menu.js"></script>
<input type="hidden" id="userid" value="<%=request.getParameter("userid")%>" />

<style>

</style>

<title>Insert title here</title>
</head>
<body>
<div style="border-right:1px solid rgb(175, 168, 168);height:100%;margin-top:0px;" id="main_div">

<div id="functionlist_div" style="margin-top:0px;"></div>

<li style="display:none;"><a href='../../GetCheckInfoHistoryList.do?&pagecounter=1' target="content" style="white-space:nowrap;">资产检查历史管理</a></li>
</div>


<script>

initpage_menu();
document.getElementById("main_div").style.height=(window.screen.height-230)+"px";
</script>
</body>

</html>