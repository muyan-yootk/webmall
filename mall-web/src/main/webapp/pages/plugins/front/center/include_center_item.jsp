<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String orders_list = request.getAttribute("basePath") + "/pages/front/center/orders/orders_list.action" ;
	String shopcar_list = request.getAttribute("basePath") + "/pages/front/center/shopcar/shopcar_list.action" ;
%>
<ul class="nav nav-pills nav-stacked">									<!-- 定义导航 -->
	<li class="${param.ch == 1 ? "active" : ""}"><a href="<%=orders_list%>">我的订单</a></li>			<!-- 活跃导航项 -->
	<li class="${param.ch == 2 ? "active" : ""}"><a href="<%=shopcar_list%>">购物车</a></li>			<!-- 禁用导航项 -->
</ul>
 