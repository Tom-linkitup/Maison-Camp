package com.func.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_func.model.*;
import com.func.model.*;

@WebServlet("/FuncServlet")
public class FuncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String func_id = req.getParameter("func_id");
				FuncService funcSvc = new FuncService();
				funcSvc.deleteFunc(func_id);
				
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/viewFunc.jsp");
				failureView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("需先刪除其他表格的關聯");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/viewFunc.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				String func_id = req.getParameter("func_id");
				String func_idReg = "^[0-9]{4}$";
				if(func_id == null || func_id.trim().length() == 0) {
					errorMsgs.add("功能編號: 請勿空白");
				}else if(!func_id.trim().matches(func_idReg)) {
					errorMsgs.add("功能編號: 格式錯誤 須為0000~9999");
				}
				
				String func_name = req.getParameter("func_name");
				if(func_name == null || func_name.trim().length() == 0) {
					errorMsgs.add("功能編號: 請勿空白");
				}
				String func_info = req.getParameter("func_info");
				if(func_info == null || func_info.trim().length() == 0) {
					errorMsgs.add("功能編號: 請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/addFunc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				FuncService funcSvc = new FuncService();
				funcSvc.addFunc(func_id,func_name,func_info);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/viewFunc.jsp");
				failureView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("錯誤: 該功能編號已存在");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/addFunc.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
