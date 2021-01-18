<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.roomphoto.model.*" %>
<%@ page import="com.room.model.*" %>
<%@ page import="com.roomrsv.model.*"%>

<!-- 取得會員資料 -->
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<!-- 取得預訂的房型資料 -->
<%
	String room_category_id = (String) session.getAttribute("room_category_id");
	pageContext.setAttribute("room_category_id", room_category_id);
	
	RoomTypeService roomTypeSvc = new RoomTypeService();
	RoomTypeVO roomTypeVO = roomTypeSvc.getOneRT(room_category_id);
	pageContext.setAttribute("roomTypeVO", roomTypeVO);
	
	RoomPhotoService roomPhotoSvc = new RoomPhotoService();
	List<RoomPhotoVO> rphList = roomPhotoSvc.getAllRPH(room_category_id);
	pageContext.setAttribute("rphList", rphList);
%>
<!-- 取得預訂資料 -->
<%
	JSONObject jsonObj = (JSONObject) session.getAttribute("infoJson");
	Integer stay = new Integer(jsonObj.getString("stay"));
	pageContext.setAttribute("stay", stay);
	
	String startDate = jsonObj.getString("startDate");
	pageContext.setAttribute("startDate", startDate);
	
	String leaveDate = jsonObj.getString("leaveDate");
	pageContext.setAttribute("leaveDate", leaveDate);
	
	Integer qty = jsonObj.getInt("qty");
	pageContext.setAttribute("qty", qty);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/order.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="flexslider.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>Maison Camp | 確認訂單</title>
</head>
    <body>
        <header class="header">
            <nav role="navigation">
                <div id="menuToggle">
                    <input type="checkbox" id="checkboxtoggle"/>
                    <span></span>
                    <span></span>
                    <span></span>
                    <ul id="menu">
                        <a href="<%=request.getContextPath()%>/front-end/front-index.jsp"><li>首頁</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/news/News.jsp"><li>最新消息</li></a>
                        <a class="enterAlert" href="<%=request.getContextPath()%>/front-end/member/Member.jsp"><li>會員中心</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-type/RoomType.jsp"><li>帳型介紹</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><li>立即訂房</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/item/shoppingMall.jsp"><li>精選商城</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/activity/selectPage.jsp"><li>活動預約</li></a>
                        <a href="#"><li>聯絡我們</li></a>
                    </ul>
                </div>
            </nav>          
            <a href="#"><img id="logoo" class="img-logo" src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>
            <ul class="signin-links">
	        	<li><i style="margin-right:7px; color:#c15c16;" class="fas fa-child fa-1x"></i>${memVO.name} 您好<i style="color:#496b6b; margin: 0 10px 0 5px;" class="fas fa-exclamation"></i><a class="signin" href="<%=request.getContextPath()%>/Member.do?action=logout"><i class="fas fa-sign-out-alt"></i></a></li>
	      	</ul>      
        </header>
        <!-- content header -->
        <div class="title-text">
          <div>訂單確認</div>
        </div>
        <!-- booking order -->
        <div class="news-title">
        	<div class="headline">
        		<h3 style="color:#555;"><span style="color:#e67e22;">訂購資料</span>及付款</h3>
        		<i class="fa fa-chevron-circle-right color-green margin-right-5" style="color:#e67e22; margin-bottom:20px;"></i>
        		<span>以下為您的預訂資訊，確認無誤後請使用信用卡付款</span>
        	</div>
			  <div class="accordin">
			      <input type="checkbox" name="tab-1" id="tab-1" checked/>	      
			      <label for="tab-1" class="accordin_title"><i style="margin-right:5px;" class="fa fa-cogs"></i>類型說明</label>  
			      <div class="accordin-detail-1 container" style="width:100%;">
				      <div class="row">
				      	<div class="col-sm-4">
				      		<div class="flexslider">
							  <ul class="slides">
							  	<c:forEach var="photo" items="${rphList}">
                        		<li>
                          			<img src="<%=request.getContextPath()%>/PhotoList.do?room_photo_id=${photo.room_photo_id}" />
                        		</li> 
                        		</c:forEach>
							  </ul>
							</div>
				      	</div>
				      	<div class="col-sm-8">
							<ul style="list-style:none; padding:5px 0; line-height:1.7em;">
				      			<li>名稱: ${roomTypeVO.room_name}</li>
				      			<li>型態: ${roomTypeVO.room_type}</li>
				      			<li>說明:<p> 1.此帳型價格內含提供至多${roomTypeVO.room_guest}人早餐；如有超過之使用人數，需依現場收費公告為主，收取相關費用。<br>		      			
											2.此帳型可提供加人加床服務；煩請來信或來電告知，以便安排;每帳限加一床；加人、加床費用另計。</p>
								</li>
								<li>入住日期: ${startDate}</li>
								<li>退房日期: ${leaveDate}</li>
								<li>數量: ${qty} 間</li>
							</ul>
				      	</div>
				      </div>
			      </div>
		    </div>
		
		    <div class="accordin">
		      <input type="checkbox" name="tab-2" id="tab-2" />
		      <label for="tab-2" class="accordin_title"><i style="margin-right:5px;" class="fa fa-paperclip"></i>價格資訊</label>
			      <div class="accordin_detail">
			        <table class="table table-striped table-bordered margin-top-20">
			        	<thead>
			        		<tr style="color:#e67e22;">
			        			<th>入住日</th>
			        			<th>房間單價</th>
			        			<th>訂購數量</th>
			        			<th>使用天數</th>
			        			<th>金額小計</th>
			        		</tr>
			        	</thead>
			        	<tbody>
			        		<tr>
			        			<td>${startDate}</td>
			        			<td>${roomTypeVO.room_price}</td>
			        			<td>${qty}</td>
			        			<td>${stay}</td>
			        			<td>${roomTypeVO.room_price * qty * stay}</td>
			        		</tr>
			        		<tr>
			        			<td colspan="5" class="align-rt" style="text-align:end;"><span style="color:#c15c61;">價格：</span> ${roomTypeVO.room_price * qty * stay}</td>	        			
			        		</tr>
			        		<tr>
			        			<td colspan="5" class="align-rt" style="text-align:end;"><span style="color:#c15c61;">優惠折數：</span> 0.8</td>
			        		</tr>
			        		<tr>
			        			<td colspan="5" class="align-rt" style="text-align:end;"><span style="color:#c15c61;">總計：</span>$ ${roomTypeVO.room_price * qty * stay * 0.8}</td>
			        		</tr>
			        	</tbody>
			        </table>
			      </div>
		    </div>
		
		    <div class="accordin">
		      <input type="checkbox" name="tab-3" id="tab-3" />
		      <label for="tab-3" class="accordin_title"><i style="margin-right:5px;" class="fa fa-user"></i>訂購人資料</label>
		      <div class="accordin_detail">
		        	<table class="table table-striped margin-top-20">
		        		<tbody>
		        			<tr>
		        				<td>姓名：</td>
		        				<td>${memVO.name}</td>
		        			</tr>
		        			<tr>
		        				<td>Email：</td>
		        				<td>${memVO.email}</td>
		        			</tr>
		        			<tr>
		        				<td>生日：</td>
		        				<td>${memVO.birthday}</td>
		        			</tr>        		
		        		</tbody>
		        	</table>
		      </div>
		    </div>
		    
		    <div class="accordin">
		      <input type="checkbox" name="tab-4" id="tab-4" />
		      <label for="tab-4" class="accordin_title"><i style="margin-right:5px;" class="far fa-info-circle"></i>取消預定須知</label>
		      <div class="accordin_detail">
		        	<div class="alert alert-warning">
		        		<ul class="list-unstyled" style="line-height:1.7; padding-top:10px;">
		        			<li><i class="fa fa-chevron-circle-right color-green margin-right-5" style="color:#e67e22;"></i> 顧客於使用日當日取消預訂扣總價總金額 100%。</li>
		        			<li><i class="fa fa-chevron-circle-right color-green margin-right-5" style="color:#e67e22;"></i> 顧客於使用日前 7-9 日內取消預訂扣總價總金額 50%。</li>  
		        			<li><i class="fa fa-chevron-circle-right color-green margin-right-5" style="color:#e67e22;"></i> 顧客於使用日前 14日(含14 日)取消預訂扣總價總金額 0%。</li>           		
		        		</ul>      	
		        	</div>
		      </div>
		    </div>	    
		    <div class="accordin">
		      <input type="checkbox" name="tab-5" id="tab-5" />
		      <label for="tab-5" class="accordin_title"><i style="margin-right:5px;" class="far fa-money-bill-alt"></i>選擇付款方式</label>
		      <div class="accordin_detail">
		        <div class="creditcard">
		        	<label class="radio">
		        		<input type="radio" name="payment" checked>
		        		<i class="fal fa-credit-card fa-2x"></i><span style="margin-left: 10px;">信用卡</span>
		        	</label>
		        </div>
		      </div>
		    </div>
		    <div class="steps-control">
		    	<div class="text-center">
			    	<a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><button type="button">返回</button></a>	    	
			    	<form style="display:inline-block;" method="post" action="<%=request.getContextPath()%>/RoomOrder.do">
			    		<input type="hidden" name="stay" value="${stay}">
				    	<input type="hidden" name="mem_id" value="${memVO.mem_id}">
				    	<input type="hidden" name="check_in" value="${startDate}">
				    	<input type="hidden" name="check_out" value="${leaveDate}">
				    	<input type="hidden" name="room_category_id" value="${room_category_id}">
				    	<input type="hidden" name="room_promotion_id" value="RP10002">
				    	<input type="hidden" name="quantity" value="${qty}">
				    	<input type="hidden" name="room_order_price" value="${roomTypeVO.room_price * qty * stay * 0.8}">
				    	<input type="hidden" name="action" value="insert">
				    	<button type="submit">付款</button>  	
			    	</form>
		    	</div>
		    </div>
	    </div>
        <footer class="site-footer">
            <div class="container">
                <div class="row">
                <div class="col-sm-12 col-md-6">
                    <h6>關於我們</h6>
                    <p class="text-justify"><i>Maison Camp</i> 豪華露營提供多項的活動的安排與遊憩設施。<br>適合親子或三五好友一同前來放鬆體驗。<br>豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗。<br>快來與我們一起聽流水蟲鳴鳥叫 與森林一同呼吸。</p>
                </div>
                <div class="col-xs-6 col-md-2">
                    <h6>快速連結</h6>
                    <ul class="footer-links">
                    <li><a href="">會員登入</a></li>
                    <li><a href="">立即訂房</a></li>
                    <li><a href="">精選商城</a></li>
                    <li><a href="">預約活動</a></li>
                    <li><a href="">聯繫我們</a></li>
                    </ul>
                </div>
                <div class="col-xs-6 col-md-3">
                    <a href=""><img class="footer-map" src="<%=request.getContextPath()%>/img/footer.png" style="height: 160px; width: 300px;" alt=""></a>
                </div>
                </div>
                <hr>
            </div>
            <div class="container">
                <div class="row">
                <div class="col-md-8 col-sm-6 col-xs-12">
                    <p class="copyright-text">Copyright &copy; 2021 All Rights Reserved by 
                <a href="#">Maison Camp</a>.
                    </p>
                </div>
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <ul class="social-icons">
                    <li><a class="facebook" href="#"><i class="fab fa-facebook-f"></i></a></li>
                    <li><a class="twitter" href="#"><i class="fab fa-twitter"></i></a></li>
                    <li><a class="dribbble" href="#"><i class="fab fa-dribbble"></i></a></li>
                    <li><a class="linkedin" href="#"><i class="fab fa-linkedin"></i></a></li> 
                    </ul>
                </div>
                </div>
            </div>
        </footer>
      <!-- footer -->
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="jquery.flexslider.js"></script>
        <script src="<%=request.getContextPath()%>/js/front-end/room-booking.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript">
			$(window).load(function() {
				  $('.flexslider').flexslider({
				    animation: "slide"
				  });
			});
		</script>
</body>
</html>