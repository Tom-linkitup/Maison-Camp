<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room_comment.model.*"%>
<%@ page import="com.roomorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<%
	Room_commentService room_commentSvc = new Room_commentService();
	List<Room_commentVO> list = room_commentSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/front-end/roomComment.css" />

    <!DOCTYPE html>
<html>
  
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <meta charset=utf-8 />
    <title>JS Bin</title>
  </head>

    
 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>


.sidenav {
  height: 100%;
  width: 0px;
  position: fixed;
  z-index: 1;
  top: 0;
  right: 0;
  background-color:#e8e900;
  overflow-x: hidden;
  transition: 1.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 3s;
}

.sidenav a:hover {
  color: #f1f1f1	;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  left: 5px;
  font-size: 36px; 
  margin-left: 0px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>

<div><span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>



<div id="mySidenav" class="sidenav">

  <a href="" class="closebtn" onclick="closeNav()">&times; </a><p></p>
	  <div class="post">
  			<c:forEach var="room_commentVO" items="${list}" >
  			<div class="sidebar">
  			</div>

	 		<div class="mybox-middle" >
        		<div class="widget"><BR>
        		    <span class="timestamp" style="clear:both; ">&nbsp;<td>在<fmt:formatDate value="${room_commentVO.time}" pattern="yyyy-MM-dd "/>留下評語</td></span>
			              <br>
			              <div>
          				   
          				    
         	         		<div style="blue"><span class="timestamp" style="clear:both;">&nbsp;入住房型:</span>${room_commentVO.room_category_id}</div>
        		       </div>
        		        		
        		<div class="message" type="text">${room_commentVO.room_comment_content}</div> 
    	
	    	 	</div>
  			</div>
  				<div class="mybox-reply" >
  				<c:if test="${ not empty room_commentVO.comment_reply  }">
        	  		<span>MAISON露營家&nbsp;:</span><div class="message">&nbsp;<td><div>${room_commentVO.comment_reply}</div></td></div>
      	 		</c:if>
      	 		</div>
      	 		
      	 		<p></p>
      	 </c:forEach>
 	  </div>
</div>
</div>




<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "600px";
  document.body.style.backgroundColor = "rgba(255, 0, 0, 0.8)";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.body.style.backgroundColor = "white";
}

$(document).mouseup(function (e){
	var area = $('div#mySidenav.sidenav');
	if(!area.is(e.target) && area.has(e.target).length===0){
		document.getElementById("mySidenav").style.width = "0";
		document.body.style.backgroundColor = "white";	
	}
});


</script>
   
</body>


  </html>
