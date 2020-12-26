/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.38
 * Generated at: 2020-12-26 02:50:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.front_002dend.room_002dbooking;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class RoomBooking_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<link href=\"https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900\" rel=\"stylesheet\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.10.0/css/all.css\" />\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/front-end/room-booking.css\">\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/png\" href=\"");
      out.print(request.getContextPath());
      out.write("/img/logo.png\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\">\n");
      out.write("<title>Maison Camp | 訂房系統</title>\n");
      out.write("</head>\n");
      out.write("    <body>\n");
      out.write("        <header class=\"header\">\n");
      out.write("            <nav role=\"navigation\">\n");
      out.write("                <div id=\"menuToggle\">\n");
      out.write("                    <input type=\"checkbox\" id=\"checkboxtoggle\"/>\n");
      out.write("                    <span></span>\n");
      out.write("                    <span></span>\n");
      out.write("                    <span></span>\n");
      out.write("                    <ul id=\"menu\">\n");
      out.write("                        <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/front-index.jsp\"><li>首頁</li></a>\n");
      out.write("                        <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/news/News.jsp\"><li>最新消息</li></a>\n");
      out.write("                        <a href=\"#\"><li>會員登入</li></a>\n");
      out.write("                        <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-type/RoomType.jsp\"><li>帳型介紹</li></a>\n");
      out.write("                        <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-booking/RoomBooking.jsp\"><li>立即訂房</li></a>\n");
      out.write("                        <a href=\"#\"><li>精選商城</li></a>\n");
      out.write("                        <a href=\"#\"><li>活動預約</li></a>\n");
      out.write("                        <a href=\"#\"><li>聯絡我們</li></a>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </nav>          \n");
      out.write("            <a href=\"#\"><img id=\"logoo\" class=\"img-logo\" src=\"");
      out.print(request.getContextPath());
      out.write("/img/logo.png\" alt=\"\"></a>         \n");
      out.write("        </header>\n");
      out.write("        <!-- content header -->\n");
      out.write("        <div class=\"title-text\">\n");
      out.write("          <div>線上訂房</div>\n");
      out.write("        </div>\n");
      out.write("        <!-- booking calendar -->\n");
      out.write("        <div class=\"news-title\">\n");
      out.write("\t\t\t<div class=\"container content-xs\">\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"picknight\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<form class=\"sky-form\" action=\"\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-sm-5\" style=\"display:inline-flex;\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-3 text-right control-label\">選擇類型:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-9\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<section>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"select\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<select class=\"change-room\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">兩人帳</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">兩床帳</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">四床帳</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</section>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-sm-4\" style=\"display:inline-flex; margin-left:-10px;\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-6 text-right control-label\">使用天數:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-6\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<section>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"select\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<select class=\"change-room\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">1</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">2</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">3</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">4</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">5</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">6</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">7</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</section>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-sm-3\" style=\"display:inline-flex;\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-4 text-right control-label\">數量:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-8\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<section>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"select\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<select class=\"change-room\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">1</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">2</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">3</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</section>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<section>\n");
      out.write("\t\t\t\t\t<div class=\"table-responsive\">\n");
      out.write("\t\t\t\t\t\t<div class=\"ui-datepicker-inline ui-datepicker\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"card\">\n");
      out.write("\t\t\t\t\t            <div style=\"display:inline-flex; justify-content: space-between; background-color:#eeeeee;\">\n");
      out.write("\t\t\t\t\t                <a class=\"\" id=\"prev\" onclick=\"prev()\"><i class=\"fas fa-less-than fa-1x\"></i></a>\n");
      out.write("\t\t\t\t\t            \t<h4 class=\"card-title\" id=\"mmYY\" style=\"margin-top:10px; color:#666666;\">月/年</h4>\n");
      out.write("\t\t\t\t\t                <a class=\"\" id=\"next\" onclick=\"next()\"><i class=\"fas fa-greater-than fa-1x\"></i></a>\n");
      out.write("\t\t\t\t\t            </div>\n");
      out.write("\t\t\t\t\t            <table class=\"table table-bordered\" id=\"dateDetail\" style=\"text-align:center;\">\n");
      out.write("\t\t\t\t\t                <thead>\n");
      out.write("\t\t\t\t\t                    <tr>\n");
      out.write("\t\t\t\t\t                        <th class=\"holiday\">日</th>\n");
      out.write("\t\t\t\t\t                        <th>一</th>\n");
      out.write("\t\t\t\t\t                        <th>二</th>\n");
      out.write("\t\t\t\t\t                        <th>三</th>\n");
      out.write("\t\t\t\t\t                        <th>四</th>\n");
      out.write("\t\t\t\t\t                        <th>五</th>\n");
      out.write("\t\t\t\t\t                        <th class=\"holiday\">六</th>\n");
      out.write("\t\t\t\t\t                    </tr>\n");
      out.write("\t\t\t\t\t                </thead>\n");
      out.write("\t\t\t\t\t                <tbody id=\"body\">\t\t\t\n");
      out.write("\t\t\t\t\t                </tbody>\n");
      out.write("\t\t\t\t\t            </table>\t\t\t\t            \n");
      out.write("\t\t\t\t\t        </div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\t\t\n");
      out.write("\t\t\t\t</section>\n");
      out.write("\t\t\t</div>\n");
      out.write("        </div>\n");
      out.write("        <footer class=\"site-footer\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                <div class=\"col-sm-12 col-md-6\">\n");
      out.write("                    <h6>關於我們</h6>\n");
      out.write("                    <p class=\"text-justify\"><i>Maison Camp</i> 豪華露營提供多項的活動的安排與遊憩設施。<br>適合親子或三五好友一同前來放鬆體驗。<br>豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗。<br>快來與我們一起聽流水蟲鳴鳥叫 與森林一同呼吸。</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-6 col-md-2\">\n");
      out.write("                    <h6>快速連結</h6>\n");
      out.write("                    <ul class=\"footer-links\">\n");
      out.write("                    <li><a href=\"\">會員登入</a></li>\n");
      out.write("                    <li><a href=\"\">立即訂房</a></li>\n");
      out.write("                    <li><a href=\"\">精選商城</a></li>\n");
      out.write("                    <li><a href=\"\">預約活動</a></li>\n");
      out.write("                    <li><a href=\"\">聯繫我們</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-6 col-md-3\">\n");
      out.write("                    <a href=\"\"><img class=\"footer-map\" src=\"");
      out.print(request.getContextPath());
      out.write("/img/footer.png\" style=\"height: 160px; width: 300px;\" alt=\"\"></a>\n");
      out.write("                </div>\n");
      out.write("                </div>\n");
      out.write("                <hr>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                <div class=\"col-md-8 col-sm-6 col-xs-12\">\n");
      out.write("                    <p class=\"copyright-text\">Copyright &copy; 2021 All Rights Reserved by \n");
      out.write("                <a href=\"#\">Maison Camp</a>.\n");
      out.write("                    </p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-4 col-sm-6 col-xs-12\">\n");
      out.write("                    <ul class=\"social-icons\">\n");
      out.write("                    <li><a class=\"facebook\" href=\"#\"><i class=\"fab fa-facebook-f\"></i></a></li>\n");
      out.write("                    <li><a class=\"twitter\" href=\"#\"><i class=\"fab fa-twitter\"></i></a></li>\n");
      out.write("                    <li><a class=\"dribbble\" href=\"#\"><i class=\"fab fa-dribbble\"></i></a></li>\n");
      out.write("                    <li><a class=\"linkedin\" href=\"#\"><i class=\"fab fa-linkedin\"></i></a></li>   \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("      \n");
      out.write("\n");
      out.write("      <!-- footer -->\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/js/front-end/room-booking.js\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\"></script>\n");
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
