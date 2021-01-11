<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
<style>

  th#info{
  	min-width:400px !important;
  }
  table#actTable th { 
	white-space: nowrap !important;
	text-align:center !important; 
  }
  table#actTable td	{
  	text-align:center !important; 
  }
  div.dataTables_scroll{
  	width: 900px !important;
  	overflow:auto !important; 
  }
</style>

</head>
<body>



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div id="content-1">
		<table id="actTable" >
		<thead>
			<tr>
				<th>活動編號</th>
				<th>活動類別編號</th>
				<th id="info">活動內容</th>
				<th>活動價格</th>
				<th>活動開始時間</th>
				<th>活動結束時間</th>
				<th>活動開始報名時間</th>
				<th>活動結束報名時間</th>
				<th>報名人數上限</th>
				<th>報名人數下限</th>
				<th>以報名人數</th>
				<th>活動名稱</th>
				<th>活動狀態</th>
				<th>活動折扣</th>
				<th>折扣內容</th>
				<th>折扣開始時間</th>
				<th>折扣結束時間</th>
				<th>修改</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="actVO" items="${list}">

				<tr>
					<td>${actVO.actId}</td>
					<td>${actVO.actCategoryId}</td>
					<td class="info">${actVO.actInfo}</td>
					<td>${actVO.actPrice}</td>
					<td>${actVO.actStartDate}</td>
					<td>${actVO.actEndDate}</td>
					<td>${actVO.actApplyOpen}</td>
					<td>${actVO.actApplyClose}</td>
					<td>${actVO.maxPeople}</td>
					<td>${actVO.minPeople}</td>
					<td>${actVO.actAlreadyApply}</td>
					<td>${actVO.actName}</td>
					<td>${actVO.actStatus}</td>
					<td>${actVO.actDiscount}</td>
					<td>${actVO.actPromInfo}</td>
					<td>${actVO.actPromStartDate}</td>
					<td>${actVO.actPromCloseDate}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/activity/act.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="actId" value="${actVO.actId}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
<script>
$('#actTable').dataTable({
//    "searching": true, //搜尋
//    "sPaginationType": "full_numbers", //分頁功能樣式
   	  "lengthMenu": [2,3], //顯示筆數
//    "processing": true, //顯示資料處理狀態
//    "serverSide": false, //Server端資料處理模式（分頁、排序、過濾）
//    "stateSave": true, //表格狀態保存，當頁面刷新時，是否要保存當前表格狀態，不保存狀態便會在刷新時回復到原始狀態
//    "destroy": true, //每一次修改時銷毀資料
      "info": true, // 顯示資訊
	  "autoWidth": true, //是否要自動調整表格寬度
//    "ordering": false, //是否要開啟排序
	  "scrollX": "200px", //設置x軸高度
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
</html>