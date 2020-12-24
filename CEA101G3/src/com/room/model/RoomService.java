package com.room.model;

import java.util.List;

public class RoomService {
	
private RoomDAO_Interface dao;
	
	public RoomService() {
		dao = new RoomJDBCDAO();
	}
	
	public RoomVO addRM(String room_category_id, int people, int status) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoom_category_id(room_category_id);
		roomVO.setPeople(people);
		roomVO.setStatus(status);
		
		dao.addRoom(roomVO);
		
		return roomVO;
	}
	
	public RoomVO updateRM(String room_category_id, int people, int status, String room_id) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoom_category_id(room_category_id);
		roomVO.setPeople(people);
		roomVO.setStatus(status);
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

}
