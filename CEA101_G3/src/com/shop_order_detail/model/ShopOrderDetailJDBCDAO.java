package com.shop_order_detail.model;

import java.sql.*;
import java.util.*;

public class ShopOrderDetailJDBCDAO implements ShopOrderDetailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String password = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt =con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shopOrderDetailVO.getShop_order_id());
			pstmt.setString(2, shopOrderDetailVO.getItem_id());
			pstmt.setString(3, shopOrderDetailVO.getItem_promotion_id());
			pstmt.setString(4, shopOrderDetailVO.getNote());
			pstmt.setInt(5, shopOrderDetailVO.getQuantity());
			pstmt.setInt(6,shopOrderDetailVO.getItem_price());
			
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
	
	public void update(ShopOrderDetailVO shopOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, shopOrderDetailVO.getItem_id());
			pstmt.setString(2, shopOrderDetailVO.getItem_promotion_id());
			pstmt.setString(3, shopOrderDetailVO.getNote());
			pstmt.setInt(4, shopOrderDetailVO.getQuantity());
			pstmt.setInt(5, shopOrderDetailVO.getItem_price());
			pstmt.setString(6,shopOrderDetailVO.getShop_order_id());
			
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

	public void delete(String shop_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,shop_order_id);
			
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
		
	public ShopOrderDetailVO findByPrimaryKey(String shop_order_id) {
		ShopOrderDetailVO shopOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
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
		return shopOrderDetailVO;
	}
	
	public List<ShopOrderDetailVO> getAll(){
		List<ShopOrderDetailVO> list = new ArrayList<ShopOrderDetailVO>();
		ShopOrderDetailVO shopOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally{
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	
	public static void main(String[] args) {
		ShopOrderDetailJDBCDAO dao = new ShopOrderDetailJDBCDAO();
		ShopOrderDetailVO svo = new ShopOrderDetailVO();
		
		//新增
//		svo.setShop_order_id("SD10021");
//		svo.setItem_id("I10020");
//		svo.setItem_promotion_id("IP20");
//		svo.setNote("123");
//		svo.setQuantity(30);
//		svo.setItem_price(1000);
//		dao.insert(svo);
		
		//修改
		
//		svo.setItem_id("I10020");
//		svo.setItem_promotion_id("IP30");
//		svo.setNote("456");
//		svo.setQuantity(2000);
//		svo.setItem_price(20000);
//		svo.setShop_order_id("SD10021");
//		dao.update(svo);
		
		//刪除
//		dao.delete("SD10020");
		//查詢一
//		svo = dao.findByPrimaryKey("SD10020");
//		System.out.println(svo.getShop_order_id()+ ",");
//		System.out.println(svo.getItem_id()+ ",");
//		System.out.println(svo.getItem_promotion_id()+ ",");
//		System.out.println(svo.getNote()+ ",");
//		System.out.println(svo.getQuantity()+ ",");
//		System.out.println(svo.getItem_price());
//		System.out.println();
		
		//查全部
		List<ShopOrderDetailVO> list = dao.getAll();
		for(ShopOrderDetailVO shodVO: list) {
			System.out.print(shodVO.getShop_order_id()+ ",");
			System.out.print(shodVO.getItem_id()+ ",");
			System.out.print(shodVO.getItem_promotion_id()+ ",");
			System.out.print(shodVO.getNote()+ ",");
			System.out.print(shodVO.getQuantity()+ ",");
			System.out.print(shodVO.getItem_price());
			System.out.println();
		}
	}

	
}
