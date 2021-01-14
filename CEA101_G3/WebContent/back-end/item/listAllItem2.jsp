<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*" %>
<%@ page import="com.item_category.model.*"%>
<%
	ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="ItemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-1" style="max-height:100% ;	overflow: auto;">
	<div style="position:relative;">
	<select id="chooseCategory" name="chooseCategory" style="position: absolute;left: 0px; z-index:99;">
	<c:forEach var="CategoryVO" items="${ItemCategorySvc.getAll()}">
	<option value="${CategoryVO.itemCategoryId}">${CategoryVO.itemCategoryName}</option>
	</c:forEach>
	</select>
	</div>
		<h2 style="text-align:center; margin-bottom:20px;">商品資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>商品編號</th>
				<th>商品類別編號</th>
				<th>商品名稱</th>
				<th>商品資訊</th>
				<th>商品價格</th>
				<th>商品狀態</th>
				<th>商品修改</th>
				<th>商品刪除</th>
			</tr>
			<c:forEach var="itemVO" items="${list}">			
			<tr class="disappear">
				<td>${itemVO.itemId}</td>
				<td>${itemVO.itemCategoryId}</td>
				<td>${itemVO.itemName}</td>
				<td>${itemVO.itemInfo}</td>
				<td>${itemVO.itemPrice}</td>
				<td><c:choose>
					<c:when test="${itemVO.itemStatus == '0'}">
						上架
					</c:when>
					<c:otherwise>
						下架
					</c:otherwise>
					</c:choose></td>
				<td>
				<input type="hidden" name="itemId" value="${itemVO.itemId}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="<%=request.getContextPath()%>/item/item.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="itemId" value="${itemVO.itemId}">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/item/item.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>商品修改</td></tr>
					<tr><td>商品編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="itemId" class="input-noEdit" type="text" name="itemId" readonly></td></tr>			
					<tr><td>商品類別編號：</td><td><input id="itemCategoryId" class="input-beautify" type="text" name="itemCategoryId" required></td></tr>
					<tr><td>商品名稱：</td><td><input id="itemName" class="input-beautify" type="text" name="itemName" required></td></tr>
					<tr><td>商品資訊：</td><td><input id="itemInfo" class="input-beautify" type="text" name="itemInfo" required></td></tr>
					<tr><td>商品價格：</td><td><input id="itemPrice" class="input-beautify" type="text" name="itemPrice" required></td></tr>
					<tr><td>商品狀態：</td>
						<td>
						<select id="itemStatus" class="input-beautify" type="number" name="itemStatus" required>
							<option>請選擇狀態</option>
							<option value="0">上架</option>
							<option value="1">下架</option>
						</select>
						</td>
					</tr>			
					<tr><td colspan="2" align="center">
					
					<input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">
				</table>
			</form>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	<script>
	$(document).ready(function(){
	$("#chooseCategory").change(function(){
		$("#myTable").find(".disappear").html("");
		let itemCategoryId= $('#chooseCategory').val();
		$.ajax({
	        url:"<%=request.getContextPath()%>/item/item.do?action=menuAjax",
	        data: {
	        	itemCategoryId: itemCategoryId,
	        },
            type : "POST",
            success : function(data) { 
         		let data2 = JSON.parse(data)
         		for(i in data2){
         			for(j in data2[i]){
             			let tr = document.createElement("tr");
             			tr.classList.add("disappear");
             			let thead = document.createElement("thead");
             			let tbody = document.createElement("tbody");
             			let td1 = document.createElement("td");
             			let td2 = document.createElement("td");
             			let td3 = document.createElement("td");
             			let td4 = document.createElement("td");
             			let td5 = document.createElement("td");
             			let td6 = document.createElement("td");
             			let td7 = document.createElement("td");
             			let td8 = document.createElement("td");
             			let btn1 = document.createElement("button");
             			let form = document.createElement("form");
             			let input1 = document.createElement("input");
             			let input2 = document.createElement("input");
             			form.setAttribute("method","post");
             			form.setAttribute("action","${pageContext.request.contextPath}/item/item.do");
             			input1.setAttribute("name","action");
             			input1.setAttribute("value","delete");
             			input1.setAttribute("type","hidden");
             			
             			input2.setAttribute("name","itemId");
             			input2.setAttribute("value",data2[i][j].itemId);
             			input2.setAttribute("type","hidden");
             			
             			let btn2 = document.createElement("button");
             			btn1.innerText = "修改";
             			btn2.innerText = "刪除";
             			btn1.classList.add("edit2");
             			btn2.setAttribute("type","submit");
             			
             			td1.innerText=data2[i][j].itemId;
             			td2.innerText=data2[i][j].itemCategoryId;
             			td3.innerText=data2[i][j].itemName;
             			td4.innerText=data2[i][j].itemInfo;
             			td5.innerText=data2[i][j].itemPrice;
             			td6.innerText=data2[i][j].itemStatus;
             			td7.append(btn1);
             			form.append(input1);
             			form.append(input2);
             			form.append(btn2);
             			td8.append(form);
             			
             			tr.append(td1);
             			tr.append(td2);
             			tr.append(td3);
             			tr.append(td4);
             			tr.append(td5);
             			tr.append(td6);
             			tr.append(td7);
             			tr.append(td8);
             			$("#myTable").append(tr);
         				
         			}
         		}
         		$(".edit2").click(function() {
        			$("#lightBox").css("display","");
        			let tr = $(this).parents("tr");
        			let children = tr.children();
        			$("#itemId").val(children.eq(0).text());
        			$("#itemCategoryId").val(children.eq(1).text());
        			$("#itemName").val(children.eq(2).text());
        			$("#itemInfo").val(children.eq(3).text());
        			$("#itemPrice").val(children.eq(4).text());
        			$("#itemStatus").val("請選擇狀態");
        		})
               
            }
        }) 
	 }) 

		$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#itemId").val(children.eq(0).text());
			$("#itemCategoryId").val(children.eq(1).text());
			$("#itemName").val(children.eq(2).text());
			$("#itemInfo").val(children.eq(3).text());
			$("#itemPrice").val(children.eq(4).text());
			$("#itemStatus").val("請選擇狀態");
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
		
	})	
	</script>	
</body>
</html>