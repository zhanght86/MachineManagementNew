var userid=document.getElementById("userid");


//IE8 不兼容js 中 string trim() 方法
String.prototype.trim = function(){ return Trim(this);};
function LTrim(str)
{
    var i;
    for(i=0;i<str.length;i++)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(i,str.length);
    return str;
}
function RTrim(str)
{
    var i;
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(0,i+1);
    return str;
}
function Trim(str)
{
    return LTrim(RTrim(str));
}

//搜索栏回车按钮
function FSubmit(e)
{
	if(e ==13|| e ==32)
	{
		reloadMachineInfoList(null,null);
	}
}


//页面载入，初始化函数
function init()
{
	 //载入数据
	loadMachineInfoList();
}


//载入机器信息列表
function loadMachineInfoList(in_keyword,in_pagecounter,in_pageamount,in_showall,in_searchcondition,in_orderstring)
{	
	var keyword=in_keyword||"";
	var pagecounter=in_pagecounter||1;
	var pageamount=in_pageamount||10;
	var showall=in_showall||"0";
	var searchconditio=in_searchcondition||"";
	var orderstring=in_orderstring||" order by machineinfo.moveintime desc ";
	
	var url="/MachineManagement/loadMachineInfoList.do";
		   var pars="&keyword="+keyword
			              +"&pagecounter="+pagecounter
		                  +"&pageamount="+pageamount
		                  +"&showall="+showall
		                  +"&searchcondition="+encodeURI(searchconditio)
		                  +"&orderstring="+orderstring
		                  +"&userid="+userid.value;
		 
		 var ajax=new Ajax.Updater(
				    "machineinfolist_div" ,
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(request){onComplete_loadMachineInfoList(request)}
				    }
				  );
}


function onComplete_loadMachineInfoList()
{
	setCheckButtonStyle();
	pagersetting();
	orderButtonStyleSetting();
}
	

//设置页脚的上一页下一页按钮显隐
function pagersetting()
{
	
	var pageamount_select=document.getElementById("pageamount_select");
	var pageamount=parseInt(document.getElementById("pageamount").value);
	if(pageamount_select&&pageamount_select!=null&&pageamount_select!=undefined&&pageamount_select!="")
	{
		for(i=0;i<pageamount_select.options.length;i++)
	   {
			 if(pageamount_select.options[i].value==pageamount)
		     {
				 pageamount_select.options[i].selected="selected";
				 break;
			 }
			}
	}
	

//	 var pagecounter=parseInt(document.getElementById("pagecounter").value);
//	 var totalpages=parseInt(document.getElementById("totalpages").value);
//	 var nextpage=document.getElementById("nextpage");
//	 var prepage=document.getElementById("prepage");
//	
//	 if(pagecounter+1>totalpages)
//	 {
//		 nextpage.readonly=true;
//	 }
//	 else
//	 {
//		 nextpage.readonly=false;
//	 }
//	 
//	 if(pagecounter-1<1)
//	 {
//		 prepage.readonly=true;
//	 }
//	 else
//	 {
//		 prepage.readonly=false;
//	 }
}


function orderButtonStyleSetting()
{
	  //设置排序按钮的样式
	var orderbuttonstyle=document.getElementById("orderbuttonstyle").value;
	if(orderbuttonstyle!=null&&orderbuttonstyle!=""&&orderbuttonstyle!=undefined)
	{
		var styleArray=orderbuttonstyle.split(',');
		var orderbutton=document.getElementById(styleArray[0]);
		if(orderbutton&&orderbutton!=undefined)
		{
			orderbutton.className=styleArray[1];
		}
	}
}

//根据机器当月的A B 检查表填写情况和当月日期，设置按钮的样式
function setCheckButtonStyle()
{
	var check_myDate = new Date();
    var check_year=check_myDate.getFullYear();
	var check_month=check_myDate.getMonth()+1;
	var check_day=check_myDate.getDate();
	 var machineidarray=document.getElementsByName("name_machineid");

	 if(machineidarray.length!=0)
      {
		 for(i=0;i<machineidarray.length;i++)
		{
			 if(document.getElementById("checkStateA_"+machineidarray[i].innerHTML).value=='0')
			 {
				 if(check_day<=5&&check_day>=1)
				 {
					 if(document.getElementById("button_CheckA_"+machineidarray[i].innerHTML))
					{
					 document.getElementById("button_CheckA_"+machineidarray[i].innerHTML).style.backgroundColor ="#FFFF6F";
					}
				 }
				 else if(check_day>=5&&check_day<=12)
				 {
					 if(document.getElementById("button_CheckA_"+machineidarray[i].innerHTML))
						{
					 document.getElementById("button_CheckA_"+machineidarray[i].innerHTML).style.backgroundColor ="#FF7575";
						}
				 }
				 else
				 {
					 if(document.getElementById("button_CheckA_"+machineidarray[i].innerHTML))
						{
					 document.getElementById("button_CheckA_"+machineidarray[i].innerHTML).style.backgroundColor ="red";
						}
				 }
			  }
			 
//			 if(document.getElementById("checkStateB_"+machineidarray[i].innerHTML).value=='0')
//			 {
//				 if(check_day<=5&&check_day>=1)
//				 {
//					 if(document.getElementById("button_CheckB_"+machineidarray[i].innerHTML))
//						{
//					 document.getElementById("button_CheckB_"+machineidarray[i].innerHTML).style.backgroundColor ="#FF7575";
//						}
//				 }
//				 else if(check_day>=5&&check_day<=12)
//				 {
//					 if(document.getElementById("button_CheckB_"+machineidarray[i].innerHTML))
//						{
//					 document.getElementById("button_CheckB_"+machineidarray[i].innerHTML).style.backgroundColor ="#FF2D2D";
//						}
//				 }
//				 else
//				 {
//					 if(document.getElementById("button_CheckB_"+machineidarray[i].innerHTML))
//						{
//					 document.getElementById("button_CheckB_"+machineidarray[i].innerHTML).style.backgroundColor ="red";
//						}
//				 }
//				 
//			  }
			 
			 
		 }
	 }
}

//新增机器
function newmachine()
{
	var userid=document.getElementById("userid").value;
	//window.open('/MachineManagement/jsp/newmachine.jsp?&userid='+userid,'新增机器','height=500,width=450,top=200,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
	window.location.href='newmachine.do?&userid='+userid;
}

//显示详情
function machinedetail(machineid)
{
	var userid=document.getElementById("userid").value;
	 var tr=document.getElementById("tr_"+machineid);
	 var pars="?&id="+tr.cells[0].innerHTML+"&userid="+userid+"&machineType="+tr.cells[10].innerHTML;
	 url='machineDetails.do'+pars;
	  window.location.href=url;
}


//删除机器
function deletemachine(machineid)
{  
	 var r=window.confirm("确认删除这台机器的相关记录吗？")
	  if (r==true)
	    {
		  var userid=document.getElementById("userid").value;
		     var url="/MachineManagement/deletemachine.do?id="+machineid+"&userid="+userid;
				 var ajax=new Ajax.Request(
						    url, 
						    {
						    	parameters:"",
						    	encoding:'UTF-8',
						        onComplete: function(request){onComplete_delete(request)}
						    }
						  );
	    }
}

function onComplete_delete(request)
{
	 //alert(request.responseText);
	 reloadMachineInfoList(null,null);
}

//打开某机器的A检查表
function chekinfoa(machineid)
{
	//window.open('/MachineManagement/jsp/checkinfoa.jsp?&machineid='+machineid); 
	var userid=document.getElementById("userid").value;
	window.location.href='/MachineManagement/jsp/checkinfoa.jsp?&machineid='+machineid+"&userid="+userid+"&ischeck=1";
		
	
}

//打开某机器的B检查表
function chekinfob(machineid)
{
	//window.open('/MachineManagement/jsp/checkinfob.jsp?&machineid='+machineid); 
	var userid=document.getElementById("userid").value;
	 window.location.href='/MachineManagement/jsp/checkinfob.jsp?&machineid='+machineid+"&userid="+userid+"&ischeck=1";
	
}

//针对某机器发送当月检查表填写情况的提示邮件
function sendEmailById(machineid)
{

    var url="/MachineManagement/sendEmailById.do"
		 var pars="&machineid="+machineid
		                  +"&userid="+userid.value;
		 var ajax=new Ajax.Request(
				    url, 
				    {
				    	method: "POST",
				    	parameters:pars,
				    	encoding:'UTF-8',
				        onComplete: function(request){onComplete_sendEmailById(request)}
				    }
				  );
}

function onComplete_sendEmailById(request)
{
	 alert(request.responseText);
}


//针对某台机器生成当月检查表A B 的WORD 附件
function wordexport(machineid)
{
	location.href='/MachineManagement/wordExportByMachineId.do?&machineid='+machineid;
	//window.open('/MachineManagement/wordExportByMachineId.do?&machineid='+machineid,"word导出",'height=460,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
}

function exportWordMachineInfo1(direction,amount)
{
	
	var pagecounter=document.getElementById("pagecounter").value;
	var totalpages=document.getElementById("totalpages").value;
	var pageamount=document.getElementById("pageamount").value;
	var keyword=document.getElementById("machinesearch").value;
	//var searchcondition=document.getElementById("searchcondition").value;
	var orderstring=document.getElementById("orderstring").value;
	var showall="1";

	if(!pagecounter||pagecounter==""||pagecounter==null){pagecounter=1;}
	if(!totalpages||totalpages==""||totalpages==null){totalpages=1;}
	if(!pageamount||pageamount==""||pageamount==null){pageamount=10;}
	if(!keyword||keyword==""||keyword==null){keyword="";}
	if(!searchcondition||searchcondition==""||searchcondition==null){searchcondition="";}
	if(!orderstring||orderstring==""||orderstring==null){orderstring="";}
	
	pagecounter=parseInt(pagecounter);
	totalpages=parseInt(totalpages);
	pageamount=parseInt(pageamount);
	
	//针对翻页请求做判断处理
	if(direction&&direction!=null&&direction!=undefined&&direction!="")
	{
		if(direction==1)
		{
			if(pagecounter>1){pagecounter=pagecounter-1;}
		}
		else if(direction==2)
		{
			if(pagecounter<totalpages){pagecounter=pagecounter+1}
		}
	}
	
	//针对改变页面数据量判断处理
//	if(amount&&amount!=null&&amount!=undefined&&amount!="")
//	{
//	  if(amount=="ALL")
//	  {showall="1";}
//	  else
//	  {
//		  showall="0";
//		  pageamount= parseInt(amount);
//	  }
//	  
//	}
	
	//首次搜索时候，从第一页显示
	if((!direction||direction==null||direction==undefined||direction=="") &&(!amount||amount==null||amount==undefined||amount=="")) 
	{
		pagecounter=1;
	}
	
	//针对条件搜索做出判断
	var propertynumber=document.getElementById("checkby_propertynumber_checkbox");
	var machinelocation=document.getElementById("checkby_machineLocation_checkbox");
	var ipaddr=document.getElementById("checkby_ipaddr_checkbox");
	var machineusage=document.getElementById("checkby_machineUsage_checkbox");
	var department=document.getElementById("checkby_department_checkbox");
	var responsible=document.getElementById("checkby_responsible_checkbox");
	
	// 资产类型
	var checkby_machineType_checkbox_1=document.getElementById("checkby_machineType_checkbox_1");
	var checkby_machineType_checkbox_2=document.getElementById("checkby_machineType_checkbox_2");
	var checkby_machineType_checkbox_3=document.getElementById("checkby_machineType_checkbox_3");
	var checkby_machineType_checkbox_4=document.getElementById("checkby_machineType_checkbox_4");
	var checkby_machineType_checkbox_5=document.getElementById("checkby_machineType_checkbox_5");
	var checkby_machineType_checkbox_6=document.getElementById("checkby_machineType_checkbox_6");
	var checkby_machineType_checkbox_7=document.getElementById("checkby_machineType_checkbox_7");
	var checkby_machineType_checkbox_8=document.getElementById("checkby_machineType_checkbox_8");
	
	var conditionstr="";
	
	  //资产号
	  if(propertynumber&&propertynumber.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString(' machineinfo.PropertyNumber REGEXP ',keyword.trim())+"   or ";
	  }
	  
	  //ip
	  if(ipaddr&&ipaddr.checked)
	  {
		  conditionstr=conditionstr+"  "+buildConditionString('machineinfo.IPAdd REGEXP',keyword.trim())+"   or ";
	  }
	  
	  //用途
	  if(machineusage&&machineusage.checked)
	  {
		  conditionstr=conditionstr+"  "+buildConditionString('machineinfo.MachineUsage REGEXP',keyword.trim())+"   or ";
	  }
	  
	  //位置
	  if(machinelocation&&machinelocation.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString('machineinfo.MachineLocation REGEXP',keyword.trim())+"   or ";
	  }
	  
	  //部门
	  if(department&&department.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString('machineinfo.Department REGEXP',keyword.trim())+" or ";
	  }
	  
	  //责任人
	  if(responsible&&responsible.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString('machineinfo.Responsible REGEXP',keyword.trim())+" or ";
	  }
	  
	  if(checkby_machineType_checkbox_1&&checkby_machineType_checkbox_1.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='1' or ";
	  }
	  
	  if(checkby_machineType_checkbox_2&&checkby_machineType_checkbox_2.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='2' or ";
	  }
	  
	  if(checkby_machineType_checkbox_3&&checkby_machineType_checkbox_3.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='3' or ";
	  }
	  
	  if(checkby_machineType_checkbox_4&&checkby_machineType_checkbox_4.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='4' or ";
	  }
	  if(checkby_machineType_checkbox_5&&checkby_machineType_checkbox_5.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='5' or ";
	  }
	  if(checkby_machineType_checkbox_6&&checkby_machineType_checkbox_6.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='6' or ";
	  }
	  if(checkby_machineType_checkbox_7&&checkby_machineType_checkbox_7.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='7' or ";
	  }
	  if(checkby_machineType_checkbox_8&&checkby_machineType_checkbox_8.checked)
	  {
		  conditionstr=conditionstr+" machineinfo.MachineType='8' or ";
	  }
	  
	   if(conditionstr!="")
		{
			   conditionstr=conditionstr.trim();
			  
			   if(conditionstr.substr(conditionstr.length-2,2)=="or")
				{
				   conditionstr=conditionstr.substr(0,conditionstr.length-2);
				}
			   
				  
			   if(conditionstr.substr(conditionstr.length-3,3)=="and")
				{
				   conditionstr=conditionstr.substr(0,conditionstr.length-3);
				}
			   
		   conditionstr=" and ( "+conditionstr+" ) ";
		 }


	loadMachineInfoListForExport(keyword,pagecounter,pageamount,"1",conditionstr,orderstring);
}

//载入机器信息列表
function loadMachineInfoListForExport(in_keyword,in_pagecounter,in_pageamount,in_showall,in_searchcondition,in_orderstring)
{	
	var keyword=in_keyword||"";
	var pagecounter=in_pagecounter||1;
	var pageamount=in_pageamount||10;
	var showall=in_showall||"0";
	var searchconditio=in_searchcondition||"";
	var orderstring=in_orderstring||" order by machineinfo.moveintime desc ";
	
	var url="/MachineManagement/wordExportMachineInfo1.do";
		   var pars="?&keyword="+keyword
			              +"&pagecounter="+pagecounter
		                  +"&pageamount="+pageamount
		                  +"&showall="+showall
		                  +"&searchcondition="+encodeURI(searchconditio)
		                  +"&orderstring="+orderstring
		                  +"&userid="+userid.value;
		   url=url+pars;
		   location.href=url;
			//window.open(url,"word导出",'height=200,width=500,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
}

//处理条件语句多个空格替换问题
function buildConditionString(sqlString,conditionString)
{
	var finalConditionString='';
	conditionString=conditionString.trim().replace(/\s+/g, '|');
	var conditionArray=conditionString.split('|');
	if(conditionArray.length>0)
	{
		for(var i=0;i<conditionArray.length;i++)
		{
			finalConditionString+=" "+sqlString+" '("+conditionArray[i]+")' and ";
		}
	}
	else
	{
		finalConditionString+=" "+sqlString+" '()' ";
	}
	
	  
	   if(finalConditionString!="")
		{
		   finalConditionString=finalConditionString.trim();
			  
		   if(finalConditionString.substr(finalConditionString.length-3,3)=="and")
			{
			   finalConditionString=finalConditionString.substr(0,finalConditionString.length-3);
			}
		}
	
	
	finalConditionString="("+finalConditionString+")";
//	alert(finalConditionString);
	return finalConditionString;
}

//翻页 改变页面数据量 查询 公用方法
function reloadMachineInfoList(direction,amount)
{
	
	var pagecounter=document.getElementById("pagecounter").value;
	var totalpages=document.getElementById("totalpages").value;
	var pageamount=document.getElementById("pageamount").value;
	var keyword=document.getElementById("machinesearch").value;
	//var searchcondition=document.getElementById("searchcondition").value;
	var orderstring=document.getElementById("orderstring").value;
	var showall="0";

	if(!pagecounter||pagecounter==""||pagecounter==null){pagecounter=1;}
	if(!totalpages||totalpages==""||totalpages==null){totalpages=1;}
	if(!pageamount||pageamount==""||pageamount==null){pageamount=10;}
	if(!keyword||keyword==""||keyword==null){keyword="";}
	if(!searchcondition||searchcondition==""||searchcondition==null){searchcondition="";}
	if(!orderstring||orderstring==""||orderstring==null){orderstring="";}
	
	pagecounter=parseInt(pagecounter);
	totalpages=parseInt(totalpages);
	pageamount=parseInt(pageamount);
	
	//针对翻页请求做判断处理
	if(direction&&direction!=null&&direction!=undefined&&direction!="")
	{
		if(direction==1)
		{
			if(pagecounter>1){pagecounter=pagecounter-1;}
		}
		else if(direction==2)
		{
			if(pagecounter<totalpages){pagecounter=pagecounter+1}
		}
	}
	
	//针对改变页面数据量判断处理
	if(amount&&amount!=null&&amount!=undefined&&amount!="")
	{
	  if(amount=="ALL")
	  {showall="1";}
	  else
	  {
		  showall="0";
		  pageamount= parseInt(amount);
	  }
	  
	}
	
	//首次搜索时候，从第一页显示
	if((!direction||direction==null||direction==undefined||direction=="") &&(!amount||amount==null||amount==undefined||amount=="")) 
	{
		pagecounter=1;
	}
	
	//针对条件搜索做出判断
	var propertynumber=document.getElementById("checkby_propertynumber_checkbox");
	var machinelocation=document.getElementById("checkby_machineLocation_checkbox");
	var ipaddr=document.getElementById("checkby_ipaddr_checkbox");
	var machineusage=document.getElementById("checkby_machineUsage_checkbox");
	var department=document.getElementById("checkby_department_checkbox");
	var responsible=document.getElementById("checkby_responsible_checkbox");
	
	// 资产类型
	var checkby_machineType_checkbox_1=document.getElementById("checkby_machineType_checkbox_1");
	var checkby_machineType_checkbox_2=document.getElementById("checkby_machineType_checkbox_2");
	var checkby_machineType_checkbox_3=document.getElementById("checkby_machineType_checkbox_3");
	var checkby_machineType_checkbox_4=document.getElementById("checkby_machineType_checkbox_4");
	var checkby_machineType_checkbox_5=document.getElementById("checkby_machineType_checkbox_5");
	var checkby_machineType_checkbox_6=document.getElementById("checkby_machineType_checkbox_6");
	var checkby_machineType_checkbox_7=document.getElementById("checkby_machineType_checkbox_7");
	var checkby_machineType_checkbox_8=document.getElementById("checkby_machineType_checkbox_8");
	var conditionstr="";
	
	  //资产号
	  if(propertynumber&&propertynumber.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString(' machineinfo.PropertyNumber REGEXP ',keyword.trim())+"   or ";
	  }
	  
	  //ip
	  if(ipaddr&&ipaddr.checked)
	  {
		  conditionstr=conditionstr+"  "+buildConditionString('machineinfo.IPAdd REGEXP',keyword.trim())+"   or ";
	  }
	  
	  //用途
	  if(machineusage&&machineusage.checked)
	  {
		  conditionstr=conditionstr+"  "+buildConditionString('machineinfo.MachineUsage REGEXP',keyword.trim())+"   or ";
	  }
	  
	  //位置
	  if(machinelocation&&machinelocation.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString('machineinfo.MachineLocation REGEXP',keyword.trim())+"   or ";
	  }
	  
	  //部门
	  if(department&&department.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString('machineinfo.Department REGEXP',keyword.trim())+" or ";
	  }
	  
	  //责任人
	  if(responsible&&responsible.checked)
	  {
		  conditionstr=conditionstr+" "+buildConditionString('machineinfo.Responsible REGEXP',keyword.trim())+" or ";
	  }
	  
	   if(conditionstr!="")
		{
		   conditionstr=conditionstr.trim();
			  
			   if(conditionstr.substr(conditionstr.length-2,2)=="or")
				{
				   conditionstr=conditionstr.substr(0,conditionstr.length-2);
				}
			   
				  
			   if(conditionstr.substr(conditionstr.length-3,3)=="and")
				{
				   conditionstr=conditionstr.substr(0,conditionstr.length-3);
				}
			   
		  
		 }
	  
	  
	  
	  
	  var conditionstr1='';
	  
	  if(checkby_machineType_checkbox_1&&checkby_machineType_checkbox_1.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='1' or ";
	  }
	  
	  if(checkby_machineType_checkbox_2&&checkby_machineType_checkbox_2.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='2' or ";
	  }
	  
	  if(checkby_machineType_checkbox_3&&checkby_machineType_checkbox_3.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='3' or ";
	  }
	  
	  if(checkby_machineType_checkbox_4&&checkby_machineType_checkbox_4.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='4' or ";
	  }
	  if(checkby_machineType_checkbox_5&&checkby_machineType_checkbox_5.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='5' or ";
	  }
	  if(checkby_machineType_checkbox_6&&checkby_machineType_checkbox_6.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='6' or ";
	  }
	  if(checkby_machineType_checkbox_7&&checkby_machineType_checkbox_7.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='7' or ";
	  }
	  if(checkby_machineType_checkbox_8&&checkby_machineType_checkbox_8.checked)
	  {
		  conditionstr1=conditionstr1+" machineinfo.MachineType='8' or ";
	  }
	  
	   if(conditionstr1!="")
		{
		   conditionstr1=conditionstr1.trim();
			  
			   if(conditionstr1.substr(conditionstr1.length-2,2)=="or")
				{
				   conditionstr1=conditionstr1.substr(0,conditionstr1.length-2);
				}
			   
				  
			   if(conditionstr1.substr(conditionstr1.length-3,3)=="and")
				{
				   conditionstr1=conditionstr1.substr(0,conditionstr1.length-3);
				}
			   
		  
		 }
	   
	   if(conditionstr!='')
	   {
		   if(conditionstr1!='')
		   {
			   conditionstr=" and ( ("+conditionstr+")"+" and "+"("+conditionstr1+")) ";
		    }
		   else
			{
			   conditionstr=" and ( "+conditionstr+" ) ";
			 }
		   
	   }
	   else
	   {
		   if(conditionstr1!='')
		   {
			   conditionstr=" and ( "+conditionstr1+" ) ";
		    }
		   else
			{
			   conditionstr='';
			 }
		   
	   }

	loadMachineInfoList(keyword,pagecounter,pageamount,showall,conditionstr,orderstring);
}

//全选
function selectAll(obj)
{
	var target_table=document.getElementById("machineinfolist_table");
	var trArray=target_table.getElementsByTagName("tr");
	
	if(obj.checked)
	{
     for(i=1;i<trArray.length;i++)
    {
    	     if(! trArray[i].cells[1].childNodes[0].checked)
    	     {
    	    	 trArray[i].cells[1].childNodes[0].checked=true;
    	     }
     }
	}
	else
	{
	     for(i=1;i<trArray.length;i++)
	     {
	     	     if(trArray[i].cells[1].childNodes[0].checked)
	     	     {
	     	    	 trArray[i].cells[1].childNodes[0].checked=false;
	     	     }
	      }
	}
	 
}


//条件选中
function addCondition(obj,type)
{
	var searchcondition_div=document.getElementById(type);
	
	if(document.getElementById(obj.id))
	{
		//searchcondition_div.getElementById("")
	}
	if(obj.className=="")
	{
		  obj.className=="condition_selected";
    }
	else
	{
		obj.className=="";
	}
}


//设置排序按钮的样式
function setOrderByString(orderby_icon_id)
{
	var orderby_icon=document.getElementById(orderby_icon_id);
	var orderstring=document.getElementById("orderstring");
	var orderbuttonstyle=document.getElementById("orderbuttonstyle");
	
	var idArray;
	if(orderby_icon&&orderby_icon!=null&&orderby_icon!=undefined&&orderby_icon!="")
	{
		idArray=orderby_icon_id.split('_');
	
		if(orderby_icon.className=="glyphicon glyphicon-arrow-up")
		{
			orderby_icon.className="glyphicon glyphicon-arrow-down";
			orderstring.value=" order by convert( machineinfo."+idArray[1]+" using gbk) COLLATE gbk_chinese_ci DESC ";
		}
		else
		{
			orderby_icon.className="glyphicon glyphicon-arrow-up";
			orderstring.value=" order by convert( machineinfo."+idArray[1]+" using gbk) COLLATE gbk_chinese_ci  ASC ";
			
		}
		
		
		if(orderbuttonstyle&&orderbuttonstyle!=null&&orderbuttonstyle!=undefined)
		{
			orderbuttonstyle.value=orderby_icon_id+","+orderby_icon.className;
		}
	
	}
	reloadMachineInfoList(null,null);
}
