package com.actCategory.model;

import java.util.List;

public interface ActCategoryDAOInterface {
	public void insert(ActCategoryVO act_categoryVo);
	public void update(ActCategoryVO act_categoryVo);
	public ActCategoryVO findByPK (String  act_category_id);
	public List<ActCategoryVO> getAll();	
}
