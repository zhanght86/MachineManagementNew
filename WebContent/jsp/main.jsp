<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
response.setBufferSize(Integer.MAX_VALUE-1) ;
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<input type="hidden" id="userid" value="${userid}" />
<title>系统主界面</title>

</head>
<frameset cols="*,1024,*"  frameborder="no">
<frame src="about:blank" ></frame>
<frameset rows="90px,90%"  >
<frame src='header.do?&userid=${userid}&loginname=${loginname}&displayname=${displayname}'/>
<frameset cols="160px,85%" >
<frame src='menu.do?&userid=${userid}' /> 
<frame id="content" name="content" src='content.do' /> 
</frameset>
</frameset>
<frame src="about:blank"></frame>
</frameset>
</html>
