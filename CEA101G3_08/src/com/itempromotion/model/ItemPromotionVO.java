package com.itempromotion.model;

import java.sql.Date;

public class ItemPromotionVO implements java.io.Serializable{
	private String item_promotion_id;
	private String item_id;
	private String item_promotion_info;
	private Float item_discount;
	private Date item_prom_start_date;
	private Date item_prom_close_date;
	
	public String getItem_promotion_id() {
		return item_promotion_id;
	}
	public void setItem_promotion_id(String item_promotion_id) {
		this.item_promotion_id = item_promotion_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_promotion_info() {
		return item_promotion_info;
	}
	public void setItem_promotion_info(String item_promotion_info) {
		this.item_promotion_info = item_promotion_info;
	}
	public Float getItem_discount() {
		return item_discount;
	}
	public void setItem_discount(Float item_discount) {
		this.item_discount = item_discount;
	}
	public Date getItem_prom_start_date() {
		return item_prom_start_date;
	}
	public void setItem_prom_start_date(Date item_prom_start_date) {
		this.item_prom_start_date = item_prom_start_date;
	}
	public Date getItem_prom_close_date() {
		return item_prom_close_date;
	}
	public void setItem_prom_close_date(Date item_prom_close_date) {
		this.item_prom_close_date = item_prom_close_date;
	}
	
}
