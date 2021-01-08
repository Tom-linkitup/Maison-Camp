package com.item_photo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.item_photo.model.ItemPhotoService;
import com.item_photo.model.ItemPhotoVO;

@MultipartConfig
public class itemPhotoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("itemPhotoId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String itemPhotoId = null;
				try {
					itemPhotoId = str;
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ItemPhotoService itemPhotoSvc = new ItemPhotoService();
				ItemPhotoVO itemPhotoVO = itemPhotoSvc.getOneItemPhoto(itemPhotoId);
				if (itemPhotoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemPhotoVO", itemPhotoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item_photo/listOneItemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/select_page.jsp");
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
				String itemPhotoId = new String(req.getParameter("itemPhotoId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ItemPhotoService itemPhotoSvc = new ItemPhotoService();
				ItemPhotoVO itemPhotoVO = itemPhotoSvc.getOneItemPhoto(itemPhotoId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("itemPhotoVO", itemPhotoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item_photo/update_item_photo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/listAllItemPhoto.jsp");
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
				String itemPhotoId = new String(req.getParameter("itemPhotoId").trim());
				
				
				InputStream is = req.getPart("content").getInputStream();
				byte[] content = null;
				if (is.available() == 0 ) {
					ItemPhotoService itemPhotoSvc = new ItemPhotoService();
					ItemPhotoVO itemPhotoVO = itemPhotoSvc.getOneItemPhoto(itemPhotoId);
					content = itemPhotoVO.getContent();
				}else {
					content = new byte[is.available()];
					is.read(content);
					is.close();
				}
					
				String itemId = new String(req.getParameter("itemId").trim());

				ItemPhotoVO itemPhotoVO = new ItemPhotoVO();
				itemPhotoVO.setItemPhotoId(itemPhotoId);
				itemPhotoVO.setItemId(itemId);
				itemPhotoVO.setContent(content);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemPhotoVO", itemPhotoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/update_item_photo_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ItemPhotoService itemPhotoSvc = new ItemPhotoService();
				itemPhotoVO = itemPhotoSvc.updateItemPhoto(itemPhotoId, itemId, content);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemPhotoVO", itemPhotoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/item_photo/listOneItemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/update_item_photo_input.jsp");
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
				InputStream is = req.getPart("content").getInputStream();
				if (is == null ) {
					errorMsgs.add("商品照片請上傳");
				}
				
				String itemId = new String(req.getParameter("itemId").trim());
				System.out.println(itemId);
				
				ItemPhotoVO itemPhotoVO = new ItemPhotoVO();
				itemPhotoVO.setItemId(itemId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemPhotoVO", itemPhotoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/addItemPhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ItemPhotoService itemPhotoSvc = new ItemPhotoService();
				byte[] bytes = new byte[0];
				bytes = new byte[is.available()];
				is.read(bytes);
				
				itemPhotoVO = itemPhotoSvc.addItemPhoto(itemId,bytes);
				is.close();
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/item_photo/listAllItemPhoto.jsp";
				System.out.println("OK");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item_photo/addItemPhoto.jsp");
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
				String itemPhotoId = new String(req.getParameter("itemPhotoId"));
				
				/***************************2.開始刪除資料***************************************/
				ItemPhotoService itemPhotoSvc = new ItemPhotoService();
				itemPhotoSvc.deleteItemPhoto(itemPhotoId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/item_photo/listAllItemPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item_photo/listAllItemPhoto.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
