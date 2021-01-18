package com.extra_charges.model;

import java.util.List;

public class Extra_chargesService {

	private Extra_chargesDAO_interface dao;

	public Extra_chargesService() {
		dao = new Extra_chargesDAO();
	}

	public Extra_chargesVO addExtra_charges(String room_order_id,String item,Integer price) {

		Extra_chargesVO extra_chargesVO = new Extra_chargesVO();

		
		extra_chargesVO.setRoom_order_id(room_order_id);
		extra_chargesVO.setItem(item);
		extra_chargesVO.setPrice(price);

		dao.insert(extra_chargesVO);

		return extra_chargesVO;
	}

	public Extra_chargesVO updateExtra_charges(String extra_charges_id, String room_order_id,String item,Integer price) {

		Extra_chargesVO extra_chargesVO = new Extra_chargesVO();

		extra_chargesVO.setExtra_charges_id(extra_charges_id);
		extra_chargesVO.setRoom_order_id(room_order_id);
		extra_chargesVO.setItem(item);
		extra_chargesVO.setPrice(price);

		dao.update(extra_chargesVO);

		return extra_chargesVO;
	}

	public void deleteExtra_charges(String extra_charges_id) {
		dao.delete(extra_charges_id);
	}

	public Extra_chargesVO getOneExtra_charges(String extra_charges_id) {
		return dao.findByPrimaryKey(extra_charges_id);
	}

	public List<Extra_chargesVO> getAll() {
		return dao.getAll();
	}
	
	public List<Extra_chargesVO> getByRO(String room_order_id){
		return dao.getByRoomOrderId(room_order_id);
	}
	
	public List<Extra_chargesVO> getAllCheckIn() {
		return dao.getAllCheckIn();
	}
	
	public List<Extra_chargesVO> getAllCheckOut() {
		return dao.getAllCheckOut();
	}
	
	
	
	
	
}
