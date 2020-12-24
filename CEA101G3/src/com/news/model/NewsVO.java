package com.news.model;

import java.io.Serializable;

public class NewsVO implements Serializable{
	private String news_id;
	private String emp_id;
	private String news_content;
	private java.sql.Date create_time;
	
	public NewsVO(){
		
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public java.sql.Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(java.sql.Date create_time) {
		this.create_time = create_time;
	}

}
