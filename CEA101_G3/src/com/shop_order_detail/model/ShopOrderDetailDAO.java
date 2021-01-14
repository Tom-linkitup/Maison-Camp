package com.shop_order_detail.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ShopOrderDetailDAO implements ShopOrderDetailDAO_interface{


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

	public static final String INSERT_STMT = "INSERT INTO SHOP_ORDER_DETAIL (SHOP_ORDER_ID , ITEM_ID , ITEM_PROMOTION_ID , NOTE , QUANTITY , ITEM_PRICE)"
			+ "VALUES(?,?,?,?,?,?)";
	public static final String GET_ALL_STMT = "SELECT * FROM SHOP_ORDER_DETAIL ORDER BY SHOP_ORDER_ID";
	public static final String GET_ONE_STMT = "SELECT * FROM SHOP_ORDER_DETAIL WHERE SHOP_ORDER_ID = ?";
	public static final String DELETE = "DELETE FROM SHOP_ORDER_DETAIL WHERE SHOP_ORDER_ID = ?";
	public static final String UPDATE = "UPDATE SHOP_ORDER_DETAIL SET ITEM_ID=?, ITEM_PROMOTION_ID=?, NOTE=?, QUANTITY=?, ITEM_PRICE=? WHERE SHOP_ORDER_ID = ?";
	
	
	public void insert(ShopOrderDetailVO shopOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shopOrderDetailVO.getShop_order_id());
			pstmt.setString(2, shopOrderDetailVO.getItem_id());
			pstmt.setString(3, shopOrderDetailVO.getItem_promotion_id());
			pstmt.setString(4, shopOrderDetailVO.getNote());
			pstmt.setInt(5, shopOrderDetailVO.getQuantity());
			pstmt.setInt(6,shopOrderDetailVO.getItem_price());
			
			pstmt.executeUpdate();
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
	
	public void update(ShopOrderDetailVO shopOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, shopOrderDetailVO.getItem_id());
			pstmt.setString(2, shopOrderDetailVO.getItem_promotion_id());
			pstmt.setString(3, shopOrderDetailVO.getNote());
			pstmt.setInt(4, shopOrderDetailVO.getQuantity());
			pstmt.setInt(5, shopOrderDetailVO.getItem_price());
			pstmt.setString(6,shopOrderDetailVO.getShop_order_id());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
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

	public void delete(String shop_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,shop_order_id);
			
			pstmt.executeUpdate();
			
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
		
	public List<ShopOrderDetailVO> findByPrimaryKey(String shop_order_id) {
		List<ShopOrderDetailVO> list = new ArrayList<ShopOrderDetailVO>();
		ShopOrderDetailVO shopOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,shop_order_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopOrderDetailVO = new ShopOrderDetailVO();
				
				shopOrderDetailVO.setShop_order_id(rs.getString("shop_order_id"));
				shopOrderDetailVO.setItem_id(rs.getString("item_id"));
				shopOrderDetailVO.setItem_promotion_id(rs.getString("item_promotion_id"));
				shopOrderDetailVO.setNote(rs.getString("note"));
				shopOrderDetailVO.setQuantity(rs.getInt("quantity"));
				shopOrderDetailVO.setItem_price(rs.getInt("item_price"));
				list.add(shopOrderDetailVO);
			}
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
	
	public List<ShopOrderDetailVO> getAll(){
		List<ShopOrderDetailVO> list = new ArrayList<ShopOrderDetailVO>();
		ShopOrderDetailVO shopOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopOrderDetailVO = new ShopOrderDetailVO();
				
				shopOrderDetailVO.setShop_order_id(rs.getString("shop_order_id"));
				shopOrderDetailVO.setItem_id(rs.getString("item_id"));
				shopOrderDetailVO.setItem_promotion_id(rs.getString("item_promotion_id"));
				shopOrderDetailVO.setNote(rs.getString("note"));
				shopOrderDetailVO.setQuantity(rs.getInt("quantity"));
				shopOrderDetailVO.setItem_price(rs.getInt("item_price"));
				list.add(shopOrderDetailVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public void insert2(ShopOrderDetailVO shopOrderDetailVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,shopOrderDetailVO.getShop_order_id());	
			pstmt.setString(2,shopOrderDetailVO.getItem_id());
			pstmt.setString(3,shopOrderDetailVO.getItem_promotion_id());
			pstmt.setString(4,shopOrderDetailVO.getNote());
			pstmt.setInt(5,shopOrderDetailVO.getQuantity());
			pstmt.setFloat(6,shopOrderDetailVO.getItem_price());
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}
}
