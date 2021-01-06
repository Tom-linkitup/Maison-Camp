<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
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
							<li class=""><a href="<%=request.getContextPath()%>/back_end/emp/protected/lookYourSelf.jsp" class="">�ӤH���</a></li>
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
						src="<%=request.getContextPath()%>/back_end/emp/images/456.png"
						class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${empVO.emp_name}</p>
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
							href="<%=request.getContextPath()%>/back_end/emp/protected/viewEmp.jsp">���u�޲z</a></li>
						<li><a
							href="<%=request.getContextPath()%>/back_end/emp/protected/viewEmpFunc.jsp">���u�v���޲z</a></li>
						<li><a href="#">�t�ΰѼƺ޲z</a></li>
						<li><a
							href="<%=request.getContextPath()%>/back_end/emp/protected/viewFunc.jsp">�v���޲z</a></li>
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
			<div style="width: 100%; height: 42em; overflow: scroll;">
				<div style="height: 40px;">
					<nav class="navbar navbar-expand-lg navbar-light bg-light"
						style="display: block;">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back_end/emp/protected/addEmp.jsp">New
										employee</a></li>
								<li class="nav-item"><a class="nav-link disabled" href="#">#</a>
								</li>
							</ul>
							<form class="form-inline my-2 my-lg-0" action="">
								<input class="form-control mr-sm-2 selectEmpBySomeThing"
									type="text" placeholder="Search" aria-label="Search">
							</form>
						</div>
					</nav>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- ���~�������-->
					<div class="alert alert-danger" role="alert"
						style="margin-top: 30px;">
						<h4 class="alert-heading">�o�Ϳ��~! :</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}<br>
						</c:forEach>
					</div>
				</c:if>
				<table class="table table-striped" style="margin-top: 35px;">
					<!-- �˵������\��-->
					<thead>
						<tr>
							<th>���u�s��:</th>
							<th>���u�b��:</th>
							<th>���u�m�W:</th>
							<th>���u���A:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="empVO" items="${list}" varStatus="times">
							<tr class="colorRow">
								<th scope="row" class="id_info">${empVO.emp_id}</th>
								<td>${empVO.emp_user_id}</td>
								<td class="name_info">${empVO.emp_name}</td>
								<td><c:choose>
										<c:when test="${empVO.emp_status == '1'}">
								      	�b¾
								     </c:when>
										<c:otherwise>
							      		��¾
							     	 </c:otherwise>
									</c:choose></td>
								<td>
									<FORM METHOD="post" class="form"
										ACTION="<%=request.getContextPath()%>/emp/emp.do">
										<button type="submit" class="btn modify updateEmp"
											style="background-color: #c16c51; color: white">���u�ק�</button>
										<input type="hidden" name="emp_id" value="${empVO.emp_id}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
	<script src="<%=request.getContextPath()%>/back_end/js/backer.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	${alert}
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
					$(".selectEmpBySomeThing").keyup(
							function() {
								let id_info = $('.id_info');
								let name_info = $('.name_info');
								$(".colorRow").addClass('displayN');

								for (let i = 0; i < id_info.length; i++) {
									if (id_info[i].innerText.indexOf($(
											".selectEmpBySomeThing").val()
											.trim()) != -1
											| name_info[i].innerText.indexOf($(
													".selectEmpBySomeThing")
													.val().trim()) != -1) {
										id_info[i].parentNode.classList
												.remove('displayN');
										id_info[i].parentNode.classList
												.add('displayB');
									}
								}

							});
				});
		
		$(".updateEmp").click(function(event) {
			var btn = $(this);
			event.preventDefault();
			if (!boole) {
				swal("�v������ �L�k�ק�","", "error");	
			}else{
				btn.parent("FORM").submit();
			}
		});
	</script>

</body>
</html>

