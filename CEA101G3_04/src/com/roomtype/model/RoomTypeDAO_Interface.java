package com.roomtype.model;

import java.util.List;

public interface RoomTypeDAO_Interface {
	public void addRoomType(RoomTypeVO roomTypeVO);
	public void updateRoomType(RoomTypeVO roomTypeVO);
	public void deleteRoomType(String room_category_id);
	public RoomTypeVO findByRoomCategoryId(String room_category_id);
	public List<RoomTypeVO> getAllRoomType();
}
