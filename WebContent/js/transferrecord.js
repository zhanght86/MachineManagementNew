/**
 * 
 */

function initpage_transferrecord()
{
	var transferrecord_machineid=document.getElementById("transferrecord_machineid").value;
	loadtransferrecord(transferrecord_machineid);
	loadUserShortListTransferRecord("responsible_list_div_transferrecord");

}

function loadtransferrecord(machineid)
{
    var url="/MachineManagement/transferrecord.do";
	var pars="&machineid="+machineid;
	var ajax=new Ajax.Updater(
			    "transferrecord_content_div",
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(){}
			    }
			  );
}

//function newTransferRecord()
//{
//	var winopen=window.open('/MachineManagement/jsp/newTransferRecord.jsp?&machineid='+transferrecord_machineid,'新增调转记录','height=200,width=300,top=300,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
//
//}


function transferrecord_save()
{
	 var input_MachineInfoID=document.getElementById("input_transferrecord_machineinfoid").value;
	 var input_CurOwner=document.getElementById("input_transferrecord_curowner").value;
	 var input_CurOwnerEmail=document.getElementById("input_transferrecord_curownerEmail").value;
	 var input_transferrecord_department=document.getElementById("input_transferrecord_department").value;
	 var input_transferrecord_reason=document.getElementById("input_transferrecord_reason").value;
	 
	 
	 var check="";
	 
  	 if(!input_CurOwner||input_CurOwner==""||input_CurOwner==null)
	 {
		 check=check+"新所有者是必填项;"+"\r\n";
	 }
  	 
  	 if(!input_CurOwnerEmail||input_CurOwnerEmail==""||input_CurOwnerEmail==null)
	 {
		 check=check+"新所有者邮箱是必填项;"+"\r\n";
	 }

  	 
  	 if(!input_transferrecord_department||input_transferrecord_department==""||input_transferrecord_department==null)
	 {
		 check=check+"所有者部门是必填项;"+"\r\n";
	 }

	 if(check!="")
	 {
		 alert(check);
		return;
	 }
	 
	 
	 
	 var url="/MachineManagement/addTransferRecord.do"
		 var pars="&MachineInfoID="+input_MachineInfoID+"&CurOwner="+input_CurOwner+"&CurOwnerEmail="+input_CurOwnerEmail+"&Department="+input_transferrecord_department+"&Reason="+input_transferrecord_reason;

	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_transferrecord_save(request)}
			    }
			  );
}

function onComplete_transferrecord_save(request)
{
	 alert(request.responseText);
	 loadtransferrecord(transferrecord_machineid);
}

function loadUserShortListTransferRecord(tergetElement)
{
	var url="/MachineManagement/loadUserShortListTransferRecord.do";
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

function autoSetResponsibleInfoTrasnferRecord(userid)
{
	 var url="/MachineManagement/getUserByUserid.do"
		 var pars="&userid="+userid;
     
	 var ajax=new Ajax.Request(
			    url, 
			    {
			    	method: "POST",
			    	parameters:pars,
			    	encoding:'UTF-8',
			        onComplete: function(request){onComplete_autoSetResponsibleInfoTrasnferRecord(request)}
			    }
			  );
	
}

//载入用户详情完成事件，自动填充相应的页面数据
function onComplete_autoSetResponsibleInfoTrasnferRecord(request)
{
	 //alert(request.responseText);
	 var objJson = request.responseText.evalJSON();
	 
	 document.getElementById("input_transferrecord_curowner").value=objJson["name"];
	 document.getElementById("input_transferrecord_curownerEmail").value=objJson["email"];
	 document.getElementById("input_transferrecord_department").value=objJson["department"];
	// document.getElementById("input_machineinfo_responsibleContactNumber").value=objJson["contactnumber"];
}


function transferrecord_savechange(transferrecordid)
{
	var tr_object= document.getElementById("transferrecord_tr_"+transferrecordid);
	
	var transferrecord_id=tr_object.cells[0].innerHTML;
	var transferrecord_machineInfoID=tr_object.cells[1].innerHTML;
	var transferrecord_pre_owner=tr_object.cells[2].childNodes[0].value;
	var transferrecord_pre_owner_email=tr_object.cells[3].childNodes[0].value;
	var transferrecord_cur_owner=tr_object.cells[4].childNodes[0].value
	var transferrecord_cur_owner_email=tr_object.cells[5].childNodes[0].value;
	var transferrecord_preid=tr_object.cells[6].innerHTML;
	var transferrecord_cur_owner_department=tr_object.cells[7].childNodes[0].value;
	var transferrecord_reason=tr_object.cells[8].childNodes[0].value;
	
	var url="/MachineManagement/updateTransferRecord.do";
	var pars="&id="+transferrecord_id
	                +"&MachineInfoID="+transferrecord_machineInfoID
	                +"&PreOwner="+transferrecord_pre_owner
	                +"&PreOwnerEmail="+transferrecord_pre_owner_email
	                +"&CurOwner="+transferrecord_cur_owner
	                +"&CurOwnerEmail="+transferrecord_cur_owner_email
	                +"&Department="+transferrecord_cur_owner_department
	                +"&preid="+transferrecord_preid
	                +"&Reason="+transferrecord_reason;
	
	
		 var ajax=new Ajax.Request(
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(response){onComplete_transferrecord_savechange(response)}
				    }
				  );
}



function onComplete_transferrecord_savechange(response)
{
	 alert(response.responseText);
}

function transferrecord_delete(id)
{
	
	if(!window.confirm("调转记录具有连续性，确认删除？")){return;}
	
	var url="/MachineManagement/deleteTransferRecord.do";
	var pars="&id="+id;
	
		 var ajax=new Ajax.Request(
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(response){onComplete_transferrecord_delete(response)}
				    }
				  );
}

function onComplete_transferrecord_delete(response)
{
	 alert(response.responseText);
	 loadtransferrecord(transferrecord_machineid);
}




