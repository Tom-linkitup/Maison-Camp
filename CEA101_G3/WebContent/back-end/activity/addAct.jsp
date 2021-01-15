<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actCategory.model.*"%>
<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
	List<ActCategoryVO> actCategoryList = new ActCategoryService().getAll();
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���ʸ�Ʒs�W - addEmp.jsp</title>


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
	<div id="content-2">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do" name="form1">
			<table>
				<jsp:useBean id="actCategoryService" scope="page"
					class="com.actCategory.model.ActCategoryService" />
				<tr>
					<td>�������O�s��:<font color=red><b>*</b></font></td>
					<td><select size="1" name="actCategoryId">
							<c:forEach var="actCategoryVO" items="${actCategoryService.all}">
								<option value="${actCategoryVO.actCategoryId}"
									${(activityVO.actCategoryId==actCategoryVO.actCategoryId)? 'selected':'' }>${actCategoryVO.actCategoryName}
							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<td>���ʤ��e:</td>
					<td><input type="TEXT" name="actInfo" size="45"
						value="<%=(activityVO == null) ? "" : activityVO.getActInfo()%>" /></td>
				</tr>
				<tr>
					<td>���ʻ���:</td>
					<td><input type="TEXT" name="actPrice" size="45"
						value="<%=(activityVO == null) ? 1000 : activityVO.getActPrice()%>" /></td>
				</tr>
				<tr>
					<td>���ʶ}�l�ɶ�:</td>
					<td><input name="actStartDate" id="f_date1" type="text"></td>
				</tr>
				<tr>
					<td>���ʵ����ɶ�:</td>
					<td><input name="actEndDate" id="f_date2" type="text"></td>
				</tr>
				<tr>
					<td>���ʶ}�l���W�ɶ�:</td>
					<td><input name="actApplyOpen" id="f_date3" type="text"></td>
				</tr>
				<tr>
					<td>���ʵ������W�ɶ�:</td>
					<td><input name="actApplyClose" id="f_date4" type="text"></td>
				</tr>
				<tr>
					<td>���W�H�ƤW��:</td>
					<td><input type="TEXT" name="maxPeople" size="45"
						value="<%=(activityVO == null) ? 10 : activityVO.getMaxPeople()%>" /></td>
				</tr>
				<tr>
					<td>���W�H�ƤU��:</td>
					<td><input type="TEXT" name="minPeople" size="45"
						value="<%=(activityVO == null) ? 5 : activityVO.getMinPeople()%>" /></td>
				</tr>
				<tr>
					<td>�w���W�H��:</td>
					<td><input type="TEXT" name="actAlreadyApply" size="45"
						value="<%=(activityVO == null) ? 0 : activityVO.getActAlreadyApply()%>" /></td>
				</tr>
				<tr>
					<td>���ʦW��:</td>
					<td><input type="TEXT" name="actName" size="45"
						value="<%=(activityVO == null) ? "����" : activityVO.getActName()%>" /></td>
				</tr>
				<tr>
					<td>���ʪ��A:</td>
					
					<td>
					<input type="hidden" name="actStatus" size="45"
						value="0" />
					<input type="TEXT" size="45" readonly
						value="���}�l���W" /></td>
				</tr>
				<tr>
					<td>���ʧ馩:</td>
					<td><input type="TEXT" name="actDiscount" size="45"
						value="<%=(activityVO == null) ? 1.0 : activityVO.getActDiscount()%>" /></td>
				</tr>
				<tr>
					<td>�馩���e:</td>
					<td><input type="TEXT" name="actPromInfo" size="45"
						value="<%=(activityVO == null) ? "�L" : activityVO.getActPromInfo()%>" /></td>
				</tr>
				<tr>
					<td>�馩�}�l�ɶ�:</td>
					<td><input name="actPromStartDate" id="f_date5" type="text"></td>
				</tr>
				<tr>
					<td>�馩�����ɶ�:</td>
					<td><input name="actPromCloseDate" id="f_date6" type="text"></td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="�e�X�s�W">
		</FORM>
	</div>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
<%
	java.sql.Date actStartDate = null;
	java.sql.Date actEndDate = null;
	java.sql.Date actApplyOpen = null;
	java.sql.Date actApplyClose = null;
	java.sql.Date actPromStartDate = null;
	java.sql.Date actPromCloseDate = null;

	try {
		actStartDate = activityVO.getActStartDate();
	} catch (Exception e) {
		actStartDate = new java.sql.Date(System.currentTimeMillis());
	}

	try {
		actEndDate = activityVO.getActEndDate();
	} catch (Exception e) {
		actEndDate = new java.sql.Date(System.currentTimeMillis());
	}

	try {
		actApplyOpen = activityVO.getActApplyOpen();
	} catch (Exception e) {
		actApplyOpen = new java.sql.Date(System.currentTimeMillis());
	}

	try {
		actApplyClose = activityVO.getActApplyClose();
	} catch (Exception e) {
		actApplyClose = new java.sql.Date(System.currentTimeMillis());
	}

	try {
		actPromStartDate = activityVO.getActPromStartDate();
	} catch (Exception e) {
		actPromStartDate = new java.sql.Date(System.currentTimeMillis());
	}

	try {
		actPromCloseDate = activityVO.getActPromCloseDate();
	} catch (Exception e) {
		actPromCloseDate = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=actStartDate%>', // value:   new Date(),
        });
        
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=actEndDate%>', // value:   new Date(),
        });
        $('#f_date3').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=actApplyOpen%>', // value:   new Date(),
         });
        $('#f_date4').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=actApplyClose%>', // value:   new Date(),
         });
        $('#f_date5').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=actPromStartDate%>', // value:   new Date(),
         });
        $('#f_date6').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=actPromCloseDate%>', // value:   new Date(),
		});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>