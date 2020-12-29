package com.roomtype.model;

import java.io.Serializable;

public class RoomTypeVO implements Serializable{
	private String room_category_id;
	private String room_name;
	private String room_type;
	private Integer room_price;
	private Integer area;
	private Integer room_guest;
	private Integer room_quantity;
	private Integer room_category_status;
	private String room_info;
	
	public RoomTypeVO(){
		
	}
	

	public Integer getRoom_guest() {
		return room_guest;
	}


	public void setRoom_guest(Integer room_guest) {
		this.room_guest = room_guest;
	}


	public Integer getRoom_quantity() {
		return room_quantity;
	}


	public void setRoom_quantity(Integer room_quantity) {
		this.room_quantity = room_quantity;
	}


	public String getRoom_category_id() {
		return room_category_id;
	}

	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public Integer getRoom_price() {
		return room_price;
	}

	public void setRoom_price(Integer room_price) {
		this.room_price = room_price;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getRoom_category_status() {
		return room_category_status;
	}

	public void setRoom_category_status(Integer room_category_status) {
		this.room_category_status = room_category_status;
	}

	public String getRoom_info() {
		return room_info;
	}

	public void setRoom_info(String room_info) {
		this.room_info = room_info;
	}
	
}
