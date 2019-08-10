$(function(){
	/*若已经登录则显示查询窗口
	$("#menu3").on("click",function(){
		if(){
			$(".mask").css("display","block");
			$(".member-query").css("display","block");
		}
	})
	*/
	/*测试从后台获取数据用
	$(".comment").on("click",function(){
		$.ajax({
			type:"get",
			url:"js/user-message.json",
			dataType:"json",
			success: function(data){
				alert(data[0].name);
			},
			async:true
		});
	})
	*/
	$("#menu4").hover(function(){
		$(".nav-list").fadeIn(500);
	},function(){
		$(".nav-list").fadeOut(100);
	})
	//
	$("input[value='确认搜索']").click(function(){
		var $member_id=$("input[name='member-id']").val();
		var $member_name=$("input[name='member-name']").val();
		if ($member_id==""||$member_name=="") {
			alert("编号和姓名不能为空");
			return false;
		} 
	})
})