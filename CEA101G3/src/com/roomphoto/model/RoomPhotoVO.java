package com.roomphoto.model;

import java.io.Serializable;

public class RoomPhotoVO implements Serializable {
	private String room_photo_id;
	private String room_category_id;
	private byte[] content;
	
	
	public RoomPhotoVO() {
		
	}


	public String getRoom_photo_id() {
		return room_photo_id;
	}


	public void setRoom_photo_id(String room_photo_id) {
		this.room_photo_id = room_photo_id;
	}


	public String getRoom_category_id() {
		return room_category_id;
	}


	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}


	public byte[] getContent() {
		return content;
	}


	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
