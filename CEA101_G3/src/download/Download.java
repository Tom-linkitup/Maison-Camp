package download;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			String room_order_id = req.getParameter("room_order_id");
			String filename = room_order_id + ".pdf";
			String filepath = "/Users/tomgu/CEA101_WebApp/CEA101G3/CEA101_G3/WebContent/pdf/";
			req.setCharacterEncoding("utf-8");
			res.setContentType("APPLICATION/OCTET-STREAM");
			res.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
			
			FileInputStream fileInputStream = new FileInputStream(filepath + filename);
			
			OutputStream toClient = new BufferedOutputStream(res.getOutputStream());
			
			int i;
			while ((i=fileInputStream.read()) != -1) {
				 toClient.write(i);
			}
			fileInputStream.close();
			toClient.close();
			return;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
