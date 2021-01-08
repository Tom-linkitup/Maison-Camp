package com.item_photo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		
			String id = req.getParameter("id");
			
			ItemPhotoService itemPhotoSvc = new ItemPhotoService();
			
			ItemPhotoVO itemPhotoVO = itemPhotoSvc.getOneItemPhoto(id);
			
			byte[] buf = itemPhotoVO.getContent();
			
			res.getOutputStream().write(buf);
	}
}
