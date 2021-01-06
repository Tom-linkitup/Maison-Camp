package com.item_comment.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item_comment.model.ItemCommentService;
import com.item_comment.model.ItemCommentVO;

public class ItemCommentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("itemCommentId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String itemCommentId = null;
				try {
					itemCommentId = str;
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ItemCommentService itemCommentSvc = new ItemCommentService();
				ItemCommentVO itemCommentVO = itemCommentSvc.getOneItemComment(itemCommentId);
				if (itemCommentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemCommentVO", itemCommentVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item_comment/listOneItemComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String itemCommentId = new String(req.getParameter("itemCommentId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ItemCommentService itemCommentSvc = new ItemCommentService();
				ItemCommentVO itemCommentVO = itemCommentSvc.getOneItemComment(itemCommentId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("itemCommentVO", itemCommentVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item_comment/update_item_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/listAllItemComment.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_item_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String itemCommentId = new String(req.getParameter("itemCommentId").trim());

				String itemId = new String(req.getParameter("itemId").trim());

				String shopComment = req.getParameter("shopComment").trim();
				if (shopComment == null || shopComment.trim().length() == 0) {
					errorMsgs.add("評論內容請勿空白");
				}

				java.sql.Timestamp time = new java.sql.Timestamp(Long.parseLong(req.getParameter("time").trim()));

				ItemCommentVO itemCommentVO = new ItemCommentVO();
				itemCommentVO.setItemCommentId(itemCommentId);
				itemCommentVO.setItemId(itemId);
				itemCommentVO.setShopComment(shopComment);
				itemCommentVO.setTime(time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemCommentVO", itemCommentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/update_item_comment_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ItemCommentService itemCommentSvc = new ItemCommentService();
				itemCommentVO = itemCommentSvc.updateItemComment(itemCommentId, itemId, shopComment, time);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemCommentVO", itemCommentVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/item_comment/listOneItemComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/update_item_comment_input.jsp");
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
				
				String itemId = new String(req.getParameter("itemId").trim());

				String shopComment = req.getParameter("shopComment").trim();
				if (shopComment == null || shopComment.trim().length() == 0) {
					errorMsgs.add("評論內容請勿空白");
				}

				java.sql.Timestamp time = new java.sql.Timestamp(Long.parseLong(req.getParameter("time").trim()));
				
				ItemCommentVO itemCommentVO = new ItemCommentVO();
				itemCommentVO.setItemId(itemId);
				itemCommentVO.setShopComment(shopComment);
				itemCommentVO.setTime(time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemCommentVO", itemCommentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/addItemComment.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ItemCommentService itemCommentSvc = new ItemCommentService();
				itemCommentVO = itemCommentSvc.addItemComment(itemId, shopComment, time);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/item_comment/listAllItemComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_comment/addItemComment.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String itemCommentId = new String(req.getParameter("itemCommentId"));
				
				/***************************2.開始刪除資料***************************************/
				ItemCommentService itemCommentSvc = new ItemCommentService();
				itemCommentSvc.deleteItemComment(itemCommentId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/item_comment/listAllItemComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item_comment/listAllItemComment.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
