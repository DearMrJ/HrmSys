$(function(){
	$('li[title="人员管理"] img').on("click",function(){
		$('li[title="人员管理"] .submenu-box').slideToggle(100);
	});
	$('li[title="薪资管理"] img').on("click",function(){
		$('li[title="薪资管理"] .submenu-box').slideToggle(100);
	});
	$('li[title="职位管理"] img').on("click",function(){
		$('li[title="职位管理"] .submenu-box').slideToggle(100);
	});
	$('li[title="部门管理"] img').on("click",function(){
		$('li[title="部门管理"] .submenu-box').slideToggle(100);
	});
	$('li[title="用户管理"] img').on("click",function(){
		$('li[title="用户管理"] .submenu-box').slideToggle(100);
	});
})