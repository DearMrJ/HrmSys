$(function(){
	var $width=$(document).width();
	var $height=$(document).height();
	$("body").css("min-width",$width+"px");
	$("body").css("min-height",$height+"px");
	var header_height=$('.header-box').height();
	$("#username").css("line-height",header_height+"px");
	$("#logout").css("line-height",header_height+"px");
	$("h2").css("line-height",header_height+"px");
})