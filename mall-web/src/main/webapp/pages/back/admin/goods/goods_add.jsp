<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <jsp:include page="/pages/plugins/back/include_basepath.jsp" />
    <jsp:include page="/pages/plugins/back/include_javascript_head.jsp" />
    <script type="text/javascript" src="js/back/admin/goods/goods_add.js"></script>
</head>
<%!
    public static final String GOODS_ADD_URL = "pages/back/admin/goods/goods_add.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- 导入头部标题栏内容 -->
    <jsp:include page="/pages/plugins/back/include_title_head.jsp" />
    <!-- 导入左边菜单项 -->
    <jsp:include page="/pages/plugins/back/include_menu_item.jsp">
        <jsp:param name="mi" value="1"/>
        <jsp:param name="msi" value="11"/>
    </jsp:include>
    <div class="content-wrapper text-left">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <strong><span class="glyphicon glyphicon-pencil"></span>&nbsp;商品添加</strong>
            </div>
            <div class="panel-body" style="height: 90%;">
                <form action="<%=GOODS_ADD_URL%>" method="post" class="form-horizontal" id="goodsForm" enctype="multipart/form-data">
                    <fieldset>
                        <div class="form-group" id="nameDiv">
                            <label class="col-md-3 control-label">商品名称：</label>
                            <div class="col-md-6">
                                <input type="text" id="name" name="name" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <span id="nameMsg"></span>
                            </div>
                        </div>
                        <div class="form-group" id="priceDiv">
                            <label class="col-md-3 control-label">商品价格：</label>
                            <div class="col-md-6">
                                <input type="text" id="price" name="price" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <span id="priceMsg"></span>
                            </div>
                        </div>
                        <div class="form-group" id="fileDiv">
                            <label class="col-md-3 control-label">商品图片：</label>
                            <div class="col-md-6">
                                <input type="file" name="file" id="file" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <span id="fileMsg"></span>
                            </div>
                        </div>
                        <div class="form-group" id="buttonDiv">
                            <div class="col-md-6 col-md-push-3">
                                <input type="submit" id="submitBut" class="btn btn-primary" value="增加">
                                <input type="reset" id="resetBut" class="btn btn-danger" value="重置">
                            </div>
                        </div>
                    </fieldset>
                </form>
                <jsp:include page="/pages/plugins/include_alert.jsp"/>
            </div>
            <div class="panel-footer" style="height:80px;">
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
