package com.roomorderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomOrderDetailJDBCDAO implements RoomOrderDetailDAO_Interface {
	
	
	
	public static final String Add_Stmt = "INSERT INTO ROOM_ORDER_DETAIL (ROOM_ORDER_ID, ROOM_ID, ROOM_PROMOTION_ID, CHECK_IN_DATE, LIVE_IN_DATE, NOTE, QUANTITY, ROOM_ORDER_PRICE) VALUES(?,?,?,?,?,?,?,?)";

	@Override
	public void addRoomOrderDetail(RoomOrderDetailVO roomOrderDetail) {
		
		
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

	@Override
	public void addRoomOrderAndDetail(RoomOrderDetailVO roomOrderDetailVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

}
