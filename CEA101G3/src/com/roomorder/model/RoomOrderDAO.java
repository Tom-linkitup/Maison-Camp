package com.roomorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomOrderDAO implements RoomOrderDAO_Interface {
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
	
	private static final String Add_Stmt = "INSERT INTO ROOM_ORDER (ROOM_ORDER_ID, MEM_ID, PAYMENT, TIME, ROOM_TOTAL_AMOUNT, STATUS) VALUES ('RD' || room_order_id_seq.NEXTVAL,?,?,?,?,?)";
	private static final String Update_Stmt = "UPDATE ROOM_ORDER SET MEM_ID=?, PAYMENT=?, TIME=?, ROOM_TOTAL_AMOUNT=?, STATUS=? WHERE ROOM_ORDER_ID=?";
	private static final String Delete_Stmt = "DELETE FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String Get_One_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, PAYMENT, TIME, ROOM_TOTAL_AMOUNT, STATUS FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String Get_All_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, PAYMENT, TIME, ROOM_TOTAL_AMOUNT, STATUS FROM ROOM_ORDER ORDER BY ROOM_ORDER_ID";
	
	@Override
	public void addRoomOrder(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Add_Stmt);
			pstmt.setString(1, roomOrderVO.getMem_id());
			pstmt.setString(2, roomOrderVO.getPayment());
			pstmt.setDate(3, roomOrderVO.getTime());
			pstmt.setInt(4, roomOrderVO.getRoom_total_amount());
			pstmt.setInt(5, roomOrderVO.getStatus());
			
			int orderAdd = pstmt.executeUpdate();
			System.out.println("新增"+ orderAdd + "筆訂房訂單資料");

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
	public void updateRoomOrder(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Update_Stmt);
			pstmt.setString(1, roomOrderVO.getMem_id());
			pstmt.setString(2, roomOrderVO.getPayment());
			pstmt.setDate(3, roomOrderVO.getTime());
			pstmt.setInt(4, roomOrderVO.getRoom_total_amount());
			pstmt.setInt(5, roomOrderVO.getStatus());
			pstmt.setString(6, roomOrderVO.getRoom_order_id());
			
			int orderUpdate = pstmt.executeUpdate();
			System.out.println("更新"+ orderUpdate + "筆訂房訂單資料");

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
	public void deleteRoomOrder(String room_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, room_order_id);
			
			int orderDelete = pstmt.executeUpdate();
			System.out.println("刪除"+ orderDelete + "筆訂房訂單資料");

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
	public RoomOrderVO findByRoomOrderId(String room_order_id) {
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, room_order_id);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
				roomOrderVO.setPayment(rs.getString("PAYMENT"));
				roomOrderVO.setTime(rs.getDate("TIME"));
				roomOrderVO.setRoom_total_amount(rs.getInt("ROOM_TOTAL_AMOUNT"));
				roomOrderVO.setStatus(rs.getInt("STATUS"));			
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
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> getAllRoomOrder() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_Stmt);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
				roomOrderVO.setPayment(rs.getString("PAYMENT"));
				roomOrderVO.setTime(rs.getDate("TIME"));
				roomOrderVO.setRoom_total_amount(rs.getInt("ROOM_TOTAL_AMOUNT"));
				roomOrderVO.setStatus(rs.getInt("STATUS"));
				list.add(roomOrderVO);
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

}
