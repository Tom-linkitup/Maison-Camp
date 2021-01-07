package com.activityOrder.model;

import java.util.List;

public interface ActivityOrderDAO_interfact {
	 public void insert(ActivityOrderVO  activityOrderVO);
     public void update(ActivityOrderVO  activityOrderVO);
     public void delete(String acttOrderId);
     public ActivityOrderVO  findByActOrderId(String actOrderId);
     public List<ActivityOrderVO > findByActId(String actId);
     public List<ActivityOrderVO > getAll();
}
