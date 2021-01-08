<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Item: Home</title>

</head>
<body bgcolor="white">
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
  <li><a href='listAllItemComment.jsp'>List</a> all Items.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="itemComment.do" >
        <b>��J���׽s�� (�pIC10001):</b>
        <input type="text" name="itemCommentId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="itemCommentSvc" scope="page" class="com.item_comment.model.ItemCommentService" />
   
  <li>
     <FORM METHOD="post" ACTION="itemComment.do" >
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="itemCommentId">
         <c:forEach var="itemCommentVO" items="${itemCommentSvc.all}" > 
          <option value="${itemCommentVO.itemCommentId}">${itemCommentVO.itemCommentId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="itemComment.do" >
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="itemCommentId">
         <c:forEach var="itemCommentVO" items="${itemCommentSvc.all}" > 
          <option value="${itemCommentVO.itemCommentId}">${itemCommentVO.itemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>���u�޲z</h3>

<ul>
  <li><a href='addItemComment.jsp'>Add</a> a new ItemComment.</li>
</ul>
</body>
</html>