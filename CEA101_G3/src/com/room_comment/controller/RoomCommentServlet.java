package com.room_comment.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.room_comment.model.*;
import com.room_promotion.model.Room_promotionService;
import com.room_promotion.model.Room_promotionVO;
import com.roomorder.model.RoomOrderService;
import com.roomorder.model.RoomOrderVO;

public class RoomCommentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("請求有進來");
		
//if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("room_comment_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�ж����ת��s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_comment/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				String room_comment_id = null;
//				try {
//					room_comment_id = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("�ж����׽s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_comment/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				Room_commentService room_commentSvc = new Room_commentService();
//				Room_commentVO room_commentVO = room_commentSvc.getOneRoom_comment(room_comment_id);
//				if (room_commentVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_comment/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("room_commentVO", room_commentVO); // ��Ʈw���X��room_commentVO����,�s�Jreq
//				String url = "/back-end/room_comment/listOneRoomComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneRoom_comment.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/room_comment/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllRoom_comment.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("�o�̬OgetOneForUpdate");
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				String room_comment_id = new String(req.getParameter("room_comment_id"));
//System.out.println(room_comment_id);
//				/***************************2.�}�l�d�߸��****************************************/
//				Room_commentService room_commentSvc = new Room_commentService();
//				Room_commentVO room_commentVO = room_commentSvc.getOneRoom_comment(room_comment_id);
//System.out.println(room_comment_id);								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("room_commentVO", room_commentVO);         // ��Ʈw���X��room_commentVO����,�s�Jreq
//				String url = "/back-end/room_comment/updateRoomCommentInput.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_room_comment_input.jsp
//				successView.forward(req, res);
//System.out.println("3");
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/room_comment/listAllRoomComment.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//if ("update".equals(action)) { // �Ӧ�update_room_comment_input.jsp���ШD
//System.out.println("�o�̬Oupdate");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String room_comment_id = new String(req.getParameter("room_comment_id").trim());
//System.out.println(room_comment_id);					
//				String room_category_id = req.getParameter("room_category_id");
//				String room_category_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (room_category_id == null || room_category_id.trim().length() == 0) {
//					errorMsgs.add("���u�m�W: �ФŪť�");
//				} else if(!room_category_id.trim().matches(room_category_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//System.out.println("5");				
//				String room_comment_content = req.getParameter("room_comment_content").trim();
//				if (room_comment_content == null || room_comment_content.trim().length() == 0) {
//					errorMsgs.add("¾��ФŪť�");
//				}	
//				
//				Timestamp time = null;
//				try {
//					time = java.sql.Timestamp.valueOf(req.getParameter("time").trim());
//				} catch (IllegalArgumentException e) {
//					time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//
//				Room_commentVO room_commentVO = new Room_commentVO();
//				room_commentVO.setRoom_comment_id(room_comment_id);
//				room_commentVO.setRoom_category_id(room_category_id);
//				room_commentVO.setRoom_comment_content(room_comment_content);
//				room_commentVO.setTime(time);
//System.out.println(room_comment_id);
//System.out.println(room_category_id);
//System.out.println(room_comment_content);
//System.out.println(time);
//				
//				
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("room_commentVO", room_commentVO); // �t����J�榡���~��room_commentVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_comment/updateRoomCommentInput.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//			
//				/***************************2.�}�l�ק���*****************************************/
//				Room_commentService room_commentSvc = new Room_commentService();
//				room_commentVO = room_commentSvc.updateRoom_comment(room_comment_id, room_category_id, room_comment_content, time);
//System.out.println("6");					
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("room_commentVO", room_commentVO); // ��Ʈwupdate���\��,���T����room_commentVO����,�s�Jreq
//				String url = "/back-end/room_comment/listOneRoomComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneRoom_comment.jsp
//				successView.forward(req, res);
//System.out.println("7");
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/room_comment/updateRoomCommentInput.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // �Ӧ�addRoom_comment.jsp���ШD  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				String room_category_id = req.getParameter("room_category_id");
//				String room_category_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (room_category_id == null || room_category_id.trim().length() == 0) {
//					errorMsgs.add("�Ы����:�ФŪť�");
//				} else if(!room_category_id.trim().matches(room_category_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�Ы����:�u��OTWINS,DOUBLE,QUADRUPLE");
//	            }
//				
//				String room_comment_content = req.getParameter("room_comment_content").trim();
//				if (room_comment_content == null || room_comment_content.trim().length() == 0) {
//					errorMsgs.add("���פ��e");
//				}
//				
//				java.sql.Timestamp time = null;
//				try {
//					time = java.sql.Timestamp.valueOf(req.getParameter("time").trim());
//				} catch (IllegalArgumentException e) {
//					time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//				
//				
//
//				Room_commentVO room_commentVO = new Room_commentVO();
//				room_commentVO.setRoom_category_id(room_category_id);
//				room_commentVO.setRoom_comment_content(room_comment_content);
//				room_commentVO.setTime(time);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("room_commentVO", room_commentVO); // �t����J�榡���~��room_commentVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/room_comment/addRoomComment.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				Room_commentService room_commentSvc = new Room_commentService();
//				room_commentVO = room_commentSvc.addRoom_comment(room_category_id, room_comment_content, time);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/back-end/room_comment/listAllRoomComment.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllRoom_comment.jsp
//				successView.forward(req, res);				
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/room_comment/addRoomComment.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("delete".equals(action)) { // 來自listAllRepair.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數***************************************/
				String room_comment_id = new String (req.getParameter("room_comment_id"));
				
				/***************************2.開始刪除資料***************************************/
				Room_commentService room_commentSvc = new Room_commentService();
				room_commentSvc.deleteRoom_comment(room_comment_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/room_comment/select_page.jsp";
				req.setAttribute("deleteSuccess", "yes");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				}
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
						req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String room_comment_id = req.getParameter("room_comment_id");
				
				/***************************2.開始查詢資料****************************************/
				Room_commentService room_commentSvc = new Room_commentService();
				Room_commentVO room_commentVO = room_commentSvc.getOneRoom_comment(room_comment_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("room_commentVO", room_commentVO);         // 資料庫取出的room_commentVO物件,存入req
				String url = "/back-end/room_comment/select_page.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("update".equals(action)) { 
			
			
			
			try {
						String room_comment_id = req.getParameter("room_comment_id").trim();
						String room_category_id = req.getParameter("room_category_id").trim();
						String room_comment_content = req.getParameter("room_comment_content").trim();
						String comment_reply = req.getParameter("comment_reply");
						
						
						String get = req.getParameter("time").trim();
						Timestamp time = java.sql.Timestamp.valueOf(get);
						
						
						Room_commentVO room_commentVO = new Room_commentVO();
						room_commentVO.setRoom_comment_id(room_comment_id);
						room_commentVO.setRoom_category_id(room_category_id);
						room_commentVO.setRoom_comment_content(room_comment_content);
						room_commentVO.setTime(time);
						room_commentVO.setComment_reply(comment_reply);

						Room_commentService room_commentSvc = new Room_commentService();
						room_commentVO = room_commentSvc.updateRoom_comment(room_comment_id,room_category_id,room_comment_content,time,comment_reply);
						
						String url = "/back-end/room_comment/select_page.jsp";
						req.setAttribute("updateSuccess", "yes");
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRoom_promotion.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} 
			
			
						catch (Exception e) {
						e.printStackTrace();
					}
				}
		//用房型找評論
		if("getRoomCommentByRtc".equals(action)) {
			System.out.println("有來到這");
			req.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			String room_category_id = req.getParameter("room_category_id");
			System.out.println("1");
			System.out.println(room_category_id);
			Room_commentService room_commentSvc = new Room_commentService();
			List<Room_commentVO> getRoom_commentVoByRtc = room_commentSvc.getRmByRTC(room_category_id);
			String url = "/back-end/room_comment/select_page.jsp";
			req.setAttribute("getRoom_commentVoByRtc", getRoom_commentVoByRtc);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			System.out.println("1");
		}
		
		
		// 使用者新增評論
				if("userinsert".equals(action)) {
					
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						
						String room_category_id = req.getParameter("room_category_id");
						String room_comment_content = req.getParameter("room_comment_content");
						
												
						
						String url = "/front-end/member/Member.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
		
		
	
	}

	
	
}
