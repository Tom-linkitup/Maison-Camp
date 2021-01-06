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

public class getItemPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
			String itemId = req.getParameter("itemId");
			
			ItemPhotoService itemPhotoSvc = new ItemPhotoService();
			
			List<ItemPhotoVO> iphList = itemPhotoSvc.getAll(itemId);
			
			req.setAttribute("iphList", iphList);
			
			String forurl = "/back-end/item/ItemInfo.jsp";
			req.setAttribute("query", "query");
			RequestDispatcher successView = req.getRequestDispatcher(forurl);
			successView.forward(req, res);
	}
}
