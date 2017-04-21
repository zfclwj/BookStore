<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
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
		<span class="wel_word">结算</span>
		<%@ include file="/pages/commons/login_success_common.jsp" %>
	</div>
	
	<div id="main">
		<h1>你的订单已结算，订单号为<span style="color: red">${sessionScope.orderId}</span></h1>
	</div>
	
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>