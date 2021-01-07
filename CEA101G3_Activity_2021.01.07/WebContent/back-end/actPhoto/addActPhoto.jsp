<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actPhoto.model.*"%>
<%
	ActPhotoVO actPhotoVO = (ActPhotoVO) request.getAttribute("ActPhotoVO");
%>
<!DOCTYPE html>
<html>

<head>
    <title>新增圖片</title>
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
    		 alert('請上傳圖片檔案');
    	}
    }
    </script>
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
<div id="content-4">
    <FORM METHOD="post" ACTION="<%= request.getContextPath()%>/actPhoto/ActPhotoServlet.do" name="form1" enctype="multipart/form-data">
        <table>
            <tr>
                <td>活動編號:</td>
                <td><input type="TEXT" name="ACT_ID" size="45" value="<%= (actPhotoVO==null)? "" : actPhotoVO.getActId()%>" placeholder="請輸入活動編號" /></td>
            </tr>
            <tr>
                <td>活動圖片:</td>
                <td>
                    <input name="photo" type="file" id="myFile" onchange="preView(this)">
                </td>
            </tr>
        </table>
        <img id="pre">
        <br>
        <input type="hidden" name="action" value="insert">
        <input type="submit" value="送出新增">
    </FORM>
    <h4><a href="<%= request.getContextPath()%>/back-end/actPhoto/actPhoto_select_page.jsp">回首頁</a></h4>
 </div>
</body>

</html>