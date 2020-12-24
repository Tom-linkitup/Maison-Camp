<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/news.css">
        <link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Maison Camp | 最新消息</title>
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
        <div id="float-top">
          <a href="#">
            <i class="fa fa-chevron-up"></i><br>
            Top
          </a>
        </div>
      </div>

      <!-- content header -->
      <div class="title-text">
        <div>最新消息</div>
      </div>

      <!-- content -->
      <div class="news-title">
        <div id="content" class="container">
          <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
              <div class="row">
                <div class="col-md-12 col-xs-12 col-12 mol">
                  <div class="sm-list">
                    <div class="news-head">
                    2020.12.20
                    </div>
                    <div class="news-body">
                    【開放訂位公告】豪華露營區開放2021/2/28以前帳位預定，1月1日起開放2021年3月及4月的訂位
                    </div>
                  </div>
                  <div class="sm-list">
                    <div class="news-head">
                    2020.12.20
                    </div>
                    <div class="news-body">
                    【Maison Camp】讓您不管在室內還是室外都能享受自然氣息
                    </div>
                  </div>
                  <div class="sm-list">
                    <div class="news-head">
                    2020.12.30
                    </div>
                    <div class="news-body">
                    【訂位公告】跨年及農曆新年僅提供全包餐假期優惠，預定請至"套裝特惠"查詢並訂購
                    </div>
                  </div>
                  <div class="sm-list">
                    <div class="news-head">
                    2020.12.21
                    </div>
                    <div class="news-body">
                    【Maison Camp】仲夏之夜豪華露營，草地包場音樂會
                    </div>
                  </div>
                  <div class="sm-list">
                    <div class="news-head">
                    2020.12.24
                    </div>
                    <div class="news-body">
                    【Maison Camp】提供現在最流行的 Glamping 豪華露營，要帶給您一種全新的旅遊體驗
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- footer -->
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
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/js/front-end/news.js"></script> 

</body>
</html>