package com.roomrsv.model;

import java.time.LocalDate;
import java.util.List;

public interface RoomRsvDAO_interface {
	public void insert(LocalDate rsv_date);
	public void update(LocalDate rsv_date, String room_category_id ,Integer room_left);
	public void delete(LocalDate rsv_date);
	public Integer roomCheck(LocalDate rsv_date, Integer stay, String room_category_id);
	public RoomRsvVO getOneByDateNRmType(LocalDate rsv_date, String room_category_id);
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsv_date);
	public List<RoomRsvVO> getAll();
	public List<RoomRsvVO> getAllByRoomCategoryId(String room_category_id);
}
