package com.shop_order.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

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
		
		if(buylist == null) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/item/shoppingMall.jsp");
			failureView.forward(req, res);
			return;
		}
		
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
				
				//ws
				Set<javax.websocket.Session> orderSession = (Set<javax.websocket.Session>) session.getAttribute("orderSession");
				if (orderSession != null && orderSession.size() > 0) {
					JSONObject data = new JSONObject();
					try {
						data.put("newOrder","新增訂單成功");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					orderSession.forEach(e -> e.getAsyncRemote().sendText(data.toString()));
				}
				//
				
				
				session.removeAttribute("shoppingcart");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = req.getContextPath()+"/front-end/thankYouPage/ThankYou.jsp";
				res.sendRedirect(url);
				return;
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/item/shoppingMall.jsp");
				failureView.forward(req, res);
				return;
			}
		}


	}
}
