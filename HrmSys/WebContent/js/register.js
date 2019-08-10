window.onload=function()
{
	var btn=document.getElementById("btn");//获取按钮
	document.getElementById("password").setCustomValidity("密码只能是6~16位字母、数字组合。");
	btn.onclick=function()//添加按钮点击事件
	{	
		var Username = document.getElementById("username").value;//获取用户名
		var Password = document.getElementById("password").value;//获取密码
		var Password1 = document.getElementById("password1").value;//获取密码
	    //判断输入是否正确在这个内容块写
	    /*****************************/
	   if(Username == "")
	   {
	   		alert("用户名不能为空");
	   		return false;
	   }
	   if(Username.length<3){
		   alert("用户名太短了");
		   return false;
	   }
	   if(Username.indexOf("-")!= -1){
			alert("用户名不能包含特殊字符");
			return false;
		}
	   if(Username.indexOf("#")!= -1){
		   alert("用户名不能包含特殊字符");
		   return false;
	   }
	   if(Password == "")
	   {
	   		alert("密码不能为空");
	   		return false;
	   }
	   if(Password.length<6){
			alert("密码只能是6~16位字符");
			return false;
		}
		if(Password.length>16){
			alert("密码只能是6~16位字符");
			return false;
		}
		if(Password.indexOf("-")!= -1){
			alert("密码不能包含特殊字符");
			return false;
		}
	   if(Password.indexOf("#")!= -1){
		   alert("密码不能包含特殊字符");
		   return false;
	   }
	   if(Password1 != Password)
	   {
		   	alert("两次密码不一致");
		   	return false;
	   }else{
		   	var that = $('#password');
		   	var that1 = $('#password1');
		   	console.log(Username);
		   	//获取公钥
			$.ajax({
				url:"/HrmSys/user/getPublicKey",
				type:"post",
				data:{},
				success:function(result){//返回公钥
					var publicKey = result;
					console.log(publicKey);
					//对密码进行加密
			        var encrypt = new JSEncrypt();
			        encrypt.setPublicKey(publicKey);
			        var password = that.val();
			        that.val(encrypt.encrypt(password));
			        that1.val(encrypt.encrypt(password));
			        $('#form').submit();
				}
			});
	   }
	}
}
