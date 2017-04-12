<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
response.setBufferSize(Integer.MAX_VALUE-1) ;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
.clear
{
   margin:0 0 0 0;
   padding:0 0 0 0;
   celling:0 0 0 0;
}
</style>
</head>
<body>


<input id="pagecounter" value="${pagecounter}" style="display:none;" />
<input id="totalpages" value="${totalpages}" style="display:none;" />
<input id="pageamount" value="${pageamount}" style="display:none;" />
<input id="keyword" value="" style="display:none;" />

<table  class="table table-hover" id="machineinfolist_table" >

<thead>
<tr>
<th style="display:none;">机器序号</th>
<th width="1px" style="display:none;"><input type="checkbox"  onclick="selectAll(this);"/></th>
<th width="48px">序号</th>
<!-- <th style="with-space:nowrap;">资产号</th> -->
<th style="with-space:nowrap;width:80px;">
<div class="btn-group btn-group-xs" role="group" >
<button class="btn btn-default" onclick="setOrderByString('orderby_MachineLocation_icon');">机器位置<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"  id="orderby_MachineLocation_icon" ></span></button>
</div>
</th>
<th style="width:105px;">IP地址</th>
<th > 所属课题</th>
<th  width="80px">
<div class="btn-group btn-group-xs" role="group" >
<button class="btn btn-default" onclick="setOrderByString('orderby_Department_icon');">部门<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"  id="orderby_Department_icon" ></span></button>
</div>
</th>
<th style="width:48px;">
<div class="btn-group btn-group-xs" role="group" >
<button class="btn btn-default" onclick="setOrderByString('orderby_Responsible_icon');">责任人<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"  id="orderby_Responsible_icon" ></span></button>
</div>
</th>
<th width="125px;"><span class="glyphicon glyphicon-wrench" aria-hidden="true" ></span> 管理</th>
<th style="color:red;width:150px;"><span class="glyphicon glyphicon-warning-sign" aria-hidden="true" ></span>  维护</th>
</tr>
</thead>

<tbody>
 	<c:forEach items="${MachineInfoList}" var="mi"  varStatus="status">
 	  <tr id="tr_${mi.id}" >
 	  <td name="name_machineid" style="display:none;"><c:out value="${mi.id}"></c:out></td>
 	  <td style="display:none;"><input type="checkbox" /></td>
 	  <td>${mi.displayNumber}</td>
 	  <!-- 
      <td><a href="#" onclick="machinedetail(${mi.id});"><c:out value="${mi.propertyNumber}"></c:out></a></td>
       -->	 
     <td><xmp class="clear"><c:out value="${mi.machineLocation}"></c:out></xmp></td>
      <td><a href="#" onclick="machinedetail(${mi.id});"><c:out value="${mi.ipAdd}"></c:out></a></td>
      <td><c:out value="${mi.project}"></c:out></td>
      <td><c:out value="${mi.department}"></c:out></td>
      <td><c:out value="${mi.responsible}"></c:out></td>
      <td>
       <div class="btn-group btn-group-xs" >
          <button class="btn btn-default" style="display:inline-block;" onclick="machinedetail(${mi.id});">详情</button>
          <button class="btn btn-default"  style="display:inline-block;" onClick="deletemachine(${mi.id});">删除</button>
           <c:choose> 
          <c:when test="${mi.machineType==2}">
      		 <button class="btn btn-default" style="display:inline-block;" onClick="wordexport(${mi.id});" >输出</button> 
          </c:when>
            <c:otherwise>   <button class="btn btn-default" style="display:inline-block;" onClick="wordexport(${mi.id});" disabled="disabled">输出</button> </c:otherwise>  
            </c:choose> 
          </div>
      </td>

      <td>
      <input id="checkStateA_${mi.id}" type="hidden"  value="${mi.checkStateA}"/>
      <input id="checkStateB_${mi.id}" type="hidden"  value="${mi.checkStateB}"/>
      <c:choose>  
      <c:when test="${mi.machineType==2}">
            <div class="btn-group btn-group-xs" >
          <button style="display:inline-block;" class="btn btn-default"  onClick="chekinfoa(${mi.id});" id="button_CheckA_${mi.id}">检查(A)</button>
          
          <button style="display:inline-block;" class="btn btn-default"  onClick="chekinfob(${mi.id});"  id="button_CheckB_${mi.id}">检查(B)</button>
           
     </div>
     
          <c:choose>  
         <c:when test="${isadmin==true}">
           <div class="btn-group btn-group-xs" >
          <button title="发送邮件:<c:out value="${mi.responsibleEmail}"></c:out>" class="btn btn-default"  style="display:inline-block;" onClick="sendEmailById(${mi.id});"><span class="glyphicon glyphicon-envelope" aria-hidden="true" ></span></button>
          </div>
           </c:when>
                <c:otherwise>
                </c:otherwise>  
          </c:choose>  
     
     
     </c:when>
     <c:otherwise>(请个人注意维护)</c:otherwise>  
     </c:choose>  
      </td>
      <td style="display:none;"><c:out value="${mi.machineType}"></c:out></td>
 	  </tr>
 	</c:forEach>
 	</tbody>
</table>

<div id="pager_div" style="float:right;margin-right:20px;">

<p style="display:inline-block;"><a id="pagecounter_a">${pagecounter}</a>/<a id="totalamount_a">${totalpages}</a> 共(${totalamount})条</a>
<select id="pageamount_select"   onchange="reloadMachineInfoList(null,this.options[this.options.selectedIndex].value);">
<option value="10">10</option>
<option value="20">20</option>
<option value="50">50</option>
<option value="100">100</option>
</select>
     <div class="btn-group btn-group-xs" role="group" style="margin-left:5px;" >
      <button class="btn btn-default"  id="prepage" style="display:inline-block; " onclick="reloadMachineInfoList(1,null)" >上一页</button>
       <button class="btn btn-default"  id="nextpage" style="display:inline-block;" onclick="reloadMachineInfoList(2,null)">下一页</button>
     </div>
     <div style="width:100%;height:30px;display:block"></div>
</div>

</body>
</html>




