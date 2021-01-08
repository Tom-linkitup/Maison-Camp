package com.shop_order.model;

public class Item implements java.io.Serializable{
	
	private String name;
	private int price;
	private int quantity;
	private String itemId;
	public Item() {
		name= "";
		price= 0;
		quantity= 0;
		itemId= "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
