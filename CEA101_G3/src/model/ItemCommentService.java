package com.item_comment.model;

import java.util.List;

public class ItemCommentService {
	private ItemCommentDAO_interface dao;
	
	public ItemCommentService() {
		dao = new ItemCommentDAO();
	}
	
	public ItemCommentVO addItemComment( String itemId,String shopComment, java.sql.Timestamp time) {
		ItemCommentVO ItemCommentVO = new ItemCommentVO();

		ItemCommentVO.setItemId(itemId);
		ItemCommentVO.setShopComment(shopComment);
		ItemCommentVO.setTime(time);
		dao.insert(ItemCommentVO);

		return ItemCommentVO;
	}
	
	public ItemCommentVO updateItemComment(String itemCommentId, String itemId,String shopComment, java.sql.Timestamp time) {

		ItemCommentVO ItemCommentVO = new ItemCommentVO();

		ItemCommentVO.setItemCommentId(itemCommentId);
		ItemCommentVO.setItemId(itemId);
		ItemCommentVO.setShopComment(shopComment);
		ItemCommentVO.setTime(time);
		dao.update(ItemCommentVO);

		return ItemCommentVO;
	}
	
	public void deleteItemComment(String itemCommentId) {
		dao.delete(itemCommentId);
	}
	
	public ItemCommentVO getOneItemComment(String itemCommentId) {
		return dao.findByPrimaryKey(itemCommentId);
	}
	
	public List<ItemCommentVO> getAll(){
		return dao.getAll();
	}
}
