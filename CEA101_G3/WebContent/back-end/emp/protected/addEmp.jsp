<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<!DOCTYPE html>
<html lang="en">
<body>
<%@ include file="/back-end/back-template/backIndex.file" %>
				<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do">
						<div class="form-group">
							<div class="form-group">
								<label for="formGroupExampleInput2">員工姓名:</label> <input
									type="text" class="form-control" name="emp_name">
							</div>
							<label for="formGroupExampleInput">員工帳號:</label> <input
								type="text" class="form-control" name="emp_user_id">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">員工密碼:</label> <input
								type="password" class="form-control" name="emp_user_pwd">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">員工狀態:</label> 
							<select class="custom-select" name="emp_status">
								<option value="0">0(已離職)</option>
								<option value="1">1(在職)</option>
								<option value="2">2(留職停薪)</option>
							</select>
						</div>
						<button type="submit" class="btn btn-lg btn-block"
							style="background-color: #c16c51; color: white; margin-top: 30px;">提交</button>
						<input type="hidden" name="action" value="insert">
					</FORM>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- 錯誤驗證顯示-->
					<div class="alert alert-danger" role="alert" style="margin-top : 30px">
						<h4 class="alert-heading">新增失敗:</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}<br>
						</c:forEach>
					</div>
				</c:if>
<%@ include file="/back-end/back-template/backIndex2.file" %>
</body>
</html>