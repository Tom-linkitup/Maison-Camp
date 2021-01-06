package com.item_comment.model;
import java.sql.Date;
import java.sql.Timestamp;

public class ItemCommentVO implements java.io.Serializable{
	private String itemCommentId;
	private String itemId;
	private String shopComment;
	private java.sql.Timestamp time;
	
	public String getItemCommentId() {
		return itemCommentId;
	}
	public void setItemCommentId(String itemCommentId) {
		this.itemCommentId = itemCommentId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getShopComment() {
		return shopComment;
	}
	public void setShopComment(String shopComment) {
		this.shopComment = shopComment;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
