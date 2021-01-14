package com.room.model;

import java.util.List;

public class RoomService {
	
private RoomDAO_Interface dao;
	
	public RoomService() {
		dao = new RoomDAO();
	}
	
	public RoomVO addRM(String room_category_id, int status, int occupy) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoom_category_id(room_category_id);
		roomVO.setStatus(status);
		roomVO.setOccupy(occupy);
		
		dao.addRoom(roomVO);
		
		return roomVO;
	}
	
	public RoomVO updateRM(String room_category_id, int status, int occupy, String room_id) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoom_category_id(room_category_id);
		roomVO.setStatus(status);
		roomVO.setOccupy(occupy);
		roomVO.setRoom_id(room_id);
		
		dao.updateRoom(roomVO);
		
		return roomVO;
	}
	
	public void deleteRM(String room_id) {
		dao.deleteRoom(room_id);
	}
	
	public RoomVO getOneRM(String room_id) {
		return dao.findByRoomId(room_id);
	}
	
	public List<RoomVO> getRmByRTC(String room_category_id){
		return dao.getByRoomCategoryId(room_category_id);
	}
	
	public List<RoomVO> getAllRM(){
		return dao.getAllRoom();
	}
	
	public void updateRmCondition(Integer status, String room_id) {
		dao.updateRmStatus(status, room_id);
	}
	
	public void updateRmLive(Integer occupy, String room_id) {
		dao.updateRmOccupy(occupy, room_id);
	}

}
