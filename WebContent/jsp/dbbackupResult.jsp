<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>dbbackup</title>
</head>
<body>
	backup
	<table>
		<tr>
			<td>用户名称</td>
			<td>${userInfo.trueName }</td>
		</tr>
		<tr>
			<td>用户邮箱</td>
			<td>${userInfo.cstnetId }</td>
		</tr>
		<tr>
			<td>用户umt id</td>
			<td>${userInfo.umtId }</td>
		</tr>
		<tr>
			<td>用户类型</td>
			<td>${userInfo.type} </td>
		</tr>
		<tr>
			<td>用户登录类型</td>
			<td>${userInfo.passwordType }</td>
		</tr>
		<tr>
			<td>token</td>
			<td>${token}</td>
		</tr>
		<tr>
			<td>refresh token</td>
			<td>${refreshToken }</td>
		</tr>
	
	</table>

</body>
</html>