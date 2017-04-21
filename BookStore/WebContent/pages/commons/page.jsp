<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="page_nav">
	<a href="${requestScope.page.url}?pageNo=1">首页</a>
	<a href="${requestScope.page.url}?pageNo=${requestScope.page.pageNo-1}" class="preve">上一页</a>
	<c:choose>
		<c:when test="${requestScope.page.pageNoCount<=5}">
			<c:set var="begin" value="1" scope="page"></c:set>
			<c:set var="end" value="${requestScope.page.pageNoCount}" scope="page"></c:set>
		</c:when>
		
		<c:when test="${requestScope.page.pageNoCount>5}">
			<c:choose>
				<c:when test="${requestScope.page.pageNo < 3}">
					<c:set var="begin" value="1" scope="page"></c:set>
					<c:set var="end" value="5" scope="page"></c:set>
				</c:when>
				
				<c:when test="${requestScope.page.pageNo > requestScope.page.pageNoCount-2}">
					<c:set var="begin" value="${requestScope.page.pageNoCount-4}" scope="page"></c:set>
					<c:set var="end" value="${requestScope.page.pageNoCount}" scope="page"></c:set>
				</c:when>
				
				<c:otherwise>
					<c:set var="begin" value="${requestScope.page.pageNo-2}" scope="page"></c:set>
					<c:set var="end" value="${requestScope.page.pageNo+2}" scope="page"></c:set>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose>
	
	<c:forEach begin="${pageScope.begin}" end="${pageScope.end}" var="i">
		<c:if test="${requestScope.page.pageNo == i}">
			<a href="${requestScope.page.url}?pageNo=${i}"><span style="color:red">[${i}]</span></a>
		</c:if>
		<c:if test="${requestScope.page.pageNo != i}"> 
			<a href="${requestScope.page.url}?pageNo=${i}"><span>[${i}]</span></a>
		</c:if>
	</c:forEach>
			
	<a href="${requestScope.page.url}?pageNo=${requestScope.page.pageNo+1}" class="next">下一页</a>
	<a href="${requestScope.page.url}?pageNo=${requestScope.page.pageNoCount}">末页</a>
	共${requestScope.page.pageNoCount}页，${requestScope.page.countSize}条记录 到第<input name="pageNo" id="pn_input" value="${param.pageNo}" />页
	<input type="button" value="确定" id="pageButton">
</div>