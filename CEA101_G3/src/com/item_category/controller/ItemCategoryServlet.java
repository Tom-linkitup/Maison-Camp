package com.item_category.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.item_category.model.ItemCategoryService;
import com.item_category.model.ItemCategoryVO;

public class ItemCategoryServlet extends HttpServlet {
 
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
				String str = req.getParameter("itemCategoryId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String itemCategoryId = null;
				try {
					itemCategoryId = str;
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ItemCategoryService itemCategorySvc = new ItemCategoryService();
				ItemCategoryVO itemCategoryVO = itemCategorySvc.getOneItemCategory(itemCategoryId);
				if (itemCategoryVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemCategoryVO", itemCategoryVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item_category/listOneItemCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/select_page.jsp");
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
				String itemCategoryId = new String(req.getParameter("itemCategoryId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ItemCategoryService itemCategorySvc = new ItemCategoryService();
				ItemCategoryVO itemCategoryVO = itemCategorySvc.getOneItemCategory(itemCategoryId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("itemCategoryVO", itemCategoryVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item_category/update_item_category_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/listAllItemCategory.jsp");
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
				String itemCategoryId = new String(req.getParameter("itemCategoryId").trim());

				String itemCategoryName = req.getParameter("itemCategoryName");
				String itemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (itemCategoryName == null || itemCategoryName.trim().length() == 0) {
					errorMsgs.add("商品類別: 請勿空白");
				} else if (!itemCategoryName.trim().matches(itemNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品類別: 只能是中、英文字母、數字和_ , 且長度必需在1到20之間");
				}

				ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
				itemCategoryVO.setItemCategoryId(itemCategoryId);
				itemCategoryVO.setItemCategoryName(itemCategoryName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemCategoryVO", itemCategoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/update_item_category_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ItemCategoryService itemCategorySvc = new ItemCategoryService();
				itemCategoryVO = itemCategorySvc.updateItemCategory(itemCategoryId, itemCategoryName);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemCategoryVO", itemCategoryVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/item_category/listAllItemCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/update_item_category_input.jsp");
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
				String itemCategoryId = req.getParameter("itemCategoryId").trim();
				String itemNameReg = "^[(I0-9)]{4}$";
				ItemCategoryService itemCts = new ItemCategoryService();
				if (itemCategoryId == null || itemCategoryId.trim().length() == 0) {
					errorMsgs.add("商品類別編號請勿空白");
				}else if (!itemCategoryId.trim().matches(itemNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是I加三位數(數字)");
				}
				
				String itemCategoryName = req.getParameter("itemCategoeyName");
				if (itemCategoryName == null || itemCategoryName.trim().length() == 0) {
					errorMsgs.add("商品類別: 請勿空白");
				}


				ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
				itemCategoryVO.setItemCategoryId(itemCategoryId);
				itemCategoryVO.setItemCategoryName(itemCategoryName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemCategoryVO", itemCategoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/addItemCategory.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ItemCategoryService itemCategorySvc = new ItemCategoryService();
				itemCategoryVO = itemCategorySvc.addItemCategory(itemCategoryId, itemCategoryName);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/item_category/listAllItemCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_category/addItemCategory.jsp");
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
				String itemCategoryId = new String(req.getParameter("itemCategoryId"));
				
				/***************************2.開始刪除資料***************************************/
				ItemCategoryService itemCategorySvc = new ItemCategoryService();
				itemCategorySvc.deleteItemCategory(itemCategoryId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/item_category/listAllItemCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item_category/listAllItemCategory.jsp");
				failureView.forward(req, res);
			}
		}
		if("changeCategory".equals(action)) {
			
			String itemCategoryId = req.getParameter("itemCategoryId");
			
			req.setAttribute("itemCategoryId", itemCategoryId);
			String url = "/front-end/item/shoppingMall.jsp";
			RequestDispatcher view = req.getRequestDispatcher(url);
			view.forward(req,res);
		}
	}

}
