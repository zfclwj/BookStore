<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@ include file="/pages/commons/head.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@ include file="/pages/commons/order.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>状态</td>
				
			</tr>
				
			<c:choose>
				<c:when test="${! empty requestScope.orders}">
					<c:forEach items="${requestScope.orders}" var="order">
						<tr>
							<td>${order.createTime}</td>
							<td>${order.totalPrice}</td>
							<td><a href="getOrdersInfo.serverOrderServlet?orderId=${order.orderId}">查看详情</a></td>
							<td>
								<c:choose>
									<c:when test="${order.status==0}"><a href="sendGoods.serverOrderServlet?orderId=${order.orderId}">点击发货</a></c:when>
									<c:when test="${order.status==1}">已发货</c:when>
									<c:when test="${order.status==2}">已签收</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">暂时没有订单!</td>
					</tr>
				</c:otherwise>
			</c:choose>	
		</table>
	</div>
	
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>