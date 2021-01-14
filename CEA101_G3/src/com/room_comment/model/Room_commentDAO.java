package com.room_comment.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.room.model.RoomVO;

import java.sql.*;

public class Room_commentDAO implements Room_commentDAO_interface {
	//連線池
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new javax.naming.InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GDB");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}

	private static final String INSERT_STMT = 
		"INSERT INTO room_comment(room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id)"
		+ "VALUES ('RC' || room_comment_id_seq.NEXTVAL, ?, ?, current_TimeStamp,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id FROM room_comment order by room_comment_id desc";
	private static final String GET_ONE_STMT = 
		"SELECT room_comment_id,room_category_id,room_comment_content, time,comment_reply,room_order_id FROM room_comment where room_comment_id = ?";
	private static final String DELETE = 
		"DELETE FROM room_comment where room_comment_id = ?";
	private static final String UPDATE = 
		"UPDATE room_comment set room_category_id=?, room_comment_content=?, time = ? , comment_reply=? ,room_order_id =? where room_comment_id = ?";

	private static final String GETALLTWINS=
			"SELECT room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id FROM room_comment where room_category_id =any (select  room_category_id from room_comment where room_category_id='TWINS') order by time";
	
	private static final String GETALLDOUBLE=
			"SELECT room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id FROM room_comment where room_category_id =any (select  room_category_id from room_comment where room_category_id='DOUBLE') order by time";
	
	private static final String GETALLQUADRUPLE=
			"SELECT room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id FROM room_comment where room_category_id =any (select  room_category_id from room_comment where room_category_id='QUADRUPLE') order by time";
	
	private static final String GETALLREPLY=
	"SELECT room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id FROM room_comment where comment_reply =any (select  comment_reply from room_comment where comment_reply is not null) order by time desc";
	
	private static final String GETALLWAITREPLY=
	"SELECT room_comment_id,room_category_id,room_comment_content,time,comment_reply,room_order_id FROM room_comment where comment_reply is null order by time desc";
	
	private static final String Get_By_RCT = "SELECT ROOM_COMMENT_ID, ROOM_CATEGORY_ID, ROOM_COMMENT_CONTENT,TIME,COMMENT_REPLY ,ROOM_ORDER_ID FROM ROOM_COMMENT WHERE ROOM_CATEGORY_ID=? ORDER BY TIME desc";
	
	
	@Override
	public void insert(Room_commentVO room_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, room_commentVO.getRoom_category_id());
			pstmt.setString(2, room_commentVO.getRoom_comment_content());
			pstmt.setTimestamp(3, room_commentVO.getTime());
			pstmt.setString(4, room_commentVO.getComment_reply());
			pstmt.setString(5, room_commentVO.getRoom_order_id());
			
				
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Room_commentVO room_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, room_commentVO.getRoom_category_id());
			pstmt.setString(2, room_commentVO.getRoom_comment_content());
			pstmt.setTimestamp(3, room_commentVO.getTime());
			pstmt.setString(4, room_commentVO.getComment_reply());
			pstmt.setString(5, room_commentVO.getRoom_order_id());
			pstmt.setString(6, room_commentVO.getRoom_comment_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String room_comment_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, room_comment_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Room_commentVO findByPrimaryKey(String room_comment_id) {

		Room_commentVO room_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, room_comment_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// room_commentVo 銋迂� Domain objects
				room_commentVO = new Room_commentVO();
				room_commentVO.setRoom_comment_id(rs.getString("room_comment_id"));
				room_commentVO.setRoom_order_id(rs.getString("room_order_id"));
				room_commentVO.setRoom_category_id(rs.getString("room_category_id"));
				room_commentVO.setRoom_comment_content(rs.getString("room_comment_content"));
				room_commentVO.setTime(rs.getTimestamp("time"));
				room_commentVO.setComment_reply(rs.getString("comment_reply"));
				
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return room_commentVO;
	}

	@Override
	public List<Room_commentVO> getAll() {
		List<Room_commentVO> list = new ArrayList<Room_commentVO>();
		Room_commentVO room_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// room_commentVO 銋迂� Domain objects
				room_commentVO = new Room_commentVO();
				room_commentVO.setRoom_comment_id(rs.getString("room_comment_id"));
				room_commentVO.setRoom_order_id(rs.getString("room_order_id"));
				room_commentVO.setRoom_category_id(rs.getString("room_category_id"));
				room_commentVO.setRoom_comment_content(rs.getString("room_comment_content"));
				room_commentVO.setTime(rs.getTimestamp("time"));
				room_commentVO.setComment_reply(rs.getString("comment_reply"));
				list.add(room_commentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
	
	@Override
	public List<Room_commentVO> getAllReply() {
		List<Room_commentVO> list = new ArrayList<Room_commentVO>();
		Room_commentVO room_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLREPLY);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// room_commentVO 銋迂� Domain objects
				room_commentVO = new Room_commentVO();
				room_commentVO.setRoom_comment_id(rs.getString("room_comment_id"));
				room_commentVO.setRoom_order_id(rs.getString("room_order_id"));
				room_commentVO.setRoom_category_id(rs.getString("room_category_id"));
				room_commentVO.setRoom_comment_content(rs.getString("room_comment_content"));
				room_commentVO.setTime(rs.getTimestamp("time"));
				room_commentVO.setComment_reply(rs.getString("comment_reply"));
				list.add(room_commentVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
	@Override
	public List<Room_commentVO> getAllWaitReply() {
		List<Room_commentVO> list = new ArrayList<Room_commentVO>();
		Room_commentVO room_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLWAITREPLY);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// room_commentVO 銋迂� Domain objects
				room_commentVO = new Room_commentVO();
				room_commentVO.setRoom_comment_id(rs.getString("room_comment_id"));
				room_commentVO.setRoom_order_id(rs.getString("room_order_id"));
				room_commentVO.setRoom_category_id(rs.getString("room_category_id"));
				room_commentVO.setRoom_comment_content(rs.getString("room_comment_content"));
				room_commentVO.setTime(rs.getTimestamp("time"));
				room_commentVO.setComment_reply(rs.getString("comment_reply"));
				list.add(room_commentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public List<Room_commentVO> getByRoomCategoryId(String room_category_id) {
		List<Room_commentVO> room_commentList = new ArrayList<>();
		Room_commentVO room_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_By_RCT);
			
			pstmt.setString(1, room_category_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				room_commentVO = new Room_commentVO();
				room_commentVO.setRoom_comment_id(rs.getString("ROOM_COMMENT_ID"));
				room_commentVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				room_commentVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				room_commentVO.setRoom_comment_content(rs.getString("ROOM_COMMENT_CONTENT"));
				room_commentVO.setTime(rs.getTimestamp("TIME"));
				room_commentVO.setComment_reply(rs.getString("COMMENT_REPLY"));
				room_commentList.add(room_commentVO);
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
		return room_commentList;
	}

	public static void main(String[] args) {

		Room_commentDAO dao = new Room_commentDAO();
		
		// �憓�
		
//		Room_commentVO room_commentVO1 = new Room_commentVO();
//		room_commentVO1.setRoom_category_id("TWINS");
//		room_commentVO1.setRoom_comment_content("好棒");
//		room_commentVO1.setTime(java.sql.Timestamp.valueOf("2021-12-29 13:32:02.123456789"));
//		room_commentVO1.setComment_reply("謝謝");
//		dao.insert(room_commentVO1);
		
//		 java.sql.Timestamp.valueOf
//		// 靽格
//		Room_commentVO room_commentVO2 = new Room_commentVO();
//		room_commentVO2.setRoom_comment_id("RC10002");
//		room_commentVO2.setRoom_category_id("DOUBLE");
//		room_commentVO2.setRoom_comment_content("==");
//		room_commentVO2.setTime(java.sql.Timestamp.valueOf("2020-12-22 13:20:00"));
//		dao.update(room_commentVO2);
//
//		// ��
//		dao.delete("RC4");
//
//		// �閰�
//		Room_commentVO room_commentVO3 = dao.findByPrimaryKey("RC10001");
//		System.out.print(room_commentVO3.getRoom_comment_id() + ",");
//		System.out.print(room_commentVO3.getRoom_category_id() + ",");
//		System.out.print(room_commentVO3.getRoom_comment_content() + ",");
//		System.out.print(room_commentVO3.getTime() + ",");
//		System.out.println("---------------------");
////
////		// �閰�
		List<Room_commentVO> list = dao.getAll(); 
		for (Room_commentVO aRoom_comment : list) {
			System.out.print(aRoom_comment.getRoom_comment_id() + ",");
			System.out.print(aRoom_comment.getRoom_category_id() + ",");
			System.out.print(aRoom_comment.getRoom_comment_content() + ",");
			System.out.print(aRoom_comment.getTime() + ",");
			System.out.print(aRoom_comment.getComment_reply() + ",");
			System.out.println();
		}
	}
}