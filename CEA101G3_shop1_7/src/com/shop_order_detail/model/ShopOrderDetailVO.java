package com.shop_order_detail.model;

public class ShopOrderDetailVO implements java.io.Serializable{
	private String shop_order_id;
	private String item_id;
	private	String item_promotion_id;
	private String note;
	private Integer quantity;
	private Integer item_price;
	
	public String getShop_order_id() {
		return shop_order_id;
	}
	public void setShop_order_id(String shop_order_id) {
		this.shop_order_id = shop_order_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_promotion_id() {
		return item_promotion_id;
	}
	public void setItem_promotion_id(String item_promotion_id) {
		this.item_promotion_id = item_promotion_id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getItem_price() {
		return item_price;
	}
	public void setItem_price(Integer item_price) {
		this.item_price = item_price;
	}
	
}
