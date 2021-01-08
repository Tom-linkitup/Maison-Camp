package com.emp.model;

import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;
	
	public EmpService() {
		dao = new EmpDAO();
	}
	
	public EmpVO addEmp(String emp_user_id, String emp_user_pwd, String emp_name, Integer emp_status) {
		
		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_user_id(emp_user_id);
		empVO.setEmp_user_pwd(emp_user_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_status(emp_status);
		dao.insert(empVO);
		
		return empVO;
	}
	
	public EmpVO updateEmp(String emp_id, String emp_user_id, String emp_user_pwd, String emp_name, Integer emp_status) {
		
		EmpVO empVO = new EmpVO();
		
		empVO.setEmp_id(emp_id);
		empVO.setEmp_user_id(emp_user_id);
		empVO.setEmp_user_pwd(emp_user_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_status(emp_status);
		dao.update(empVO);
		
		return empVO;
	}
	
	public void deleteEmp(String emp_id) {
		dao.delete(emp_id);
	}
	
	public EmpVO getOneEmp(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	
	public List<EmpVO> getAll(){
		return dao.getAll(); 
	}
	
	public EmpVO isUser(String emp_user_id, String emp_user_pwd) {
		return dao.getUser(emp_user_id, emp_user_pwd);
	}
}
