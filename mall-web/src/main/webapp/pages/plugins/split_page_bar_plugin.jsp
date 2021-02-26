<%@ page pageEncoding="UTF-8"%>
<%--
1、组件引用模式：
	<jsp:include page="split_page_bar_plugin.jsp"/>
2、传递的属性内容：
	request.setAttribute("columnData",columnData) ;
	request.setAttribute("keyword",keyword) ;
	request.setAttribute("url",url) ;
	request.setAttribute("column",column) ;
	request.setAttribute("currentPage",currentPage) ;
	request.setAttribute("lineSize",lineSize) ;
	request.setAttribute("allRecorders",allRecorders) ;
--%>
<%
	int currentPage = 1 ; // 当前所在页编号
	int lineSize = 5 ; // 每页显示3行记录
	long allRecorders = 0 ; // 总的记录数
	int pageSize = 0 ; // 计算总页数
	String url = null ;
	String column = null ; // 表示要进行模糊查询的列名称
	String keyword = null ; // 模糊查询关键字
	int seed = 3 ; // 定义一个判断的种子数值
	String label = (String) request.getAttribute("label") ;
	Object labelValue = null ;
	if (label != null) {
		labelValue = request.getAttribute(label) ;
	}
	if (labelValue == null || label == null) {
		label = "nothing" ;
		labelValue = "" ;
	}
%>
<%
	try {
		currentPage = (Integer) request.getAttribute("currentPage") ;
	} catch (Exception e) {}
	try {
		lineSize = (Integer) request.getAttribute("lineSize") ;
	} catch (Exception e) {}
	try {
		allRecorders = (Long) request.getAttribute("allRecorders") ;
	} catch (Exception e) {}
	url = request.getContextPath() + (String) request.getAttribute("url") ;
	column = (String) request.getAttribute("column") ;
	keyword = (String) request.getAttribute("keyword") ;
%>
<%	// 对接下来的数据要进行一些处理
	pageSize = (int) (allRecorders + lineSize - 1) / lineSize ;
	if (pageSize == 0) {	// 没有任何的数据记录
		pageSize = 1 ; // 当前在第1页
	}
	if (column == null) {
		column = "" ;
	}
	if (keyword == null) {
		keyword = "" ;
	}
%>
	<div id="pagebarDiv" style="float:right">
		<ul class="pagination">
<%
	if (currentPage == 1) {
%>
			<li class="disabled"><span>&lt;&lt;</span></li>
<%
	} else {
%> 
			<li><a href="<%=url%>?cp=<%=currentPage - 1%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>">&lt;&lt;</a></li>
<%
	}
	if (pageSize <= seed * 2) {
		for (int x = 1 ; x <= pageSize ; x ++) {
			if (currentPage == x) {
%>
				<li class="active"><span><%=x%></span></li>
<%
			} else {
%>
				<li><a href="<%=url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>"><%=x%></a></li>
<%
			}
		}
	} else {
		if (currentPage == 1) {
%>
			<li class="active"><span>1</span></li>
<%
		} else {
%>
		<li><a href="<%=url%>?cp=1&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>">1</a></li>
<%
		}
%>
<%		// 此处需要进行中间范围的打印处理
		if (currentPage < seed * 2 - 1) {	// 触发了一个出现省略的问题
			for (long x = 2 ; x <= seed * 2 ; x ++) {
				if (currentPage == x) {
%>
					<li class="active"><span><%=x%></span></li>
<%
				} else {
%>
					<li><a href="<%=url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>"><%=x%></a></li>
<%
				}
			}
		} else {
			if (currentPage > seed * 2 - 1) {
%>
				<li><span>...</span></li>
<%
			}
			long startPage = currentPage - seed ;
			long endPage = currentPage + seed ;
			if (endPage >= pageSize) {
				endPage = pageSize - 1 ;
			}
			for (long x = startPage ; x <= endPage ; x ++) {
				if (currentPage == x) {
%>
					<li class="active"><span><%=x%></span></li>
<%
				} else {
%>
					<li><a href="<%=url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>"><%=x%></a></li>
<%
				}
			}
		}
		if ((currentPage + seed + 1) < pageSize) {
%>
			<li><span>...</span></li>
<%
		}
%>
<%
		if (currentPage == pageSize) {
%>
			<li class="active"><span><%=pageSize%></span></li>
<%
		} else {
%>
			<li><a href="<%=url%>?cp=<%=pageSize%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>"><%=pageSize%></a></li>
<%
		}
	}
%>
<%
		if (currentPage == pageSize) {
%>
			<li class="disabled"><span>&gt;&gt;</span></li>
<%
		} else {
%>
			<li><a href="<%=url%>?cp=<%=currentPage + 1%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyword%>&<%=label%>=<%=labelValue%>">&gt;&gt;</a></li>
<%
		}
%>
		</ul>
	</div>