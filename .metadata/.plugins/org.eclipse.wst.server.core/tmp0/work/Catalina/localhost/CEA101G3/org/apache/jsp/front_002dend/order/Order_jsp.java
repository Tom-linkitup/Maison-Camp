/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.38
 * Generated at: 2021-01-02 07:21:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.front_002dend.order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import org.json.*;
import java.time.LocalDate;
import com.member.model.*;
import com.roomtype.model.*;
import com.roomphoto.model.*;
import com.room.model.*;
import com.roomrsv.model.*;

public final class Order_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("org.json");
    _jspx_imports_packages.add("com.roomphoto.model");
    _jspx_imports_packages.add("com.member.model");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("com.roomtype.model");
    _jspx_imports_packages.add("com.room.model");
    _jspx_imports_packages.add("com.roomrsv.model");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.time.LocalDate");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

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
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- 取得會員資料 -->\n");

	MemberVO memVO = (MemberVO) session.getAttribute("memVO");

      out.write("\n");
      out.write("<!-- 取得預訂的房型資料 -->\n");

	String room_category_id = (String) session.getAttribute("room_category_id");
	pageContext.setAttribute("room_category_id", room_category_id);
	
	RoomTypeService roomTypeSvc = new RoomTypeService();
	RoomTypeVO roomTypeVO = roomTypeSvc.getOneRT(room_category_id);
	pageContext.setAttribute("roomTypeVO", roomTypeVO);
	
	RoomPhotoService roomPhotoSvc = new RoomPhotoService();
	List<RoomPhotoVO> rphList = roomPhotoSvc.getAllRPH(room_category_id);
	pageContext.setAttribute("rphList", rphList);

      out.write("\n");
      out.write("<!-- 取得預訂資料 -->\n");

	JSONObject jsonObj = (JSONObject) session.getAttribute("infoJson");

	Integer stay = new Integer(jsonObj.getString("stay"));
	pageContext.setAttribute("stay", stay);
	
	String startDate = jsonObj.getString("startDate");
	pageContext.setAttribute("startDate", startDate);
	
	String leaveDate = jsonObj.getString("leaveDate");
	pageContext.setAttribute("leaveDate", leaveDate);
	
	Integer qty = jsonObj.getInt("qty");
	pageContext.setAttribute("qty", qty);

      out.write("\n");
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
      out.write("/css/front-end/order.css\">\n");
      out.write("<link rel=\"shortcut icon\" type=\"image/png\" href=\"");
      out.print(request.getContextPath());
      out.write("/img/logo.png\">\n");
      out.write("<link rel=\"stylesheet\" href=\"flexslider.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\">\n");
      out.write("<title>Maison Camp | 確認訂單</title>\n");
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
      out.write("                         <a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/front-index.jsp\"><li>首頁</li></a>\n");
      out.write("                    \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/news/News.jsp\"><li>最新消息</li></a>\n");
      out.write("                   \t \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/member/Member.jsp\"><li>會員中心</li></a>\n");
      out.write("                    \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-type/RoomType.jsp\"><li>帳型介紹</li></a>\n");
      out.write("                    \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-booking/RoomBooking.jsp\"><li>立即訂房</li></a>\n");
      out.write("                    \t<a href=\"#\"><li>精選商城</li></a>\n");
      out.write("                    \t<a href=\"#\"><li>活動預約</li></a>\n");
      out.write("                    \t<a href=\"#\"><li>聯絡我們</li></a>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </nav>          \n");
      out.write("            <a href=\"#\"><img id=\"logoo\" class=\"img-logo\" src=\"");
      out.print(request.getContextPath());
      out.write("/img/logo.png\" alt=\"\"></a>\n");
      out.write("            <ul class=\"signin-links\">\n");
      out.write("\t        \t<li><i style=\"margin-right:7px; color:#c15c16;\" class=\"fas fa-child fa-1x\"></i>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memVO.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" 您好<i style=\"color:#496b6b; margin: 0 10px 0 5px;\" class=\"fas fa-exclamation\"></i><a class=\"signin\" href=\"");
      out.print(request.getContextPath());
      out.write("/Member.do?action=logout\"><i class=\"fas fa-sign-out-alt\"></i></a></li>\n");
      out.write("\t      \t</ul>      \n");
      out.write("        </header>\n");
      out.write("        <!-- content header -->\n");
      out.write("        <div class=\"title-text\">\n");
      out.write("          <div>訂單確認</div>\n");
      out.write("        </div>\n");
      out.write("        <!-- booking order -->\n");
      out.write("        <div class=\"news-title\">\n");
      out.write("        \t<div class=\"headline\">\n");
      out.write("        \t\t<h3 style=\"color:#555;\"><span style=\"color:#e67e22;\">訂購資料</span>及付款</h3>\n");
      out.write("        \t\t<i class=\"fa fa-chevron-circle-right color-green margin-right-5\" style=\"color:#e67e22; margin-bottom:20px;\"></i>\n");
      out.write("        \t\t<span>以下為您的預訂資訊，確認無誤後請使用信用卡付款</span>\n");
      out.write("        \t</div>\n");
      out.write("\t\t\t  <div class=\"accordin\">\n");
      out.write("\t\t\t      <input type=\"checkbox\" name=\"tab-1\" id=\"tab-1\" checked/>\t      \n");
      out.write("\t\t\t      <label for=\"tab-1\" class=\"accordin_title\"><i style=\"margin-right:5px;\" class=\"fa fa-cogs\"></i>類型說明</label>  \n");
      out.write("\t\t\t      <div class=\"accordin-detail-1 container\" style=\"width:100%;\">\n");
      out.write("\t\t\t\t      <div class=\"row\">\n");
      out.write("\t\t\t\t      \t<div class=\"col-sm-4\">\n");
      out.write("\t\t\t\t      \t\t<div class=\"flexslider\">\n");
      out.write("\t\t\t\t\t\t\t  <ul class=\"slides\">\n");
      out.write("\t\t\t\t\t\t\t  \t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      boolean _jspx_th_c_005fforEach_005f0_reused = false;
      try {
        _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
        _jspx_th_c_005fforEach_005f0.setParent(null);
        // /front-end/order/Order.jsp(103,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f0.setVar("photo");
        // /front-end/order/Order.jsp(103,10) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rphList}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
        int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
        try {
          int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
          if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\n");
              out.write("                        \t\t<li>\n");
              out.write("                          \t\t\t<img src=\"");
              out.print(request.getContextPath());
              out.write("/PhotoList.do?room_photo_id=");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${photo.room_photo_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
              out.write("\" />\n");
              out.write("                        \t\t</li> \n");
              out.write("                        \t\t");
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
      out.write("\n");
      out.write("\t\t\t\t\t\t\t  </ul>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t      \t</div>\n");
      out.write("\t\t\t\t      \t<div class=\"col-sm-8\">\n");
      out.write("\t\t\t\t\t\t\t<ul style=\"list-style:none; padding:5px 0; line-height:1.7em;\">\n");
      out.write("\t\t\t\t      \t\t\t<li>名稱: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("\t\t\t\t      \t\t\t<li>型態: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("\t\t\t\t      \t\t\t<li>說明:<p> 1.此帳型價格內含提供至多");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_guest}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("人早餐；如有超過之使用人數，需依現場收費公告為主，收取相關費用。<br>\t\t      \t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t2.此帳型可提供加人加床服務；煩請來信或來電告知，以便安排;每帳限加一床；加人、加床費用另計。</p>\n");
      out.write("\t\t\t\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t\t\t\t<li>入住日期: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${startDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("\t\t\t\t\t\t\t\t<li>退房日期: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${leaveDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("\t\t\t\t\t\t\t\t<li>數量: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qty}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" 間</li>\n");
      out.write("\t\t\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t      \t</div>\n");
      out.write("\t\t\t\t      </div>\n");
      out.write("\t\t\t      </div>\n");
      out.write("\t\t    </div>\n");
      out.write("\t\t\n");
      out.write("\t\t    <div class=\"accordin\">\n");
      out.write("\t\t      <input type=\"checkbox\" name=\"tab-2\" id=\"tab-2\" />\n");
      out.write("\t\t      <label for=\"tab-2\" class=\"accordin_title\"><i style=\"margin-right:5px;\" class=\"fa fa-paperclip\"></i>價格資訊</label>\n");
      out.write("\t\t\t      <div class=\"accordin_detail\">\n");
      out.write("\t\t\t        <table class=\"table table-striped table-bordered margin-top-20\">\n");
      out.write("\t\t\t        \t<thead>\n");
      out.write("\t\t\t        \t\t<tr style=\"color:#e67e22;\">\n");
      out.write("\t\t\t        \t\t\t<th>入住日</th>\n");
      out.write("\t\t\t        \t\t\t<th>房間單價</th>\n");
      out.write("\t\t\t        \t\t\t<th>訂購數量</th>\n");
      out.write("\t\t\t        \t\t\t<th>使用天數</th>\n");
      out.write("\t\t\t        \t\t\t<th>金額小計</th>\n");
      out.write("\t\t\t        \t\t</tr>\n");
      out.write("\t\t\t        \t</thead>\n");
      out.write("\t\t\t        \t<tbody>\n");
      out.write("\t\t\t        \t\t<tr>\n");
      out.write("\t\t\t        \t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${startDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t        \t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_price}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t        \t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qty}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t        \t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${stay}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t        \t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_price * qty * stay}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t        \t\t</tr>\n");
      out.write("\t\t\t        \t\t<tr>\n");
      out.write("\t\t\t        \t\t\t<td colspan=\"5\" class=\"align-rt\" style=\"text-align:end;\"><span style=\"color:#c15c61;\">價格：</span> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_price * qty * stay}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\t        \t\t\t\n");
      out.write("\t\t\t        \t\t</tr>\n");
      out.write("\t\t\t        \t\t<tr>\n");
      out.write("\t\t\t        \t\t\t<td colspan=\"5\" class=\"align-rt\" style=\"text-align:end;\"><span style=\"color:#c15c61;\">優惠折數：</span> 0.8</td>\n");
      out.write("\t\t\t        \t\t</tr>\n");
      out.write("\t\t\t        \t\t<tr>\n");
      out.write("\t\t\t        \t\t\t<td colspan=\"5\" class=\"align-rt\" style=\"text-align:end;\"><span style=\"color:#c15c61;\">總計：</span>$ ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_price * qty * stay * 0.8}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t\t        \t\t</tr>\n");
      out.write("\t\t\t        \t</tbody>\n");
      out.write("\t\t\t        </table>\n");
      out.write("\t\t\t      </div>\n");
      out.write("\t\t    </div>\n");
      out.write("\t\t\n");
      out.write("\t\t    <div class=\"accordin\">\n");
      out.write("\t\t      <input type=\"checkbox\" name=\"tab-3\" id=\"tab-3\" />\n");
      out.write("\t\t      <label for=\"tab-3\" class=\"accordin_title\"><i style=\"margin-right:5px;\" class=\"fa fa-user\"></i>訂購人資料</label>\n");
      out.write("\t\t      <div class=\"accordin_detail\">\n");
      out.write("\t\t        \t<table class=\"table table-striped margin-top-20\">\n");
      out.write("\t\t        \t\t<tbody>\n");
      out.write("\t\t        \t\t\t<tr>\n");
      out.write("\t\t        \t\t\t\t<td>姓名：</td>\n");
      out.write("\t\t        \t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memVO.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t        \t\t\t</tr>\n");
      out.write("\t\t        \t\t\t<tr>\n");
      out.write("\t\t        \t\t\t\t<td>Email：</td>\n");
      out.write("\t\t        \t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memVO.email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t        \t\t\t</tr>\n");
      out.write("\t\t        \t\t\t<tr>\n");
      out.write("\t\t        \t\t\t\t<td>生日：</td>\n");
      out.write("\t\t        \t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memVO.birthday}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</td>\n");
      out.write("\t\t        \t\t\t</tr>        \t\t\n");
      out.write("\t\t        \t\t</tbody>\n");
      out.write("\t\t        \t</table>\n");
      out.write("\t\t      </div>\n");
      out.write("\t\t    </div>\n");
      out.write("\t\t    \n");
      out.write("\t\t    <div class=\"accordin\">\n");
      out.write("\t\t      <input type=\"checkbox\" name=\"tab-4\" id=\"tab-4\" />\n");
      out.write("\t\t      <label for=\"tab-4\" class=\"accordin_title\"><i style=\"margin-right:5px;\" class=\"far fa-info-circle\"></i>取消預定須知</label>\n");
      out.write("\t\t      <div class=\"accordin_detail\">\n");
      out.write("\t\t        \t<div class=\"alert alert-warning\">\n");
      out.write("\t\t        \t\t<ul class=\"list-unstyled\" style=\"line-height:1.7; padding-top:10px;\">\n");
      out.write("\t\t        \t\t\t<li><i class=\"fa fa-chevron-circle-right color-green margin-right-5\" style=\"color:#e67e22;\"></i> 顧客於使用日當日取消預訂扣總價總金額 100%。</li>\n");
      out.write("\t\t        \t\t\t<li><i class=\"fa fa-chevron-circle-right color-green margin-right-5\" style=\"color:#e67e22;\"></i> 顧客於使用日前 7-9 日內取消預訂扣總價總金額 50%。</li>  \n");
      out.write("\t\t        \t\t\t<li><i class=\"fa fa-chevron-circle-right color-green margin-right-5\" style=\"color:#e67e22;\"></i> 顧客於使用日前 14日(含14 日)取消預訂扣總價總金額 0%。</li>           \t\t\n");
      out.write("\t\t        \t\t</ul>      \t\n");
      out.write("\t\t        \t</div>\n");
      out.write("\t\t      </div>\n");
      out.write("\t\t    </div>\t    \n");
      out.write("\t\t    <div class=\"accordin\">\n");
      out.write("\t\t      <input type=\"checkbox\" name=\"tab-5\" id=\"tab-5\" />\n");
      out.write("\t\t      <label for=\"tab-5\" class=\"accordin_title\"><i style=\"margin-right:5px;\" class=\"far fa-money-bill-alt\"></i>選擇付款方式</label>\n");
      out.write("\t\t      <div class=\"accordin_detail\">\n");
      out.write("\t\t        <div class=\"creditcard\">\n");
      out.write("\t\t        \t<label class=\"radio\">\n");
      out.write("\t\t        \t\t<input type=\"radio\" name=\"payment\" checked>\n");
      out.write("\t\t        \t\t<i class=\"fal fa-credit-card fa-2x\"></i><span style=\"margin-left: 10px;\">信用卡</span>\n");
      out.write("\t\t        \t</label>\n");
      out.write("\t\t        </div>\n");
      out.write("\t\t      </div>\n");
      out.write("\t\t    </div>\n");
      out.write("\t\t    <div class=\"steps-control\">\n");
      out.write("\t\t    \t<div class=\"text-center\">\n");
      out.write("\t\t\t    \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/front-end/room-booking/RoomBooking.jsp\"><button type=\"button\">返回</button></a>\t    \t\n");
      out.write("\t\t\t    \t<form method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/RoomOrder.do\">\n");
      out.write("\t\t\t    \t\t<input type=\"hidden\" name=\"stay\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${stay}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"mem_id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memVO.mem_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"check_in\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${startDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"check_out\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${leaveDate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"room_category_id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${room_category_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"room_promotion_id\" value=\"RP10002\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"quantity\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qty}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"room_order_price\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomTypeVO.room_price * qty * stay * 1}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("\t\t\t\t    \t<input type=\"hidden\" name=\"action\" value=\"insert\">\n");
      out.write("\t\t\t\t    \t<button type=\"submit\">付款</button>  \t\n");
      out.write("\t\t\t    \t</form>\n");
      out.write("\t\t    \t</div>\n");
      out.write("\t\t    </div>\n");
      out.write("\t    </div>\n");
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
      out.write("                    <li><a class=\"linkedin\" href=\"#\"><i class=\"fab fa-linkedin\"></i></a></li> \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("      <!-- footer -->\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\"></script>\n");
      out.write("        <script src=\"jquery.flexslider.js\"></script>\n");
      out.write("        <script src=\"");
      out.print(request.getContextPath());
      out.write("/js/front-end/room-booking.js\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("\t\t<script type=\"text/javascript\">\n");
      out.write("\t\t\t$(window).load(function() {\n");
      out.write("\t\t\t\t  $('.flexslider').flexslider({\n");
      out.write("\t\t\t\t    animation: \"slide\"\n");
      out.write("\t\t\t\t  });\n");
      out.write("\t\t\t});\n");
      out.write("\t\t</script>\n");
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
