<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/pages/commons/head.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var tr = $(this).parent().parent();
			var name=tr.find("td:eq(0)").html();
			var id=$(this).next().val();
			var flag= confirm("你确认要删除《"+name+"》的信息吗?");
			if(flag){
				var url="${pageContext.request.contextPath}/deleteItems.CartServlet";
				var args={
					"id":id,
					"time":new Date()
				};
				$.get(url,args,function(data){
					if(data.flag){
						tr.remove();
						if(data.totalCount!= 0){
							$("#ajaxTotalCount").html(data.totalCount);
							$("#ajaxTotalPrice").html(data.totalPrice);
						}else{
							$("#ajaxTotalPrice").parent().parent().hide();
							$("<tr><td colspan='5'><a href='index.jsp'>购物车还没有商品,快去购物吧^_^!</a></td></tr>").appendTo("table");
						}
					}
				},"json");
			}
			return false;
		});
		
		$(".clear").click(function(){
			var flag = confirm("你确认要清空购物车吗?");
			if(flag){
				var url="${pageContext.request.contextPath}/clearItems.CartServlet";
				var args={
					"time":new Date()
				};
				$.get(url,args,function(data){
					if(data){
						$("table").find("tr:gt(0)").hide();
						$(".cart_info").hide();
						$("<tr><td colspan='5'><a href='index.jsp'>购物车还没有商品,快去购物吧^_^!</a></td></tr>").appendTo("table");
					}
				},"text");
			}
			return false;
		});
		
		$(":input[name='count']").change(function(){
			var count=$(this).val();
			var id=$(this).parent().parent().find("a").next().val();
			var url="${pageContext.request.contextPath}/updateItems.CartServlet";
			var args={
				"id":id,
				"count":count,
				"time":new Date()
			};
			$.getJSON(url,args,function(data){
				$("#ajaxTotalCount").html(data.totalCount);
				$("#ajaxTotalPrice").html(data.totalPrice);
				$("#totalPrice_"+id).html(data.price);
			});
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/pages/commons/login_success_common.jsp" %>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>		
			<c:choose>
				<c:when test="${! empty sessionScope.cart}">
					<c:forEach items="${sessionScope.cart.cart}" var="item">
						<tr>
							<td>${item.value.name}</td>
							<td><input type="text" name="count" value="${item.value.count}"/></td>
							<td>${item.value.price}</td>
							<td id="totalPrice_${item.value.id}">${item.value.totalPrice}</td>
							<td>
								<a href="deleteItems.CartServlet?id=${item.value.id}" class="delete">删除</a>
								<input type="hidden" value="${item.value.id}">
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5"><a href="index.jsp">购物车还没有商品,快去购物吧^_^!</a></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		
		<c:if test="${! empty sessionScope.cart}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count" id="ajaxTotalCount">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price" id="ajaxTotalPrice">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="${pageContext.request.contextPath}/clearItems.CartServlet" class="clear">清空购物车</a></span>
				<span class="cart_span"><a href="saveOrder.orderServlet">去结账</a></span>
			</div>
		</c:if>
	</div>
	
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>