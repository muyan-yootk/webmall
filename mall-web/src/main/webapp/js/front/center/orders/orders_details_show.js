$(function(){
	$("#sumAmount").text(sumAmount()) ;
}) ;
function sumAmount(){
	sum = 0 ; // 保存总数量
	$("td[id^='amount-']").each(function() {
		amount = parseInt($(this).text()) ;
		sum += amount ;
	}) ;
	return sum ;
}