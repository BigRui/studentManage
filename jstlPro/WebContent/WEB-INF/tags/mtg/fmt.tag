<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="value" type="Double" required="true" rtexprvalue="true" %>

<jsp:doBody />
<fmt:formatNumber value="${value }" pattern="##,##0.00"></fmt:formatNumber>
<%
	System.out.println("我就在tagfiles里面写脚本");
%>