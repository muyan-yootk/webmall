<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/pages/plugins/back/include_basepath.jsp" />
<jsp:include page="/pages/plugins/back/include_javascript_head.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp" />
		<div class="content-wrapper text-center">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-envelope"></span>&nbsp;操作完成</strong>
				</div>
				<div class="panel-body">
					<script type="text/javascript">
						window.onload = function() {
							goForward() ;
						}
						function goForward() {
							spanObject = document.getElementById("countSpan") ;
							count = parseInt(spanObject.innerHTML) ;	// 取得当前计数的内容
							count -- ;
							if (count == 0) {	// 要进行跳转
								window.location = "${path}" ;	// 跳转
							} else {
								spanObject.innerHTML = count ;
								setTimeout(goForward,1000) ;
							}
						}
					</script>
					<div class="h1 text-left">${msg}</div>
					<div class="h1 text-left"><span id="countSpan">5</span>秒后跳转到其它页面，如果没有跳转请按<a href="${basePath}${path}">这里</a>！</div>
					<div class="alert alert-success" id="alertDiv" style="display: none;">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<span id="alertText"></span>
					</div>
				</div>
				<div class="panel-footer">
					<jsp:include page="/pages/plugins/company_footer.jsp"/>
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
</body>
</html>
