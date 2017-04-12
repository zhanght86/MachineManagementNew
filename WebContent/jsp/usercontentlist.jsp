<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setBufferSize(Integer.MAX_VALUE-1) ;
%>

 <link href="/MachineManagement/bootstrap-3.3.4-dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<table  class="table table-hover" id="userlist_table">
<thead>
<tr>
<th  style="display:none;"><input type="checkbox"  onclick="selectAll(this);"/></th>
<th>序号</th>
<th style="with-space:nowrap;">用户姓名</th>
<th>登录名</th>
<th>邮件</th>
<th>部门</th>
<th style="color:red;">管理</th>
<th>
<tbody>
 	<c:forEach items="${UserList}" var="user"  varStatus="status">
 	  <tr id="tr_${user.id}" >
 	  <td style="display:none;"><input type="checkbox" /></td>
 	  <td>${user.displayID}</td>
      <td><a href="#" onclick="userteail(${user.id})"><c:out value="${user.name}"></c:out></a></td>
      <td><c:out value="${user.username}"></c:out></td>
      <td><c:out value="${user.email}"></c:out></td>
      <td><c:out value="${user.department}"></c:out></td>
      <td>
      <div class="btn-group btn-group-xs" >
      <button title="删除用户" class="btn btn-default"  style="display:inline-block;" onClick="deleteUser(${user.id});"><span class="glyphicon glyphicon-remove" aria-hidden="true" ></span></button>
      </div>
      </td>
      </tr>
 	</c:forEach>
 	</tbody>
</table>
<div >


</div>

