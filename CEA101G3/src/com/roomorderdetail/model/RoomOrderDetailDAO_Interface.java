package com.roomorderdetail.model;

import java.util.List;

public interface RoomOrderDetailDAO_Interface {
	public void addRoomOrderDetail(RoomOrderDetailVO roomOrderDetail);
	public void updateRoomOrderDetail(RoomOrderDetailVO roomOrderDetail);
	public void deleteRoomOrderDetail(String room_order_id);
	public RoomOrderDetailVO findByRoomOrderId(String room_order_id);
	public List<RoomOrderDetailVO> getAllRoomOrderDetail();
}
