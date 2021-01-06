package com.item_comment.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.item.model.ItemJDBCDAO;
import com.item.model.ItemVO;

public class ItemCommentJDBCDAO implements ItemCommentDAO_interface {

	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ITEM_COMMENT (item_comment_id,item_id,shop_comment,time) VALUES ('IC' || ITEM_COMMENT_SEQ.NEXTVAL, ?, ?, ?)";
	private static final String UPDATE = "UPDATE ITEM_COMMENT set item_id=?, shop_comment=?, time=? where item_comment_id = ?";
	private static final String DELETE = "DELETE FROM ITEM_COMMENT where item_comment_id = ?";
	private static final String GET_ONE_STMT = "SELECT * from ITEM_COMMENT where item_comment_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM ITEM_COMMENT order by item_comment_id";
	
	public void insert(ItemCommentVO ItemCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ItemCommentVO.getItemId());
			pstmt.setString(2, ItemCommentVO.getShopComment());
			pstmt.setTimestamp(3, ItemCommentVO.getTime());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update(ItemCommentVO ItemCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			

			pstmt.setString(1, ItemCommentVO.getItemId());
			pstmt.setString(2, ItemCommentVO.getShopComment());
			pstmt.setTimestamp(3, ItemCommentVO.getTime());
			pstmt.setString(4, ItemCommentVO.getItemCommentId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void delete(String itemCommentId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, itemCommentId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public ItemCommentVO findByPrimaryKey(String itemCommentId) {

		ItemCommentVO ItemCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itemCommentId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ItemCommentVO = new ItemCommentVO();
				ItemCommentVO.setItemCommentId(rs.getString("item_comment_id"));
				ItemCommentVO.setItemId(rs.getString("item_id"));
				ItemCommentVO.setShopComment(rs.getString("shop_comment"));
				ItemCommentVO.setTime(rs.getTimestamp("time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return ItemCommentVO;
	}

	public List<ItemCommentVO> getAll() {
		List<ItemCommentVO> list = new ArrayList<ItemCommentVO>();
		ItemCommentVO ItemCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ItemCommentVO = new ItemCommentVO();
				ItemCommentVO.setItemCommentId(rs.getString("item_comment_id"));
				ItemCommentVO.setItemId(rs.getString("item_id"));
				ItemCommentVO.setShopComment(rs.getString("shop_comment"));
				ItemCommentVO.setTime(rs.getTimestamp("time"));
				list.add(ItemCommentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {

		ItemCommentJDBCDAO dao = new ItemCommentJDBCDAO();

//		// 新增

//		ItemCommentVO ItemCommentVO1 = new ItemCommentVO();
//				ItemCommentVO1.setItemId("I10005");
//				ItemCommentVO1.setShopComment("非常棒，超好喝");
//				ItemCommentVO1.setTime(java.sql.Timestamp.valueOf("2020-12-3 11:3:50"));
//				dao.insert(ItemCommentVO1);

		// 修改
//		ItemCommentVO ItemCommentVO2 = new ItemCommentVO();
//		ItemCommentVO2.setItemCommentId("IC");
//		ItemCommentVO2.setItemId("I10005");
//		ItemCommentVO2.setShopComment("test!!!");
//		ItemCommentVO2.setTime(java.sql.Timestamp.valueOf("2020-12-10"));
//		dao.update(ItemCommentVO2);

//		// 刪除
//		dao.delete("IC2");		

//		// 查詢
//		ItemCommentVO ItemCommentVO3 = dao.findByPrimaryKey("IC3");
//		System.out.print(ItemCommentVO3.getItemCommentId() + ",");
//		System.out.print(ItemCommentVO3.getItemId() + ",");
//		System.out.print(ItemCommentVO3.getShopComment() + ",");
//		System.out.print(ItemCommentVO3.getTime() + ",");
//
//		System.out.println("---------------------");

		// 查詢
		List<ItemCommentVO> list = dao.getAll();
//		for (ItemCommentVO aItemComment : list) {
//			System.out.print(aItemComment.getItemCommentId() + ",");
//			System.out.print(aItemComment.getItemId() + ",");
//			System.out.print(aItemComment.getShopComment() + ",");
//			System.out.print(aItemComment.getTime() + ",");
//			System.out.println();
//		}

	}

}
