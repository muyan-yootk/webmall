<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<jsp:include page="/pages/plugins/basepath.jsp"/>
	<script type="text/javascript" src="js/login.js"></script>
</head>
<%!
	public static final String LOGIN_URL = "member_login.action" ;
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
						<strong><span class="glyphicon glyphicon-user"></span>&nbsp;用户登录</strong>
					</div>
					<div class="panel-body">
						<c:if test="${mid == null}">
						<form class="form-horizontal" action="<%=LOGIN_URL%>" id="myform" method="post">
							<fieldset>
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="midDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="mid">登录名：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="mid" name="mid" class="form-control"
											placeholder="用户名 / 邮箱 / 手机">
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
											placeholder="请输入登录密码" value="hello">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="passwordMsg"></div>
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
								<div class="form-group" id="remembermeDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="code">&nbsp;</label>
									<div class="col-md-3">
										<!-- 定义表单输入组件 -->
										<input type="checkbox" id="rememberme" name="rememberme" class=""
											value="true">10天内免登录
									</div> 
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="remembermeMsg"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<button type="submit" class="btn btn-primary">登录</button>
										<button type="reset" class="btn btn-warning">重置</button>
										<a href="regist.jsp" type="button" class="btn-link">还未注册？</a>
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
