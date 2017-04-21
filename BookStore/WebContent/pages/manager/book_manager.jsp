<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@ include file="/pages/commons/head.jsp" %>
<script type="text/javascript">
	$(function(){
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
		
		$(".delete").click(function(){
			var name=$(this).parent().parent().find("td:eq(0)").text();
			var flag=confirm("你确定要删除《"+name+"》的信息吗?");
			if(flag){
				$(this).parent().parent().remove();
				var url="${pageContext.request.contextPath}/"+ $(this).attr("href");
				var args={
					"time":new Date
				} 
				$.post(url,args);
			}
			return false;
		});
		
		$("#pageButton").click(function(){
			var pageNo = $("#pn_input").val();
			if(maxPage<pageNo){
				pageNo=maxPage;
			}
			location.href="page.Book?pageNo="+pageNo;
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/pages/commons/order.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<th>名称</th>
				<th>价格</th>
				<th>作者</th>
				<th>销量</th>
				<th>库存</th>
				<th colspan="2">操作</th>
			</tr>		
			<c:choose>
				<c:when test="${!empty requestScope.page.list }">
					<c:forEach items="${requestScope.page.list }" var="book">
						<tr>
							<td>${book.name}</td>
							<td>${book.price}</td>
							<td>${book.author}</td>
							<td>${book.sales}</td>
							<td>${book.stock}</td>
							<td><a href="getBook.Book?id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
							<td><a href="delete.Book?id=${book.id}" class="delete">删除</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">数据库没有数据,请添加!</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageNo}">添加图书</a></td>
			</tr>	
		</table>
	</div>
	
	<%@ include file="/pages/commons/page.jsp" %>
	<br/><br/>
	<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>