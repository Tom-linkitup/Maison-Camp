package com.item.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.model.ItemService;
import com.item.model.ItemVO;

public class ItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");

		PrintWriter out = res.getWriter();

//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("itemId");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入商品編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				String itemId = null;
//				try {
//					itemId = str;
//				} catch (Exception e) {
//					errorMsgs.add("商品編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				ItemService itemSvc = new ItemService();
//				ItemVO itemVO = itemSvc.getOneItem(itemId);
//				if (itemVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("itemVO", itemVO); // 資料庫取出的empVO物件,存入req
//				String url = "/back-end/item/listOneItem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}

//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				String itemId = new String(req.getParameter("itemId"));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				ItemService itemSvc = new ItemService();
//				ItemVO itemVO = itemSvc.getOneItem(itemId);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("itemVO", itemVO); // 資料庫取出的empVO物件,存入req
//				String url = "/back-end/item/update_item_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/listAllItem.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("update".equals(action)) { // 來自update_item_input.jsp的請求

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String itemId = new String(req.getParameter("itemId").trim());

				String itemName = req.getParameter("itemName");
//				String itemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (itemName == null || itemName.trim().length() == 0) {
//					errorMsgs.add("商品姓名: 請勿空白");
//				} else if (!itemName.trim().matches(itemNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("商品姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}

				String itemInfo = req.getParameter("itemInfo").trim();
//				if (itemInfo == null || itemInfo.trim().length() == 0) {
//					errorMsgs.add("商品資訊請勿空白");
//				}

				Integer itemPrice = null;
//				try {
				itemPrice = new Integer(req.getParameter("itemPrice").trim());
//				} catch (NumberFormatException e) {
//					itemPrice = 0;
//					errorMsgs.add("商品價格請填數字.");
//				}

				Integer itemStatus = null;
//				try {
				itemStatus = new Integer(req.getParameter("itemStatus").trim());
//				} catch (NumberFormatException e) {
//					itemStatus = 0;
//					errorMsgs.add("商品狀態請填數字.");
//				}

				String itemCategoryId = new String(req.getParameter("itemCategoryId").trim());

				ItemVO itemVO = new ItemVO();
				itemVO.setItemId(itemId);
				itemVO.setItemName(itemName);
				itemVO.setItemInfo(itemInfo);
				itemVO.setItemPrice(itemPrice);
				itemVO.setItemStatus(itemStatus);
				itemVO.setItemCategoryId(itemCategoryId);
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/update_item_input.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}

				/*************************** 2.開始修改資料 *****************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.updateItem(itemId, itemCategoryId, itemName, itemInfo, itemPrice, itemStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemVO", itemVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/item/ItemInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

			if ("insert".equals(action)) { // 來自addEmp.jsp的請求

				Map<String, String> errorMsgs = new LinkedHashMap<>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

					String itemName = req.getParameter("itemName");
					if (itemName == null || itemName.trim().length() == 0) {
						errorMsgs.put("itemName", "商品名稱: 請勿空白");
					}

					String itemInfo = req.getParameter("itemInfo").trim();
					if (itemInfo == null || itemInfo.trim().length() == 0) {
						errorMsgs.put("itemInfo", "商品資訊請勿空白");
					}

					Integer itemPrice = null;
					try {
						itemPrice = new Integer(req.getParameter("itemPrice").trim());
					} catch (NumberFormatException e) {
						itemPrice = 0;
						errorMsgs.put("itemPrice", "商品價格請填數字.");
					}

					Integer itemStatus = null;
					try {
						itemStatus = new Integer(req.getParameter("itemStatus").trim());
					} catch (NumberFormatException e) {
						itemStatus = 0;
						errorMsgs.put("itemStatus", "商品狀態請填0或1.");
					}

					String itemCategoryId = new String(req.getParameter("itemCategoryId").trim());
					ItemVO itemVO = new ItemVO();
					itemVO.setItemName(itemName);
					itemVO.setItemInfo(itemInfo);
					itemVO.setItemPrice(itemPrice);
					itemVO.setItemStatus(itemStatus);
					itemVO.setItemCategoryId(itemCategoryId);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/ItemInfo.jsp");
						failureView.forward(req, res);
						return;
					}

					/*************************** 2.開始新增資料 ***************************************/
					ItemService itemSvc = new ItemService();
					itemVO = itemSvc.addItem(itemCategoryId, itemName, itemInfo, itemPrice, itemStatus);

					/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					String url = "/back-end/item/ItemInfo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		

			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ***************************************/
					String itemId = new String(req.getParameter("itemId"));

					/*************************** 2.開始刪除資料 ***************************************/
					ItemService itemSvc = new ItemService();
					itemSvc.deleteItem(itemId);

					/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
					String url = "/back-end/item/ItemInfo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	}
}
