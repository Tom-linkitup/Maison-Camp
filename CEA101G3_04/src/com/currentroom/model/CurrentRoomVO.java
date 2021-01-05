package com.currentroom.model;

import java.io.Serializable;

public class CurrentRoomVO implements Serializable{
	
	private String room_category_id;
	private Integer room_current_left;
	
	public CurrentRoomVO () {
		
	}
	
	public CurrentRoomVO (String room_category_id, int room_current_left) {
		super();
		this.room_category_id = room_category_id;
		this.room_current_left = room_current_left;
	}

	public String getRoom_category_id() {
		return room_category_id;
	}

	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}

	public Integer getRoom_current_left() {
		return room_current_left;
	}

	public void setRoom_current_left(Integer room_current_left) {
		this.room_current_left = room_current_left;
	}

}
