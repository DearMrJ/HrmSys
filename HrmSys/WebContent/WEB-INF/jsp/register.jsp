<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>注册</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsencrypt.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
		<style type="text/css">
			*{
				margin: 0;
	            padding: 0;
	            list-style: none;
			}
		    body{
		    	background: url(http://pic.netbian.com/uploads/allimg/180303/223949-15200879890985.jpg);
		    	background-repeat: no-repeat;
		    	background-size: cover;
		    }
			.register-box{
				position: absolute;
	            left: 50%;
	            top: 50%;
	            margin-left: 180px;
	            margin-top: -200px;
	            width: 360px;
	            height: 360px;
	            border-radius: 10px;
	            background: rgba(255,255,255,.2);
			} 
			.register-content{
				width: 360px;
				height: 300px;
			}
			h2{
				text-align: center;
			}
			p{
				float: right;
				font-size: 14px;
				text-align: center;
			}
			input[type='text']{	    
	          margin-right: 79px;
	          width: 170px;
	          height: 30px;
	          outline: none;
            }
            input[type='password']{
	          margin-right: 79px;
	          width: 170px;
	          height: 30px;
	          outline: none;
            }
            input[type='button']{
             margin-right: 79px;
             width: 204px;
	         height: 30px;	   
            }
            input::-webkit-input-placeholder {
	         /* placeholder颜色  */
	         color: #aab2bd;
	         /* placeholder字体大小  */
	         font-size: 15px;
	         /* placeholder位置  */
	         text-align: left; 
	         }
            .tip{
            	margin: 0 auto;
            	width: 200px;
            	height: 30px; 
                clear: both;
                color: red;
            	font-size: 12px;
            	line-height: 30px;
            	
            }
            .footer{
				margin-top: 0px;
				width: 360px;
				height: 30px;
			}
			.footer p{
				clear: both;
				text-align: center;
				
			}
            a{
				text-decoration: none;
				color: #FFFFFF;
			}
			/**输入框图标**/
			.user{
				background-image: url(icon/user.ico);/*设置小图标*/
				background-size: 17px 17px;/*小图标的大小*/
				background-position: 5px 5px;/*小图标在input的位置*/
				background-repeat: no-repeat;/*背景小图标不重复*/
				border:1px solid #ddd;
				padding-left: 30px;
			}
			.pwd{
				background-image: url(icon/pwd2.ico);/*设置小图标*/
				background-size: 17px 17px;/*小图标的大小*/
				background-position: 5px 5px;/*小图标在input的位置*/
				background-repeat: no-repeat;/*背景小图标不重复*/
				border:1px solid #ddd;
				padding-left: 30px;
			}
		</style>
		
	</head>
	<body>
		<div class="register-box">
			<div class="register-content">
				<form id="form" action="/HrmSys/user/regist">
					 <h2>register</h2>
					 <p style="margin-top: 30px;"><label>用户名&nbsp;</label>&nbsp;<input class="user" type="text" name="username" id="username" placeholder="-username-" autocomplete="off" style="border: 0px;border-radius:10px;font-size: 15px;"/></p>
					
				     <div class="username-tip tip">
				     </div><!-- pattern="[A-Za-z0-9]{6,16}"校验 -->
			         <p><label>密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;</label>&nbsp;<input class="pwd" type="password" name="password" id="password" placeholder="-password-" style="border: 0px;border-radius:10px;font-size: 15px;"/></p>
			         <div class="pwd1-tip tip">
			         	
			         </div>
			    
			         <p><label>确认密码&nbsp;</label>&nbsp;<input class="pwd" type="password" name="" id="password1" placeholder="-confirm password-" style="border: 0px;border-radius:10px;font-size: 15px;"/></p>
			         <div class="pwd2-tip tip">
			         	
			         </div>
			         <p style="margin-top: 25px;"><input id="btn" type="button" value="注册" style="border: 0px;border-radius:10px;"/></p>
		         </form>
			</div>
			<div class="footer">
					<!-- ？p标签样式的 float和 内联样式 的.footer p一起作用才能达到效果？ -->
					<!-- 从登陆页面到注册页面，不能直接通过a标签访问WEB-INF下的资源 ，需要在web.xml中配置映射-->
		          <p style="color: #CCCCCC;float:none;margin-top: 8px" ><a href="loginForm">已有账号？去登录</a></p>
			</div>
		</div>
	</body>
</html>
