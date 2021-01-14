package com.item_photo.model;

public class ItemPhotoVO implements java.io.Serializable{
	private String itemPhotoId;
	private String itemId;
	private byte[] content;
	
	public String getItemPhotoId() {
		return itemPhotoId;
	}
	public void setItemPhotoId(String itemPhotoId) {
		this.itemPhotoId = itemPhotoId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
