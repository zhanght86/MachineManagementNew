<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="commonTable"  style="table-layout:fixed;">
<tr><td colspan="12"><p>服务器检查登记(A)</p></td><tr>
<tr>
<th >流水号</th>
<td colspan="2">${checkInfoA.flowNumber} </td>
<th>责任部门</th>
<td colspan="2">${checkInfoA.responsibilityDepartment} </td>
<th>机器位置</th>
<td colspan="2">${checkInfoA.machineLocation} </td>
<th>IP地址</th>
<td colspan="2">${checkInfoA.ipAdd} </td>
</tr>


<tr>
<th style="with-space:nowrap;" >资产号</th>
<td colspan="3">${checkInfoA.propertyNumber} </td>
<th>机器型号</th>
<td colspan="3">${checkInfoA.model} </td>
<th>用途</th>
<td colspan="3">${checkInfoA.machineUsage} </td>
</tr>

<tr>
<th style="with-space:nowrap;">序列号</th>
<td colspan="3">${checkInfoA.serialNumber} </td>
<th>系统信息</th>
<td colspan="3">${checkInfoA.systemInfo} </td>
<th>维护人员</th>
<td colspan="3">${checkInfoA.mantainceStaff} </td>
</tr>

<tr>
<th >备份内容</th>
<td colspan="3">${checkInfoA.backupContent} </td>
<th >变更1</th>
<td colspan="3">${checkInfoA.backupContentChange1} </td>
<th >变更2</th>
<td colspan="3">${checkInfoA.backupContentChange2} </td>
</tr>

<tr>
<th >文件目录</th>
<td colspan="3">${checkInfoA.fileDirectory} </td>
<th >变更1</th>
<td colspan="3">${checkInfoA.fileDirectoryChange1} </td>
<th >变更2</th>
<td colspan="3">${checkInfoA.fileDirectoryChange2} </td>
</tr>

<tr>
<th >备份周期</th>
<td colspan="3">${checkInfoA.backupPeriod} </td>
<th >变更1</th>
<td colspan="3">${checkInfoA.backupPeriodChange1} </td>
<th >变更2</th>
<td colspan="3">${checkInfoA.backupPeriodChange2} </td>
</tr>
<tr>
<td colspan="12"><button style="display:inline-block;float:right;" onclick="updatemachine(${mi.id});" >保存检查表(A)</button></td>
</tr>
</table>