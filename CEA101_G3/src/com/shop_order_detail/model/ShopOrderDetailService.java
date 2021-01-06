package com.shop_order_detail.model;

import java.sql.Date;
import java.util.List;

import com.shop_order.model.ShopOrderJDBCDAO;

public class ShopOrderDetailService {

	private ShopOrderDetailDAO_interface dao;
	
	public ShopOrderDetailService() {
		dao = new ShopOrderDetailJDBCDAO();
	}

	public ShopOrderDetailVO addShopOrderDetail(String item_id, String item_promotion_id, String note, Integer quantity, Integer item_price) {
		
		ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();

		
		shopOrderDetailVO.setItem_id(item_id);
		shopOrderDetailVO.setItem_promotion_id(item_promotion_id);
		shopOrderDetailVO.setNote(note);
		shopOrderDetailVO.setQuantity(quantity);
		shopOrderDetailVO.setItem_price(item_price);
		dao.insert(shopOrderDetailVO);
		return shopOrderDetailVO;
		
	}
	
	public ShopOrderDetailVO updateShopOrderDetail(String shop_order_id, String item_id, String item_promotion_id, String note, Integer quantity, Integer item_price) {
		
		ShopOrderDetailVO shopOrderDetailVO = new ShopOrderDetailVO();
		
		shopOrderDetailVO.setShop_order_id(shop_order_id);
		shopOrderDetailVO.setItem_id(item_id);
		shopOrderDetailVO.setItem_promotion_id(item_promotion_id);
		shopOrderDetailVO.setNote(note);
		shopOrderDetailVO.setQuantity(quantity);
		shopOrderDetailVO.setItem_price(item_price);
		dao.update(shopOrderDetailVO);
		
		return shopOrderDetailVO;
	}

	
	public void deleteShopOrderDetail(String shop_order_id) {
		dao.delete(shop_order_id);
	}
	public ShopOrderDetailVO getOneShopOrderDetail(String shop_order_id){
		return dao.findByPrimaryKey(shop_order_id);
	}
	public List<ShopOrderDetailVO> getAll(){
		return dao.getAll();
	}
}
