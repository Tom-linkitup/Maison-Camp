<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>IBM ActCategory: Home</title>

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
   <tr><td><h3>IBM ActCategory: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM ActCategory: Home</p>

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
  <li><a href='<%= request.getContextPath() %>/back-end/actcategory/listAllActCategory.jsp'>List</a> all ActCategorys.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/actcategory/activityCategory.do" >
        <b>輸入活動類別編號 (如ACT_CATEGORY1):</b>
        <input type="text" name="actCategoryId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="actCategorySvc" scope="page" class="com.actCategory.model.ActCategoryService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/actcategory/activityCategory.do" >
       <b>選擇活動類別編號:</b>
       <select size="1" name="actCategoryId">
         <c:forEach var="actCategoryVO" items="${actCategorySvc.all}" > 
          <option value="${actCategoryVO.actCategoryId}">${actCategoryVO.actCategoryId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/actcategory/activityCategory.do" >
       <b>選擇活動類別名稱:</b>
       <select size="1" name="actCategoryId">
         <c:forEach var="actCategoryVO" items="${actCategorySvc.all}" > 
          <option value="${actCategoryVO.actCategoryId}">${actCategoryVO.actCategoryName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>活動類別管理</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/back-end/actcategory/addActCategory.jsp'>Add</a> a new ActCategory.</li>
</ul>

</body>
</html>