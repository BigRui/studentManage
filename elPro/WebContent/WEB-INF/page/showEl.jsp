<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>演示el表达式</title>
</head>
<body>
	<h1>演示el表达式</h1>
	\${true }:${true }<br/>
	\${false }:${false }<br/>
	<hr/>
	输出数值：<br/>
	\${10 }:${10 }<br/>
	\${3.14 }:${3.14 }<br/>
	\${0.5E2 }:${0.5E2 }<br/>
	输出字符串：<br/>
	\${'hello' }:${'hello' }<br/>
	<hr/>
	输出空：<br/>
	\${sessionScope.user }:${sessionScope.user }<br/>
	\${null }:${null }<br/>
	<hr/>
	算数运算：                            <br/>
	\${ 1 + 1 }:${1 + 1 }                 <br/>
	\${'1' + '1' }:${'1' + '1'}           <br/>
	\${'1' }\${'1'}:${'1' }${'1'}			<br/>
	\${10 / 2}:${10 / 2}           <br/>
	\${10 div 2}:${10 div 2}           <br/>
	\${10 /0 }:${10 / 0}           <br/>
	\${10 % 3 }:${10 % 3}           <br/>
	\${10 mod 3 }:${10 mod 3}       <br/>
	\${ 9 % 2 == 0 ? '偶数': '奇数' }:${ 9 % 2 == 0 ? '偶数': '奇数' }   <br/>
	<hr/>
	关系运算符：<br/>
	\${ 5 == 4 } :${ 5 == 4 }<br/>
	\${ 5 eq 4 } :${ 5 eq 4 }<br/>
	<hr/>
	empty演示：<br/>
	\${empty null}:${empty null }<br/>
	\${empty '' }:${empty '' }<br/>
	<%
		String name = request.getParameter("name.hello");
		out.println(name);
	%>
	<br/>
	${param['name.hello'] }<br/>
	${param['user-name'] }<br/>
	从提交的表单中读取一组参数：<br/>
	${paramValues['user-name'][0] }<br/>
	${paramValues['user-name'][1] }<br/>
	${paramValues['user-name']['1'] }<br/>
	取得头信息：header<br/>
	Accept-Language:${header['Accept-Language'] }
	<hr/>
	pageContext演示：<br/>
	\${pageContext.request.contextPath }:${pageContext.request.contextPath }
	<hr/>
	cookie隐式对象的演示：<br/>
	\${cookie.JSESSIONID.value }:${cookie.JSESSIONID.value }<br/>
	initParam用法：没有用：<br/>
	\${initParam.servletContextInit }:${initParam.servletContextInit }<br/>
	<hr/>
	综合演示el表达式：
	
</body>
</html>