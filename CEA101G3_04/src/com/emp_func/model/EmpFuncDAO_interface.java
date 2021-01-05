package com.emp_func.model;

import java.util.List;

public interface EmpFuncDAO_interface {

	public void insert(EmpFuncVO empFuncVO);
	public void delete(String emp_id,  String func_id); 
	public List<EmpFuncVO> findByPrimaryKey(String emp_id);
	public List<EmpFuncVO> getAll();
	
	public List<String> findFuncs(String emp_id);
}
