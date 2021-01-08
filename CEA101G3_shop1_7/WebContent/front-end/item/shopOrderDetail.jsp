<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_category.model.*"%>
<%@ page import="com.shop_order.model.*"%>

<%Vector<Item> buylist = (Vector<Item>) session.getAttribute("shoppingcart");%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/icon-font.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/style2orderdetail.css">
    <link rel="shortcut icon" type="image/png" href="img/camplogo.png">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Maison Camp | �S��a</title>
</head>

<body>
    <header class="header">
        <nav role="navigation">
            <div id="menuToggle">
                <input type="checkbox" id="checkboxtoggle" /><!-- ���W���s -->
                <span></span>
                <span></span>
                <span></span>
                <ul id="menu">
                    <a href="#">
                        <li>����</li>
                    </a>
                    <a href="#">
                        <li>�̷s����</li>
                    </a>
                    <a href="#">
                        <li>�|���n�J</li>
                    </a>
                    <a href="#">
                        <li>�ߧY�q��</li>
                    </a>
                    <a href="#">
                        <li>���ӫ�</li>
                    </a>
                    <a href="#">
                        <li>���ʹw��</li>
                    </a>
                    <a href="#">
                        <li>�p���ڭ�</li>
                    </a>
                </ul>
            </div>
        </nav>
        <div class="col-xs-4 col-12 logo">
            <a href="<%=request.getContextPath()%>/front-end/item/shoppingMall.jsp"><img id="logoo" class="img-logo" src="<%=request.getContextPath() %>/front-end/item/images/logo.png" alt=""></a><!-- LOGO -->
            <div class="car-bg p-2" >
            <img class="shopcar" src="<%=request.getContextPath() %>/front-end/item/images/shopping-cart.png">
            </div>
        </div>
    </header>
	<div class="container h-60">
		<ul class="list-unstyled">
		<%if (buylist != null && (buylist.size() > 0)) {%>
		<%
			 for (int index = 0; index < buylist.size(); index++) {
				Item order = buylist.get(index);
		%>
				  <li class="media pb-2">
				    <img class="mr-3 mt-2 orderDetailPic" src="<%=request.getContextPath()%>/item_photo/photoReader.do?id=IPH10004" alt="Generic placeholder image">
				    <div class="media-body mt-3">
				      <h5 class="mt-0 mb-1"><%=order.getName() %></h5>				      
				    </div>
				   	<div class="btn-group" role="group" aria-label="Basic example">
					  <button type="button" class="btn btn-secondary dec changeQuantity">-</button>
					  <input type="text" id="sendQuantity" name="quantity" size="3" value="<%=order.getQuantity()%>">						  	  
					  <button type="button" class="btn btn-secondary inc changeQuantity">+</button>
			     	 </div>
				    <div class="itemTotalPrice">$NT<%=order.getPrice() %></div>
				    <i class="fas fa-times mt-2" style="width:20px; height:20px;"></i>
				  </li>
			<%}%>
			<%}%>
		</ul>
		<form class="photo-form bottom-button" method="post" action="<%=request.getContextPath()%>/ShoppingServlet">
		<input type="hidden" name="action" value="CHECKOUT">
			<button type="submit">�e�����b</button>
		</form>
	</div>
	
    <footer class="site-footer">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-6">
                    <h6>����ڭ�</h6>
                    <p class="text-justify"><i>Maison Camp</i> �����S�紣�Ѧh�������ʪ��w�ƻP�C�ͳ]�I�C<br>�A�X�ˤl�ΤT���n�ͤ@�P�e�ө��P����C<br>�����S��B �������� �B�����s���������A�n�a���z�@�إ��s���S������C<br>�@�_ť�y���λﳾ�s �P�˪L�@�P�I�l�C</p>
                </div>
                <div class="col-xs-6 col-md-2">
                    <h6>�ֳt�s��</h6>
                    <ul class="footer-links">
                        <li><a href="">�|���n�J</a></li>
                        <li><a href="">�ߧY�q��</a></li>
                        <li><a href="">���ӫ�</a></li>
                        <li><a href="">�w������</a></li>
                        <li><a href="">�pô�ڭ�</a></li>
                    </ul>
                </div>
                <div class="col-xs-6 col-md-3">
                    <a href=""><img src="<%=request.getContextPath() %>/front-end/item/images/footer.png" style="height: 160px; width: 300px;" alt=""></a>
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
    <script src="<%=request.getContextPath() %>/js/shoppingMall.js"></script>
    
   <script type="text/javascript">
   		$(document).ready(function(){
   		
   		})
   </script>
</body>


</html>