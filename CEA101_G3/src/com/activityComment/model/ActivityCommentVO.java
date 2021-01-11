package com.activityComment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActivityCommentVO implements Serializable {
	private String actCommentId;
	private String actOrderId;
	private String actCategoryId;
	private String actComment;
	private Timestamp createTime;
	
	public ActivityCommentVO() {
		super();
	}



	public String getActCommentId() {
		return actCommentId;
	}



	public void setActCommentId(String actCommentId) {
		this.actCommentId = actCommentId;
	}
	
	public String getActOrderId() {
		return actOrderId;
	}

	public void setActOrderId(String actOrderId) {
		this.actOrderId = actOrderId;
	}

	public String getActCategoryId() {
		return actCategoryId;
	}

	public void setActCategoryId(String actCategoryId) {
		this.actCategoryId = actCategoryId;
	}

	public String getActComment() {
		return actComment;
	}

	public void setActComment(String actComment) {
		this.actComment = actComment;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	
}
