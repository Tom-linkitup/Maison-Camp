package com.shop_order_detail.model;

import java.util.*;

public interface ShopOrderDetailDAO_interface {
	public void insert(ShopOrderDetailVO shopOrderDetailVO);
	public void update(ShopOrderDetailVO shopOrderDetailVO);
	public void delete(String shop_order_id);
	public List<ShopOrderDetailVO> findByPrimaryKey(String shop_order_id);
	public List<ShopOrderDetailVO> getAll();
	
	public void insert2 (ShopOrderDetailVO shopOrderDetailVO , java.sql.Connection con);
}
