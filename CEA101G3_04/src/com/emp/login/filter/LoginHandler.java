package com.emp.login.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.emp_func.model.EmpFuncService;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();

		List<String> errorMsgs = new LinkedList<String>();
		session.setAttribute("errorMsgs", errorMsgs);
		// 錯誤驗證
		String emp_user_id = req.getParameter("emp_user_id");
		String emp_user_pwd = req.getParameter("emp_user_pwd");

		EmpService empSvc = new EmpService();
		EmpVO loginEmpVO = empSvc.isUser(emp_user_id, emp_user_pwd);
		
		if (loginEmpVO == null) {
			errorMsgs.add("帳號或密碼錯誤!");
			res.sendRedirect(req.getContextPath() + "/back-end/emp/loginEmp.jsp");
			return;
		} else {
			//讓cookie保存登入 依帳號。
			Cookie cookie = new Cookie("username", loginEmpVO.getEmp_id());
			cookie.setMaxAge(60 * 60);
			res.addCookie(cookie);
			
			EmpFuncService empFuncSvc = new EmpFuncService(); //保存使用者權限
			List funcList = empFuncSvc.findEmpFuncs(loginEmpVO.getEmp_id());
			session.setAttribute("funcList", funcList);
			
			session.setAttribute("loginEmpVO", loginEmpVO); // *工作1: 才在session內做已經登入過的標識
			try {
				String location = (String) session.getAttribute("location");//登入成功後重新導回登入前畫面
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location); //?
					return;
				}
			} catch (Exception ignored) {
			}
			
			res.sendRedirect(req.getContextPath() + "/back-end/emp/protected/backer.jsp");
		}

	}
}
