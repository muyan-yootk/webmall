<%@ page pageEncoding="UTF-8" %>
<%	request.setCharacterEncoding("UTF-8") ;
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<h1>全局错误页</h1>
${errors}