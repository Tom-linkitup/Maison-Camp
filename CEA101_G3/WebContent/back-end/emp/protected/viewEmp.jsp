<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style>
.displayB {
	display: '';
}

.displayN {
	display: none;
}
</style>
</head>
<%@ include file="/back-end/back-template/backIndex.file"%>
<div style="width: 100%; height: 42em; overflow: scroll;">
	<div style="height: 40px;">
		<nav class="navbar navbar-expand-lg navbar-light bg-light"
			style="display: block;">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/back-end/emp/protected/addEmp.jsp">New
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
				<th>員工帳號:</th>
				<th>員工姓名:</th>
				<th>員工狀態:</th>
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
								      	在職
								     </c:when>
							<c:otherwise>
							      		離職
							     	 </c:otherwise>
						</c:choose></td>
					<td>
						<FORM METHOD="post" class="form"
							ACTION="<%=request.getContextPath()%>/emp/emp.do">
							<button type="submit" class="btn modify updateEmp"
								style="background-color: #c16c51; color: white">員工修改</button>
							<input type="hidden" name="emp_id" value="${empVO.emp_id}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="/back-end/back-template/backIndex2.file"%>
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
				swal("權限不足 無法修改","", "error");	
			}else{
				btn.parent("FORM").submit();
			}
		});
	</script>

</body>
</html>


