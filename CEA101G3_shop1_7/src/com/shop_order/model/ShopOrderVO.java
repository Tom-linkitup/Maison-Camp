package com.shop_order.model;
import java.sql.Date;

public class ShopOrderVO implements java.io.Serializable {
	private String shop_order_id;
	private String mem_id;
	private String payment;
	private Date time;
	private Float shop_total_amount;
	private Integer status;
	
	
	
	public String getShop_order_id() {
		return shop_order_id;
	}
	public void setShop_order_id(String shop_order_id) {
		this.shop_order_id = shop_order_id;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getShop_total_amount() {
		return shop_total_amount;
	}
	public void setShop_total_amount(float shop_total_amount) {
		this.shop_total_amount = shop_total_amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
