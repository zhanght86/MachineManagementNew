<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <link href="css/common.css" rel="stylesheet"  type="text/css" />-->
<script Language="JavaScript" src="/MachineManagement/js/prototype.js"></script> 
<title>MachineInfoList</title>

</head>
<style>
.commonTable {
	border-collapse: collapse;
	border: none;
	width: 100%;
	height:100%;
	border: solid #000 1px;
}
.commonTable tr th
{
 border: solid #000 1px;
 text-align:center;
 align:middle;
 background: #E3EFFF; 
 font-weight:bold;
 white-space:nowrap;
 
}
.commonTable tr{
	border: solid #000 1px;
}
.commonTable tr td{
 border: solid #000 1px;
 text-align:center;
 background: #fff; 
 align:middle;
}
</style>
<body onload="init();">

<input id="pagecounter" value='${pagecounter}' style="display:none;" />
<input id="totalamount" value='${totalamount}' style="display:none;" />
<input id="keyword" value='${keyword}' style="display:none;" />
<p>历史检查记录查询</p>



<table class="commonTable" >
<tr>


<td>

</td>

<td><input style="width:98%" type="text" id="keyword_machineid" value='${keyword_machineid}'></input></td>

<td><input style="width:98%" type="text" id="keyword_year" value='${keyword_year}'></input></td>

<td><a id='dosearch' href='javascript:search()'>搜索</a> <a id='doclear' href='javascript:clear_saerch()'>清除</a></td>
</tr>
<tr >
<th >检查表类型</th>
<th >机器序号</th>
<th style="with-space:nowrap;">检查表年份</th>
<th style="color:red;">检查历史记录</th>
</tr>
 	<c:forEach items="${checkInfoList}" var="ci" varStatus="var">
 	  <tr>
 	  <td><c:out value="${ci.type}"></c:out></td>
 	  <td><c:out value="${ci.id}"></c:out></td>
      <td><c:out value="${ci.year}"></c:out></td>
      <td>
          <button style="display:inline-block;" onclick="checkhistory('${ci.type}','${ci.id}','${ci.year}');">查看</button>
      </td>
 	  </tr>
 	</c:forEach>
</table>

<div>
<p style="display:inline-block;">${pagecounter}/${totalamount}</p>
<a id="prepage" href='/MachineManagement/GetCheckInfoHistoryList.do?&pagecounter=' target="content" style="display:inline-block;">上一页</a>
<a id="nextpage" href='/MachineManagement/GetCheckInfoHistoryList.do?&pagecounter=' target="content" style="display:inline-block;">下一页</a>
</div>
<script>

//页面载入，初始化函数
 function init()
 {
	 //设置页脚的上一页下一页的链接和pagecounter
	 pagersetting();
     
 }
 
//搜索
 function search()
 { 
	 var keyword_machineid=document.getElementById("keyword_machineid").value;
	 var keyword_year=document.getElementById("keyword_year").value;

	 
	 var dosearch=document.getElementById("dosearch");
     window.location.href="/MachineManagement/GetCheckInfoHistoryList.do?&pagecounter=1"+"&keyword_machineid="+keyword_machineid+"&keyword_year="+keyword_year;
 }
 

 
 //清除关键字
 function clear_saerch()
 { 

	 document.getElementById("keyword_machineid").value="";
	 document.getElementById("keyword_year").value="";

	 
	 var dosearch=document.getElementById("dosearch");
     window.location.href="/MachineManagement/GetCheckInfoHistoryList.do?&pagecounter=1&keyword_machineid=&keyword_year=";
 }
 
//设置页脚的上一页下一页的链接和pagecounter
 function pagersetting()
 {
	 var pagecounter=parseInt(document.getElementById("pagecounter").value);
	 var totalamount=parseInt(document.getElementById("totalamount").value);
	 
	 var nextpage=document.getElementById("nextpage");
	 var prepage=document.getElementById("prepage");
	 
	
	 var keyword_machineid=document.getElementById("keyword_machineid").value;
	 var keyword_year=document.getElementById("keyword_year").value;
	 
	 if(pagecounter+1>totalamount)
	 {
		 nextpage.readonly=true;
		 nextpage.href=prepage.href+pagecounter+"&keyword_machineid="+keyword_machineid+"&keyword_year="+keyword_year;
	 }
	 else
	 {
		 nextpage.readonly=false;
		 nextpage.href=nextpage.href+(pagecounter+1)+"&keyword_machineid="+keyword_machineid+"&keyword_year="+keyword_year;
	 }
	 
	 if(pagecounter-1<1)
	 {
		 prepage.readonly=true;
		 prepage.href=prepage.href+pagecounter+"&keyword_machineid="+keyword_machineid+"&keyword_year="+keyword_year;
	 }
	 else
	 {
		 prepage.readonly=false;
	     prepage.href=prepage.href+(pagecounter-1)+"&keyword_machineid="+keyword_machineid+"&keyword_year="+keyword_year;
	 }
	 
 }
 

 function checkhistory(type,id,year)
 {

	 if(type=="A")
	 {
		window.open('/MachineManagement/jsp/checkinfoahistory.jsp?&machineid='+id+"&year="+year,"检查记录(A)",'height=620,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 

	 }
	 else if(type=="B")
     {
		 
		window.open('/MachineManagement/jsp/checkinfobhistory.jsp?&machineid='+id+"&year="+year,"检查记录(B)",'height=460,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
			 
     }
 }
  


</script>
</body>
</html>