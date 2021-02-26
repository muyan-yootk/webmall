$(function() {
	$("button[id*=addCar-]").each(function(){
		var gid = $(this).attr("id").split("-")[1] ;
		$(this).on("click",function(){
			console.log("*** gid = " + gid) ;
			operateAlert(true,"购物车添加成功！","购物车添加失败！") ;
		}) ;
	}) ; 
}) 