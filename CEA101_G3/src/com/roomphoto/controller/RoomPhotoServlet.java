package com.roomphoto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.roomphoto.model.RoomPhotoService;
import com.roomphoto.model.RoomPhotoVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RoomPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String action = req.getParameter("action");
					
		
		if("upload".equals(action)) {
			
			Map<String, String> errorPhotoMsgs = new LinkedHashMap<>();
			req.setAttribute("errorPhotoMsgs", errorPhotoMsgs);
			
			String room_category_id = req.getParameter("room_category_id");
			
			if("noSelectRoomType".equals(room_category_id)) {
				errorPhotoMsgs.put("room_category_id", "*請選擇房型編號");
				String url = "/back-end/room-type/RoomTypeInfo.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			Collection<Part> parts = req.getParts();
			for(Part part : parts) {
				if(part.getContentType() != null) {
					InputStream in = part.getInputStream();
					byte[] b = new byte[in.available()];
					in.read(b);
					in.close();	
					
					RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
					roomPhotoVO.setRoom_category_id(room_category_id);
					roomPhotoVO.setContent(b);
					
					RoomPhotoService roomPhotoSvc = new RoomPhotoService();
					roomPhotoVO = roomPhotoSvc.addRPH(room_category_id, b);		
				}
			}
			
			String url = "/back-end/room-type/RoomTypeInfo.jsp";
			req.setAttribute("uploadSuccess", "uploadSuccess");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if("delete".equals(action)) {
			String room_photo_id = req.getParameter("room_photo_id");
			RoomPhotoService roomPhotoSvc = new RoomPhotoService();
			
			roomPhotoSvc.deleteRPH(room_photo_id.trim());
			
			req.setAttribute("deletePicSucess", "deletePicSuccess");
			return;
		}
	}
}
