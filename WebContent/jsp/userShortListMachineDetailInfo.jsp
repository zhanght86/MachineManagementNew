<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
response.setBufferSize(Integer.MAX_VALUE-1) ;
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%>


<select  onchange="autoSetResponsibleInfoMachineDetailInfo(this.options[this.options.selectedIndex].value);" style="height:25px;">
     <option value="-1">( 请选择 )</option>
 	<c:forEach items="${UserList}" var="user"  varStatus="status">
      <option value="${user.id}">${user.displayID} . ${user.name }( ${user.department})</option>
 	</c:forEach>
</select>