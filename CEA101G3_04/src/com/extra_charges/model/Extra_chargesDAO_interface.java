package com.extra_charges.model;

import java.util.List;



public interface Extra_chargesDAO_interface {
	public void insert(Extra_chargesVO extra_chargesVO);
    public void update(Extra_chargesVO extra_chargesVO);
    public void delete(String extra_charges_id);
    public Extra_chargesVO findByPrimaryKey(String extra_charges_id);
    public List<Extra_chargesVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<Extra_chargesVO> getAll(Map<String, String[]> map); 
	
}
