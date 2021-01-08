package com.shop_order.model;

import java.util.*;

import com.shop_order_detail.model.ShopOrderDetailVO;

public interface ShopOrderDAO_interface {
	public void insert(ShopOrderVO shop_orderVO);
	public void update(ShopOrderVO shop_orderVO);
	public void delete(String shop_order_id);
	public ShopOrderVO  findByPrimaryKey(String shop_order_id);
	public List<ShopOrderVO> getAll();
	
	public void insertWithOrderDetail(ShopOrderVO shopOrderVO , List<ShopOrderDetailVO> list);
}
