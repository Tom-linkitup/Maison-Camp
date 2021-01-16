<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp_func.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<body>
<jsp:useBean id="funcSvc" class="com.func.model.FuncService" scope="page" />
<jsp:useBean id="empSvc" class="com.emp.model.EmpService" scope="page" />
<%@ include file="/back-end/back-template/backIndex.file" %>
				<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp_func/emp_func.do">
						<div class="form-group">
							<label for="formGroupExampleInput2">功能編號:</label> 
								<select name="emp_id">
									<c:forEach items="${empSvc.getAll()}" var="empVO">
									<option  value="${empVO.emp_id}">${empVO.emp_id} ${empVO.emp_name}</option>
									</c:forEach>
								</select>
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">功能編號:</label> 
								<select name="func_id">
									<c:forEach items="${funcSvc.getAll()}" var="funcVO">
									<option  value="${funcVO.func_id}">${funcVO.func_id} ${funcVO.func_name}</option>
									</c:forEach>
								</select>
						</div>
						<button type="submit" class="btn btn-lg btn-block" name="insert"
							style="background-color: #c16c51; color: white; margin-top: 30px;">提交</button>
						<input type="hidden" name="action" value="insert">
					</FORM>
				</div>
				<c:if test="${not empty errorMsgs}">
					<!-- 錯誤驗證顯示-->
					<div class="alert alert-danger" role="alert"
						style="margin-top: 30px">
						<h4 class="alert-heading">新增失敗:</h4>
						<c:forEach var="message" items="${errorMsgs}">
									${message}<br>
						</c:forEach>
					</div>
				</c:if>
<%@ include file="/back-end/back-template/backIndex2.file" %>
</body>
</html>