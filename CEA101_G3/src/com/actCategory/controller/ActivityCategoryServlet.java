package com.actCategory.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.actCategory.model.*;

public class ActivityCategoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actCategoryId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actcategory/selectPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String actCategoryId = null;
				try {
					actCategoryId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("活動類別編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actcategory/selectPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActCategoryService actCategorySvc = new ActCategoryService();
				ActCategoryVO actCategoryVO = actCategorySvc.getOneActCategory(actCategoryId);
				if (actCategoryVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actcategory/selectPage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actCategoryVO", actCategoryVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/actcategory/listOneActCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actcategory/selectPage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String actCategoryId = new String(req.getParameter("actCategoryId"));
				
				/***************************2.開始查詢資料****************************************/
				ActCategoryService actCategorySvc = new ActCategoryService();
				ActCategoryVO actCategoryVO = actCategorySvc.getOneActCategory(actCategoryId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actCategoryVO", actCategoryVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/actcategory/updateActCategoryInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actcategory/listAllActCategory.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String myRegex = "^ACT_CATEGORY[0-9]+$";
				String actCategoryId = new String(req.getParameter("actCategoryId").trim());
				if (actCategoryId == null || actCategoryId.trim().length() == 0) {
					errorMsgs.add("活動類別編號請勿空白");
				} else if(!actCategoryId.trim().matches(myRegex)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動類別編號只能是ACT_CATEGORY開頭");
	            }
				String actCategoryName = req.getParameter("actCategoryName").trim();
				if (actCategoryName == null || actCategoryName.trim().length() == 0) {
					errorMsgs.add("活動類別名稱請勿空白");
				}	


				ActCategoryVO actCategoryVO = new ActCategoryVO();
				actCategoryVO.setActCategoryId(actCategoryId);
				actCategoryVO.setActCategoryName(actCategoryName);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actCategoryVO", actCategoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/actcategory/updateActCategoryInput.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActCategoryService actCategorySvc = new ActCategoryService();
				actCategoryVO = actCategorySvc.updateActCategor(actCategoryId, actCategoryName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actCategoryVO", actCategoryVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/actcategory/listOneActCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actcategory/updateActCategoryInput.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String myRegex = "^ACT_CATEGORY[0-9]+$";
				String actCategoryId = new String(req.getParameter("actCategoryId").trim());
				if (actCategoryId == null || actCategoryId.trim().length() == 0) {
					errorMsgs.add("活動類別編號請勿空白");
				} else if(!actCategoryId.trim().matches(myRegex)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動類別編號只能是ACT_CATEGORY開頭");
	            }
				
				String actCategoryName = req.getParameter("actCategoryName").trim();
				if (actCategoryName == null || actCategoryName.trim().length() == 0) {
					errorMsgs.add("活動類別名稱請勿空白");
				}	


				ActCategoryVO actCategoryVO = new ActCategoryVO();
				actCategoryVO.setActCategoryId(actCategoryId);
				actCategoryVO.setActCategoryName(actCategoryName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actCategoryVO", actCategoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actcategory/addActCategory.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActCategoryService actCategorySvc = new ActCategoryService();
				actCategoryVO = actCategorySvc.addActCategor(actCategoryId, actCategoryName);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/actcategory/listAllActCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actcategory/addActCategory.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}

