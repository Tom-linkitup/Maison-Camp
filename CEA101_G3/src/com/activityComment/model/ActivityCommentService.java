package com.activityComment.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.actPhoto.model.ActPhotoVO;
import com.activityOrder.model.ActivityOrderJDBCDAO;
import com.activityOrder.model.ActivityOrderVO;

public class ActivityCommentService {

	private ActivityCommentDAO_interface dao;
	
	public ActivityCommentService() {
		super();
		dao = new ActivityCommentDAO();
	}
	
	public ActivityCommentVO addActivityComment(String orderId,String actCategoryId, String comment) {
		
		ActivityCommentVO acVO = new ActivityCommentVO();
		acVO.setActOrderId(orderId);
		acVO.setActCategoryId(actCategoryId);;
		acVO.setActComment(comment);
		
		dao.insert(acVO);
		
		return acVO;
	}
	
	public ActivityCommentVO updateActivityComment(String actCommentId, String comment) {
		
		ActivityCommentVO acVO = dao.findActivityCommentId(actCommentId);
		
	
		acVO.setActComment(comment);
		
		dao.update(acVO);
		
		return acVO;
	}
	
	public void deleteActivityCommentVO(String activityCommentId) {
		dao.delete(activityCommentId);
	}
	
	public ActivityCommentVO findByActCommentId(String actCommentId) {
		ActivityCommentVO acVO = new ActivityCommentVO();
		acVO = dao.findActivityCommentId(actCommentId);
		
		return acVO;
	}
	
	
	public List<ActivityCommentVO> getAll(){
		return dao.getAll();
	}
	
	public List<ActivityCommentVO> getByActCategoryId(String actCategoryId){
		return dao.getByActCategoryId(actCategoryId);
	}
	
	public static void main(String[] argv) {
//		
//		ActivityCommentService acs = new ActivityCommentService();
//		List<ActivityCommentVO> ls = acs.getByActId("A10004");
//		for(ActivityCommentVO acVO : ls) {
//			System.out.println(acVO.getActCommentId());
//		}
//		
//		
		
	}
	
	
	
	
}
