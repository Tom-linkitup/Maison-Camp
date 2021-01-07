package com.activity.model;

import java.util.*;

public interface ActivityDAO_interface {
	public void insert(ActivityVO activitoVo);
	public void update(ActivityVO activitoVo);
	public ActivityVO findByPK (String  act_id);
	public List<ActivityVO> getAll();
	public List<ActivityVO> getAll(Map<String, String[]> map);
}
