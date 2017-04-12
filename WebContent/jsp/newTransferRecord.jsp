<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增调转记录</title>
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript" src="/MachineManagement/js/transferrecord.js"></script> 
</head>
<body>
<table>
<tr><td>设备编号</td><td><input class="input1" id="input_transferrecord_machineinfoid" type="text" value='<%=request.getParameter("machineid")%>' disabled="disabled"></input></td></tr>
<tr><td>新所有者</td><td><input class="input1" id="input_transferrecord_curowner" type="text" value=""></input></td></tr>
<tr><td>新所有者邮箱</td><td><input class="input1" id="input_transferrecord_curownerEmail" type="text" value=""></input></td></tr>
<tr><td colspan="2"><input type="button" onclick="transferrecord_save()" value="新增" style="float:right;"/></td></tr>
</table>

</body>

</html>