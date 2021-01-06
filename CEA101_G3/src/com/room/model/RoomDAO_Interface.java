package com.room.model;

import java.util.List;

public interface RoomDAO_Interface {
	public void addRoom(RoomVO roomVO);
	public void updateRoom(RoomVO roomVO);
	public void deleteRoom(String room_id);
	public RoomVO findByRoomId(String room_id);
	public List<RoomVO> getByRoomCategoryId(String room_category_id);
	public List<RoomVO> getAllRoom();
}
