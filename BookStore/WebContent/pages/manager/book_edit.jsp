<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%@ include file="/pages/commons/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@ include file="/pages/commons/order.jsp"%>
		</div>
		
		<div id="main">
			<c:choose>
				<c:when test="${empty requestScope.book}">
					<c:set var="method" value="save.Book" scope="page"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="method" value="update.Book" scope="page"></c:set>
				</c:otherwise>
			</c:choose>
			<form action="${pageScope.method}" method="post">
				<input type="hidden" name="id" value="${requestScope.book.id}"/>
				<input type="hidden" name="pageNo" value="${param.pageNo}"/>
				<table>
					<tr>
						<th>名称</th>
						<th>价格</th>
						<th>作者</th>
						<th>销量</th>
						<th>库存</th>
						<th colspan="2">操作</th>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}" /></td>
						<td><input name="author" type="text" value="${requestScope.book.author}" /></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}" /></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}" /></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
		</div>
		<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>