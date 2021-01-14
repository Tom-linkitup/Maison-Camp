package com.roomorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomorder.model.RoomOrderDAO;
import com.roomorder.model.RoomOrderService;
import com.roomorder.model.RoomOrderVO;
import com.roomorderdetail.model.RoomOrderDetailService;
import com.roomorderdetail.model.RoomOrderDetailVO;
import com.roomrsv.model.RoomRsvDAO;
import com.roomtype.model.RoomTypeService;

@WebServlet("/RoomOrderServlet")
public class RoomOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
		if("update".equals(action)) {
			try {
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
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("insert".equals(action)) {
			
			try {	
				// 取得前台訂單資訊
				String mem_id = req.getParameter("mem_id");
				Integer stay = new Integer(req.getParameter("stay"));
				java.sql.Date check_in_date = java.sql.Date.valueOf(req.getParameter("check_in"));
				java.sql.Date check_out_date = java.sql.Date.valueOf(req.getParameter("check_out"));			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sdf.format(check_in_date);
				String room_category_id = req.getParameter("room_category_id");
				String room_promotion_id = req.getParameter("room_promotion_id");
				Integer room_order_price = Integer.parseInt(req.getParameter("room_order_price"));
				Integer quantity = new Integer(req.getParameter("quantity"));
				LocalDate order_time = LocalDate.now();
				String note = "you are ordering";
				
				JSONObject orderItem = new JSONObject();
				orderItem.put("stay", stay);
				orderItem.put("room_category_id", room_category_id);
				orderItem.put("startDate", startDate);
				orderItem.put("quantity", quantity);
				
				
				RoomOrderVO roVO = new RoomOrderVO();
				roVO.setMem_id(mem_id);
				roVO.setCheck_in_date(check_in_date);
				roVO.setCheck_out_date(check_out_date);
				roVO.setStatus(new Integer(0));
				
				List<RoomOrderDetailVO> rodlist = new ArrayList<>();
				RoomOrderDetailVO rodVO = new RoomOrderDetailVO();
				rodVO.setRoom_category_id(room_category_id);
				rodVO.setRoom_promotion_id(room_promotion_id);
				rodVO.setQuantity(quantity);
				rodVO.setRoom_order_price(room_order_price);
				rodVO.setOrder_time(java.sql.Date.valueOf(order_time));
				rodVO.setNote(note);
				
				rodlist.add(rodVO);
				
				RoomOrderDAO roDAO = new RoomOrderDAO();
				roDAO.insertWithDetails(roVO, rodlist, orderItem);
				res.sendRedirect(req.getContextPath() + "/front-end/thankYouPage/ThankYou.jsp");
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("cancelRoomOrder".equals(action)) {
			
			try {
				//取得取消訂單資料
				String room_order_id = req.getParameter("room_order_id");
				String room_category_id = req.getParameter("room_category_id");
				Integer quantity = new Integer(req.getParameter("quantity"));
				java.sql.Date check_in_date = java.sql.Date.valueOf(req.getParameter("check_in_date"));
				java.sql.Date check_out_date = java.sql.Date.valueOf(req.getParameter("check_out_date"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sdf.format(check_in_date);
				
				//計算訂單住房天數更新預定表
				LocalDate orderFrom = check_in_date.toLocalDate();
				LocalDate orderTo = check_out_date.toLocalDate();
				Integer stay = orderTo.compareTo(orderFrom);
				System.out.println("入住天數為" + stay);
				
				JSONObject orderItem = new JSONObject();
				orderItem.put("stay", stay);
				orderItem.put("room_category_id", room_category_id);
				orderItem.put("startDate", startDate);
				orderItem.put("quantity", quantity);
					
				//呼叫 order DAO 更新訂單狀態為取消
				RoomOrderDAO dao = new RoomOrderDAO();
				dao.updateWithRsv(new Integer(1), room_order_id, orderItem);
				
				res.sendRedirect(req.getContextPath() + "/front-end/member/Member.jsp");
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}
		
		if("getAvailableRoom".equals(action)) {
			try {
				String room_order_id = req.getParameter("room_order_id");
				RoomOrderDetailService rodSvc = new RoomOrderDetailService();
				RoomTypeService rtSvc = new RoomTypeService();
				JSONObject roomobj = new JSONObject();
				//取得房型名稱
				String room_name = rtSvc.getOneRT(rodSvc.getOneROD(room_order_id).getRoom_category_id()).getRoom_name();
				//取得可選用房間編號
				RoomService rmSvc = new RoomService();
				List<RoomVO> rmlist = rmSvc.getRmByRTC(rodSvc.getOneROD(room_order_id).getRoom_category_id());
				roomobj.put("room_order_id", room_order_id);
				roomobj.put("rmlist", rmlist);
				roomobj.put("room_name", room_name);
				out.print(roomobj);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if("updateRoom".equals(action)) {
			try {
				String room_id = req.getParameter("selected_room_id");
				String room_order_id = req.getParameter("room_order_id");
				RoomOrderService roSvc = new RoomOrderService();
				roSvc.updateOrderCondition(new Integer(2), room_id, room_order_id);
				RoomService rmSvc = new RoomService();
				rmSvc.updateRmLive(new Integer(1), room_id);
				String success = "success";
				out.print(success);			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("checkOut".equals(action)) {
			try {
				String room_order_id = req.getParameter("room_order_id");
				String room_id = req.getParameter("room_id");
				RoomOrderService roSvc = new RoomOrderService();
				roSvc.updateOrderCondition(new Integer(3), "已退房", room_order_id);
				RoomService rmSvc = new RoomService();
				rmSvc.updateRmLive(new Integer(0), room_id);
				String success = "success";
				out.print(success);		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("cancelRoomOrderFromBackend".equals(action)) {
			
			try {
				//取得取消訂單資料
				String room_order_id = req.getParameter("room_order_id");
				String room_category_id = req.getParameter("room_category_id");
				Integer quantity = new Integer(req.getParameter("quantity"));
				java.sql.Date check_in_date = java.sql.Date.valueOf(req.getParameter("check_in_date"));
				java.sql.Date check_out_date = java.sql.Date.valueOf(req.getParameter("check_out_date"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sdf.format(check_in_date);
				
				//計算訂單住房天數更新預定表
				LocalDate orderFrom = check_in_date.toLocalDate();
				LocalDate orderTo = check_out_date.toLocalDate();
				Integer stay = orderTo.compareTo(orderFrom);
				System.out.println("入住天數為" + stay);
				
				JSONObject orderItem = new JSONObject();
				orderItem.put("stay", stay);
				orderItem.put("room_category_id", room_category_id);
				orderItem.put("startDate", startDate);
				orderItem.put("quantity", quantity);
					
				//呼叫 order DAO 更新訂單狀態為取消
				RoomOrderDAO dao = new RoomOrderDAO();
				dao.updateWithRsv(new Integer(1), room_order_id, orderItem);
				
				String url = "/back-end/room-order/RoomOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}
		
	}

}
