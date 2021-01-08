package com.emp_func.model;

import java.util.List;

public class EmpFuncService {
	
	private EmpFuncDAO_interface dao;
	
	public EmpFuncService() {
		dao = new EmpFuncDAO();
	}
	
	public EmpFuncVO addEmpFunc(String emp_id , String func_id) {
		
		EmpFuncVO empFuncVO = new EmpFuncVO();
		empFuncVO.setEmp_id(emp_id);
		empFuncVO.setFunc_id(func_id);
		dao.insert(empFuncVO);
		return empFuncVO;
		
	}
	
	public void deleteEmpFunc(String emp_id , String func_id) {
		dao.delete(emp_id , func_id);
	}
	
	public List<EmpFuncVO> getOneEmpFunc(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	
	public List<String> findEmpFuncs(String emp_id) {
		return dao.findFuncs(emp_id);
	}
	
	public List<EmpFuncVO> getAll(){
		return dao.getAll();
	}
}
