<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_category.model.*"%>
<%@ page import="com.shop_order.model.*"%>
<%@ page import="com.member.model.*"%>
<!-- 取得會員資料 -->
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<!-- 取得預訂的商品 -->
<%
	Vector<Item> buylist = (Vector<Item>) session.getAttribute("shoppingcart");
	String amount = (String) request.getAttribute("amount");
%>
<!-- 取得預訂資料 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/checkOut.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
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
	        	<li><i style="margin-right:7px; color:#c15c16;" class="fas fa-child fa-1x"></i>${memVO.name}您好<i style="color:#496b6b; margin: 0 10px 0 5px;" class="fas fa-exclamation"></i><a class="signin" href="<%=request.getContextPath()%>/Member.do?action=logout"><i class="fas fa-sign-out-alt"></i></a></li>
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
		      <input type="checkbox" name="tab-2" id="tab-2" checked/>
		      <label for="tab-2" class="accordin_title"><i style="margin-right:5px;" class="fa fa-paperclip"></i>價格資訊</label>
			      <div class="accordin_detail">
			        <table class="table table-striped table-bordered margin-top-20">
			        	<thead>
			        		<tr style="color:#e67e22;">
			        			<th>商品名稱</th>
			        			<th>商品單價</th>
			        			<th>訂購數量</th>
			        			<th>金額小計</th>
			        		</tr>
			        	</thead>
			        	<tbody>
			   		<%if (buylist != null && (buylist.size() > 0)) {%>
	       			<%
						 for (int index = 0; index < buylist.size(); index++) {
							Item order = buylist.get(index);
					%>
			        		<tr>
			        			<td><%=order.getName()%></td>
			        			<td><%=order.getPrice()%></td>
			        			<td><%=order.getQuantity()%></td>
			        			<td><%=order.getPrice()*order.getQuantity()%></td>
			        		</tr>
			       			<%}}%>
			        		<tr>
			        			<td colspan="5" class="align-rt" style="text-align:end;"><span style="color:#c15c61;">總計：</span>$<%=amount%></td>
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
		        				<td>${memVO.email}<td>
		        			</tr>
		        			<tr>
		        				<td>生日：</td>
		        				<td>${memVO.birthday }</td>
		        			</tr>        		
		        		</tbody>
		        	</table>
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
			    	<a href="<%=request.getContextPath() %>/front-end/item/shopOrderDetail.jsp"><button type="button">返回</button></a>	    	
			    	<form style="display:inline-block;" method="post" action="<%=request.getContextPath()%>/shop_order/createshop_order.do">
			    		<input type="hidden" name="status" value="1">
			    		<input type="hidden" name="amount" value="<%=amount%>">
				    	<input type="hidden" name="mem_id" value="${memVO.mem_id}">
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
                    <a href=""><img class="footer-map" src="<%=request.getContextPath() %>/img/footer.png" style="height: 160px; width: 300px;" alt=""></a>
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
        <script src="<%=request.getContextPath() %>/js/front-end/room-booking.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>