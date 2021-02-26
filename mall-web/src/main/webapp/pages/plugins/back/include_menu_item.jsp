<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%!
	public static final String INDEX_URL = "pages/back/index.jsp" ;

	public static final String GOODS_ADD_URL = "pages/back/admin/goods/add_pre.action" ;
	public static final String GOODS_LIST_URL = "pages/back/admin/goods/list.action" ;

	public static final String MEMBER_LIST_URL = "pages/back/admin/member/list.action" ;
	public static final String ORDERS_LIST_URL = "pages/back/admin/orders/list.action" ;
%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="images/admin_set.png" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<p>后台管理</p>
			</div> 
		</div>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header"><i class="fa fa-slack"></i> 沐言商城</li>
			<li class="treeview ${param.mi==1 ? 'active' : ''}"><a href="<%=INDEX_URL%>"> <i
					class="fa fa-folder-open"></i> <span>商品管理</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li class="${param.msi==11 ?  'active' : ''}"><a href="<%=GOODS_ADD_URL%>"><i class="fa fa-pencil"></i>
							添加商品</a></li>
					<li class="${param.msi==12 ?  'active' : ''}"><a href="<%=GOODS_LIST_URL%>"><i class="fa fa-list"></i>
							商品列表</a></li>
				</ul></li>
			<li class="treeview ${param.mi==2 ? 'active' : ''}"><a href="<%=INDEX_URL%>"> <i class="fa  fa-folder-open"></i>
					<span>用户管理</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li class="${param.msi==21 ?  'active' : ''}"><a href="<%=MEMBER_LIST_URL%>"><i
							class="fa fa-users"></i> 用户列表</a></li>
				</ul></li>
			<li class="treeview ${param.mi==3 ? 'active' : ''}"><a href="<%=INDEX_URL%>"> <i class="fa  fa-folder-open"></i>
					<span>订单管理</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li class="${param.msi==31 ?  'active' : ''}"><a href="<%=ORDERS_LIST_URL%>"><i
							class="fa fa-file"></i> 订单列表</a></li>
				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>