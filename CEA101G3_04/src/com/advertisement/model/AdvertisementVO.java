package com.advertisement.model;

import java.io.Serializable;

public class AdvertisementVO implements Serializable{
	private String adv_id;
	private String emp_id;
	private java.sql.Date adv_start_date;
	private java.sql.Date adv_close_date;
	private String adv_content;
	
	public AdvertisementVO () {
		
	}

	public String getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public java.sql.Date getAdv_start_date() {
		return adv_start_date;
	}

	public void setAdv_start_date(java.sql.Date adv_start_date) {
		this.adv_start_date = adv_start_date;
	}

	public java.sql.Date getAdv_close_date() {
		return adv_close_date;
	}

	public void setAdv_close_date(java.sql.Date adv_close_date) {
		this.adv_close_date = adv_close_date;
	}

	public String getAdv_content() {
		return adv_content;
	}

	public void setAdv_content(String adv_content) {
		this.adv_content = adv_content;
	}
	
}