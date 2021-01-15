package com.room_promotion.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.room_promotion.model.Room_promotionService;
import com.room_promotion.model.Room_promotionVO;

public class RoomPromotionServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
 
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
System.out.println("有收到請求");		
		
//if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("room_promotion_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入優惠專案編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_promotion/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String room_promotion_id = null;
//				try {
//					room_promotion_id = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_promotion/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				Room_promotionService room_promotionSvc = new Room_promotionService();
//				Room_promotionVO room_promotionVO = room_promotionSvc.getOneRoom_promotion(room_promotion_id);
//				if (room_promotionVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_promotion/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("room_promotionVO", room_promotionVO); // 資料庫取出的room_promotionVO物件,存入req
//				String url = "/back-end/room_promotion/listOneRoomPromotion.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRoom_promotion.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/room_promotion/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
		
if ("getOne_For_Update".equals(action)) { // 來自listAllRoom_promotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
						req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String room_promotion_id = req.getParameter("room_promotion_id");
				
				/***************************2.開始查詢資料****************************************/
				Room_promotionService room_promotionSvc = new Room_promotionService();
				Room_promotionVO room_promotionVO = room_promotionSvc.getOneRoom_promotion(room_promotion_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("room_promotionVO", room_promotionVO);         // 資料庫取出的room_promotionVO物件,存入req
				String url = "/back-end/room_promotion/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_room_promotion_input.jsp
				successView.forward(req, res);
				return;

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
if ("update".equals(action)) { 
	
	Map<String, String> errorMsgs = new LinkedHashMap<>();
	req.setAttribute("errorMsgs", errorMsgs);
	
	try {
				String room_promotion_id = req.getParameter("room_promotion_id").trim();
			
				String str = req.getParameter("room_category_id").trim();
				String [] data = str.split(",");
				String room_category_id = data[0];
	
				String room_promotion_info = req.getParameter("room_promotion_info").trim();
				if(room_promotion_info == null || room_promotion_info.trim().length() == 0) {
					errorMsgs.put("room_promotion_info", "*優惠內容不得為空");
				}
					
				Double room_discount = null;
				try {
					room_discount = new Double(req.getParameter("room_discount").trim());
				} catch (NumberFormatException e) {
					room_discount = 0.0;
					errorMsgs.put("room_discount", "*請輸入0~1之間的折扣");
				}
				java.sql.Date room_prom_start_date = null;
				try {
					room_prom_start_date = java.sql.Date.valueOf(req.getParameter("room_prom_start_date").trim());
				} catch (IllegalArgumentException e) {
					room_prom_start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("room_prom_start_date","請選擇日期!");
				}
				
				java.sql.Date room_prom_end_date = null;
				try {
					room_prom_end_date = java.sql.Date.valueOf(req.getParameter("room_prom_end_date").trim());
				} catch (IllegalArgumentException e) {
					room_prom_end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("room_prom_end_date","請選擇日期!");
				}

				
				Room_promotionVO room_promotionVO = new Room_promotionVO();
				room_promotionVO.setRoom_promotion_id(room_promotion_id);
				room_promotionVO.setRoom_category_id(room_category_id);
				room_promotionVO.setRoom_promotion_info(room_promotion_info);
				room_promotionVO.setRoom_discount(room_discount);
				room_promotionVO.setRoom_prom_start_date(room_prom_start_date);
				room_promotionVO.setRoom_prom_end_date(room_prom_end_date);

				Room_promotionService room_promotionSvc = new Room_promotionService();
				room_promotionVO = room_promotionSvc.updateRoom_promotion(room_promotion_id,room_category_id,room_promotion_info,  room_discount,room_prom_start_date,room_prom_end_date);
				
				String url = "/back-end/room_promotion/select_page.jsp";
				req.setAttribute("updateSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom_promotion.jsp
				successView.forward(req, res);
				return;

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

if ("insert".equals(action)) { // 來自addRoom_promotion.jsp的請求  
	System.out.println("有進去insert請求");
	Map<String, String> errorMsgs = new LinkedHashMap<>();
	req.setAttribute("errorMsgs", errorMsgs);

	try {			
		String room_category_id = req.getParameter("room_category_id").trim();
		Room_promotionService roomTypeSvcTest = new Room_promotionService();
		List<Room_promotionVO> room_promotionList = roomTypeSvcTest.getAll();
		for(Room_promotionVO rt : room_promotionList) {
			
			if(room_category_id.equals(rt.getRoom_category_id())) {
				req.setAttribute("repeat", "repeat");
			}
		}
	
		if(room_category_id == null || room_category_id.trim().length() == 0) {
			errorMsgs.put("room_category_id", "*房型不得為空(TWINS,DOUBLE,QUADRUPLE)");
		}
		
		
		String room_promotion_info = req.getParameter("room_promotion_info").trim();
		if(room_promotion_info == null || room_promotion_info.trim().length() == 0) {
			errorMsgs.put("room_promotion_info", "*優惠內容不得為空");
		}
			
		
		Double room_discount = null;
			try {
				room_discount = new Double(req.getParameter("room_discount").trim());
			} catch (NumberFormatException e) {
				room_discount = 0.0;
				errorMsgs.put("room_discount", "*請輸入0~1之間的折扣");
			}
			
			
			
				
				java.sql.Date room_prom_start_date = null;
				java.sql.Date error1 =java.sql.Date.valueOf("1970-01-01");
				try {
					room_prom_start_date = java.sql.Date.valueOf(req.getParameter("room_prom_start_date").trim());
					
					if(room_prom_start_date.equals(error1)) {
						room_prom_start_date=java.sql.Date.valueOf("");
						errorMsgs.put("room_prom_start_date","請選擇日期!");
					}
					
					
				} catch (IllegalArgumentException e) {
					room_prom_start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("room_prom_start_date","請選擇日期!");
				}
				
			//end的日期處理	
				java.sql.Date room_prom_end_date = null;
				java.sql.Date error2 =java.sql.Date.valueOf("1970-01-01");
				try {
					room_prom_end_date = java.sql.Date.valueOf(req.getParameter("room_prom_end_date").trim());
					
					if(room_prom_end_date.equals(error2)) {
						room_prom_end_date=java.sql.Date.valueOf("");
						errorMsgs.put("room_prom_end_date","請選擇日期!");
					}
				} catch (IllegalArgumentException e) {
					room_prom_end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("room_prom_end_date","請選擇日期!");
				}
				System.out.println(room_prom_end_date);
			
									
				
				
				
				

				Room_promotionVO room_promotionVO = new Room_promotionVO();
				room_promotionVO.setRoom_category_id(room_category_id);
				room_promotionVO.setRoom_promotion_info(room_promotion_info);
				room_promotionVO.setRoom_discount(room_discount);
				room_promotionVO.setRoom_prom_start_date(room_prom_start_date);
				room_promotionVO.setRoom_prom_end_date(room_prom_end_date);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("remain", room_promotionVO); // 含有輸入格式錯誤的room_promotionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/room_promotion/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Room_promotionService room_promotionSvc = new Room_promotionService();
				room_promotionVO = room_promotionSvc.addRoom_promotion(room_category_id, room_promotion_info,room_discount, room_prom_start_date,room_prom_end_date);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/room_promotion/select_page.jsp";
				req.setAttribute("insertSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllRoom_promotion.jsp
				successView.forward(req, res);				
				return;
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
if ("delete".equals(action)) { // 來自listAllRoom_promotion.jsp

				/***************************1.接收請求參數***************************************/
				String room_promotion_id = req.getParameter("room_promotion_id");
				
				/***************************2.開始刪除資料***************************************/
				Room_promotionService room_promotionSvc = new Room_promotionService();
				room_promotionSvc.deleteRoom_promotion(room_promotion_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/room_promotion/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				return;
				
		
			
		}
	}
}
