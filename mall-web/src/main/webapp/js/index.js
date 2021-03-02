$(function() {
	$("button[id*=addCar-]").each(function(){
		var gid = $(this).attr("id").split("-")[1] ;
		$(this).on("click",function(){
			console.log("【购物车添加】商品编号（gid） = " + gid) ;
			$.get("pages/front/center/shopcar/add.action",{"gid": gid},function(data){
				operateAlert(data.trim() == "true","购物车添加成功！","购物车添加失败！") ;
			},"text") ;
		}) ;
	}) ; 
}) 