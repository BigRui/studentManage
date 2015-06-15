<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mtg" tagdir="/WEB-INF/tags/mtg" %>

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
<script type="text/javascript">
	window.onload=function(){
			document.getElementById("sbm").onclick = function(){
				document.getElementById('currentPage').value = 1;
			};
			
	}
</script>
</head>
<body>
<%@ include file="/WEB-INF/page/common/header.jsp"%>
	<div>
		<form action="${ctx }/student/student-list" method="get" id="shearchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage }"/>
			<input type="hidden" id="numPerPage" name="numPerPage" value="${page.numPerPage }"/>
			学生姓名：<input type="text" name="studentName" value="${param.studentName }"/>
			<input type="submit" value="检索" id="sbm"/>
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
	<c:forEach items="${requestScope.page.list }" var="stu" varStatus="status">
		<tr class="${status.index % 2 == 0 ? 'odd' : ''}">
			<td>${stu.id }</td>
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
<hr/>
<!-- 要分页了 -->
	<mtg:pg totalPageNum="${page.totalPageNum }" numPerPage="${page.numPerPage }" currentPage="${page.currentPage }" />
<!-- 分页结束了 -->
<hr/>
<br/>
<a href="${ctx }/student/student-add">添加学生信息</a>
</body>
</html>