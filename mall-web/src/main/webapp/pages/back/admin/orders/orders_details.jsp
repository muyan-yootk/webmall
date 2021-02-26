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
    public static final String ORDERS_SHOW_URL = "pages/back/admin/orders/list.action";
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
                <div class="row">
                    <div class="col-md-3"><strong>收件人：</strong></div>
                    <div class="col-md-9 col-md-pull-1">${orders.name}</div>
                </div>
                <div class="row">
                    <div class="col-md-3"><strong>下单日期：</strong></div>
                    <div class="col-md-9 col-md-pull-1">${orders.subdate}</div>
                </div>
                <div class="row">
                    <div class="col-md-3"><strong>配送地址：</strong></div>
                    <div class="col-md-9 col-md-pull-1">${orders.address}</div>
                </div>
                <div class="row">
                    <div class="col-md-3"><strong>总金额：</strong></div>
                    <div class="col-md-9 col-md-pull-1">${orders.price}</div>
                </div>
                <div class="row">
                    <div class="col-md-3"><strong>购买商品总数：</strong></div>
                    <div class="col-md-9 col-md-pull-1" id="sumAmount">6</div>
                </div>
                <div class="row">
                    <table class="table table-condensed">
                        <thead>
                        <tr>
                            <th class="text-center"><strong>商品名称</strong></th>
                            <th class="text-center"><strong>价格</strong></th>
                            <th class="text-center"><strong>购买数量</strong></th>
                            <th class="text-center"><strong>总额</strong></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${allGoods}" var="goods">
                            <tr>
                                <td class="text-center">${goods.name}</td>
                                <td class="text-center">${goods.price}</td>
                                <td class="text-center" id="amount-${goods.gid}">${details[goods.gid]}</td>
                                <td class="text-center">${goods.price * details[goods.gid]}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
