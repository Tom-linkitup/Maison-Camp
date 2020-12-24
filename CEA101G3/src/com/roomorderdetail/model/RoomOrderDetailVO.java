package com.roomorderdetail.model;

import java.io.Serializable;

public class RoomOrderDetailVO implements Serializable {
	private String room_order_id;
	private String room_id;
	private String room_promotion_id;
	private java.sql.Date check_in_date;
	private int live_in_date;
	private String note;
	private int quantity;
	private int room_order_price;
	
	public RoomOrderDetailVO () {
		
	}

	public String getRoom_order_id() {
		return room_order_id;
	}

	public void setRoom_order_id(String room_order_id) {
		this.room_order_id = room_order_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_promotion_id() {
		return room_promotion_id;
	}

	public void setRoom_promotion_id(String room_promotion_id) {
		this.room_promotion_id = room_promotion_id;
	}

	public java.sql.Date getCheck_in_date() {
		return check_in_date;
	}

	public void setCheck_in_date(java.sql.Date check_in_date) {
		this.check_in_date = check_in_date;
	}

	public int getLive_in_date() {
		return live_in_date;
	}

	public void setLive_in_date(int live_in_date) {
		this.live_in_date = live_in_date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

}
