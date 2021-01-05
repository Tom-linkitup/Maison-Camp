package com.func.model;

import java.util.List;

public interface FuncDAO_interface {
	public void insert(FuncVO funcVO);
	public void update(FuncVO funcVO);
	public void delete(String func_id);
	public FuncVO findByPrimaryKey(String func_id);
	public List<FuncVO> getAll();
}
