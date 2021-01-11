<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actPhoto.model.*"%>

<%
ActPhotoVO actPhotoVO = (ActPhotoVO ) request.getAttribute("ActPhotoVO");
%>




<!DOCTYPE html>
<html>
<head>
<title>修改圖片資料</title>

<script>
        function preView(e){

   	 	let myFile = document.getElementById("myFile");

    	if(e.files){

    		let file = e.files[0];
    		let reader = new FileReader();
    		reader.addEventListener('load', function(e){
    			console.log(e.target.result);
    			let img = document.getElementById("pre")
    			img.src = e.target.result;

    		});

    		reader.readAsDataURL(file);

    	}else{
//     		 alert('請上傳圖片檔案');
    	}
    }
</script>

<style>
	img{
	width:100px;
	height:100px;
	}
	
	td{
		width:100px;
		height:100px;
	}
</style>



</head>
<body>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<th>活動圖片編號</th>
		<th>活動編號</th>
		<th>圖片</th>
		<th>上傳的圖片<th>
		<th>修改</th>
	</tr>

	<tr>
		<td>
			<%=actPhotoVO.getActPhotoId()%>
		</td>
	
		<td>
			<input type="TEXT" name="ACT_ID" size="20" value="<%=actPhotoVO.getActId()%>" />
		</td>
	
		<td>
		<c:if test="${ActPhotoVO.content != null}">	
			<img src="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do?action=showPhoto&photo=${ActPhotoVO.actPhotoId}">
		</c:if>
			
		<c:if test="${ActPhotoVO.content == null }">
				查無圖片
		</c:if>
		</td>
		
		<td>
			<img id="pre">
		</td>
			
		<td>
			<input type="file" name="photo" id="myFile" onchange="preView(this)"/>
		</td>
	</tr>
</table>




<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ACT_PHOTO_ID" value="<%=actPhotoVO.getActPhotoId()%>">


<input type="submit" value="送出修改"></FORM>

<h4><a href="<%= request.getContextPath()%>/back-end/actPhoto/actPhoto_select_page.jsp">回首頁</a></h4>

</body>
</html>