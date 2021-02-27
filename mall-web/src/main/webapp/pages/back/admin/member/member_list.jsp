<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <jsp:include page="/pages/plugins/back/include_basepath.jsp" />
    <jsp:include page="/pages/plugins/back/include_javascript_head.jsp" />
    <script type="text/javascript" src="js/back/admin/member/member_list.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- 导入头部标题栏内容 -->
    <jsp:include page="/pages/plugins/back/include_title_head.jsp" />
    <!-- 导入左边菜单项 -->
    <jsp:include page="/pages/plugins/back/include_menu_item.jsp">
        <jsp:param name="mi" value="2"/>
        <jsp:param name="msi" value="21"/>
    </jsp:include>
    <div class="content-wrapper text-left">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <strong><span class="glyphicon glyphicon-list"></span>&nbsp;用户列表</strong>
            </div>
            <div class="panel-body" style="height: 90%;">
                <div id="splitSearchDiv">
                    <jsp:include page="/pages/plugins/split_page_search_plugin.jsp"/>
                </div>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr id="member-title">
                            <td class="text-center" style="width:20%">用户ID</td>
                            <td class="text-center" style="width:70%">姓名</td>
                            <td class="text-center" style="width:10%">级别</td>
                        </tr>
                    </thead>
                    <tbody id="memberBody">
                    <c:forEach items="${allMembers}" var="member">
                        <tr id="member-${member.mid}">
                            <td>${member.mid}</td>
                            <td>${member.name}</td>
                            <td>
                                <c:if test="${member.level == 0}">
                                    <span class="text-info">普通用户</span>
                                </c:if>
                                <c:if test="${member.level == 1}">
                                    <span class="text-danger">后台管理员</span>
                                </c:if>
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
