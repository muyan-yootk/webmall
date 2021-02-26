<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/basepath.jsp"/>
<body class="container-fluid">
	<div class="contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div> 
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
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
									window.location = "${pageContext.request.contextPath}${url}" ;	// 跳转
								} else {
									spanObject.innerHTML = count ;
									setTimeout(goForward,1000) ;
								}
							}
						</script>
						<div>${msg}</div>
						<div><span id="countSpan">5</span>秒后跳转到其它页面，如果没有跳转请按<a href="${basePath}${url}">这里</a>！</div>
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
		</div>
	</div>
