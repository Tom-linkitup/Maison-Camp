<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ItemService itemSvc = new ItemService();
    List<ItemVO> list = itemSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="itemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<html>
<head>

<title>所有商品資料</title>
</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/item/select_page.jsp"><i class="fas fa-home"></i></i>回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品資訊</th>
		<th>商品價格</th>
		<th>商品狀態</th>
		<th>商品類別編號</th>
		<th>修改</th>
		<th>刪除</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${itemVO.itemId}</td>
			<td>${itemVO.itemName}</td>
			<td>${itemVO.itemInfo}</td>
			<td>${itemVO.itemPrice}</td>
			<td>${itemVO.itemStatus}</td> 
			<td>
				${itemCategorySvc.getOneItemCategory(itemVO.itemCategoryId).itemCategoryName} 
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="itemId"  value="${itemVO.itemId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="itemId"  value="${itemVO.itemId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
	<%@ include file="page2.file" %> 
</body>
</html>