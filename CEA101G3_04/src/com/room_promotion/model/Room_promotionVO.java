package com.room_promotion.model;

import java.sql.Date;

public class Room_promotionVO implements java.io.Serializable{

	private String room_promotion_id;
	private String room_category_id;
	private String room_promotion_info;
	private Double room_discount;
	private Date room_prom_start_date;
	private Date room_prom_end_date;
	
	
	public String getRoom_promotion_id() {
		return room_promotion_id;
	}
	public void setRoom_promotion_id(String room_promotion_id) {
		this.room_promotion_id = room_promotion_id;
	}
	public String getRoom_category_id() {
		return room_category_id;
	}
	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}
	public String getRoom_promotion_info() {
		return room_promotion_info;
	}
	public void setRoom_promotion_info(String room_promotion_info) {
		this.room_promotion_info = room_promotion_info;
	}
	public Double getRoom_discount() {
		return room_discount;
	}
	public void setRoom_discount(Double room_discount) {
		this.room_discount = room_discount;
	}
	public Date getRoom_prom_start_date() {
		return room_prom_start_date;
	}
	public void setRoom_prom_start_date(Date room_prom_start_date) {
		this.room_prom_start_date = room_prom_start_date;
	}
	public Date getRoom_prom_end_date() {
		return room_prom_end_date;
	}
	public void setRoom_prom_end_date(Date room_prom_end_date) {
		this.room_prom_end_date = room_prom_end_date;
	}






}
