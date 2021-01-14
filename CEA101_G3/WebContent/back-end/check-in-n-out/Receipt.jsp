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
<%@ page import="com.itextpdf.text.BaseColor" %>
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
	Integer room_price = roomOrderDetailVO.getRoom_order_price();
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
 		//中文字的解決
 		BaseFont bfChinese = BaseFont.createFont("/Users/tomgu/CEA101_WebApp/CEA101G3/CEA101_G3/WebContent/font/wei.ttf", "Identity-H", BaseFont.NOT_EMBEDDED);
 		Font FontChinese = new Font(bfChinese, 15, Font.NORMAL);
 		
 		Paragraph title = new Paragraph();//新增一個段落，類似html的<p>
		title.add(new Chunk("Maison Camp | 露營家", FontChinese)); //新增一個字串然後加到Paragraph
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(10); //設定Space
		document.add(title); //每個element都要加到document才會顯示
		
		Paragraph p = new Paragraph();//新增一個段落，類似html的<p>
		p.add(new Chunk("#消費明細", FontChinese)); //新增一個字串然後加到Paragraph
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(20); //設定Space
		document.add(p); //每個element都要加到document才會顯示
		
		PdfPTable table = new PdfPTable(2);  //建立一個只有兩個欄位的表格
		table.setWidthPercentage(80f);    //設定表格寬
		table.setWidths(new float[]{0.20f, 0.90f});    //這兩個欄位的比例大小
		 
		PdfPCell cell = new PdfPCell();   //建立一個儲存格
		//透過 Paragraph 物件增加元素及指定編碼, 也可以直接存入字串
		cell.addElement(new Paragraph("訂單編號", FontChinese));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
		         
		cell = new PdfPCell();
		cell.addElement(new Paragraph(roomOrderVO.getRoom_order_id(), FontChinese));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		
		//cell2
		PdfPCell cell2 = new PdfPCell(); 
		cell2.addElement(new Paragraph("會員姓名", FontChinese));
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell2);
		         
		cell2 = new PdfPCell();
		cell2.addElement(new Paragraph(memVO.getName(), FontChinese));
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell2);
		
		//cell 3
		PdfPCell cell3 = new PdfPCell();
		cell3.addElement(new Paragraph("入住房型", FontChinese));
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell3);
		         
		cell3 = new PdfPCell();
		cell3.addElement(new Paragraph(roomTypeVO.getRoom_name(), FontChinese));
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell3);
		
		//cell 4
		PdfPCell cell4 = new PdfPCell(); 
		cell4.addElement(new Paragraph("入住日期", FontChinese));
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell4);
		         
		cell4 = new PdfPCell();
		cell4.addElement(new Paragraph(roomOrderVO.getCheck_in_date().toString(),FontChinese));
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell4);
		
		//cell 5
		PdfPCell cell5 = new PdfPCell(); 
		cell5.addElement(new Paragraph("退房日期", FontChinese));
		cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell5);
		         
		cell5 = new PdfPCell();
		cell5.addElement(new Paragraph(roomOrderVO.getCheck_out_date().toString(),FontChinese));
		cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell5);
		
		//cell 6
		PdfPCell cell6 = new PdfPCell(); 
		cell6.addElement(new Paragraph("入住天數", FontChinese));
		cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell6);
		         
		cell6 = new PdfPCell();
		cell6.addElement(new Paragraph(stay + "晚", FontChinese));
		cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell6);
		
		//cell 7
		PdfPCell cell7 = new PdfPCell(); 
		cell7.addElement(new Paragraph("訂單金額", FontChinese));
		cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell7);
		         
		cell7 = new PdfPCell();
		cell7.addElement(new Paragraph(room_price + "元", FontChinese));
		cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell7);
		
		//cell 8
		PdfPCell cell8 = new PdfPCell(); 
		cell8.addElement(new Paragraph("額外消費", FontChinese));
		cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell8);
		         
		cell8 = new PdfPCell();
		cell8.addElement(new Paragraph(excTotal + "元", FontChinese));
		cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell8);
		
		table.setSpacingAfter(20);
		document.add(table);    //將表格加入 PDF 檔案裡
		
		Paragraph total = new Paragraph();
		total.add(new Chunk("總消費金額:" + (room_price + excTotal) + "元", FontChinese)); 
		total.setAlignment(Element.ALIGN_CENTER);
		total.setSpacingAfter(10); 
		document.add(total); 
		
		Paragraph footer = new Paragraph();
		footer.add(new Chunk("感謝您的入住!", FontChinese)); 
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setSpacingAfter(20);
		document.add(footer); 
		
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