<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <jsp:include page="/pages/plugins/back/include_basepath.jsp" />
    <jsp:include page="/pages/plugins/back/include_javascript_head.jsp" />
    <script type="text/javascript" src="js/back/admin/orders/orders_list.js"></script>
</head>
<%!
    public static final String ORDERS_SHOW_URL = "pages/back/admin/orders/details.action";
%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- 导入头部标题栏内容 -->
    <jsp:include page="/pages/plugins/back/include_title_head.jsp" />
    <!-- 导入左边菜单项 -->
    <jsp:include page="/pages/plugins/back/include_menu_item.jsp">
        <jsp:param name="mi" value="3"/>
        <jsp:param name="msi" value="31"/>
    </jsp:include>
    <div class="content-wrapper text-left">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <strong><span class="glyphicon glyphicon-list"></span>&nbsp;订单列表</strong>
            </div>
            <div class="panel-body" style="height: 90%;">
                <div id="splitSearchDiv">
                    <jsp:include page="/pages/plugins/split_page_search_plugin.jsp"/>
                </div>
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th class="text-center" style="width:10%"><strong>订单编号</strong></th>
                        <th class="text-center" style="width:10%"><strong>用户ID</strong></th>
                        <th class="text-center" style="width:5%"><strong>总价</strong></th>
                        <th class="text-center" style="width:10%"><strong>收件人</strong></th>
                        <th class="text-center" style="width:25%"><strong>配送地址</strong></th>
                        <th class="text-center" style="width:10%"><strong>联系电话</strong></th>
                        <th class="text-center" style="width:20%"><strong>下单日期</strong></th>
                        <th class="text-center" style="width:10%"><strong>操作</strong></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allOrders}" var="orders">
                        <tr>
                            <td class="text-center">${orders.oid}</td>
                            <td class="text-center">${orders.mid}</td>
                            <td class="text-center">${orders.price}</td>
                            <td class="text-center">${orders.name}</td>
                            <td class="text-center">${orders.address}</td>
                            <td class="text-center">${orders.phone}</td>
                            <td class="text-center">${orders.subdate}</td>
                            <td class="text-center">
                                <a type="button" class="btn btn-primary btn-xs" href="<%=ORDERS_SHOW_URL%>?oid=${orders.oid}">
                                    <span class="glyphicon glyphicon-list-alt"></span>&nbsp;查看详情</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div id="splitBarDiv" style="float:right">
                    <jsp:include page="/pages/plugins/split_page_bar_plugin.jsp"/>
                </div>
                <div class="row" style="height:50px;">
                    <jsp:include page="/pages/plugins/include_alert.jsp">
                        <jsp:param value="true" name="setfloat"/>
                    </jsp:include>
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
