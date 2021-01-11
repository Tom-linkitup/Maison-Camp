package com.emp_func.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_func.model.*;

public class EmpFuncServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String emp_id = req.getParameter("emp_id");
				String emp_idReg = "^E1[0-9]{4}$";
				if(emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.add("員工編號: 請勿空白");
				}else if(!emp_id.trim().matches(emp_idReg)) {
					errorMsgs.add("員工編號: 格式錯誤 須為(E1XXXX)");
				}
				String func_id = req.getParameter("func_id");
				String func_idReg = "^[0-9]{4}$";
				if(func_id == null || func_id.trim().length() == 0) {
					errorMsgs.add("功能編號: 請勿空白");
				}else if(!func_id.trim().matches(func_idReg)) {
					errorMsgs.add("功能編號: 須為(0000~9999)");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/addEmpFunc.jsp");
					failureView.forward(req, res);
					return;
				}

				EmpFuncService empFuncSvc = new EmpFuncService();
				empFuncSvc.addEmpFunc(emp_id, func_id);

				String url = "/back-end/emp/protected/viewEmpFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/addEmpFunc.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String emp_id = req.getParameter("emp_id");
				String func_id = req.getParameter("func_id");

				EmpFuncService empFuncSvc = new EmpFuncService();
				List<String> funcs = empFuncSvc.findEmpFuncs(emp_id);
				
				
				empFuncSvc.deleteEmpFunc(emp_id, func_id);
				
				String url = "/back-end/emp/protected/viewEmpFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				

			} catch (Exception e) {
				errorMsgs.add("需先刪除其他表格的關聯");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/protected/addEmpFunc.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
