package com.actPhoto.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.actPhoto.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ActPhotoServlet extends HttpServlet {
	
    public ActPhotoServlet() {
        super();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
	//顯示圖片
		
		if("showPhoto".equals(action)) {
			
			ActPhotoDAO dao = new ActPhotoDAO();
			
			byte[] content = dao.getContent(req.getParameter("photo"));
			
			res.setContentType("image/jpeg");
			ServletOutputStream out = res.getOutputStream();
				
			out.write(content);
			out.close();
		}
		
		
		
		
	// 新增圖片	
		if("insert".equals(action)) {
			ActPhotoVO apVO = new ActPhotoVO();
			ActPhotoService aps = new ActPhotoService();
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String actId = req.getParameter("ACT_ID");
				String actIdRex = "^A[0-9]+$";
				if(actId!=null && actId.matches(actIdRex)) {
					apVO.setActId(actId);
				}else{
					errorMsgs.add("活動編號格式不正確");
				}
				
				
				Part part = req.getPart("photo");
				if(part!=null) {
					InputStream is = part.getInputStream();
					if(is.available()==0) {
						errorMsgs.add("圖片來源或格式不正確");
					}
					
					byte[] photo = new byte[is.available()];
					is.read(photo);
					is.close();
					apVO.setContent(photo);
				}
				
			}catch(Exception e) {
				errorMsgs.add("其他錯誤發生");
				e.printStackTrace();
			}
			
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actPhoto/addActPhoto.jsp");
				failureView.forward(req, res);
				
			}else {
				aps.addActPhoto(apVO.getActId(), apVO.getContent());
				
				
				String url = "/back-end/actPhoto/listAllActPhoto.jsp";
				req.setAttribute("ActPhotoVO", apVO);
				RequestDispatcher sucess = req.getRequestDispatcher(url);
				sucess.forward(req, res);
			
			}
			
		}
		
		
//		查詢依活動圖片編號查詢  
		
		if("listByActPhotoId".equals(action)) {
			String ActPhotoId = req.getParameter("ACT_PHOTO_ID");
			String ActPhotoIdReg = "^APH[0-9]+$";
			ActPhotoVO apVO = null;
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			if(ActPhotoId==null ||!ActPhotoId.matches(ActPhotoIdReg)) {
				errorMsgs.add("圖片編號格式不正確");
				
			}else {
				try {			
					ActPhotoService aps = new ActPhotoService();
					apVO = aps.findByActPhotoId(ActPhotoId);
					System.out.println(apVO.getActId());
					
				}catch(Exception e) {
					errorMsgs.add("發生其他錯誤");
					e.printStackTrace();
				}
			}		
			
			if(errorMsgs.isEmpty()) {
				String url = "/back-end/actPhoto/listOneActPhoto.jsp";
				req.setAttribute("ActPhotoVO", apVO);
				RequestDispatcher sucess = req.getRequestDispatcher(url);
				sucess.forward(req, res);
			}else {
				String url = "/back-end/actPhoto/actPhoto_select_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
					
		}else {
			
		}
	
		// 從listAllActPhoto.jsp listOneActPhoto.jsp  選擇要update的項目 並轉交update_actPhoto.jsp
		if("getOne_For_Update".equals(action)) {
			
			String apID= (String) req.getParameter("ACT_PHOTO_ID");
			ActPhotoService aps = new ActPhotoService();
			ActPhotoVO apVO = aps.findByActPhotoId(apID);
			
			try {
			

			req.setAttribute("ActPhotoVO", apVO);
			
			System.out.println(apVO);
			
			String url = "/back-end/actPhoto/update_actPhoto.jsp";
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);	
			
			
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		// 來自update_actPhoto 來更新資料
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String apID = req.getParameter("ACT_PHOTO_ID");
			ActPhotoService aps = new ActPhotoService();
			ActPhotoVO apVO = aps.findByActPhotoId(apID);
			
			try {

				String actID = req.getParameter("ACT_ID");
				String actIdRex = "^[A]{1}[0-9]{5}$";
			
				if(actID == null || !actID.matches(actIdRex)) {
					errorMsgs.add("活動編號格式不正確");
				}
				
				byte[] photo = null;
 				Part part = req.getPart("photo");
 				
				if(part!=null) {
				InputStream is = part.getInputStream();
				photo = new byte[is.available()];
				is.read(photo);
				is.close();
				}
				
				if(photo.length!=0) {
					apVO.setContent(photo);
				}
				
				apVO.setActId(actID);
				
		
			}catch(Exception e) {
				errorMsgs.add("其他錯誤發生");
				e.printStackTrace();
			}
			
			
			if(errorMsgs.isEmpty()) {
				
				aps.updateActPhoto(apVO.getActPhotoId(), apVO.getActId(), apVO.getContent());
			
				req.setAttribute("ActPhotoVO", apVO);
				String url = "/back-end/actPhoto/listOneActPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else {
				req.setAttribute("ActPhotoVO", apVO);
				String url = "/back-end/actPhoto/update_actPhoto.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				
			}
				
		}
		
		
		// 刪除圖片
		if("delete".equals(action)) {
			
			try {
				
				String apID = req.getParameter("ACT_PHOTO_ID");
				
				ActPhotoService aps = new ActPhotoService();
				aps.deletActPhoto(apID);
				
				String url = "/back-end/actPhoto/listAllActPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}catch(Exception e) {
				
				System.out.println("刪除失敗");
				e.printStackTrace();
			}
			
			
		}
		
		// 以活動ID來查詢圖片
		if("listByActId".equals(action)) {
			String actID = req.getParameter("ACT_ID");
			String actIDreg = "^A[0-9]+$";
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			if(actID!=null && actID.matches(actIDreg)) {
				
				ActPhotoService aps = new ActPhotoService();
				List<ActPhotoVO> list = aps.getByActId(actID);
				
				req.getSession().setAttribute("ActPhotoVOList", list);
				String url = "/back-end/actPhoto/listByActId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else{
				errorMsgs.add("活動編號格式不正確");
				String url = "/back-end/actPhoto/actPhoto_select_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		}
		
		
	}

}
