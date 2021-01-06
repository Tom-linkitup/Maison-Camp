package com.shop_order.model;

import java.sql.Date;
import java.util.List;

public class ShopOrderService {

	private ShopOrderDAO_interface dao;

	public ShopOrderService() {
		dao = new ShopOrderJDBCDAO();
	}

	public ShopOrderVO addShopOrder(String mem_id, String payment, Date time,
			Integer shop_total_amount, Integer status) {

		ShopOrderVO shopOrderVO = new ShopOrderVO();

		shopOrderVO.setMem_id(mem_id);
		shopOrderVO.setPayment(payment);
		shopOrderVO.setTime(time);
		shopOrderVO.setShop_total_amount(shop_total_amount);
		shopOrderVO.setStatus(status);
		dao.insert(shopOrderVO);

		return shopOrderVO;
	}

	public ShopOrderVO updateShopOrder(String mem_id, String payment, Date time, Integer shop_total_amount,
			Integer status, String shop_order_id) {

		ShopOrderVO shopOrderVO = new ShopOrderVO();

		shopOrderVO.setShop_order_id(shop_order_id);
		shopOrderVO.setMem_id(mem_id);
		shopOrderVO.setPayment(payment);
		shopOrderVO.setTime(time);
		shopOrderVO.setShop_total_amount(shop_total_amount);
		shopOrderVO.setStatus(status);
		dao.update(shopOrderVO);
		return shopOrderVO;

	}
	
	public void deleteShopOrder(String shop_order_id) {
		dao.delete(shop_order_id);
	}
	
	public ShopOrderVO getOneShopOrder(String shop_order_id) {
		return dao.findByPrimaryKey(shop_order_id);
	}
	
	public List<ShopOrderVO> getAll(){
		return dao.getAll();
	}
	
}
