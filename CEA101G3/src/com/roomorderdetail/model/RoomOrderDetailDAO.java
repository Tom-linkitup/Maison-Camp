package com.roomorderdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.roomrsv.model.RoomRsvDAO;

public class RoomOrderDetailDAO implements RoomOrderDetailDAO_Interface{
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
		
		private static final String Add_Stmt = "INSERT INTO ROOM_ORDER_DETAIL (ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, QUANTITY, ROOM_ORDER_PRICE, ORDER_TIME, NOTE) VALUES (?,?,?,?,?,?,?)";
		private static final String Update_Stmt = "UPDATE ROOM_ORDER_DETAIL SET ROOM_CATEGORY_ID=?, ROOM_PROMOTION_ID=?, QUANTITY=?, ROOM_ORDER_PRICE=?, ORDER_TIME=?, NOTE=? WHERE ROOM_ORDER_ID=?";
		private static final String Delete_Stmt = "DELETE FROM ROOM_ORDER_DETAIL WHERE ROOM_ORDER_ID=?";
		private static final String Get_One_Stmt = "SELECT ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, QUANTITY, ROOM_ORDER_PRICE, ORDER_TIME, NOTE FROM ROOM_ORDER_DETAIL WHERE ROOM_ORDER_ID=?";
		private static final String Get_All_Stmt = "SELECT ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, QUANTITY, ROOM_ORDER_PRICE, ORDER_TIME, NOTE FROM ROOM_ORDER_DETAIL ORDER BY ROOM_ORDER_ID";
		
		@Override
		public void addRoomOrderDetail(RoomOrderDetailVO roomOrderDetailVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Add_Stmt);
				pstmt.setString(1, roomOrderDetailVO.getRoom_order_id());
				pstmt.setString(2, roomOrderDetailVO.getRoom_category_id());
				pstmt.setString(3, roomOrderDetailVO.getRoom_promotion_id());
				pstmt.setInt(4, roomOrderDetailVO.getQuantity());
				pstmt.setInt(5, roomOrderDetailVO.getRoom_order_price());
				pstmt.setDate(6, roomOrderDetailVO.getOrder_time());
				pstmt.setString(7, roomOrderDetailVO.getNote());
				
				int orderDetailAdd = pstmt.executeUpdate();
				System.out.println("新增"+ orderDetailAdd + "筆訂單明細資料");

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
		public void updateRoomOrderDetail(RoomOrderDetailVO roomOrderDetailVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Update_Stmt);
				
				pstmt.setString(1, roomOrderDetailVO.getRoom_category_id());
				pstmt.setString(2, roomOrderDetailVO.getRoom_promotion_id());
				pstmt.setInt(3, roomOrderDetailVO.getQuantity());
				pstmt.setInt(4, roomOrderDetailVO.getRoom_order_price());
				pstmt.setDate(5, roomOrderDetailVO.getOrder_time());
				pstmt.setString(6, roomOrderDetailVO.getNote());
				
				pstmt.setString(7, roomOrderDetailVO.getRoom_order_id());
				
				int orderDetailUpdate = pstmt.executeUpdate();
				System.out.println("更新"+ orderDetailUpdate + "筆訂單明細資料");

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
		public void deleteRoomOrderDetail(String room_order_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Delete_Stmt);
				
				pstmt.setString(1, room_order_id);
				
				int orderDelete = pstmt.executeUpdate();
				System.out.println("刪除"+ orderDelete + "筆訂單明細資料");

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
		public RoomOrderDetailVO findByRoomOrderId(String room_order_id) {
			RoomOrderDetailVO roomOrderDetailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Get_One_Stmt);
				
				pstmt.setString(1, room_order_id);
				rs = pstmt.executeQuery();	
				while(rs.next()) {
					roomOrderDetailVO = new RoomOrderDetailVO();
					roomOrderDetailVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
					roomOrderDetailVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
					roomOrderDetailVO.setRoom_promotion_id(rs.getString("ROOM_PROMOTION_ID"));
					roomOrderDetailVO.setQuantity(rs.getInt("QUANTITY"));
					roomOrderDetailVO.setRoom_order_price(rs.getInt("ROOM_ORDER_PRICE"));
					roomOrderDetailVO.setOrder_time(rs.getDate("ORDER_TIME"));
					roomOrderDetailVO.setNote(rs.getString("NOTE"));
					
				}

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
			return roomOrderDetailVO;
		}
		@Override
		public List<RoomOrderDetailVO> getAllRoomOrderDetail() {
			List<RoomOrderDetailVO> list = new ArrayList<>();
			RoomOrderDetailVO roomOrderDetailVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(Get_All_Stmt);			
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					roomOrderDetailVO = new RoomOrderDetailVO();
					roomOrderDetailVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
					roomOrderDetailVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
					roomOrderDetailVO.setRoom_promotion_id(rs.getString("ROOM_PROMOTION_ID"));
					roomOrderDetailVO.setQuantity(rs.getInt("QUANTITY"));
					roomOrderDetailVO.setRoom_order_price(rs.getInt("ROOM_ORDER_PRICE"));
					roomOrderDetailVO.setOrder_time(rs.getDate("ORDER_TIME"));
					roomOrderDetailVO.setNote(rs.getString("NOTE"));
					list.add(roomOrderDetailVO);
				}		
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
			return list;
		}
		@Override
		public void addRoomOrderAndDetail(RoomOrderDetailVO roomOrderDetailVO, Connection con) {
			PreparedStatement pstmt = null;

			try {
	     		pstmt = con.prepareStatement(Add_Stmt);
				pstmt.setString(1, roomOrderDetailVO.getRoom_order_id());
				pstmt.setString(2, roomOrderDetailVO.getRoom_category_id());
				pstmt.setString(3, roomOrderDetailVO.getRoom_promotion_id());
				pstmt.setInt(4, roomOrderDetailVO.getQuantity());
				pstmt.setInt(5, roomOrderDetailVO.getRoom_order_price());
				pstmt.setDate(6, roomOrderDetailVO.getOrder_time());
				pstmt.setString(7, roomOrderDetailVO.getNote());
				pstmt.executeUpdate();
				
				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3.設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-order-detail");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
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
			}
			
		}
}
