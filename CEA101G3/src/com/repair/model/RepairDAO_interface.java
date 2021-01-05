package com.repair.model;


import java.util.List;


public interface RepairDAO_interface {
	public void insert(RepairVO repairVO);
    public void update(RepairVO repairVO);
    public void delete(String repair_id);
    public RepairVO findByPrimaryKey(String repair_id);
    public List<RepairVO> getAll();
    public List<RepairVO> getStatus1();
    public List<RepairVO> getStatus0();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RepairVO> getAll(Map<String, String[]> map); 
}
