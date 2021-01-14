<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.roomphoto.model.*"%>
<%@ page import="com.room.model.*" %>

<%	
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> roomTypeList = roomTypeSvc.getAllRT();
	pageContext.setAttribute("roomTypeList", roomTypeList);	
	
	String room_category_id = request.getParameter("room_category_id");
	RoomTypeService roomTypeSvc2 = new RoomTypeService();
	RoomTypeVO roomTypeVO = roomTypeSvc2.getOneRT(room_category_id);
	pageContext.setAttribute("roomTypeVO", roomTypeVO);
	
	RoomPhotoService roomPhotoSvc = new RoomPhotoService();
	List<RoomPhotoVO> rphList = roomPhotoSvc.getAllRPH(room_category_id);
	pageContext.setAttribute("rphList", rphList);
	
	RoomService roomSvc = new RoomService();
	List<RoomVO> roomList = roomSvc.getRmByRTC(room_category_id);
	for(RoomVO roomVO : roomList){
		pageContext.setAttribute("roomVO", roomVO);
	}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/room-detail.css">
        <link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
        <link rel="stylesheet" href="flexslider.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Maison Camp | 房間介紹</title>
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
      </header>
      <!-- float-sidebar -->
      <div id="float-sidebar">
        <div id="float-fb">
          <a href="#">
            <i class="fa fa-comment"></i><br>
            評論區
          </a>
        </div>
        <div id="float-booking">
          <a href="#">
            <i class="fa fa-calendar"></i><br>
            入營預約
          </a>
        </div>
        <div id="float-promo">
          <a href="#">
            <i class="fa fa-bed"></i><br>
            優惠方案
          </a>
        </div>
        <div id="float-top">
          <a href="#">
            <i class="fa fa-chevron-up"></i><br>
            Top
          </a>
        </div>
      </div>
      <!-- content-header -->
      <div class="title-text">
        <div>房間介紹</div>
      </div>
      <!-- content -->
      <div class="purchase-title">      
        <div class="container content-main">
          <div class="row content-row">
            <div class="main col-md-9">
              <div class="row">
                <div class="col-md-12 col-xs-12 col-12 col-12 faq-content">
                  <div class="row">
                    <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 col-12">
                      <h5>${roomTypeVO.room_name}</h5>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="flexslider">
                        <ul class="slides">
                        <c:forEach var="photo" items="${rphList}">
                          <li data-thumb="<%=request.getContextPath()%>/PhotoList.do?room_photo_id=${photo.room_photo_id}">
                            <img src="<%=request.getContextPath()%>/PhotoList.do?room_photo_id=${photo.room_photo_id}" />
                          </li> 
                        </c:forEach>                                      
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="row content-title col-md-12">                   
                    <div class="headline">
                      <h4>類型介紹</h4>
                    </div>                    
                  </div>
                  <div class="row col-md-12">
                    <div class="stmt-headline">
                      <h3>${roomTypeVO.room_type}</h3>
                    </div>              
                  </div>
                  <div class="row text-content col-md-12">
                    <div class="line">
                      <span>房型及價格說明：</span>
                    </div>
                    <div>
                      <table class="table table-bordered">
                        <tr class="room-stmt-head">
                          <th>房型名稱</th>
                          <th>定價</th>
                          <th>坪數</th>
                          <th>可住人數</th>
                          <th>可否加床</th>
                        </tr>
                        <tr class="room-stmt">
                          <td>${roomTypeVO.room_name}</td>
                          <td>NT$${roomTypeVO.room_price}</td>
                          <td>${roomTypeVO.area}坪</td>
                          <td>${roomTypeVO.room_guest}人</td>
                          <td>可</td>
                        </tr>
                      </table>
                    </div>
                  </div>
                  <div class="row text-content col-md-12">
                    <div class="line">
                      <span>類型說明：</span>
                    </div>
                    <p>
                      以上費用均已含10%服務費。
                      <br>
                      1. 入帳時間：15:00後，離帳時間：11:00前。
                      <br>
                      2. 此帳型價格內含提供至多${roomTypeVO.room_guest}人早餐；如有超過之使用人數，需依現場收費公告為主，收取相關費用。
                      <br>
                      3. 此帳型限加一床；至多${roomTypeVO.room_guest+1}人入住。
                      <br>
                      4. 如您實際入住人數(包含大人+小孩)，超過上述之人數規定，本莊園將有權利取消您的訂位，並依取消規定，收取相關費用。
                      <br>
                      5. 如有異動，需依現場公告為主。
                    </p>
                  </div>
                  <div class="row text-content col-md-12">
                    <div class="line">
                      <span>設施介紹：</span>
                    </div>
                    <p>
                      全國首創的豪華帳篷，每頂帳篷裡皆有獨立的衛浴設備，選用飯店級舒適床墊與寢具，帳篷內亦提供盥洗清潔用品組、大小浴巾、吹風機、快煮壺、室內拖鞋，輕鬆入住，是享受戶外美學和大自然的最佳體驗方式。
                    </p>
                  </div>
                  <div class="row text-content col-md-12">
                    <div class="line">
                      <span>帳內設備：</span>
                      <div class="row iconbox">
                        <ul class="row post-share">
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil1.gif" alt="" title="洗髮精"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil2.gif" alt="" title="牙刷牙膏"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil3.gif" alt="" title="刮鬍刀"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil4.gif" alt="" title="吹風機"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil5.gif" alt="" title="梳子"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil6.gif" alt="" title="毛巾"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil7.gif" alt="" title="浴巾"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil8.gif" alt="" title="棉花棒"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil9.gif" alt="" title="個人淋浴間"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil10.gif" alt="" title="室內用拖鞋"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil11.gif" alt="" title="雨傘"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil12.gif" alt="" title="熱水瓶"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil13.gif" alt="" title="免費礦泉水"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil14.gif" alt="" title="暖氣機"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil16.gif" alt="" title="冷氣機"></li>
                          <li><img src="<%=request.getContextPath()%>/img/room-util/roomutil15.gif" alt="" title="客房鑰匙"></li>
                        </ul>
                      </div>
                    </div>                                 
                  </div> 
                  <div class="reserve">
                    <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><button class="btn btn-danger" type="submit">立即預約</button></a>
                  </div>                
                </div>                      
              </div>
            </div>
            <div class="content-sidebar">
              <table>
                <tbody>
                  <tr class="content-sidebar-first">
                    <td>
                      <span>
                        <strong>房型一覽</strong>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <ul>
                        <c:forEach var="roomType" items="${roomTypeList}">
                      	<c:choose>
                      		<c:when test="${roomType.room_category_status == '0'}">
                        		<li><a href="<%=request.getContextPath()%>/front-end/room/RoomDetail.jsp?room_category_id=${roomType.room_category_id}">${roomType.room_name}</a></li>
                      		</c:when>
                      	</c:choose>
                      </c:forEach>
                      </ul>
                    </td>
                  </tr>
                </tbody>
              </table>
              <p></p>
              <table>
                <tbody>
                  <tr class="content-sidebar-second">
                    <td style="background-color: #d96166;">
                      <span>
                        <img src="<%=request.getContextPath()%>/img/alert.png" alt="">
                        <span>目前可預訂時間</span>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td style="background-color: #faf9f2;">
                      <span>
                        <span>豪華露營可預約時間：</span>
                      </span>
                    </td>
                  </tr>
                </tbody>
              </table>
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
                <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                <li><a class="dribbble" href="#"><i class="fa fa-dribbble"></i></a></li>
                <li><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>   
              </ul>
            </div>
          </div>
        </div>
      </footer>
    </div>
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
        <script src="jquery.flexslider.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/js/front-end/room-detail.js"></script>
</body>
</html>