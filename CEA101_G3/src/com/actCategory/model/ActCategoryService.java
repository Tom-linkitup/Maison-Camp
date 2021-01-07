package com.actCategory.model;

import java.util.List;

public class ActCategoryService {
	private ActCategoryDAOInterface dao;

	public ActCategoryService() {
		dao = new ActCategoryDAO();
	}

	public ActCategoryVO addActCategor(String actCategoryId, String actCategoryName) {
		ActCategoryVO actCategoryVO = new ActCategoryVO();
		actCategoryVO.setActCategoryId(actCategoryId);
		actCategoryVO.setActCategoryName(actCategoryName);
		dao.insert(actCategoryVO);
		return actCategoryVO;
	}

	public ActCategoryVO updateActCategor(String actCategoryId, String actCategoryName) {
		ActCategoryVO actCategoryVO = new ActCategoryVO();
		actCategoryVO.setActCategoryId(actCategoryId);
		actCategoryVO.setActCategoryName(actCategoryName);
		dao.update(actCategoryVO);
		return actCategoryVO;
	}
	
	public ActCategoryVO getOneActCategory(String actCategoryId) {
		return dao.findByPK(actCategoryId);
	}
	
	public List<ActCategoryVO> getAll(){
		return dao.getAll();
	}
}
