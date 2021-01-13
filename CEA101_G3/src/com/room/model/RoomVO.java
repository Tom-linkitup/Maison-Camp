package com.room.model;

import java.io.Serializable;

public class RoomVO implements Serializable {
	private String room_id;
	private String room_category_id;
	private int status;
	private int occupy;
	public RoomVO() {
		
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_category_id() {
		return room_category_id;
	}

	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOccupy() {
		return occupy;
	}

	public void setOccupy(int occupy) {
		this.occupy = occupy;
	}
	
}
