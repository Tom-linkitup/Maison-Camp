package com.room_comment.model;

import java.util.List;



public class Room_commentService {


	private Room_commentDAO_interface dao;

	public Room_commentService() {
		dao = new Room_commentJDBCDAO();
	}

	public Room_commentVO addRoom_comment(String room_category_id,String room_comment_content, java.sql.Timestamp time,String comment_reply) {

		Room_commentVO room_commentVO = new Room_commentVO();
		
		room_commentVO.setRoom_category_id(room_category_id);
		room_commentVO.setRoom_comment_content(room_comment_content);
		room_commentVO.setTime(time);
		room_commentVO.setComment_reply(comment_reply);
		dao.insert(room_commentVO);

		return room_commentVO;
	}

	public Room_commentVO updateRoom_comment(String room_comment_id, String room_category_id,String room_comment_content, java.sql.Timestamp time,String comment_reply) {

		Room_commentVO room_commentVO = new Room_commentVO();

		room_commentVO.setRoom_comment_id(room_comment_id);
		room_commentVO.setRoom_category_id(room_category_id);
		room_commentVO.setRoom_comment_content(room_comment_content);
		room_commentVO.setTime(time);
		room_commentVO.setComment_reply(comment_reply);
		dao.update(room_commentVO);

		return room_commentVO;
	}

	public void deleteRoom_comment(String room_comment_id) {
		dao.delete(room_comment_id);
	}

	public Room_commentVO getOneRoom_comment(String room_comment_id) {
		return dao.findByPrimaryKey(room_comment_id);
	}

	public List<Room_commentVO> getAll() {
		return dao.getAll();
	}
	
	public List<Room_commentVO> getAllTwins() {
		return dao.getAllTwins();
	}
	public List<Room_commentVO> getAllDouble() {
		return dao.getAllDouble();
	}
	public List<Room_commentVO> getAllQuadruple() {
		return dao.getAllQuadruple();
	}
	public List<Room_commentVO> getAllReply() {
		return dao.getAllReply();
	}
	public List<Room_commentVO> getAllWaitReply() {
		return dao.getAllWaitReply();
	}
}
