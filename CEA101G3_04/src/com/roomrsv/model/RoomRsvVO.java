package com.roomrsv.model;

import java.io.Serializable;
import java.time.LocalDate;

public class RoomRsvVO implements Serializable {
	private LocalDate rsv_date;
	private String room_category_id;
	private Integer room_left;
	
	public RoomRsvVO() {};
	
	public RoomRsvVO(LocalDate rsv_date, String room_category_id, Integer room_left) {
		super();
		this.setRsv_date(rsv_date);
		this.room_category_id = room_category_id;
		this.room_left = room_left;
	}

	

	public String getRoom_category_id() {
		return room_category_id;
	}

	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}

	public Integer getRoom_left() {
		return room_left;
	}

	public void setRoom_left(Integer room_left) {
		this.room_left = room_left;
	}

	public LocalDate getRsv_date() {
		return rsv_date;
	}

	public void setRsv_date(LocalDate rsv_date) {
		this.rsv_date = rsv_date;
	}

	
	
	
}
