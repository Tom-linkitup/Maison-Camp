package com.extra_charges.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.extra_charges.model.Extra_chargesDAO_interface;
import com.extra_charges.model.Extra_chargesVO;

import java.sql.*;

public class Extra_chargesDAO implements Extra_chargesDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO EXTRA_CHARGES(EXTRA_CHARGES_ID,ROOM_ORDER_ID,ITEM,PRICE) VALUES('EXC' || EXTRA_CHARGES_ID_seq.nextval,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT EXTRA_CHARGES_ID,ROOM_ORDER_ID,ITEM,PRICE FROM EXTRA_CHARGES order by EXTRA_CHARGES_ID";
	private static final String GET_ONE_STMT = 
		"SELECT EXTRA_CHARGES_ID,ROOM_ORDER_ID,ITEM,PRICE FROM EXTRA_CHARGES where EXTRA_CHARGES_ID =?";
	private static final String DELETE = 
		"DELETE FROM EXTRA_CHARGES where EXTRA_CHARGES_ID = ?";
	private static final String UPDATE = 
		"UPDATE EXTRA_CHARGES set ROOM_ORDER_ID=?, ITEM=?, PRICE=? where EXTRA_CHARGES_ID = ?";
	private static final String Get_By_Order_Id = "SELECT * FROM EXTRA_CHARGES WHERE ROOM_ORDER_ID=?";

	@Override
	public void insert(Extra_chargesVO extra_chargesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, extra_chargesVO.getRoom_order_id());
			pstmt.setString(2, extra_chargesVO.getItem());
			pstmt.setInt(3, extra_chargesVO.getPrice());
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
	public void update(Extra_chargesVO extra_chargesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, extra_chargesVO.getRoom_order_id());
			pstmt.setString(2, extra_chargesVO.getItem());
			pstmt.setInt(3, extra_chargesVO.getPrice());
			pstmt.setString(4, extra_chargesVO.getExtra_charges_id());
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
	public void delete(String extra_charges_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, extra_charges_id);

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
	public Extra_chargesVO findByPrimaryKey(String extra_charges_id) {

		Extra_chargesVO extra_chargesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, extra_charges_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// extraChargesVO 也稱為 Domain objects
				extra_chargesVO = new Extra_chargesVO();
				extra_chargesVO.setExtra_charges_id(rs.getString("extra_charges_id"));
				extra_chargesVO.setRoom_order_id(rs.getString("room_order_id"));
				extra_chargesVO.setItem(rs.getString("item"));
				extra_chargesVO.setPrice(rs.getInt("price"));
				
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
		return extra_chargesVO;
	}

	@Override
	public List<Extra_chargesVO> getAll() {
		List<Extra_chargesVO> list = new ArrayList<Extra_chargesVO>();
		Extra_chargesVO extra_chargesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// extraChargesVO 也稱為 Domain objects
				extra_chargesVO = new Extra_chargesVO();
				extra_chargesVO.setExtra_charges_id(rs.getString("extra_charges_id"));
				extra_chargesVO.setRoom_order_id(rs.getString("room_order_id"));
				extra_chargesVO.setItem(rs.getString("item"));
				extra_chargesVO.setPrice(rs.getInt("price"));
				list.add(extra_chargesVO); // Store the row in the list
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

	public static void main(String[] args) {

		Extra_chargesDAO dao = new Extra_chargesDAO();

//	// 新增
		Extra_chargesVO extra_chargesVO1 = new Extra_chargesVO();
		extra_chargesVO1.setRoom_order_id("RD10011");
		extra_chargesVO1.setItem("弄壞房間設備");
		extra_chargesVO1.setPrice(1000);
		dao.insert(extra_chargesVO1);
//
//	// 修改
		Extra_chargesVO extra_chargesVO2 = new Extra_chargesVO();
		extra_chargesVO2.setExtra_charges_id("EXC10003");
		extra_chargesVO2.setRoom_order_id("RD10013");
		extra_chargesVO2.setItem("破壞公物1234");
		extra_chargesVO2.setPrice(2000);
		dao.update(extra_chargesVO2);
	
//	
////	// 刪除
	dao.delete("EXC10027");



	//查詢
	Extra_chargesVO extra_chargesVO3 = dao.findByPrimaryKey("EXC10003");
	System.out.print(extra_chargesVO3.getExtra_charges_id() + ",");
	System.out.print(extra_chargesVO3.getRoom_order_id() + ",");
	System.out.print(extra_chargesVO3.getItem() + ",");
	System.out.print(extra_chargesVO3.getPrice() + ",");

	System.out.println("---------------------");


	// 查詢ALL
	List<Extra_chargesVO> list = dao.getAll();
	for (Extra_chargesVO aExtra_charges : list) {
		System.out.print(aExtra_charges.getExtra_charges_id() + ",");
		System.out.print(aExtra_charges.getRoom_order_id() + ",");
		System.out.print(aExtra_charges.getItem() + ",");
		System.out.print(aExtra_charges.getPrice() + ",");
		
		System.out.println();
	
	}
  }

	@Override
	public List<Extra_chargesVO> getByRoomOrderId(String room_order_id) {
		List<Extra_chargesVO> list = new ArrayList<Extra_chargesVO>();
		Extra_chargesVO extra_chargesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_By_Order_Id);
			
			pstmt.setString(1, room_order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// extraChargesVO 也稱為 Domain objects
				extra_chargesVO = new Extra_chargesVO();
				extra_chargesVO.setExtra_charges_id(rs.getString("extra_charges_id"));
				extra_chargesVO.setRoom_order_id(rs.getString("room_order_id"));
				extra_chargesVO.setItem(rs.getString("item"));
				extra_chargesVO.setPrice(rs.getInt("price"));
				list.add(extra_chargesVO); // Store the row in the list
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
}
