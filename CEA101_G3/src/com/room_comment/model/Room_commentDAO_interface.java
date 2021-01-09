package com.room_comment.model;

import java.util.*;

import com.room.model.RoomVO;

public interface Room_commentDAO_interface {
     public void insert(Room_commentVO room_commentVO);
     public void update(Room_commentVO room_commentVO);
     public void delete(String room_comment_id);
     public Room_commentVO findByPrimaryKey(String room_comment_id);
     public List<Room_commentVO> getAll();
     public List<Room_commentVO> getAllReply();
     public List<Room_commentVO> getAllWaitReply();
     public List<Room_commentVO> getByRoomCategoryId(String room_category_id);
//萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Room_commentVO> getAll(Map<String, String[]> map); 
}
