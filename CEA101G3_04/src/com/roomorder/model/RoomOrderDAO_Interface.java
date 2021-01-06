package com.roomorder.model;

import java.util.List;
import java.util.Set;

import com.roomorderdetail.model.RoomOrderDetailVO;

public interface RoomOrderDAO_Interface {
	public void addRoomOrder(RoomOrderVO roomOrderVO);
	public void updateRoomOrder(RoomOrderVO roomOrderVO);
	public void deleteRoomOrder(String room_order_id);
	public RoomOrderVO findByRoomOrderId(String room_order_id);
	public List<RoomOrderVO> getAllRoomOrder();
	//查詢某訂房訂單的明細(一對多)(回傳Set)
    public Set<RoomOrderDetailVO> getDetailsByRoomOrderId(String room_order_id);    
    //同時新增訂單與訂單明細
    public void insertWithDetails(RoomOrderVO roomOrderVO , List<RoomOrderDetailVO> list);
}