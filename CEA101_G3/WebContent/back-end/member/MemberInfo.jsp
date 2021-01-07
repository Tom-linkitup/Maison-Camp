<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%
	MemberService memSvc = new MemberService();
	List<MemberVO> list = memSvc.getAllMEM();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/member.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<header>
		<h2 style="text-align:center; margin-bottom:20px;">會員資訊</h2>
	</header>
	<div style="height:43em; overflow:scroll;">
		<div style="display:inline-flex; position:absolute; top:70px; right:15px;">
			<div style="line-height:2; margin-right:4px;">搜尋會員</div>
			<form class="form-inline my-2 my-lg-0" action="">
				<input class="form-control mr-sm-2 selectEmpBySomeThing" type="text" placeholder="Search" aria-label="Search">
			</form>	
		</div>
		<table id="myTable" class="table table-striped" style="text-align:center;">
			<tr class="header">
				<th>編號</th>
				<th>帳號</th>
				<th>密碼</th>
				<th>姓名</th>
				<th>電話</th>
				<th>國籍</th>
				<th>信箱</th>
				<th>性別</th>
				<th>出生年月日</th>
				<th>身分證字號</th>
				<th>付款資訊</th>
				<th>狀態</th>
				<th>修改</th>
			</tr>
			<c:forEach var="memVO" items="${list}">			
			<tr class="hover colorRow">
				<td class="id_info">${memVO.mem_id}</td>
				<td>${memVO.user_id}</td>
				<td>${memVO.user_pwd}</td>
				<td class="name_info">${memVO.name}</td>
				<td>${memVO.phone}</td>
				<td>${memVO.nation}</td>
				<td>${memVO.email}</td>
				<td>${memVO.sexual}</td>
				<td>${memVO.birthday}</td>
				<td>${memVO.personal_id}</td>
				<td>${memVO.payment}</td>
				<td><c:choose>
					<c:when test="${memVO.status == '0'}">
						未驗證
					</c:when>
					<c:otherwise>
						已驗證
					</c:otherwise>
					</c:choose></td>
				<td>	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/Member.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>會員修改</td></tr>
					<tr><td>會員編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="mem_id" class="input-noEdit" type="text" name="mem_id" readonly></td></tr>			
					<tr><td>會員帳號：</td><td><input id="user_id" class="input-beautify" type="text" name="user_id" required></td></tr>
					<tr><td>會員密碼：</td><td><input id="user_pwd" class="input-beautify" type="password" name="user_pwd" required></td></tr>
					<tr><td>會員姓名：</td><td><input id="name" class="input-beautify" type="text" name="name" required></td></tr>
					<tr><td>行動電話：</td><td><input id="phone" class="input-beautify" type="text" name="phone" required></td></tr>
					<tr><td>會員國籍：</td><td><input id="nation" class="input-beautify" type="text" name="nation" required></td></tr>
					<tr><td>電子信箱：</td><td><input id="email" class="input-beautify" type="text" name="email" required></td></tr>
					<tr><td>會員性別：</td><td><input id="sexual" class="input-beautify" type="text" name="sexual" required></td></tr>
					<tr><td>出生年月日：</td><td><input id="birthday" class="input-beautify" type="text" name="birthday" required></td></tr>
					<tr><td>身分證字號：</td><td><input id="personal_id" class="input-beautify" type="text" name="personal_id" required></td></tr>
					<tr><td>付款卡號：</td><td><input id="payment" class="input-beautify" type="text" name="payment" required></td></tr>
					<tr><td>會員狀態：</td>
						<td>
						<select id="status" class="input-beautify" type="text" name="status" required>
							<option>請選擇狀態</option>
							<option value="0">未驗證</option>
							<option value="1">已驗證</option>
						</select>
						</td>
					</tr>		
					<tr><td colspan="2" align="center">
					<input class="input-beautify" type="hidden" name="note" required>
					<input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">
				</table>
			</form>
		</div>
	</div>
	<script>
	$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#mem_id").val(children.eq(0).text());
			$("#user_id").val(children.eq(1).text());
			$("#user_pwd").val(children.eq(2).text());
			$("#name").val(children.eq(3).text());
			$("#phone").val(children.eq(4).text());
			$("#nation").val(children.eq(5).text());
			$("#email").val(children.eq(6).text());
			$("#sexual").val(children.eq(7).text());
			$("#birthday").val(children.eq(8).text());
			$("#personal_id").val(children.eq(9).text());
			$("#payment").val(children.eq(10).text());
			$("#status").val("請選擇狀態");
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		});
	
	$(".selectEmpBySomeThing").keyup(function() {
		let id_info = $('.id_info');
		let name_info = $('.name_info');
		$(".colorRow").addClass('displayN');

		for (let i = 0; i < id_info.length; i++) {
			if (id_info[i].innerText.indexOf($(".selectEmpBySomeThing").val().trim()) != -1 || 
					name_info[i].innerText.indexOf($(".selectEmpBySomeThing").val().trim()) != -1) {
				id_info[i].parentNode.classList.remove('displayN');
				id_info[i].parentNode.classList.add('displayB');
			}
		}
	});
	</script>
	<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>