package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/MemberLoginHandler")
public class MemberLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		HttpSession session = req.getSession();
		
		List<String> errorMsgs = new LinkedList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		//錯誤驗證
		String mem_email = req.getParameter("mem_email");
		String mem_user_pwd = req.getParameter("mem_user_pwd");
		
		MemberService memSvc = new MemberService();
		MemberVO memVO = memSvc.isUser(mem_email, mem_user_pwd);
		
		if(memVO == null) {
			errorMsgs.add("帳號或密碼錯誤");
			if(!errorMsgs.isEmpty()) {			
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/front-index.jsp");
				failureView.forward(req, res);
			}
		}else {
			//讓cookie保存登入，依email
			Cookie cookie = new Cookie("username", memVO.getEmail());
			cookie.setMaxAge(24 * 60 * 60); //存活一天
			res.addCookie(cookie);
			
			session.setAttribute("memVO", memVO); //做登入過的標籤
			try {
				String location = (String) session.getAttribute("location"); //導回登入前畫面
				if(location != null) {
					session.removeAttribute("location"); //看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			}catch(Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/front-end/front-index.jsp");
		}
		
	}

}
