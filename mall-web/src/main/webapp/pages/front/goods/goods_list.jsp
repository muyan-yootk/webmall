<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<jsp:include page="/pages/plugins/basepath.jsp"/>
<script type="text/javascript" src="js/front/goods/goods_list.js"></script> 
</head>
<body>
	<div class="contentback"> 
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div> 
		<div id="contentDiv" style="height: 100%;">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-edit"></span>&nbsp;商品列表</strong>
					</div>
					<div class="panel-body">
						<table class="table table-striped table-bordered table-hover">
							<c:forEach items="${allGoods}" var="goods">
								<tr>
									<td class="text-center" style="width:10%"><img src="http://upload-server/upload/${goods.photo}" style="width:30px;"></td>
									<td style="width:70%">${goods.name}</td>
									<td style="width:10%">￥${goods.price}</td> 
									<td style="width:10%">
										<button id="addCar-${goods.gid}" class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;加入购物车</button>
									</td>
								</tr> 
							</c:forEach>
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
				</div>
			</div>
		</div> 

	</div>
	<jsp:include page="/pages/plugins/company_footer.jsp"/>
</body>
</html>