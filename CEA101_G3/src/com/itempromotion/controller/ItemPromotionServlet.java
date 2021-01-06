package com.itempromotion.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itempromotion.model.*;

public class ItemPromotionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String item_promotion_id = req.getParameter("item_promotion_id");

			if (item_promotion_id == null || (item_promotion_id.trim().length() == 0)) {
				errorMsgs.add("請輸入產品編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/item_promotion/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ItemPromotionService itemPromotionSvc = new ItemPromotionService();
			ItemPromotionVO itemPromotionVO = itemPromotionSvc.getOneItemPromotionVO(item_promotion_id);

			if (itemPromotionVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/item_promotion/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("itemPromotionVO", itemPromotionVO);
			String url = ("/back_end/item_promotion/listOneItemPromotion.jsp");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String item_promotion_id = req.getParameter("item_promotion_id");

				/*************************** 2.開始查詢資料 ****************************************/
				ItemPromotionService itemPromotionSvc = new ItemPromotionService();
				ItemPromotionVO itemPromotionVO = itemPromotionSvc.getOneItemPromotionVO(item_promotion_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("itemPromotionVO", itemPromotionVO);
				String url = "/back_end/item_promotion/update_item_promotion_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/item_promotion/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String item_promotion_id = req.getParameter("item_promotion_id");
				String item_id = req.getParameter("item_id");
				String item_idReg = "^I[1-9][0-9]{4}$";
				if (item_id == null || item_id.trim().length() == 0) {
					errorMsgs.add("商品編號: 請勿空白");
				} else if (!item_id.trim().matches(item_idReg)) {
					errorMsgs.add("商品編號: 必須為 I 開頭 + 五位數字");
				}
				
				String item_promotion_info = req.getParameter("item_promotion_info");
				String item_promotion_idReg = "^IP[1-9][0-9]{4}$";
				Float item_discount = null;
				try {
					item_discount = new Float(req.getParameter("item_discount"));
				}catch (NumberFormatException e) {
					item_discount = 0F;
					errorMsgs.add("折扣請填數字.");
				}
				
				java.sql.Date item_prom_start_date = null;
				try {
					item_prom_start_date = java.sql.Date
					.valueOf(req.getParameter("item_prom_start_date").trim());
				}catch(IllegalArgumentException e) {
					item_prom_start_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date item_prom_close_date = java.sql.Date
						.valueOf(req.getParameter("item_prom_close_date").trim());
				try {
					item_prom_close_date = java.sql.Date
					.valueOf(req.getParameter("item_prom_close_date").trim());
				}catch(IllegalArgumentException e) {
					item_prom_close_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				ItemPromotionVO itemPromotionVO = new ItemPromotionVO();
				itemPromotionVO.setItem_promotion_id(item_promotion_id);
				itemPromotionVO.setItem_id(item_id);
				itemPromotionVO.setItem_promotion_info(item_promotion_info);
				itemPromotionVO.setItem_discount(item_discount);
				itemPromotionVO.setItem_prom_start_date(item_prom_start_date);
				itemPromotionVO.setItem_prom_close_date(item_prom_close_date);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemPromotionVO", itemPromotionVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/item_promotion/update_item_promotion_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				ItemPromotionService itemPromotionSvc = new ItemPromotionService();
				itemPromotionVO = itemPromotionSvc.updateItemPromotion(item_promotion_id, item_id, item_promotion_info,
						item_discount, item_prom_start_date, item_prom_close_date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemPromotionVO", itemPromotionVO);
				String url = "/back_end/item_promotion/listOneItemPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/item_promotion/update_item_promotion_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/

			
				String item_id = req.getParameter("item_id");
				String item_idReg = "^I[1-9][0-9]{4}$";
				if (item_id == null || item_id.trim().length() == 0) {
					errorMsgs.add("商品編號: 請勿空白");
				} else if (!item_id.trim().matches(item_idReg)) {
					errorMsgs.add("商品編號: 必須為 I 開頭 + 五位數字");
				}
				
				String item_promotion_info = req.getParameter("item_promotion_info");
				
				Float item_discount = null;
				try {
					item_discount = new Float(req.getParameter("item_discount"));
				}catch (NumberFormatException e) {
					item_discount = 0F;
					errorMsgs.add("折扣請填數字.");
				}
				
				java.sql.Date item_prom_start_date = null;
				try {
					item_prom_start_date = java.sql.Date
					.valueOf(req.getParameter("item_prom_start_date").trim());
				}catch(IllegalArgumentException e) {
					item_prom_start_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date item_prom_close_date = java.sql.Date
						.valueOf(req.getParameter("item_prom_close_date").trim());
				try {
					item_prom_close_date = java.sql.Date
					.valueOf(req.getParameter("item_prom_close_date").trim());
				}catch(IllegalArgumentException e) {
					item_prom_close_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				ItemPromotionVO itemPromotionVO = new ItemPromotionVO();
				itemPromotionVO.setItem_id(item_id);
				itemPromotionVO.setItem_promotion_info(item_promotion_info);
				itemPromotionVO.setItem_discount(item_discount);
				itemPromotionVO.setItem_prom_start_date(item_prom_start_date);
				itemPromotionVO.setItem_prom_close_date(item_prom_close_date);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemPromotionVO", itemPromotionVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/item_promotion/addItemPromotion.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				ItemPromotionService itemPromotionSvc = new ItemPromotionService();
				itemPromotionVO = itemPromotionSvc.addItemPromotion(item_id, item_promotion_info, item_discount, item_prom_start_date, item_prom_close_date);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/item_promotion/listAllItemPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
				

			} catch (Exception e) {
				errorMsgs.add("資料修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/item_promotion/addItemPromotion.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String item_promotion_id = req.getParameter("item_promotion_id");
				/*************************** 2.開始刪除資料 ***************************************/
				ItemPromotionService itemPromotionSvc = new ItemPromotionService();
				itemPromotionSvc.deleteItemPromotion(item_promotion_id);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/item_promotion/listAllItemPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/item_promotion/listAllItemPromotion.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
