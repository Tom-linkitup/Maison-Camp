package com.shop_order.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.shop_order.model.*;
import com.shop_order_detail.model.ShopOrderDetailService;
import com.shop_order_detail.model.ShopOrderDetailVO;

public class CreateShopOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF8");
		HttpSession session = req.getSession();
		Vector<Item> buylist = (Vector<Item>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_id = req.getParameter("mem_id");
				String payment = "creditcard";
				java.sql.Date time = new java.sql.Date(System.currentTimeMillis());

				Float shop_total_amount = new Float(req.getParameter("amount"));
				
				Integer status = 1;
				
				
				
				ShopOrderVO shopOrderVO = new ShopOrderVO(); 
				shopOrderVO.setMem_id(mem_id);
				shopOrderVO.setPayment(payment);
				shopOrderVO.setTime(time);
				shopOrderVO.setShop_total_amount(shop_total_amount);
				shopOrderVO.setStatus(status);

				List<ShopOrderDetailVO> list = new ArrayList<ShopOrderDetailVO>(); 
				ShopOrderDetailVO detailVO = null;
				for(int i = 0 ; i <buylist.size(); i++ ) {
					Item item = buylist.get(i);
					detailVO = new ShopOrderDetailVO();
					detailVO.setItem_id(item.getItemId());
					detailVO.setItem_price(item.getPrice());
					detailVO.setItem_promotion_id("IP10001");
					detailVO.setQuantity(item.getQuantity());
					detailVO.setNote("good");
					list.add(detailVO);		
				}
				

				/*************************** 2.開始修改資料 *****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				shopOrderSvc.addWithOrderDetail(shopOrderVO, list);
				System.out.println("訂單新增完成");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/item/shoppingMall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/item/shoppingMallllllllllllllllllllll.jsp");
				failureView.forward(req, res);
			}
		}


	}
}
