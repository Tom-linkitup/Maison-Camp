<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>
<%
	RoomOrderService roSvc = new RoomOrderService();
	List<RoomOrderVO> checkIns = roSvc.getAllCheckInOrder(); //取得當天要checkIn的訂單
	List<RoomOrderVO> checkOuts = roSvc.getAllCheckOutOrder(); //取得當天尚未CheckOut的訂單
	pageContext.setAttribute("checkIns", checkIns);
	pageContext.setAttribute("checkOuts", checkOuts);
%>

<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html lang="en">
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/back-end/counterSystem.css">
<title>房務系統</title>
</head>
<body>
	<div class="status">
		<h4>客房預訂管理</h4>
	</div>
	<div class="header">
			<div class="current">
				<div>
					<p>${checkIns.size()}</p>
					<i class="fal fa-bell fa-3x"></i>
				</div>
				<h4>今日待入住訂單</h4>
			</div>
			<div class="current">
				<div>
					<p style="color:red;">${checkOuts.size()}</p>
					<i class="fal fa-sign-out-alt fa-3x"></i>
				</div>
				<h4>今日待退房訂單</h4>
			</div>
			<div class="current">
				<div>
					<p style="color:#1776b9;">${checkOuts.size()}</p>
					<div class="percentage">58%</div>
				</div>
				<h4>今日住房數</h4>
			</div>
	</div>
	
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">入住訂單</label> 
		
		<input class="title" id="tab-2" type="radio" name="tab-group" />
		<label for="tab-2">退房訂單</label> 
		
		<input class="title" id="tab-3" type="radio" name="tab-group"  /> 
		<label for="tab-3">入住中訂單</label>
		
		<div id="content">
			<jsp:include page="CheckIn.jsp" />
			<jsp:include page="CheckOut.jsp" />
			<jsp:include page="InRoom.jsp" />
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<%-- <script>
	$(document).ready(function () {
	    let bookingDetail = $("#booking-detail-info");
	    $(".booking-detail").click(function (e) {
	        e.preventDefault();
	        let src = $(this).attr("href");
	        bookingDetail.addClass("display-show");
	        bookingDetail.children("iframe").attr("src", src);
	    });
	    $(".icon").click(function () {
	        $(this).parents(".display-show").removeClass("display-show");
	    });
	    $(".check-in").click(function () {
	        let rooms = $(this).closest("tr").next().find(".room-check-in");
	        if (!rooms.hasClass("show")) {
	            rooms.addClass("show");
	        } else {
	            rooms.removeClass("show");
	        }
	    });
	    $(".empty").click(function () {
	        $(this).siblings(".empty").removeClass("selected");
	        $(this).addClass("selected");
	    });
	    $(".checkin-confirm").click(function () {
	        let selects = $(this).siblings(".checkin-option");
	        let mbid = $(this).attr("data-mbid");
	        let bkno = $(this).attr("data-bkno");
	        let roomArr = [];
	        for (let i = 0; i < selects.length; i++) {
	            let rm_no = selects.eq(i).children(".selected").text();
	            if (roomArr.indexOf(rm_no) < 0) {
	                roomArr.push(rm_no);
	            } else {
	                Swal.fire({
	                    position: "center",
	                    title: "房號重複",
	                    icon: "error",
	                    text: "請重新選擇房號",
	                });
	                return;
	            }
	        }
	        $.ajax({
	            //更新訂單狀態
	            url: "<%=request.getContextPath()%>/bookingServlet?action=checkin",
	            data: {
	                bk_no: bkno,
	            },
	            type: "POST",
	        });
	        for (i in roomArr) {
	            //將選好的房號放入房間
	            $.ajax({
	                url: "<%=request.getContextPath()%>/RoomsServlet?action=update_check_in",
	                data: {
	                    rm_no: roomArr[i],
	                    mb_id: mbid,
	                    bk_no: bkno,
	                },
	                type: "POST",
	                success: function (msg) {
	                    if (msg == "success") {
	                        Swal.fire({
	                            position: "center",
	                            title: "辦理入住成功",
	                            icon: "success",
	                            showConfirmButton: false,
	                        });
	                        setTimeout(function () {
	                            window.location.reload();
	                        }, 1000);
	                    } else {
	                        Swal.fire({
	                            position: "center",
	                            title: "系統爆炸了",
	                            icon: "error",
	                        });
	                    }
	                },
	            });
	        }
	    });
	    $(".checkout-confirm").click(function () {
	        let mbid = $(this).attr("data-mbid");
	        let bkno = $(this).attr("data-bkno");
	        $.ajax({
	            //更新訂單狀態
	            url: "<%=request.getContextPath()%>/bookingServlet?action=checkout",
	            data: {
	                bk_no: bkno,
	            },
	            type: "POST",
	        });
	        $.ajax({
	            //將房號資訊清空
	            url: "<%=request.getContextPath()%>/RoomsServlet?action=update_check_out",
	            data: {
	                mb_id: mbid,
	                bk_no: bkno,
	            },
	            type: "POST",
	            success: function (msg) {
	                if (msg == "success") {
	                    Swal.fire({
	                        position: "center",
	                        title: "退房完成",
	                        icon: "success",
	                        showConfirmButton: false,
	                    });
	                    setTimeout(function () {
	                        window.location.reload();
	                    }, 1000);
	                } else {
	                    Swal.fire({
	                        position: "center",
	                        title: "系統爆炸了",
	                        icon: "error",
	                    });
	                }
	            },
	        });
	    });
	});

	</script> --%>
<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>
