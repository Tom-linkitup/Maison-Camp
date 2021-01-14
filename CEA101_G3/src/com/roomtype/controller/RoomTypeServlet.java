package com.roomtype.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class RoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {			
				String room_category_id = req.getParameter("room_category_id").trim();
				RoomTypeService roomTypeSvcTest = new RoomTypeService();
				
				List<RoomTypeVO> roomTypeList = roomTypeSvcTest.getAllRT();
				for(RoomTypeVO rt : roomTypeList) {
					if(room_category_id.equals(rt.getRoom_category_id())) {
						req.setAttribute("repeat", "repeat");
					}
				}
				if(room_category_id == null || room_category_id.trim().length() == 0) {
					errorMsgs.put("room_category_id", "*房型編號不得為空");
				}
					
				String room_name = req.getParameter("room_name").trim();
				if(room_name == null || room_name.trim().length() == 0) {
					errorMsgs.put("room_name", "*房型名稱不得為空");
				}
				
				String room_type = req.getParameter("room_type");
				if(room_type == null || room_type.trim().length() == 0) {
					errorMsgs.put("room_type", "*房型類別不得為空");
				}
				Integer room_price = null;
				try {
					room_price = new Integer(req.getParameter("room_price").trim());	
				}catch(NumberFormatException e) {
					errorMsgs.put("room_price", "*請輸入整數");
				}
				Integer area = null;
				try {
					area = new Integer(req.getParameter("area").trim());	
				}catch(NumberFormatException e) {
					errorMsgs.put("area", "*請輸入整數");
				}
				Integer room_guest = null;
				try {
					room_guest = new Integer(req.getParameter("room_guest").trim());	
				}catch(NumberFormatException e) {
					errorMsgs.put("room_guest", "*請輸入整數");
				}
				Integer room_quantity = null;
				try {
					room_quantity = new Integer(req.getParameter("room_quantity").trim());	
				}catch(NumberFormatException e) {
					errorMsgs.put("room_quantity", "*請輸入整數");
				}
				Integer room_category_status = null;
				try {
					room_category_status = new Integer(req.getParameter("room_category_status").trim());		
				}catch(NumberFormatException e) {
					errorMsgs.put("room_category_status", "*請輸入0或1");
				}
				String room_info = req.getParameter("room_info").trim();
				if(room_info == null || room_info.trim().length() == 0) {
					errorMsgs.put("room_info", "*房型敘述不得為空");
				}
				
				if(!errorMsgs.isEmpty()) {			
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room-type/RoomTypeInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				RoomTypeVO roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_category_id(room_category_id);
				roomTypeVO.setRoom_name(room_name);
				roomTypeVO.setRoom_type(room_type);
				roomTypeVO.setRoom_price(room_price);
				roomTypeVO.setArea(area);
				roomTypeVO.setRoom_guest(room_guest);
				roomTypeVO.setRoom_quantity(room_quantity);
				roomTypeVO.setRoom_category_status(room_category_status);
				roomTypeVO.setRoom_info(room_info);
				req.setAttribute("roomTypeVO", roomTypeVO);
				
				RoomTypeService roomTypeSvc = new RoomTypeService();			
				roomTypeSvc.addRT(room_category_id, room_name, room_type, room_price, area, room_guest, room_quantity, room_category_status, room_info);
				
				String url = "/back-end/room-type/RoomTypeInfo.jsp";
				req.setAttribute("insertSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String room_category_id = req.getParameter("room_category_id");
			
				RoomTypeService roomTypeSvc = new RoomTypeService();
				RoomTypeVO roomTypeVO = roomTypeSvc.getOneRT(room_category_id);
								
				
				req.setAttribute("roomTypeVO", roomTypeVO); 
				String url = "/back-end/room-type/RoomTypeInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("update".equals(action)) {
			
			try {
				String room_category_id = req.getParameter("room_category_id");
				String room_name = req.getParameter("room_name");
				String room_type = req.getParameter("room_type");
				Integer room_price = new Integer(req.getParameter("room_price"));
				Integer area = new Integer(req.getParameter("area"));
				Integer room_guest = new Integer(req.getParameter("room_guest"));
				Integer room_quantity = new Integer(req.getParameter("room_quantity"));
				Integer room_category_status = new Integer(req.getParameter("room_category_status"));
				String room_info = req.getParameter("room_info");
				
				RoomTypeVO roomTypeVO = new RoomTypeVO();
				
				roomTypeVO.setRoom_category_id(room_category_id);
				roomTypeVO.setRoom_name(room_name);
				roomTypeVO.setRoom_type(room_type);
				roomTypeVO.setRoom_price(room_price);
				roomTypeVO.setArea(area);
				roomTypeVO.setRoom_category_status(room_category_status);
				roomTypeVO.setRoom_info(room_info);
				
				RoomTypeService roomTypeSvc = new RoomTypeService();
				roomTypeVO = roomTypeSvc.updateRT(room_category_id, room_name, room_type, room_price, area, room_guest, room_quantity, room_category_status, room_info);
				
				String url = "/back-end/room-type/RoomTypeInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("delete".equals(action)) {
			String room_category_id = req.getParameter("room_category_id");
			
			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeSvc.deleteRT(room_category_id);
			
			String url = "/back-end/room-type/RoomTypeInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
			return;
		}
	}

}
