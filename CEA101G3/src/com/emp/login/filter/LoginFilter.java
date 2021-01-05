package com.emp.login.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.emp_func.model.EmpFuncService;

public class LoginFilter implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1. 讓登出後就算按上一頁也無法看到內容
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		EmpService empSvc = new EmpService();
		EmpVO loginEmpVO = null;
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("username".equals(c.getName())) {	
					
						if(session.getAttribute("loginEmpVO") == null) { //解決如果使用者為登入 但關閉瀏覽器後再次開啟仍可維持登入(session消失)
							
							loginEmpVO = empSvc.getOneEmp(c.getValue());
							
							EmpFuncService empFuncSvc = new EmpFuncService();//保存使用者權限
							List funcList = empFuncSvc.findEmpFuncs(loginEmpVO.getEmp_id());
							session.setAttribute("funcList", funcList);
							
							session.setAttribute("loginEmpVO", loginEmpVO);
						}
						
					chain.doFilter(request, response);
					return;
				}
			}
		}

		if (session.getAttribute("loginEmpVO") == null){ //使用者從網址直接進去需登入畫面時。
			session.setAttribute("location", request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/back_end/emp/loginEmp.jsp");
		} else {
			chain.doFilter(request, response);
		}

	}
}
