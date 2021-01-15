package com.item.model;

import java.util.List;

public interface ItemDAO_interface {
        public void insert(ItemVO ItemVO);
        public void update(ItemVO ItemVO);
        public void delete(String ItemId);
        public ItemVO findByPrimaryKey(String ItemId);
        public List<ItemVO> getAll();
        public List<ItemVO> getByCategoryStOne(String itemCategoryId);
      //萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 
	
}
