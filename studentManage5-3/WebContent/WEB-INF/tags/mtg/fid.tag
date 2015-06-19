<%@ tag language="java" pageEncoding="UTF-8" import="java.util.*" body-content="empty"%>

<%
	String formId = UUID.randomUUID().toString();
	HashSet<String> formIdSet = (HashSet<String>)session.getAttribute("formIdSet");
	if(formIdSet == null) {
		formIdSet = new HashSet<String>();
		session.setAttribute("formIdSet", formIdSet);
	}
	formIdSet.add(formId);
	jspContext.setAttribute("formId", formId);
%>
<input type="hidden" name="formId" value="${formId }"/>
<jsp:doBody></jsp:doBody>