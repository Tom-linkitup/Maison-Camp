<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>


<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
								<label for="formGroupExampleInput">員工編號:</label> 
									<h3><%=empVO.getEmp_id()%></h3>
							
								<label for="formGroupExampleInput">員工姓名:</label>
									<h3><%=empVO.getEmp_name()%></h3>
									
								<label for="formGroupExampleInput">員工帳號:</label><input
									type="text" class="form-control" name="emp_user_id" value="<%=empVO.getEmp_user_id()%>">
								
								<label for="formGroupExampleInput">員工密碼:</label> <input
									type="password" class="form-control" name="emp_user_pwd" value="<%=empVO.getEmp_user_pwd()%>">
									
								<label for="formGroupExampleInput2">員工狀態:</label>
								 <select class="custom-select" name="emp_status" disabled>
									<option value="0">0(已離職)</option>
									<option value="1" selected>1(在職)</option>
								</select>
							</div>
						</div>
						<input type="hidden" name="emp_id" value="<%=empVO.getEmp_id()%>">
						<input type="hidden" name="emp_name" value="<%=empVO.getEmp_name()%>">
<%-- 						<input type="hidden" name="emp_name" value="<%=empVO.getEmp_user_id()%>"> --%>
<%-- 						<input type="hidden" name="emp_name" value="<%=empVO.getEmp_status()%>"> --%>
						<input type="hidden" name="action" value="updateS">
						<button type="submit" class="btn btn-lg btn-block"
							style="background-color: #c16c51; color: white; margin-top: 30px;">提交</button>
						
					</FORM>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- 錯誤驗證顯示-->
					<div class="alert alert-danger" role="alert" style="margin-top : 30px">
						<h4 class="alert-heading">修改失敗:</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}<br>
								</c:forEach>
					</div>
				</c:if>
<%@ include file="/back-end/back-template/backIndex2.file" %>
</body>
</html>