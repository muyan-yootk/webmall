<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String goods_list_url = "pages/front/goods/goods_list.action" ;
	String admin_url = "pages/back/index.jsp" ;
	String member_logout_url = "member_logout.action" ;
	String member_login_url = "member_login_pre.action" ;
	String orders_list_url = "pages/front/center/orders/orders_list.action" ;
	String shopcar_list_url = "pages/front/center/shopcar/shopcar_list.action" ;
%>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="<%=goods_list_url%>">
			<img src="images/muyan_yootk.png" style="width:100px;"></a>
	</div>
	<ul class="nav navbar-nav">
		<c:if test="${member == null}">
			<li><a href="<%=member_login_url%>"><span class="glyphicon glyphicon-user"></span>&nbsp;登录</a></li>
		</c:if>
		<c:if test="${member != null}">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span>&nbsp;个人中心<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<%=orders_list_url%>">
						<span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单列表</a></li>
					<li><a href="<%=member_logout_url%>">
						<span class="glyphicon glyphicon-log-out"></span>&nbsp;系统注销</a></li>
					<c:if test="${member.level == 1}">
						<li><a href="<%=admin_url%>">
							<span class="glyphicon glyphicon-cog"></span>&nbsp;后台管理</a></li>
					</c:if>
				</ul></li>
			<li><a href="<%=shopcar_list_url%>">
				<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;我的购物车</a></li>
		</c:if>
		<li><a href="https://www.yootk.com/resources">
			<span class="glyphicon glyphicon-leaf"></span>&nbsp;直播课程回放</a></li>
	</ul> 
	<form class="navbar-form navbar-left" action="<%=goods_list_url%>" method="post">
		<div class="form-group"> 
			<input type="text" class="form-control input-xs" name="kw" placeholder="请输入商品关键字..." value="${keyWord}" style="width:600px;background: #F5F5F5;height:30px;">
			<input type="hidden" name="col" value="name">
			<button class="btn btn-danger" type="submit" style="height:30px;">搜索</button>
		</div>
	</form>
</nav>
