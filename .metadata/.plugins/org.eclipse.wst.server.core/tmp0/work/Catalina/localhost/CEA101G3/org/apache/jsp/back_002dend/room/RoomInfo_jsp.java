/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.38
 * Generated at: 2020-12-24 11:27:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.back_002dend.room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.roomtype.model.*;
import com.roomphoto.model.*;

public final class RoomInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/back-end/room/backIndex.file", Long.valueOf(1608636200000L));
    _jspx_dependants.put("/back-end/room/backIndex2.file", Long.valueOf(1608636200000L));
    _jspx_dependants.put("/WEB-INF/lib/standard.jar", Long.valueOf(1608636200000L));
    _jspx_dependants.put("jar:file:/Users/tomgu/CEA101_WebApp/CEA101G3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CEA101G3/WEB-INF/lib/standard.jar!/META-INF/c.tld", Long.valueOf(1064305020000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.roomphoto.model");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("com.roomtype.model");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("    <title>露營管理後台</title>\n");
      out.write("    <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.1.2/collection/icon/icon.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/back-end/admin.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/back-end/context.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/back-end/style.css\">\n");
      out.write("</head>\n");
      out.write("\t<body class=\"hold-transition skin-blue sidebar-mini\">\n");
      out.write("            <header class=\"main-header\">\n");
      out.write("                <a href=\"index2.html\" class=\"logo\">\n");
      out.write("                    <span class=\"logo-mini\"><b>M</b></span>\n");
      out.write("                    <span class=\"logo-lg\"><b>Maison </b> Camp</span>\n");
      out.write("                </a>\n");
      out.write("                <nav class=\"navbar navbar-static-top\" role=\"navigation\">\n");
      out.write("                    <a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">\n");
      out.write("                        <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    </a>\n");
      out.write("                    <div class=\"navbar-custom-menu\">\n");
      out.write("                        <ul class=\"nav navbar-nav\">\n");
      out.write("                            <li class=\"dropdown user user-menu\">\n");
      out.write("                                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">         \n");
      out.write("                                    <span class=\"hidden-xs\">香港山雞</span>\n");
      out.write("                                </a>\n");
      out.write("                                <ul class=\"dropdown-menu\">                           \n");
      out.write("                                    <li class=\"\">                                     \n");
      out.write("                                        <a href=\"#\" class=\"\">員工個人資料</a>         \n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a href=\"#\" class=\"\">管理員登出</a>\n");
      out.write("                                    </li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                </nav>\n");
      out.write("            </header>\n");
      out.write("            <aside class=\"main-sidebar\">\n");
      out.write("                <section class=\"sidebar\">\n");
      out.write("                  <div class=\"user-panel\">\n");
      out.write("                        <div class=\"pull-left image\">\n");
      out.write("                            <img src=\"");
      out.print(request.getContextPath());
      out.write("/img/back/054101015D42DFD18B366090E3C6D8B5.png\" class=\"img-circle\" alt=\"User Image\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"pull-left info\">\n");
      out.write("                            <p>香港山雞</p>\n");
      out.write("                            <a href=\"#\"><i class=\"fa fa-circle text-success\"></i> Online</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                  <ul class=\"sidebar-menu\" data-widget=\"tree\">\n");
      out.write("                      <a href=\"");
      out.print(request.getContextPath());
      out.write("/back-end/back-index.jsp\"><li class=\"header\">管理員介面</li></a>\n");
      out.write("                      <li class=\"treeview\">\n");
      out.write("                          <a href=\"#\">\n");
      out.write("                              <i class=\"fa fa-desktop\"></i>\n");
      out.write("                              <span>前台頁面管理</span><span class=\"pull-right-container\">\n");
      out.write("                                  <i class=\"fa fa-angle-down pull-right\"></i>\n");
      out.write("                              </span>\n");
      out.write("                          </a>\n");
      out.write("                          <ul class=\"treeview-menu\">\n");
      out.write("                              <li><a href=\"#\">最新消息管理</a></li>\n");
      out.write("                              <li><a href=\"#\">評論管理</a></li>\n");
      out.write("                              <li><a href=\"#\">促銷專案管理</a></li>\n");
      out.write("                              <li><a href=\"#\">廣告管理</a></li>\n");
      out.write("                          </ul>\n");
      out.write("                      </li>\n");
      out.write("                      <li class=\"treeview\">\n");
      out.write("                      <a href=\"#\">\n");
      out.write("                          <i class=\"fa fa-cubes\"></i>\n");
      out.write("                          <span>露營後台管理</span><span class=\"pull-right-container\">\n");
      out.write("                              <i class=\"fa fa-angle-down pull-right\"></i>\n");
      out.write("                          </span>\n");
      out.write("                      </a>\n");
      out.write("                      <ul class=\"treeview-menu\">\n");
      out.write("                          <li><a href=\"#\">員工管理</a></li>\n");
      out.write("                          <li><a href=\"#\">員工權限管理</a></li>\n");
      out.write("                          <li><a href=\"#\">系統參數管理</a></li>\n");
      out.write("                      </ul>\n");
      out.write("                      </li>\n");
      out.write("                      <li class=\"treeview\">\n");
      out.write("                        <a href=\"#\">\n");
      out.write("                            <i class=\"fa fa-user-circle\"></i>\n");
      out.write("                            <span>會員管理</span><span class=\"pull-right-container\">\n");
      out.write("                                <i class=\"fa fa-angle-down pull-right\"></i>\n");
      out.write("                            </span>\n");
      out.write("                        </a>\n");
      out.write("                        <ul class=\"treeview-menu\">\n");
      out.write("                            <li><a href=\"#\">會員資料管理</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                        </li>\n");
      out.write("                      <li class=\"treeview\">\n");
      out.write("                      <a href=\"#\">\n");
      out.write("                          <i class=\"fa fa-bed\"></i>\n");
      out.write("                          <span>訂房後台管理</span><span class=\"pull-right-container\">\n");
      out.write("                              <i class=\"fa fa-angle-down pull-right\"></i>\n");
      out.write("                          </span>\n");
      out.write("                      </a>\n");
      out.write("                      <ul class=\"treeview-menu\">\n");
      out.write("                          <li><a href=\"");
      out.print(request.getContextPath());
      out.write("/back-end/room-type/RoomTypeInfo.jsp\">房型管理</a></li>\n");
      out.write("                          <li><a href=\"");
      out.print(request.getContextPath());
      out.write("/back-end/room/RoomInfo.jsp\">房間管理</a></li>\n");
      out.write("                          <li><a href=\"#\">住房訂單管理</a></li>\n");
      out.write("                          <li><a href=\"#\">修繕管理</a></li>\n");
      out.write("                          <li><a href=\"#\">入住管理</a></li>\n");
      out.write("                          <li><a href=\"#\">退房管理</a></li>\n");
      out.write("                      </ul>\n");
      out.write("                      </li>\n");
      out.write("                      <li class=\"treeview\">\n");
      out.write("                          <a href=\"#\">\n");
      out.write("                              <i class=\"fa fa-shopping-cart\"></i>\n");
      out.write("                              <span>商城後台管理</span><span class=\"pull-right-container\">\n");
      out.write("                                  <i class=\"fa fa-angle-down pull-right\"></i>\n");
      out.write("                              </span>\n");
      out.write("                          </a>\n");
      out.write("                          <ul class=\"treeview-menu\">\n");
      out.write("                              <li><a href=\"#\">銷售統計管理</a></li>\n");
      out.write("                              <li><a href=\"#\">商品管理</a></li>\n");
      out.write("                              <li><a href=\"#\">商品訂單管理</a></li>\n");
      out.write("                          </ul>\n");
      out.write("                      </li>\n");
      out.write("                      <li class=\"treeview\">\n");
      out.write("                          <a href=\"#\">\n");
      out.write("                              <i class=\"fa fa-plane\"></i>\n");
      out.write("                              <span>活動後台管理</span><span class=\"pull-right-container\">\n");
      out.write("                                  <i class=\"fa fa-angle-down pull-right\"></i>\n");
      out.write("                              </span>\n");
      out.write("                          </a>\n");
      out.write("                          <ul class=\"treeview-menu\">\n");
      out.write("                              <li><a href=\"#\">活動內容管理</a></li>\n");
      out.write("                              <li><a href=\"#\">活動報名管理</a></li>\n");
      out.write("                              <li><a href=\"#\">活動排程</a></li>\n");
      out.write("                          </ul>\n");
      out.write("                      </li>\n");
      out.write("                  </ul>\n");
      out.write("                </section>\n");
      out.write("            </aside>\n");
      out.write("            <div class=\"content-wrapper text-tab active\">\n");
      out.write("                <section class=\"content container-fluid\">");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>房間管理</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/back-end/room.css\" />\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/bootstrap/bootstrap.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.10.0/css/all.css\" />\n");
      out.write("<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n");
      out.write("<script src=\"http://code.jquery.com/jquery-1.12.4.min.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/back-end/room/RoomInfo.jsp\"><h2 style=\"text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;\">房間管理</h2></a>\n");
      out.write("\t<div id=\"container\">\n");
      out.write("\t\t<input class=\"title\" id=\"tab-1\" type=\"radio\" name=\"tab-group\" checked=\"checked\" />\n");
      out.write("\t\t<label for=\"tab-1\">查看所有房間</label> \n");
      out.write("\t\t\n");
      out.write("\t\t<input class=\"title\" id=\"tab-2\" type=\"radio\" name=\"tab-group\"/> \n");
      out.write("\t\t<label for=\"tab-2\">新增房間</label> \n");
      out.write("\t\t\n");
      out.write("\t\t<input class=\"title\" id=\"tab-3\" type=\"radio\" name=\"tab-group\"/> \n");
      out.write("\t\t<label for=\"tab-3\">預設空白</label>\n");
      out.write("\t\t\n");
      out.write("\t\t<input class=\"title\" id=\"tab-4\" type=\"radio\" name=\"tab-group\"/> \n");
      out.write("\t\t<label for=\"tab-4\">等我想到再說</label>\n");
      out.write("\t\t\n");
      out.write("\t\t<div id=\"content\">\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "CheckRoom.jsp", out, false);
      out.write("\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "AddRoom.jsp", out, false);
      out.write("\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "default1.jsp", out, false);
      out.write("\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "default2.jsp", out, false);
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t");
      out.write("\n");
      out.write("\n");
      out.write("</section>\n");
      out.write("            </div>\n");
      out.write("        <footer class=\"main-footer\">\n");
      out.write("            <strong>Copyright &copy; 2020<a href=\"#\"> Maison Camp</a>.</strong> All rights reserved.\n");
      out.write("        </footer>\n");
      out.write("    \n");
      out.write("        \n");
      out.write("        <!-- Add the sidebar's background. This div must be placed\n");
      out.write("        immediately after the control sidebar -->\n");
      out.write("        <div class=\"control-sidebar-bg\"></div>\n");
      out.write("        </div>\n");
      out.write("        <!-- ./wrapper -->\n");
      out.write("        <!-- REQUIRED JS SCRIPTS -->\n");
      out.write("        <!-- jQuery 3 -->\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\" integrity=\"sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <!-- Bootstrap 3.3.7 -->\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <!-- AdminLTE App -->\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.3/js/adminlte.min.js\"></script> \n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/js/back-end/backer.js\"></script>\n");
      out.write("\t</body>\n");
      out.write("</html>");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
