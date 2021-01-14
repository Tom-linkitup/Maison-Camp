<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<jsp:useBean id="funcSvc" scope="page" class="com.func.model.FuncService" />

<%
	
%>

<!DOCTYPE html>
<html lang="en">
<body>
<%@ include file="/back-end/back-template/backIndex.file" %>
				<div class="container">
					<div class="row">
						<div class="col-sm-4">
							<img class="img-thumbnail"
								src="<%=request.getContextPath()%>/back-end/emp/images/member.png"
								style="margin: 10px 8px;">
							<div style="margin: 10px 8px;">
								<p>員工編號: ${loginEmpVO.emp_id}</p>
								<p>姓名: ${loginEmpVO.emp_name}</p>
								<p>帳號: ${loginEmpVO.emp_user_id}</p>
								<p>密碼: ${loginEmpVO.emp_user_pwd}</p>
								<p>狀態: ${loginEmpVO.emp_status}</p>
							</div>
								<FORM METHOD="post" class="form"
										ACTION="<%=request.getContextPath()%>/emp/emp.do">
										<button type="submit" class="btn btn-outline-secondary">修改個人資料</button>
										<input type="hidden" name="emp_id" value="${loginEmpVO.emp_id}">
										<input type="hidden" name="action" value="updateSelf">
									</FORM>
									
						</div>
						<div class="col-sm-6">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>權限名稱</th>
										<th>權限資訊</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="func" items="${funcList}" varStatus="times">
										<c:forEach var="funcVO" items="${funcSvc.all}">
											<c:if test="${func == funcVO.func_id}">
												<tr>
													<th scope="row">${times.count}</th>
													<td>${funcVO.func_name}</td>
													<td>${funcVO.func_info}</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
	${alert}
	<%@ include file="/back-end/back-template/backIndex2.file" %>
</body>
</html>