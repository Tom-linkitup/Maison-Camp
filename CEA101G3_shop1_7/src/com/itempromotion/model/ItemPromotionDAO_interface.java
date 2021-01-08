package com.itempromotion.model;

import java.util.*;

public interface ItemPromotionDAO_interface {
	public void insert(ItemPromotionVO itemPromotionVO);
	public void update(ItemPromotionVO itemPromotionVO);
	public void delete(String itemPromotionId);
	public ItemPromotionVO findByPrimaryKey(String itemPromotion_id);
	public List<ItemPromotionVO> getAll();
}
