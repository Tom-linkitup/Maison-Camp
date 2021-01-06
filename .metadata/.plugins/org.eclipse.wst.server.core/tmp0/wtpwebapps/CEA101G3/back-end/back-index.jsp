<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>露營管理後台</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.1.2/collection/icon/icon.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back-end/admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back-end/context.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back-end/style.css">
</head>
	<body class="hold-transition skin-blue sidebar-mini">
            <header class="main-header">
                <a href="index2.html" class="logo">
                    <span class="logo-mini"><b>M</b></span>
                    <span class="logo-lg"><b>Maison </b> Camp</span>
                </a>
                <nav class="navbar navbar-static-top" role="navigation">
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">         
                                    <span class="hidden-xs">香港山雞</span>
                                </a>
                                <ul class="dropdown-menu">                           
                                    <li class="">                                     
                                        <a href="#" class="">員工個人資料</a>         
                                    </li>
                                    <li>
                                        <a href="#" class="">管理員登出</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <aside class="main-sidebar">
                <section class="sidebar">
                  <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<%=request.getContextPath()%>/img/back/054101015D42DFD18B366090E3C6D8B5.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>香港山雞</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                  <ul class="sidebar-menu" data-widget="tree">
                      <li class="header"><a href="<%=request.getContextPath()%>/back-end/back-index.jsp">管理員介面</a></li>
                      <li class="treeview">
                          <a href="#">
                              <i class="fa fa-desktop"></i>
                              <span>前台頁面管理</span><span class="pull-right-container">
                                  <i class="fa fa-angle-down pull-right"></i>
                              </span>
                          </a>
                          <ul class="treeview-menu">
                              <li><a href="#">最新消息管理</a></li>
                              <li><a href="#">評論管理</a></li>
                              <li><a href="#">促銷專案管理</a></li>
                              <li><a href="#">廣告管理</a></li>
                          </ul>
                      </li>
                      <li class="treeview">
                      <a href="#">
                          <i class="fa fa-cubes"></i>
                          <span>露營後台管理</span><span class="pull-right-container">
                              <i class="fa fa-angle-down pull-right"></i>
                          </span>
                      </a>
                      <ul class="treeview-menu">
                          <li><a href="#">員工管理</a></li>
                          <li><a href="#">員工權限管理</a></li>
                          <li><a href="#">系統參數管理</a></li>
                      </ul>
                      </li>
                      <li class="treeview">
                        <a href="#">
                            <i class="fa fa-user-circle"></i>
                            <span>會員管理</span><span class="pull-right-container">
                                <i class="fa fa-angle-down pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="<%=request.getContextPath()%>/back-end/member/MemberInfo.jsp">會員資料管理</a></li>
                        </ul>
                        </li>
                      <li class="treeview">
                      <a href="#">
                          <i class="fa fa-bed"></i>
                          <span>訂房後台管理</span><span class="pull-right-container">
                              <i class="fa fa-angle-down pull-right"></i>
                          </span>
                      </a>
                      <ul class="treeview-menu">
                          <li><a href="<%=request.getContextPath()%>/back-end/room-type/RoomTypeInfo.jsp">房型管理</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/room/RoomInfo.jsp">房間管理</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/room-order/RoomOrder.jsp">住房訂單管理</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/room_repair/select_page.jsp">修繕管理</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/room_promotion/select_page.jsp">訂房優惠管理</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/room_comment/select_page.jsp">房間評論管理</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/extra_charges/select_page.jsp">額外消費管理</a></li>
                          <li><a href="#">入住管理</a></li>
                          <li><a href="#">退房管理</a></li>
                      </ul>
                      </li>
                      <li class="treeview">
                          <a href="#">
                              <i class="fa fa-shopping-cart"></i>
                              <span>商城後台管理</span><span class="pull-right-container">
                                  <i class="fa fa-angle-down pull-right"></i>
                              </span>
                          </a>
                          <ul class="treeview-menu">
                              <li><a href="#">銷售統計管理</a></li>
                              <li><a href="#">商品管理</a></li>
                              <li><a href="#">商品訂單管理</a></li>
                          </ul>
                      </li>
                      <li class="treeview">
                          <a href="#">
                              <i class="fa fa-plane"></i>
                              <span>活動後台管理</span><span class="pull-right-container">
                                  <i class="fa fa-angle-down pull-right"></i>
                              </span>
                          </a>
                          <ul class="treeview-menu">
                              <li><a href="#">活動內容管理</a></li>
                              <li><a href="#">活動報名管理</a></li>
                              <li><a href="#">活動排程</a></li>
                          </ul>
                      </li>
                  </ul>
                </section>
            </aside>
            <div class="content-wrapper text-tab active">
                <section class="content container-fluid">
                    <div>望子成龍啊！</div>
                </section>
            </div>
        <footer class="main-footer">
            <strong>Copyright &copy; 2020<a href="#"> Maison Camp</a>.</strong> All rights reserved.
        </footer>
    
        
        <!-- Add the sidebar's background. This div must be placed
        immediately after the control sidebar -->
        <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->
        <!-- REQUIRED JS SCRIPTS -->
        <!-- jQuery 3 -->
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        <!-- AdminLTE App -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.3/js/adminlte.min.js"></script> 
        <script src="<%=request.getContextPath()%>/js/back-end/backer.js"></script>
	</body>
</html>