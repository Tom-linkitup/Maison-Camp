package com.roomrsv.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.json.JSONObject;

public interface RoomRsvDAO_interface {
	public void insert(LocalDate rsv_date, String room_category_id, Connection con);
	public void update(JSONObject orderItem, Connection con);
	public void updateWithOrder(JSONObject orderItem, Connection con);
	public void delete(LocalDate rsv_date);
	public Integer roomCheck(LocalDate rsv_date, Integer stay, String room_category_id);
	public RoomRsvVO getOneByDateNRmType(LocalDate rsv_date, String room_category_id, Connection con);
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsv_date);
	public List<RoomRsvVO> getAll();
	public List<RoomRsvVO> getAllByRoomCategoryId(String room_category_id);
}
