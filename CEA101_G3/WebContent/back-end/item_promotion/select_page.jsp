<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.json.JSONObject"%>
<%@ page import="com.itempromotion.model.*"%>

<html>
<head>
<title>item_promotion HOME</title>
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

<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>itempromotion: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Itempromotion: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back_end/item_promotion/listAllItemPromotion.jsp'>List</a> all ItemPromotion.<br><br></li> 
<!--   單一查詢 -->
    <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_promotion/item_promotion.do" >
        <b>輸入訂單編號 (如IP10001):</b>
        <input type="text" name="item_promotion_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="itemPromotionSvc" scope="page" class="com.itempromotion.model.ItemPromotionService"/>
  
  <li>
  	
  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_promotion/item_promotion.do">
  		<b>請選擇商品促銷編號:</b>
  		<select size="1" name="item_promotion_id">
  		<c:forEach var="itemPromotionVO" items="${itemPromotionSvc.all}" >
  			<option value="${itemPromotionVO.item_promotion_id}">${itemPromotionVO.item_promotion_id}
  		</c:forEach>
  		</select>
  		<input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
  	</FORM>
  </li>
 
  
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_promotion/item_promotion.do">
  		<b>請選擇商品促銷編號:</b>
  		<select size="1" name="item_promotion_id">
  		<c:forEach var="itemPromotionVO" items="${itemPromotionSvc.all}" >
  			<option value="${itemPromotionVO.item_promotion_id}">${itemPromotionVO.item_id}
  		</c:forEach>
  		</select>
  		<input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
  	</FORM>
  </li>
 </ul> 
 
 <h3>訂單管理</h3>

<ul>
  <li><a href='addItemPromotion.jsp'>Add</a> a new Shop Order.</li>
</ul>
</body>
</html>