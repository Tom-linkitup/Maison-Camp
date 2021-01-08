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
  <li><a href='listAllSystem.jsp'>List</a> all Items.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/system/system.do" >
        <b>��J�ӫ~�s�� (�pI10001):</b>
        <input type="text" name="itemId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" >
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="itemId">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.itemId}">${itemVO.itemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" >
       <b>��ܰӫ~�W��:</b>
       <select size="1" name="itemId">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.itemId}">${itemVO.itemName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<ul>
  <li><a href='addItem.jsp'>Add</a> a new Item.</li>
</ul>
</body>
</html>