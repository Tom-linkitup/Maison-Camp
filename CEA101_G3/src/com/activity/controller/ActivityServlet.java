package com.activity.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.activity.model.*;
import com.activityOrder.model.ActivityOrderService;
import com.activityOrder.model.ActivityOrderVO;

public class ActivityServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
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
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("actId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+req.getParameter("from")+"/activity/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String actId = null;
				try {
					actId = new String(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+req.getParameter("from")+"/activity/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO activityVO = actSvc.getOneActivity(actId);
				if (activityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/"+req.getParameter("from")+"/activity/selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("activityVO", activityVO); // 資料庫取出的empVO物件,存入req
				String url = "/"+req.getParameter("from")+"/activity/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+req.getParameter("from")+"/activity/selectPage.jsp");
				failureView.forward(req, res);
			}
		}

	
		if ("getOne_For_Update".equals(action))

		{ // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String actId = new String(req.getParameter("actId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ActivityService actSvc = new ActivityService();
				ActivityVO activityVO = actSvc.getOneActivity(actId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("activityVO", activityVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/activity/updateActInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/selectPage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String actId = new String(req.getParameter("actId").trim());

				String actCategoryId = req.getParameter("actCategoryId");

				String actInfo = req.getParameter("actInfo").trim();
				if (actInfo == null || actInfo.trim().length() == 0) {
					errorMsgs.add("活動內容請勿空白");
				}

				Integer actPrice = null;
				try {
					actPrice = new Integer(req.getParameter("actPrice").trim());
				} catch (NumberFormatException e) {
					actPrice = 0;
					errorMsgs.add("價格請填數字");
				}

				java.sql.Date actStartDate = null;
				try {
					actStartDate = java.sql.Date.valueOf(req.getParameter("actStartDate").trim());
				} catch (IllegalArgumentException e) {
					actStartDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actEndDate = null;
				try {
					actEndDate = java.sql.Date.valueOf(req.getParameter("actEndDate").trim());
				} catch (IllegalArgumentException e) {
					actEndDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actApplyOpen = null;
				try {
					actApplyOpen = java.sql.Date.valueOf(req.getParameter("actApplyOpen").trim());
				} catch (IllegalArgumentException e) {
					actApplyOpen = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actApplyClose = null;
				try {
					actApplyClose = java.sql.Date.valueOf(req.getParameter("actApplyClose").trim());
				} catch (IllegalArgumentException e) {
					actApplyClose = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer maxPeople = null;
				try {
					maxPeople = new Integer(req.getParameter("maxPeople").trim());
				} catch (NumberFormatException e) {
					maxPeople = 0;
					errorMsgs.add("價格請填數字");
				}

				Integer minPeople = null;
				try {
					minPeople = new Integer(req.getParameter("minPeople").trim());
				} catch (NumberFormatException e) {
					minPeople = 0;
					errorMsgs.add("價格請填數字");
				}

				Integer actAlreadyApply = null;
				try {
					actAlreadyApply = new Integer(req.getParameter("actAlreadyApply").trim());
				} catch (NumberFormatException e) {
					actAlreadyApply = 0;
					errorMsgs.add("價格請填數字");
				}

				String actName = req.getParameter("actName").trim();
				if (actName == null || actName.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}

				Integer actStatus = null;
				try {
					actStatus = new Integer(req.getParameter("actStatus").trim());
				} catch (NumberFormatException e) {
					actStatus = 0;
					errorMsgs.add("價格請填數字");
				}

				Double actDiscount = null;
				try {
					actDiscount = new Double(req.getParameter("actDiscount").trim());
				} catch (NumberFormatException e) {
					actDiscount = 0.0;
					errorMsgs.add("薪水請填數字.");
				}

				String actPromInfo = req.getParameter("actPromInfo").trim();
				if (actPromInfo == null || actPromInfo.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}

				java.sql.Date actPromStartDate = null;
				try {
					actPromStartDate = java.sql.Date.valueOf(req.getParameter("actPromStartDate").trim());
				} catch (IllegalArgumentException e) {
					actPromStartDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actPromCloseDate = null;
				try {
					actPromCloseDate = java.sql.Date.valueOf(req.getParameter("actPromCloseDate").trim());
				} catch (IllegalArgumentException e) {
					actPromCloseDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				ActivityVO activityVO = new ActivityVO();
				activityVO.setActId(actId);
				activityVO.setActCategoryId(actCategoryId);
				activityVO.setActInfo(actInfo);
				activityVO.setActPrice(actPrice);
				activityVO.setActStartDate(actStartDate);
				activityVO.setActEndDate(actEndDate);
				activityVO.setActApplyOpen(actApplyOpen);
				activityVO.setActApplyClose(actApplyClose);
				activityVO.setMaxProple(maxPeople);
				activityVO.setMinPeople(minPeople);
				activityVO.setActAlreadyApply(actAlreadyApply);
				activityVO.setActName(actName);
				activityVO.setActStatus(actStatus);
				activityVO.setActDiscount(actDiscount);
				activityVO.setActPromInfo(actPromInfo);
				activityVO.setActPromStartDate(actPromStartDate);
				activityVO.setActPromCloseDate(actPromCloseDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/updateActInput.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ActivityService actSvc = new ActivityService();
				activityVO = actSvc.updateActivit(actId, actCategoryId, actInfo, actPrice, actStartDate, actEndDate,
						actApplyOpen, actApplyClose, maxPeople, minPeople, actAlreadyApply, actName, actStatus,
						actDiscount, actPromInfo, actPromStartDate, actPromCloseDate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("activityVO", activityVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/activity/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/updateActInput.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String actCategoryId = req.getParameter("actCategoryId");

				String actInfo = req.getParameter("actInfo").trim();
				if (actInfo == null || actInfo.trim().length() == 0) {
					errorMsgs.add("活動內容請勿空白");
				}

				Integer actPrice = null;
				try {
					actPrice = new Integer(req.getParameter("actPrice").trim());
				} catch (NumberFormatException e) {
					actPrice = 0;
					errorMsgs.add("價格請填數字");
				}

				java.sql.Date actStartDate = null;
				try {
					actStartDate = java.sql.Date.valueOf(req.getParameter("actStartDate").trim());
				} catch (IllegalArgumentException e) {
					actStartDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actEndDate = null;
				try {
					actEndDate = java.sql.Date.valueOf(req.getParameter("actEndDate").trim());
				} catch (IllegalArgumentException e) {
					actEndDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actApplyOpen = null;
				try {
					actApplyOpen = java.sql.Date.valueOf(req.getParameter("actApplyOpen").trim());
				} catch (IllegalArgumentException e) {
					actApplyOpen = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actApplyClose = null;
				try {
					actApplyClose = java.sql.Date.valueOf(req.getParameter("actApplyClose").trim());
				} catch (IllegalArgumentException e) {
					actApplyClose = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer maxPeople = null;
				try {
					maxPeople = new Integer(req.getParameter("maxPeople").trim());
				} catch (NumberFormatException e) {
					maxPeople = 0;
					errorMsgs.add("價格請填數字");
				}

				Integer minPeople = null;
				try {
					minPeople = new Integer(req.getParameter("minPeople").trim());
				} catch (NumberFormatException e) {
					minPeople = 0;
					errorMsgs.add("價格請填數字");
				}

				Integer actAlreadyApply = null;
				try {
					actAlreadyApply = new Integer(req.getParameter("actAlreadyApply").trim());
				} catch (NumberFormatException e) {
					actAlreadyApply = 0;
					errorMsgs.add("價格請填數字");
				}

				String actName = req.getParameter("actName").trim();
				if (actName == null || actName.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}

				Integer actStatus = null;
				try {
					actStatus = new Integer(req.getParameter("actStatus").trim());
				} catch (NumberFormatException e) {
					actStatus = 0;
					errorMsgs.add("價格請填數字");
				}

				Double actDiscount = null;
				try {
					actDiscount = new Double(req.getParameter("actDiscount").trim());
				} catch (NumberFormatException e) {
					actDiscount = 0.0;
					errorMsgs.add("薪水請填數字.");
				}

				String actPromInfo = req.getParameter("actPromInfo").trim();
				if (actPromInfo == null || actPromInfo.trim().length() == 0) {
					errorMsgs.add("活動名稱請勿空白");
				}

				java.sql.Date actPromStartDate = null;
				try {
					actPromStartDate = java.sql.Date.valueOf(req.getParameter("actPromStartDate").trim());
				} catch (IllegalArgumentException e) {
					actPromStartDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date actPromCloseDate = null;
				try {
					actPromCloseDate = java.sql.Date.valueOf(req.getParameter("actPromCloseDate").trim());
				} catch (IllegalArgumentException e) {
					actPromCloseDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				ActivityVO activityVO = new ActivityVO();
				activityVO.setActCategoryId(actCategoryId);
				activityVO.setActInfo(actInfo);
				activityVO.setActPrice(actPrice);
				activityVO.setActStartDate(actStartDate);
				activityVO.setActEndDate(actEndDate);
				activityVO.setActApplyOpen(actApplyOpen);
				activityVO.setActApplyClose(actApplyClose);
				activityVO.setMaxProple(maxPeople);
				activityVO.setMinPeople(minPeople);
				activityVO.setActAlreadyApply(actAlreadyApply);
				activityVO.setActName(actName);
				activityVO.setActStatus(actStatus);
				activityVO.setActDiscount(actDiscount);
				activityVO.setActPromInfo(actPromInfo);
				activityVO.setActPromStartDate(actPromStartDate);
				activityVO.setActPromCloseDate(actPromCloseDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/addAct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ActivityService actSvc = new ActivityService();
				activityVO = actSvc.addActivit(actCategoryId, actInfo, actPrice, actStartDate, actEndDate, actApplyOpen,
						actApplyClose, maxPeople, minPeople, actAlreadyApply, actName, actStatus, actDiscount,
						actPromInfo, actPromStartDate, actPromCloseDate);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/activity/selectPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/addAct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				
				/***************************2.開始複合查詢***************************************/
				ActivityService actSvc = new ActivityService();
				List<ActivityVO> activityList = actSvc.getSelect(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("activityList", activityList); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/"+req.getParameter("from")+"/activity/listSelectAct.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/"+req.getParameter("from")+"/activity/listSelectAct.jsp");
				failureView.forward(req, res);
			}
		}		
		
		// 後端完成活動 訂單轉狀態
		if ("completeAct".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				/*************************** 1.接收請求參數 ****************************************/
				String actId = new String(req.getParameter("actId"));

				/***************************2.開始資料查詢***************************************/
				ActivityOrderService aos = new ActivityOrderService();
				List<ActivityOrderVO> aoList = aos.findByActId(actId);
				for (ActivityOrderVO ao : aoList) {
					if(ao.getStatus() == 0) {
					aos.updateByActOrderId(ao.getActOrderId(), ao.getActId(), ao.getMemId(), ao.getNote(), ao.getPeople(), ao.getActPrice(),ao.getPayment(), ao.getCreateTime(), 2);
					}
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("aoList", aoList); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/activity/selectPage.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activity/selectPage.jsp");
				failureView.forward(req, res);
			}
		}		

	}
}
