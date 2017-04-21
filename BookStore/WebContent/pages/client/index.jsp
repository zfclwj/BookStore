<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/pages/commons/head.jsp" %>
<script type="text/javascript">
	$(function(){
		var userPower="${sessionScope.userPrower}";
		if(userPower.length!=0){
			if(userPower=="common"){
				$("#backManger").hide();
			}else{
				$("#backManger").show();
			}
		}
		
		var maxPage=${requestScope.page.pageNoCount};
		var pageNo=${requestScope.page.pageNo};
		
		if(maxPage==pageNo){
			$(".next").hide();
		}else{
			$(".next").show();
		}
		
		if(pageNo==1){
			$(".preve").hide();
		}else{
			$(".preve").show();
		}
		
		$("#pageButton").click(function(){
			var pageNo = $("#pn_input").val();
			if(maxPage<pageNo){
				pageNo=maxPage;
			}
			location.href="pageByPrice.clientBookServlet?pageNo="+pageNo;
		});
		
		$("a").click(function(){
			var hrefOld=$(this).attr("href");
			if(hrefOld.indexOf("?pageNo=")>0){
				var minPrice=$("#min").val();
				var maxPrice=$("#max").val();
				if(minPrice!=""){
					hrefOld=hrefOld+"&minPrice="+minPrice;
				}
				if(maxPrice!=""){
					hrefOld=hrefOld+"&maxPrice="+maxPrice;
				}
				$(this).attr("href",hrefOld);
			}
		});
		
		$(".addToCart").click(function(){
			var name="${sessionScope.name}";
			if(name.length==0){
				location.href="${pageContext.request.contextPath}/pages/user/login.jsp";
				return false;
			}else{
				var url="${pageContext.request.contextPath}/addCartItems.CartServlet";
				var args={
					"id":$(this).next().val(),
					"time":new Date()
				};
				$.getJSON(url,args,function(data){
					$("#ajaxSpan").html("您的购物车中有"+data.totalCount+"件商品");
					$("#ajaxDiv").html("您刚刚将<span id='nameSpan' style='color: red'>"+data.lastName+"</span>加入到了购物车中");
					alert("添加成功!")
				});
			}
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<c:choose>
				<c:when test="${! empty sessionScope.name}">
					<div>
						<span>欢迎<span class="um_span">${sessionScope.name}</span>光临尚硅谷书城</span>
						<a href="pages/cart/cart.jsp">购物车</a>
						<a href="pages/manager/manager.jsp" id="backManger">后台管理</a>
						<a href="logout.User">注销</a>&nbsp;&nbsp;
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<a href="pages/user/login.jsp">登录</a> | 
						<a href="pages/user/regist.jsp">注册</a>&nbsp;&nbsp;
						<a href="pages/user/login.jsp">购物车</a>
						<a href="pages/user/login.jsp">后台管理</a>
					</div>
				</c:otherwise>
			</c:choose>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="pageByPrice.clientBookServlet" method="post"  id="SearchForm">
					价格：<input id="min" type="text" name="minPrice" value="${param.minPrice}"> 元 - 
						<input id="max" type="text" name="maxPrice" value="${param.maxPrice}"> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<c:choose>
				<c:when test="${! empty sessionScope.cart}">
					<div style="text-align: center">
						<span id="ajaxSpan">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
						<div id="ajaxDiv">
							您刚刚将<span id="nameSpan" style="color: red">${sessionScope.lastName}</span>加入到了购物车中
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div style="text-align: center">
						<span id="ajaxSpan"></span>
						<div id="ajaxDiv"><br/></div>
					</div>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${!empty requestScope.page.list }">
					<c:forEach items="${requestScope.page.list }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="${book.imgPath}" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.name}</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author}</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price}</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales}</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock}</span>
								</div>
								<div class="book_add">
									<button class="addToCart">加入购物车</button>
									<input type="hidden" value="${book.id}"/>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">数据库没有数据,请添加!</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</div>
		<%@ include file="/pages/commons/page.jsp" %>
	</div>
	
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>