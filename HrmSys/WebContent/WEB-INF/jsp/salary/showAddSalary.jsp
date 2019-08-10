<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加薪资记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctp}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctp}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctp}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctp }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctp }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctp}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctp}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctp}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctp}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<link href="${ctp}/css/pager.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="${ctp }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
	 
	    $(function(){
	    	/** 员工表单提交 */
			$("#salaryForm").submit(function(){
				var employee_name = $("#employee_name");
				var employee_id = $("#employee_id");
				var job_id = $("#job_id");
				var dept_id = $("#dept_id");
				var basic_salary = $("#basic_salary");
				var bonus = $("#bonus");
				var fine = $("#fine");
				var msg = "";
				if ($.trim(employee_name.val()) == ""){
					msg = "姓名不能为空！";
					employee_name.focus();
				}else if ($.trim(employee_id.val()) == ""){
					msg = "工号不能为空！";
					employee_id.focus();
				}else if ($.trim(job_id.val()) == 0){
					msg = "职位不能为空！";
					job_id.focus();
				}else if ($.trim(dept_id.val()) == 0){
					msg = "部门不能为空！";
					dept_id.focus();
				}else if ($.trim(basic_salary.val()) == ""){
					msg = "基本工资不能为空！";
					basic_salary.focus();
				}else if ($.trim(bonus.val()) == ""){
					msg = "奖金不能为空！";
					bonus.focus();
				}else if ($.trim(fine.val()) == ""){
					msg = "罚金不能为空！";
					fine.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#salaryForm").submit();
			});
	    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctp}/img/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctp}/img/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：薪资管理  &gt; 添加记录</td>
	<td width="15" height="32"><img src="${ctp}/img/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctp}/salary/addSalary" id="salaryForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">
			    			姓名：<input type="text" name="employee_name" id="employee_name" size="20"/>&nbsp;&nbsp;
			    			&nbsp;&nbsp;员工号：<input type="text" name="employee_id" id="employee_id" size="20"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">职位：
		    			 <select name="job_id" id="job_id" style="width:143px;">
					    			<option value="0">--请选择职位--</option>
					    			<c:forEach items="${requestScope.jobs }" var="job">
					    				<option value="${job.id}">${job.name}</option>
					    			</c:forEach>
					    		</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					      &nbsp;&nbsp;&nbsp;&nbsp;部&nbsp;&nbsp;&nbsp;门：
		    			 <select name="dept_id" id="dept_id" style="width:143px;">
					    			<option value="0">--请选择部门--</option>
					    			<c:forEach items="${requestScope.depts }" var="dept">
					    				<option value="${dept.id}">${dept.name}</option>
					    			</c:forEach>
					    		</select>&nbsp;&nbsp;
					    </td>
		    		</tr>
		    		
					<tr>
						<td class="font3 fftd">
							忽略：<input type="text" name="ignoreMe" size="20"/> 
							基本工资：<input type="text" name="basic_salary" id="basic_salary" size="20"/>
						</td> 
					</tr>
			
			<tr>
				<td class="font3 fftd">
					奖金：<input name="bonus" id="bonus" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
					罚&nbsp;&nbsp;&nbsp;金：<input name="fine" id="fine" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd">
			<input type="submit" value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>