package com.func.model;

import java.util.List;

public class FuncService {
	FuncDAO_interface dao = null;
	
	public FuncService() {
		dao = new FuncJDBCDAO();
	}
	
	public FuncVO addFunc(String func_id, String func_name, String func_info) {
		
		FuncVO funcVO = new FuncVO();
		funcVO.setFunc_id(func_id);
		funcVO.setFunc_name(func_name);
		funcVO.setFunc_info(func_info);
		
		dao.insert(funcVO);
		
		return funcVO;
	}
	
	public FuncVO updateFunc(String func_name, String func_info, String func_id) {
		FuncVO funcVO = new FuncVO();
		funcVO.setFunc_id(func_id);
		funcVO.setFunc_name(func_name);
		funcVO.setFunc_info(func_info);
		dao.update(funcVO);
		return funcVO;
	}
	
	public void deleteFunc(String func_id) {
		dao.delete(func_id);
	}
	
	public FuncVO gerOneFunc(String func_id) {
		return dao.findByPrimaryKey(func_id);
	}
	
	public List<FuncVO> getAll(){
		return dao.getAll();
	}
}
