<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.emp.model.*"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<%
	RepairService repairSvc = new RepairService();
	List<RepairVO> list = repairSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-1">
		<h2 style="text-align:center; margin-bottom:20px;">維修資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>維修編號</th>
				<th>房間編號</th>
				<th>員工編號</th>
				<th>修繕內容</th>
				<th>修繕狀態</th>
				<th>更新修繕</th>
				<th>修繕刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			
			<c:forEach var="repairVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${repairVO.repair_id}</td>
				<td>${repairVO.room_id}</td>
				<td><c:forEach var="empVO" items="${empSvc.all}"><c:if test="${repairVO.emp_id==empVO.emp_id}">${empVO.emp_id}【${empVO.emp_name}】	</c:if></c:forEach></td>					
				<td>${repairVO.repair_info}</td>
				<td><c:choose>
					<c:when test="${repairVO.status == '0'}">
						未完成
					</c:when>
					<c:otherwise>
						已完成
					</c:otherwise>
					</c:choose></td>
				
				
				<td>
				<input type="hidden" name="repair_id" value="${repairVO.repair_id}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="${pageContext.request.contextPath}/repair/repair.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="repair_id" value="${repairVO.repair_id}">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
			<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/repair/repair.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>房型修改</td></tr>
					<tr><td>維修編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="repair_id" class="input-beautify" type="text" name="repair_id" readonly></td></tr>			
					<tr><td>房間代號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_id" class="input-beautify" type="text" name="room_id" readonly ></td></tr>
					<tr><td>員工編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="emp_id" class="input-beautify" type="text" name="emp_id" readonly></td></tr>
					<tr><td>維修內容：</td><td><input id="repair_info" class="input-beautify" type="text" name="repair_info" required></td></tr>
					<tr><td>維修狀態：</td>
					<td>
						<select id="status" class="input-beautify" type="text" name="status" required>
							<option value="0">未完成</option>
							<option value="1">已完成</option>
						</select>
						</td>
					<tr>
					<td><input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">		
					</TD></TR>
				</table>
				
			</form>
		</div>
	</div>
	<script>		
		$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#repair_id").val(children.eq(0).text());
			$("#room_id").val(children.eq(1).text());
			$("#emp_id").val(children.eq(2).text());
			$("#repair_info").val(children.eq(3).text());
			$("#status").val(children.eq(4).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
		
		
		$(".delete").click(function() {
			window.alert("不可刪除");
			
			location.reload();
		})
	</script>	

</body>

</html>