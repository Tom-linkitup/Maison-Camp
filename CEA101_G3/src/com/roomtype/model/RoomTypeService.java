package com.roomtype.model;

import java.util.List;

public class RoomTypeService {
	private RoomTypeDAO_Interface dao;
	
	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}
	
	public RoomTypeVO addRT(String room_category_id, String room_name, String room_type, int room_price, int area, int room_guest, int room_quantity, int room_category_status, String room_info) {
		
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		
		roomTypeVO.setRoom_category_id(room_category_id);
		roomTypeVO.setRoom_name(room_name);
		roomTypeVO.setRoom_type(room_type);
		roomTypeVO.setRoom_price(room_price);
		roomTypeVO.setArea(area);
		roomTypeVO.setRoom_guest(room_guest);
		roomTypeVO.setRoom_quantity(room_quantity);
		roomTypeVO.setRoom_category_status(room_category_status);
		roomTypeVO.setRoom_info(room_info);
		dao.addRoomType(roomTypeVO);
		
		return roomTypeVO;
	}
	
	public RoomTypeVO updateRT(String room_category_id, String room_name, String room_type, int room_price, int area, int room_guest, int room_quantity, int room_category_status, String room_info) {
		
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		
		roomTypeVO.setRoom_category_id(room_category_id);
		roomTypeVO.setRoom_name(room_name);
		roomTypeVO.setRoom_type(room_type);
		roomTypeVO.setRoom_price(room_price);
		roomTypeVO.setArea(area);
		roomTypeVO.setRoom_guest(room_guest);
		roomTypeVO.setRoom_quantity(room_quantity);
		roomTypeVO.setRoom_category_status(room_category_status);
		roomTypeVO.setRoom_info(room_info);
		dao.updateRoomType(roomTypeVO);
		
		return roomTypeVO;
	}
	
	public void deleteRT(String room_category_id) {
		dao.deleteRoomType(room_category_id);
	}
	
	public RoomTypeVO getOneRT(String room_category_id) {
		return dao.findByRoomCategoryId(room_category_id);
	}
	
	public List<RoomTypeVO> getAllRT(){
		return dao.getAllRoomType();
	}
}
