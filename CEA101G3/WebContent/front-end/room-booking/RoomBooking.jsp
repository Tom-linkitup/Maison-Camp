<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.room.model.*" %>

<%
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> rtList = roomTypeSvc.getAllRT();
	pageContext.setAttribute("rtList", rtList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/room-booking.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>Maison Camp | 訂房系統</title>
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
                        <a href="#"><li>會員登入</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-type/RoomType.jsp"><li>帳型介紹</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><li>立即訂房</li></a>
                        <a href="#"><li>精選商城</li></a>
                        <a href="#"><li>活動預約</li></a>
                        <a href="#"><li>聯絡我們</li></a>
                    </ul>
                </div>
            </nav>          
            <a href="#"><img id="logoo" class="img-logo" src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>         
        </header>
        <!-- content header -->
        <div class="title-text">
          <div>線上訂房</div>
        </div>
        <!-- booking calendar -->
        <div class="news-title">
			<div class="container content-xs">
				<div class="row">
					<div class="picknight">
						<div class="form-group">
							<div class="row">
								<form class="sky-form" action="">
									<div class="col-sm-5" style="display:inline-flex;">
										<label class="col-sm-3 text-right control-label">選擇類型:</label>
										<div class="col-sm-9">
											<section>
												<label class="select">
													<select class="change-room">
													<c:forEach var="rt" items="${rtList}">
														<c:if test="${rt.room_category_status == '0'}">
															<option value="${rt.room_category_id}">${rt.room_name}</option>											
														</c:if>
													</c:forEach>
													</select>
												</label>
											</section>
										</div>
									</div>
									<div class="col-sm-4" style="display:inline-flex; margin-left:-10px;">
										<label class="col-sm-6 text-right control-label">使用天數:</label>
										<div class="col-sm-6">
											<section>
												<label class="select">
													<select class="change-room">
														<option value="">1</option>
														<option value="">2</option>
														<option value="">3</option>
														<option value="">4</option>
														<option value="">5</option>
														<option value="">6</option>
														<option value="">7</option>
													</select>
												</label>
											</section>
										</div>
									</div>
									<div class="col-sm-3" style="display:inline-flex;">
										<label class="col-sm-4 text-right control-label">數量:</label>
										<div class="col-sm-8">
											<section>
												<label class="select">
													<select class="change-room">
														<option value="">1</option>
														<option value="">2</option>
														<option value="">3</option>
													</select>
												</label>
											</section>
										</div>
									</div>							
								</form>
							</div>
						</div>
					</div>
				</div>
				<section>
					<div class="table-responsive">
						<div class="ui-datepicker-inline ui-datepicker">
							<div class="card">
					            <div style="display:inline-flex; justify-content: space-between; background-color:#eeeeee;">
					                <a class="" id="prev" onclick="prev()"><i class="fas fa-less-than fa-1x"></i></a>
					            	<h4 class="card-title" id="mmYY" style="margin-top:10px; color:#666666;">月/年</h4>
					                <a class="" id="next" onclick="next()"><i class="fas fa-greater-than fa-1x"></i></a>
					            </div>
					            <table class="table table-bordered" id="dateDetail" style="text-align:center;">
					                <thead>
					                    <tr>
					                        <th class="holiday">日</th>
					                        <th>一</th>
					                        <th>二</th>
					                        <th>三</th>
					                        <th>四</th>
					                        <th>五</th>
					                        <th class="holiday">六</th>
					                    </tr>
					                </thead>
					                <tbody id="body">			
					                </tbody>
					            </table>				            
					        </div>
						</div>
					</div>		
				</section>
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
        <script src="<%=request.getContextPath()%>/js/front-end/room-booking.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>