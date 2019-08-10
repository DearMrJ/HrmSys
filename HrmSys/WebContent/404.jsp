<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    <!-- 通过web.xml指定的错误页面不需要 isErrorPage属性也可以-->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>404</title>

	<link href="${pageContext.request.contextPath}/css/404.css" rel="stylesheet" type="text/css" />
	<!--脚本开始--> 
	<script> 
	function countDown(seconds){ 
		if(--seconds>0) {
			document.getElementById("num").innerText=seconds;
			setTimeout(function(){countDown(seconds)},1000);  
		}else{
			window.location.href='/HrmSys/main';
		} 
	}
</script> 
</head>
<body>
	<div id="tips"><b id="num" style="font-size: 20px;"></b>秒后跳转到首页。。。<br />
		<a href="/HrmSys/main"><input class="btn" type="button" value="立即返回"  style="text-align: center;font-family: '微软雅黑';font-size: 15px;"/></a>
	</div>

	<script>
		countDown(10); //此函数需要放到特定组件后
	</script>

</body>
</html>
