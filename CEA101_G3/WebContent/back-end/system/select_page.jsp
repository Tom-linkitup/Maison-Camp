<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Item: Home</title>

</head>
<body bgcolor="white">
<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
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
        <b>輸入商品編號 (如I10001):</b>
        <input type="text" name="itemId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="itemId">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.itemId}">${itemVO.itemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="itemId">
         <c:forEach var="itemVO" items="${itemSvc.all}" > 
          <option value="${itemVO.itemId}">${itemVO.itemName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>員工管理</h3>

<ul>
  <li><a href='addItem.jsp'>Add</a> a new Item.</li>
</ul>
</body>
</html>