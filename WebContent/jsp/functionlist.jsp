<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
response.setBufferSize(Integer.MAX_VALUE-1) ;
%>

 	<c:forEach items="${FunctionList}" var="fl"  varStatus="status">
 	
 	<a class="list-group-item" href="${fl.functionURL}<%=request.getParameter("userid")%>" target="content" style="white-space:nowrap;" id="${fl.htmlid }" >${fl.functionIcon }${fl.functionName }</a>

  </c:forEach>