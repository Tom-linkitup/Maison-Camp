<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="backIndex.file" %> 
<html>
<head>
<meta charset="UTF-8">
<title>Item: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>
</head>
<body bgcolor="white">
<table id="table-1">
   <tr><td><h3>IBM Item: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Item: Home</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllItemPhoto.jsp'>List</a> all Items.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_photo/itemPhoto.do" >
        <b>��J�ӫ~�s�� (�pI10001):</b>
        <input type="text" name="itemPhotoId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="itemPhotoSvc" scope="page" class="com.item_photo.model.ItemPhotoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_photo/itemPhoto.do" >
       <b>��ܰӫ~�Ӥ��s��:</b>
       <select size="1" name="itemPhotoId">
         <c:forEach var="itemPhotoVO" items="${itemPhotoSvc.all}" > 
          <option value="${itemPhotoVO.itemPhotoId}">${itemPhotoVO.itemPhotoId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_photo/itemPhoto.do" >
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="itemPhotoId">
         <c:forEach var="itemPhotoVO" items="${itemPhotoSvc.all}" > 
          <option value="${itemPhotoVO.itemPhotoId}">${itemPhotoVO.itemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>���u�޲z</h3>

<ul>
  <li><a href='addItemPhoto.jsp'>Add</a> a new Item.</li>
</ul>
</body>
</html>
<%@ include file="backIndex2.file" %> 