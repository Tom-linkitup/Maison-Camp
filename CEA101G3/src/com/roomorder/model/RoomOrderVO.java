package com.roomorder.model;

import java.io.Serializable;

public class RoomOrderVO implements Serializable {
	private String room_order_id;
	private String mem_id;	
	private String payment;	
	private java.sql.Date time;	
	private int room_total_amount;	
	private int status;	
	
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public java.sql.Date getTime() {
		return time;
	}

	public void setTime(java.sql.Date time) {
		this.time = time;
	}

	public int getRoom_total_amount() {
		return room_total_amount;
	}

	public void setRoom_total_amount(int room_total_amount) {
		this.room_total_amount = room_total_amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
