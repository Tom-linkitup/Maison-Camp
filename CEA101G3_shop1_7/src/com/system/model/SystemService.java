package com.system.model;

import java.util.List;

public class SystemService {
	
	private SystemDAO_interface dao;
	
	public SystemService() {
		dao = new SystemJDBCDAO();
	}
	
	public SystemVO addSystem(String sysId, String empId, String sysContent) {
		SystemVO SystemVO = new SystemVO();

		SystemVO.setSysId(sysId);
		SystemVO.setEmpId(empId);
		SystemVO.setSysContent(sysContent);
		dao.insert(SystemVO);

		return SystemVO;
	}
	
	public SystemVO updateSystem(String sysId,String empId, String sysContent) {

		SystemVO SystemVO = new SystemVO();

		SystemVO.setSysId(sysId);
		SystemVO.setEmpId(empId);
		SystemVO.setSysContent(sysContent);
		dao.update(SystemVO);

		return SystemVO;
	}
	
	public void deleteSystem(String sysId) {
		dao.delete(sysId);
	}
	
	public SystemVO getOneSystem(String sysId) {
		return dao.findByPrimaryKey(sysId);
	}
	
	public List<SystemVO> getAll(){
		return dao.getAll();
	}
}
