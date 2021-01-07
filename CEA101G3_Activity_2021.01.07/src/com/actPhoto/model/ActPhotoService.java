package com.actPhoto.model;

import java.util.List;

public class ActPhotoService {

	private ActPhotoDAO_interface dao;
	
	public ActPhotoService() {
		dao = new ActPhotoDAO();
	}
	
	public ActPhotoVO addActPhoto(String ActId, byte[] content) {
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		

		actPhotoVO.setActId(ActId);	
		actPhotoVO.setContent(content);
		
		dao.insert(actPhotoVO);
		
		return actPhotoVO;
	}
	
	public ActPhotoVO updateActPhoto(String actPhotoId, String actId, byte[] content) {
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		
		actPhotoVO.setActPhotoId(actPhotoId);
		actPhotoVO.setActId(actId);	
		actPhotoVO.setContent(content);
		
		dao.update(actPhotoVO);
		
		return actPhotoVO;
	};
	
	public void deletActPhoto(String actPhotoId) {

		
		dao.deleteByActPhotoId(actPhotoId);;
	}
	
	public ActPhotoVO findByActPhotoId(String actPhotoId) {
		ActPhotoVO actPhotoVO = new ActPhotoVO();
		
		actPhotoVO = dao.findByActPhotoId(actPhotoId);
		
		return actPhotoVO;
	};
	
	public List<ActPhotoVO> getAll(){
		
		return dao.getAll();
	}
	
	public List<ActPhotoVO> getByActId(String actId){
		
		return dao.getByActId(actId);
	}
	
	
	
	
	public static void main(String[] ar) {
		
//		ActPhotoService as = new ActPhotoService();
//		ActPhotoVO apVO = null;
//		apVO = as.findByACT_PHOTO_ID("APH20");
//		
//		System.out.println(apVO.getACT_PHOTO_ID());
		
	}
	
	
	
	
}
