package com.roomphoto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;


@WebServlet("/FrontEndRTPhoto")
public class FrontEndRTPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		
		String room_category_id = req.getParameter("room_category_id");
		
		RoomPhotoService rphSvc = new RoomPhotoService();
		
		List<RoomPhotoVO> rphList = rphSvc.getAllRPH(room_category_id);
		
		for(RoomPhotoVO rph : rphList) {
			RoomPhotoVO roomPhotoVO = rphSvc.getOneRPH(rph.getRoom_photo_id());
			byte[] buf = roomPhotoVO.getContent();	
			res.getOutputStream().write(buf);
		}
		
	}

}
