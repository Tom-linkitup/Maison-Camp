package com.roomorderdetail.model;

import java.io.Serializable;

public class RoomOrderDetailVO implements Serializable {
	private String room_order_id;
	private String room_category_id;
	private String room_promotion_id;
	private int quantity;
	private int room_order_price;
	private java.sql.Date order_time;
	private String note;
	
	public RoomOrderDetailVO () {
		
	}

	public String getRoom_order_id() {
		return room_order_id;
	}

	public void setRoom_order_id(String room_order_id) {
		this.room_order_id = room_order_id;
	}

	public String getRoom_category_id() {
		return room_category_id;
	}

	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}

	public String getRoom_promotion_id() {
		return room_promotion_id;
	}

	public void setRoom_promotion_id(String room_promotion_id) {
		this.room_promotion_id = room_promotion_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRoom_order_price() {
		return room_order_price;
	}

	public void setRoom_order_price(int room_order_price) {
		this.room_order_price = room_order_price;
	}

	public java.sql.Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(java.sql.Date order_time) {
		this.order_time = order_time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	

}
