<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.room_comment.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Room_commentVO room_commentVO = (Room_commentVO) request.getAttribute("room_commentVO"); //Room_commentServlet.java(Concroller), �s�Jreq��room_commentVO����
%>


<html>
<head>
<title>�B�~���O��� - listOneRoomComment.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listOneRoomComment.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_comment/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/room_comment/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>�ж����׽s��</th>
		<th>�Ы�</th>
		<th>���פ��e</th>
		<th>���׮ɶ�</th>
		
	</tr>

		<tr>
				<td><%=room_commentVO.getRoom_comment_id()%></td>
				<td><%=room_commentVO.getRoom_category_id()%></td>
				<td><%=room_commentVO.getRoom_comment_content()%></td>
				<td><%=room_commentVO.getTime()%></td>
			
			
			
		</tr>
	
</table>


</body>
</html>