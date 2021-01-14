package com.item_category.model;

import java.util.List;

public class ItemCategoryService {
	private ItemCategoryDAO_interface dao;

	public ItemCategoryService() {
		dao = new ItemCategoryDAO();
	}

	public ItemCategoryVO addItemCategory(String itemCategoryId, String itemCategoryName) {
		ItemCategoryVO ItemCategoryVO = new ItemCategoryVO();

		ItemCategoryVO.setItemCategoryId(itemCategoryId);
		ItemCategoryVO.setItemCategoryName(itemCategoryName);
		dao.insert(ItemCategoryVO);

		return ItemCategoryVO;
	}

	public ItemCategoryVO updateItemCategory(String itemCategoryId, String itemCategoryName) {

		ItemCategoryVO ItemCategoryVO = new ItemCategoryVO();

		ItemCategoryVO.setItemCategoryId(itemCategoryId);
		ItemCategoryVO.setItemCategoryName(itemCategoryName);
		dao.update(ItemCategoryVO);

		return ItemCategoryVO;
	}

	public void deleteItemCategory(String itemCategoryId) {
		dao.delete(itemCategoryId);
	}
	
	public ItemCategoryVO getOneItemCategory(String itemCategoryId) {
		return dao.findByPrimaryKey(itemCategoryId);
	}
	
	public List<ItemCategoryVO> getAll(){
		return dao.getAll();
	}
}
