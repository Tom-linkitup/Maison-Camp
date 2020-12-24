package com.roomorder.model;

import java.util.List;

public class RoomOrderService {
	private RoomOrderDAO_Interface dao;
	
	public RoomOrderService() {
		dao = new RoomOrderDAO();
	}
	
	public RoomOrderVO addRO(String room_order_id, String mem_id, String payment, java.sql.Date time, int room_total_amount, int status) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		
		roomOrderVO.setRoom_order_id(room_order_id);
		roomOrderVO.setMem_id(mem_id);
		roomOrderVO.setPayment(payment);
		roomOrderVO.setTime(time);
		roomOrderVO.setRoom_total_amount(room_total_amount);
		roomOrderVO.setStatus(status);
		dao.addRoomOrder(roomOrderVO);
		
		return roomOrderVO;
	}
	
	public RoomOrderVO updateRO(String room_order_id, String mem_id, String payment, java.sql.Date time, int room_total_amount, int status) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		
		roomOrderVO.setRoom_order_id(room_order_id);
		roomOrderVO.setMem_id(mem_id);
		roomOrderVO.setPayment(payment);
		roomOrderVO.setTime(time);
		roomOrderVO.setRoom_total_amount(room_total_amount);
		roomOrderVO.setStatus(status);
		dao.updateRoomOrder(roomOrderVO);
		
		return roomOrderVO;
	}
	
	public void deleteRO(String room_order_id) {
		dao.deleteRoomOrder(room_order_id);
	}
	
	public RoomOrderVO getOneRO(String room_order_id) {
		return dao.findByRoomOrderId(room_order_id);
	}
	
	public List<RoomOrderVO> getAllRO() {
		return dao.getAllRoomOrder();
	}
}
