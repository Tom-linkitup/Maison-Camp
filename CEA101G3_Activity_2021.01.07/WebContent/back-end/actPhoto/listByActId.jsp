<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actPhoto.model.*"%>
    
    
<%
	List<ActPhotoVO> list = (ArrayList<ActPhotoVO>) session.getAttribute("ActPhotoVOList");
	pageContext.setAttribute("list",list);
%>
    
<!DOCTYPE html>
<html>
<head>
<title>List by Act_Id</title>

<style>
	img{
	width: 200px;
	height: 200px;
	}
</style>

</head>
<body>

<table>
	<tr>
		<th>活動圖片編號</th>
		<th>活動編號</th>
		<th>圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="actPhotoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actPhotoVO.actPhotoId}</td>
			<td>${actPhotoVO.actId}</td>
			<td>
			
		
			<c:if test="${actPhotoVO.content != null}">	
			<img src="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do?action=showPhoto&photo=${actPhotoVO.actPhotoId}">
			</c:if>
			
			<c:if test="${actPhotoVO.content == null }">
				查無圖片
			</c:if>
			
			</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ACT_PHOTO_ID"  value="${actPhotoVO.actPhotoId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ACT_PHOTO_ID"  value="${actPhotoVO.actPhotoId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<h4><a href="<%= request.getContextPath()%>/back-end/actPhoto/actPhoto_select_page.jsp">回首頁</a></h4>

</body>
</html>