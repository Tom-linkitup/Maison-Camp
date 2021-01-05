package com.roomphoto.model;

import java.util.List;

public interface RoomPhotoDAO_Interface {
	public void addRoomPhoto(RoomPhotoVO roomPhotoVO);
	public void updateRoomPhoto(RoomPhotoVO roomPhotoVO);
	public void deleteRoomPhoto(String room_photo_id);
	public RoomPhotoVO findByRoomPhotoId(String room_photo_id);
	public List<RoomPhotoVO> getAllRoomPhoto(String room_category_id);
}
