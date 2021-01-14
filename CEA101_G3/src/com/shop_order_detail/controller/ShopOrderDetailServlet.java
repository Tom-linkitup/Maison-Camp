package com.shop_order_detail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.shop_order_detail.model.*;

@WebServlet("/ShopOrderDetailServlet")
public class ShopOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/shop_order_detail/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 *****************************************/
			ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
			List<ShopOrderDetailVO> shopOrderDetailVO = shopOrderDetailSvc.getAllByShopOrderDetail(shop_order_id);

			if (shopOrderDetailVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/shop_order_detail/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("shopOrderDetailVO", shopOrderDetailVO);
			String url = "/back_end/shop_order_detail/listOneShopOrderDetail.jsp";
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
				ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
				List<ShopOrderDetailVO> shopOrderDetailVO = shopOrderDetailSvc.getAllByShopOrderDetail(shop_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shopOrderDetailVO", shopOrderDetailVO);
				String url = "/back_end/shop_order_detail/update_shop_order_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/shop_order_detail/select_page.jsp");
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
				String item_id = req.getParameter("item_id");
//				String item_idReg = "^I[1-9][0-9]{4}$";
//				if (item_id == null || item_id.trim().length() == 0) {
//					errorMsgs.add("商品編號: 請勿空白");
//				} else if (!item_id.trim().matches(item_idReg)) {
//					errorMsgs.add("商品編號: 必須為 I 開頭 + 五位數字");
//				}

				String item_promotion_id = req.getParameter("item_promotion_id");
//				String item_promotion_idReg = "^IP[1-9][0-9]{4}$";
//				if(item_promotion_id == null || item_promotion_id.trim().length() == 0) {
//					errorMsgs.add("商品促銷編號: 請勿空白");
//				}else if(!item_promotion_id.trim().matches(item_promotion_idReg)){
//					errorMsgs.add("商品促銷編號: 必須為IP開頭 + 五位數字");
//				}
				
				String note = req.getParameter("note");
				
				Integer quantity =null;
				try {
					quantity = new Integer(req.getParameter("quantity"));
				}catch(NumberFormatException e) {
					quantity = 1;
					errorMsgs.add("商品數量為數字");
				}
				Integer item_price = null;
				try {
					item_price = new Integer(req.getParameter("item_price"));
				}catch(NumberFormatException e) {
					item_price = 0;
					errorMsgs.add("商品價格為數字");
				}
				
				ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();
				shopOrderDetailVO.setItem_promotion_id(item_promotion_id);
				shopOrderDetailVO.setItem_id(item_id);
				shopOrderDetailVO.setItem_promotion_id(item_promotion_id);
				shopOrderDetailVO.setNote(note);
				shopOrderDetailVO.setQuantity(quantity);
				shopOrderDetailVO.setItem_price(item_price);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("shopOrderDetailVO", shopOrderDetailVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/shop_order_detail/update_shop_order_detail_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
				shopOrderDetailVO = shopOrderDetailSvc.updateShopOrderDetail(shop_order_id,
						item_id, item_promotion_id, note, quantity, item_price);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shopOrderDetailVO", shopOrderDetailVO);
				String url = "/back_end/shop_order_detail/listOneShopOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/shop_order_detail/update_shop_order_detail_input.jsp");
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

				String item_promotion_id = req.getParameter("item_promotion_id");
				String item_promotion_idReg = "^IP[1-9][0-9]{4}$";
				if(item_promotion_id == null || item_promotion_id.trim().length() == 0) {
					errorMsgs.add("商品促銷編號: 請勿空白");
				}else if(!item_promotion_id.trim().matches(item_promotion_idReg)){
					errorMsgs.add("商品促銷編號: 必須為IP開頭 + 五位數字");
				}
				
				String note = req.getParameter("note");
				
				Integer quantity =null;
				try {
					quantity = new Integer(req.getParameter("quantity"));
				}catch(NumberFormatException e) {
					quantity = 1;
					errorMsgs.add("商品數量為數字");
				}
				Integer item_price = null;
				try {
					item_price = new Integer(req.getParameter("item_price"));
				}catch(NumberFormatException e) {
					item_price = 0;
					errorMsgs.add("商品價格為數字");
				}
				
				ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();
				shopOrderDetailVO.setItem_promotion_id(item_promotion_id);
				shopOrderDetailVO.setItem_id(item_id);
				shopOrderDetailVO.setItem_promotion_id(item_promotion_id);
				shopOrderDetailVO.setNote(note);
				shopOrderDetailVO.setQuantity(quantity);
				shopOrderDetailVO.setItem_price(item_price);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("shopOrderDetailVO", shopOrderDetailVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/shop_order_detail/update_shop_order_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
				shopOrderDetailVO = shopOrderDetailSvc.addShopOrderDetail(item_id, item_promotion_id, note, quantity, item_price);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/shop_order_detail/listAllShopOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			}catch (Exception e) {
				errorMsgs.add("資料修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shop_order/addShopOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String shop_order_id = req.getParameter("shop_order_id");
				/*************************** 2.開始刪除資料 ***************************************/
				ShopOrderDetailService shopOrderDetailSvc = new ShopOrderDetailService();
				shopOrderDetailSvc.deleteShopOrderDetail(shop_order_id);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/shop_order_detail/listAllShopOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/shop_order_detail/listAllShopOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
