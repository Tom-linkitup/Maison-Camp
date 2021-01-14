package com.shop_order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop_order_detail.model.ShopOrderDetailService;
import com.shop_order_detail.model.ShopOrderDetailVO;

@WebServlet("/findOrder")
public class findOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		String action = req.getParameter("action");
		
			String shopOrderId = req.getParameter("shopOrderId");
			ShopOrderDetailService shopDetailSvc = new ShopOrderDetailService();
			List<ShopOrderDetailVO> shopOrderDetails = shopDetailSvc.getAllByShopOrderDetail(shopOrderId);
			req.setAttribute("shopOrderDetails", shopOrderDetails);
			
			String url = "/front-end/item/memShopDetail.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		
	}

}
