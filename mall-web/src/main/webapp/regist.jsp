<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<jsp:include page="/pages/plugins/basepath.jsp"/>
	<script type="text/javascript" src="js/regist.js"></script>
</head>
<%!
	public static final String REGIST_URL = "member_regist.action" ;
%>
<body class="container-fluid">
<div>
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
					<strong><span class="glyphicon glyphicon-user"></span>&nbsp;用户注册</strong>
				</div>
				<div class="panel-body">
					<c:if test="${mid == null}">
						<form class="form-horizontal" action="<%=REGIST_URL%>" id="myform" method="post">
							<fieldset>
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="midDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="mid">登录名：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="mid" name="mid" class="form-control"
											   placeholder="请输入用户名..." value="admin">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="midMsg"></div>
								</div>
								<div class="form-group" id="passwordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="password">登录密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="password" name="password" class="form-control"
											   placeholder="请输入登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="passwordMsg"></div>
								</div>
								<div class="form-group" id="confDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="conf">密码确认：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="conf" name="conf" class="form-control"
											   placeholder="请输入登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="confMsg"></div>
								</div>
								<div class="form-group" id="codeDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="code">验证码：</label>
									<div class="col-md-3">
										<!-- 定义表单输入组件 -->
										<input type="text" id="code" name="code" class="form-control"
											   placeholder="验证码" size="4" maxlength="4">
									</div>
									<div class="col-md-2">
										<img src="ImageCode" id="imageCode" title="看不清？单击换一张图片">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="codeMsg"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<button type="submit" class="btn btn-primary">注册</button>
										<button type="reset" class="btn btn-warning">重置</button>
									</div>
								</div>
							</fieldset>
						</form>
					</c:if>
					<c:if test="${mid != null}">
						您已经登录过了，请正常访问！
					</c:if>
				</div>
				<div class="panel-footer" style="height:80px;">
					<jsp:include page="/pages/plugins/include_alert.jsp"/>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/pages/plugins/company_footer.jsp"/>
</body>
</html>
