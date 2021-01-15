package com.emp.func.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpVO;
import com.emp_func.model.EmpFuncService;



public class EmpViewFilter implements Filter {
	private FilterConfig config;

	@Override
	public void init(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		List funcList = (ArrayList) session.getAttribute("funcList");
		
		if (funcList.contains("0001")) {
			chain.doFilter(request, response);
		} else {
			String alert= "<script> swal('權限不足 無法觀看','', 'warning'); </script>";
			request.setAttribute("alert",alert);
			
			String path ="/back-end/emp/protected/backer.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/back_end/emp/protected/backer.jsp");
		}
	}
}
