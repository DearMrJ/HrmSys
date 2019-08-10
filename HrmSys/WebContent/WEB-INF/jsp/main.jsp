<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>工资管理系统</title>
		<link rel="stylesheet" type="text/css" href="${ctp}/css/index.css"/>
		<link rel="stylesheet" href="${ctp}/live2d/css/live2d.css" />
		<link rel="stylesheet" type="text/css" href="${ctp}/css/default.css"/>
		<link rel="stylesheet" type="text/css" href="${ctp}/css/font-awesome.css"/>
		<link rel="stylesheet" type="text/css" href="${ctp}/src/flipclock/css/flipclock.css"/>
	</head>
	<body>
		<!---->
	    <!--看板娘-->   
        <div id="landlord">
            <div class="message" style="opacity:0"></div>
            <canvas id="live2d" width="280" height="250" class="live2d"></canvas>
            <div class="hide-button">隐藏</div>
        </div>
        <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
        <script type="text/javascript">
            var message_Path = '/live2d/'
            var home_Path = 'https://haremu.com/'
        </script>
        <script type="text/javascript" src="${ctp}/live2d/js/live2d.js"></script>
        <script type="text/javascript" src="${ctp}/live2d/js/message.js"></script>
        <script type="text/javascript">
            loadlive2d("live2d", "live2d/model/tia/model.json");
        </script>
        <!--看板娘结束-->
		<!--最外围的盒子-->
		<div class="box">
			<div class="header-box clearfloat">
				<h2>
					工资管理系统
				</h2>
				<div class="clock-box">
			  	   <div class="htmleaf-content">
			                <div class="clock"></div>
		            </div>	
			    </div>
			    <ul class="clearfloat">			    			       
	               <!--用户名-->  
			    	<li id="username" class="user_info">
			    		 <i class="fa fa-user-circle-o" aria-hidden="true"></i>&nbsp;${current_username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	</li>
			    	<li id="logout">
			    		<a href="user/logout"  class="log_out"><i class="fa fa-sign-out" aria-hidden="true"></i>注销 </a>
			    	</li>
			    </ul>
		    </div>
		    <!--主体部分开始-->  
		    <div class="body-box clearfloat">
        		<!--左菜单栏开始-->
        	<div class="left-menu clearfloat">
        		<ul class="menu-list">
        		
        			<li title="用户管理">
        			    <img src="img/plus01.gif"/>用户管理
        			    <ul class="submenu-box">
        			    	<li><a href="user/selectUser" target="show_message">用户查询</a></li>
        			    	<c:if test="${current_state==2}">
        			    		<li><a href="user/addUser?flag=1" target="show_message">添加用户</a></li>
        			    	</c:if>
        			    </ul>
        			</li>
        			
        			
        			<li title="部门管理">
        				<img src="img/plus01.gif"/>部门管理 
        				<ul class="submenu-box">
        					<li title="部门查询"><a href="dept/selectDept" target="show_message">部门查询</a></li>
        					<c:if test="${current_state==2}">
        						<li title="添加部门"><a href="dept/addDept?flag=1" target="show_message">添加部门</a></li>
        					</c:if>
        				</ul>
        			</li>
        			
        			<li  title="职位管理">
        				<img src="img/plus01.gif"/>职位管理 
        				<ul class="submenu-box">
        					<li title="职位查询"><a href="job/selectJob" target="show_message">职位查询</a></li>
        					<c:if test="${current_state==2}">
        						<li title="添加职位"><a href="job/addJob?flag=1" target="show_message">添加职位</a></li>
        					</c:if>
        				</ul>
        			</li>
        			
        			<li title="人员管理">       			
        	            <img src="img/plus01.gif"/>员工管理               
        	            <ul class="submenu-box">
        	                <li title="员工查询"><a href="employee/selectEmployee" target="show_message">员工查询</a></li>
        	                <c:if test="${current_state==2}">
        	                	<li title="添加员工"><a href="employee/addEmployee?flag=1" target="show_message">添加员工</a></li>
        	                </c:if>
        	            </ul>
        			</li>
        			
        			<li  title="薪资管理">
        				<img src="img/plus01.gif"/>薪资管理 
        				<ul class="submenu-box">
        					<li title="薪资查询"><a href="salary/selectSalary" target="show_message">薪资查询</a></li>
        					<c:if test="${current_state==2}">
        						<li title="添加薪资记录"><a href="salary/addSalary?flag=1" target="show_message">添加记录</a></li>
        					</c:if>
        					<li title="工资计算"><a href="salary/countAvg?flag=1" target="show_message">工资计算</a></li>
        				</ul>
        			</li>
        		</ul>       		
        	</div>
        	<!--左菜单栏结束--> 
        	<!--信息展示栏开始-->
        	<div class="show-message">
              	<iframe src="right" width="100%" height="100%" style="border: none;" name="show_message"></iframe>
             </div>
             <!--信息展示栏结束-->  
        	<!--主体部分结束--> 
		</div>
		<!--大盒子结束--> 
		</div>		
		<script src="${ctp}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		    var clock;			
			$(document).ready(function() {
				clock = $('.clock').FlipClock({
					clockFace: 'TwelveHourClock'
				});
			});
	    </script>
		<script src="${ctp}/js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctp}/js/funtion.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctp}/compiled/flipclock.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>

