package com.roomorderdetail.model;

import java.util.List;

public interface RoomOrderDetailDAO_Interface {
	public void addRoomOrderDetail(RoomOrderDetailVO roomOrderDetailVO);
	public void updateRoomOrderDetail(RoomOrderDetailVO roomOrderDetailVO);
	public void deleteRoomOrderDetail(String room_order_id);
	public RoomOrderDetailVO findByRoomOrderId(String room_order_id);
	public List<RoomOrderDetailVO> getAllRoomOrderDetail();
	//同時新增訂單與明細
    public void addRoomOrderAndDetail(RoomOrderDetailVO roomOrderDetailVO , java.sql.Connection con);
}
