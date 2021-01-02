package com.roomrsv.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class RoomRsvService {
	private RoomRsvDAO_interface dao;
	
	public RoomRsvService() {
		dao = new RoomRsvDAO();
	}
	
	public void insertRsvDate(LocalDate rsv_date, String room_category_id, Connection con) {
		dao.insert(rsv_date, room_category_id, con);
	}
	
	public RoomRsvVO updateRmLeft(LocalDate rsv_date, String room_category_id, Integer room_left) {
		RoomRsvVO rsvVO = new RoomRsvVO();
		rsvVO.setRsv_date(rsv_date);
		rsvVO.setRoom_category_id(room_category_id);
		rsvVO.setRoom_left(room_left);
		return rsvVO;
	}
	
	public void deleteRsvDate(LocalDate rsv_date) {
		dao.delete(rsv_date);
	}
	
	public Integer roomCheck(LocalDate rsv_date, Integer stay, String room_category_id) {
		return dao.roomCheck(rsv_date, stay, room_category_id);
	}
	
	public RoomRsvVO getOneByDateNRmType(LocalDate rsv_date, String room_category_id, Connection con) {
		return dao.getOneByDateNRmType(rsv_date, room_category_id, con);
	}
	
	public List<RoomRsvVO> getOneByDate(LocalDate rsv_date) {
		return dao.getOneDayByDate(rsv_date);
	}
	
	public List<RoomRsvVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomRsvVO> getAllByRmType(String room_category_id) {
		return dao.getAllByRoomCategoryId(room_category_id);
	}
}
