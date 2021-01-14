<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_category.model.*"%>
<%@ page import="com.shop_order.model.*"%>
<%@ page import="com.shop_order_detail.model.*"%>

<%
	List<ShopOrderDetailVO> shopOrderDetails =(List<ShopOrderDetailVO>) request.getAttribute("shopOrderDetails");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-end/style2orderdetail.css">
    <link rel="shortcut icon" type="image/png" href="img/camplogo.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    <title>Maison Camp | 露營家</title>
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
        <div class="col-xs-4 col-12 logo">
            <div class="car-bg mt-2" >
            	<a href="<%=request.getContextPath()%>/front-end/item/shopOrderDetail.jsp">
           			 <img class="shoppingcar" src="<%=request.getContextPath() %>/img/shopping-cart.png">
           			 <span class="badge"></span>
           	 	</a>
            </div>
        </div>
    </header>
    <div class="title-text">
        <div>精選商城</div>
      </div>
	<div class="container-fluid h-60" style="background-color:#f9f9f9; min-height:500px !important;">
		<ul class="list-unstyled" style=" width: 80%; margin-left: 10%; padding-top: 50px;
    padding-bottom: 50px;
    margin-block-end: 0;">
		<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />
			<c:forEach var="shopOrderDetailVO" items="${shopOrderDetails}">
				  <li class="media mb-3 itemClass">
				    <img class="mr-3 mt-2 orderDetailPic" src="<%=request.getContextPath()%>/photoByitemId?itemId=${shopOrderDetailVO.item_id}" alt="Generic placeholder image">
				    <div class="media-body mt-3">
				      <h3 class="mt-0 mb-1">${itemSvc.getOneItem(shopOrderDetailVO.item_id).itemName}</h3>				      
				    </div>
				   	<div class="btn-group paraGroup" role="group" aria-label="Basic example">
					  <h4 class="itemTotalPrice">數量: ${shopOrderDetailVO.quantity}<br>
					  $NT ${shopOrderDetailVO.item_price*shopOrderDetailVO.quantity}</h4>
			     	 </div>
				  </li>
			</c:forEach>
		</ul>
		
	</div>
	
    <footer class="site-footer">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-6">
                    <h6>關於我們</h6>
                    <p class="text-justify"><i>Maison Camp</i> 豪華露營提供多項的活動的安排與遊憩設施。<br>適合親子或三五好友一同前來放鬆體驗。<br>豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗。<br>一起聽流水蟲鳴鳥叫 與森林一同呼吸。</p>
                </div>
                <div class="col-xs-6 col-md-2">
                    <h6>快速連結</h6>
                    <ul class="footer-links">
	                  <li><a href="<%=request.getContextPath()%>/front-end/member/Member.jsp">會員中心</a></li>
	                  <li><a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp">立即訂房</a></li>
	                  <li><a href="<%=request.getContextPath()%>/front-end/item/shoppingMall.jsp">精選商城</a></li>
	                  <li><a href="<%=request.getContextPath()%>/front-end/activity/selectPage.jsp">預約活動</a></li>
	                  <li><a href="">聯繫我們</a></li>
	                </ul>
                </div>
                <div class="col-xs-6 col-md-3">
                    <a href=""><img src="<%=request.getContextPath() %>/img/footer.png" style="height: 160px; width: 300px;" alt=""></a>
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
                        <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a class="dribbble" href="#"><i class="fa fa-dribbble"></i></a></li>
                        <li><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
</body>
</html>