<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/back-template/backIndex.file" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ItemCategory: Home</title>

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
   <tr><td><h3>IBM ItemCategory: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Item: Home</p>

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
  <li><a href='listAllItemCategory.jsp'>List</a> all Items.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_category/itemCategory.do" >
        <b>輸入商品類別編號 (如I001):</b>
        <input type="text" name="itemCategoryId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="itemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_category/itemCategory.do" >
       <b>選擇商品類別編號:</b>
       <select size="1" name="itemCategoryId">
         <c:forEach var="itemCategoryVO" items="${itemCategorySvc.all}" > 
          <option value="${itemCategoryVO.itemCategoryId}">${itemCategoryVO.itemCategoryId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_category/itemCategory.do" >
       <b>選擇商品類別名稱:</b>
       <select size="1" name="itemCategoryId">
         <c:forEach var="itemCategoryVO" items="${itemCategorySvc.all}" > 
          <option value="${itemCategoryVO.itemCategoryId}">${itemCategoryVO.itemCategoryName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>員工管理</h3>

<ul>
  <li><a href='addItemCategory.jsp'>Add</a> a new Item.</li>
</ul>
</body>
</html>
<%@ include file="/back-end/back-template/backIndex2.file" %> 