package com.item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemJDBCDAO implements ItemDAO_interface {
	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ITEM (item_id,item_category_id,item_name,item_info,item_price,item_status) VALUES ('I' || SEQ_ITEM_ID.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ONE_STMT = "SELECT * from item where item_id = ?";
	private static final String GET_ALL_STMT = "SELECT *  FROM ITEM order by item_id";
	private static final String DELETE = "DELETE FROM ITEM where item_id = ?";
	private static final String UPDATE = "UPDATE ITEM set item_category_id=?, item_name=?, item_info=?, item_price=?, item_status=? where item_id = ?";

	public void insert(ItemVO ItemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ItemVO.getItemCategoryId());
			pstmt.setString(2, ItemVO.getItemName());
			pstmt.setString(3, ItemVO.getItemInfo());
			pstmt.setDouble(4, ItemVO.getItemPrice());
			pstmt.setInt(5, ItemVO.getItemStatus());

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


	public void update(ItemVO ItemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ItemVO.getItemCategoryId());
			pstmt.setString(2, ItemVO.getItemName());
			pstmt.setString(3, ItemVO.getItemInfo());
			pstmt.setInt(4, ItemVO.getItemPrice());
			pstmt.setInt(5, ItemVO.getItemStatus());
			pstmt.setString(6, ItemVO.getItemId());
			
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


	public void delete(String itemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, itemId);

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

	public ItemVO findByPrimaryKey(String itemId) {

		ItemVO ItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itemId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ItemVO = new ItemVO();
				ItemVO.setItemId(rs.getString("item_id"));
				ItemVO.setItemCategoryId(rs.getString("item_category_id"));
				ItemVO.setItemName(rs.getString("item_name"));
				ItemVO.setItemInfo(rs.getString("item_info"));
				ItemVO.setItemPrice(rs.getInt("item_price"));
				ItemVO.setItemStatus(rs.getInt("item_status"));
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
		return ItemVO;
	}

	public List<ItemVO> getAll() {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO ItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ItemVO = new ItemVO();
				ItemVO.setItemId(rs.getString("item_id"));
				ItemVO.setItemCategoryId(rs.getString("item_category_id"));
				ItemVO.setItemName(rs.getString("item_name"));
				ItemVO.setItemInfo(rs.getString("item_info"));
				ItemVO.setItemPrice(rs.getInt("item_price"));
				ItemVO.setItemStatus(rs.getInt("item_status"));
				list.add(ItemVO); // Store the row in the list
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

		ItemJDBCDAO dao = new ItemJDBCDAO();

//		// 新增
//		ItemVO ItemVO1 = new ItemVO();
//				ItemVO1.setItemCategoryId("I008");
//				ItemVO1.setItemName("小寶寶");
//				ItemVO1.setItemInfo("讚!");
//				ItemVO1.setItemPrice(1000);
//				ItemVO1.setItemStatus(1);
//				dao.insert(ItemVO1);

		// 修改
//		ItemVO ItemVO2 = new ItemVO();
//		ItemVO2.setItemId("I10041");
//		ItemVO2.setItemCategoryId("I006");
//		ItemVO2.setItemName("酸白菜");
//		ItemVO2.setItemInfo("高冷大白菜10000公克");
//		ItemVO2.setItemPrice(1500);
//		ItemVO2.setItemStatus(0);
//		dao.update(ItemVO2);

//		// 刪除
//		dao.delete("I10024");		

//		// 查詢
//		ItemVO ItemVO3 = dao.findByPrimaryKey("I4");
//		System.out.print(ItemVO3.getItemId() + ",");
//		System.out.print(ItemVO3.getItemCategoryId() + ",");
//		System.out.print(ItemVO3.getItemName() + ",");
//		System.out.print(ItemVO3.getItemInfo() + ",");
//		System.out.print(ItemVO3.getItemPrice() + ",");
//		System.out.print(ItemVO3.getItemStatus() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<ItemVO> list = dao.getAll();
//		for (ItemVO aItem : list) {
//			System.out.print(aItem.getItemId() + ",");
//			System.out.print(aItem.getItemCategoryId() + ",");
//			System.out.print(aItem.getItemName() + ",");
//			System.out.print(aItem.getItemInfo() + ",");
//			System.out.print(aItem.getItemPrice() + ",");
//			System.out.print(aItem.getItemStatus() + ",");
//			System.out.println();
//		}

	}
}
