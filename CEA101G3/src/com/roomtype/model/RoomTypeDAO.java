package com.roomtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomTypeDAO implements RoomTypeDAO_Interface{
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
		
		private static final String Add_Stmt = "INSERT INTO ROOM_CATEGORY (ROOM_CATEGORY_ID, ROOM_NAME, ROOM_TYPE, ROOM_PRICE, AREA, ROOM_GUEST, ROOM_QUANTITY, ROOM_CATEGORY_STATUS, ROOM_INFO)" 
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		private static final String Update_Stmt = "UPDATE ROOM_CATEGORY SET ROOM_NAME=?, ROOM_TYPE=?, ROOM_PRICE=?, AREA=?, ROOM_GUEST=?, ROOM_QUANTITY=?, ROOM_CATEGORY_STATUS=?, ROOM_INFO=? WHERE ROOM_CATEGORY_ID=?";
		private static final String Delete_Stmt = "DELETE FROM ROOM_CATEGORY WHERE ROOM_CATEGORY_ID=?";
		private static final String Get_All_Stmt = "SELECT ROOM_CATEGORY_ID, ROOM_NAME, ROOM_TYPE, ROOM_PRICE, AREA, ROOM_GUEST, ROOM_QUANTITY, ROOM_CATEGORY_STATUS, ROOM_INFO FROM ROOM_CATEGORY ORDER BY ROOM_CATEGORY_ID";
		private static final String Get_One_Stmt = "SELECT ROOM_CATEGORY_ID, ROOM_NAME, ROOM_TYPE, ROOM_PRICE, AREA, ROOM_GUEST, ROOM_QUANTITY, ROOM_CATEGORY_STATUS, ROOM_INFO FROM ROOM_CATEGORY WHERE ROOM_CATEGORY_ID=?";
		
		
		@Override
		public void addRoomType(RoomTypeVO roomTypeVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Add_Stmt);
				
				pstmt.setString(1, roomTypeVO.getRoom_category_id());
				pstmt.setString(2, roomTypeVO.getRoom_name());
				pstmt.setString(3, roomTypeVO.getRoom_type());
				pstmt.setInt(4, roomTypeVO.getRoom_price());
				pstmt.setInt(5, roomTypeVO.getArea());
				pstmt.setInt(6, roomTypeVO.getRoom_guest());
				pstmt.setInt(7, roomTypeVO.getRoom_quantity());
				pstmt.setInt(8, roomTypeVO.getRoom_category_status());
				pstmt.setString(9, roomTypeVO.getRoom_info());
				
				int roomAdd = pstmt.executeUpdate();
				System.out.println("新增"+ roomAdd + "筆房型資料");
				
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
		public void updateRoomType(RoomTypeVO roomTypeVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Update_Stmt);
				
				pstmt.setString(1, roomTypeVO.getRoom_name());
				pstmt.setString(2, roomTypeVO.getRoom_type());
				pstmt.setInt(3, roomTypeVO.getRoom_price());
				pstmt.setInt(4, roomTypeVO.getArea());
				pstmt.setInt(5, roomTypeVO.getRoom_guest());
				pstmt.setInt(6, roomTypeVO.getRoom_quantity());
				pstmt.setInt(7, roomTypeVO.getRoom_category_status());
				pstmt.setString(8, roomTypeVO.getRoom_info());
				
				pstmt.setString(9, roomTypeVO.getRoom_category_id());
				
				int roomUpdate = pstmt.executeUpdate();
				System.out.println("更新"+ roomUpdate + "筆房型資料");
				
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
		public void deleteRoomType(String room_category_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Delete_Stmt);
				
				pstmt.setString(1, room_category_id);
				
				int roomUpdate = pstmt.executeUpdate();
				System.out.println("刪除"+ roomUpdate + "筆房型資料");
				
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
		public RoomTypeVO findByRoomCategoryId(String room_category_id) {
			RoomTypeVO roomTypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Get_One_Stmt);
				
				pstmt.setString(1, room_category_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					roomTypeVO = new RoomTypeVO();
					roomTypeVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
					roomTypeVO.setRoom_name(rs.getString("ROOM_NAME"));
					roomTypeVO.setRoom_type(rs.getString("ROOM_TYPE"));
					roomTypeVO.setRoom_price(rs.getInt("ROOM_PRICE"));
					roomTypeVO.setArea(rs.getInt("AREA"));
					roomTypeVO.setRoom_guest(rs.getInt("ROOM_GUEST"));
					roomTypeVO.setRoom_quantity(rs.getInt("ROOM_QUANTITY"));
					roomTypeVO.setRoom_category_status(rs.getInt("ROOM_CATEGORY_STATUS"));
					roomTypeVO.setRoom_info(rs.getString("ROOM_INFO"));
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
			return roomTypeVO;
		}

		@Override
		public List<RoomTypeVO> getAllRoomType() {
			List<RoomTypeVO> roomTypeList = new ArrayList<>();
			RoomTypeVO roomTypeVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Get_All_Stmt);			
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					roomTypeVO = new RoomTypeVO();
					roomTypeVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
					roomTypeVO.setRoom_name(rs.getString("ROOM_NAME"));
					roomTypeVO.setRoom_type(rs.getString("ROOM_TYPE"));
					roomTypeVO.setRoom_price(rs.getInt("ROOM_PRICE"));
					roomTypeVO.setArea(rs.getInt("AREA"));
					roomTypeVO.setRoom_guest(rs.getInt("ROOM_GUEST"));
					roomTypeVO.setRoom_quantity(rs.getInt("ROOM_QUANTITY"));
					roomTypeVO.setRoom_category_status(rs.getInt("ROOM_CATEGORY_STATUS"));
					roomTypeVO.setRoom_info(rs.getString("ROOM_INFO"));
					roomTypeList.add(roomTypeVO); // Store the row in the list
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
			return roomTypeList;
		}
		
		
}
