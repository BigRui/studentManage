<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<style type="text/css">
	td, th{
		border:1px solid red;
	}
	.young {
    background-color: yellow;
}
	
</style>
</head>
<body>
	<h1>学生信息列表，主要演示jstl</h1>
		
	<table>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>是否成年</th>
			<th>性别</th>
			<th>帅锅、美铝</th>
			<th>生日时辰</th>
			<th>月薪</th>
			<th>货币</th>
		</tr>
	<c:forEach items="${studentList }" var="stu" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${stu.name }</td>
			<td>${stu.age }</td>
			<td class="${stu.age < 17 ? 'young':''}">${stu.age < 17 ? '未成年':'成年'}</td>
			<td>
				<c:if test="${stu.sex eq 'm' }">
					帅锅
				</c:if>
			</td>
			<td>
				<c:choose>
					<c:when test="${stu.sex eq 'm' }">帅锅</c:when>
					<c:when test="${stu.sex == 'f'}">美铝</c:when>
					<c:otherwise>为知</c:otherwise>
				</c:choose>
			</td>
			<td>
				<fmt:formatDate value="${stu.birthday }" pattern="yyyy/MM-dd HH:mm:ss"/>
			</td>
			<td>
				<fmt:formatNumber value="${stu.salary }" pattern="##,#00.0#"></fmt:formatNumber>
			</td>
			<td>
				<fmt:formatNumber value="${stu.salary }" type="percent"></fmt:formatNumber>
				<fmt:formatNumber value="${stu.salary }" type="currency"></fmt:formatNumber>
			</td>
		</tr>
	</c:forEach>
	<hr/>
</table>
	foreach的非常不常用写法
	<c:forEach begin="0" end="10" step="2" var="it">
		${it }<br/>
	</c:forEach>
	<hr/>
	forea遍历map:<br/>
	<c:forEach items="${map }" var="entry">
		${entry.key } == ${entry.value }<br/>
	</c:forEach>
	
	<hr/>
	<%--
		pageContext.setAttribute("strs", "asdf,dd,saaadf,affsdf");
	--%>
	<c:set var="strs" scope="page" value="sdaf,dd,dfd"></c:set>
	
	forTokens演示，不常用：<br/>
	<c:forTokens items="${strs }" delims="," var="it">
		${it }<br/>
	
	</c:forTokens>
	c:remove删除掉某个属性
	<c:remove var="strs" scope="page"/>
	${strs }
	c:out用途是可以识别html关键字付:<br/>
	<c:out value="${htmlStr }" escapeXml="true"></c:out>
	
	c:url可以给url后边加上jsessionid，对应客户禁用cookie<br/>
	<c:url value="sdfdsaf/sadf">
		<c:param name="name">zhangsan</c:param>
	</c:url>
	
	c:import演示：<hr/>
	<c:import url="http://www.baidu.com"></c:import>
</body>
</html>