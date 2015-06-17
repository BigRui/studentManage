<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加学生</title>
</head>
<body>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<h1>${requestScope['update-operate'] ? '更新':'添加'}学生信息</h1>
<form action="${ctx }/student/student-add" method="post">
		<input type="hidden" name="id" value="${student.id }"/>
		<label> 学生姓名：<input type="text" name="name" ${requestScope['update-operate'] ? 'readonly="readonly"': ''} id="name" value="${student.name }"/></label><br/>
		<label> 学生年龄：<input type="text" name="age" id="age" value="${student.age }"/></label><br/>
		<label> 学生email：<input type="text" name="email" id="email" value="${student.email }"/></label><br/>
		<input type="submit" value="提交" />
		<input type="reset" value="重置" />
	</form>
	
	<br/>
	<a href="${ctx }/student/student-list">学生一览<a/>
</body>

</html>