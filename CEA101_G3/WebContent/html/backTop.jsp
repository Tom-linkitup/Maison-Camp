<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>�S��޲z��x</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.1.2/collection/icon/icon.css">
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
                                    <span class="hidden-xs">����s��</span>
                                </a>
                                <ul class="dropdown-menu">                           
                                    <li class="">                                     
                                        <a href="#" class="">���u�ӤH���</a>         
                                    </li>
                                    <li>
                                        <a href="#" class="">�޲z���n�X</a>
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
                            <img src="<%=request.getContextPath()%>/img/back.png" class="img-circle" style="width:100px; height:50px" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>����s��</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                  <ul class="sidebar-menu" data-widget="tree">
                      <a href="<%=request.getContextPath()%>/back-end/back-index.jsp"><li class="header">�޲z������</li></a>
                      <li class="treeview">
                          <a href="#">
                              <i class="fa fa-desktop"></i>
                              <span>�e�x�����޲z</span><span class="pull-right-container">
                                  <i class="fa fa-angle-down pull-right"></i>
                              </span>
                          </a>
                          <ul class="treeview-menu">
                              <li><a href="#">�̷s�����޲z</a></li>
                              <li><a href="#">���׺޲z</a></li>
                              <li><a href="#">�P�P�M�׺޲z</a></li>
                              <li><a href="#">�s�i�޲z</a></li>
                          </ul>
                      </li>
                      <li class="treeview">
                      <a href="#">
                          <i class="fa fa-cubes"></i>
                          <span>�S���x�޲z</span><span class="pull-right-container">
                              <i class="fa fa-angle-down pull-right"></i>
                          </span>
                      </a>
                      <ul class="treeview-menu">
                          <li><a href="#">���u�޲z</a></li>
                          <li><a href="#">���u�v���޲z</a></li>
                          <li><a href="#">�t�ΰѼƺ޲z</a></li>
                      </ul>
                      </li>
                      <li class="treeview">
                        <a href="#">
                            <i class="fa fa-user-circle"></i>
                            <span>�|���޲z</span><span class="pull-right-container">
                                <i class="fa fa-angle-down pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#">�|����ƺ޲z</a></li>
                        </ul>
                        </li>
                      <li class="treeview">
                      <a href="#">
                          <i class="fa fa-bed"></i>
                          <span>�q�Ы�x�޲z</span><span class="pull-right-container">
                              <i class="fa fa-angle-down pull-right"></i>
                          </span>
                      </a>
                      <ul class="treeview-menu">
                          <li><a href="<%=request.getContextPath()%>/back-end/room-type/RoomTypeInfo.jsp">�Ы��޲z</a></li>
                          <li><a href="<%=request.getContextPath()%>/back-end/room/RoomInfo.jsp">�ж��޲z</a></li>
                          <li><a href="#">��Эq��޲z</a></li>
                          <li><a href="#">��µ�޲z</a></li>
                          <li><a href="#">�J��޲z</a></li>
                          <li><a href="#">�h�к޲z</a></li>
                      </ul>
                      </li>
                      <li class="treeview">
                          <a href="#">
                              <i class="fa fa-shopping-cart"></i>
                              <span>�ӫ���x�޲z</span><span class="pull-right-container">
                                  <i class="fa fa-angle-down pull-right"></i>
                              </span>
                          </a>
                          <ul class="treeview-menu">
                              <li><a href="#">�P��έp�޲z</a></li>
                              <li><a href="#">�ӫ~�޲z</a></li>
                              <li><a href="#">�ӫ~�q��޲z</a></li>
                          </ul>
                      </li>
                      <li class="treeview">
                          <a href="#">
                              <i class="fa fa-plane"></i>
                              <span>���ʫ�x�޲z</span><span class="pull-right-container">
                                  <i class="fa fa-angle-down pull-right"></i>
                              </span>
                          </a>
                          <ul class="treeview-menu">
                              <li><a href="<%= request.getContextPath() %>/back-end/activity/selectPage.jsp">���ʤ��e�޲z</a></li>
                              <li><a href="<%= request.getContextPath() %>/back-end/actOrder/selectPage.jsp">���ʳ��W�޲z</a></li>
                              <li><a href="<%= request.getContextPath() %>/back-end/actComment/selectPage.jsp">���ʵ��׺޲z</a></li>
                              <li><a href="<%= request.getContextPath() %>/back-end/actcategory/selectPage.jsp">�������O�޲z</a></li>
                          </ul>
                      </li>
                  </ul>
                </section>
            </aside>
            <div class="content-wrapper text-tab active">
                <section class="content container-fluid">

</body>
</html>