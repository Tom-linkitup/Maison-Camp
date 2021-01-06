<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp_func.model.*"%>
<%@ page import="java.util.*"%>

<%
	EmpFuncService empFuncSvc = new EmpFuncService();
	List<EmpFuncVO> list = empFuncSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>露營管理後台</title>
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
	href="<%=request.getContextPath()%>/back_end/css/admin.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/context.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/style.css">
	
	<style>
		.displayB {
			display: '';
		}
		
		.displayN {
			display: none;
		}
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<header class="main-header">
		<a href="<%=request.getContextPath()%>/back_end/emp/protected/backer.jsp" class="logo"> <span class="logo-mini"><b>M</b></span>
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
							<li class=""><a href="<%=request.getContextPath()%>/back_end/emp/protected/lookYourSelf.jsp" class="">個人資料</a></li>
							<li><a
								href="<%=request.getContextPath()%>/emp/emp.do?action=logout">登出</a>
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
						src="<%=request.getContextPath()%>/back_end/emp/images/456.png"
						class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${empVO.emp_name}</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<ul class="sidebar-menu" data-widget="tree">
				<li class="header">管理員介面</li>
				<li class="treeview"><a href="#"> <i class="fa fa-desktop"></i>
						<span>前台頁面管理</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">最新消息管理</a></li>
						<li><a href="#">評論管理</a></li>
						<li><a href="#">促銷專案管理</a></li>
						<li><a href="#">廣告管理</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-cubes"></i>
						<span>露營後台管理</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a
							href="<%=request.getContextPath()%>/back_end/emp/protected/viewEmp.jsp">員工管理</a></li>
						<li><a
							href="<%=request.getContextPath()%>/back_end/emp/protected/viewEmpFunc.jsp">員工權限管理</a></li>
						<li><a href="#">系統參數管理</a></li>
						<li><a
							href="<%=request.getContextPath()%>/back_end/emp/protected/viewFunc.jsp">權限管理</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-user-circle"></i> <span>會員管理</span><span
						class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">會員資料管理</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-bed"></i>
						<span>訂房後台管理</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">房型管理</a></li>
						<li><a href="#">房間管理</a></li>
						<li><a href="#">住房訂單管理</a></li>
						<li><a href="#">修繕管理</a></li>
						<li><a href="#">入住管理</a></li>
						<li><a href="#">退房管理</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i
						class="fa fa-shopping-cart"></i> <span>商城後台管理</span><span
						class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">銷售統計管理</a></li>
						<li><a href="#">商品管理</a></li>
						<li><a href="#">商品訂單管理</a></li>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-plane"></i>
						<span>活動後台管理</span><span class="pull-right-container"> <i
							class="fa fa-angle-down pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="#">活動內容管理</a></li>
						<li><a href="#">活動報名管理</a></li>
						<li><a href="#">活動排程</a></li>
					</ul></li>
			</ul>
		</section>
	</aside>
	<div class="content-wrapper text-tab active">
		<section class="content container-fluid">
			<div style="width: 100%; height: 42em; overflow: scroll;">
				<div style="height: 40px;">
					<nav class="navbar navbar-expand-lg navbar-light bg-light"
						style="display: block;">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back_end/emp/protected/addEmpFunc.jsp">New
										employee function</a></li>
								<li class="nav-item"><a class="nav-link disabled" href="#">#</a>
								</li>
							</ul>

							<form class="form-inline my-2 my-lg-0" action="">
								<input class="form-control mr-sm-2 selectEmpFuncBySomeThing"
									type="text" placeholder="Search" aria-label="Search">
							</form>
						</div>
					</nav>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- 錯誤驗證顯示-->
					<div class="alert alert-danger" role="alert" style="margin-top: 30px;">
						<h4 class="alert-heading">發生錯誤! :</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}<br>
						</c:forEach>
					</div>
				</c:if>
				<table class="table table-striped" style="margin-top: 35px;">
					<!-- 檢視全部功能-->
					<thead>
						<tr>
							<th>員工編號:</th>
							<th>權限編號:</th>
							<th>權限名稱:</th>
							<th>權限資訊:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="empFuncVO" items="${list}" varStatus="times">
							<tr class="colorRow">
								<th scope="row" class="id_info">${empFuncVO.emp_id}</th>
								<td>${empFuncVO.func_id}</td>
								<c:forEach var="funcVO" items="${funcSvc.all}">
									<c:if test="${empFuncVO.func_id == funcVO.func_id}">
										<td class="funcName_info">${funcVO.func_name}</td>
										<td>${funcVO.func_info}</td>
									</c:if>
								</c:forEach>
								<td>
									<FORM METHOD="post" class="form"
										ACTION="<%=request.getContextPath()%>/emp_func/emp_func.do">
										<button class="btn deleteEmpFunc" style="background-color: #c16c51; color: white">刪除員工權限</button>
										<input type="hidden" name="emp_id" value="${empFuncVO.emp_id}">
										<input type="hidden" name="func_id" value="${empFuncVO.func_id}"> 
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div></div>
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
	<script src="<%=request.getContextPath()%>/back_end/js/backer.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


	<script>
		let boole = false;	
	</script>

	<c:forEach var="func" items="${funcList}" varStatus="times">
		<script>
			if (${func == 0002}) {
				boole = true;
			}
		</script>
	</c:forEach>

	<script>
		$(document).ready(
			function() {
				$(".selectEmpFuncBySomeThing").keyup(
					function() {
						let id_info = $('.id_info');
						let funcName_info = $('.funcName_info');
						$(".colorRow").addClass('displayN');

						for (let i = 0; i < id_info.length; i++) {
							if (id_info[i].innerText.indexOf($(
									".selectEmpFuncBySomeThing").val().trim()) != -1
									| funcName_info[i].innerText.indexOf($(
											".selectEmpFuncBySomeThing")
											.val().trim()) != -1) {
								id_info[i].parentNode.classList
										.remove('displayN');
								id_info[i].parentNode.classList
										.add('displayB');
							}
						}
					});
				});
	
		
		$(".deleteEmpFunc").click(function(event) {
			var btn = $(this);
			event.preventDefault();
			if (!boole) {
				swal("權限不足 無法修改","", "error");
			}else{
				swal({
					  title: "卻定要刪除嗎?",
					  text: "刪除後將無法復原!",
					  icon: "warning",
					  buttons: true,
					  dangerMode: true,
					})
					.then((willDelete) => {
					  if (willDelete) {
					    btn.parent("FORM").submit();
					  }
					});
			}
		});
	</script>

</body>
</html>