package com.roomorder.model;

import java.io.Serializable;

public class RoomOrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String room_order_id;
	private String mem_id;	
	private java.sql.Date check_in_date;
	private java.sql.Date check_out_date;
	private int status;
	private String current_room_id;
	
	public RoomOrderVO(){
		
	}

	public String getRoom_order_id() {
		return room_order_id;
	}

	public void setRoom_order_id(String room_order_id) {
		this.room_order_id = room_order_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public java.sql.Date getCheck_in_date() {
		return check_in_date;
	}

	public void setCheck_in_date(java.sql.Date check_in_date) {
		this.check_in_date = check_in_date;
	}

	public java.sql.Date getCheck_out_date() {
		return check_out_date;
	}

	public void setCheck_out_date(java.sql.Date check_out_date) {
		this.check_out_date = check_out_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCurrent_room_id() {
		return current_room_id;
	}

	public void setCurrent_room_id(String current_room_id) {
		this.current_room_id = current_room_id;
	}

}
