package com.actPhoto.model;

public class ActPhotoVO implements java.io.Serializable{
	private String actPhotoId;
	private String actId;
	private byte[] content;
	
	public ActPhotoVO() {
		super();
	}

	public String getActPhotoId() {
		return actPhotoId;
	}

	public void setActPhotoId(String actPhotoId) {
		this.actPhotoId = actPhotoId;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	

	
	
}
