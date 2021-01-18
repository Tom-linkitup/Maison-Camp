<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*" %>
<%@ page import="com.item_category.model.*" %>
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/item.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>


<style>
    .toast-success {
	  background-color: #3276b1;
	}
</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/back-end/item/ItemInfo.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">商品訂單管理</h2></a>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">查看所有訂單</label> 
		
		<div id="content">
			<jsp:include page="/back-end/shop_order_detail/listAllItemOrder.jsp" />
		</div>
	</div>
<%-- 	<%@ include file="/back-end/item/backIndex2.file"%> --%>
</body>

	       <script>
        $(document).ready(() => {
		   	
  		  //WS
  	    	var MyPoint = "/NotifyShopWS";
  	    	var host = window.location.host;
  	    	var path = window.location.pathname;
  	    	var webCtx = path.substring(0, path.indexOf('/', 1));
  	    	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
  	    	var webSocket = new WebSocket(endPointURL);
  	    	webSocket.onmessage = function(event) {
  	    		var jsonObj = JSON.parse(event.data);
  	    		let newOrder = jsonObj.newOrder;
  	    		toastr["success"](newOrder);
  	    	};
        });
        </script>
        
         <script>
         toastr.options = {
        		  "closeButton": false,
        		  "debug": false,
        		  "newestOnTop": false,
        		  "progressBar": false,
        		  "positionClass": "toast-top-right",
        		  "preventDuplicates": false,
        		  "onclick": null,
        		  "showDuration": "300",
        		  "hideDuration": "1000",
        		  "timeOut": "10000",
        		  "extendedTimeOut": "1000",
        		  "showEasing": "swing",
        		  "hideEasing": "linear",
        		  "showMethod": "fadeIn",
        		  "hideMethod": "fadeOut"
        		}
		</script>
</html>
<%@ include file="/back-end/back-template/backIndex2.file"%>