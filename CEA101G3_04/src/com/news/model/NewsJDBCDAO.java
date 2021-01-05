package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NewsJDBCDAO implements NewsDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	public static final String Add_Stmt = "INSERT INTO NEWS (NEWS_ID, EMP_ID, NEWS_CONTENT,CREATE_TIME) VALUES ('NEWS' || news_id_seq.NEXTVAL, ?,?,?)";

	@Override
	public void addNews(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, newsVO.getEmp_id());
			pstmt.setString(2, newsVO.getNews_content());
			pstmt.setDate(3, newsVO.getCreate_time());
			
			int newsAdd = pstmt.executeUpdate();
			System.out.println("新增"+ newsAdd + "筆最新消息");
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}

	@Override
	public void updateNews(NewsVO newsVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNews(String news_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NewsVO findByNewsId(String news_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NewsVO> getAllNews() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		NewsJDBCDAO dao = new NewsJDBCDAO();
		
		NewsVO news1 = new NewsVO();
		news1.setEmp_id("E10003");
		news1.setNews_content("仲夏之夜在Maison Camp豪華露營");
		news1.setCreate_time(java.sql.Date.valueOf("2020-11-26"));
		dao.addNews(news1);
	}
}
