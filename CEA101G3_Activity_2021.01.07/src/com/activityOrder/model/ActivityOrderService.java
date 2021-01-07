package com.activityOrder.model;


import java.util.*;
import java.util.Date;
import java.sql.*;

public class ActivityOrderService {

	private ActivityOrderDAO_interfact dao;
	
	public ActivityOrderService() {
		super();
		dao = new ActivityOrderDAO();
	}
	
	public ActivityOrderVO addActOrder(String actID, String memId, String note,
			Integer people, Integer actPrice, String payment,java.sql.Date createTime, Integer status){
		
		ActivityOrderVO aoVO = new ActivityOrderVO();
		
		aoVO.setActId(actID);
		aoVO.setMemId(memId);
		aoVO.setNote(note);
		aoVO.setPeople(people);
		aoVO.setActPrice(actPrice);
		aoVO.setPayment(payment);
		aoVO.setCreateTime(createTime);
		aoVO.setStatus(status);
		
		dao.insert(aoVO);
		
		
		return aoVO;
	}
	
	
	public ActivityOrderVO updateByActOrderId(String actOrderId,String actId, String memId, String note,
	Integer people, Integer actPrice, String payment,java.sql.Date createTime, Integer status) {
		
		ActivityOrderVO aoVO = new ActivityOrderVO();

		aoVO.setActOrderId(actOrderId);
		aoVO.setActId(actId);
		aoVO.setMemId(memId);
		aoVO.setNote(note);
		aoVO.setPeople(people);
		aoVO.setActPrice(actPrice);
		aoVO.setPayment(payment);
		aoVO.setCreateTime(createTime);
		aoVO.setStatus(status);
		
		dao.update(aoVO);
		
		
		return aoVO;
	}
	
	public void deletebyActOrderId(String actOrderId) {
		
		dao.delete(actOrderId);
	}
	
	public ActivityOrderVO findByActOrderID(String actOrderId) {
		
		ActivityOrderVO aoVO = new ActivityOrderVO();

		aoVO = dao.findByActOrderId(actOrderId);
		
		return aoVO;
	}
	
	
	public List<ActivityOrderVO> getAll(){
		
		return dao.getAll();
	}
	
	public List<ActivityOrderVO> findByActId(String actId){
		return dao.findByActId(actId);
	}
	
	
	
	public static void main(String[] argv) {
		
		
		
		//test 新稱活動訂單 OK
		ActivityOrderService aos = new ActivityOrderService();
		
//		java.util.Date date = new Date();
//		java.sql.Date CREATE_TIME = new java.sql.Date(date.getTime());
//		
//		 ActivityOrderVO aoVO = aos.addActOrder("A10009", "M10009", "速度要辣不要切", 5, 5555, "用身體償還"
//				,CREATE_TIME , 0);
//		 
//		System.out.println(aoVO.getActId());
//		System.out.println(aoVO.getCreateTime());
//		System.out.println(aoVO.getNote());
		
		
		
		// test 依照ACT_ORDER_ID查詢OK
		
//		ActivityOrderVO aoVO = new ActivityOrderVO();
//		
//		aoVO =  aos.findByActOrderID("AD10001");
//		
//		System.out.println(aoVO.getActOrderId());
//		System.out.println(aoVO.getActId());
//		System.out.println(aoVO.getMemId());
//		System.out.println(aoVO.getNote());
		
		
		// test update OK
		
		
//		GregorianCalendar cl = new GregorianCalendar(1993, 12 ,22);
//		
//
//		java.util.Date date = cl.getGregorianChange();
//		java.sql.Date createTime = new java.sql.Date(date.getTime());
//		
//		ActivityOrderVO aoVO = null;
//		
//		aoVO= aos.updateByActOrderId("AD10012", "A10003", "M10001", "小姐妳喜歡吃蔥嗎", 5, 81000, "用身體", createTime, 0);
//		
//		System.out.println(aoVO.getActOrderId());
//		System.out.println(aoVO.getMemId());
//		System.out.println(aoVO.getNote());
//		
//		ActivityOrderVO aoVO2 = new ActivityOrderVO();
//		aoVO2 = aos.findByActOrderID("AD10012");
//		
//		System.out.println(aoVO2.getMemId());
//		System.out.println(aoVO2.getNote());
	
//		//test 依活動編號查詢 OK
//		List<ActivityOrderVO> list = aos.findByActId("A10007");
//		System.out.println(list.size());
//		for(ActivityOrderVO aoVO : list) {
//			System.out.println(aoVO.getActOrderId());
//			System.out.println(aoVO.getActId());
//			System.out.println(aoVO.getMemId());
//			System.out.println(aoVO.getNote());
//		}
//		
		
		//test delete OK
		aos.deletebyActOrderId("AD10008");
		
	}
	
}
