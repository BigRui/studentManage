<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/common.css"/>
</head>
<body>
	<h1>欢迎访问</h1>
	<form action="${pageContext.request.contextPath }/user/toForm" method="post">
		用户名：<input name="name"/></br>
		<input type="submit" value="提交"/>
	</form>
	
	<a href="${pageContext.request.contextPath }/user/toRest/id/123/name/zhangsan/password/helloworld">提交一个name值</a>
	
	<h1>提交一个user</h1>
	<sp:form action="${pageContext.request.contextPath }/user/toUser" method="post" modelAttribute="user">
		用户名：<input name="name"/><sp:errors path="name"/><br/>
		生日时辰：<input name="birth"/><sp:errors path="birth"/></br>
		薪资：<input name="salary"/><sp:errors path="salary"/></br>
		老师名：<input name="teacher.name"/></br>
		老师地址的名：<input name="teacher.address.name"/></br>
		老师地址的邮编：<input name="teacher.address.postCode"/></br>
		<input type="submit" value="提交"/>
	</sp:form>
</body>
</html>