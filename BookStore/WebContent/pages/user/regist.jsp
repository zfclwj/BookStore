<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/pages/commons/head.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
<script type="text/javascript">
	$(function(){
		var pattStr=/^\w{5,16}$/;
		var pattEmail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		
		$("#username").change(function(){
			var name=$("#username").val();
			name=$.trim(name);
			if(!pattStr.test(name)){
				$(".errorMsg").html("用户名不符合规范!");
				alert("用户名只能是数字、字母、下划线的5-16组合!");
			}else{
				var url="${pageContext.request.contextPath}/checkUserExties.User";
				var args={
					"name":name,
					"time":new Date()
				};
				$.get(url,args,function(data){
					$(".errorMsg").html(data);
				},"text");
			}
		});
		
		$("#password").change(function(){
			var password=$("#password").val();
			password=$.trim(password);
			if(!pattStr.test(password)){
				$(".errorMsg").html("密码不符合规范!");
				alert("用户名只能是数字、字母、下划线的5-16组合!");
			}else{
				$(".errorMsg").html("");
			}
		});
		
		$("#repwd").change(function(){
			var repwd=$("#repwd").val();
			repwd=$.trim(repwd);
			var password=$("#password").val();
			password=$.trim(password);
			if(repwd!=password){
				$(".errorMsg").html("密码不一致!");
			}else{
				$(".errorMsg").html("");
			}
		});
		
		$("#email").change(function(){
			var email=$("#email").val();
			email=$.trim(email);
			if(!pattEmail.test(email)){
				$(".errorMsg").html("邮箱格式不正确!");
			}else{
				$(".errorMsg").html("");
			}
		});
		
		$("#code").change(function(){
			var code=$("#code").val();
			code=$.trim(code);
			if(code==""){
				$(".errorMsg").html("验证码不能为空!");
			}else{
				$(".errorMsg").html("");
			}
		});
		
		$("#codeImg").click(function(){
			$(this).attr("src","img.jpg?time=" + new Date);
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${requestScope.message}</span>
							</div>
							<div class="form">
								<form action="regist.User" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${param.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${param.email}" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 130px;" name="code" id="code" value="${param.code}"/>
									<img alt="" id="codeImg" src="img.jpg" style="float: right;  width: 100px; height:35px; margin-right: 50px;">						
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/commons/foot.jsp" %>
</body>
</html>