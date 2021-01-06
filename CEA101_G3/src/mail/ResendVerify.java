package mail;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/ResendVerify.do")
public class ResendVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		HttpSession session = req.getSession();
		String resendAuth_mem_id = (String)session.getAttribute("resendAuth_mem_id");
		String authCode = (String)session.getAttribute("authCode");
		String inputAuthCode = req.getParameter("authcode").trim();
		
		if(authCode.equals(inputAuthCode)) {
			MemberService memSvc = new MemberService();
			memSvc.updateMemStatus(new Integer(1), resendAuth_mem_id);
			MemberVO memVO = memSvc.getOneMEM(resendAuth_mem_id);
			session.setAttribute("memVO", memVO);		
			String url = "/front-end/signup/ResendVerify.jsp";
			req.setAttribute("authSuccess", "yes");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
	}

}
