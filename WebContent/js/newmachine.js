 
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
	 var input_purchaseTime_obj=document.getElementById("input_machineinfo_purchaseTime");
	 var input_price=document.getElementById("input_machineinfo_price").value;
	 var input_project=document.getElementById("input_machineinfo_project").value;
	 var input_comments=document.getElementById("input_machineinfo_comments").value;
	 var input_moveInTime=document.getElementById("input_machineinfo_moveInTime").value;
	 var input_moveInTime_obj=document.getElementById("input_machineinfo_moveInTime");
	 var input_machineinfo_purchaser=document.getElementById("input_machineinfo_purchaser").value;
	 var input_machineinfo_purchaseMethod=document.getElementById("input_machineinfo_purchaseMethod").value;
	 var input_machineinfo_supplier=document.getElementById("input_machineinfo_supplier").value;
	 var input_machineinfo_supplierContact=document.getElementById("input_machineinfo_supplierContact").value;
	 var input_machineinfo_supplierContactNumber=document.getElementById("input_machineinfo_supplierContactNumber").value;
	 var input_machineinfo_machineType_obj=document.getElementById("input_machineinfo_machineType");
	 var index = input_machineinfo_machineType_obj.selectedIndex; // 选中索引
	 var input_machineinfo_machineType = input_machineinfo_machineType_obj.options[index].value; // 选中值
	 var newmachine_responsibleuserid=document.getElementById("newmachine_responsibleuserid").value;
	 var input_machineinfo_propertyName=document.getElementById("input_machineinfo_propertyName").value;
	 
	 var check="";
	 
	 /* 	
	  if(!input_machineinfo_propertyName||input_machineinfo_propertyName==""||input_machineinfo_propertyName==null)
	 {
		 check=check+"资产名称是必填项;"+"\r\n";
	 }
	  */
	 
	 
/* 	 if(!input_propertyNumber||input_propertyNumber==""||input_propertyNumber==null)
	 {
		 check=check+"资产号是必填项;"+"\r\n";
	 }
	  */
	  
	 if(!input_machineLocation||input_machineLocation==""||input_machineLocation==null)
	 {
		 check=check+"机器位置是必填项;"+"\r\n";
	 }
/* 	 
	 if(!input_model||input_model==""||input_model==null)
	 {
		 check=check+"型号是必填项;"+"\r\n";
	 }
	  */
/* 	 if(!input_ipAdd||input_ipAdd==""||input_ipAdd==null)
	 {
		 check=check+"IP地址是必填项;"+"\r\n";
	 } */
	 
/* 	 if(!input_machineUsage||input_machineUsage==""||input_machineUsage==null)
	 {
		 check=check+"机器用途是必填项;"+"\r\n";
	 }
	  */
	  
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

	 
/* 	 if(!input_systemInfo||input_systemInfo==""||input_systemInfo==null)
	 {
		 check=check+"系统信息是必填项;"+"\r\n";
	 } */
	 
	 if(!input_moveInTime||input_moveInTime==""||input_moveInTime==null)
	 {
		 var myDate = new Date();
		 input_purchaseTime_obj.value=myDate.Format("yyyy-MM-dd hh:mm:ss"); 
		 input_moveInTime= input_purchaseTime_obj.value;
		// check=check+"购买时间是必填项;"+"\r\n";
	 }
	 
	 if(!input_purchaseTime||input_purchaseTime==""||input_purchaseTime==null)
	 {
		 var myDate = new Date();
		 input_moveInTime_obj.value=myDate.Format("yyyy-MM-dd hh:mm:ss"); 
		 input_purchaseTime= input_moveInTime_obj.value;
		// check=check+"购买时间是必填项;"+"\r\n";
	 }
	 
	 if(!input_price||input_price!=""||input_price!=null)
	 {
		 if(!isNumber(input_price))
		{
			check=check+"请用数字和小数点表示有效的价格(或者留空)"+"\r\n";
		}
	 } 
	 
/* 	 if(!input_project||input_project==""||input_project==null)
	 {
		 check=check+"项目是必填项;"+"\r\n";
	 } */
	 
	 if(!input_machineinfo_machineType||input_machineinfo_machineType==""||input_machineinfo_machineType==null)
	 {
		 check=check+"设备类型是必填项;"+"\r\n";
	 }
	 
	 
	 if(check!="")
	 {
		 alert(check);
		return;
	 }

	 var userid=document.getElementById("userid").value;
	 var url="/MachineManagement/addmachine.do"
		 var pars="&propertyNumber="+input_propertyNumber
		 +"&propertyName="+input_machineinfo_propertyName
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
         +"&userid="+userid
         +"&purchaser="+input_machineinfo_purchaser
         +"&purchaseMethod="+input_machineinfo_purchaseMethod
         +"&supplier="+input_machineinfo_supplier
         +"&supplierContact="+input_machineinfo_supplierContact
         +"&supplierContactNumber="+input_machineinfo_supplierContactNumber
         +"&machineType="+input_machineinfo_machineType
         +"&responsibleid="+newmachine_responsibleuserid;
	 
	 
	 
	 
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
	 alert(request.responseText);
	 var userid=document.getElementById("userid").value;
	  window.location.href="/MachineManagement/GetMachineInfoList.do?&userid="+userid;
 }

 function loadUserList_newmachine(tergetElement)
 {
 	var url="/MachineManagement/loadUserShortListNewMachine.do";
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

 
 function autoSetResponsibleInfoNewMachine(userid)
 {
	 document.getElementById("newmachine_responsibleuserid").value=userid;
	 
	 document
	 var url="/MachineManagement/getUserByUserid.do"
		 var pars="&userid="+userid;
      
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_autoSetResponsibleInfoNewMachine(request)}
			    }
			  );
 	
 }
 
//载入用户详情完成事件，自动填充相应的页面数据
 function onComplete_autoSetResponsibleInfoNewMachine(request)
 {
 	 //alert(request.responseText);
 	 var objJson = request.responseText.evalJSON();
 	 
 	 document.getElementById("input_machineinfo_responsible").value=objJson["name"];
 	 document.getElementById("input_machineinfo_responsibleEmail").value=objJson["email"];
 	 document.getElementById("input_machineinfo_department").value=objJson["department"];
 	 document.getElementById("input_machineinfo_responsibleContactNumber").value=objJson["contactnumber"];
 }