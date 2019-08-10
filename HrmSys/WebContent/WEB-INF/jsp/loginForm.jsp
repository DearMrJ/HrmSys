<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
		
		<script src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jsencrypt.min.js" type="text/javascript" ></script>
		<script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript" ></script>
		<script>
			$(function (){//文档载入完毕就执行，实现记住我的最后一步。即回显cookie中的信息 
				var cookieUser = $.cookie('username');
				var cookiePwd = $.cookie('password');
				$("#username").val(cookieUser);
				if(cookiePwd != null&&cookiePwd !=""){//判断上一次是否是记住密码，是则继续保持记住密码
					$("#checkbox1").prop("checked",true);
					$("#password").val(cookiePwd);
				}
			});
		</script>
		<script src="${pageContext.request.contextPath}/js/login.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="head-imgline"><!-- 熊猫头 -->
			<div class="head-imgbox">
				<img src="${pageContext.request.contextPath}/img/head.png" />
			</div>
		</div>
		<div class="login-box">
			<div class="login-content">
				<form id="form" action="login" method="post">
				   <h2>login</h2>
				   <p><label style="margin-left: 25px; line-height: 30px;">用户名</label><input class="user" type="text" name="loginname" id="username" placeholder="-username-" style="border-radius:10px;font-size: 15px;"/></p>
				   <p><label style="margin-left: 25px; line-height: 30px;">密&nbsp;&nbsp;&nbsp;码</label><input class="pwd" type="password" name="password" id="password" placeholder="-password-" style="border-radius:10px;font-size: 15px;"/></p>
				   <p><label style="margin-left: 25px;line-height: 30px;">记住我&nbsp;</label><input type="checkbox" name="checkbox" id="checkbox1" value="checked" style="border-radius:10px;"/></p>
				   <p><input class="safebtn" type="button" name="btn" id="btn" value="安全登录" style="border-radius:10px;color: #ffffff;font-size: 15px" /></p>
			   </form>
			</div>
			<div class="footer">
				<p align="center" style="color: #CCCCCC ;" ><a href="register">&nbsp;&nbsp;&nbsp;&nbsp;注册</a>&nbsp;|&nbsp;<a href="">忘记密码</a></p>
			</div>
		</div>
	</body>
</html>
