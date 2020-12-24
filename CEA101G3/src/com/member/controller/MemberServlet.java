package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

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
			
			MemberService memSvc = new MemberService();
			memSvc.addMEM(user_id, user_pwd, name, phone, nation, email, sexual, note, birthday, personal_id, status, payment);
			
			String url = "/front-end/signup/SignUp.jsp";
			req.setAttribute("insertSuccess", "yes");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("update".equals(action)) {
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
			int status = 0;
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
			
			session.invalidate();
			RequestDispatcher tologin = req.getRequestDispatcher("/front-end/front-index.jsp");
			tologin.forward(req, res);
		}
		
		
	}

}
