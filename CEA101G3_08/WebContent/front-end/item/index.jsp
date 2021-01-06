<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>露營管理後台</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.1.2/collection/icon/icon.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.3/css/AdminLTE.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.3/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<body>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Main Header -->
            <header class="main-header">
                <!-- Logo -->
                <a href="index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>M</b></span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Maison </b> Camp</span>
                </a>
                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->
                            <li class="dropdown messages-menu">
                                <!-- Menu toggle button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-envelope-o"></i>
                                    <span class="label label-success">4</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 4 messages</li>
                                    <li>
                                        <!-- inner menu: contains the messages -->
                                        <ul class="menu">
                                            <li>
                                                <!-- start message -->
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <!-- User Image -->
                                                        <img src="images/十三妹.png" class="img-circle" alt="User Image">
                                                    </div>
                                                    <!-- Message title and timestamp -->
                                                    <h4>
                                                        Support Team
                                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                    </h4>
                                                    <!-- The message -->
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <!-- end message -->
                                        </ul>
                                        <!-- /.menu -->
                                    </li>
                                    <li class="footer"><a href="#">See All Messages</a></li>
                                </ul>
                            </li>
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="images/十三妹.png" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs">洪興十三妹</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="images/十三妹.png" class="img-circle" alt="User Image">
                                        <p>
                                            Hong Kong 十三妹 - Web Developer
                                            <small>Employee since Nov. 2020</small>
                                        </p>
                                    </li>
                                    <!-- Menu Body -->
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Followers</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Sales</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Friends</a>
                                            </div>
                                        </div>
                                        <!-- /.row -->
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->
                            <li>
                                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="images/十三妹.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>洪興十三妹</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">管理員介面</li>
                        <!--Optionally, you can add icons to the links-->
                        <li class="active"><a href="#"><i class="fa fa-home"></i> <span>管理員登入</span></a></li>
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
                                <li><a href="#">會員資料管理</a></li>
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
                                <li><a href="#">房型管理</a></li>
                                <li><a href="#">房間管理</a></li>
                                <li><a href="#">住房訂單管理</a></li>
                                <li><a href="#">修繕管理</a></li>
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
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!--Content Header (Page header)-->
                <section class="content-header">
                    <h1>商品管理</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                        <li class="active">Here</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content container-fluid">
                    <!--------------------------
                  | Your Page Content Here |
                  -------------------------->
                    <form class="example" action="action_page.php">
                        <input type="text" placeholder="搜尋商品" name="search">
                    </form>
                    <p></p>
                    <hr />
                    <h2><b class="fa fa-user-circle"> 商品資料</b></h2>
                    <table id="myTable">
				<jsp:include page="select_page.jsp"></jsp:include>
                    </table>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <!-- Main Footer -->
            <footer class="main-footer">
                <!-- To the right -->
                <div class="pull-right hidden-xs">
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2020<a href="#"> Maison Camp</a>.</strong> All rights reserved.
            </footer>
            <!-- Add the sidebar's background. This div must be placed
        immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->
        <!-- REQUIRED JS SCRIPTS -->
        <!-- jQuery 3 -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.3/js/adminlte.min.js"></script>
    </body>

</html>