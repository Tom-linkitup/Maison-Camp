package com.currentroom.model;

public class CurrentRoomService {
	private CurrentRoomDAO_Interface dao;
	
	public CurrentRoomService() {
		dao = new CurrentRoomDAO();
	}
	
	public Integer getCurRoomQtyByRT(String room_category_id) {
		return dao.getCurrentRoomQtyByRoomCategoryId(room_category_id);
	}
}
