<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <jsp:include page="/pages/plugins/back/include_basepath.jsp" />
    <jsp:include page="/pages/plugins/back/include_javascript_head.jsp" />
    <script type="text/javascript" src="js/back/admin/goods/goods_list.js"></script>
</head>
<%!
    public static final String GOODS_EDIT_URL = "pages/back/admin/goods/edit.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- 导入头部标题栏内容 -->
    <jsp:include page="/pages/plugins/back/include_title_head.jsp" />
    <!-- 导入左边菜单项 -->
    <jsp:include page="/pages/plugins/back/include_menu_item.jsp">
        <jsp:param name="mi" value="1"/>
        <jsp:param name="msi" value="12"/>
    </jsp:include>
    <div class="content-wrapper text-left">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <strong><span class="glyphicon glyphicon-list"></span>&nbsp;商品列表</strong>
            </div>
            <div class="panel-body" style="height: 90%;">
                <div id="splitSearchDiv">
                    <jsp:include page="/pages/plugins/split_page_search_plugin.jsp"/>
                </div>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr id="goods-title">
                            <td class="text-center" style="width:5%"><input type="checkbox" id="selectall"></td>
                            <td class="text-center" style="width:20%">商品图片</td>
                            <td class="text-center" style="width:55%">商品名称</td>
                            <td class="text-center" style="width:10%">商品价格</td>
                            <td class="text-center" style="width:10%">操作</td>
                        </tr>
                    </thead>
                    <tbody id="goodsBody">
                    <c:forEach items="${allGoods}" var="goods">
                        <tr id="goods-${goods.gid}">
                            <td class="text-center"><input type="checkbox" id="gid" value="${goods.gid}"></td>
                            <td class="text-center"><img src="http://upload-server/upload/${goods.photo}" style="width:30px;"></td>
                            <td>${goods.name}</td>
                            <td>￥${goods.price}</td>
                            <td>
                                <button id="${goods.gid}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div><button type="button" id="deleteBut" class="btn btn-lg btn-danger">
                    <span class="glyphicon glyphicon-remove"></span>&nbsp;删除选中商品</button></div>
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
