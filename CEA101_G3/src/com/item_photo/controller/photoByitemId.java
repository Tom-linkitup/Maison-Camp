package com.item_photo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item_photo.model.ItemPhotoService;
import com.item_photo.model.ItemPhotoVO;

@WebServlet("/photoByitemId")
public class photoByitemId extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		
			String itemId = req.getParameter("itemId");
			
			ItemPhotoService itemPhotoSvc = new ItemPhotoService();
			
			ItemPhotoVO itemPhotoVO = itemPhotoSvc.getOneByItemId(itemId);
			
			byte[] buf = itemPhotoVO.getContent();
			
			res.getOutputStream().write(buf);
			res.flushBuffer();
	}

}
