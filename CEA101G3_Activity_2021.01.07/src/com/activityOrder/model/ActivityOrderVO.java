package com.activityOrder.model;

import java.io.Serializable;
import java.sql.Date;

public class ActivityOrderVO implements Serializable{

	private String actOrderId;
	private String actId;
	private String memId;
	private String note;
	private Integer people;
	private Integer actPrice;
	private String payment;
	private Date createTime;
	private Integer status;
	
	public ActivityOrderVO() {
		super();
	}

	public String getActOrderId() {
		return actOrderId;
	}

	public void setActOrderId(String actOrderId) {
		this.actOrderId = actOrderId;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}

	public Integer getActPrice() {
		return actPrice;
	}

	public void setActPrice(Integer actPrice) {
		this.actPrice = actPrice;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
}
