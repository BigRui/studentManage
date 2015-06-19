<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="numPerPage" type="Integer" required="true"%>
<%@ attribute name="currentPage" type="Integer" required="true"%>
<%@ attribute name="totalPageNum" type="Integer" required="true"%>
<%@ attribute name="shearchFormId"  required="false"%>

<script type="text/javascript">
	var fms = document.getElementById("${empty shearchFormId ? 'shearchForm':shearchFormId}");
	fms = fms || document.forms[0];
	
	function gotoPage(i) {
		fms.currentPage.value = i;
		fms.submit();
	}
	function changeNumPerPage(i) {
		fms.numPerPage.value = i;
		fms.submit();
	}
</script>
	<select onchange="changeNumPerPage(this.value)">
		<option ${5  == numPerPage ? 'selected="selected"':'' } value="5">5</option>
		<option ${10 == numPerPage ? 'selected="selected"':'' } value="10">10</option>
		<option ${15 == numPerPage ? 'selected="selected"':'' } value="15">15</option>
		<option ${20 == numPerPage ? 'selected="selected"':'' } value="20">20</option>
		<option ${50 == numPerPage ? 'selected="selected"':'' } value="50">50</option>
	</select>
	<%--当一条记录都没有，也就是总共0页的时候，显示1页 --%>
	<c:if test="${totalPageNum == 0 }">1</c:if>
	<c:forEach begin="1" end="${totalPageNum }" step="1" var="v" varStatus="status">
		<c:choose>
			<c:when test="${v == currentPage }">
				${v }
			</c:when>
			<c:otherwise>
				<a href="#" onclick="gotoPage(${v})">${v }</a>
			</c:otherwise>
		</c:choose>
		<c:if test="${not status.last }">|</c:if>
	</c:forEach>
