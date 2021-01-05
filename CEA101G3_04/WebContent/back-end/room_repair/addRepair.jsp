<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>

<% 
	RepairVO repairVO = (RepairVO) request.getAttribute("remain");
	String insertSuccess = (String)request.getAttribute("insertSuccess");
	String repeat = (String)request.getAttribute("repeat");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:if test="${not empty errorMsgs}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("新增失敗","請修正錯誤！","error");	
	</script>		
	</c:if>
	<c:if test="${repeat == 'repeat'}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("編號重複","請修正錯誤！","error");
	</script>
	</c:if>
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-2").prop("checked",true);
			swal("新增成功", "請查看修繕編號", "success");
		</script>
	</c:if>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:forEach var="message" items="${errorMsgs}">
	</c:forEach>
<form method="post" action="${pageContext.request.contextPath}/repair/repair.do">	
<div id="content-2">
				
<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
	<tr><td>房間編號：</td><td><select  name="room_id" class="input-beautify">
			<c:forEach var="roomVO" items="${roomSvc.allRM}">
				<option value="${roomVO.room_id}" 'selected':'' } >${roomVO.room_id}
			</c:forEach></select>
	     </td></tr><br><br>
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />		
	<tr><td>員工編號：</td><td><select  name="emp_id" class="input-beautify">
			<c:forEach var="empVO" items="${empSvc.all}">
				<option value="${empVO.emp_id}" 'selected':'' } >${empVO.emp_name}(${empVO.emp_id}) 
			</c:forEach>
</select></td></tr><br><br>
	<tr><td>維修資訊：</td><input class="input-beautify" type="text" name="repair_info" value="">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.repair_info}</p>
			<input  id="status" class="input-beautify" type="text" name="status"  value="0" hidden>
			</tr>
			<td><input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增修繕</button>
				
				</td>
				</tr>
		</div>
	</form>
</body>
</html>