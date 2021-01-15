package com.item_photo.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemPhotoJDBCDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ITEM_PHOTO (item_photo_id,item_id,content) VALUES ('IPH' || ITEM_PHOTO_SEQ.NEXTVAL, ?, ?)";
	
	public void insert(ItemPhotoVO ItemPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ItemPhotoVO.getItemId());
			pstmt.setBytes(2, ItemPhotoVO.getContent());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) {
		FileInputStream fis;
		byte[] buffer = null ;
		try {
			fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return buffer;
		}
	}
	
	
	public static void main(String[] args) {
		ItemPhotoJDBCDAO dao = new ItemPhotoJDBCDAO();

//		// 新增
		ItemPhotoVO ItemPhotoVO1 = new ItemPhotoVO();

		String path ="c:\\shoppingImg\\";
//		String path = "/Users/tomgu/desktop/shoppingImg/";
		
		for(int i = 1 ; i < 55 ; i++) {
			byte[] pic = getPictureByteArray(path + i + ".jpg");
			if(i<10)
				ItemPhotoVO1.setItemId("I1000"+i);
			else if(i>=10)
				ItemPhotoVO1.setItemId("I100"+i);
			ItemPhotoVO1.setContent(pic);
			dao.insert(ItemPhotoVO1);
		}
	}
}
