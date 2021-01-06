package com.roomorder.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomorder.model.RoomOrderService;
import com.roomorder.model.RoomOrderVO;

@WebServlet("/RoomOrderServlet")
public class RoomOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		String action = req.getParameter("action");
		
		if("update".equals(action)) {	
			//取得欲更新資料
			String room_order_id = req.getParameter("room_order_id");
			String mem_id = req.getParameter("mem_id");
			java.sql.Date check_in_date = java.sql.Date.valueOf(req.getParameter("check_in_date"));
			java.sql.Date check_out_date = java.sql.Date.valueOf(req.getParameter("check_out_date"));
			Integer status = new Integer(req.getParameter("status"));
			
			//呼叫service開始更新
			RoomOrderService roSvc = new RoomOrderService();
			RoomOrderVO roomOrderVO = new RoomOrderVO();
			roomOrderVO = roSvc.updateRO(room_order_id, mem_id, check_in_date, check_out_date, status);
			
			String url = "/back-end/room-order/RoomOrder.jsp";
			req.setAttribute("roomOrderVO", roomOrderVO);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}

}