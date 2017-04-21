<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
			<span class="wel_word">我的订单</span>
			<%@ include file="/pages/commons/login_success_common.jsp" %>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>		
			<c:choose>
				<c:when test="${! empty requestScope.orders}">
					<c:forEach items="${requestScope.orders}" var="order">
						<tr>
							<td>${order.createTime}</td>
							<td>${order.totalPrice}</td>
							<td>
								<c:choose>
									<c:when test="${order.status==0}">未发货</c:when>
									<c:when test="${order.status==1}"><a href="recevieGoods.orderServlet?orderId=${order.orderId}">确认收货</a></c:when>
									<c:when test="${order.status==2}">交易完成</c:when>
								</c:choose>
							</td>
							<td><a href="getUserOrdersInfo.orderServlet?orderId=${order.orderId}">查看详情</a></td>
						</tr>		
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4"><a href="index.jsp">还没有订单,快去购物吧^_^!</a></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>