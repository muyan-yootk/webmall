<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setAttribute("basePath", basePath) ;
%>
<base href="<%=basePath%>/">
<title>沐言商城（沐言科技 —— 新时代软件教育领导品牌）</title>
<link rel="shortcut icon"href="images/logo.ico">
<meta http-equiv="keywords" content="李兴华 java python go 大数据"/>
<meta http-equiv="description" content="沐言科技（www.yootk.com）"/>
<meta name="viewport" content="width=device-width,initial-scale=1"> 
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="jquery/additional-methods.min.js"></script>
<script type="text/javascript" src="jquery/Message_zh_CN.js"></script>
<script type="text/javascript" src="js/yootk.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />