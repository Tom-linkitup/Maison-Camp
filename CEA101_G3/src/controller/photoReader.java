package com.item_photo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item_photo.model.ItemPhotoService;
import com.item_photo.model.ItemPhotoVO;

public class photoReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		
			String itemPhotoId = req.getParameter("itemPhotoId");
			
			ItemPhotoService itemPhotoSvc = new ItemPhotoService();
			
			ItemPhotoVO itemPhotoVO = itemPhotoSvc.getOneItemPhoto(itemPhotoId);
			
			byte[] buf = itemPhotoVO.getContent();
			
			res.getOutputStream().write(buf);
			res.flushBuffer();
	}
}
