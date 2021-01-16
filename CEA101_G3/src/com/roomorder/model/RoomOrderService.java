package com.roomorder.model;

import java.util.List;

public class RoomOrderService {
	private RoomOrderDAO_Interface dao;
	
	public RoomOrderService() {
		dao = new RoomOrderDAO();
	}
	
	public RoomOrderVO addRO(String mem_id, java.sql.Date check_in_date, java.sql.Date check_out_date, int status) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		
		roomOrderVO.setMem_id(mem_id);
		roomOrderVO.setCheck_in_date(check_in_date);
		roomOrderVO.setCheck_out_date(check_out_date);
		roomOrderVO.setStatus(status);
		dao.addRoomOrder(roomOrderVO);
		
		return roomOrderVO;
	}
	
	public RoomOrderVO updateRO(String room_order_id, String mem_id, java.sql.Date check_in_date, java.sql.Date check_out_date, int status) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		
		roomOrderVO.setRoom_order_id(room_order_id);
		roomOrderVO.setMem_id(mem_id);
		roomOrderVO.setCheck_in_date(check_in_date);
		roomOrderVO.setCheck_out_date(check_out_date);
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
	
	public List<RoomOrderVO> getOneByMemId(String mem_id){
		return dao.findByMemId(mem_id);
	}
	
	public List<RoomOrderVO> getAllCheckInOrder(){
		return dao.getAllBeforeToday();
	}
	
	public List<RoomOrderVO> getAllCheckOutOrder(){
		return dao.getAllDateOut();
	}
	
	public List<RoomOrderVO> getOrderByStatus(Integer status){
		return dao.getAllByOrderStatus(status);
	}
	
	public void updateOrderCondition(Integer status, String current_room_id, String room_order_id) {
		dao.updateOrderStatus(status, current_room_id, room_order_id);
	}
	
	public List<RoomOrderVO> getAllInRoom(){
		return dao.getAllInRoom();
	}
}
