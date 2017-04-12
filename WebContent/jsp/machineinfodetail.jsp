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
 
 <script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript"  src='/MachineManagement/js/WdatePicker.js'></script>
 
<input id="machineinfo_machineid" value="<%=request.getParameter("id")%>" style="display:none"></input>
<input id="machineinfo_userid" value="<%=request.getParameter("userid")%>" style="display:none"></input>
<title>Insert title here</title>
</head>

<input id="machineinfo_responsibleuserid" value="" style="display:none"></input>
<body  onload="inipage();">
<div class="panel panel-info">
  <div class="panel-heading" ><h3>资产详情查看/修改</h3></div>
  <div class="panel-body">

<table class="table table-condensed" style="width:100%;">
<tr><th>资产名称</th><td colspan="3"><input class="form-control " placeholder="资产名称" aria-describedby="basic-addon1" id="input_machineinfo_propertyName" type="text" value=""></input></td></tr>
<tr><th>资产号</th><td><input class="form-control " placeholder="资产号" aria-describedby="basic-addon1" id="input_machineinfo_propertyNumber" type="text" value=""></input>
<th>机器位置<img src="/MachineManagement/image/redstar.png" /></th><td><input class="form-control " placeholder="机器位置" aria-describedby="basic-addon1" id="input_machineinfo_machineLocation" type="text" value=""></input></td></tr>

<tr><th>机型</th><td><input class="form-control " placeholder="机型" aria-describedby="basic-addon1" id="input_machineinfo_model" type="text" value=""></input></td>
<th>IP地址</th><td><input class="form-control " placeholder="IP地址 " aria-describedby="basic-addon1" id="input_machineinfo_ipAdd" type="text" value=""></input></td></tr>

<tr><th>用途</th><td><input class="form-control " placeholder="用途" aria-describedby="basic-addon1" id="input_machineinfo_machineUsage" type="text" value=""></input></td>
<th>设备类型</th><td>
  <select id="input_machineinfo_machineType" style="height:25px;">
   <option value="">(请选择类型)</option>
   <option value="1">电脑</option>
   <option value="2">服务器</option>
   <option value="3">笔记本</option>
   <option value="4">软件</option>
   <option value="5">硬件</option>
   <option value="6">网络设备</option>
   <option value="7">家具</option>
   <option value="8">打印机</option>
  </select>

  </td>
</tr>
<tr>
<th style="color:red;">选择责任人</th><td>  <div id="responsible_list_div_machineinfo" style="width:100%;"> </div></td>
<th>登记人</th><td><input class="form-control " placeholder="登记人" aria-describedby="basic-addon1" id="input_machineinfo_registrant" type="text" value="" readonly="readonly"></input></td>
</tr>

<tr><th>责任人<img src="/MachineManagement/image/redstar.png" /></th><td><input class="form-control " placeholder="责任人" aria-describedby="basic-addon1" id="input_machineinfo_responsible" type="text" value="" readonly="readonly"></input></td>
<th>邮箱<img src="/MachineManagement/image/redstar.png" /></th><td><input class="form-control " placeholder="邮箱" aria-describedby="basic-addon1" id="input_machineinfo_responsibleEmail" type="text" value="" readonly="readonly"></input></td></tr>

<tr><th>部门<img src="/MachineManagement/image/redstar.png" /></th><td><input class="form-control " placeholder="部门" aria-describedby="basic-addon1" id="input_machineinfo_department" type="text" value="" readonly="readonly"></input></td>
<th>责任人电话<img src="/MachineManagement/image/redstar.png" /></th><td><input class="form-control " placeholder="责任人电话" aria-describedby="basic-addon1" id="input_machineinfo_responsibleContactNumber" type="text" value=""  readonly="readonly"></input></td></tr>


<tr><th>购买人</th><td><input class="form-control " placeholder="购买人" aria-describedby="basic-addon1" id="input_machineinfo_purchaser" type="text" value="" ></input></td>
<th>购买方式</th><td><input class="form-control " placeholder="购买方式" aria-describedby="basic-addon1" id="input_machineinfo_purchaseMethod" type="text" value=""  ></input></td></tr>


<tr><th>供应商</th><td><input class="form-control " placeholder="供应商" aria-describedby="basic-addon1" id="input_machineinfo_supplier" type="text" value="" ></input></td>
<th>供应商联系人</th><td><input class="form-control " placeholder="供应商联系人" aria-describedby="basic-addon1" id="input_machineinfo_supplierContact" type="text" value="" ></input></td></tr>


<tr><th>供应商联系人电话</th><td><input class="form-control " placeholder="供应商联系人电话" aria-describedby="basic-addon1" id="input_machineinfo_supplierContactNumber" type="text" value="" ></input></td>
</tr>


<tr><th>系统信息</th><td><textarea class="form-control " style="height:100px;" placeholder="系统信息" aria-describedby="basic-addon1" id="input_machineinfo_systemInfo" type="text" value=""></textarea></td>
<th>所属课题</th><td><textarea class="form-control " style="height:100px;"  placeholder="归属项目" aria-describedby="basic-addon1" id="input_machineinfo_project" type="text" value=""></textarea></td></tr>

<tr><th>价格</th><td><input class="form-control " placeholder="价格" aria-describedby="basic-addon1" id="input_machineinfo_price" type="text" value=""></input></td>
<th>购买时间</th><td><input class="form-control " placeholder="购买时间" aria-describedby="basic-addon1"id="input_machineinfo_purchaseTime" type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></input></td></tr>



<tr><th>入机房时间</th><td><input class="form-control " placeholder="入机房时间" aria-describedby="basic-addon1" id="input_machineinfo_moveInTime" type="text" value="" disabled="disabled"></input></td>
<th>记录更新时间</th><td><input class="form-control " placeholder="记录更新时间" aria-describedby="basic-addon1" id="input_machineinfo_updateTime" type="text" value="" disabled="disabled"></input></td></tr>

<tr><th>备注</th><td colspan="3"><textarea  class="form-control " placeholder="备注" aria-describedby="basic-addon1" id="input_machineinfo_comments" type="text" value="" ></textarea ></td></tr>

<tr><td colspan="4">
<div style="float:right;margin-right:10px;">
<button class="btn btn-primary" onclick="save()"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true" ></span> 保存更改</button>
</div>
</td></tr>
</table>
  </div>
</div>
<script>
loadUserShortListMachineDetailInfo("responsible_list_div_machineinfo");

 function inipage()
 {
	 loadMachineInfo();
 }

 function loadMachineInfo()
 {
	 var id=document.getElementById("machineinfo_machineid").value;

	 var url="/MachineManagement/GetMachineInfoById.do"
	 var pars="&id="+id;
	 
	 url=encodeURI(url);
	 pars=encodeURI(pars);
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_loadMachineInfo(request)}
			    }
			  );
	 
 }
 
 function onComplete_loadMachineInfo(request)
 {
	 var objJson = request.responseText.evalJSON();
	 
	 document.getElementById("machineinfo_responsibleuserid").value=objJson["responsibleUserId"];
	 document.getElementById("input_machineinfo_propertyNumber").value=objJson["propertyNumber"];
	 document.getElementById("input_machineinfo_machineLocation").value=objJson["machineLocation"];
	 document.getElementById("input_machineinfo_model").value=objJson["model"];
	 document.getElementById("input_machineinfo_ipAdd").value=objJson["ipAdd"];
	 document.getElementById("input_machineinfo_machineUsage").value=objJson["machineUsage"];
	 document.getElementById("input_machineinfo_department").value=objJson["department"];
	 document.getElementById("input_machineinfo_responsible").value=objJson["responsible"];
	 document.getElementById("input_machineinfo_responsibleEmail").value=objJson["responsibleEmail"];
	 document.getElementById("input_machineinfo_responsibleContactNumber").value=objJson["responsibleContactNumber"];
	 document.getElementById("input_machineinfo_systemInfo").value=objJson["systemInfo"];
	 document.getElementById("input_machineinfo_purchaseTime").value=objJson["purchaseTime"];
	 document.getElementById("input_machineinfo_price").value=objJson["price"];
	 document.getElementById("input_machineinfo_project").value=objJson["project"];
	 document.getElementById("input_machineinfo_comments").value=objJson["comments"];
	 document.getElementById("input_machineinfo_registrant").value=objJson["registrant"];
	 document.getElementById("input_machineinfo_moveInTime").value=objJson["moveInTime"];
	 document.getElementById("input_machineinfo_updateTime").value=objJson["updateTime"];
	 document.getElementById("input_machineinfo_purchaser").value=objJson["purchaser"];
	 document.getElementById("input_machineinfo_purchaseMethod").value=objJson["purchaseMethod"];
	 document.getElementById("input_machineinfo_supplier").value=objJson["supplier"];
	 document.getElementById("input_machineinfo_supplierContact").value=objJson["supplierContact"];
	 document.getElementById("input_machineinfo_supplierContactNumber").value=objJson["supplierContactNumber"];
	 document.getElementById("input_machineinfo_propertyName").value=objJson["propertyName"];
	 
	 var machineType=objJson["machineType"];
	 var input_machineinfo_machineType=document.getElementById("input_machineinfo_machineType");
	 var optionarray=input_machineinfo_machineType.getElementsByTagName("option");

	 for(i=0;i<optionarray.length;i++)
	 {
		 
		 if(i==machineType)
		{
			 input_machineinfo_machineType.options[i].selected="selected";
		}
		 else
		{
			 input_machineinfo_machineType.options[i].selected="";
		 }
	 }
	 
	 
	 
	 //document.getElementById("input_machineinfo_emailList").value=objJson["emailList"];



 }
 
 function isNumber(e) { 
     var re =/^(-$|-\d*|-\d*\.|-\d*\.\d*|0|\d*|\d*\.|\d*\.\d*)$/
     if (e!= "")
     { 
         if (!re.test(e))
 		{ 
             return 0;
         }
         else
         {
         	return 1;
         }
     }
     else
     {
     	 return 1;
     }
 }
 function save()
 {
	 var id=document.getElementById("machineinfo_machineid").value;
	 var input_propertyNumber=document.getElementById("input_machineinfo_propertyNumber").value;
	 var input_machineLocation=document.getElementById("input_machineinfo_machineLocation").value;
	 var input_model=document.getElementById("input_machineinfo_model").value;
	 var input_ipAdd=document.getElementById("input_machineinfo_ipAdd").value;
	 var input_machineUsage=document.getElementById("input_machineinfo_machineUsage").value;
	 var input_department=document.getElementById("input_machineinfo_department").value;
	 var input_responsible=document.getElementById("input_machineinfo_responsible").value;
	 var input_responsibleEmail=document.getElementById("input_machineinfo_responsibleEmail").value;
	 var input_responsibleContactNumber=document.getElementById("input_machineinfo_responsibleContactNumber").value;
	 var input_systemInfo=document.getElementById("input_machineinfo_systemInfo").value;
	 var input_purchaseTime=document.getElementById("input_machineinfo_purchaseTime").value;
	 var input_price=document.getElementById("input_machineinfo_price").value;
	 var input_project=document.getElementById("input_machineinfo_project").value;
	 var input_comments=document.getElementById("input_machineinfo_comments").value;
	 var input_moveInTime=document.getElementById("input_machineinfo_moveInTime").value;
	 
	 var input_machineinfo_purchaser=document.getElementById("input_machineinfo_purchaser").value;
	 var input_machineinfo_purchaseMethod=document.getElementById("input_machineinfo_purchaseMethod").value;
	 var input_machineinfo_supplier=document.getElementById("input_machineinfo_supplier").value;
	 var input_machineinfo_supplierContact=document.getElementById("input_machineinfo_supplierContact").value;
	 var input_machineinfo_supplierContactNumber=document.getElementById("input_machineinfo_supplierContactNumber").value;
	 
	 
	 
	 var input_machineinfo_machineType_obj=document.getElementById("input_machineinfo_machineType");
	 var index = input_machineinfo_machineType_obj.selectedIndex; // 选中索引
	 var input_machineinfo_machineType = input_machineinfo_machineType_obj.options[index].value; // 选中值
	 
	 
	 var machineinfo_responsibleuserid=document.getElementById("machineinfo_responsibleuserid").value;
	 var input_machineinfo_propertyName=document.getElementById("input_machineinfo_propertyName").value;
	 
	 var check="";
	 

	 /*
	
 	 if(!input_machineinfo_propertyName||input_machineinfo_propertyName==""||input_machineinfo_propertyName==null)
 	 {
 		 check=check+"资产名称是必填项;"+"\r\n";
 	 }
 */
	 	 if(!input_machineLocation||input_machineLocation==""||input_machineLocation==null)
	 	 {
	 		 check=check+"机器位置是必填项;"+"\r\n";
	 	 }

	 	  
	 	 if(!input_department||input_department==""||input_department==null)
	 	 {
	 		 check=check+"所属部门是必填项;"+"\r\n";
	 	 }
	 	 if(!input_responsible||input_responsible==""||input_responsible==null)
	 	 {
	 		 check=check+"责任人是必填项;"+"\r\n";
	 	 }
	 	 if(!input_responsibleEmail||input_responsibleEmail==""||input_responsibleEmail==null)
	 	 {
	 		 check=check+"责任人邮箱是必填项;"+"\r\n";
	 	 }
	 	 if(!input_responsibleContactNumber||input_responsibleContactNumber==""||input_responsibleContactNumber==null)
	 	 {
	 		 check=check+"责任人电话是必填项;"+"\r\n";
	 	 }
		 if(!input_price||input_price!=""||input_price!=null)
		 {
			 if(!isNumber(input_price))
			{
				check=check+"请用数字和小数点表示有效的价格(或者留空)"+"\r\n";
			}
		 } 
	 	 
	 	 if(check!="")
	 	 {
	 		 alert(check);
	 		return;
	 	 }
	 
	 
	 
	 
	 var url="/MachineManagement/updatemachine.do"
	 var pars="&userid="+machineinfo_responsibleuserid
		       +"&id="+id
		       +"&propertyName="+input_machineinfo_propertyName
		       +"&propertyNumber="+input_propertyNumber
	           +"&machineLocation="+input_machineLocation
	           +"&model="+input_model
	           +"&ipAdd="+input_ipAdd
	           +"&machineUsage="+input_machineUsage
	           +"&department="+input_department
	           +"&responsible="+input_responsible
	           +"&responsibleEmail="+input_responsibleEmail
	           +"&responsibleContactNumber="+input_responsibleContactNumber
	           +"&systemInfo="+input_systemInfo
	           +"&purchaseTime="+input_purchaseTime
	           +"&price="+input_price
	           +"&project="+input_project
	           +"&comments="+encodeURIComponent(input_comments)
	           +"&moveInTime="+input_moveInTime
	           +"&purchaser="+input_machineinfo_purchaser
	           +"&purchaseMethod="+input_machineinfo_purchaseMethod
	           +"&supplier="+input_machineinfo_supplier
	           +"&supplierContact="+input_machineinfo_supplierContact
	           +"&supplierContactNumber="+input_machineinfo_supplierContactNumber
	           +"&machineType="+input_machineinfo_machineType;
	           
	 
	url=encodeURI(url);
	 pars=encodeURI(pars);
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_save(request)}
			    }
			  );
 }
 
 function onComplete_save(request)
 {
	 loadMachineInfo();
	 alert(request.responseText);
 }



 function loadUserList(tergetElement)
 {
 	var url="/MachineManagement/loadUserShortList.do";
 	var pars="";
 		 var ajax=new Ajax.Updater(
 				    tergetElement,
 				    url, 
 				    {
 				    	method: "POST",
 				    	parameters:pars,
 				    	encoding:'UTF-8',
 				        onComplete: function(){}
 				    }
 				  );
 }



 function loadUserShortListMachineDetailInfo(tergetElement)
 {
 	var url="/MachineManagement/loadUserShortListMachineDetailInfo.do";
 	var pars="";
 		 var ajax=new Ajax.Updater(
 				    tergetElement,
 				    url, 
 				    {
 				    	method: "POST",
 				    	parameters:pars,
 				    	encoding:'UTF-8',
 				        onComplete: function(){}
 				    }
 				  );
 }


 
 function autoSetResponsibleInfoMachineDetailInfo(userid)
 {
	 document.getElementById("machineinfo_responsibleuserid").value=userid;
	 var url="/MachineManagement/getUserByUserid.do"
		 var pars="&userid="+userid;
      
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_autoSetResponsibleInfoMachineDetailInfo(request)}
			    }
			  );
 	
 }
 
//载入用户详情完成事件，自动填充相应的页面数据
 function onComplete_autoSetResponsibleInfoMachineDetailInfo(request)
 {
 	 //alert(request.responseText);
 	 var objJson = request.responseText.evalJSON();
 	 
 	 document.getElementById("input_machineinfo_responsible").value=objJson["name"];
 	 document.getElementById("input_machineinfo_responsibleEmail").value=objJson["email"];
 	 document.getElementById("input_machineinfo_department").value=objJson["department"];
 	 document.getElementById("input_machineinfo_responsibleContactNumber").value=objJson["contactnumber"];
 }
 
</script>

</body>
</html>