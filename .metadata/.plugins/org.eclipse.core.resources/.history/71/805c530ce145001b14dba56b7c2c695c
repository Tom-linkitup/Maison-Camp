package com.roomorderdetail.model;

import java.util.List;

import com.roomorder.model.RoomOrderDAO;
import com.roomorder.model.RoomOrderVO;

public class RoomOrderDetailService {
	private RoomOrderDetailDAO_Interface dao;
	
	public RoomOrderDetailService() {
		dao = new RoomOrderDetailDAO();
	}
	
	public RoomOrderDetailVO addROD(String room_order_id, String room_category_id, String room_promotion_id, int quantity, int room_order_price, java.sql.Date order_time, String note) {
		
		RoomOrderDetailVO roomOrderDetailVO = new RoomOrderDetailVO();
		
		roomOrderDetailVO.setRoom_order_id(room_order_id);
		roomOrderDetailVO.setRoom_category_id(room_category_id);
		roomOrderDetailVO.setRoom_promotion_id(room_promotion_id);
		roomOrderDetailVO.setQuantity(quantity);
		roomOrderDetailVO.setRoom_order_price(room_order_price);
		roomOrderDetailVO.setOrder_time(order_time);
		roomOrderDetailVO.setNote(note);
		dao.addRoomOrderDetail(roomOrderDetailVO);
		
		return roomOrderDetailVO;
	}
	
	public RoomOrderDetailVO updateROD(String room_order_id, String room_category_id, String room_promotion_id, int quantity, int room_order_price, java.sql.Date order_time, String note) {
		
		RoomOrderDetailVO roomOrderDetailVO = new RoomOrderDetailVO();
		
		roomOrderDetailVO.setRoom_order_id(room_order_id);
		roomOrderDetailVO.setRoom_category_id(room_category_id);
		roomOrderDetailVO.setRoom_promotion_id(room_promotion_id);
		roomOrderDetailVO.setQuantity(quantity);
		roomOrderDetailVO.setRoom_order_price(room_order_price);
		roomOrderDetailVO.setOrder_time(order_time);
		roomOrderDetailVO.setNote(note);
		dao.updateRoomOrderDetail(roomOrderDetailVO);
		
		return roomOrderDetailVO;
	} 
	
	public void deleteROD(String room_order_id) {
		dao.deleteRoomOrderDetail(room_order_id);
	}
	
	public RoomOrderDetailVO getOneROD(String room_order_id) {
		return dao.findByRoomOrderId(room_order_id);
	}
	
	public List<RoomOrderDetailVO> getAllROD() {
		return dao.getAllRoomOrderDetail();
	}
	
	
}
