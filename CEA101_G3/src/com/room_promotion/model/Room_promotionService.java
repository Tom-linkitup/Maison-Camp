package com.room_promotion.model;
import java.util.List;

public class Room_promotionService {	

	private Room_promotionDAO_interface dao;
	
		public Room_promotionService() {
			dao = new Room_promotionDAO();
		}

		public Room_promotionVO addRoom_promotion(String room_category_id, String room_promotion_info,
				 Double room_discount,java.sql.Date room_prom_start_date ,java.sql.Date room_prom_end_date) {

		Room_promotionVO room_promotionVO = new Room_promotionVO();
		room_promotionVO.setRoom_category_id(room_category_id);
		room_promotionVO.setRoom_promotion_info(room_promotion_info);
		room_promotionVO.setRoom_discount(room_discount);
		room_promotionVO.setRoom_prom_start_date(room_prom_start_date);
		room_promotionVO.setRoom_prom_end_date(room_prom_end_date);
		dao.insert(room_promotionVO);

			return room_promotionVO;
		}

		public Room_promotionVO updateRoom_promotion(String room_promotion_id, String room_category_id, String room_promotion_info,
				 Double room_discount,java.sql.Date room_prom_start_date ,java.sql.Date room_prom_end_date) {

			Room_promotionVO room_promotionVO = new Room_promotionVO();

			room_promotionVO.setRoom_promotion_id(room_promotion_id);
			room_promotionVO.setRoom_category_id(room_category_id);
			room_promotionVO.setRoom_promotion_info(room_promotion_info);
			room_promotionVO.setRoom_discount(room_discount);
			room_promotionVO.setRoom_prom_start_date(room_prom_start_date);
			room_promotionVO.setRoom_prom_end_date(room_prom_end_date);
		
			dao.update(room_promotionVO);

			return room_promotionVO;
		}

		public void deleteRoom_promotion(String room_promotion_id) {
			dao.delete(room_promotion_id);
		}

		public Room_promotionVO getOneRoom_promotion(String room_promotion_id) {
			return dao.findByPrimaryKey(room_promotion_id);
		}

		public List<Room_promotionVO> getAll() {
			return dao.getAll();
		}
		public List<Room_promotionVO> getAllPast() {
			return dao.getAllPast();
		}
		public List<Room_promotionVO> getAllNow() {
			return dao.getAllNow();
		}
		public List<Room_promotionVO> getAllFuture() {
			return dao.getAllFuture();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
