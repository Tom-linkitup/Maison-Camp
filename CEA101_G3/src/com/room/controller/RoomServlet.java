package com.room.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.room.model.RoomService;
import com.room.model.RoomVO;

@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				String room_category_id = req.getParameter("room_category_id");
				if("notChoice".equals(room_category_id)) {
					errorMsgs.put("room_category_id", "*請選擇房型編號");
				}
				
				Integer status = new Integer(req.getParameter("status"));
				if(status == 99) {
					errorMsgs.put("status", "*請選擇房間狀態");
				}
				
				Integer occupy = new Integer(req.getParameter("occupy"));
				if(occupy == 100) {
					errorMsgs.put("occupy", "*請選擇入住狀態");
				}
				
				if(!errorMsgs.isEmpty()) {			
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/RoomInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_category_id(room_category_id);
				roomVO.setStatus(status);
				roomVO.setOccupy(occupy);
				
				RoomService roomSvc = new RoomService();
				roomVO = roomSvc.addRM(room_category_id, status, occupy);
				
				String url = "/back-end/room/RoomInfo.jsp";
				req.setAttribute("insertSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}
		
		if("update".equals(action)) {
			
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			
			Map<String, String> errorUpdateMsgs = new LinkedHashMap<>();
			req.setAttribute("errorUpdateMsgs", errorUpdateMsgs);
			
			String room_id = req.getParameter("room_id");
			
			try{
				String room_category_id = req.getParameter("room_category_id");
				if("notChoice".equals(room_category_id)) {
					errorUpdateMsgs.put("room_category_id", "*請選擇房型編號");
				}
				
				Integer status = new Integer(req.getParameter("status"));
				if(status == 99) {
					errorUpdateMsgs.put("status", "*請選擇房間狀態");
				}
				
				Integer occupy = new Integer(req.getParameter("occupy"));
				if(status == 100) {
					errorUpdateMsgs.put("occupy", "*請選擇房間狀態");
				}
				
				if(!errorUpdateMsgs.isEmpty()) {			
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/room/RoomInfo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				RoomVO roomVO = new RoomVO();
				roomVO.setRoom_category_id(room_category_id);
				roomVO.setStatus(status);
				
				RoomService roomSvc = new RoomService();
				roomVO = roomSvc.updateRM(room_category_id, status, occupy, room_id);
				
				String url = "/back-end/room/RoomInfo.jsp";
				req.setAttribute("updateSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}
		
		if("delete".equals(action)) {
			
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			
			String room_id = req.getParameter("room_id");
			
			RoomService roomSvc = new RoomService();
			roomSvc.deleteRM(room_id);
			
			String url = "/back-end/room/RoomInfo.jsp";
			req.setAttribute("deleteSuccess", "yes");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if("getRoomByRtc".equals(action)) {
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			
			String room_category_id = req.getParameter("room_category_id");
			
			RoomService roomSvc = new RoomService();
			List<RoomVO> getRoomVoByRtc = roomSvc.getRmByRTC(room_category_id);
			String url = "/back-end/room/RoomInfo.jsp";
			req.setAttribute("getRoomVoByRtc", getRoomVoByRtc);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
	}

}
