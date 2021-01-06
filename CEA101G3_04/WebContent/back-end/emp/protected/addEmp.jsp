<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�S��޲z��x</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.1.2/collection/icon/icon.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/admin.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/context.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/style.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<header class="main-header">
		<a href="index2.html" class="logo"> <span class="logo-mini"><b>M</b></span>
			<span class="logo-lg"><b>Maison </b> Camp</span>
		</a>
		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="push-menu"
				role="button"> <span class="sr-only">Toggle navigation</span>
			</a>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown user user-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <span
							class="hidden-xs">${empVO.emp_name}</span>
					</a>
						<ul class="dropdown-menu">
							<li class=""><a href="<%=request.getContextPath()%>/back-end/emp/lookYourSelf.jsp" class="">�ӤH���</a></li>
							<li><a
								href="<%=request.getContextPath()%>/emp/emp.do?action=logout">�n�X</a>
							</li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
	<aside class="main-sidebar">
		<section class="sidebar">
			<div class="user-panel">
				<div class="pull-left image">
					<img
						src="<%=request.getContextPath()%>/back-end/emp/images/456.png"
						class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${loginEmpVO.emp_name}</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu" data-widget="tree">
				<li class="header">�޲z������</li>
				<li class="treeview"><a href="#"> <i class="fa fa-desktop"></i>
						<span>�e�x�����޲z</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">�̷s�����޲z</a></li>
						<li><a href="#">���׺޲z</a></li>
						<li><a href="#">�P�P�M�׺޲z</a></li>
						<li><a href="#">�s�i�޲z</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-cubes"></i>
						<span>�S���x�޲z</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a
							href="<%=request.getContextPath()%>/back-end/emp/protected/viewEmp.jsp">���u�޲z</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/emp/protected/viewEmpFunc.jsp">���u�v���޲z</a></li>
						<li><a href="#">�t�ΰѼƺ޲z</a></li>
						<li><a href="<%=request.getContextPath()%>/back-end/emp/protected/viewFunc.jsp">�v���޲z</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-user-circle"></i> <span>�|���޲z</span><span
						class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">�|����ƺ޲z</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-bed"></i>
						<span>�q�Ы�x�޲z</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">�Ы��޲z</a></li>
						<li><a href="#">�ж��޲z</a></li>
						<li><a href="#">���Эq��޲z</a></li>
						<li><a href="#">��µ�޲z</a></li>
						<li><a href="#">�J���޲z</a></li>
						<li><a href="#">�h�к޲z</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-shopping-cart"></i> <span>�ӫ���x�޲z</span><span
						class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">�P��έp�޲z</a></li>
						<li><a href="#">�ӫ~�޲z</a></li>
						<li><a href="#">�ӫ~�q��޲z</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-plane"></i>
						<span>���ʫ�x�޲z</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">���ʤ��e�޲z</a></li>
						<li><a href="#">���ʳ��W�޲z</a></li>
						<li><a href="#">���ʱƵ{</a></li>
					</ul></li>
			</ul>
		</section>
	</aside>
	<div class="content-wrapper text-tab active">
		<section class="content container-fluid">
			<div style="width: 100%; height: 100%;">
				<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do">
						<div class="form-group">
							<div class="form-group">
								<label for="formGroupExampleInput2">���u�m�W:</label> <input
									type="text" class="form-control" name="emp_name">
							</div>
							<label for="formGroupExampleInput">���u�b��:</label> <input
								type="text" class="form-control" name="emp_user_id">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">���u�K�X:</label> <input
								type="password" class="form-control" name="emp_user_pwd">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">���u���A:</label> 
							<select class="custom-select" name="emp_status">
								<option value="0">0(�w��¾)</option>
								<option value="1">1(�b¾)</option>
								<option value="2">2(�d¾���~)</option>
							</select>
						</div>
						<button type="submit" class="btn btn-lg btn-block"
							style="background-color: #c16c51; color: white; margin-top: 30px;">����</button>
						<input type="hidden" name="action" value="insert">
					</FORM>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- ���~�������-->
					<div class="alert alert-danger" role="alert" style="margin-top : 30px">
						<h4 class="alert-heading">�s�W����:</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}<br>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</section>
	</div>
	<footer class="main-footer">
		<strong>Copyright &copy; 2020<a href="#"> Maison Camp</a>.
		</strong> All rights reserved.
	</footer>

	<script src="https://code.jquery.com/jquery-1.12.4.min.js"
		integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.3/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/backer.js"></script>
</body>
</html>