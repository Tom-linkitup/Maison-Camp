package com.roomphoto.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;

public class GetRoomPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String room_category_id = req.getParameter("room_category_id");
		
		RoomPhotoService rphSvc = new RoomPhotoService();
		
		List<RoomPhotoVO> rphList = rphSvc.getAllRPH(room_category_id);
		
		req.setAttribute("rphList", rphList);
		
		String forurl = "/back-end/room-type/RoomTypeInfo.jsp";
		req.setAttribute("query", "query");
		RequestDispatcher successView = req.getRequestDispatcher(forurl);
		successView.forward(req, res);	
		return;
	}

}
