package com.news.model;

import java.util.List;

public interface NewsDAO_Interface {
	public void addNews(NewsVO newsVO);
	public void updateNews(NewsVO newsVO);
	public void deleteNews(String news_id);
	public NewsVO findByNewsId(String news_id);
	public List<NewsVO> getAllNews();
}
