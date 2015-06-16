<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div>
	欢迎：${sessionScope.user.realName } &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${ctx }/user/logout">退出登陆</a>
	<hr />
</div>