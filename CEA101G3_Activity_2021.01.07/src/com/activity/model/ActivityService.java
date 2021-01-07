package com.activity.model;

import java.util.List;
import java.util.Map;

public class ActivityService {

	private ActivityDAO_interface dao;

	public ActivityService() {
		dao = new ActivityDAO();
	}

	public ActivityVO addActivit(String actCategoryId, String actInfo, Integer actPrice,
			java.sql.Date actStartDate, java.sql.Date actEndDate, java.sql.Date actApplyOpen,
			java.sql.Date actApplyClose, Integer maxPeople, Integer minPeople, Integer actAlreadyApply, String actName,
			Integer actStatus, Double actDiscount, String actPromInfo, java.sql.Date actPromStartDate,
			java.sql.Date actPromCloseDate) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActCategoryId(actCategoryId);
		activityVO.setActInfo(actInfo);
		activityVO.setActPrice(actPrice);
		activityVO.setActStartDate(actStartDate);
		activityVO.setActEndDate(actEndDate);
		activityVO.setActApplyOpen(actApplyOpen);
		activityVO.setActApplyClose(actApplyClose);
		activityVO.setMaxProple(maxPeople);
		activityVO.setMinPeople(minPeople);
		activityVO.setActAlreadyApply(actAlreadyApply);
		activityVO.setActName(actName);
		activityVO.setActStatus(actStatus);
		activityVO.setActDiscount(actDiscount);
		activityVO.setActPromInfo(actPromInfo);
		activityVO.setActPromStartDate(actPromStartDate);
		activityVO.setActPromCloseDate(actPromCloseDate);

		dao.insert(activityVO);
		return activityVO;
	}

	public ActivityVO updateActivit(String actId, String actCategoryId, String actInfo, Integer actPrice,
			java.sql.Date actStartDate, java.sql.Date actEndDate, java.sql.Date actApplyOpen,
			java.sql.Date actApplyClose, Integer maxProple, Integer minProple, Integer actAlreadyApply, String actName,
			Integer actStatus, Double actDiscount, String actPromInfo, java.sql.Date actPromStartDate,
			java.sql.Date actPromCloseDate) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActId(actId);
		activityVO.setActCategoryId(actCategoryId);
		activityVO.setActInfo(actInfo);
		activityVO.setActPrice(actPrice);
		activityVO.setActStartDate(actStartDate);
		activityVO.setActEndDate(actEndDate);
		activityVO.setActApplyOpen(actApplyOpen);
		activityVO.setActApplyClose(actApplyClose);
		activityVO.setMaxProple(maxProple);
		activityVO.setMinPeople(minProple);
		activityVO.setActAlreadyApply(actAlreadyApply);
		activityVO.setActName(actName);
		activityVO.setActStatus(actStatus);
		activityVO.setActDiscount(actDiscount);
		activityVO.setActPromInfo(actPromInfo);
		activityVO.setActPromStartDate(actPromStartDate);
		activityVO.setActPromCloseDate(actPromCloseDate);

		dao.update(activityVO);
		return activityVO;
	}
	
	public ActivityVO getOneActivity(String actId) {
		return dao.findByPK(actId);
	}
	
	public List<ActivityVO> getAll(){
		return dao.getAll();
	}
	
	public List<ActivityVO> getSelect(Map<String, String[]> map){
		return dao.getAll(map);
	}
}
