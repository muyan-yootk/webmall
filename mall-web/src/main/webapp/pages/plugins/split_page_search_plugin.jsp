<%@ page pageEncoding="UTF-8"%>
<%--
1、组件引用模式：
	<jsp:include page="split_page_search_plugin.jsp"/>
2、属性的设置：
	request.setAttribute("columnData",columnData) ;
	request.setAttribute("keyword",keyword) ;
	request.setAttribute("url",url) ;
	request.setAttribute("column",column) ;
--%>
<%
	// 通过属性进行所需要的参数的传递
	String columnData = (String) request.getAttribute("columnData") ;
	String keyword = (String) request.getAttribute("keyword") ;
	String url = (String) request.getAttribute("url") ;
	String column = (String) request.getAttribute("column") ;
	String label = (String) request.getAttribute("label") ;
	Object labelValue = null ;
	if (label != null) {
		labelValue = request.getAttribute(label) ;
	}
	if (labelValue == null || label == null) {
		label = "nothing" ;
		labelValue = "" ;
	}
	if (keyword == null) {
		keyword = "" ; // 设置空字符串
	}
	if (column == null) {
		column = "" ;
	}
%>
<div id="searchDiv">
	<form ation="<%=url%>" class="form-horizontal" id="searchform" method="get">
		<div class="form-group" id="searchDiv">
<%
	if (!(columnData == null || "".equals(columnData))) {
%>
			<div class="col-md-2">
				<select id="col" name="col" class="form-control">
<%
					String columnResult [] = columnData.split("\\|") ;
					for (int x = 0 ; x < columnResult.length ; x ++) {
						String temp [] = columnResult[x].split(":") ;
%>
						<option value="<%=temp[1]%>" <%=column.equals(temp[1])?"selected":""%>><%=temp[0]%></option>
<%
					}
%>
				</select>
			</div>
<%
	}
%>
			<div class="col-md-9">
				<input type="hidden" name="<%=label%>" value="<%=labelValue%>">
				<input type="text" id="kw" name="kw" class="form-control" placeholder="请输入模糊查询关键字" value="<%=keyword%>">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary" value="搜索">
			</div>
		</div>
	</form>
</div>