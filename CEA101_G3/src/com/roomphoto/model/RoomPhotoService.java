package com.roomphoto.model;

import java.util.List;

public class RoomPhotoService {
	private RoomPhotoDAO_Interface dao;
	
	public RoomPhotoService() {
		dao = new RoomPhotoDAO();
	}
	
	public RoomPhotoVO addRPH(String room_category_id, byte[] content) {
		
		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		
		roomPhotoVO.setRoom_category_id(room_category_id);
		roomPhotoVO.setContent(content);
		
		dao.addRoomPhoto(roomPhotoVO);
		
		return roomPhotoVO;
	}
	
	public RoomPhotoVO updateRPH(String room_category_id, byte[] content, String room_photo_id) {
		
		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		
		roomPhotoVO.setRoom_category_id(room_category_id);
		roomPhotoVO.setContent(content);
		roomPhotoVO.setRoom_photo_id(room_photo_id);
		
		dao.updateRoomPhoto(roomPhotoVO);
		
		return roomPhotoVO;
	}
	
	public void deleteRPH(String room_photo_id) {
		dao.deleteRoomPhoto(room_photo_id);
	}
	
	public RoomPhotoVO getOneRPH(String room_photo_id) {
		return dao.findByRoomPhotoId(room_photo_id);
	}
	
	public List<RoomPhotoVO> getAllRPH(String room_category_id){
		return dao.getAllRoomPhoto(room_category_id);
	}
}
