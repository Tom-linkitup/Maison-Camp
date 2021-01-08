package com.itempromotion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop_order.model.ShopOrderJDBCDAO;
import com.shop_order.model.ShopOrderVO;

public class ItemPromotionJDBCDAO implements ItemPromotionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String password = "123456";

	public static final String INSERT_STMT = "INSERT INTO ITEM_PROMOTION (ITEM_PROMOTION_ID, ITEM_ID, ITEM_PROMOTION_INFO, ITEM_DISCOUNT, ITEM_PROM_START_DATE, ITEM_PROM_CLOSE_DATE)"
			+ "VALUES('IP'|| ITEM_PROMOTION_ID_SEQ.NEXTVAL,?,?,?,?,?)";
	public static final String GET_ALL_STMT = "SELECT * FROM ITEM_PROMOTION ORDER BY ITEM_PROMOTION_ID";
	public static final String GET_ONE_STMT = "SELECT * FROM ITEM_PROMOTION WHERE ITEM_PROMOTION_ID=?";
	public static final String DELETE = "DELETE FROM ITEM_PROMOTION WHERE ITEM_PROMOTION_ID = ?";
	public static final String UPDATE = "UPDATE ITEM_PROMOTION SET ITEM_ID=?, ITEM_PROMOTION_INFO=?, ITEM_DISCOUNT=?, ITEM_PROM_START_DATE=?, ITEM_PROM_CLOSE_DATE=? WHERE ITEM_PROMOTION_ID = ?";

	public void insert(ItemPromotionVO itemPromotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itemPromotionVO.getItem_id());
			pstmt.setString(2, itemPromotionVO.getItem_promotion_info());
			pstmt.setFloat(3, itemPromotionVO.getItem_discount());
			pstmt.setDate(4, itemPromotionVO.getItem_prom_start_date());
			pstmt.setDate(5, itemPromotionVO.getItem_prom_close_date());

			pstmt.executeUpdate();

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

	public void update(ItemPromotionVO itemPromotionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, itemPromotionVO.getItem_id());
			pstmt.setString(2, itemPromotionVO.getItem_promotion_info());
			pstmt.setFloat(3, itemPromotionVO.getItem_discount());
			pstmt.setDate(4, itemPromotionVO.getItem_prom_start_date());
			pstmt.setDate(5, itemPromotionVO.getItem_prom_close_date());
			pstmt.setString(6, itemPromotionVO.getItem_promotion_id());

			pstmt.executeUpdate();

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

	public void delete(String item_promotion_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, item_promotion_id);

			pstmt.executeUpdate();
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

	public ItemPromotionVO findByPrimaryKey(String item_promotion_id) {

		ItemPromotionVO itemPromotionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, item_promotion_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemPromotionVO = new ItemPromotionVO();
				itemPromotionVO.setItem_promotion_id(rs.getString("item_promotion_id"));
				itemPromotionVO.setItem_id(rs.getString(2));
				itemPromotionVO.setItem_promotion_info(rs.getString(3));
				itemPromotionVO.setItem_discount(rs.getFloat(4));
				itemPromotionVO.setItem_prom_start_date(rs.getDate(5));
				itemPromotionVO.setItem_prom_close_date(rs.getDate(6));
			}

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
		return itemPromotionVO;
	}

	public List<ItemPromotionVO> getAll() {
		List<ItemPromotionVO> list = new ArrayList<ItemPromotionVO>();
		ItemPromotionVO itemPromotionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemPromotionVO = new ItemPromotionVO();

				itemPromotionVO.setItem_promotion_id(rs.getString("item_promotion_id"));
				itemPromotionVO.setItem_id(rs.getString("item_id"));
				itemPromotionVO.setItem_promotion_info(rs.getString("item_promotion_info"));
				itemPromotionVO.setItem_discount(rs.getFloat("item_discount"));
				itemPromotionVO.setItem_prom_start_date(rs.getDate("item_prom_start_date"));
				itemPromotionVO.setItem_prom_close_date(rs.getDate("item_prom_close_date"));
				list.add(itemPromotionVO);
			}

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
		ItemPromotionJDBCDAO dao = new ItemPromotionJDBCDAO();
		ItemPromotionVO svo = new ItemPromotionVO();

		// 新增
//		svo.setItem_id("I10001");
//		svo.setItem_promotion_info("�t�ϸ`");
//		svo.setItem_discount(0.7F);
//		svo.setItem_prom_start_date(java.sql.Date.valueOf("1996-01-01"));
//		svo.setItem_prom_close_date(java.sql.Date.valueOf("1996-01-30"));
//		dao.insert(svo);
		// 修改
//		svo.setItem_id("I10201");
//		svo.setItem_promotion_info("�U�t�`");
//		svo.setItem_discount(0.9F);
//		svo.setItem_prom_start_date(java.sql.Date.valueOf("1996-01-01"));
//		svo.setItem_prom_close_date(java.sql.Date.valueOf("1996-02-28"));
//		svo.setItem_promotion_id("IP3");
//		dao.update(svo);
		// 刪除
//		dao.delete("IP2");
		// 查詢一
//			svo = dao.findByPrimaryKey("IP3");
//			System.out.print(svo.getItem_promotion_id()+ ",");
//			System.out.print(svo.getItem_id()+ ",");
//			System.out.print(svo.getItem_promotion_info()+ ",");
//			System.out.print(svo.getItem_discount()+ ",");
//			System.out.print(svo.getItem_prom_start_date()+ ",");
//			System.out.print(svo.getItem_prom_close_date()+ ",");
//			System.out.println();
		
		// 查詢全部
		
//		List<ItemPromotionVO> list = dao.getAll();
//		for(ItemPromotionVO ipvo : list) {
//			System.out.print(ipvo.getItem_promotion_id()+ ",");
//			System.out.print(ipvo.getItem_id()+ ",");
//			System.out.print(ipvo.getItem_promotion_info()+ ",");
//			System.out.print(ipvo.getItem_discount()+ ",");
//			System.out.print(ipvo.getItem_prom_start_date()+ ",");
//			System.out.print(ipvo.getItem_prom_close_date()+ ",");
//			System.out.println();
//		}
	}
}
