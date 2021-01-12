package com.extra_charges.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.extra_charges.model.*;

public class ExtraChargesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
	doPost(req, res);
}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
	
//if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp?????
//	
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.??隢?? - 頛詨?撘?隤方???**********************/
//				String str = req.getParameter("extra_charges_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("隢撓?憿??祥?????(EXC10001???~~)");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_extra_charges/select_page.jsp");
//					failureView.forward(req, res);
//					return;//蝔?葉?
//				}
//				
//				String extra_charges_id = null;
//				try {
//					extra_charges_id = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("憿??祥??楊??撘?迤蝣?");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_extra_charges/select_page.jsp");
//					failureView.forward(req, res);
//					return;//蝔?葉?
//				}
//		
//				/***************************2.???閰Ｚ???*****************************************/
//				Extra_chargesService extra_chargesSvc = new Extra_chargesService();
//				Extra_chargesVO extra_chargesVO = extra_chargesSvc.getOneExtra_charges(extra_charges_id);
//				if (extra_chargesVO == null) {
//					errorMsgs.add("??鞈??");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_extra_charges/select_page.jsp");
//					failureView.forward(req, res);
//					return;//蝔?葉?
//				}
//				/***************************3.?閰Ｗ???,皞??漱(Send the Success view)*************/
//				req.setAttribute("extra_chargesVO", extra_chargesVO); // 鞈?澈????xtra_chargesVO?隞?,摮req
//				String url = "/back-end/room_extra_charges/listOneExtraCharges.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ????漱 listOneExtraCharges.jsp
//				successView.forward(req, res);
//				
//				/***************************?隞???隤方???*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("?瘜?????:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/room_extra_charges/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
		
if ("getOne_For_Update".equals(action)) { // 靘listAllEextraCharges.jsp?????

	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String extra_charges_id = new String(req.getParameter("extra_charges_id"));
				/***************************2.開始查詢資料****************************************/
				Extra_chargesService extra_chargesSvc = new Extra_chargesService();
				Extra_chargesVO extra_chargesVO = extra_chargesSvc.getOneExtra_charges(extra_charges_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("extra_chargesVO", extra_chargesVO);        
				String url = "/back-end/extra_charges/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************?隞???隤方???**********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
if ("insert".equals(action)) { // 靘addExtraCharges.jsp?????  
			
	Map<String, String> errorMsgs = new LinkedHashMap<>();
	req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				
				String room_order_id = req.getParameter("room_order_id").trim();
			
				String item = req.getParameter("item").trim();
				if (item == null || item.trim().length() == 0) {
					errorMsgs.put("item","請輸入消費內容，不可空白");
				}
				
				
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0 ;
					errorMsgs.put("price","請輸入消費金額，不可填入數字以外的內容");
				}
								
				Extra_chargesVO extra_chargesVO = new Extra_chargesVO();
				extra_chargesVO.setRoom_order_id(room_order_id);
				extra_chargesVO.setItem(item);
				extra_chargesVO.setPrice(price);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("extra_chargesVO", extra_chargesVO); // ???撓??撘隤斤?xtra_chargesVO?隞?,銋?req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/extra_charges/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Extra_chargesService extra_chargesSvc = new Extra_chargesService();
				extra_chargesVO = extra_chargesSvc.addExtra_charges(room_order_id,item,price);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/extra_charges/select_page.jsp";
				req.setAttribute("insertSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url); // ?憓????漱listAllExtraCharges.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/extra_charges/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		




if ("delete".equals(action)) { // 靘listAllExtraCharges.jsp

	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);

	try {
		/***************************1.接收請求參數***************************************/
		String extra_charges_id = new String(req.getParameter("extra_charges_id"));
		
		/***************************2.開始刪除資料***************************************/
		Extra_chargesService extra_chargesSvc = new Extra_chargesService();
		extra_chargesSvc.deleteExtra_charges(extra_charges_id);
		
		/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
		String url = "/back-end/extra_charges/select_page.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(req, res);
		
		/***************************?隞???隤方???**********************************/
	} catch (Exception e) {
		errorMsgs.add("刪除失敗:"+e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back-end/extra_charges/select_page.jsp");
		failureView.forward(req, res);
	}
}

		
		
		


if ("update".equals(action)) { // 靘updateExtraChargesInput.jsp?????
	
	Map<String, String> errorMsgs = new LinkedHashMap<>();
	req.setAttribute("errorMsgs", errorMsgs);

	try {
		String extra_charges_id = new String(req.getParameter("extra_charges_id").trim());
		String room_order_id = req.getParameter("room_order_id");
		
		String item = req.getParameter("item").trim();
		if (item == null || item.trim().length() == 0) {
			errorMsgs.put("item","消費內容不可為空");
		}	
		
		
		Integer price = null;
		try {
			price = new Integer(req.getParameter("price").trim());
		} catch (NumberFormatException e) {
			price = 0;
			errorMsgs.put("price","金額必須是純數字");
		}
		
		Extra_chargesVO extra_chargesVO = new Extra_chargesVO();
		extra_chargesVO.setExtra_charges_id(extra_charges_id);
		extra_chargesVO.setRoom_order_id(room_order_id);
		extra_chargesVO.setItem(item);
		extra_chargesVO.setPrice(price);

		
		
		Extra_chargesService extra_chargesSvc = new Extra_chargesService();
		extra_chargesVO = extra_chargesSvc.updateExtra_charges(extra_charges_id, room_order_id, item, price);
		
		req.setAttribute("extra_chargesVO", extra_chargesVO); //修改成功後,轉交
		String url = "/back-end/extra_charges/select_page.jsp";
		req.setAttribute("updateSuccess", "yes");
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);

		/***************************其他可能的錯誤處理*************************************/
	} catch (Exception e) {
		RequestDispatcher failureView = req
				.getRequestDispatcher("/back-end/extra_charges/select_page.jsp");
		failureView.forward(req, res);
	}
}

		if ("insertByCheckOut".equals(action)) { 
			try {			
				String room_order_id = req.getParameter("room_order_id").trim();
				String item = req.getParameter("item").trim();
				Integer price = new Integer(req.getParameter("price").trim());
								
				Extra_chargesVO extra_chargesVO = new Extra_chargesVO();
				extra_chargesVO.setRoom_order_id(room_order_id);
				extra_chargesVO.setItem(item);
				extra_chargesVO.setPrice(price);
			
				Extra_chargesService extra_chargesSvc = new Extra_chargesService();
				extra_chargesVO = extra_chargesSvc.addExtra_charges(room_order_id, item, price);
				JSONObject extraItem = new JSONObject();
				extraItem.put("room_order_id", extra_chargesVO.getRoom_order_id());
				extraItem.put("item", extra_chargesVO.getItem());
				extraItem.put("price", extra_chargesVO.getPrice());
				extraItem.put("success", "success");
				out.print(extraItem);		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}


