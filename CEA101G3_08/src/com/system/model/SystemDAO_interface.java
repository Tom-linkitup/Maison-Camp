package com.system.model;

import java.util.List;

import com.item.model.ItemVO;

public interface SystemDAO_interface {
        public void insert(SystemVO SystemVO);
        public void update(SystemVO SystemVO);
        public void delete(String sysId);
        public SystemVO findByPrimaryKey(String sysId);
        public List<SystemVO> getAll();
      //萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 
	
}
