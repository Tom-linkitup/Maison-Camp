package com.activityOrder.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actPhoto.model.ActPhotoService;
import com.activityOrder.model.*;

public class ActOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActOrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
// ------------前端新增活動訂單-----------
		if ("insertByMember".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接受請求參數----檢查資料格式
				String actId = req.getParameter("actId");

				String memId = req.getParameter("memId");

				String note = req.getParameter("note");

				Integer people = null;

				try {
					people = Integer.parseInt(req.getParameter("people"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("人數請輸入數字");
				}
				if (people == 0) {
					errorMsgs.add("參加人數不得為零");
				}

				Integer status = null;
				status = Integer.parseInt(req.getParameter("status"));

				String payment = req.getParameter("payment");

				java.sql.Date createTime = java.sql.Date.valueOf(req.getParameter("createTime"));

				Integer actPrice = null;
				actPrice = Integer.valueOf(req.getParameter("actPrice"));

				// 資料格式驗證完成 包裝資料
				ActivityOrderVO aoVO = new ActivityOrderVO();

				aoVO.setActId(actId);
				aoVO.setMemId(memId);
				aoVO.setNote(note);
				aoVO.setPeople(people);
				aoVO.setStatus(status);
				aoVO.setPayment(payment);
				aoVO.setActPrice(actPrice);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityOrderVO", aoVO);
					String url = "/front-end/actOrder/addActOrder.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				ActivityOrderService aos = new ActivityOrderService();
				aos.addActOrder(actId, memId, note, people, actPrice, payment, createTime, status);
				String url = "/front-end/activity/selectPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("其他錯誤發生");
				e.printStackTrace();
			}
		}

// ------------後端新增活動訂單-----------
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接受請求參數----檢查資料格式
				String actId = req.getParameter("actId");
				String actIdRex = "^A[0-9]+$";

				if (actId == null || !actId.matches(actIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}

				String memId = req.getParameter("memId");
				String memIdRex = "^M[0-9]+$";

				if (memId == null || !memId.matches(memIdRex)) {
					errorMsgs.add("會員編號格式不正確");
				}

				String note = req.getParameter("note");

				Integer people = null;

				try {
					people = Integer.parseInt(req.getParameter("people"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("人數請輸入數字");
				}

				if (people == 0) {
					errorMsgs.add("參加人數不得為零");
				}

				Integer status = null;
				try {
					status = Integer.parseInt(req.getParameter("status"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("狀態碼請輸入數字");
				}

				String payment = req.getParameter("payment");

				java.sql.Date createTime = java.sql.Date.valueOf(req.getParameter("createTime"));

				Integer actPrice = null;
				try {
					actPrice = Integer.valueOf(req.getParameter("actPrice"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("活動價格請輸入數字");
				}

				// 資料格式驗證完成 包裝資料
				ActivityOrderVO aoVO = new ActivityOrderVO();

				aoVO.setActId(actId);
				aoVO.setMemId(memId);
				aoVO.setNote(note);
				aoVO.setPeople(people);
				aoVO.setStatus(status);
				aoVO.setPayment(payment);
				aoVO.setActPrice(actPrice);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityOrderVO", aoVO);
					String url = "/back-end/actOrder/addActOrder.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				ActivityOrderService aos = new ActivityOrderService();
				aos.addActOrder(actId, memId, note, people, actPrice, payment, createTime, status);
				String url = "/back-end/actOrder/listAllActOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("其他錯誤發生");
				e.printStackTrace();
			}
		}

// ------------------------查詢依訂單編號查詢活動訂單
		if ("listByActOrderId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接受請求參數----檢查資料格式
				String actId = req.getParameter("actId");
				String actIdRex = "^AD[0-9]+$";

				if (actId == null || !actId.matches(actIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}
				ActivityOrderService aos = new ActivityOrderService();
				ActivityOrderVO aoVO = aos.findByActOrderID(actId);

				req.setAttribute("activityOrderVO", aoVO);

				if (errorMsgs.isEmpty()) {
					String url = "/" + req.getParameter("from") + "/actOrder/listOneActOrder.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else {
					req.setAttribute("activityOrderVO", aoVO);
					String url = "/" + req.getParameter("from") + "/actOrder/actOrder_select_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

// ---------------------------------------依活動編號查詢訂單
		if ("listByActId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String actId = req.getParameter("actId");
				String actIdRex = "^A[0-9]+$";
				if (actId == null || !actId.matches(actIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}

				List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
				ActivityOrderService aos = new ActivityOrderService();
				list = aos.findByActId(actId);

				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/actOrder/actOrder_select_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("list", list);
				String url = "/back-end/actOrder/listOrderByActId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//--------------------來自各查詢頁面的更新活動訂單請求
		if ("getOne_For_Update".equals(action)) {
			try {

				String actOrderId = req.getParameter("actOrderId");
				ActivityOrderService aos = new ActivityOrderService();
				ActivityOrderVO aoVO = aos.findByActOrderID(actOrderId);

				req.setAttribute("activityOrderVO", aoVO);
				String url = "/back-end/actOrder/updateActOrder.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//---------------------更新活動訂單資料
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String actOrderId = req.getParameter("actOrderId");

				// 先撈出該筆訂單
				ActivityOrderService aos = new ActivityOrderService();
				ActivityOrderVO aoVO = aos.findByActOrderID(actOrderId);

				String actId = req.getParameter("actId");
				String actIdRex = "^A[0-9]+$";
				if (actId == null || !actId.matches(actIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}

				String memId = req.getParameter("memId");

				String note = req.getParameter("note");

				Integer people = null;

				try {
					people = Integer.parseInt(req.getParameter("people"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("人數請輸入數字");
				}

				if (people == 0) {
					errorMsgs.add("參加人數不得為零");
				}

				Integer status = null;
				try {
					status = Integer.parseInt(req.getParameter("status"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("狀態碼請輸入數字");
				}

				String payment = req.getParameter("payment");

				java.sql.Date createTime = java.sql.Date.valueOf(req.getParameter("createTime"));

				Integer actPrice = null;
				try {
					actPrice = Integer.valueOf(req.getParameter("actPrice"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("活動價格請輸入數字");
				}

				aoVO.setActId(actId);
				aoVO.setNote(note);
				aoVO.setPeople(people);
				aoVO.setStatus(status);
				aoVO.setPayment(payment);
				aoVO.setCreateTime(createTime);
				aoVO.setActPrice(actPrice);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityOrderVO", aoVO);
					String url = "/back-end/actOrder/updateActOrder.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}

				aos.updateByActOrderId(actOrderId, actId, memId, note, people, actPrice, payment, createTime, status);

				req.setAttribute("activityOrderVO", aoVO);
				String url = "/back-end/actOrder/listOneActOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

// ---------------------前端取消活動訂單
		if ("usercancel".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String actOrderId = req.getParameter("actOrderId");

				// 先撈出該筆訂單
				ActivityOrderService aos = new ActivityOrderService();
				ActivityOrderVO aoVO = aos.findByActOrderID(actOrderId);

				String actId = aoVO.getActId();
				String memId = aoVO.getMemId();

				String note = aoVO.getNote();

				Integer people = aoVO.getPeople();

				Integer status = 1;

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

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityOrderVO", aoVO);
					String url = "/front-end/member/Member.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}

				aos.updateByActOrderId(actOrderId, actId, memId, note, people, actPrice, payment, createTime, status);

				req.setAttribute("activityOrderVO", aoVO);
				String url = "/front-end/member/Member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

//---------------------刪除活動訂單資料
		if ("delete".equals(action)) {

			try {

				String actOrderId = req.getParameter("actOrderId");

				ActivityOrderService aos = new ActivityOrderService();
				aos.deletebyActOrderId(actOrderId);

				String url = "/back-end/actOrder/listAllActOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				System.out.println("刪除失敗");
				e.printStackTrace();
			}

		}

	}

}
