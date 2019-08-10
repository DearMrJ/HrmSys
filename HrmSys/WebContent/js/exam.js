$(function(){
	var val=null;
	$('input[name="test1"]').change(function(){
		 val=$('input[name="test1"]:checked').val();				
	})
	var arr=[];
	var str;
	$('input[name="test2"]').change(function(){
		$("input[name='test2']:checked").each(function(i){
		    arr[i] = $(this).val();
		});
		str=arr.join(",");
	})
	$("#btn2").click(function(){
		var mark=0;
		if (val=="span") {
			mark+=20;
		}
		if (str=="php,jsp,asp") {
			mark+=20;
		}
		if ($('.test3 input').val().trim()=="HttpServletRequest") {
			mark+=20;
		}
		if ($('.test4 input').val().trim()=="invalidate()") {
			mark+=20;
		}
		if ($('.test5 input').val().trim()=="统一资源定位符") {
			mark+=20;
		}
		window.sessionStorage.setItem("mark",mark);//sessionStorage暂存
		window.location.href="/OnlineTest/score/addScore?mark="+mark;
	})
})
