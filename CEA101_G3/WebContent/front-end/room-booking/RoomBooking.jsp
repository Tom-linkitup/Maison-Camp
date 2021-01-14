<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.room.model.*" %>
<%@ page import="com.roomrsv.model.*"%>

<%
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> rtList = roomTypeSvc.getAllRT();
	pageContext.setAttribute("rtList", rtList);
%>
<%
	RoomRsvService rsvService = new RoomRsvService();
	List<RoomRsvVO> rsvList = rsvService.getAll();
	pageContext.setAttribute("rsvList", rsvList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/room-booking.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/available.css">
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
													<select name="room-type" id="room-type" class="change-room">
															
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
													<select name="stay" id="stay" class="change-room">
														<option value="1" selected>1晚</option>
														<option value="2">2晚</option>
														<option value="3">3晚</option>
														<option value="4">4晚</option>
														<option value="5">5晚</option>
														<option value="6">6晚</option>
														<option value="7">7晚</option>
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
													<select name="qty" id="qty" class="change-room">
														<option value="1">1間</option>
														<option value="2">2間</option>
														<option value="3">3間</option>
														<option value="4">4間</option>
														<option value="5">5間</option>
														<option value="6">6間</option>
														<option value="7">7間</option>
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
					<div class="view">
			            <div id="display"></div>
			             <div class="calendar-backward arrow">
			            	<i class="fas fa-chevron-left"></i>
			        	</div>
				        <div class="calendar-forward arrow">
				            <i class="fas fa-chevron-right"></i>
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
	<script type="text/javascript">
	
	$(document).ready(function () {
        let display = document.getElementById("display");
        let weeks = ["ㄧ", "二", "三", "四", "五", "六", "日"];
        let today = new Date();
        let thisYear = today.getFullYear();
        let thisMonth = today.getMonth();
        let todayDate = today.getDate();
        let todayStr = thisYear + "-" + (thisMonth+1) + "-" + todayDate
        console.log(todayStr);
        let current = 0;
        var loaded = [0, 1]
        getCalendars(12); //拿一年份的月曆！
        fetchAvalibility(current);
        fetchAvalibility(current+1);
        
        function createCalendar(year, month) {
            let feb = leapYear(year);
            let monthOfDay = [31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            let wrapper = document.createElement("div"); //包住個別日曆
            wrapper.classList.add("calendar-wrapper");
            let title = document.createElement("div"); //產生日曆標頭
            title.classList.add("title");
            title.innerHTML = "<p>" + year + " / " + (month + 1) + "</p>";
            let table = document.createElement("table"); //產生日曆表格
            table.classList.add("calendar");
            table.classList.add("table-bordered")
            let firstTr = document.createElement("tr"); //產生標頭列
            firstTr.classList.add("week-title");

            table.append(firstTr);
            wrapper.append(title);
            wrapper.append(table);
            //建立抬頭
            for (let i = 0; i < 7; i++) {
                let th = document.createElement("th");
                th.innerText = weeks[i];
                firstTr.append(th);
            }
            //找出該月第一天是禮拜幾
            let firstDayOfWeek = new Date(year, month, 1).getDay();
            if (firstDayOfWeek == 0) firstDayOfWeek = 7;
            //確認月曆行數
            let rows = (monthOfDay[month] + firstDayOfWeek - 1) / 7;
            //產生月曆行數
            for (let i = 0; i < rows; i++) {
                let tr = document.createElement("tr");
                for (let j = 1; j <= 7; j++) {
                    let td = document.createElement("td");
                    let a = document.createElement("a");
                    let img = document.createElement("img");
                    img.setAttribute("src", "<%=request.getContextPath()%>/img/loader.gif");
                    img.setAttribute("style", "display:none; width:60%; margin-left:30px;")
                    td.classList.add("calendar-td");
                    a.classList.add("calendar-box");
                    let id =
                        year.toString() + "-"
                        + (month + 1).toString().padStart(2, "0") + "-"
                        + (i * 7 + j - firstDayOfWeek + 1).toString().padStart(2, "0");
                    if (i === 0 && j >= firstDayOfWeek) {
                        a.setAttribute("data-year", year);
                        a.setAttribute("data-month", month + 1);
                        a.setAttribute("data-date", j + i * 7 - firstDayOfWeek + 1);
                        a.setAttribute("id", id);
                    } else if (i * 7 + j - firstDayOfWeek + 1 <= monthOfDay[month]) {
                        a.setAttribute("data-year", year);
                        a.setAttribute("data-month", month + 1);
                        a.setAttribute("data-date", j + i * 7 - firstDayOfWeek + 1);
                        a.setAttribute("id", id);
                    }
                    a.append(img);
                    td.append(a);
                    tr.append(td);
                }
                table.append(tr);
            }
            return wrapper;
        }

        function leapYear(year) {
            let feb = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ? 29 : 28;
            return feb;
        }
        //填充日期資訊
        function fillUpDates(year, month, thisMonthDate) {
            let feb = leapYear(year);
            let monthOfDay = [31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            for (let i = thisMonthDate; i <= monthOfDay[month]; i++) {
                let celldate = document.createElement("div");
                let cellqty = document.createElement("div");
                let cellprice = document.createElement("div");
                cellqty.classList.add("calendar-qty");
                cellprice.classList.add("calendar-price");
                celldate.classList.add("calendar-date");
                let id = year.toString() + "-" + (month + 1).toString().padStart(2, "0") + "-" + i.toString().padStart(2, "0");
                let hrefstr =
                    "<%=request.getContextPath()%>/RoomRsv.do?action=booking" 
                    + "&date=" + id
                    + "&stay=0";
                let box = document.getElementById(id);
                box.setAttribute("href", hrefstr);
                box.classList.add("calendar-default");
                celldate.innerText = i;
                box.append(celldate);
                box.append(cellqty);
                box.append(cellprice);
            }
        }
        
        function getCalendars(number) {
            for (i = 0; i < number; i++) {
                let thisMonthDate = 1;
                let year = thisYear + Math.floor(thisMonth / 12);
                let month = thisMonth % 12;
                let calendar = createCalendar(year, month);
                display.append(calendar);
                if (i == 0){
                    thisMonthDate = todayDate;
                } 
                fillUpDates(year, month, thisMonthDate);
                thisMonth++;
            }
            thisMonth = today.getMonth();
            let todaybox = document.getElementById(
                       thisYear.toString() + "-" 
                    + (thisMonth + 1).toString().padStart(2, "0") + "-"
                    + today.getDate().toString().padStart(2, "0")
            );
            todaybox.classList.add("calendar-today");

            let position = 0;
            let forward = $(".calendar-forward");
            let backward = $(".calendar-backward");
            let calendars = $(".calendar-wrapper");
            calendars.eq(0).css("opacity", "1");
            calendars.eq(1).css("opacity", "1");
            backward.fadeOut();
            //導覽
            forward.click(function () {
                current += 1;
                let calendarWidth = parseInt($(".calendar-wrapper").css("width").split("px")[0]);
                calendars.css("opacity", "0");
                if (0 < number - current) {
                    backward.fadeIn(0);
                    $("#display").css("transform", "translateX(-" + (position += calendarWidth) + "px)");
                }
                if (1 === number - current) {
                    forward.fadeOut(0);
                }
                calendars.eq(current).css("opacity", "1");
                calendars.eq(current + 1).css("opacity", "1");
                if (loaded.indexOf(current+1) < 0){
                	fetchAvalibility(current + 1);
                    loaded.push(current+1);
                }
            });
            backward.click(function () {
                current -= 1;
                let calendarWidth = parseInt($(".calendar-wrapper").css("width").split("px")[0]);
                calendars.css("opacity", "0");
                if (number - current > 0) {
                    forward.fadeIn(0);
                    $("#display").css("transform", "translateX(-" + (position -= calendarWidth) + "px)");
                }
                if (number - current === 12) {
                    backward.fadeOut(0);
                }
                calendars.eq(current).css("opacity", "1");
                
                if (loaded.indexOf(current) < 0){
                	fetchAvalibility(current);
                    loaded.push(current+1);
                }
            });
            $(window).resize(function () {
                let CalendarWidth = parseInt($(".calendar-wrapper").css("width").split("px")[0]);
                let reposition = CalendarWidth * current;
                position = reposition;
                $("#display").css("transform", "translateX(-" + reposition + "px)");
            });
        }
        var rm_price = {
        		<c:forEach var="roomtypevo" items="${rtList}">
        			${roomtypevo.room_category_id}:${roomtypevo.room_price},
        		</c:forEach>
        };
        
        function fetchAvalibility(currentCal){
            let allDays = $(".calendar-wrapper").eq(currentCal).find(".calendar-default");
            let stayDays = $("#stay").val();
            let roomType = $("#room-type").val();
            let qty = $("#qty").val();
            for (let i = 0 ; i < allDays.length; i++){
            	let currentDate = allDays.eq(i);
                $.ajax({
                     url: "<%=request.getContextPath()%>/RoomRsv.do",
                     data:{
                         date: currentDate.attr("id"),
                         stay: stayDays,
                         rmtype: roomType,
                         qty: qty,
                         action:"roomCheck"
                     },
                     type: 'POST',
                     beforeSend: function() {
                    	 currentDate.children("img").show();
                      },
                     success: function(str){
                        var data = JSON.parse(str)
                        let rmType = Object.keys(data)[0];
                        let roomLeft = Object.values(data)[0];
            
    					if(data.isFull == "true"){
    						currentDate.addClass("calendar-isFull");
    						/* currentDate.attr("href",""); */
    					} else if(data.isMam == "true"){
    						currentDate.addClass("calendar-isMam");
    					} else {
    						
   							if (data != null){
   								currentDate.children(".calendar-price").text("$" + rm_price[rmType]);
   								currentDate.children(".calendar-qty").text("剩 " + roomLeft);
   							}
    				
    						currentDate.addClass("calendar-isEmpty");
    				
    						let href = currentDate.attr("href").split("stay")[0] 
    									+ "stay=" + $("#stay").val() 
    									+ "&qty=" + $("#qty").val()
    									+ "&rmType=" + rmType;
    					   						
    						currentDate.attr("href", href);
    					}
                        currentDate.children("img").hide();
                     }
                })
            } 
        }
        $("#stay").change(function(){
        	loaded = [current, current+1];
        	fetchAvalibility(current)
        	fetchAvalibility(current+1)
        	 $(".calendar-price").text("");
        	$(".calendar-qty").text("");
        	 $(".calendar-default").removeClass("calendar-isFull calendar-isMam calendar-isEmpty");
        })
        
        $("#qty").change(function(){
        	loaded = [current, current+1];
        	fetchAvalibility(current)
        	fetchAvalibility(current+1)
        	 $(".calendar-price").text("");
        	$(".calendar-qty").text("");
        	 $(".calendar-default").removeClass("calendar-isFull calendar-isMam calendar-isEmpty");
        })
        
        $("#room-type").change(function(){
        	loaded = [current, current+1];
        	fetchAvalibility(current)
        	fetchAvalibility(current+1)
        	 $(".calendar-price").text("");
        	$(".calendar-qty").text("");
        	 $(".calendar-default").removeClass("calendar-isFull calendar-isMam calendar-isEmpty");
        })
    });
            
	</script>
</body>
</html>