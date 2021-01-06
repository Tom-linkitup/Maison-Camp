package com.roomorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.roomorderdetail.model.RoomOrderDetailVO;

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

	@Override
	public Set<RoomOrderDetailVO> getDetailsByRoomOrderId(String room_order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithDetails(RoomOrderVO deptVO, List<RoomOrderDetailVO> list) {
		// TODO Auto-generated method stub
		
	}
	
}