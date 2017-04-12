<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="../bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript" src='/MachineManagement/js/checkinfoa.js?randomId=<%=Math.random()%>'></script> 
<title>机器检查表（A）</title>

</head>

<body >

<input id="checkinfoa_machineid" value='<%=request.getParameter("machineid")%>' style="display:none;" />

<input id="checkinfoaid" type="hidden"></input>

<input id="checkrecordaid" type="hidden"></input>

<input id="checkinfoa_machineInfoID" type="hidden"></input>

<input id="checkrecorda_checkinfoid" type="hidden"></input>

<input id="checkrecorda_monthnumber" type="hidden"></input>

<% 
if( request.getParameter("ischeck").equals("1")){
%>
<div>
<a href="/MachineManagement/GetMachineInfoList.do?&userid=<%=request.getParameter("userid")%>" style="float:left;">主页 </a>
<p style="float:left;"> / </p>
<a href="#" style="float:left;">检查表 (A) </a>
</div>
<div style="clear:both;"></div>
<%}%>

<div class="panel panel-info">
  <div class="panel-heading"><h3><p style="display:inline-block;">中国科学院文献情报中心</p><p style="display:inline-block;" id="checkinfoa_year_identifier"></p><p style="display:inline-block;">年服务器系统检查登记表(A)</p></h3></div>
  <div class="panel-body">
  
<table class="table-bordered"  style="table-layout:fixed;width:100%">
<tr>
<th >流水号</th>
<td colspan="2" ><input class="form-control "aria-describedby="basic-addon1"  id="checkinfoamain_FlowNumber" type="text" value="" ></input></td>
<th>责任部门</th>
<td colspan="2" id="checkinfoamain_ResponsibilityDepartment"></td>
<th>机器位置</th>
<td colspan="2" id="checkinfoamain_MachineLocation"></td>
<th>IP地址</th>
<td colspan="2" id="checkinfoamain_IPAdd"></td>
</tr>


<tr>
<th style="with-space:nowrap;" >资产号</th>
<td colspan="3" id="checkinfoamain_PropertyNumber"></td>
<th>机器型号</th>
<td colspan="3" id="checkinfoamain_Model"></td>
<th>用途</th>
<td colspan="3" id="checkinfoamain_MachineUsage"></td>
</tr>

<tr>
<th style="with-space:nowrap;">序列号</th>
<td colspan="3"><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_SerialNumber" type="text" value="" ></input></td>
<th>系统信息</th>
<td colspan="3" id="checkinfoamain_SystemInfo"></td>
<th>维护人员</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_MantainceStaff" type="text" value="" ></input></td>
</tr>

<tr>
<th >备份内容</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_BackupContent" type="text" value="" ></input></td>
<th >变更1</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_BackupContentChange1" type="text" value="" ></input></td>
<th >变更2</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_BackupContentChange2" type="text" value="" ></input></td>
</tr>

<tr>
<th >文件目录</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_FileDirectory" type="text" value="" ></input></td>
<th >变更1</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_FileDirectoryChange1" type="text" value="" ></input></td>
<th >变更2</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1" id="checkinfoamain_FileDirectoryChange2" type="text" value="" ></input></td>
</tr>

<tr>
<th >备份周期</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1"  id="checkinfoamain_BackupPeriod" type="text" value="" ></td>
<th >变更1</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1"  id="checkinfoamain_BackupPeriodChange1" type="text" value=""></td>
<th >变更2</th>
<td colspan="3" ><input class="form-control "aria-describedby="basic-addon1"  id="checkinfoamain_BackupPeriodChange2" type="text" value=""></td>
</tr>
<tr>
<td colspan="12"><button class="btn btn-primary" style="display:inline-block;float:right;" onclick="updateCheckInfoA();" ><span class="glyphicon glyphicon-floppy-save" aria-hidden="true" ></span> 保存检查表(A)</button></td>
</tr>
</table>


<table id="checkrecorda" class="table-bordered" style="table-layout:fixed;width:100%">
<tr >
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month1" onclick="loadCheckRecordaByMonth(1)" style="font-size:13px;">01月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month2" onclick="loadCheckRecordaByMonth(2)" style="font-size:13px;">02月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month3" onclick="loadCheckRecordaByMonth(3)" style="font-size:13px;">03月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month4" onclick="loadCheckRecordaByMonth(4)" style="font-size:13px;">04月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month5" onclick="loadCheckRecordaByMonth(5)" style="font-size:13px;">05月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month6" onclick="loadCheckRecordaByMonth(6)" style="font-size:13px;">06月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month7" onclick="loadCheckRecordaByMonth(7)" style="font-size:13px;">07月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month8" onclick="loadCheckRecordaByMonth(8)" style="font-size:13px;">08月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month9" onclick="loadCheckRecordaByMonth(9)" style="font-size:13px;">09月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month10" onclick="loadCheckRecordaByMonth(10)" style="font-size:13px;">10月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month11" onclick="loadCheckRecordaByMonth(11)" style="font-size:13px;">11月</button></th>
<th ><button class="form-control"  aria-describedby="basic-addon1" id="checkinfoa_month12" onclick="loadCheckRecordaByMonth(12)" style="font-size:13px;">12月</button></th>
</tr>

<tr>
<td colspan="2" style="text-align:right;"><strong>操作系统<img src="/MachineManagement/image/redstar.png" /></strong></td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1"  id="checkrecorda_os" type="text" /></td>
</tr>

<tr>
<td colspan="2"></td>
<td colspan="10">
<input aria-describedby="basic-addon1" id="checkrecorda_application" type="checkbox" />&nbsp;应用程序&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_patch" type="checkbox" />&nbsp;补丁更新&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_dataBaseCheck" type="checkbox" />&nbsp;数据库&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_check360" type="checkbox" />&nbsp;360安全卫士&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_antivirus" type="checkbox" />&nbsp;杀毒软件
</td>
</tr>


<tr>
<td colspan="2" ><strong>是否正常使用账号<img src="/MachineManagement/image/redstar.png" /></strong></td>
<!-- 
<td colspan="2" >
<input aria-describedby="basic-addon1" id="checkrecorda_accountNormal1" type="checkbox" value="" />&nbsp;正常
<input aria-describedby="basic-addon1" id="checkrecorda_accountNormal2" type="checkbox" value="" />&nbsp;不正常
 -->
<td colspan="10" >
<input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_accountNormal" type="text" ></input>
</td>
</tr>

<tr>
<td colspan="2"><strong>密码强度</strong></td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" placeholder="(特殊字符+字母+数字=8位)"  id="checkrecorda_passwordStrength" type="text" /></td>
</tr>

<tr>
<th colspan="2" style="text-align:left;">日志情况</th>
<td colspan="10" >
<input aria-describedby="basic-addon1" id="checkrecorda_eventLog" type="checkbox" />&nbsp;事件日志<img src="/MachineManagement/image/redstar.png" />&nbsp;&nbsp;&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_webLog" type="checkbox" />&nbsp;web日志&nbsp;&nbsp;&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_dataBaseLog" type="checkbox" />&nbsp;数据库日志
</td>
</tr>

<tr>
<td colspan="2"><strong>硬盘空间情况<img src="/MachineManagement/image/redstar.png" /></strong></td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_hardDriverUsage" type="text" /></td>
</tr>

<tr>
<td colspan="2"><strong>页面篡改及异常</strong></td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_pageException" type="text" /></td>
</tr>

<tr>
<td colspan="2"><strong>反动及敏感信息</strong></td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_reactionaryException" type="text" /></td>
</tr>


<tr>
<td colspan="12"><strong>高危漏洞扫描</strong></td>
</tr>

<tr>
<td colspan="2" style="text-align:right;">有/无</td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" placeholder="(有/无)" id="checkrecorda_patchScanExist" type="text" /></td>
</tr>

<tr>
<td colspan="2" style="text-align:right;">处理结果</td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_patchScanHandle" type="text" /></td>
</tr>

<tr>
<td colspan="2" style="text-align:right;">扫描人</td>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_patchScanOperator" type="text" /></td>
</tr>



<tr>
<td colspan="2" ><strong>服务进程是否正常</strong></td>
<!-- 
<td colspan="2" >&nbsp;
<input aria-describedby="basic-addon1" id="checkrecorda_service1" type="checkbox" value="" />&nbsp;正常
<input aria-describedby="basic-addon1" id="checkrecorda_service2" type="checkbox" value="" />&nbsp;不正常
 -->
<td colspan="10" >
<input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_service" type="text" ></input>
</td>
</tr>

<tr>
<th colspan="2" >系统责任人签字<img src="/MachineManagement/image/redstar.png" /></th>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_osResponsibleSingnature" type="text" ></input></td>
</tr>

<tr>
<th colspan="2" >系统管理员签字</th>
<td colspan="10" ><input class="form-control "aria-describedby="basic-addon1" id="checkrecorda_osAdminsitratorSignature" type="text" ></input></td>
</tr>

<tr>
<td colspan="12" ><i style="float:left;align:bottom;margin: 12px auto 0px 1px;color:#6C6C6C;">(红色星号标记项请务必填写完整)</i><button class="btn btn-primary " onclick="updateCheckRecordA()" style="float:right;" id="checkinfoa_button_monthsave"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true" ></span> 保存本月检查记录</button></td>
</tr>
</table>
</div></div>
<script>
var checkinfoa_machineid="<%=request.getParameter("machineid")%>";
var checkinfoa_year;
var checkinfoa_month;
var checkinfoa_day;
var checkinfoa_currentmonth;

initpage_checkinfoa();

</script>
</body>



</html>