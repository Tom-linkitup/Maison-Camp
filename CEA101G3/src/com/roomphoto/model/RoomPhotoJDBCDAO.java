package com.roomphoto.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomPhotoJDBCDAO implements RoomPhotoDAO_Interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	public static final String Add_Stmt = "INSERT INTO ROOM_PHOTO (ROOM_PHOTO_ID, ROOM_CATEGORY_ID, CONTENT) VALUES ('RPH' || room_photo_id_seq.NEXTVAL,?,?)";
	public static final String Update_Stmt = "UPDATE ROOM_PHOTO SET ROOM_CATEGORY_ID=?, CONTENT=? WHERE ROOM_PHOTO_ID=?";
	public static final String Delete_Stmt = "DELETE FROM ROOM_PHOTO WHERE ROOM_PHOTO_ID=?";
	public static final String Get_One_Stmt = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, CONTENT FROM ROOM_PHOTO WHERE ROOM_PHOTO_ID=?";
	public static final String Get_All_Stmt = "SELECT ROOM_PHOTO_ID, ROOM_CATEGORY_ID, CONTENT FROM ROOM_PHOTO WHERE ROOM_CATEGORY_ID=?";
	
	@Override
	public void addRoomPhoto(RoomPhotoVO roomPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, roomPhotoVO.getRoom_category_id());
			pstmt.setBytes(2, roomPhotoVO.getContent());
			
			int addPhoto = pstmt.executeUpdate();
			System.out.println("新增"+ addPhoto + "筆圖片資料");
	
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
	public void updateRoomPhoto(RoomPhotoVO roomPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Stmt);
			
			pstmt.setString(1, roomPhotoVO.getRoom_category_id());
			pstmt.setBytes(2, roomPhotoVO.getContent());
			pstmt.setString(3, roomPhotoVO.getRoom_photo_id());
			
			int updatePhoto = pstmt.executeUpdate();
			System.out.println("更新"+  updatePhoto + "筆圖片資料");
	
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
	public void deleteRoomPhoto(String room_photo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, room_photo_id);
			
			int deletePhoto = pstmt.executeUpdate();
			System.out.println("刪除"+  deletePhoto + "筆圖片資料");
	
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
	public RoomPhotoVO findByRoomPhotoId(String room_photo_id) {
		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, room_photo_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoom_photo_id(rs.getString("ROOM_PHOTO_ID"));
				roomPhotoVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomPhotoVO.setContent(rs.getBytes("CONTENT"));
			}
	
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
		return roomPhotoVO;
	}

	@Override
	public List<RoomPhotoVO> getAllRoomPhoto(String room_category_id) {
		List<RoomPhotoVO> roomPhotoList = new ArrayList<>();
		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt);
			
			pstmt.setString(1, room_category_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoom_photo_id(rs.getString("ROOM_PHOTO_ID"));
				roomPhotoVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomPhotoVO.setContent(rs.getBytes("CONTENT"));
				roomPhotoList.add(roomPhotoVO);
			}
	
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
		return roomPhotoList;
	}
	
//	public static void main(String[] args) throws IOException {
//		RoomPhotoJDBCDAO dao = new RoomPhotoJDBCDAO();
		//新增房型圖片
//		RoomPhotoVO photo1 = new RoomPhotoVO();
//		photo1.setRoom_category_id("DOUBLE");
//		photo1.setContent(getByteArray("/Users/tomgu/CEA101_WebApp/eclipse_WTF_Workspace/CEA101G3/WebContent/images/house2.png"));	
//		dao.addRoomPhoto(photo1);
		
//		RoomPhotoVO photo2 = new RoomPhotoVO();
//		photo2.setRoom_photo_id("RTC002");
//		photo2.setRoom_category_id("RT02");
//		photo2.setContent(getByteArray("/Users/tomgu/CEA101_WebApp/eclipse_WTF_Workspace/CEA101G3/WebContent/images/content3.jpg"));
//		dao.addRoomPhoto(photo2);
		
//		RoomPhotoVO photo3 = new RoomPhotoVO();
//		photo3.setRoom_photo_id("RTC003");
//		photo3.setRoom_category_id("RT03");
//		photo3.setContent(getByteArray("/Users/tomgu/CEA101_WebApp/eclipse_WTF_Workspace/CEA101G3/WebContent/images/content1.jpg"));
//		dao.addRoomPhoto(photo3);
		
		//刪除房型圖片
//		dao.deleteRoomPhoto("RTC003");
		
		//取房型圖片
//		RoomPhotoVO roomPhotoFind = dao.findByRoomPhotoId("RTC002");
//		System.out.print(roomPhotoFind.getRoom_photo_id() + ",");
//		System.out.print(roomPhotoFind.getRoom_category_id() + ",");
//		readPicture(roomPhotoFind.getContent());
		
		//取所有房型圖片
//		List<RoomPhotoVO> roomPhotoList = dao.getAllRoomPhoto("DOUBLE");
//		for(int i = 1; i <= roomPhotoList.size(); i++) {
//			System.out.print(roomPhotoList.get(i-1).getRoom_photo_id() + ",");
//			System.out.print(roomPhotoList.get(i-1).getRoom_category_id() + ",");
//			readAllPicture(roomPhotoList.get(i-1).getContent(), i);
//		}
		
//	}
	
	

	private static byte[] getByteArray(String imageUrl) throws IOException {
		FileInputStream fis = new FileInputStream(imageUrl);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buffer = new byte[bis.available()];
		bis.read(buffer);
		bis.close();
		return buffer;
	}
	
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("/Users/tomgu/CEA101_WebApp/eclipse_WTF_Workspace/CEA101G3/WebContent/images/content5.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		PrintStream ps = new PrintStream(bos);
		ps.write(bytes);
		ps.flush();
		ps.close();
		bos.close();
		fos.close();
	}
	
	public static void readAllPicture(byte[] bytes, int i) throws IOException {
		FileOutputStream fos = new FileOutputStream("/Users/tomgu/CEA101_WebApp/eclipse_WTF_Workspace/CEA101G3/WebContent/images/con" + i + ".jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		PrintStream ps = new PrintStream(bos);
		ps.write(bytes);
		ps.flush();
		ps.close();
		bos.close();
		fos.close();
	}
	
	

}
