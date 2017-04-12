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


<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<script Language="JavaScript"  src='/MachineManagement/js/WdatePicker.js'></script>
 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title></title>

</head>

<body onload="initpage_userdetail()">

<input id="userdetail_userid" value="<%=request.getParameter("userid")%>" style="display:none"></input>

<div>
<a href="/MachineManagement/systemUserManagement.do?&userid=<%=request.getParameter("userid")%>" style="float:left;">主页 </a>
<p style="float:left;"> / </p>
<a href="#" style="float:left;">用户详情 </a>
</div>
<div style="clear:both;"></div>

<center  style="margin-top:30px;"">
<table style="width:320px;align:center;">
<tr>
<td>

<div class="input-group">
  <span class="input-group-addon " id="basic-addon1" style="width:100px;" >用户姓名<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control " placeholder="用户姓名" aria-describedby="basic-addon1" id="input_user_name"  style="width:200px;" ></input>
</div>

<div class="input-group">
  <span class="input-group-addon " id="basic-addon1" style="width:100px;" >登陆名<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control " placeholder="登录名" aria-describedby="basic-addon1" id="input_user_username"  style="width:200px;" ></input>
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1"  style="width:100px;">密码<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1" id="input_user_password" style="width:175px;"></input>
   <input type="checkbox"  id="inupt_checkbox_updatepw" style="width:25px;height:25px;margin:0px;padding:0px;" title="勾选修改密码"onclick="changepw_input(this);" />
   <input type="hidden" id="updatepw_hidden" value="0" />
   <input type="hidden" id="updatepw_hidden_old" value="" />
</div>


<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">邮箱<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="邮箱" aria-describedby="basic-addon1" id="input_user_email" style="width:200px;" ></input>
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">部门<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="部门 " aria-describedby="basic-addon1" id="input_user_department" style="width:200px;"></input>
</div>

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">电话<img src="/MachineManagement/image/redstar.png" /></span>
  <input type="text" class="form-control" placeholder="电话 " aria-describedby="basic-addon1" id="input_user_contactnumber" style="width:200px;"></input>
</div>


<div class="input-group" >
  <span class="input-group-addon" id="basic-addon1" style="width:100px;">用户角色</span>
   <select value="2" class="selectpicker"  id="input_users_userrole" style="width:200px;height:25px;">普通用户
   <option value="1">系统管理员</option>
   <option value="2" >普通用户</option>
   </select>
</div>


<div class="input-group" style="float:right;margin-right:10px;margin-top:10px;">
<button class="btn btn-default" onclick="save()">保存</button>
</div>
 
 </td>
 </tr>
 </table>
 
 </center>
 

</body>

<script>

function initpage_userdetail()
{
    var userid=document.getElementById("userdetail_userid").value;
	loaduserdetail(userid);
}

// 载入用户详情
function loaduserdetail(userid)
{
	 var url="/MachineManagement/getUserByUserid.do"
		 var pars="&userid="+userid;
      
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_loaduserdetail(request)}
			    }
			  );
	
}
// 载入用户详情完成事件，填充页面数据
function onComplete_loaduserdetail(request)
{
	 //alert(request.responseText);
	 var objJson = request.responseText.evalJSON();
	 
	 document.getElementById("input_user_name").value=objJson["name"];
	 document.getElementById("input_user_username").value=objJson["username"];
	 document.getElementById("input_user_password").value=objJson["password"];
	 document.getElementById("updatepw_hidden_old").value=objJson["password"];

	 document.getElementById("input_user_email").value=objJson["email"];
	 document.getElementById("input_user_department").value=objJson["department"];
	 document.getElementById("input_user_contactnumber").value=objJson["contactnumber"];
	 
	 input_user_password.disabled=true;
	 var userRole=objJson["userRole"];

	 var input_users_userrole=document.getElementById("input_users_userrole");
	 var optionarray=input_users_userrole.getElementsByTagName("option");

	 for(i=0;i<optionarray.length;i++)
	 {
		 
		 if((i+1)==userRole)
		{
			 input_users_userrole.options[i].selected="selected";
		}
		 else
		{
			 input_users_userrole.options[i].selected="";
		 }
	 }
	 
	// var index = input_users_userrole_obj.selectedIndex; // 选中索引
	 
	// var input_users_userrole = input_users_userrole_obj.options[index].value; // 选中值
}

// 保存用户信息
function save ()
{
	
	 var input_user_name=document.getElementById("input_user_name").value;
	 var input_user_username=document.getElementById("input_user_username").value;
	 var input_user_password=document.getElementById("input_user_password").value;
	 var input_user_email=document.getElementById("input_user_email").value;
	 var input_user_department=document.getElementById("input_user_department").value;
	 var input_user_contactnumber=document.getElementById("input_user_contactnumber").value;
	 
	 var input_users_userrole_obj=document.getElementById("input_users_userrole");
	 var index = input_users_userrole_obj.selectedIndex; // 选中索引
	 var input_users_userrole = input_users_userrole_obj.options[index].value; // 选中值
	 
	 
	 var updatepw_hidden=document.getElementById("updatepw_hidden").value;
	 
	  var check="";
	  
	  
	 if(!input_user_name||input_user_name==""||input_user_name==null)
	 {
			 check=check+"用户姓名是必填项;"+"\r\n";
	 }
		  
	 if(!input_user_username||input_user_username==""||input_user_username==null)
	 {
		 check=check+"登录名是必填项;"+"\r\n";
	 }
	  
	 if(!input_user_password||input_user_password==""||input_user_password==null)
	 {
		 check=check+"密码是必填项;"+"\r\n";
	 }
	 
	 if(!input_user_email||input_user_email==""||input_user_email==null)
	 {
		 check=check+"邮箱是必填项;"+"\r\n";
	 }
	 
	 if(!input_user_department||input_user_department==""||input_user_department==null)
	 {
		 check=check+"部门是必填项;"+"\r\n";
	 }
	 

	 if(!input_user_contactnumber||input_user_contactnumber==""||input_user_contactnumber==null)
	 {
		 check=check+"电话是必填项;"+"\r\n";
	 }
	 if(check!="")
	 {
		 alert(check);
		return;
	 }

	 var userid=document.getElementById("userdetail_userid").value;
	 
	 var url="/MachineManagement/updateUser.do"
		 var pars="&userid="+userid
		 +"&name="+input_user_name
		 +"&username="+input_user_username
         +"&password="+input_user_password
         +"&email="+input_user_email
         +"&department="+input_user_department
         +"&contactnumber="+input_user_contactnumber
         +"&roleid="+input_users_userrole
         +"&updatepw="+updatepw_hidden;
         
         
      
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
	alert(request.responseText);
}

//判断是否需要更新密码
function changepw_input(obj)
{
	var updatepw_hidden=document.getElementById("updatepw_hidden");
	 var input_user_password=document.getElementById("input_user_password");
	var updatepw_hidden_old=document.getElementById("updatepw_hidden_old").value;
	 if(obj.checked)
	{
		 obj.checked="checked";
		 updatepw_hidden.value="1";
		 input_user_password.disabled=false;
		 input_user_password.value="";
	}
	 else
	{
		 obj.checked="";
		 updatepw_hidden.value="0";
		 input_user_password.disabled=true;
		 input_user_password.value= updatepw_hidden_old;
	}
	 
}

</script>
</html>