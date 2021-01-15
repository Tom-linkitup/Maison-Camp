<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room_comment.model.*"%>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.roomorder.model.*" %>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	List<Room_commentVO> list = null;
	list = (List<Room_commentVO>)request.getAttribute("getRoom_commentVoByRtc");
	if (list == null) {
	Room_commentService room_commentSvc = new Room_commentService();
	list = room_commentSvc.getAll();
}
	pageContext.setAttribute("list", list);
	
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> roomTypeList = roomTypeSvc.getAllRT();
	pageContext.setAttribute("roomTypeList", roomTypeList);
%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${updateSuccess == 'yes' }">
		<script>
			$("#tab-5").prop("checked",true);
			swal("回覆/修改成功", "請再次確認資料是否正確", "success");
		</script>
	</c:if>
<c:if test="${deleteSuccess == 'yes' }">
		<script>
			$("#tab-5").prop("checked",true);
			swal("刪除成功", "此則評論已被刪除", "success");
		</script>
	</c:if>
	
	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-1">
		<h2 style="text-align:center; margin-bottom:20px;">評論資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>評論編號</th>
				<th>評論房型</th>
				<th>評論內容</th>
				<th>評論時間</th>
				<th>回覆內容</th>
				<th>評論回覆</th>
				<th>評論刪除</th>
			</tr>
			<div style="display:inline-block; width:600px; margin-bottom:8px;">	
				<div style="display:inline;">
					<form method="post" action="${pageContext.request.contextPath}/room_comment/room_comment.do">
						<select name="room_category_id">
								<option>請選擇查詢房型</option>
							<c:forEach var="roomType" items="${roomTypeList}">
								<option value="${roomType.room_category_id}">${roomType.room_category_id}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="getRoomCommentByRtc">
						<input class="queryRT" type="submit" value="查詢">
					</form>		
				</div>
			<%@ include file="page1.file"%>
			<c:forEach var="room_commentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${room_commentVO.room_comment_id}</td>
				<td>${room_commentVO.room_category_id}</td>
				<td>${room_commentVO.room_comment_content}</td>
				<td><fmt:formatDate value="${room_commentVO.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				
				<td>${room_commentVO.comment_reply}</td>
				
				<td>
				    <input type="hidden" name="room_comment_id" value="${room_commentVO.room_comment_id}">
					<input type="hidden" name="action" value="getOne_For_Update">
				    <button class="reply btn btn-primary" type="submit">回覆/修改</button>
				</td>
				
				
				<td>
					<input type="hidden" name="room_comment_id" value="${room_commentVO.room_comment_id}">
				    <input type="hidden" name="action" value="getOne_For_Update" >
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</td>
				
			</tr>
			</c:forEach>	
		</table>
					<%@ include file="page2.file"%>
		
		
		
		<div id="replyLightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/room_comment/room_comment.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>評論資訊</td></tr>
					<tr><td>評論編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_comment_id"      class="input-beautify"   type="text" name="room_comment_id" readonly></td></tr>			
					<tr><td>評論房型：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_category_id"     class="input-beautify" type="text" name="room_category_id" readonly ></td></tr>
					<tr><td>評論內容：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_comment_content" class="input-beautify" type="text" name="room_comment_content" readonly></td></tr>
					<tr><td>評論時間：</td><td><input style="background-color:#f9f9f9; border:none;" id="time"                 class="input-beautify" type="text" name="time" readonly></td></tr>
					<tr><td>評論回覆內容：</td><td><input style="background-color:#ffE384; border:none;" id="comment_reply"      class="input-beautify" type="text" name="comment_reply" required ></td></tr>
					
					<TR><TD><input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="確定送出">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">		
					</TD></TR>
				</table>
			</form>
		</div>
		
		<div id="deleteLightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/room_comment/room_comment.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>評論資訊</td></tr>
					<tr><td>評論編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_comment_id_delete"      class="input-beautify"   type="text" name="room_comment_id" readonly></td></tr>			
					<tr><td>評論房型：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_category_id_delete"     class="input-beautify" type="text" name="room_category_id" readonly ></td></tr>
					<tr><td>評論內容：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_comment_content_delete" class="input-beautify" type="text" name="room_comment_content" readonly></td></tr>
					<tr><td>評論時間：</td><td><input style="background-color:#f9f9f9; border:none;" id="time_delete"                 class="input-beautify" type="text" name="time" readonly></td></tr>
					<tr><td>評論回覆內容：</td><td><input style="background-color:#f9f9f9; border:none;" id="comment_reply_delete"      class="input-beautify" type="text" name="comment_reply" readonly ></td></tr>
					
					<TR><TD><input type="hidden" name="action" value="delete">
					<input class="btn btn-danger" type="submit" id="btnDelete" value="確定刪除">
					<input class="btn btn-warning" type="button" id="btnDeleteCancel" value="取消">		
					</TD></TR>
				</table>
			</form>
		</div>
	</div>
	
	<script>		
			$(".reply").click(function() {
			$("#replyLightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#room_comment_id").val(children.eq(0).text());
			$("#room_category_id").val(children.eq(1).text());
			$("#room_comment_content").val(children.eq(2).text());
			$("#time").val(children.eq(3).text());
			$("#comment_reply").val(children.eq(4).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#replyLightBox").css("display","none");
		})
		
		
		
		$(".delete").click(function() {
			$("#deleteLightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#room_comment_id_delete").val(children.eq(0).text());
			$("#room_category_id_delete").val(children.eq(1).text());
			$("#room_comment_content_delete").val(children.eq(2).text());
			$("#time_delete").val(children.eq(3).text());
			$("#comment_reply_delete").val(children.eq(4).text());
		})
		
		$("#btnDeleteCancel").click(function() {
			$("#deleteLightBox").css("display","none");
		})
	
		
	</script>	
	
	
	
	
	
</body>
</html>