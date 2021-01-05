package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.emp_func.model.EmpFuncService;

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");	
		String action = req.getParameter("action");
		
//		if ("getOne_For_Display".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String emp_id = req.getParameter("emp_id");
//				if (emp_id == null || (emp_id.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				/*************************** 2.開始查詢資料 *****************************************/
//
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(emp_id);
//				if (empVO == null) {
//					errorMsgs.add("查無資料");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
//				String url = "/back_end/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_id = req.getParameter("emp_id");

				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/emp/protected/updateEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/updateEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String emp_id = req.getParameter("emp_id").trim();

				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String emp_user_id = req.getParameter("emp_user_id");
				String emp_user_idReg = "^[a-zA-Z0-9_]{6,20}$";
				if (emp_user_id == null || emp_user_id.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}else if(!emp_user_id.trim().matches(emp_user_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 只能是英文字母、數字 , 且長度必需在6到20之間");
	            }
				

				String emp_user_pwd = req.getParameter("emp_user_pwd");
				String emp_user_pwdReg = "^[a-zA-Z0-9_]{6,20}$";
				if (emp_user_pwd == null || emp_user_pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				}else if(!emp_user_pwd.trim().matches(emp_user_pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 只能是英文字母、數字 , 且長度必需在6到20之間");
	            }
				
				Integer emp_status = null;
				emp_status = new Integer(req.getParameter("emp_status"));


				EmpVO empVO = new EmpVO();
				empVO.setEmp_id(emp_id);
				empVO.setEmp_user_id(emp_user_id);
				empVO.setEmp_user_pwd(emp_user_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_status(emp_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/updateEmp.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_user_id, emp_user_pwd, emp_name, emp_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/emp/protected/viewEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/updateEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateSelf".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_id = req.getParameter("emp_id");

				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/emp/protected/updateEmpBySelf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/lookYourSelf.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateS".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String emp_id = req.getParameter("emp_id").trim();

				String emp_name = req.getParameter("emp_name");

				String emp_user_id = req.getParameter("emp_user_id");

				String emp_user_pwd = req.getParameter("emp_user_pwd");
				String emp_user_pwdReg = "^[a-zA-Z0-9_]{6,20}$";
				if (emp_user_pwd == null || emp_user_pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				}else if(!emp_user_pwd.trim().matches(emp_user_pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 只能是英文字母、數字 , 且長度必需在6到20之間");
	            }
				
				Integer emp_status = null;
				emp_status = new Integer(req.getParameter("emp_status"));


				EmpVO empVO = new EmpVO();
				empVO.setEmp_id(emp_id);
				empVO.setEmp_user_id(emp_user_id);
				empVO.setEmp_user_pwd(emp_user_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_status(emp_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/updateEmpBySelf.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_user_id, emp_user_pwd, emp_name, emp_status);
				
				HttpSession session = req.getSession();
				session.setAttribute("loginEmpVO", empVO);
				

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/emp/protected/lookYourSelf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/updateEmpBySelf.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/		
				String emp_name = req.getParameter("emp_name");
				String emp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!emp_name.trim().matches(emp_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String emp_user_id = req.getParameter("emp_user_id");
				String emp_user_idReg = "^(a-zA-Z0-9_){6,20}$";
				if (emp_user_id == null || emp_user_id.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}else if(!emp_user_id.trim().matches(emp_user_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 只能是英文字母、數字 , 且長度必需在6到20之間");
	            }
				

				String emp_user_pwd = req.getParameter("emp_user_pwd");
				String emp_user_pwdReg = "^(a-zA-Z0-9_){6,20}$";
				if (emp_user_pwd == null || emp_user_pwd.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				}else if(!emp_user_id.trim().matches(emp_user_pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 只能是英文字母、數字 , 且長度必需在6到20之間");
	            }
				
				Integer emp_status = null;
				emp_status = new Integer(req.getParameter("emp_status"));

				EmpVO empVO = new EmpVO();
				empVO.setEmp_user_id(emp_user_id);
				empVO.setEmp_user_pwd(emp_user_pwd);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_status(emp_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_user_id, emp_user_pwd, emp_name, emp_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/emp/protected/viewEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/protected/addEmp.jsp");
				failureView.forward(req, res);
			}
		}


		if ("logout".equals(action)) {
			HttpSession session = req.getSession();

			Cookie[] cookies = req.getCookies();// 使用者登出後 把Cookie清掉
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("username".equals(c.getName())) {
						
						c.setMaxAge(0);
						c.setPath(req.getContextPath());
						res.addCookie(c);
					}
				}
			}		
			session.invalidate();		
			res.sendRedirect(req.getContextPath() + "/back_end/emp/loginEmp.jsp");
//			RequestDispatcher tologin = req.getRequestDispatcher("/back_end/emp/loginEmp.jsp");
//			tologin.forward(req, res);
		}
		
	}
	
}
