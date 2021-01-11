<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actCategory.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActCategoryService actCategorySvc = new ActCategoryService();;
    List<ActCategoryVO> list = actCategorySvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有活動類別資料 - listAllAct.jsp</title>

</head>
<body>
<div id="content-1">
<table>
	<tr>
		<th>活動類別編號</th>
		<th>活動類別名稱</th>
		<th>修改</th>
	</tr>

	<c:forEach var="actCategoryVO" items="${list}">
		
		<tr>
			<td>${actCategoryVO.actCategoryId}</td>
			<td>${actCategoryVO.actCategoryName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actcategory/activityCategory.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actCategoryId"  value="${actCategoryVO.actCategoryId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>

</body>
</html>