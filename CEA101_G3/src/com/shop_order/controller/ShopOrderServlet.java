package com.shop_order.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.shop_order.model.*;

public class ShopOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String shop_order_id = req.getParameter("shop_order_id");

			if (shop_order_id == null || (shop_order_id.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shop_order/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ShopOrderService shopOrderSvc = new ShopOrderService();
			ShopOrderVO shopOrderVO = shopOrderSvc.getOneShopOrder(shop_order_id);

			if (shopOrderVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shop_order/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("shopOrderVO", shopOrderVO);
			String url = "/front_end/shop_order/listOneShopOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String shop_order_id = req.getParameter("shop_order_id");

				/*************************** 2.開始查詢資料 ****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				ShopOrderVO shopOrderVO = shopOrderSvc.getOneShopOrder(shop_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shopOrderVO", shopOrderVO);
				String url = "/front_end/shop_order/update_shop_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shop_order/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String shop_order_id = req.getParameter("shop_order_id");
				String mem_id = req.getParameter("mem_id");
				String payment = req.getParameter("payment");
				java.sql.Date time = java.sql.Date.valueOf(req.getParameter("time").trim());
				String tmp = req.getParameter("shop_total_amount");
				Float shop_total_amount = new Float(tmp);
				Integer status = new Integer(req.getParameter("status"));
				

				ShopOrderVO shopOrderVO = new ShopOrderVO();
				shopOrderVO.setShop_order_id(shop_order_id);
				shopOrderVO.setMem_id(mem_id);
				shopOrderVO.setPayment(payment);
				shopOrderVO.setTime(time);
				shopOrderVO.setShop_total_amount(shop_total_amount);
				shopOrderVO.setStatus(status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopOrderVO", shopOrderVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order_detail/ItemOrderInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				shopOrderVO = shopOrderSvc.updateShopOrder(mem_id, payment, time, shop_total_amount, status,
						shop_order_id);
				System.out.println("00000");

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopOrderVO", shopOrderVO);
				String url = "/back-end/shop_order_detail/ItemOrderInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shop_order_detail/ItemOrderInfo.jsp");
				failureView.forward(req, res);
			}
		}



	}
}
