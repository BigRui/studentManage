<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	#failDiv {
	    color: red;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="failDiv">
		${loginFail }
	</div>
	<form action="/studentManage/user/login" method="post">
		<label for="userName">
			用户名：
			<input type="text" name="userName" id="userName" value="${requestScope.userName }"/></label>
		</br>
		<label for="password">
			密码：
			<input type="password" name="password" id="password" value="${password }"/>
			</label>
		</br>
		记住用户名和密码：</br>
		<input type="checkbox" name="remenberMe" id="remenberMe" /></br>
		<input type="submit" value="提交" />
		<input type="reset" value="重置" />
	</form>
</body>
</html>