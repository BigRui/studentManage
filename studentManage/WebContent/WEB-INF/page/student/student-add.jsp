<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加学生</title>
</head>
<body>
<h1>添加学生信息</h1>
<form action="add" method="post">
		<label> 学生姓名：<input type="text" name="name" id="name" /></label></br>
		<label> 学生年龄：<input type="text" name="age" id="age" /></label></br>
		<label> 学生email：<input type="text" name="email" id="email" /></label></br>
		<input type="submit" value="提交" />
		<input type="reset" name="" id="" value="重置" />
	</form>
</body>
</html>