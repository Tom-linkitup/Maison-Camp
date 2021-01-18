package com.roomorder.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import com.roomorderdetail.model.RoomOrderDetailVO;

public interface RoomOrderDAO_Interface {
	public void addRoomOrder(RoomOrderVO roomOrderVO);
	public void updateRoomOrder(RoomOrderVO roomOrderVO);
	public void deleteRoomOrder(String room_order_id);
	public RoomOrderVO findByRoomOrderId(String room_order_id);
	public List<RoomOrderVO> findByMemId(String mem_id);
	public List<RoomOrderVO> getAllRoomOrder();
	//查詢某訂房訂單的明細(一對多)(回傳Set)
    public Set<RoomOrderDetailVO> getDetailsByRoomOrderId(String room_order_id);    
    //同時新增訂單與訂單明細與預定表
    public void insertWithDetails(RoomOrderVO roomOrderVO , List<RoomOrderDetailVO> list, JSONObject orderItem);
    //同時取消訂單（更改訂單狀態）與更新預定表
    public void updateWithRsv(Integer status, String room_order_id, JSONObject orderItem);
    //查詢當天需入住的訂單
    public List<RoomOrderVO> getAllBeforeToday();
    //查詢當天需退房的訂單
    public List<RoomOrderVO> getAllDateOut();
    //依訂單狀態查詢訂單
    public List<RoomOrderVO> getAllByOrderStatus(Integer status);
    //改變訂單狀態
    public void updateOrderStatus(Integer status, String room_order_id, String current_room_id);
    //抓取已入住的名單，用於新增額外消費
    public List<RoomOrderVO> getAllInRoom();
}
