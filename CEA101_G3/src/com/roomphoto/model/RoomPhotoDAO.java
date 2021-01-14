package com.roomphoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomPhotoDAO implements RoomPhotoDAO_Interface{
	
	//連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, roomPhotoVO.getRoom_category_id());
			pstmt.setBytes(2, roomPhotoVO.getContent());
			
			int addPhoto = pstmt.executeUpdate();
			System.out.println("新增"+ addPhoto + "筆圖片資料");
	
		}catch (SQLException se) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Update_Stmt);
			
			pstmt.setString(1, roomPhotoVO.getRoom_category_id());
			pstmt.setBytes(2, roomPhotoVO.getContent());
			pstmt.setString(3, roomPhotoVO.getRoom_photo_id());
			
			int updatePhoto = pstmt.executeUpdate();
			System.out.println("更新"+  updatePhoto + "筆圖片資料");
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, room_photo_id);
			
			int deletePhoto = pstmt.executeUpdate();
			System.out.println("刪除"+  deletePhoto + "筆圖片資料");
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, room_photo_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoom_photo_id(rs.getString("ROOM_PHOTO_ID"));
				roomPhotoVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomPhotoVO.setContent(rs.getBytes("CONTENT"));
			}
	
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
			con = ds.getConnection();
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

}
