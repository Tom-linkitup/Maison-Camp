package com.activityComment.model;

import java.util.*;


public interface ActivityCommentDAO_interface {
	 public void insert(ActivityCommentVO activityCommentVO);
     public void update(ActivityCommentVO activityCommentVO);
     public void delete(String activityCommentId);
     public ActivityCommentVO findActivityCommentId(String activityCommentId);
     public List<ActivityCommentVO> getAll();
     public List<ActivityCommentVO> getByActCategoryId(String actCategoryId);

}
