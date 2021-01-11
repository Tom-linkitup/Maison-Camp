package com.repair.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.repair.model.*;
import com.repair.model.RepairService;
import com.repair.model.RepairVO;

public class RepairServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("有收到請求");
	
		
if ("getOne_For_Display".equals(action)) {  // 來自select_page.jsp的請求
			
	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("repair_id");
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String repair_id = null;
				try {
					repair_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RepairService repairSvc = new RepairService();
				RepairVO repairVO = repairSvc.getOneRepair(repair_id);
				if (repairVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("repairVO", repairVO); // ��Ʈw���X��repairVO����,�s�Jreq
				String url = "/back-end/room_repair/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRepair.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
if ("getOne_For_Update".equals(action)) { // 來自listAllRepair.jsp的請求

	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數****************************************/
				String repair_id = req.getParameter("repair_id");
				
				/***************************2.開始查詢資料****************************************/
				RepairService repairSvc = new RepairService();
				RepairVO repairVO = repairSvc.getOneRepair(repair_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("repairVO", repairVO);         // 資料庫取出的room_promotionVO物件,存入req
				String url = "/back-end/room_repair/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_repair_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		
//		
//if ("update".equals(action)) { 
//			try {
//				Map<String, String> errorMsgs = new LinkedHashMap<>();
//				req.setAttribute("errorMsgs", errorMsgs);
//		
//				String repair_id = req.getParameter("repair_id").trim();
//					
//				String room_id = req.getParameter("room_id").trim();
//				if (room_id == null || room_id.trim().length() == 0) {
//					errorMsgs.put("room_id", "*房間編號不得為空(RM10001,RM10002)");
//				}	
//			
//				String emp_id = req.getParameter("emp_id").trim();
//				emp_id = emp_id.substring(0,6);
//				
//				String repair_info = req.getParameter("repair_info").trim();
//				if (repair_info == null || repair_info.trim().length() == 0) {
//					errorMsgs.put("repair_info", "*維修資訊不得為空");
//				}	
//				
//				Integer status = new Integer (req.getParameter("status").trim());
//				
//					
//					
//					Collection<Part> parts = req.getParts();
//					for(Part part : parts) {
//						if(part.getContentType() != null) {
//							InputStream in = part.getInputStream();
//							byte[] b = new byte[in.available()];
//							in.read(b);
//							in.close();	
//				
//				System.out.println("有來到2");
//				RepairVO repairVO = new RepairVO();
//				repairVO.setRepair_id(repair_id);
//				repairVO.setRoom_id(room_id);
//				repairVO.setEmp_id(emp_id);
//				repairVO.setRepair_info(repair_info);
//				repairVO.setStatus(status);
//				repairVO.setRepair_photo(b);
//				System.out.println("有來到3");
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("repairVO", repairVO); // �t����J�榡���~��repairVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				System.out.println("有來到4");
//				
//				/***************************2.開始更新資料***************************************/
//				RepairService repairSvc = new RepairService();
//				repairVO = repairSvc.updateRepair(repair_id, room_id,emp_id, repair_info,status,repair_photo);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				req.setAttribute("repairVO", repairVO); // ��Ʈwupdate���\��,���T����repairVO����,�s�Jreq
//				String url = "/back-end/room_repair/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); //
//				successView.forward(req, res);
//				System.out.println("有來到5");
//				/***************************其他可能的錯誤處理*************************************/
//				} catch (Exception e) {
//				e.printStackTrace();
//				}
//}
		

if ("insert".equals(action)) {   // 來自select_page.jsp的請求 
System.out.println("請求有進來insert");

		Map<String, String> errorMsgs = new LinkedHashMap<>();
		req.setAttribute("errorMsgs", errorMsgs);

			try {
				String room_id = req.getParameter("room_id").trim();
				System.out.println("room_id");
				RepairService repairSvcTest = new RepairService();
				List<RepairVO> repairList = repairSvcTest.getAll();
				for(RepairVO rp : repairList) {
					if(room_id==null || room_id.trim().length() == 0){
						errorMsgs.put("room_id", "*房間編號不得為空");
					}
				}
				
				String emp_id = req.getParameter("emp_id").trim();
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.put("emp_id", "*員工編號不得為空");
				}
				
				String repair_info = req.getParameter("repair_info").trim();
				if (repair_info == null || repair_info.trim().length() == 0) {
					errorMsgs.put("repair_info", "*維修資訊不得為空");
				}
				
				Integer status = new Integer (req.getParameter("status").trim());
				if (status == null ) {
					errorMsgs.put("status", "*維修狀態不得為空");
				}
				
				byte[] repair_photo=null;
				Part part = req.getPart("repair_photo");
				InputStream is = part.getInputStream();
				repair_photo = new byte[is.available()];
				is.read(repair_photo);
				is.close();
				
				
				RepairVO repairVO = new RepairVO();
				repairVO.setRoom_id(room_id);
				repairVO.setEmp_id(emp_id);
				repairVO.setRepair_info(repair_info);
				repairVO.setStatus(status);
				repairVO.setRepair_photo(repair_photo);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repairVO", repairVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RepairService repairSvc = new RepairService();
				repairVO = repairSvc.addRepair(room_id, emp_id,repair_info,status,repair_photo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				String url = "/back-end/room_repair/select_page.jsp";
				req.setAttribute("insertSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url); // // 新增成功後轉交
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				e.printStackTrace();
			}
	}


		
		
if ("delete".equals(action)) { // 來自listAllRepair.jsp

			try {
				/***************************1.接收請求參數***************************************/
				String repair_id = new String(req.getParameter("repair_id"));
				
				/***************************2.開始刪除資料***************************************/
				RepairService repairSvc = new RepairService();
				repairSvc.deleteRepair(repair_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/						
				String url = "/back-end/room_repair/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
			}
				
			
				catch (Exception e) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/room_repair/select_page.jsp");
					failureView.forward(req, res);
				}
			}
				
			
		
}
}

	

