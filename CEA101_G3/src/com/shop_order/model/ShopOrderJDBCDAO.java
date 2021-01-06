package com.shop_order.model;

import java.sql.*;
import java.util.*;

public class ShopOrderJDBCDAO implements ShopOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "YUCHI";
	String password = "123456";

	public static final String INSERT_STMT = "INSERT INTO SHOP_ORDER (SHOP_ORDER_ID,MEM_ID,PAYMENT,TIME,SHOP_TOTAL_AMOUNT,STATUS)"
			+ "VALUES('SD'|| SHOP_ORDER_ID_SEQ.NEXTVAL,?,?,?,?,?)";
	public static final String GET_ALL_STMT = "SELECT SHOP_ORDER_ID,MEM_ID,PAYMENT,to_char(TIME,'yyyy-mm-dd') TIME,SHOP_TOTAL_AMOUNT,STATUS FROM SHOP_ORDER ORDER BY SHOP_ORDER_ID";

	public static final String GET_ONE_STMT = "SELECT SHOP_ORDER_ID,MEM_ID,PAYMENT,to_char(TIME,'yyyy-mm-dd') TIME,SHOP_TOTAL_AMOUNT,STATUS FROM SHOP_ORDER WHERE SHOP_ORDER_ID = ?";

	public static final String DELETE = "DELETE FROM SHOP_ORDER WHERE SHOP_ORDER_ID = ?";

	public static final String UPDATE = "UPDATE SHOP_ORDER SET MEM_ID=?, PAYMENT=?, TIME=?, SHOP_TOTAL_AMOUNT=?, STATUS=? WHERE SHOP_ORDER_ID = ?";

	@Override
	public void insert(ShopOrderVO shopOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, shopOrderVO.getMem_id());
			pstmt.setString(2, shopOrderVO.getPayment());
			pstmt.setDate(3, shopOrderVO.getTime());
			pstmt.setInt(4, shopOrderVO.getShop_total_amount());
			pstmt.setInt(5, shopOrderVO.getStatus());

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

	@Override
	public void update(ShopOrderVO shopOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, shopOrderVO.getMem_id());
			pstmt.setString(2, shopOrderVO.getPayment());
			pstmt.setDate(3, shopOrderVO.getTime());
			pstmt.setInt(4, shopOrderVO.getShop_total_amount());
			pstmt.setInt(5, shopOrderVO.getStatus());
			pstmt.setString(6, shopOrderVO.getShop_order_id());

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

	@Override
	public void delete(String shop_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, shop_order_id);

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

	@Override
	public ShopOrderVO findByPrimaryKey(String shop_order_id) {
		
		ShopOrderVO shopOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shop_order_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shopOrderVO = new ShopOrderVO();
				shopOrderVO.setShop_order_id(rs.getString("shop_order_id"));
				shopOrderVO.setMem_id(rs.getString("mem_id"));
				shopOrderVO.setPayment(rs.getString("payment"));
				shopOrderVO.setTime(rs.getDate("time"));
				shopOrderVO.setShop_total_amount(rs.getInt("shop_total_amount"));
				shopOrderVO.setStatus(rs.getInt("status"));
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
		return shopOrderVO;
	}

	@Override
	public List<ShopOrderVO> getAll() {
		List<ShopOrderVO> list = new ArrayList<ShopOrderVO>();
		ShopOrderVO shopOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopOrderVO = new ShopOrderVO();

				shopOrderVO.setShop_order_id(rs.getString("shop_order_id"));
				shopOrderVO.setMem_id(rs.getString("mem_id"));
				shopOrderVO.setPayment(rs.getString("payment"));
				shopOrderVO.setTime(rs.getDate("time"));
				shopOrderVO.setShop_total_amount(rs.getInt("shop_total_amount"));
				shopOrderVO.setStatus(rs.getInt("status"));
				list.add(shopOrderVO);
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
		ShopOrderJDBCDAO dao = new ShopOrderJDBCDAO();
		ShopOrderVO svo = new ShopOrderVO();

		//新增

//		svo.setMem_id("M10011");
//		svo.setPayment("creditcard");
//		svo.setTime(java.sql.Date.valueOf("1996-01-01"));
//		svo.setShop_total_amount(7000);
//		svo.setStatus(1);
//		dao.insert(svo);

		//修改 
//		svo.setShop_order_id("SD1");
// 		svo.setMem_id("M10001"); 
//		svo.setPayment("aaaaaa���l�紵");
//		svo.setTime(java.sql.Date.valueOf("2005-01-01"));
//		svo.setShop_total_amount(5000);
//		svo.setStatus(1);
//		dao.update(svo);

		// 刪除
//		dao.delete("SD12");
		
		// 查詢1
//		Shop_orderVO svo2 = dao.findByPrimaryKey("SD2");
//		System.out.print(svo2.getShop_order_id()+",");
//		System.out.print(svo2.getMem_id()+",");
//		System.out.print(svo2.getPayment()+",");
//		System.out.print(svo2.getTime()+",");
//		System.out.print(svo2.getShop_total_amount()+",");
//		System.out.print(svo2.getStatus());

		// 查全部
		List<ShopOrderVO> list = dao.getAll();
		for (ShopOrderVO shvo : list) {
			System.out.print(shvo.getShop_order_id() + ",");
			System.out.print(shvo.getMem_id() + ",");
			System.out.print(shvo.getPayment() + ",");
			System.out.print(shvo.getTime() + ",");
			System.out.print(shvo.getShop_total_amount() + ",");
			System.out.print(shvo.getStatus());
			System.out.println();
		}

	}
}
