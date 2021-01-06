<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.func.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<body>
<%@ include file="/back-end/back-template/backIndex.file" %>
				<div>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/func/func.do">
						<div class="form-group">
							<label for="formGroupExampleInput">功能編號:</label> <input
								type="text" class="form-control" name="func_id"
								placeholder="功能編號">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">功能名稱:</label> <input
								type="text" class="form-control" name="func_name"
								placeholder="功能名稱">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">功能敘述:</label> <input
								type="text" class="form-control" name="func_info"
								placeholder="敘述功能用途">
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