package com.roomorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomOrderJDBCDAO implements RoomOrderDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	private static final String Add_Stmt = "INSERT INTO ROOM_ORDER (ROOM_ORDER_ID, MEM_ID, PAYMENT, TIME, ROOM_TOTAL_AMOUNT, STATUS) VALUES ('RD' || room_order_id_seq.NEXTVAL,?,?,?,?,?)";
	private static final String Update_Stmt = "UPDATE ROOM_ORDER SET STATUS=? WHERE ROOM_ORDER_ID=?";
	private static final String Get_One_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, PAYMENT, TIME, ROOM_TOTAL_AMOUNT, STATUS FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String Get_All_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, PAYMENT, TIME, ROOM_TOTAL_AMOUNT, STATUS FROM ROOM_ORDER_ID ORDER BY ROOM_ORDER_ID";
	@Override
	public void addRoomOrder(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, roomOrderVO.getMem_id());
			pstmt.setString(2, roomOrderVO.getPayment());
			pstmt.setDate(3, roomOrderVO.getTime());
			pstmt.setInt(4, roomOrderVO.getRoom_total_amount());
			pstmt.setInt(5, roomOrderVO.getStatus());
			
			int orderAdd = pstmt.executeUpdate();
			System.out.println("新增"+ orderAdd + "筆訂房訂單資料");
			
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
	public void updateRoomOrder(RoomOrderVO roomOrderVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoomOrder(String room_order_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoomOrderVO findByRoomOrderId(String room_order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomOrderVO> getAllRoomOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		RoomOrderJDBCDAO dao = new RoomOrderJDBCDAO();
		RoomOrderVO roomOrder1 = new RoomOrderVO();
		
		roomOrder1.setMem_id("M10003");
		roomOrder1.setPayment("4877-5633-6844-5034");
		roomOrder1.setTime(java.sql.Date.valueOf("2020-12-20"));
		roomOrder1.setRoom_total_amount(13000);
		roomOrder1.setStatus(1);
		dao.addRoomOrder(roomOrder1);
	}
}
