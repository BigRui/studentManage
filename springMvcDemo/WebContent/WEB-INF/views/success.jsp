<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交成功</title>
</head>
<body>
	<h1>这是success页面</h1>
	name值：${requestScope.name }
	
	userId:${userId } password:${password } name:${name }
	<br/>
	<a href="${pageContext.request.contextPath }/user/toIndex">返回index页面</a>
</body>
</html>