package com.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import mail.MailService;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {		
			try {
				String user_id = req.getParameter("user_id").trim();
				String user_pwd = req.getParameter("user_pwd").trim();
				String name = req.getParameter("name").trim();
				String phone = req.getParameter("phone").trim();
				String nation = req.getParameter("nation").trim();
				String email = req.getParameter("email").trim();
				String sexual = req.getParameter("gender").trim();
				java.sql.Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
				String personal_id = req.getParameter("personal_id").trim();
				String payment = req.getParameter("payment");
				int status = 0;
				String note = "Welcome to Maison Camp";
				
				HttpSession session  = req.getSession();
				
				MemberService memSvc = new MemberService();
				memSvc.addMEM(user_id, user_pwd, name, phone, nation, email, sexual, note, birthday, personal_id, status, payment);
				
				String new_mem_id = memSvc.getOneMEMByEmail(email).getMem_id();
				MailService mail = new MailService();
				String authCode = mail.getRandom();
				String subject = "Maison Camp 會員驗證碼";
				String message = "感謝您註冊本網站會員，請輸入以下驗證碼完成註冊:" + authCode;
				
				try {
					mail.sendMail(email, subject, message);
					session.setAttribute("new_mem_id", new_mem_id);
		            session.setAttribute("authCode", authCode);
		            res.sendRedirect(req.getContextPath() + "/front-end/signup/Verify.jsp");
				}catch(Exception e) {
					e.printStackTrace();
				}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("resendAuth".equals(action)) {
			try {
				String mem_id = req.getParameter("mem_id");
				String email = req.getParameter("email");
				
				HttpSession session  = req.getSession();
				
				MailService mail = new MailService();
				String authCode = mail.getRandom();
				String subject = "Maison Camp 重發會員驗證碼";
				String message = "感謝您註冊本網站會員，請輸入以下驗證碼完成註冊:" + authCode;
				
				try {
					mail.sendMail(email, subject, message);
					session.setAttribute("resendAuth_mem_id", mem_id);
		            session.setAttribute("authCode", authCode);
		            res.sendRedirect(req.getContextPath() + "/front-end/signup/ResendVerify.jsp");
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("update".equals(action)) {
			try {
				String mem_id = req.getParameter("mem_id").trim();
				String user_id = req.getParameter("user_id").trim();
				String user_pwd = req.getParameter("user_pwd").trim();
				String name = req.getParameter("name").trim();
				String phone = req.getParameter("phone").trim();
				String nation = req.getParameter("nation").trim();
				String email = req.getParameter("email").trim();
				String sexual = req.getParameter("sexual").trim();
				java.sql.Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
				String personal_id = req.getParameter("personal_id").trim();
				String payment = req.getParameter("payment");
				Integer status = new Integer(req.getParameter("status").trim());
				String note = "Welcome to Maison Camp";
				
				MemberVO memVO = new MemberVO();
				
				memVO.setMem_id(mem_id);
				memVO.setUser_id(user_id);
				memVO.setUser_pwd(user_pwd);
				memVO.setName(name);
				memVO.setPhone(phone);
				memVO.setNation(nation);
				memVO.setEmail(email);
				memVO.setSexual(sexual);
				memVO.setBirthday(birthday);
				memVO.setPersonal_id(personal_id);
				memVO.setPayment(payment);
				memVO.setStatus(status);
				memVO.setNote(note);
				
				MemberService memSvc = new MemberService();
				memVO = memSvc.updateMEM(user_id, user_pwd, name, phone, nation, email, sexual, note, birthday, personal_id, status, payment, mem_id);
				
				String url = "/back-end/member/MemberInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("frontUpdate".equals(action)) {
			try {
				HttpSession session = req.getSession();
				
				String mem_id = req.getParameter("mem_id").trim();
				String user_id = req.getParameter("user_id").trim();
				String name = req.getParameter("name").trim();
				String phone = req.getParameter("phone").trim();
				String nation = req.getParameter("nation").trim();
				String sexual = req.getParameter("gender").trim();
				java.sql.Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
				String personal_id = req.getParameter("personal_id").trim();
				
				MemberVO memVO = new MemberVO();
				
				memVO.setMem_id(mem_id);
				memVO.setUser_id(user_id);
				memVO.setName(name);
				memVO.setPhone(phone);
				memVO.setNation(nation);
				memVO.setSexual(sexual);
				memVO.setBirthday(birthday);
				memVO.setPersonal_id(personal_id);
				
				MemberService memSvc = new MemberService();
				memSvc.updateFrontMEM(user_id, name, phone, birthday, personal_id, nation, sexual, mem_id);
				
				MemberVO memVO2 = memSvc.getOneMEM(mem_id);
				session.setAttribute("memVO", memVO2);
				
				String url = "/front-end/member/Member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);	
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("updatePwd".equals(action)) {
				
				Map<String, String> errorPwdMsgs = new LinkedHashMap<>();
				req.setAttribute("errorPwdMsgs", errorPwdMsgs);
				
			try {
				HttpSession session = req.getSession();
				
				String mem_id = req.getParameter("mem_id").trim();
				String user_old_pwd = req.getParameter("user_old_pwd").trim();
				String user_new_pwd = req.getParameter("user_new_pwd").trim();
				String user_re_enter_new_pwd = req.getParameter("user_re_enter_new_pwd").trim();
				
				MemberService memCurrentPwd = new MemberService();
				String user_current_pwd = memCurrentPwd.getOneMEM(mem_id).getUser_pwd();
				
				if(!user_old_pwd.equals(user_current_pwd)) {
					errorPwdMsgs.put("errorOldPwd", "*舊密碼不符，請重新輸入");	
				}
				
				if(!user_new_pwd.equals(user_re_enter_new_pwd)) {
					errorPwdMsgs.put("errorReEnterNewPwd", "*密碼確認輸入不正確");
				}
				
				if(!errorPwdMsgs.isEmpty()) {
					String url = "/front-end/member/Member.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}				
				
				MemberVO memVO = new MemberVO();
				
				memVO.setMem_id(mem_id);
				memVO.setUser_pwd(user_new_pwd);
				
				MemberService memSvc = new MemberService();
				memVO = memSvc.updateMemPwd(user_new_pwd, mem_id);
				
				MemberVO memVO2 = memSvc.getOneMEM(mem_id);
				session.setAttribute("memVO", memVO2);
				
				req.setAttribute("successUpdatePwd", "successUpdatePwd");
				String url = "/front-end/member/Member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if("updateCredit".equals(action)) {
			
			try {
				HttpSession session = req.getSession();
				
				String mem_id = req.getParameter("mem_id").trim();
				String payment = req.getParameter("payment").trim();
							
				MemberVO memVO = new MemberVO();
				
				memVO.setMem_id(mem_id);
				memVO.setPayment(payment);
				
				MemberService memSvc = new MemberService();
				memVO = memSvc.updateMemCredit(payment, mem_id);
				
				MemberVO memVO2 = memSvc.getOneMEM(mem_id);
				session.setAttribute("memVO", memVO2);
				
				req.setAttribute("successUpdateCredit", "successUpdateCredit");
				String url = "/front-end/member/Member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if("logout".equals(action)) {
			HttpSession session = req.getSession();

			Cookie[] cookies = req.getCookies();// 使用者登出後 把Cookie清掉
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("username".equals(c.getName())) {					
						c.setMaxAge(0);
						c.setPath(req.getContextPath());
						res.addCookie(c);
					}
				}
			}
			session.removeAttribute("memVO");
			RequestDispatcher tologin = req.getRequestDispatcher("/front-end/front-index.jsp");
			tologin.forward(req, res);
		}
	}

}
