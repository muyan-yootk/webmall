/**
 * 复选框批量选择
 * @param ele 要批量选择的复选框对象
 * @param ckd 触发此组件的当前选中状态
 */
function checkboxSelectAll(ele, ckd) {
	if (ckd == true) {
		$("input[id='" + ele + "']").prop("checked",true) ;
	} else {
		$("input[id='" + ele + "']").prop("checked",false) ;
	} 
}
/**
 * 批量操作数据执行操作
 * @param title 要进行的提示信息
 * @param elename 要操作的元素信息
 * @param url 要执行的url操作
 */
function operateChecked(title,elename,url) {
	var ids = "" ;
	$("input[id='"+elename+"']:checked").each(function() {
		ids += $(this).val() + "|" ;
	}) ;
	if (ids == "") {
		$("#alertDiv").attr("class","alert alert-danger") ;
        $("#alertText").text("您还未选择任何数据，请确认您的操作！") ;
		$("#alertDiv").fadeIn(1000,function(){
            $("#alertDiv").fadeOut(3000) ;
        }) ;
	} else {
		if (window.confirm("您确定要继续执行此操作吗？")) {
			url += "&ids=" + ids ;
			window.location = url ;
		}
	}
}
/**
 * 实现四舍五入的处理操作
 * @param num 要进行操作的数字
 * @param sca 保留的小数位
 */
function round(num,sca) {
	return Math.round(num * Math.pow(10,sca))/Math.pow(10,sca) ;
}
/**
 * 警告框操作信息，ID必须为“alertDiv”
 * @param flag 操作成功或失败的标记
 * @param suctext 操作成功时的显示文本内容
 * @param faltext 操作失败时的显示文本内容
 */
function operateAlert(flag,suctext,faltext) {
	if (flag) {
		$("#alertDiv").attr("class","alert alert-success") ;
		$("#alertText").text(suctext) ;
	} else {
		$("#alertDiv").attr("class","alert alert-danger") ;
		$("#alertText").text(faltext) ;
	}
	$("#alertDiv").fadeIn(1000,function(){
        $("#alertDiv").fadeOut(3000) ;
    }) ;
}
