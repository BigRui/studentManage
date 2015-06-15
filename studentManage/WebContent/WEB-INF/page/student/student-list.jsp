<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生一览</title>
<style type="text/css">
	.odd{background-color: yellow;}
</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	
	<div>
		<form action="${ctx }/student/student-list" method="get">
			学生姓名：<input type="text" name="studentName" />
			<input type="submit" value="检索" />
		</form>
	</div>
	
	<hr/>
	<table>
	<tr>
		<th>id</th>
		<th>学生姓名</th>
		<th>学生年龄</th>
		<th>学生email</th>
	</tr>
	<c:forEach items="${requestScope.students }" var="stu" varStatus="status">
		<tr class="${status.index % 2 == 0 ? 'odd' : ''}">
			<td>${stu.id }</td>
			<td>${stu.name }</td>
			<td>${stu.age }</td>
			<td>${stu.email}</td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>