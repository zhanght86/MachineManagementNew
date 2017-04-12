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
<meta http-equiv="Pragma" content="no-cache" >
  <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title></title>
<style>
.input1
{
 width:300px;
}
</style>
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript"  src='/MachineManagement/js/WdatePicker.js'></script>
</head>
<body>

<div>
<a href="/MachineManagement/GetMachineInfoList.do?&userid=<%=request.getParameter("userid")%>" style="float:left;">主页 </a>
<p style="float:left;"> / </p>
<a href="#" style="float:left;">资产详情</a>
</div>

<div style="clear:both;"></div>
<div id="basicMachineDetail_div">
<jsp:include page="machineinfodetail.jsp" flush="true">     
     <jsp:param name="machineid" value='<%=request.getParameter("id")%>'/> 
     <jsp:param name="userid" value='<%=request.getParameter("userid")%>'/> 
</jsp:include> 
</div>

<div id="transferrecord_div">
<jsp:include page="transferrecord.jsp" flush="true">     
     <jsp:param name="machineid" value='<%=request.getParameter("id")%>'/> 
</jsp:include> 
</div>

<% 
if( request.getParameter("machineType").equals("2")){
%>
<div id="checkinfoa_div">
<jsp:include page="checkinfoa.jsp" flush="true">     
     <jsp:param name="machineid" value='<%=request.getParameter("id")%>'/> 
      <jsp:param name="ischeck" value='0'/> 
</jsp:include> 
</div>

<div id="checkinfob_div">
<jsp:include page="checkinfob.jsp" flush="true">     
     <jsp:param name="machineid" value='<%=request.getParameter("id")%>'/> 
     <jsp:param name="ischeck" value='0'/> 
</jsp:include> 
</div>

<%}%>
    <div style="width:100%;height:50px;display:block"></div>
</body>

</html>