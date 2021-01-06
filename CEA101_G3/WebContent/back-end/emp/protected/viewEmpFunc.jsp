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
		<style>
			.displayB {
				display: '';
			}
			
			.displayN {
				display: none;
			}
		</style>
	</head>
		<%@ include file="/back-end/back-template/backIndex.file" %>
			<div style="width: 100%; height: 42em; overflow: scroll;">
				<div style="height: 40px;">
					<nav class="navbar navbar-expand-lg navbar-light bg-light"
						style="display: block;">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back-end/emp/protected/addEmpFunc.jsp">New
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
	<%@ include file="/back-end/back-template/backIndex2.file" %>
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