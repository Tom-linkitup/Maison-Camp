<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.actPhoto.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有活動資料 - listAllAct.jsp</title>
<style>
* {
	box-sizing: border-box;
}

div#demo {
	text-align: center;
}

div.list {
	border: 1px solid blue;
	border-radius: 3em;
	margin: 20px auto;
}

div.left {
	display: inline-block;
	margin: auto;
	width: 30%;
	vertical-align: middle;
}

div.right {
	display: inline-block;
	margin: auto 20px;
	width: 50%;
	vertical-align: middle;
}

div.sub {
	display: inline-block;
	margin: auto 20px;
	width: 10%;
	vertical-align: middle;
}

div.title {
	text-align: center;
	width: auto;
	height: auto;
	margin: 0px;
}

div.content {
	margin: 0px;
	width: auto;
	height: auto;
}

div.left img {
	border-radius: 50%;
	width:300px;
	height:250px;
	filter: drop-shadow(5px 5px 5px #333);
}

h1 {
	margin: 0px;
}

div.search {
	width: 100%;
	left: 20%;
	background-color: #ea5514;
	display: inline-block;
}

div.formGroup {
	display: inline-block;
	left: 15px;
	margin: 0px;
	padding: 0px;
	vertical-align: bottom;
}

form.myForm {
	vertical-align: middle;
	display: inline-block;
}

div.sellList_card{
    height: 46px;
	border-bottom: 1px solid #dedede;
}
div.sellList_card span{
	float:left;
	text-align: center;
	color: #929292;
	font-size: 26px;
    font-family: tahoma,arial,sans-serif;	
}
.sc-span1 {
    width: 34%;
}
.sc-span2 {
    width: 50%;
}
.sc-span3 {
    width: 14%;
}

</style>


</head>
<body bgcolor='white'>

	<jsp:include page="/html/top.jsp" flush="true" />
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div id="demo">
		<div class="search">
			<form action="<%=request.getContextPath()%>/activity/act.do"
				method="post" class="myForm">
				<div class="formGroup" style="display: inline-flex;">
				<jsp:useBean id="actCategoryService" scope="page" class="com.actCategory.model.ActCategoryService" />
					<label>找活動</label> 
					<select size="1" name="act_Category_Id">
						<option value="">請選擇活動名稱</option>
						<c:forEach var="actCategoryVO" items="${actCategoryService.all}">
							<option value="${actCategoryVO.actCategoryId}"
								${(activityVO.actCategoryId==actCategoryVO.actCategoryId)? 'selected':'' }>${actCategoryVO.actCategoryName}
							</option>
					
						</c:forEach>
					</select>
				</div>
				<div class="formGroup" style="display: inline-flex;">
					<label>輸入活動名稱</label> <input type="text" name="act_Name" value="">
				</div>
				<div class="formGroup" style="display: inline-flex;">
					<input type="date" name="act_Start_Date" id="pcsdate" value=""
						class="test1" placeholder="開始日期" size="14"> <label>到</label>
					<input type="date" name="act_End_Date" id="pcedate" value=""
						class="test2" placeholder="結束日期" size="14">
				</div>
				<input type="hidden" name="action" value="listEmps_ByCompositeQuery">
				<input type="hidden" name="from" value="front-end">
				<button type="submit" class="btn btn-default">搜尋</button>
			</form>

		</div>
		<div class="sellList_card">
      		<span class="sc-span1">活動圖片</span>
      		<span class="sc-span2">活動名稱、內容</span>
            <span class="sc-span3">活動詳情</span>
    	</div>
		<c:forEach var="actVO" items="${list}">
			<div class="list">
				<div class="left">
					<jsp:useBean id="acPhSvc" scope="page"
						class="com.actPhoto.model.ActPhotoService" />
					<c:forEach var="actPhotoVO"
						items="${acPhSvc.getByActId(actVO.getActId())}" begin="0" end="0">
						<c:if test="${actPhotoVO.content != null}">	
						<img  src="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do?action=showPhoto&photo=${actPhotoVO.actPhotoId}">
						</c:if>
						<c:if test="${actPhotoVO.content == null }">
						查無圖片
						</c:if>
					</c:forEach>
				</div>
				<div class="right">
					<div class="title">
						<h4>
							<a href="<%=request.getContextPath()%>/activity/act.do?action=getOne_For_Display&actId=${actVO.actId}&from=front-end" class="text-primary">${actVO.actName}</a>
						</h4>
					</div>
					<div class="content">${actVO.actInfo}</div>
				</div>
				<div class="sub">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/activity/act.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="活動詳情" class="btn btn-secondary btn-lg">
						<input type="hidden" name="actId" value="${actVO.actId}">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="hidden" name="from" value="front-end">
					</FORM>
				</div>
			</div>

		</c:forEach>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true" />
</body>
</html>
