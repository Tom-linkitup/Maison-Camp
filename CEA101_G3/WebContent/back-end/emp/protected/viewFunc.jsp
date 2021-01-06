<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.func.model.*"%>
<%@ page import="java.util.*"%>

<%
	FuncService funcSvc = new FuncService();
	List<FuncVO> list = funcSvc.getAll();
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
<%@ include file="/back-end/back-template/backIndex.file" %>
			<div style="width: 100%; height: 42em; overflow: scroll;">
				<div style="height: 40px;">
					<nav class="navbar navbar-expand-lg navbar-light bg-light"
						style="display: block;">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item"><a class="nav-link"
									href="<%=request.getContextPath()%>/back-end/emp/protected/addFunc.jsp">New
										function</a></li>
								<li class="nav-item"><a class="nav-link disabled" href="#">#</a>
								</li>
							</ul>

							<form class="form-inline my-2 my-lg-0" action="">
								<input class="form-control mr-sm-2 selectFuncBySomeThing"
									type="text" placeholder="Search" aria-label="Search">
							</form>
						</div>
					</nav>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- 錯誤驗證顯示-->
					<div class="alert alert-danger" role="alert" style="margin-top : 30px;">
						<h4 class="alert-heading">刪除失敗:</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}
								</c:forEach>
					</div>
				</c:if>
				<table class="table table-striped" style="margin-top: 35px;">
					<!-- 檢視全部功能-->
					<thead>
						<tr>
							<th>#</th>
							<th>功能編號</th>
							<th>功能名稱</th>
							<th>功能敘述</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="funcVO" items="${list}" step="1" varStatus="times">

							<tr class="colorRow">
								<th scope="row">${times.count}</th>
								<td class="id_info">${funcVO.func_id}</td>
								<td class="funcName_info">${funcVO.func_name}</td>
								<td>${funcVO.func_info}</td>
								<td>
									<FORM METHOD="post" class="form"
										ACTION="<%=request.getContextPath()%>/func/func.do"
										style="margin-bottom: 0px;">
										<button type="submit" class="btn deleteFunc"
											style="background-color: #c16c51; color: white">刪除功能</button>
										<input type="hidden" name="func_id" value="${funcVO.func_id}">
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 檢視全部功能-->
			</div>
<%@ include file="/back-end/back-template/backIndex2.file" %>

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
				$(".selectFuncBySomeThing").keyup(
					function() {
						let id_info = $('.id_info');
						let funcName_info = $('.funcName_info');
						$(".colorRow").addClass('displayN');

						for (let i = 0; i < id_info.length; i++) {
							if (id_info[i].innerText.indexOf($(
									".selectFuncBySomeThing").val().trim()) != -1
									| funcName_info[i].innerText.indexOf($(
											".selectFuncBySomeThing")
											.val().trim()) != -1) {
								id_info[i].parentNode.classList
										.remove('displayN');
								id_info[i].parentNode.classList
										.add('displayB');
							}
						}
					});
				});
	
		$(".deleteFunc").click(function(event) {
			var btn = $(this);
			event.preventDefault();
			if (!boole) {
				swal("權限不足 無法修改","", "error");
				return false;
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