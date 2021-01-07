<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actPhoto.model.*"%>


<%
	ActPhotoService acPhSvc = new ActPhotoService();
    List<ActPhotoVO> list = acPhSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  table#actPhotoTable th,table#actPhotoTable td {
    padding: 5px;
    text-align: center;
  }
  
  img{
  	width: 100px;
  	height: 100px:
  }
  
  
</style>

</head>
<body >


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div id="content-5">
<table id="actPhotoTable">
	<thead>
	<tr>
		<th>活動圖片編號</th>
		<th>活動編號</th>
		<th>圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	</thead>
	<%@ include file="page1.file" %> 
	<tbody>
	<c:forEach var="actPhotoVO" items="${list}">
		
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
	</tbody>
	</table>
</div>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
<script>
$('#actPhotoTable').dataTable({
//    "searching": true, //搜尋
//    "sPaginationType": "full_numbers", //分頁功能樣式
    "lengthMenu": [2,3], //顯示筆數
//    "processing": true, //顯示資料處理狀態
//    "serverSide": false, //Server端資料處理模式（分頁、排序、過濾）
//    "stateSave": true, //表格狀態保存，當頁面刷新時，是否要保存當前表格狀態，不保存狀態便會在刷新時回復到原始狀態
//    "destroy": true, //每一次修改時銷毀資料
//    "info": true,
//    "autoWidth": true, //是否要自動調整表格寬度
//    "ordering": false, //是否要開啟排序
//    "scrollX": "5000px", //設置x軸高度
//    "scrollY": "200px", //設置y軸高度
//    "scrollCollapse": true, //設置是否控制y軸長度自適應
//    "paging": false, //設置是否要開啟y軸內部分頁
//	"bAutoWidth": true,
//	"bLengthChange": true,
    language: {
        "lengthMenu": "顯示 _MENU_ 筆資料",
        "sProcessing": "處理中...",
        "sZeroRecords": "查無資料",
        "sInfo": "目前有 _MAX_ 筆資料",
        "sInfoEmpty": "目前共有 0 筆紀錄",
        "sInfoFiltered": " ",
        "sInfoPostFix": "",
        "sSearch": "搜尋任何內容:",
        "sUrl": "",
        "sEmptyTable": "尚未有資料紀錄存在",
        "sLoadingRecords": "載入資料中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首頁",
            "sPrevious": "上一頁",
            "sNext": "下一頁",
            "sLast": "末頁"
        },
        "order": [[0, "desc"]],
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    }
});

</script>
</body>