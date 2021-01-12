<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomorder.model.*" %>
<%@ page import="com.roomorderdetail.model.*" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.extra_charges.model.*" %>
<%@ page import="java.time.LocalDate"%>
<%@ page import="com.itextpdf.text.Document"%>
<%@ page import="com.itextpdf.text.Paragraph"%>
<%@ page import="com.itextpdf.text.Chunk"%>
<%@ page import="com.itextpdf.text.Element"%>
<%@ page import="com.itextpdf.text.PageSize"%>
<%@ page import="com.itextpdf.text.Phrase"%>
<%@ page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@ page import="com.itextpdf.text.pdf.PdfPages"%>
<%@ page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@ page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@ page import="com.itextpdf.text.Font"%>
<%@ page import="com.itextpdf.text.Font.FontFamily"%>
<%@ page import="com.itextpdf.text.pdf.BaseFont"%>
<%@ page import="com.itextpdf.text.DocumentException"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%
	String room_order_id = request.getParameter("room_order_id");
	pageContext.setAttribute("room_order_id", room_order_id);
	String mem_id = request.getParameter("mem_id");
	
	RoomOrderDetailService rodSvc = new RoomOrderDetailService();
	RoomOrderDetailVO roomOrderDetailVO = rodSvc.getOneROD(room_order_id);
	pageContext.setAttribute("roomOrderDetailVO", roomOrderDetailVO);
	
	RoomOrderService roSvc = new RoomOrderService();
	RoomOrderVO roomOrderVO = roSvc.getOneRO(room_order_id);
	pageContext.setAttribute("roomOrderVO", roomOrderVO);
	
	//取得入住天數
	LocalDate start = roomOrderVO.getCheck_in_date().toLocalDate();
	LocalDate end = roomOrderVO.getCheck_out_date().toLocalDate();
	Integer stay = end.compareTo(start);
	pageContext.setAttribute("stay", stay);
	
	MemberService memSvc = new MemberService();
	MemberVO memVO = memSvc.getOneMEM(mem_id);
	pageContext.setAttribute("memVO", memVO);
	
	RoomTypeService rtSvc = new RoomTypeService();
	RoomTypeVO roomTypeVO = rtSvc.getOneRT(roomOrderDetailVO.getRoom_category_id());
	pageContext.setAttribute("roomTypeVO", roomTypeVO);
	
	//取得額外消費總金額
	Extra_chargesService excSvc = new Extra_chargesService();
	List<Extra_chargesVO> exclist = excSvc.getByRO(room_order_id);
	Integer excTotal = 0;
	for(Extra_chargesVO exc : exclist){
		excTotal += exc.getPrice();
	}
	pageContext.setAttribute("excTotal", excTotal);
	
	//產生PDF檔案
	Document document = new Document();
    try{
  		PdfWriter.getInstance(document,new FileOutputStream("/Users/tomgu/CEA101_WebApp/CEA101G3/CEA101_G3/WebContent/pdf/" + roomOrderVO.getRoom_order_id() + ".pdf"));
 		document.open(); 
 		Paragraph title = new Paragraph();//新增一個段落，類似html的<p>
		title.add(new Chunk("Maison Camp")); //新增一個字串然後加到Paragraph
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(10); //設定Space
		document.add(title); //每個element都要加到document才會顯示
		
		Paragraph p = new Paragraph();//新增一個段落，類似html的<p>
		p.add(new Chunk("#Receipt")); //新增一個字串然後加到Paragraph
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(20); //設定Space
		document.add(p); //每個element都要加到document才會顯示
		
		PdfPTable table=new PdfPTable(1); //初始化Table然後指定欄位數目
		
		PdfPCell cell = new PdfPCell(); //初始化Cell(欄位)
		cell.setPhrase(new Phrase(roomOrderVO.getRoom_order_id())); //填入Cell欄位字串，要new一個Phrase
		cell.setHorizontalAlignment(Element.ALIGN_CENTER); //置中對齊
		table.addCell(cell); //cell加到table中
		
		cell.setPhrase(new Phrase(roomOrderDetailVO.getRoom_order_price()));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(stay + "nights"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		document.add(table); //table加到document顯示	
		
    }catch(Exception e){
   		e.printStackTrace();	
    }finally{ 
    	document.close();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/roomReceipt.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<title>Insert title here</title>
</head>
<body style="box-sizing: border-box; margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased; background-color: #f5dbce;">
  <table class="wrapper all-font-sans" width="100%" height="100%" cellpadding="0" cellspacing="0" role="presentation">
    <tr>
      <td align="center" style="padding: 24px;" width="100%">
        <table class="sm-w-full" width="600" cellpadding="0" cellspacing="0" role="presentation">
          <tr>
            <td colspan="2" class="sm-inline-block" style="display: none;">
              <img src="https://images.unsplash.com/photo-1505577058444-a3dab90d4253?ixlib=rb-0.3.5&s=fed02ccbe457c9b8fc1f2cf76f30d755&w=600&h=400&q=80&fit=crop" alt="Double Room" style="border: 0; line-height: 100%; vertical-align: middle; border-top-left-radius: 4px; border-top-right-radius: 4px; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, .1), 0 4px 6px -2px rgba(0, 0, 0, .05);">
            </td>
          </tr>
          <tr>
            <td class="sm-hidden" style="padding-top: 40px; padding-bottom: 40px;" width="160">
              <img src="https://images.unsplash.com/photo-1505577058444-a3dab90d4253?ixlib=rb-0.3.5&s=fed02ccbe457c9b8fc1f2cf76f30d755&w=320&h=800&q=80&fit=crop" alt="Double room" style="border: 0; line-height: 100%; vertical-align: middle; border-top-left-radius: 4px; border-bottom-left-radius: 4px; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, .1), 0 4px 6px -2px rgba(0, 0, 0, .05);" width="160">
            </td>
            <td align="left" class="sm-p-20 sm-dui17-b-t" style="border-radius: 2px; padding: 40px; position: relative; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, .1), 0 4px 6px -2px rgba(0, 0, 0, .05); vertical-align: top; z-index: 50;" bgcolor="#ffffff" valign="top">
              <table width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                  <td width="80%">
                    <h1 class="sm-text-lg all-font-roboto" style="font-weight: 700; line-height: 100%; margin: 0; margin-bottom: 4px; font-size: 24px;">Maison Camp 住房收據</h1>
                    <p class="sm-text-xs" style="margin: 0; color: #a0aec0; font-size: 14px;">感謝您的入住！</p>
                  </td>
                  <td style="text-align: right;" width="20%" align="right">
                    <a href="<%=request.getContextPath()%>/Download?room_order_id=${room_order_id}" style="text-decoration: none;">
                      <img src="https://image0.flaticon.com/icons/png/128/872/872220.png" alt="Download PDF" style="border: 0; line-height: 100%; vertical-align: middle; font-size: 12px;" width="24">
                    </a>
                  </td>
                </tr>
              </table>
              <div style="line-height: 32px;">&zwnj;</div>
              <table class="sm-leading-32" style="line-height: 28px; font-size: 14px;" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                  <td class="sm-inline-block" style="color: #718096;" width="50%">會員名稱</td>
                  <td class="sm-inline-block" style="font-weight: 600; text-align: right;" width="50%" align="right">${memVO.name}</td>
                </tr>
                <tr>
                  <td class="sm-inline-block" style="color: #718096;" width="50%">入住天數</td>
                  <td class="sm-inline-block" style="font-weight: 600; text-align: right;" width="50%" align="right">${stay}晚</td>
                </tr>
                <tr>
                  <td class="sm-w-1-4 sm-inline-block" style="color: #718096;" width="50%">聯繫方式</td>
                  <td class="sm-w-3-4 sm-inline-block" style="font-weight: 600; text-align: right;" width="50%" align="right">${memVO.email}</td>
                </tr>
              </table>
              <table width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                  <td style="padding-top: 24px; padding-bottom: 24px;">
                    <div style="background-color: #edf2f7; height: 2px; line-height: 2px;">&zwnj;</div>
                  </td>
                </tr>
              </table>
              <table style="font-size: 14px;" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                  <td class="sm-w-full sm-inline-block sm-text-center" width="40%">
                    <p class="all-font-roboto" style="margin: 0; margin-bottom: 4px; color: #a0aec0; font-size: 10px; text-transform: uppercase; letter-spacing: 1px;">入住日期</p>
                    <p class="all-font-roboto" style="font-weight: 600; margin: 0; color: #000000;">${roomOrderVO.check_in_date}</p>
                  </td>
                  <td class="sm-w-full sm-inline-block sm-py-12" style="font-family: Menlo, Consolas, monospace; font-weight: 600; text-align: center; color: #cbd5e0; font-size: 18px; letter-spacing: -1px;" width="20%" align="center">&gt;&gt;&gt;</td>
                  <td class="sm-w-full sm-inline-block sm-text-center" style="text-align: right;" width="40%" align="right">
                    <p class="all-font-roboto" style="margin: 0; margin-bottom: 4px; color: #a0aec0; font-size: 10px; text-transform: uppercase; letter-spacing: 1px;">退房日期</p>
                    <p class="all-font-roboto" style="font-weight: 600; margin: 0; color: #000000;">${roomOrderVO.check_out_date}</p>
                  </td>
                </tr>
              </table>
              <table width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                  <td style="padding-top: 24px; padding-bottom: 24px;">
                    <div style="background-color: #edf2f7; height: 2px; line-height: 2px;">&zwnj;</div>
                  </td>
                </tr>
              </table>
              <table style="line-height: 28px; font-size: 14px;" width="100%" cellpadding="0" cellspacing="0" role="presentation">
                <tr>
                  <td style="color: #718096;" width="50%">訂單金額</td>
                  <td style="font-weight: 600; text-align: right;" width="50%" align="right">$ ${roomOrderDetailVO.room_order_price}</td>
                </tr>
                <tr>
                  <td style="color: #718096;" width="50%">額外消費金額</td>
                  <td style="font-weight: 600; text-align: right;" width="50%" align="right">$${excTotal}</td>
                </tr>
                <tr>
                  <td style="font-weight: 600; padding-top: 32px; color: #000000; font-size: 20px;" width="50%">總金額</td>
                  <td style="font-weight: 600; padding-top: 32px; text-align: right; color: #68d391; font-size: 20px;" width="50%" align="right">$${roomOrderDetailVO.room_order_price + excTotal}</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

</body>
</html>