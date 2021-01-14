package com.item_category.model;

import java.util.List;


public interface ItemCategoryDAO_interface {
      public void insert(ItemCategoryVO ItemCategoryVO);
      public void update(ItemCategoryVO ItemCategoryVO);
      public void delete(String itemCategoryId);
      public ItemCategoryVO findByPrimaryKey(String itemCategoryId);
      public List<ItemCategoryVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<EmpVO> getAll(Map<String, String[]> map); 
	
}


