package com.item_comment.model;

import java.util.List;

public interface ItemCommentDAO_interface {
    public void insert(ItemCommentVO ItemCommentVO);
    public void update(ItemCommentVO ItemCommentVO);
    public void delete(String itemCommentId);
    public ItemCommentVO findByPrimaryKey(String itemCommentId);
    public List<ItemCommentVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ItemCommentVO> getAll(Map<String, String[]> map); 

}
