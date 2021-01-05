package com.room_promotion.model;

import java.util.List;




public interface Room_promotionDAO_interface {
	 public void insert(Room_promotionVO room_promotionVO);
     public void update(Room_promotionVO room_promotionVO);
     public void delete(String room_promotion_id);
     public Room_promotionVO findByPrimaryKey(String room_promotion_id);
     public List<Room_promotionVO> getAll();
     public List<Room_promotionVO> getAllPast();
     public List<Room_promotionVO> getAllNow();
     public List<Room_promotionVO> getAllFuture();
//萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Room_promotionVO> getAll(Map<String, String[]> map); 
}
