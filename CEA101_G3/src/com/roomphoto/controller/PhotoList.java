package com.roomphoto.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;

public class PhotoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		
		String room_photo_id = req.getParameter("room_photo_id");
		
		RoomPhotoService rphSvc = new RoomPhotoService();
		
		RoomPhotoVO roomPhotoVO  = rphSvc.getOneRPH(room_photo_id);
		
		byte[] buf = roomPhotoVO.getContent();
		
		res.getOutputStream().write(buf);
		res.flushBuffer();	
	}
}
