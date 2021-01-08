package com.item.model;

import java.util.List;

import javax.servlet.http.HttpServlet;

public class ItemService {
	
	private ItemDAO_interface dao;
	
	public ItemService() {
		dao = new ItemJDBCDAO();
	}
	
	public ItemVO addItem(String itemCategoryId, String itemName, String itemInfo, Integer itemPrice, Integer itemStatus) {
		ItemVO ItemVO = new ItemVO();

		ItemVO.setItemCategoryId(itemCategoryId);
		ItemVO.setItemName(itemName);
		ItemVO.setItemInfo(itemInfo);
		ItemVO.setItemPrice(itemPrice);
		ItemVO.setItemStatus(itemStatus);
		dao.insert(ItemVO);

		return ItemVO;
	}
	
	public ItemVO updateItem(String itemId, String itemCategoryId, String itemName, String itemInfo, Integer itemPrice, Integer itemStatus) {

		ItemVO ItemVO = new ItemVO();

		ItemVO.setItemId(itemId);
		ItemVO.setItemCategoryId(itemCategoryId);
		ItemVO.setItemName(itemName);
		ItemVO.setItemInfo(itemInfo);
		ItemVO.setItemPrice(itemPrice);
		ItemVO.setItemStatus(itemStatus);
		dao.update(ItemVO);

		return ItemVO;
	}
	
	public void deleteItem(String itemId) {
		dao.delete(itemId);
	}
	
	public ItemVO getOneItem(String itemId) {
		return dao.findByPrimaryKey(itemId);
	}
	
	public List<ItemVO> getAll(){
		return dao.getAll();
	}
	
	public List<ItemVO> getByCat(String itemCategoryId){
		return dao.getByCategory(itemCategoryId);
	}
 }
