<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<%@ include file="/pages/commons/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单详情</span>
			<%@ include file="/pages/commons/login_success_common.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>书名</td>
				<td>数量</td>
				<td>单价</td>
				<td>总价</td>
			</tr>	
			<c:choose>
				<c:when test="${! empty requestScope.orderItems}">
					<c:forEach items="${requestScope.orderItems}" var="orderItem">
						<tr>
							<td>${orderItem.name}</td>
							<td>${orderItem.count}</td>
							<td>${orderItem.price}</td>
							<td>${orderItem.totalPrice}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">该订单没有详细信息!</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>