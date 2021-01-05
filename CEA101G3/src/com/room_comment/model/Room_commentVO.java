package com.room_comment.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Room_commentVO implements java.io.Serializable{
	private String room_comment_id;
	private String room_category_id;
	private String room_comment_content;
	private Timestamp time;
	private String comment_reply;
	
	
	public String getComment_reply() {
		return comment_reply;
	}
	public void setComment_reply(String comment_reply) {
		this.comment_reply = comment_reply;
	}
		public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getRoom_comment_id() {
		return room_comment_id;
	}
	public void setRoom_comment_id(String room_comment_id) {
		this.room_comment_id = room_comment_id;
	}
	public String getRoom_category_id() {
		return room_category_id;
	}
	public void setRoom_category_id(String room_category_id) {
		this.room_category_id = room_category_id;
	}
	public String getRoom_comment_content() {
		return room_comment_content;
	}
	public void setRoom_comment_content(String room_comment_content) {
		this.room_comment_content = room_comment_content;
	}
	
	
	
}
