package com.roomorderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomOrderDetailJDBCDAO implements RoomOrderDetailDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	public static final String Add_Stmt = "INSERT INTO ROOM_ORDER_DETAIL (ROOM_ORDER_ID, ROOM_ID, ROOM_PROMOTION_ID, CHECK_IN_DATE, LIVE_IN_DATE, NOTE, QUANTITY, ROOM_ORDER_PRICE) VALUES(?,?,?,?,?,?,?,?)";

	@Override
	public void addRoomOrderDetail(RoomOrderDetailVO roomOrderDetail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, roomOrderDetail.getRoom_order_id());
			pstmt.setString(2, roomOrderDetail.getRoom_id());
			pstmt.setString(3, roomOrderDetail.getRoom_promotion_id());
			pstmt.setDate(4, roomOrderDetail.getCheck_in_date());
			pstmt.setInt(5, roomOrderDetail.getLive_in_date());
			pstmt.setString(6, roomOrderDetail.getNote());
			pstmt.setInt(7, roomOrderDetail.getQuantity());
			pstmt.setInt(8, roomOrderDetail.getRoom_order_price());
			
			
			int orderDetailAdd = pstmt.executeUpdate();
			System.out.println("新增"+ orderDetailAdd + "筆訂房訂單明細資料");
			
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
	public void updateRoomOrderDetail(RoomOrderDetailVO roomOrderDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoomOrderDetail(String room_order_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoomOrderDetailVO findByRoomOrderId(String room_order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomOrderDetailVO> getAllRoomOrderDetail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		RoomOrderDetailJDBCDAO dao = new RoomOrderDetailJDBCDAO();
		RoomOrderDetailVO roomOrderDetail1 = new RoomOrderDetailVO();
		
		roomOrderDetail1.setRoom_order_id("RD6");
		roomOrderDetail1.setRoom_id("RM4");
		roomOrderDetail1.setRoom_promotion_id("PR2");
		roomOrderDetail1.setCheck_in_date(java.sql.Date.valueOf("2020-12-22"));
		roomOrderDetail1.setLive_in_date(3);
		roomOrderDetail1.setNote("要吸煙房");
		roomOrderDetail1.setQuantity(1);
		roomOrderDetail1.setRoom_order_price(14000);
		dao.addRoomOrderDetail(roomOrderDetail1);
	}

}
