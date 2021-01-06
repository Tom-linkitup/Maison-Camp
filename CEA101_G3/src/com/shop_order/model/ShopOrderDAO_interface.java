package com.shop_order.model;

import java.util.*;

public interface ShopOrderDAO_interface {
	public void insert(ShopOrderVO shop_orderVO);
	public void update(ShopOrderVO shop_orderVO);
	public void delete(String shop_order_id);
	public ShopOrderVO  findByPrimaryKey(String shop_order_id);
	public List<ShopOrderVO> getAll();
	
}
