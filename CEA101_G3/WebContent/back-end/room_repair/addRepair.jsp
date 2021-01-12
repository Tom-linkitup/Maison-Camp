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
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

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
<form method="post" action="${pageContext.request.contextPath}/repair/repair.do" enctype="multipart/form-data">	
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
			<c:if test="${not empty errorMsgs}">
			 <p class="error" style="color:red; font-size:8px;">${errorMsgs.repair_info}</p> 
			</c:if>
			
			
			<P></P><BR><BR>
			<INPUT type="file"	id="file" name="repair_photo" accept="image/*" size="45" value="${repairVO.repair_photo}" onchange="loadImageFile(event)" />
			</p><img style="width:0px" id="preview" src="">
			
			<input  id="status" class="input-beautify" type="text" name="status"  value="0" hidden>
			</tr>
			
			<td><input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增修繕</button>
				
				</td>
				</tr>
		</div>
	</form>
</body>
<!-- 前端預覽圖片 -->
<script>
	// 	function init() {
	// 		let photo = document.getElementsByName('sou_photo')[0];
	// 		photo.addEventListener('change', function(e) {
	// 			let files = e.target.files; // 檔案的基本資訊，包括：檔案的名稱、大小與文件型態
	// 			console.log(files[0])
	// 			if (files && files[0]) {
	// 				let file = files[0];
	// 				if (file.type.indexOf('image') > -1) {
	// 					let reader = new FileReader(); // new a FileReader
	// 					reader.onload = function(e) { // 註冊FileReader檔案讀取load的事件 (3)
	// 						let img = document.createElement("img");
	// 						img.setAttribute("src", e.target.result);
	// 						img.style.height = '200px';
	// 						let node = document.getElementById("preview"); // remove all children
	// 						while (node.lastChild) {
	// 							node.removeChild(node.lastChild);
	// 						}
	// 						node.appendChild(img);
	// 					}
	// 					reader.readAsDataURL(file); // trigger onload event
	// 				} else {
	// 					alert('Please upload an image file. ');
	// 				}
	// 			}
	// 		});
	// 	}
	// 	window.onload = init;
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$("#file").change(
			function() {
				readURL(this);
				$("#preview").css("width", "200px").css("height", "150px").css(
						"margin-bottom", "20px");
			});
</script>
</html>