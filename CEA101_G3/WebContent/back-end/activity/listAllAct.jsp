<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

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



	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
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
				<th>���ʽs��</th>
				<th>�������O�s��</th>
				<th id="info">���ʤ��e</th>
				<th>���ʻ���</th>
				<th>���ʶ}�l�ɶ�</th>
				<th>���ʵ����ɶ�</th>
				<th>���ʶ}�l���W�ɶ�</th>
				<th>���ʵ������W�ɶ�</th>
				<th>���W�H�ƤW��</th>
				<th>���W�H�ƤU��</th>
				<th>�H���W�H��</th>
				<th>���ʦW��</th>
				<th>���ʪ��A</th>
				<th>���ʧ馩</th>
				<th>�馩���e</th>
				<th>�馩�}�l�ɶ�</th>
				<th>�馩�����ɶ�</th>
				<th>�ק�</th>
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
					<c:choose>
					<c:when test="${actVO.actStatus == '0'}">
						<td>���}�l���W</td>
					</c:when>
					<c:when test="${actVO.actStatus == '2'}">
						<td>����</td>
					</c:when>
					<c:when test="${actVO.actStatus == '3'}">
						<td>����</td>
					</c:when>
					<c:otherwise>
						<td>���`</td>
					</c:otherwise>
					</c:choose>
					<td>${actVO.actDiscount}</td>
					<td>${actVO.actPromInfo}</td>
					<td>${actVO.actPromStartDate}</td>
					<td>${actVO.actPromCloseDate}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/activity/act.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="�ק�"> <input type="hidden"
								name="actId" value="${actVO.actId}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
						<c:if test="${actVO.actStatus == '0'}">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/activity/act.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="�R��"> <input type="hidden"
								name="actId" value="${actVO.actId}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
<script>
// $(document).ready(function() {
	$('#actTable').dataTable({
//    "searching": true, //�j�M
//    "sPaginationType": "full_numbers", //�����\��˦�
   	  "lengthMenu": [2,3], //��ܵ���
//    "processing": true, //��ܸ�ƳB�z���A
//    "serverSide": false, //Server�ݸ�ƳB�z�Ҧ��]�����B�ƧǡB�L�o�^
//    "stateSave": true, //��檬�A�O�s�A������s�ɡA�O�_�n�O�s��e��檬�A�A���O�s���A�K�|�b��s�ɦ^�_���l���A
//    "destroy": true, //�C�@���ק�ɾP�����
      "info": true, // ��ܸ�T
	  "autoWidth": true, //�O�_�n�۰ʽվ���e��
//    "ordering": false, //�O�_�n�}�ұƧ�
	  "scrollX": "200px", //�]�mx�b����
//    "scrollY": "200px", //�]�my�b����
//    "scrollCollapse": true, //�]�m�O�_����y�b���צ۾A��
//    "paging": false, //�]�m�O�_�n�}��y�b��������
//	"bAutoWidth": true,
//	"bLengthChange": true,
    language: {
        "lengthMenu": "��� _MENU_ �����",
        "sProcessing": "�B�z��...",
        "sZeroRecords": "�d�L���",
        "sInfo": "�ثe�� _MAX_ �����",
        "sInfoEmpty": "�ثe�@�� 0 ������",
        "sInfoFiltered": " ",
        "sInfoPostFix": "",
        "sSearch": "�j�M���󤺮e:",
        "sUrl": "",
        "sEmptyTable": "�|������Ƭ����s�b",
        "sLoadingRecords": "���J��Ƥ�...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "����",
            "sPrevious": "�W�@��",
            "sNext": "�U�@��",
            "sLast": "����"
        },
        "order": [[0, "desc"]],
        "oAria": {
            "sSortAscending": ": �H�ɧǱƦC���C",
            "sSortDescending": ": �H���ǱƦC���C"
        }
    }
	});
// });

</script>
</body>
</html>