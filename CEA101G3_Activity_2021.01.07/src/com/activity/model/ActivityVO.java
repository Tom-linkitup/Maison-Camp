package com.activity.model;

import java.sql.Date;

public class ActivityVO implements java.io.Serializable {
	private String actId;
	private String actCategoryId;
	private String actInfo;
	private Integer actPrice;
	private Date actStartDate;
	private Date actEndDate;
	private Date actApplyOpen;
	private Date actApplyClose;
	private Integer maxPeople;
	private Integer minPeople;
	private Integer actAlreadyApply;
	private String actName;
	private Integer actStatus;
	private Double actDiscount;
	private String actPromInfo;
	private Date actPromStartDate;
	private Date actPromCloseDate;

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getActCategoryId() {
		return actCategoryId;
	}

	public void setActCategoryId(String actCategoryId) {
		this.actCategoryId = actCategoryId;
	}

	public String getActInfo() {
		return actInfo;
	}

	public void setActInfo(String actInfo) {
		this.actInfo = actInfo;
	}

	public Integer getActPrice() {
		return actPrice;
	}

	public void setActPrice(Integer actPrice) {
		this.actPrice = actPrice;
	}

	public Date getActStartDate() {
		return actStartDate;
	}

	public void setActStartDate(Date actStartDate) {
		this.actStartDate = actStartDate;
	}

	public Date getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
	}

	public Date getActApplyOpen() {
		return actApplyOpen;
	}

	public void setActApplyOpen(Date actApplyOpen) {
		this.actApplyOpen = actApplyOpen;
	}

	public Date getActApplyClose() {
		return actApplyClose;
	}

	public void setActApplyClose(Date actApplyClose) {
		this.actApplyClose = actApplyClose;
	}

	public Integer getMaxPeople() {
		return maxPeople;
	}

	public void setMaxProple(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}

	public Integer getMinPeople() {
		return minPeople;
	}

	public void setMinPeople(Integer minPeople) {
		this.minPeople = minPeople;
	}

	public Integer getActAlreadyApply() {
		return actAlreadyApply;
	}

	public void setActAlreadyApply(Integer actAlreadyApply) {
		this.actAlreadyApply = actAlreadyApply;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public Integer getActStatus() {
		return actStatus;
	}

	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}

	public Double getActDiscount() {
		return actDiscount;
	}

	public void setActDiscount(Double actDiscount) {
		this.actDiscount = actDiscount;
	}

	public String getActPromInfo() {
		return actPromInfo;
	}

	public void setActPromInfo(String actPromInfo) {
		this.actPromInfo = actPromInfo;
	}

	public Date getActPromStartDate() {
		return actPromStartDate;
	}

	public void setActPromStartDate(Date actPromStartDate) {
		this.actPromStartDate = actPromStartDate;
	}

	public Date getActPromCloseDate() {
		return actPromCloseDate;
	}

	public void setActPromCloseDate(Date actPromCloseDate) {
		this.actPromCloseDate = actPromCloseDate;
	}

}
