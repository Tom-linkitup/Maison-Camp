/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.38
 * Generated at: 2020-12-29 10:10:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.front_002dend.room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.roomtype.model.*;
import com.roomphoto.model.*;
import com.room.model.*;

public final class RoomDetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
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
    _jspx_imports_packages.add("com.room.model");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;

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
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
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
      out.write("\n");
      out.write("\n");
	
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> roomTypeList = roomTypeSvc.getAllRT();
	pageContext.setAttribute("roomTypeList", roomTypeList);	
	
	String room_category_id = request.getParameter("room_category_id");
	RoomTypeService roomTypeSvc2 = new RoomTypeService();
	RoomTypeVO roomTypeVO = roomTypeSvc2.getOneRT(room_category_id);
	pageContext.setAttribute("roomTypeVO", roomTypeVO);
	
	RoomPhotoService roomPhotoSvc = new RoomPhotoService();
	List<RoomPhotoVO> rphList = roomPhotoSvc.getAllRPH(room_category_id);
	pageContext.setAttribute("rphList", rphList);
	
	RoomService roomSvc = new RoomService();
	List<RoomVO> roomList = roomSvc.getRmByRTC(room_category_id);
	for(RoomVO roomVO : roomList){
		pageContext.setAttribute("roomVO", roomVO);
	}	

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/front-end/room-detail.css\">\n");
      out.write("        <link rel=\"shortcut icon\" type=\"image/png\" href=\"");
      out.print(request.getContextPath());
      out.write("/img/logo.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"flexslider.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">\n");
      out.write("        <title>Maison Camp | 房間介紹</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("      <header class=\"header\">\n");
      out.write("          <nav role=\"navigation\">\n");
      out.write("              <div id=\"menuToggle\">\n");
      out.write("                  <input type=\"checkbox\" id=\"checkboxtoggle\"/>\n");
      out.write("                  <span></span>\n");
      out.write("                  <span></span>\n");
      out.write("                  <span></span>\n");
      out.write("                  <ul id=\"menu\">\n");
      out.write("                      <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/front-index.jsp\"><li>首頁</li></a>\n");
      out.write("                      <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/news/News.jsp\"><li>最新消息</li></a>\n");
      out.write("                      <a href=\"#\"><li>會員登入</li></a>\n");
      out.write("                      <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-type/roomType.jsp\"><li>帳型介紹</li></a>\n");
      out.write("                      <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-booking/RoomBooking.jsp\"><li>立即訂房</li></a>\n");
      out.write("                      <a href=\"#\"><li>精選商城</li></a>\n");
      out.write("                      <a href=\"#\"><li>活動預約</li></a>\n");
      out.write("                      <a href=\"#\"><li>聯絡我們</li></a>\n");
      out.write("                  </ul>\n");
      out.write("              </div>\n");
      out.write("          </nav>          \n");
      out.write("          <a href=\"#\"><img id=\"logoo\" class=\"img-logo\" src=\"");
      out.print(request.getContextPath());
      out.write("/img/logo.png\" alt=\"\"></a>         \n");
      out.write("      </header>\n");
      out.write("      <!-- float-sidebar -->\n");
      out.write("      <div id=\"float-sidebar\">\n");
      out.write("        <div id=\"float-fb\">\n");
      out.write("          <a href=\"#\">\n");
      out.write("            <i class=\"fa fa-comment\"></i><br>\n");
      out.write("            評論區\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"float-booking\">\n");
      out.write("          <a href=\"#\">\n");
      out.write("            <i class=\"fa fa-calendar\"></i><br>\n");
      out.write("            入營預約\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"float-promo\">\n");
      out.write("          <a href=\"#\">\n");
      out.write("            <i class=\"fa fa-bed\"></i><br>\n");
      out.write("            優惠方案\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"float-top\">\n");
      out.write("          <a href=\"#\">\n");
      out.write("            <i class=\"fa fa-chevron-up\"></i><br>\n");
      out.write("            Top\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("      <!-- content-header -->\n");
      out.write("      <div class=\"title-text\">\n");
      out.write("        <div>房間介紹</div>\n");
      out.write("      </div>\n");
      out.write("      <!-- content -->\n");
      out.write("      <div class=\"purchase-title\">      \n");
      out.write("        <div class=\"container content-main\">\n");
      out.write("          <div class=\"row content-row\">\n");
      out.write("            <div class=\"main col-md-9\">\n");
      out.write("              <div class=\"row\">\n");
      out.write("                <div class=\"col-md-12 col-xs-12 col-12 col-12 faq-content\">\n");
      out.write("                  <div class=\"row\">\n");
      out.write("                    <div class=\"col-lg-10 col-md-10 col-sm-12 col-xs-12 col-12\">\n");
      out.write("                      <h5>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h5>\n");
      out.write("                    </div>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                      <div class=\"flexslider\">\n");
      out.write("                        <ul class=\"slides\">\n");
      out.write("                        ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      boolean _jspx_th_c_005fforEach_005f0_reused = false;
      try {
        _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
        _jspx_th_c_005fforEach_005f0.setParent(null);
        // /front-end/room/RoomDetail.jsp(109,24) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f0.setVar("photo");
        // /front-end/room/RoomDetail.jsp(109,24) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rphList}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
        int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
        try {
          int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
          if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\n");
              out.write("                          <li data-thumb=\"");
              out.print(request.getContextPath());
              out.write("/PhotoList.do?room_photo_id=");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${photo.room_photo_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
              out.write("\">\n");
              out.write("                            <img src=\"");
              out.print(request.getContextPath());
              out.write("/PhotoList.do?room_photo_id=");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${photo.room_photo_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
              out.write("\" />\n");
              out.write("                          </li> \n");
              out.write("                        ");
              int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            return;
          }
        } catch (java.lang.Throwable _jspx_exception) {
          while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
            out = _jspx_page_context.popBody();
          _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
        } finally {
          _jspx_th_c_005fforEach_005f0.doFinally();
        }
        _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
        _jspx_th_c_005fforEach_005f0_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
      }
      out.write("                                      \n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                    </div>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row content-title col-md-12\">                   \n");
      out.write("                    <div class=\"headline\">\n");
      out.write("                      <h4>類型介紹</h4>\n");
      out.write("                    </div>                    \n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row col-md-12\">\n");
      out.write("                    <div class=\"stmt-headline\">\n");
      out.write("                      <h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h3>\n");
      out.write("                    </div>              \n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row text-content col-md-12\">\n");
      out.write("                    <div class=\"line\">\n");
      out.write("                      <span>房型及價格說明：</span>\n");
      out.write("                    </div>\n");
      out.write("                    <div>\n");
      out.write("                      <table class=\"table table-bordered\">\n");
      out.write("                        <tr class=\"room-stmt-head\">\n");
      out.write("                          <th>房型名稱</th>\n");
      out.write("                          <th>定價</th>\n");
      out.write("                          <th>坪數</th>\n");
      out.write("                          <th>可住人數</th>\n");
      out.write("                          <th>可否加床</th>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr class=\"room-stmt\">\n");
      out.write("                          <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("                          <td>NT$");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_price}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("                          <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.area}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("坪</td>\n");
      out.write("                          <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_guest}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("人</td>\n");
      out.write("                          <td>可</td>\n");
      out.write("                        </tr>\n");
      out.write("                      </table>\n");
      out.write("                    </div>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row text-content col-md-12\">\n");
      out.write("                    <div class=\"line\">\n");
      out.write("                      <span>類型說明：</span>\n");
      out.write("                    </div>\n");
      out.write("                    <p>\n");
      out.write("                      以上費用均已含10%服務費。\n");
      out.write("                      <br>\n");
      out.write("                      1. 入帳時間：15:00後，離帳時間：11:00前。\n");
      out.write("                      <br>\n");
      out.write("                      2. 此帳型價格內含提供至多");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_guest}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("人早餐；如有超過之使用人數，需依現場收費公告為主，收取相關費用。\n");
      out.write("                      <br>\n");
      out.write("                      3. 此帳型限加一床；至多");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_guest+1}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("人入住。\n");
      out.write("                      <br>\n");
      out.write("                      4. 如您實際入住人數(包含大人+小孩)，超過上述之人數規定，本莊園將有權利取消您的訂位，並依取消規定，收取相關費用。\n");
      out.write("                      <br>\n");
      out.write("                      5. 如有異動，需依現場公告為主。\n");
      out.write("                    </p>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row text-content col-md-12\">\n");
      out.write("                    <div class=\"line\">\n");
      out.write("                      <span>設施介紹：</span>\n");
      out.write("                    </div>\n");
      out.write("                    <p>\n");
      out.write("                      全國首創的豪華帳篷，每頂帳篷裡皆有獨立的衛浴設備，選用飯店級舒適床墊與寢具，帳篷內亦提供盥洗清潔用品組、大小浴巾、吹風機、快煮壺、室內拖鞋，輕鬆入住，是享受戶外美學和大自然的最佳體驗方式。\n");
      out.write("                    </p>\n");
      out.write("                  </div>\n");
      out.write("                  <div class=\"row text-content col-md-12\">\n");
      out.write("                    <div class=\"line\">\n");
      out.write("                      <span>帳內設備：</span>\n");
      out.write("                      <div class=\"row iconbox\">\n");
      out.write("                        <ul class=\"row post-share\">\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil1.gif\" alt=\"\" title=\"洗髮精\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil2.gif\" alt=\"\" title=\"牙刷牙膏\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil3.gif\" alt=\"\" title=\"刮鬍刀\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil4.gif\" alt=\"\" title=\"吹風機\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil5.gif\" alt=\"\" title=\"梳子\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil6.gif\" alt=\"\" title=\"毛巾\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil7.gif\" alt=\"\" title=\"浴巾\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil8.gif\" alt=\"\" title=\"棉花棒\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil9.gif\" alt=\"\" title=\"個人淋浴間\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil10.gif\" alt=\"\" title=\"室內用拖鞋\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil11.gif\" alt=\"\" title=\"雨傘\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil12.gif\" alt=\"\" title=\"熱水瓶\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil13.gif\" alt=\"\" title=\"免費礦泉水\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil14.gif\" alt=\"\" title=\"暖氣機\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil16.gif\" alt=\"\" title=\"冷氣機\"></li>\n");
      out.write("                          <li><img src=\"");
      out.print(request.getContextPath());
      out.write("/img/room-util/roomutil15.gif\" alt=\"\" title=\"客房鑰匙\"></li>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                    </div>                                 \n");
      out.write("                  </div> \n");
      out.write("                  <div class=\"reserve\">\n");
      out.write("                    <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-booking/RoomBooking.jsp\"><button class=\"btn btn-danger\" type=\"submit\">立即預約</button></a>\n");
      out.write("                  </div>                \n");
      out.write("                </div>                      \n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"content-sidebar\">\n");
      out.write("              <table>\n");
      out.write("                <tbody>\n");
      out.write("                  <tr class=\"content-sidebar-first\">\n");
      out.write("                    <td>\n");
      out.write("                      <span>\n");
      out.write("                        <strong>房型一覽</strong>\n");
      out.write("                      </span>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("                  <tr>\n");
      out.write("                    <td>\n");
      out.write("                      <ul>\n");
      out.write("                        ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      boolean _jspx_th_c_005fforEach_005f1_reused = false;
      try {
        _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
        _jspx_th_c_005fforEach_005f1.setParent(null);
        // /front-end/room/RoomDetail.jsp(221,24) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f1.setVar("roomType");
        // /front-end/room/RoomDetail.jsp(221,24) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeList}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
        int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
        try {
          int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
          if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\n");
              out.write("                      \t");
              //  c:choose
              org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
              boolean _jspx_th_c_005fchoose_005f0_reused = false;
              try {
                _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
                _jspx_th_c_005fchoose_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
                int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
                if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("                      \t\t");
                    //  c:when
                    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
                    boolean _jspx_th_c_005fwhen_005f0_reused = false;
                    try {
                      _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
                      _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
                      // /front-end/room/RoomDetail.jsp(223,24) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                      _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomType.room_category_status == '0'}", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
                      int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
                      if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        do {
                          out.write("\n");
                          out.write("                        \t\t<li><a href=\"");
                          out.print(request.getContextPath());
                          out.write("/front-end/room/RoomDetail.jsp?room_category_id=");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomType.room_category_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write('"');
                          out.write('>');
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomType.room_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
                          out.write("</a></li>\n");
                          out.write("                      \t\t");
                          int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
                          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                            break;
                        } while (true);
                      }
                      if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        return;
                      }
                      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
                      _jspx_th_c_005fwhen_005f0_reused = true;
                    } finally {
                      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fwhen_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fwhen_005f0_reused);
                    }
                    out.write("\n");
                    out.write("                      \t");
                    int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  return;
                }
                _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
                _jspx_th_c_005fchoose_005f0_reused = true;
              } finally {
                org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fchoose_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fchoose_005f0_reused);
              }
              out.write("\n");
              out.write("                      ");
              int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            return;
          }
        } catch (java.lang.Throwable _jspx_exception) {
          while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
            out = _jspx_page_context.popBody();
          _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
        } finally {
          _jspx_th_c_005fforEach_005f1.doFinally();
        }
        _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
        _jspx_th_c_005fforEach_005f1_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f1, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f1_reused);
      }
      out.write("\n");
      out.write("                      </ul>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("                </tbody>\n");
      out.write("              </table>\n");
      out.write("              <p></p>\n");
      out.write("              <table>\n");
      out.write("                <tbody>\n");
      out.write("                  <tr class=\"content-sidebar-second\">\n");
      out.write("                    <td style=\"background-color: #d96166;\">\n");
      out.write("                      <span>\n");
      out.write("                        <img src=\"");
      out.print(request.getContextPath());
      out.write("/img/alert.png\" alt=\"\">\n");
      out.write("                        <span>目前可預訂時間</span>\n");
      out.write("                      </span>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("                  <tr>\n");
      out.write("                    <td style=\"background-color: #faf9f2;\">\n");
      out.write("                      <span>\n");
      out.write("                        <span>豪華露營可預約時間：</span>\n");
      out.write("                      </span>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("                </tbody>\n");
      out.write("              </table>\n");
      out.write("            </div> \n");
      out.write("          </div>   \n");
      out.write("        </div>     \n");
      out.write("      <footer class=\"site-footer\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("          <div class=\"row\">\n");
      out.write("            <div class=\"col-sm-12 col-md-6\">\n");
      out.write("              <h6>關於我們</h6>\n");
      out.write("              <p class=\"text-justify\"><i>Maison Camp</i> 豪華露營提供多項的活動的安排與遊憩設施。<br>適合親子或三五好友一同前來放鬆體驗。<br>豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗。<br>快來與我們一起聽流水蟲鳴鳥叫 與森林一同呼吸。</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-xs-6 col-md-2\">\n");
      out.write("              <h6>快速連結</h6>\n");
      out.write("              <ul class=\"footer-links\">\n");
      out.write("                <li><a href=\"\">會員登入</a></li>\n");
      out.write("                <li><a href=\"\">立即訂房</a></li>\n");
      out.write("                <li><a href=\"\">精選商城</a></li>\n");
      out.write("                <li><a href=\"\">預約活動</a></li>\n");
      out.write("                <li><a href=\"\">聯繫我們</a></li>\n");
      out.write("              </ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-xs-6 col-md-3\">\n");
      out.write("              <a href=\"\"><img class=\"footer-map\" src=\"");
      out.print(request.getContextPath());
      out.write("/img/footer.png\" style=\"height: 160px; width: 300px;\" alt=\"\"></a>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("          <hr>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"container\">\n");
      out.write("          <div class=\"row\">\n");
      out.write("            <div class=\"col-md-8 col-sm-6 col-xs-12\">\n");
      out.write("              <p class=\"copyright-text\">Copyright &copy; 2021 All Rights Reserved by \n");
      out.write("            <a href=\"#\">Maison Camp</a>.\n");
      out.write("              </p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4 col-sm-6 col-xs-12\">\n");
      out.write("              <ul class=\"social-icons\">\n");
      out.write("                <li><a class=\"facebook\" href=\"#\"><i class=\"fa fa-facebook\"></i></a></li>\n");
      out.write("                <li><a class=\"twitter\" href=\"#\"><i class=\"fa fa-twitter\"></i></a></li>\n");
      out.write("                <li><a class=\"dribbble\" href=\"#\"><i class=\"fa fa-dribbble\"></i></a></li>\n");
      out.write("                <li><a class=\"linkedin\" href=\"#\"><i class=\"fa fa-linkedin\"></i></a></li>   \n");
      out.write("              </ul>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </footer>\n");
      out.write("    </div>\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\" integrity=\"sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"jquery.flexslider.js\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/js/front-end/room-detail.js\"></script>\n");
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
