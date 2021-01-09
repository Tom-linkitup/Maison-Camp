package com.activityComment.Controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activityComment.model.*;
import com.activityOrder.model.ActivityOrderService;
import com.activityOrder.model.ActivityOrderVO;

public class ActCommentServlet extends HttpServlet {

	public ActCommentServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(req.getParameter("action"));
	
		// 後台新增評論
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String actCategoryId = req.getParameter("actCategoryId");
				String actCategoryIdRex = "^ACT_CATEGORY[0-9]+$";

				if (actCategoryId == null || !actCategoryId.matches(actCategoryIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}
				
				String actComment = req.getParameter("actComment");
				
				
				if(!errorMsgs.isEmpty()) {
					String url = "/back-end/actComment/addActComment.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
				
				ActivityCommentService acs = new ActivityCommentService();
				acs.addActivityComment(actCategoryId, actComment);
				String url = "/back-end/actComment/addActComment.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 使用者新增評論
		if("userinsert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String actCategoryId = req.getParameter("actCategoryId");
				String actCategoryIdRex = "^ACT_CATEGORY[0-9]+$";

				if (actCategoryId == null || !actCategoryId.matches(actCategoryIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}
				
				String actComment = req.getParameter("actComment");
				
				
				if(!errorMsgs.isEmpty()) {
					String url = "/front-end/member/Member.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
				
				ActivityCommentService acs = new ActivityCommentService();
				ActivityOrderService aos = new ActivityOrderService();
				// 先撈出該筆訂單
				String actOrderId = req.getParameter("actOrderId");
				ActivityOrderVO aoVO = aos.findByActOrderID(actOrderId);

				String actId = aoVO.getActId();
				String memId = aoVO.getMemId();

				String note = aoVO.getNote();

				Integer people = aoVO.getPeople();

				Integer status = 3;

				String payment = aoVO.getPayment();

				java.sql.Date createTime = aoVO.getCreateTime();

				Integer actPrice = aoVO.getActPrice();

				aoVO.setActId(actId);
				aoVO.setNote(note);
				aoVO.setPeople(people);
				aoVO.setStatus(status);
				aoVO.setPayment(payment);
				aoVO.setCreateTime(createTime);
				aoVO.setActPrice(actPrice);
				
				
				aos.updateByActOrderId(actOrderId, actId, memId, note, people, actPrice, payment, createTime, status);
				acs.addActivityComment(actCategoryId, actComment);
				String url = "/front-end/member/Member.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
		if("listByActCommentId".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String actCommentId = req.getParameter("actCommentId");
				String actCommentIdRex = "^[A]{1}[C]{1}[0-9]{5}$";
				
				
				if(actCommentId==null ||  !actCommentId.matches(actCommentIdRex)) {
					errorMsgs.add("活動評論編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					String url = "/back-end/actComment/actComment_select_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
				
				ActivityCommentService acs = new ActivityCommentService();
				ActivityCommentVO acVO = acs.findByActCommentId(actCommentId);
				
				
				req.setAttribute("activityCommentVO", acVO);
				String url = "/back-end/actComment/listOneActComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
		if("listByActCategoryId".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String actCategoryId = req.getParameter("actCategoryId");
				String actCategoryIdRex = "^ACT_CATEGORY[0-9]+$";

				if (actCategoryId == null || !actCategoryId.matches(actCategoryIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					String url = "/back-end/actComment/actComment_select_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
				
				ActivityCommentService acs = new ActivityCommentService();
				
				List<ActivityCommentVO> list = acs.getByActCategoryId(actCategoryId);
				
				req.setAttribute("list", list);
				String url = "/back-end/actComment/listCommentByActCategoryId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String actCommentId = req.getParameter("actCommentId");
				
				ActivityCommentService acs = new ActivityCommentService();
				
				acs.deleteActivityCommentVO(actCommentId);
				
				if(errorMsgs.isEmpty()) {
					String url = "/back-end/actComment/listAllActComment.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				
				
			}catch(Exception e) {
				errorMsgs.add("其他錯誤發生");
				String url = "/back-end/actComment/listAllActComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				e.printStackTrace();
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String actCommentId = req.getParameter("actCommentId");
				ActivityCommentService acs = new ActivityCommentService();
				ActivityCommentVO acVO = acs.findByActCommentId(actCommentId);
				
				req.setAttribute("activityCommentVO", acVO);
				String url = "/"+req.getParameter("from")+"/actComment/updateActComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("其他錯誤發生");
				String url = "/"+req.getParameter("from")+"/actComment/listAllActComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				e.printStackTrace();
			}
			
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String actCommentId = req.getParameter("actCommentId");
				ActivityCommentService acs = new ActivityCommentService();
				ActivityCommentVO acVO = acs.findByActCommentId(actCommentId);
				
				String actComment = req.getParameter("actComment");
				if(actComment.length()>500) {
					errorMsgs.add("評論長度不得超出500字元");
				}
				
				acVO.setActComment(actComment);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("activityCommentVO", acVO);
					String url = "/"+req.getParameter("from")+"/actComment/updateActComment.jsp";
					RequestDispatcher failView = req.getRequestDispatcher(url);
					failView.forward(req, res);
					
				}
				
				
				
				acs.updateActivityComment(actCommentId, actComment);
				
				req.setAttribute("activityCommentVO", acVO);
				String url = "/"+req.getParameter("from")+"/actComment/listOneActComment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("其他錯誤發生");
				String url = "/"+req.getParameter("from")+"/actComment/updateActComment.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
				e.printStackTrace();
			}
			
			
			
		
			
			
		}
		
	}

}
