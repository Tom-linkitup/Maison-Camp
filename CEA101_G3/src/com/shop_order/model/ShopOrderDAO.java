package com.shop_order.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop_order_detail.model.ShopOrderDetailDAO;
import com.shop_order_detail.model.ShopOrderDetailVO;

public class ShopOrderDAO implements ShopOrderDAO_interface {


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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, shopOrderVO.getMem_id());
			pstmt.setString(2, shopOrderVO.getPayment());
			pstmt.setDate(3, shopOrderVO.getTime());
			pstmt.setFloat(4, shopOrderVO.getShop_total_amount());
			pstmt.setInt(5, shopOrderVO.getStatus());

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

	@Override
	public void update(ShopOrderVO shopOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, shopOrderVO.getMem_id());
			pstmt.setString(2, shopOrderVO.getPayment());
			pstmt.setDate(3, shopOrderVO.getTime());
			pstmt.setFloat(4, shopOrderVO.getShop_total_amount());
			pstmt.setInt(5, shopOrderVO.getStatus());
			pstmt.setString(6, shopOrderVO.getShop_order_id());

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

	@Override
	public void delete(String shop_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, shop_order_id);

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

	@Override
	public ShopOrderVO findByPrimaryKey(String shop_order_id) {
		
		ShopOrderVO shopOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
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
			con = ds.getConnection();
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

	@Override
	public void insertWithOrderDetail(ShopOrderVO shopOrderVO, List<ShopOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			String cols[] = {"SHOP_ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, shopOrderVO.getMem_id());
			pstmt.setString(2, shopOrderVO.getPayment());
			pstmt.setDate(3, shopOrderVO.getTime());
			pstmt.setFloat(4, shopOrderVO.getShop_total_amount());
			pstmt.setInt(5, shopOrderVO.getStatus());
			
			pstmt.executeUpdate();
			
			
			String next_shop_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				next_shop_order_id = rs.getString(1);
			}
			rs.close();
			

			ShopOrderDetailDAO dao = new ShopOrderDetailDAO();
			for(ShopOrderDetailVO vo : list) {
				vo.setShop_order_id(next_shop_order_id);
				dao.insert2(vo, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
