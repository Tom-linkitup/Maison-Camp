<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itempromotion.model.*"%>

<%
	ItemPromotionVO itemPromotionVO = (ItemPromotionVO) request.getAttribute("itemPromotionVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>update itempromotion</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��ƭק� - update_itempromotion_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/item_promotion/select_page.jsp">
						<img
						src="<%=request.getContextPath()%>/back_end/item_promotion/images/189585.jpg"
						width="100" height="100" border="0">�^����
					</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>��ƭק�:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/item_promotion/item_promotion.do"
		name="form1">
		<table>
			<tr>
				<td>���~�P�P�s��:<font color=red><b>*</b></font></td>
				<td><%=itemPromotionVO.getItem_promotion_id()%></td>
			</tr>
			<tr>
				<td>���~�s��:</td>
				<td><input type="TEXT" name="item_id" size="45"
					value="<%=itemPromotionVO.getItem_id()%>" /></td>
			</tr>
			<tr>
				<td>���~�P�P��T:</td>
				<td><input type="TEXT" name="item_promotion_info" size="45"
					value="<%=itemPromotionVO.getItem_promotion_info()%>" /></td>
			</tr>
			<tr>
				<td>���~�P�P�馩:</td>
				<td><input type="TEXT" name="item_discount" size="45"
					value="<%=itemPromotionVO.getItem_discount()%>" /></td>
			</tr>
			<tr>
				<td>�P�P�}�l�ɶ�:</td>
				<td><input name="item_prom_start_date" id="f_date1" type="text"
					value="<%=itemPromotionVO.getItem_prom_start_date()%>"></td>
			</tr>
			<tr>
				<td>�P�P�}�l�ɶ�:</td>
				<td><input name="item_prom_close_date" id="f_date2" type="text"
					value="<%=itemPromotionVO.getItem_prom_close_date()%>"></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="item_promotion_id"
			value="<%=itemPromotionVO.getItem_promotion_id()%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>

</body>
<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date item_prom_start_date = null;
  java.sql.Date item_prom_close_date = null;
  try {
	  item_prom_start_date = itemPromotionVO.getItem_prom_start_date();
	  item_prom_close_date = itemPromotionVO.getItem_prom_close_date();
   } catch (Exception e) {
	   item_prom_start_date = new java.sql.Date(System.currentTimeMillis());
	   item_prom_close_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=item_prom_start_date%>'   , // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
</script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=item_prom_close_date%>'   , // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
</script>

</html>