<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生一览</title>
<style type="text/css">
	.odd{background-color: AliceBlue;}
	td,th {
    border: 1px solid green;
	}
	table {
	    border-collapse: collapse;
	}
	
</style>
</head>
<body>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
	
	<div>
		<form action="${ctx }/student/student-list" method="post">
			学生姓名：<input type="text" name="studentName" value="${param.studentName }" />
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
		<th>删除</th>
	</tr>
	<c:forEach items="${requestScope.students }" var="stu" varStatus="status">
		<tr class="${status.index % 2 == 0 ? 'odd' : ''}">
			<td>${status.count }</td>
			<td>${stu.name }</td>
			<td>${stu.age }</td>
			<td>${stu.email}</td>
			<td>
				<form action="${ctx }/student/student-delete" method="post" onsubmit="return confirm('确定删除吗？');">
					<input type="hidden" name="id" value="${stu.id }"/>
					<input type="submit" value="删除"/>
				</form>
			</td>
		</tr>
	</c:forEach>
	
</table>
<hr />
<br />
<a href="${ctx }/student/student-add">添加学生信息</a>
</body>
</html>