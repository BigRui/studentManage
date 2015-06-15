<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="start" type="Integer" rtexprvalue="true" required="true" %>
<%@ attribute name="end" type="Integer" rtexprvalue="true" required="true" %>

<%@ variable name-given="current" scope="NESTED" variable-class="Integer"%>

<c:forEach begin="${start }" end="${end }" step="1" var="v">
	<c:set var="current" value="${v }" />
	<jsp:doBody />
</c:forEach>