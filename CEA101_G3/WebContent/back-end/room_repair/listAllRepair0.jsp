<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.repair.model.*"%>
<%@ page import="com.emp.model.*"%>
<%
RepairService repairSvc = new RepairService();
List<RepairVO> list = repairSvc.getStatus0();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-4">
		<h2 style="text-align:center; margin-bottom:20px;">維修資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>維修編號</th>
				<th>房間編號</th>
				<th>員工編號</th>
				<th>修繕內容</th>
				<th>修繕圖片</th>
				<th>修繕狀態</th>
				<th>更新修繕</th>
				
			</tr>
			<%@ include file="page1.file"%>
			
			<c:forEach var="repairVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${repairVO.repair_id}</td>
				<td>${repairVO.room_id}</td>
				<td><c:forEach var="empVO" items="${empSvc.all}"><c:if test="${repairVO.emp_id==empVO.emp_id}">${empVO.emp_id}【${empVO.emp_name}】	</c:if></c:forEach></td>			
				<td>${repairVO.repair_info}</td>
				
				<td><div id="r"><img src="${pageContext.request.contextPath}/repair/repair.do?repair_id=${repairVO.repair_id}&action=getRepairPhoto"></div></td>	
				
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
			    <button class="edit0 btn btn-info" type="submit">修改</button>
				</td>
				
			</tr>
			</c:forEach>	
		</table>
		<div id="lightBox0" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/repair/repair.do" enctype="multipart/form-data">
				<table align="right" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>房型修改</td></tr>
					<tr><td>維修編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="repair_id0" class="input-beautify" type="text" name="repair_id" readonly></td></tr>			
					<tr><td>房間代號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_id0" class="input-beautify" type="text" name="room_id" readonly ></td></tr>
					<tr><td>員工編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="emp_id0" class="input-beautify" type="text" name="emp_id" readonly></td></tr>
					<tr><td>維修內容：</td><td><input id="repair_info0" class="input-beautify" type="text" name="repair_info" required></td></tr>
					<tr><td>維修狀態：</td>
					<td>
						<select id="status" class="input-beautify" type="text" name="status" required>
							<option value="0">未完成</option>
							<option value="1">已完成</option>
						</select>
						</td>
					<tr>
					
					
					<tr><td>維修圖片：</td><td><input id="repair_photo0" class="input-beautify" type="file" name="repair_photo" required accept="image/*" ></td></tr><BR>
					
					
					<td><input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel0" value="取消">		
					</TD></TR>
				</table>
				
			</form>
		</div>
	</div>
	<script>		
		$(".edit0").click(function() {
			$("#lightBox0").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#repair_id0").val(children.eq(0).text());
			$("#room_id0").val(children.eq(1).text());
			$("#emp_id0").val(children.eq(2).text());
			$("#repair_info0").val(children.eq(3).text());
			$("#status").val(children.eq(4).text());
			
			$("#repair_photo0").val(children.eq(5).attr("img"));
			document.getElementById("repair_photo0").innerHTML = document.getElementById("r");
			
			
		})
		
		$("#btnEditCancel0").click(function() {
			$("#lightBox0").css("display","none");
		})
		
		
	</script>	

</body>

</html>