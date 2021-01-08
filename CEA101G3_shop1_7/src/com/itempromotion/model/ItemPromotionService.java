package com.itempromotion.model;

import java.sql.Date;
import java.util.List;

public class ItemPromotionService {

	private ItemPromotionDAO_interface dao;

	public ItemPromotionService() {
		dao = new ItemPromotionJDBCDAO();
	}

	public ItemPromotionVO addItemPromotion(String item_id, String item_promotion_info, Float item_discount,
			Date item_prom_start_date, Date item_prom_close_date) {

		ItemPromotionVO itemPromotionVO = new ItemPromotionVO();

		itemPromotionVO.setItem_id(item_id);
		itemPromotionVO.setItem_promotion_info(item_promotion_info);
		itemPromotionVO.setItem_discount(item_discount);
		itemPromotionVO.setItem_prom_start_date(item_prom_start_date);
		itemPromotionVO.setItem_prom_close_date(item_prom_close_date);
		dao.insert(itemPromotionVO);

		return itemPromotionVO;
	}

	
	public ItemPromotionVO updateItemPromotion(String item_promotion_id, String item_id, String item_promotion_info,
			Float item_discount, Date item_prom_start_date, Date item_prom_close_date) {

		ItemPromotionVO itemPromotionVO = new ItemPromotionVO();

		itemPromotionVO.setItem_id(item_id);
		itemPromotionVO.setItem_promotion_info(item_promotion_info);
		itemPromotionVO.setItem_discount(item_discount);
		itemPromotionVO.setItem_prom_start_date(item_prom_start_date);
		itemPromotionVO.setItem_prom_close_date(item_prom_close_date);
		itemPromotionVO.setItem_promotion_id(item_promotion_id);
		dao.update(itemPromotionVO);

		return itemPromotionVO;
	}
	
	public void deleteItemPromotion(String item_promotion_id) {
		dao.delete(item_promotion_id);
	}
	
	public ItemPromotionVO getOneItemPromotionVO(String item_promotion_id) {
		return dao.findByPrimaryKey(item_promotion_id);
	}
	
	public List<ItemPromotionVO> getAll(){
		return dao.getAll();
	}
}
