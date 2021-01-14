package com.item_category.model;


import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.item.model.ItemJDBCDAO;
import com.item.model.ItemVO;

public class ItemCategoryDAO implements ItemCategoryDAO_interface {

	 private static DataSource ds = null;
	 static {
	  try {
	   Context ctx = new javax.naming.InitialContext();
	   ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GDB");
	  } catch (NamingException e) {
	   e.printStackTrace();
	  }
	 }

	private static final String INSERT_STMT = "INSERT INTO ITEM_CATEGORY (item_category_id, item_category_name) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE ITEM_CATEGORY set item_category_NAME=? WHERE item_category_id = ?";
	private static final String DELETE = "DELETE FROM ITEM_CATEGORY where item_category_id = ?";
	private static final String GET_ONE_STMT = "SELECT * from ITEM_CATEGORY where item_category_id = ?";
	private static final String GET_ALL_STMT = "SELECT item_category_id,item_category_name FROM ITEM_CATEGORY order by item_category_id";

	@Override
	public void insert(ItemCategoryVO ItemCategoryVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ItemCategoryVO.getItemCategoryId());
			pstmt.setString(2, ItemCategoryVO.getItemCategoryName());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public void update(ItemCategoryVO ItemCategoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ItemCategoryVO.getItemCategoryName());
			pstmt.setString(2, ItemCategoryVO.getItemCategoryId());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public void delete(String itemCategoryId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, itemCategoryId);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	@Override
	public ItemCategoryVO findByPrimaryKey(String itemCategoryId) {

		ItemCategoryVO ItemCategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itemCategoryId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ItemCategoryVO = new ItemCategoryVO();
				ItemCategoryVO.setItemCategoryId(rs.getString("item_category_id"));
				ItemCategoryVO.setItemCategoryName(rs.getString("item_category_name"));

			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return ItemCategoryVO;
	}

	@Override
	public List<ItemCategoryVO> getAll() {
		List<ItemCategoryVO> list = new ArrayList<ItemCategoryVO>();
		ItemCategoryVO ItemCategoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ItemCategoryVO = new ItemCategoryVO();
				ItemCategoryVO.setItemCategoryId(rs.getString("item_category_id"));
				ItemCategoryVO.setItemCategoryName(rs.getString("item_category_name"));
				list.add(ItemCategoryVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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

		ItemCategoryDAO dao = new ItemCategoryDAO();

//		// ?–°å¢?
//		ItemCategoryVO ItemCategoryVO = new ItemCategoryVO();
//		ItemCategoryVO.setItemCategoryId("I009");
//		ItemCategoryVO.setItemCategoryName("æ¸¬è©¦?”¨");
//				dao.insert(ItemCategoryVO);

		// ä¿®æ”¹
//		ItemCategoryVO ItemCategoryVO2 = new ItemCategoryVO();
//		ItemCategoryVO2.setItemCategoryId("I009");
//		ItemCategoryVO2.setItemCategoryName("?œ²??Ÿé?“å…·");
//		dao.update(ItemCategoryVO2);

//		// ?ˆª?™¤
//		dao.delete("I009");	

//		// ?Ÿ¥è©?
//		ItemCategoryVO ItemCategoryVO3 = dao.findByPrimaryKey("I002");
//		System.out.print(ItemCategoryVO3.getItemCategoryId() + ",");
//		System.out.print(ItemCategoryVO3.getItemCategoryName() + ",");
//
//		System.out.println("---------------------");

		// ?Ÿ¥è©?
		List<ItemCategoryVO> list = dao.getAll();
		for (ItemCategoryVO aItemCategory : list) {
			System.out.print(aItemCategory.getItemCategoryId() + ",");
			System.out.print(aItemCategory.getItemCategoryName() + ",");
			System.out.println();
//		}

		}
	}

}
