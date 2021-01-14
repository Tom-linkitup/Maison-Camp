<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> list = roomTypeSvc.getAllRT();
	pageContext.setAttribute("list", list);
	
	String noSelect = (String)request.getAttribute("noSelect");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${not empty errorPhotoMsgs}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("未選擇房型","請修正錯誤！","error");
	</script>
</c:if>
</head>
<style>
.photo-form {
	position:relative;
	margin-top: 10px;
	margin-left: 20px;
	width: 1100px;
	height: 450px;
	border: 4px dashed gray;
}

.photo-form #preview {
	width: 100%;
	height: 100%;
	text-align: center;
	line-height: 200px;
	color: gray;
	font-family: Arial;
}

.photo-form input {
	position:absolute;
	width: 100%;
	height: 100%;
	outline: none;
	opacity: 0;
}

.photo-form button {
	margin: 0;
	color: #fff;
	background: #16a085;
	border: none;
	width: 1100px;
	height: 45px;
	margin-top: 10px;
	margin-left: -5px;
	border-radius: 4px;
	border-bottom: 4px solid #117A60;
	transition: all .2s ease;
	outline: none;
}

.photo-form button:hover {
	background: #149174;
	color: #0C5645;
}

.photo-form button:active {
	border: 0;
}
</style>
<body>
	<div id="content-2">
		<form class="photo-form" action="${pageContext.request.contextPath}/RoomPhoto.do" method="POST" enctype="multipart/form-data">
		<div class="" style="margin-top:-30px; padding-bottom:7px;">
			<span>請選擇房型：</span>
			<select name="room_category_id">
					<option value="noSelectRoomType">請選擇</option>
				<c:forEach var="room" items="${list}">
					<option value="${room.room_category_id}">${room.room_category_id}</option>
				</c:forEach>
			</select>	
		</div>
			<input type="file" name="roomPhoto" multiple required>
			<div id="preview">拖曳照片或直接點擊區塊</div>
			<input type="hidden" name="action" value="upload">
			<button type="submit">上傳</button>
		</form>
	</div>
<script>
$(document).ready(function(){
	  $('.photo-form input').change(function(e){
		$("#preview").text("");
		let preview = $("#preview");
		let files = e.target.files
        for(let i = 0; i < files.length; i++){
            let file = files[i]
            if(file.type.indexOf("image") >= 0){
                let reader = new FileReader()
                reader.addEventListener("load", (ex) => {
                    let img =  document.createElement("img")
                    img.setAttribute("width", "200px")
                    img.setAttribute("height", "150px")
                    img.src = ex.target.result
                    img.style.marginLeft = "10px"
                    preview.append(img);
                })
                reader.readAsDataURL(file)
            }
        }
	  });
});
</script>
</body>
</html>