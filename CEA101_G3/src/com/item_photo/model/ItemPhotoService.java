package com.item_photo.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.item_category.model.ItemCategoryJDBCDAO;
import com.item_category.model.ItemCategoryVO;


public class ItemPhotoService {
	private ItemPhotoDAO_interface dao;
	
	public ItemPhotoService() {
		dao = new ItemPhotoDAO();
	}
	
	public ItemPhotoVO addItemPhoto(String itemId, byte[] content) {
		ItemPhotoVO ItemPhotoVO = new ItemPhotoVO();

		ItemPhotoVO.setItemId(itemId);
		ItemPhotoVO.setContent(content);
		dao.insert(ItemPhotoVO);

		return ItemPhotoVO;
	}
	
	public ItemPhotoVO updateItemPhoto(String itemPhotoId, String itemId, byte[] content) {

		ItemPhotoVO ItemPhotoVO = new ItemPhotoVO();
		
		ItemPhotoVO.setItemPhotoId(itemPhotoId);
		ItemPhotoVO.setItemId(itemId);
		ItemPhotoVO.setContent(content);
		dao.update(ItemPhotoVO);

		return ItemPhotoVO;
	}

	public void deleteItemPhoto(String itemPhotoId) {
		dao.delete(itemPhotoId);
	}
	
	public ItemPhotoVO getOneItemPhoto(String itemPhotoId) {
		return dao.findByPrimaryKey(itemPhotoId);
	}
	
	public ItemPhotoVO getOneByItemId(String itemId) {
		return dao.findByItemId(itemId);
	}
	
	public List<ItemPhotoVO> getAll(String item_id){
		return dao.getAll(item_id);
	}
}
