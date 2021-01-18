<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="org.json.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.roomorder.model.*" %>
<%@ page import="com.roomorderdetail.model.*" %>
<%@ page import="com.roomphoto.model.*" %>
<%@ page import="com.room.model.*" %>
<%@ page import="com.roomrsv.model.*"%>
<%@ page import="com.activityOrder.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.actCategory.model.*"%>
<%@ page import="com.shop_order.model.*"%>
<!-- 取得會員資訊 -->
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");

	// 活動
	ActivityOrderService actOSvc = new ActivityOrderService();
	List<ActivityOrderVO> actlist = actOSvc.findByMemId(memVO.getMem_id());
	pageContext.setAttribute("actlist", actlist);
	
	// 訂房
	RoomOrderService roSvc = new RoomOrderService();
	List<RoomOrderVO> rolist = roSvc.getOneByMemId(memVO.getMem_id());
	pageContext.setAttribute("rolist", rolist);
	
	// 商城
	ShopOrderService soSvc = new ShopOrderService();
	List<ShopOrderVO> solist = soSvc.getByMemId(memVO.getMem_id());
	pageContext.setAttribute("solist", solist);
	
%>
<jsp:useBean id="rtSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
<jsp:useBean id="rodSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="acPhSvc" scope="page" class="com.actPhoto.model.ActPhotoService" />
<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
<jsp:useBean id="actCategorySvc" scope="page" class="com.actCategory.model.ActCategoryService" />
<jsp:useBean id="actCommentSvc" scope="page" class="com.activityComment.model.ActivityCommentService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/member.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/build/css/countrySelect.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>Maison Camp | 會員中心</title>
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
     <!-- content header -->
    <div class="title-text">
        <div>會員中心</div>
    </div> 
    <!-- side-bar -->
    <div class="row-offcanvas row-offcanvas-left">
      <div id="sidebar" class="sidebar-offcanvas">
          <div class="col-md-12">
                <h3 style="text-align: center;">功能列表</h3>
            <ul class="nav nav-pills nav-stacked">
            	<li id="information" class="click-me active"><a href="#"><i class="fa fa-user"></i>會員基本資料</a></li>
                <li id="info-edit" class="click-me"><a href="#"><i class="fas fa-edit"></i>會員資料修改</a></li>
                <li id="password-edit" class="click-me"><a href="#"><i class="fa fa-key"></i>變更密碼</a></li>
                <li id="check-order" class="click-me dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-search"></i>查詢訂單<span class="caret"></span></a>
                	<ul class="dropdown-menu">
      					<li id="room-order"><a href="#"><i class="fas fa-bed"></i>住房訂單</a></li>
      					<li id="shop-order"><a href="#"><i class="fas fa-shopping-cart"></i>商城訂單</a></li>
      					<li id="act-order"><a href="#"><i class="fas fa-map-signs"></i>活動報名訂單</a></li>
    				</ul>        
                </li>
                <li id="payment" class="click-me"><a href="#"><i class="far fa-credit-card"></i>付款方式</a></li>
                <li id="cheap" class="click-me"><a href="#"><i class="fa fa-gift"></i>套裝優惠</a></li>
            </ul>
          </div>
      </div>
      <div id="main">
      		<div id="info" class="row info-form">     
	              <fieldset>
	                  <legend><i class="fas fa-user-circle"></i> 會員基本資料</legend>
	                  <div class="panel panel-warning" style="width:90%; margin-left:50px;">
			            <div class="panel-heading">
			                <h3 class="panel-title"><i class="far fa-campground"></i> ${memVO.name}</h3>
			            </div>
			            <div class="panel-body">
			                <div class="row">
			                    <div class="col-md-10 pull-right"> 
			                        <table class="table table-user-information">
			                        <tbody>
			                            <tr>
				                            <td>會員編號:</td>
				                            <td>${memVO.mem_id}</td>
			                            </tr>
			                            <tr>
				                            <td>會員帳號:</td>
				                            <td>${memVO.user_id}</td>
			                            </tr>
			                            <tr>
				                            <td>會員狀態:</td>
				                            <td><c:choose>
												<c:when test="${memVO.status == '0'}">
													未驗證
													<form method="post" action="<%=request.getContextPath()%>/Member.do">
														<input type="hidden" name="mem_id" value="${memVO.mem_id}">
														<input type="hidden" name="email" value="${memVO.email}">
														<input type="hidden" name="action" value="resendAuth">
														<button class="btn btn-primary" type="submit">重發驗證碼</button>
													</form>
												</c:when>
												<c:when test="${memVO.status == '1'}">
													已驗證
												</c:when>
												<c:otherwise>
													你是會員嗎？
												</c:otherwise>
												</c:choose>
											</td>
			                            </tr>
			                            <tr>
				                            <td>電子信箱:</td>
				                            <td>${memVO.email}</td>
			                            </tr>
			                            <tr>
				                            <td>會員生日:</td>
				                            <td>${memVO.birthday}</td>
			                            </tr>
			                            <tr>
				                            <td>手機號碼:</td>
				                            <td>${memVO.phone}</td>
			                            </tr>
			                            <tr>
				                            <td>國籍:</td>
				                            <td><c:choose>
												<c:when test="${memVO.nation == 'tw'}">
													<img style="width:30px; height:20px;" src="<%=request.getContextPath()%>/img/taiwan.png">
												</c:when>
												<c:when test="${memVO.nation == 'us'}">
													<img style="width:30px; height:20px;" src="<%=request.getContextPath()%>/img/USA.png">
												</c:when>
												<c:otherwise>
													已驗證
												</c:otherwise>
												</c:choose>
											</td>
			                            </tr>
			                            <tr>
				                            <td>性別:</td>
				                            <td>${memVO.sexual}</td>
			                            </tr>
			                        </tbody>
			                        </table> 
			                    </div>
			                </div>
			            </div>
			        </div>      
	              </fieldset>
	          
	      </div>
	      <div id="info-show" class="row info-form" style="display:none;">
	          <form method="post" action="<%=request.getContextPath()%>/Member.do">
	              <fieldset>
	                  <legend><i class="fa fa-edit"></i> 修改基本資料</legend>
	                  <input type="hidden" name="mem_id" value="${memVO.mem_id}">
	                  <div class="input-group input-group-icon">
	                  <div class="input-name">姓名：</div>
	                  <input type="text" placeholder="姓名 (長度6-12字)" name="name" minlength="1" maxlength="12" value="${memVO.name}"/>
	                  <div class="input-icon"><i class="fa fa-user"></i></div>
	                  <p id="errorMsgName" style="font-size:2px; color:red; margin-left:160px;"></p>
	                  </div>
	                  <div class="input-group input-group-icon">
	                  <div class="input-name">帳號：</div>
	                  <input type="text" placeholder="帳號 (長度8-12字 )" name="user_id" minlength="1" maxlength="12" value="${memVO.user_id}"/>
	                  <div class="input-icon"><i class="far fa-user-circle"></i></div>
	                      <p id="errorMsgUserId" style="font-size:2px; color:red; margin-left:160px;"></p>
	                  </div>
	                  <div class="input-group input-group-icon">
	                          <div class="input-name">手機號碼：</div>
	                  <input type="text" placeholder="手機號碼" name="phone" value="${memVO.phone}"/>
	                  <div class="input-icon"><i class="far fa-phone-alt"></i></div>
	                  </div>
	                  <div class="input-group input-group-icon">
	                          <div class="input-name">出生年月日：</div>
	                  <input id="birthday" type="text" placeholder="生日" name="birthday" value="${memVO.birthday }"/>
	                  <div class="input-icon"><i class="far fa-birthday-cake"></i></div>
	                  </div>
	                  <div class="input-group input-group-icon">
	                          <div class="input-name">身分證字號：</div>
	                  <input type="text" placeholder="身分證字號" name="personal_id" value="${memVO.personal_id}"/>
	                  <div class="input-icon"><i class="far fa-user-circle"></i></div>
	                  <p id="errorMsgID" style="font-size:2px; color:red; margin-left:160px;"></p>
	                  </div>
	                  <div class="input-group input-group-icon">
	                          <div class="input-name">國籍：</div>
	                  <input type="text" id="country" placeholder="國籍">
	                  <input type="hidden" name="nation" id="country_code" />
	                  <div class="input-icon"><i class="fas fa-globe-asia"></i></div>
	                  </div>
	                  <div class="col-half">
	                      <div class="input-name">性別：</div>
	                      <div class="input-group">           
	                      <input type="radio" name="gender" value="male" id="gender-male" checked/>
	                      <label for="gender-male">男</label>
	                      <input type="radio" name="gender" value="female" id="gender-female"/>
	                      <label for="gender-female">女</label>
	                      </div>
	                  </div>
	                  <br>
	              </fieldset>
	              <input type="hidden" name="action" value="frontUpdate">
	              <button type="submit" class="form-submit">送出申請</button>
	          </form>
	      </div>
          <div id="password-show" class="row info-form" style="display: none;">
              <form method="post" action="<%=request.getContextPath()%>/Member.do">
                  <fieldset>
                      <legend><i class="fa fa-key"></i> 變更密碼</legend>
                      <input type="hidden" name="mem_id" value="${memVO.mem_id}">
                      <div class="input-group input-group-icon">
                          <div class="input-name">舊密碼：</div>
                          <input type="password" placeholder="舊密碼" name="user_old_pwd" maxlength="12" required/>
                          <div class="input-icon"><i class="fa fa-key"></i></div>
                          <p id="errorMsgUserOldPwd" style="font-size:2px; color:red; margin-left:160px;"></p>
                      </div>
                      <div class="input-group input-group-icon">
                          <div class="input-name">新密碼：</div>
                          <input type="password" placeholder="新密碼 (長度8-12字且不包含特殊符號)" name="user_new_pwd" maxlength="12" required/>
                          <div class="input-icon"><i class="fa fa-key"></i></div>
                      </div>
                      <div class="input-group input-group-icon">
                          <div class="input-name">新密碼確認：</div>
                          <input id="check-repeat-pwd" type="password" placeholder="再次輸入新密碼" name="user_re_enter_new_pwd" required/>
                          <div class="input-icon"><i class="fa fa-key"></i></div>
                          <p id="errorMsgUserRePwd" style="font-size:2px; color:red; margin-left:160px;"></p>
                      </div>
                      <br>
                  </fieldset>
                  <input type="hidden" name="action" value="updatePwd">
                  <button type="submit" class="form-submit">送出申請</button>
              </form>
          </div>
          <!--room order section -->      
          <div id="room-order-show" class="row info-form" style="display:none;">
          	<h3 class="room-headline">住房訂單</h3>
          	<c:choose>
          		<c:when test="${empty rolist}">
	          		<div class="container">
          				<h4 style="text-align:center; color:#777;">尚無訂房訂單資料</h4>
          			</div>
          		</c:when>
          		<c:otherwise>
          		<c:forEach var="roVO" items="${rolist}">
	              <div class="container">
				      <div class="row">
				      	<div class="col-sm-4">
	                    	<img src="<%=request.getContextPath()%>/FrontEndRTPhoto?room_category_id=${rodSvc.getOneROD(roVO.room_order_id).room_category_id}" style="width:300px; height:200px; margin-left:-12px;"/>              		
				      	</div>
				      	<div class="col-sm-4">
					      	<h4 class="room-order-headline">${rtSvc.getOneRT(rodSvc.getOneROD(roVO.room_order_id).room_category_id).room_name}</h4>
							<ul style="list-style:none; padding:5px 0; line-height:2em; font-size: 15px;">
				      			<li><i class="fa fa-chevron-circle-right"></i>房型說明: ${rtSvc.getOneRT(rodSvc.getOneROD(roVO.room_order_id).room_category_id).room_type}</li>
								<li><i class="fa fa-chevron-circle-right"></i>入住日期: ${roVO.check_in_date}</li>
								<li><i class="fa fa-chevron-circle-right"></i>退房日期: ${roVO.check_out_date}</li>
								<li><i class="fa fa-chevron-circle-right"></i>訂房數量: ${rodSvc.getOneROD(roVO.room_order_id).quantity} 間</li>
								<li><i class="fa fa-chevron-circle-right"></i>訂單金額: ${rodSvc.getOneROD(roVO.room_order_id).room_order_price} 元</li>
							</ul>
				      	</div>
				      	<div class="col-sm-4 right-side">
				      		<h4>訂單編號：#${roVO.room_order_id}</h4>
				      		<h4>下單日期：${rodSvc.getOneROD(roVO.room_order_id).order_time}</h4>
				      		<c:choose>
				      			<c:when test="${roVO.status == '0'}">
						      		<i style="color:#47cf72" class="fas fa-check-circle">已付款</i>
						      		<form method="post" action="<%=request.getContextPath()%>/RoomOrder.do">
						      			<input type="hidden" name="room_order_id" value="${roVO.room_order_id}">
						      			<input type="hidden" name="room_category_id" value="${rodSvc.getOneROD(roVO.room_order_id).room_category_id}">
						      			<input type="hidden" name="quantity" value="${rodSvc.getOneROD(roVO.room_order_id).quantity}">
						      			<input type="hidden" name="check_in_date" value="${roVO.check_in_date}">
						      			<input type="hidden" name="check_out_date" value="${roVO.check_out_date}">
						      			<input type="hidden" name="action" value="cancelRoomOrder">
							      		<button class="btn btn-danger" type="submit">取消訂單</button>
						      		</form>
				      			</c:when>
				      			<c:when test="${roVO.status == '1'}">
				      				<i style="color:#c15c61" class="fas fa-check-circle">已取消</i>
				      			</c:when>
				      			<c:when test="${roVO.status == '2'}">
				      				<i style="color:purple" class="fas fa-check-circle">入住中</i>
				      			</c:when>
				      			<c:when test="${roVO.status == '3'}">
				      				<i style="color:lightblue" class="fas fa-check-circle">已完單</i>
				      			</c:when>
				      		</c:choose>	      		
				      	</div>
				      </div>
			      </div>
			      </c:forEach>
          		</c:otherwise>
          	</c:choose>
          </div>
          <!--shop order section -->      
          <div id="shop-order-show" class="row info-form" style="display:none;">
          	<h3 class="shop-headline">商城訂單</h3>
          		<c:choose>
          		<c:when test="${empty solist}">
	          		<div class="container">
          				<h4 style="text-align:center; color:#777;">尚無商品訂單資料</h4>
          			</div>
          		</c:when>
          		<c:otherwise>
          		<c:forEach var="soVO" items="${solist}">
	              <div class="container">
				      <div class="row">
				      	<div class="col-sm-4">
	                    	<img src="<%=request.getContextPath()%>/img/shopOrderImg.jpg" style="width:300px; height:200px; margin-left:-12px;"/>              		
				      	</div>
				      	<div class="col-sm-4">
					      	<h4 class="room-order-headline"><a href="<%=request.getContextPath()%>/findOrder?action=findOrderByOrderId&shopOrderId=${soVO.shop_order_id}" target=_blank>訂單資訊</a></h4>
							<ul style="list-style:none; padding:5px 0; line-height:2em; font-size: 15px;">
								<li><i class="fa fa-chevron-circle-right"></i>付款方式: ${soVO.payment}</li>
								<li><i class="fa fa-chevron-circle-right"></i>訂單金額: ${soVO.shop_total_amount} 元</li>
							</ul>
				      	</div>
				      	<div class="col-sm-4 right-side">
				      		<h4>訂單編號：#${soVO.shop_order_id}</h4>
				      		<h4>下單日期：${soVO.time}</h4>
				      		<c:choose>
				      			<c:when test="${soVO.status == '0'}">
						      		<i style="color:#47cf72" class="fas fa-check-circle">已取消</i>
				      			</c:when>
				      			<c:when test="${soVO.status == '1'}">
				      				<i style="color:#c15c61" class="fas fa-check-circle">已付款</i>
				      			</c:when>
				      			<c:otherwise>
				      				<i style="color:lightblue" class="fas fa-check-circle">已完單</i>
				      			</c:otherwise>
				      		</c:choose>	      		
				      	</div>
				      </div>
			      </div>
			      </c:forEach>
          		</c:otherwise>
          	</c:choose>
          </div>
          <!--act order section -->      
         <!--act order section -->      
          <div id="act-order-show" class="row info-form" style="display:none;">
          	<h3 class="act-headline">活動報名訂單</h3>
          	<c:choose>
          		<c:when test="${empty actlist}">
	          		<div class="container">
          				<h4 style="text-align:center; color:#777;">尚無活動訂單資料</h4>
          			</div>
          		</c:when>
          		<c:otherwise>
          		<c:forEach var="actOrderVO" items="${actlist}">
	            	<div class="container">
				      <div class="row">
				      	<div class="col-sm-4">
								<c:forEach var="actPhotoVO" items="${acPhSvc.getByActId(actOrderVO.actId)}" begin="0" end="0">
									<c:if test="${actPhotoVO.content != null}">	
										<img  src="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do?action=showPhoto&photo=${actPhotoVO.actPhotoId}" style="width:300px; height:200px; margin-left:-12px;">
									</c:if>
									<c:if test="${actPhotoVO.content == null }">
										查無圖片
									</c:if>
								</c:forEach>             		
				      	</div>
				      	<div class="col-sm-4">
					      	<h4 class="room-order-headline">${actSvc.getOneActivity(actOrderVO.actId).actName}</h4>
							<ul style="list-style:none; padding:5px 0; line-height:2em; font-size: 15px;">
								<li><i class="fa fa-chevron-circle-right"></i>報名人數: ${actOrderVO.people}</li>
				      			<li><i class="fa fa-chevron-circle-right"></i>活動起始日期: ${actSvc.getOneActivity(actOrderVO.actId).actStartDate}</li>
								<li><i class="fa fa-chevron-circle-right"></i>活動結束日期: ${actSvc.getOneActivity(actOrderVO.actId).actEndDate}</li>
								<li><i class="fa fa-chevron-circle-right"></i>活動報名費用: ${actSvc.getOneActivity(actOrderVO.actId).actPrice} 元</li>
							</ul>
				      	</div>
				      	<div class="col-sm-4">
				      		<h4>訂單編號：#${actOrderVO.actOrderId}</h4>
				      		<h4>下單日期：${actOrderVO.createTime}</h4>
				      		<c:choose>
				      			<c:when test="${actOrderVO.status == '0'}">
						      		<h4><i style="color:#47cf72" class="fas fa-check-circle">已付款</i></h4>
						      		<form method="post" action="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do" onSubmit="return CheckForm()">
						      			<input type="hidden" name="actOrderId" value="${actOrderVO.actOrderId}">
						      			<input type="hidden" name="action" value="usercancel">
							      		<button class="btn btn-danger" type="submit">取消訂單</button>
						      		</form>
						      		</div>
						      		 </div>
				      			</c:when>
				      			<c:when test="${actOrderVO.status == '1'}">
				      				<h4><i style="color:#c15c61" class="fas fa-check-circle">已取消</i></h4>
				      				</div>
				      				 </div>
				      			</c:when>
				      			<c:when test="${actOrderVO.status == '2'}">
				      				<h4><i style="color:lightblue" class="fas fa-check-circle">待評論</i></h4>
									<button id="commentbtn" class="btn-danger">顯示評論</button>
						      		</div>
						      		</div>
						      		<div style="display:none" >
						      			<form method="post" action="<%=request.getContextPath()%>/actComment/ActCommentServlet.do" onSubmit="return CheckCommentForm()">
						      			<input type="hidden" name="actCategoryId" value=${actSvc.getOneActivity(actOrderVO.actId).actCategoryId}>
						      			<h3>活動評論</h3>
						      			<input type="textarea" name="actComment">
						      			<input type="hidden" name="action" value="userinsert">
						      			<input type="hidden" name="actOrderId" value="${actOrderVO.actOrderId}">
						      			<button class="btn btn-danger" type="submit">送出</button>
						      			</form>
						      		</div>
						      	</c:when>
				      			<c:otherwise>
				      				<h4><i style="color:#c15c61" class="fas fa-check-circle">已完成</i></h4>
				      				</div>
				      				</div>
				      			    <div>
				      			    	<h3>您寶貴的評論</h3>
				      			    	<input type="textarea" value="${actCommentSvc.getOneByActOrder(actOrderVO.actOrderId).actComment}" onpaste="return false" disabled readonly>
				      			    	
				      			    </div> 
				      			</c:otherwise>
				      		</c:choose>	     		
			      </div>  
			    </c:forEach>
          		</c:otherwise>
          	</c:choose>
          </div>
          <!-- credit card section -->
          <div id="credit-show" class="row info-form" style="display: none;">
              <form>
                  <fieldset>
                      <legend><i class="far fa-credit-card"></i> 變更付款方式</legend>
                      <div class="creditcardcurrent">
                          <h4 class="cardnumber">${memVO.payment}</h4>
                          <h6>CARDHOLDER NAME</h6>
                          <p class="cardholder">JOHN DOE</p>
                          <p class="exp">04/22</p>
                          <i class="far fa-edit fa-2x delete-creditcard"></i>
                          <div class="creditcard-logo">
                              <img style="width: 70px; height: 70px;" src="<%=request.getContextPath()%>/img/Affinity-Partnership_VISA_600x600 LOGO-01.png" alt="" />
                          </div>
                      </div>
                  </fieldset>
              </form>
              <div class="credit-lightBox" style="display: none;">
                  <form id="credit" method="post" action="<%=request.getContextPath()%>/Member.do">
                  	<input type="hidden" name="mem_id" value="${memVO.mem_id}">
                      <div class="container-fluid grid">              
                        <div class="row pull-center">
                          <div class="position-card col-md-4">         
                            <div class="well">         
                              <div class="row card">
                              </div>
                              <br/>           
                              <div class="row-fluid">
                                <div class="col-md-8">
                                  <div class="form-group">
                                    <label>卡號 </label>
                                    <input type="text" name="payment" class="form-control" />
                                  </div>
                                </div>
                                <div class="col-md-4">
                                  <div class="form-group">
                                    <label>到期日</label>            
                                    <input type="text" placeholder="MM/YY" name="expiry" class="form-control" />              
                                  </div>
                                </div>
                              </div>         
                              <div class="row-fluid">
                                <div class="col-md-8">
                                  <div class="form-group">
                                    <label>持卡人</label>
                                    <input type="text" name="holder" class="form-control" />
                                  </div>
                                </div>           
                                <div class="col-md-4">
                                  <div class="form-group">
                                    <label>安全碼 </label>
                                    <input type="text" name="cvv" class="form-control" />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col-md-12 text-right">
                                <input type="hidden" name="action" value="updateCredit">
                                  <button type="submit" class="btn btn-success">提交</button>
                                  <button id="cancel-plus" type="button" class="btn btn-info">取消</button>
                                </div>     
                              </div>
                            </div>
                          </div>
                      </div> 
                    </div>            
                  </form>
              </div>
          </div>
        </div>
      </div>
    <!-- footer -->
    <footer class="site-footer">
        <div class="footer-container">
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
        <div class="footer-container">
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
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/card/2.5.0/jquery.card.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/build/js/countrySelect.min.js"></script>
<script src="<%=request.getContextPath()%>/js/front-end/member.js"></script>

 <!-- password error -->
<c:if test="${not empty errorPwdMsgs}">
	<script>
		$("#info").css("display","none");
		$("#password-show").css("display","");
		let lists = $(".click-me");
		lists.removeClass("active");
		lists.eq(2).addClass("active");
		$("#errorMsgUserOldPwd").text("${errorPwdMsgs.errorOldPwd}");
		$("#errorMsgUserRePwd").text("${errorPwdMsgs.errorReEnterNewPwd}");
	</script>		
</c:if>

<script>
function CheckForm(){
	var yes = confirm('你確定要取消嗎？');
  	if (yes) {
  	return true;
  	}else{
  	return false;
  	}
}	

function CheckCommentForm(){
	var yes = confirm('你確定送出評論嗎？');
  	if (yes) {
  	return true;
  	}else{
  	return false;
  	}
}	

$.datetimepicker.setLocale('zh');
$('#birthday').datetimepicker({
    theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
	value: '${memVO.birthday}', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});



$("#commentbtn").click(function(){
	if($(this).parent().parent().next().css('display') === 'none'){
		$(this).parent().parent().next().css("display","")
		$(this).text("隱藏評論")
	}else{
		$(this).parent().parent().next().css("display","none")
		$(this).text("顯示評論")
	}
	
});


$("#country").countrySelect({
		defaultCountry:"${memVO.nation}"
});

$("input[name=name]").blur(function(){
	let name = $("input[name=name]").val();
	if(name.trim() === ""){
		$("#errorMsgName").text("＊此欄位不得為空")
		$("input[name=name]").focus()
		$("input[name=name]").addClass("errormsg")
	}
})

$("input[name=user_id]").blur(function(){
	let user_id = $("input[name=user_id]").val();
	if(user_id.trim() === ""){
		$("#errorMsgUserId").text("＊此欄位不得為空")
		$("input[name=user_id]").focus()
		$("input[name=user_id]").addClass("errormsg")
	}
})

$("input[name=personal_id]").blur(function(){
	let personal_id= $("input[name=personal_id]").val();
	if(isValidID(personal_id.trim()) === false){
		$("#errorMsgID").text("＊身分證資料有誤")
		$("input[name=personal_id]").focus()
		$("input[name=personal_id]").addClass("errormsg")
	}
})


$("#credit").submit(function(){
	console.log($("input[name=payment]").val())
})

// personal-id valid or not    	
function isValidID(str){
  if (str === "Y10000001") return true
  if (str.length !== 10) return false
  if (!(str[0] >= "A" && "Z" >= str[0])) return false
  
  let n = alphaToNumber(str[0])
  let n1 = Math.floor(n / 10)
  let n2 = n % 10
  
  let sum = n1*1 + n2*9
  for (i = 1; i < str.length - 1; i++){
   sum += str[i] * (9 - i)
  }
  sum += Number(str[9])
  
  return sum % 10 === 0
}
	
function alphaToNumber(s){
  let scope = {
    A : 10, B : 11, C : 12, D : 13, E : 14, F : 15,
    G : 16, H : 17, I : 34, J : 18, K : 19, M : 21,
    N : 22, O : 35, P : 23, Q : 24, T : 27, U : 28,
    V : 29, W : 32, X : 30, Z : 33, L : 20, R : 25,
    S : 26, Y : 31
  }
  return scope[s] // scope["A"] = 10
}
    	
//input detect typing to remove the error class
$("input[name=name]").keydown(function (){
	$("#errorMsgName").text("")
$("input[name=name]").removeClass("errormsg")
})

$("input[name=user_id]").keydown(function (){
	$("#errorMsgUserId").text("")
$("input[name=user_id]").removeClass("errormsg")
})

$("input[name=user_pwd]").keydown(function (){
	$("#errorMsgUserPwd").text("")
$("input[name=user_pwd]").removeClass("errormsg")
})

$("input[name=re_enter_pwd]").keydown(function (){
	$("#errorMsgUserRePwd").text("")
$("input[name=re_enter_pwd]").removeClass("errormsg")
})

$("input[name=email]").keydown(function (){
	$("#errorMsgEmail").text("")
$("input[name=email]").removeClass("errormsg")
})

$("input[name=personal_id]").keydown(function (){
	$("#errorMsgID").text("")
$("input[name=personal_id]").removeClass("errormsg")
})

$("input[name=user_old_pwd]").keydown(function(){
	$("#errorMsgUserOldPwd").text("")
})

$("input[name=user_re_enter_new_pwd]").keydown(function(){
	$("#errorMsgUserRePwd").text("")
})

</script>
</body>
</html>