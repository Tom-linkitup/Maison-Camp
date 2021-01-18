<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_category.model.*"%>
<%
	String itemCategoryId = request.getParameter("itemCategoryId");
	if(itemCategoryId == null) 
		itemCategoryId = "I001";
    ItemService itemSvc = new ItemService();
    List<ItemVO> list = itemSvc.getByCatSt(itemCategoryId);
    pageContext.setAttribute("list",list);
    
    ItemCategoryService itemCatSvc = new ItemCategoryService();
    List<ItemCategoryVO> catList = itemCatSvc.getAll();
    pageContext.setAttribute("catList", catList);  
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/front-end/shopping.css">
    <link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/camplogo.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    <title>Maison Camp | 露營家</title>
    <style>
    .toast-success {
	  background-color: #3276b1;
	}
    </style>
</head>

<body>
    <div id="black"></div>
    <div id="white">
    	<div class="row">
    		<div class="col-12">
		    	<div class="lightboxImg">
		        	<img style="border:gray 1px solid"class="boxImg"  >
		        </div>   
		    </div>
		    <div class="col-8 mx-auto">
		    	<h3 class="lightboxName"></h3>
		        <div class="lightboxInfo my-3">
		        </div>
			       		<h3 class="lightboxPrice mb-3"></h3>
			        	<div class="btn-group" role="group" aria-label="Basic example">
							  <button type="button" class="btn btn-secondary dec changeQuantity">-</button>
							  <input type="text" id="sendQuantity" name="quantity" size="3" value="1" disabled>					  	  
							  <button type="button" class="btn btn-secondary inc changeQuantity">+</button>
			       		 </div>
            		<button type="button" class="btn btn-info addtoCart">加入購物車</button>
		        </div>
	 	        </div>
	 	    </div>
        </div>

    </div>
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
    <section>
        <div class="container-fluid">
            <div class="row" style="background-color: #fff">
                <!-- 中間左頁 -->
                <div class="col-md-2 bg_grad pl-3 menu_left ">
                    <ul>
                    	<c:forEach var="itemCategoryVO" items="${catList}">
                        	<li><a href="<%=request.getContextPath()%>/item_category/itemCategory.do?action=changeCategory&itemCategoryId=${itemCategoryVO.itemCategoryId}"><h5 id="itemTypeCol">${itemCategoryVO.itemCategoryName}</h5></a></li><br><br>
                        </c:forEach>
                    </ul>
                </div>
                
                <div class="col-md-8 mx-auto pt-5">
                    <!-- 中間右頁 -->
                    <div class="row" >
                    <%@ include file="page1toshop.file" %>             	
	                    <c:forEach var="itemVO" items="${list}" varStatus="count"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	                    	<%-- <c:if test="${count.count mod 4 == 0 }"> --%>
	                			<div class="row">    		
			                    	<div class="col-3">
			                            <div class="card mb-3" style="text-align: center; height:300px; width:220px; margin-left:10px;">
			                                <img style="height:210px; width:210px;" class="card-img-top itemImg" src="<%=request.getContextPath()%>/photoByitemId?itemId=${itemVO.itemId}" alt="Card image cap">
			                                <div class="card-body name"><p>${itemVO.itemName}</p></div>
			                                <FORM method="post">
			                                	<input type="hidden" class="info" name="itemInfo" value="${itemVO.itemInfo}">		                                
			                                	<input type="hidden" class="id" name="itemId" value="${itemVO.itemId}">
			                                	<input type="hidden" class="price" name="itemPrice" value="${itemVO.itemPrice}">
			                                </FORM>
			                            </div>
			                        </div>
		                        </div>
	                    <%-- 	</c:if> --%>
		                   	<%-- <c:if test="${count.count mod 4 == 0 or count.last}">
		                   		<div class="row">    		
			                    	<div class="col-3">
			                            <div class="card mb-3" style="text-align: center; height:300px; width:220px; margin-left:10px;">
			                                <img style="height:210px; width:210px;" class="card-img-top itemImg" src="<%=request.getContextPath()%>/photoByitemId?itemId=${itemVO.itemId}" alt="Card image cap">
			                                <div class="card-body name"><p>${itemVO.itemName}</p></div>
			                                <FORM method="post">
			                                	<input type="hidden" class="info" name="itemInfo" value="${itemVO.itemInfo}">		                                
			                                	<input type="hidden" class="id" name="itemId" value="${itemVO.itemId}">
			                                	<input type="hidden" class="price" name="itemPrice" value="${itemVO.itemPrice}">
			                                </FORM>
			                            </div>
			                        </div>
		                        </div>    		
	                    	</c:if> --%>
	                    </c:forEach>
	               </div> 
					
                    <nav class="col-12" aria-label="Page navigation example" style="display:block;">
                        <!-- 底層換頁btn -->
                        <ul class="pagination  justify-content-center">
                        	<%@ include file="page2toshop.file" %> 
						</ul>
                    </nav>
                    
                </div>
            </div>
    </section>
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
                        <li><a href="">會員登入</a></li>
                        <li><a href="">立即訂房</a></li>
                        <li><a href="">精選商城</a></li>
                        <li><a href="">預約活動</a></li>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

    
   <script type="text/javascript">
		   $(document).ready(() => {
		   	
		  //WS
	    	var MyPoint = "/NotifyWS";
	    	var host = window.location.host;
	    	var path = window.location.pathname;
	    	var webCtx = path.substring(0, path.indexOf('/', 1));
	    	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	    	console.log(endPointURL);
	    	var webSocket = new WebSocket(endPointURL);
	    	webSocket.onmessage = function(event) {
	    		var jsonObj = JSON.parse(event.data);
	    		let type = jsonObj.type;
	    		let items = jsonObj.cartItems;
	    		toastr["success"](type);
	    		$(".badge").text(items);
	    	};

		   	
		   	let id,info,price,name;		   	
		   	
		//新增資訊到燈箱裡
		       $(".card").click(function(){
		       	id = $(this).find(".id").val();            	
		       	info = $(this).find(".info").val();            	
		       	price = $(this).find(".price").val();
		       	name = $(this).find(".name").text();
		           $("#white").css('display','block');
		           $("#black").css('display','block');
		           $('.boxImg').attr("src",$(this).children(".itemImg").attr("src"));
		           $('.lightboxName').html(name);
		           $('.lightboxInfo').html(info);
		           $('.lightboxPrice').text("$NT" + price );
		       });
		//關燈箱
		       $("#black").click(function(){
		           $("#white").css('display','none');
		           $("#black").css('display','none');
		           $("#sendQuantity").val(1);
		       });
		       
		//ajax加入購物車   
		       $('.addtoCart').click(function(){
		    	   quantity=$('#sendQuantity').val()
		       	$.ajax({
		       		url:"<%=request.getContextPath()%>/ShoppingServlet",
		       		type:"POST",
		       		data:{
		       			"action":"ADD",
		       			"itemId":id,
		       			"price":price,
		       			"name":name,
		       			"quantity":quantity
		       		},
		       		success: function(){
		       			$("#white").css('display','none');
				        $("#black").css('display','none');
				        $("#sendQuantity").val(1);
		       		}
		       	});
		       });
		       
		
		       $('.changeQuantity').click(function(){
				   	let btn = $(this);
				   	let oldValue = btn.parent().find("#sendQuantity").val();
				   	
				   	if(btn.text() == "+"){
				   		var newVal = parseInt(oldValue) + 1;
				   	}else{
				   		if(oldValue > 1){
				   			var newVal = parseInt(oldValue) - 1;
				   		}else{
				   			newVal = 1;
				   		}
				   	}
				   	$("#sendQuantity").val(newVal);
				   });
		
		   });
   </script>
   
   <script>
   toastr.options = {
		   "closeButton": false,
		   "debug": false,
		   "newestOnTop": false,
		   "progressBar": true,
		   "positionClass": "toast-bottom-right",
		   "preventDuplicates": false,
		   "onclick": null,
		   "showDuration": "300",
		   "hideDuration": "1000",
		   "timeOut": "2500",
		   "extendedTimeOut": "1000",
		   "showEasing": "swing",
		   "hideEasing": "linear",
		   "showMethod": "fadeIn",
		   "hideMethod": "fadeOut"
		 }
</script>
</body>


</html>