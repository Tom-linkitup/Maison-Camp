<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actCategory.model.*"%>
<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	List<ActCategoryVO> actCategoryList = new ActCategoryService().getAll();
	pageContext.setAttribute("activityVO", activityVO);
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

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
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
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
	<tr><td>
		 <h3>���ʸ�ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/activity/selectPage.jsp"><img src="<%=request.getContextPath()%>/img/back.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="actCategoryService" scope="page" class="com.actCategory.model.ActCategoryService" />

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do" name="form1">
<c:if test="${activityVO.actStatus != 0}">
	<table>
	<tr>
		<td>���ʽs��:<font color=red><b>*</b></font></td>
		<td><%=activityVO.getActId()%></td>
	</tr>
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
		<td><input type="TEXT" name="actInfo" size="45"	value="<%=activityVO.getActInfo()%>" readonly disabled/></td>
	</tr>
	<tr>
		<td>���ʻ���:</td>
		<td><input type="TEXT" name="actPrice" size="45"	value="<%=activityVO.getActPrice()%>" readonly disabled/></td>
	</tr>
	<tr>
		<td>���ʶ}�l�ɶ�:</td>
		<td><input name="actStartDate" id="f_date1" type="text" readonly disabled></td>
	</tr>
	<tr>
		<td>���ʵ����ɶ�:</td>
		<td><input name="actEndDate" id="f_date2"  type="text" readonly disabled></td>
	</tr>
	<tr>
		<td>���ʶ}�l���W�ɶ�:</td>
		<td><input name="actApplyOpen" id="f_date3"  type="text" readonly disabled></td>
	</tr>
	<tr>
		<td>���ʵ������W�ɶ�:</td>
		<td><input name="actApplyClose" id="f_date4"  type="text" readonly disabled></td>
	</tr>
	<tr>
		<td>���W�H�ƤW��:</td>
		<td><input type="TEXT" name="maxPeople" size="45" value="<%=activityVO.getMaxPeople()%>" readonly disabled/></td>
	</tr>	
	<tr>
		<td>���W�H�ƤU��:</td>
		<td><input type="TEXT" name="minPeople" size="45" value="<%=activityVO.getMinPeople()%>" readonly disabled/></td>
	</tr>	
	<tr>
		<td>�w���W�H��:</td>
		<td><input type="TEXT" name="actAlreadyApply" size="45" value="<%=activityVO.getActAlreadyApply()%>" readonly disabled/></td>
	</tr>
	<tr>
		<td>���ʦW��:</td>
		<td><input type="TEXT" name="actName" size="45" value="<%=activityVO.getActName()%>" readonly disabled/></td>
	</tr>
	<tr>
		<td>���ʪ��A:</td>
		<td>
		<select size="1" name="actStatus">
		<option value="0">���}�l���W</option>
		<option value="1">���`</option>
		<option value="2">����</option>
		<option value="3">����</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>���ʧ馩:</td>
		<td><input type="TEXT" name="actDiscount" size="45" value="<%=activityVO.getActDiscount()%>" readonly/></td>
	</tr>	
	<tr>
		<td>�馩���e:</td>
		<td><input type="TEXT" name="actPromInfo" size="45" value="<%=activityVO.getActPromInfo()%>" readonly/></td>
	</tr>			
	<tr>
		<td>�馩�}�l�ɶ�:</td>
		<td><input name="actPromStartDate" id="f_date5"  type="text" readonly></td>
	</tr>			
	<tr>
		<td>�馩�����ɶ�:</td>
		<td><input name="actPromCloseDate" id="f_date6"  type="text" ></td>
	</tr>			
	

</table>


<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actId" value="<%=activityVO.getActId()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</c:if>

<c:if test="${activityVO.actStatus == 0}">
<table>
	<tr>
		<td>���ʽs��:<font color=red><b>*</b></font></td>
		<td><%=activityVO.getActId()%></td>
	</tr>

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
		<td><input type="TEXT" name="actInfo" size="45"	value="<%=activityVO.getActInfo()%>"/></td>
	</tr>
	<tr>
		<td>���ʻ���:</td>
		<td><input type="TEXT" name="actPrice" size="45"	value="<%=activityVO.getActPrice()%>" /></td>
	</tr>
	<tr>
		<td>���ʶ}�l�ɶ�:</td>
		<td><input name="actStartDate" id="f_date1" type="text" ></td>
	</tr>
	<tr>
		<td>���ʵ����ɶ�:</td>
		<td><input name="actEndDate" id="f_date2"  type="text" ></td>
	</tr>
	<tr>
		<td>���ʶ}�l���W�ɶ�:</td>
		<td><input name="actApplyOpen" id="f_date3"  type="text" ></td>
	</tr>
	<tr>
		<td>���ʵ������W�ɶ�:</td>
		<td><input name="actApplyClose" id="f_date4"  type="text" ></td>
	</tr>
	<tr>
		<td>���W�H�ƤW��:</td>
		<td><input type="TEXT" name="maxPeople" size="45" value="<%=activityVO.getMaxPeople()%>" /></td>
	</tr>	
	<tr>
		<td>���W�H�ƤU��:</td>
		<td><input type="TEXT" name="minPeople" size="45" value="<%=activityVO.getMinPeople()%>" /></td>
	</tr>	
	<tr>
		<td>�w���W�H��:</td>
		<td><input type="TEXT" name="actAlreadyApply" size="45" value="<%=activityVO.getActAlreadyApply()%>" /></td>
	</tr>
	<tr>
		<td>���ʦW��:</td>
		<td><input type="TEXT" name="actName" size="45" value="<%=activityVO.getActName()%>"/></td>
	</tr>
	<tr>
		<td>���ʪ��A:</td>
		<td>
		<select size="1" name="actStatus">
		<option value="0">���}�l���W</option>
		<option value="1">���`</option>
		<option value="2">����</option>
		<option value="3">����</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>���ʧ馩:</td>
		<td><input type="TEXT" name="actDiscount" size="45" value="<%=activityVO.getActDiscount()%>" /></td>
	</tr>	
	<tr>
		<td>�馩���e:</td>
		<td><input type="TEXT" name="actPromInfo" size="45" value="<%=activityVO.getActPromInfo()%>" /></td>
	</tr>			
	<tr>
		<td>�馩�}�l�ɶ�:</td>
		<td><input name="actPromStartDate" id="f_date5"  type="text"></td>
	</tr>			
	<tr>
		<td>�馩�����ɶ�:</td>
		<td><input name="actPromCloseDate" id="f_date6"  type="text" ></td>
	</tr>			
	

</table>


<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actId" value="<%=activityVO.getActId()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</c:if>


</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
 		   value: '<%=activityVO.getActStartDate()%>' 
        });
        
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=activityVO.getActEndDate()%>' 
        });
        $('#f_date3').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=activityVO.getActApplyOpen()%>' 
         });
        $('#f_date4').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=activityVO.getActApplyClose()%>' 
         });
        $('#f_date5').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=activityVO.getActPromStartDate()%>' 
         });
        $('#f_date6').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=activityVO.getActPromCloseDate()%>' 
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