/**
 * 
 */

function initpage()
{
	loadUsers();
}


function loadUsers()
{
	var url="/MachineManagement/loadUser.do";
	var pars="";
		 var ajax=new Ajax.Updater(
				    "usercontent_div" ,
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(request){}
				    }
				  );
}


function newuser_save()
{
	 var input_users_username=document.getElementById("input_users_username").value;
	 var input_users_password=document.getElementById("input_users_password").value;
	 var input_users_userrole_obj=document.getElementById("input_users_userrole");
	 var input_user_contactnumber=document.getElementById("input_user_contactnumber").value;
	 var input_users_name=document.getElementById("input_users_name").value;
	 
	 var index = input_users_userrole_obj.selectedIndex; // 选中索引
	 
	 var input_users_userrole = input_users_userrole_obj.options[index].value; // 选中值
	 
	 var input_user_email=document.getElementById("input_user_email").value;
	 var input_user_department=document.getElementById("input_user_department").value;
	 
	 var check="";
	 
	 
	 if(!input_users_name||input_users_name==""||input_users_name==null)
	 {
		 check=check+"用户姓名是必填项;"+"\r\n";
	 }
	 
  	 if(!input_users_username||input_users_username==""||input_users_username==null)
	 {
		 check=check+"登录名是必填项;"+"\r\n";
	 }
  	 
  	 if(!input_users_password||input_users_password==""||input_users_password==null)
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
  	
  	 if(!input_users_userrole||input_users_userrole==""||input_users_userrole==null)
	 {
		 check=check+"用户角色是必填项;"+"\r\n";
	 }

	 if(check!="")
	 {
		 alert(check);
		return;
	 }
	 
	 
	 
	 var url="/MachineManagement/addUser.do"
		 var pars="&name="+input_users_name
			            +"&username="+input_users_username
		               +"&password="+input_users_password
		               +"&userrole="+input_users_userrole
		               +"&email="+input_user_email
		               +"&department="+input_user_department
		               +"&contactnumber="+input_user_contactnumber;
	 
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_newuser_save(request)}
			    }
			  );
}

function onComplete_newuser_save(request)
{
	 alert(request.responseText);
	 loadUsers();
}





function deleteUser(userid)
{
	 var r=window.confirm("确认删除这个用户吗？")
	  if (r==true)
	    {
	 var url="/MachineManagement/deleteUser.do"
		 var pars="&userid="+userid;

	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_deleteuser(request)}
			    }
			  );
	    }
}

function onComplete_deleteuser(request)
{
	 loadUsers();
}



//显示详情
function userteail(userid)
{
	 url='/MachineManagement/jsp/userdetail.jsp?&userid='+userid;
	  window.location.href=url;
}

//全选
function selectAll(obj)
{
	var target_table=document.getElementById("userlist_table");
	var trArray=target_table.getElementsByTagName("tr");
	
	if(obj.checked)
	{
     for(i=1;i<trArray.length;i++)
    {
    	     if(! trArray[i].cells[0].childNodes[0].checked)
    	     {
    	    	 trArray[i].cells[0].childNodes[0].checked=true;
    	     }
     }
	}
	else
	{
	     for(i=1;i<trArray.length;i++)
	     {
	     	     if(trArray[i].cells[0].childNodes[0].checked)
	     	     {
	     	    	 trArray[i].cells[0].childNodes[0].checked=false;
	     	     }
	      }
	}
	 
}

