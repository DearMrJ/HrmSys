<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    <!-- 通过web.xml指定的错误页面不需要 isErrorPage属性也可以-->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>404</title>

	<link href="${pageContext.request.contextPath}/css/500.css" rel="stylesheet" type="text/css" />
	<!--脚本开始--> 
	<script> 
	function countDown(seconds){ 
		if(--seconds>0) {
			document.getElementById("num").innerText=seconds;
			setTimeout(function(){countDown(seconds)},1000);  
		}else{
			window.history.go(-1);//返回上一页
// 			window.location.href="../right";
		} 
	}
</script> 
</head>
<body>
	<div id="tips"><span style="color: #FF3030;font-size: 20px;">输入信息非法！现在我怀疑你动机不纯。</span><br/>你可以选择沉默，但你所说的都将成为呈堂证供。<br/>
		<b id="num" style="font-size: 20px;"></b>秒后返回上一个页面。。。<br />
		<input class="btn" type="button" value="立即返回"  onclick="history.back()" style="text-align: center;font-family: '微软雅黑';font-size: 15px;"/>
	</div>

	<script>
		countDown(10); //此函数需要放到特定组件后
	</script>

</body>
</html>
