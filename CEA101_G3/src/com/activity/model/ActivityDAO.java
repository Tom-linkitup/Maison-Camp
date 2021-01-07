package com.activity.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Activity;

public class ActivityDAO implements ActivityDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY ( act_id,act_category_id,act_info,act_price,act_start_date,act_end_date,act_apply_open,act_apply_close,max_people,min_people,act_already_apply,act_name,act_status,act_discount,act_prom_info,act_prom_start_date,act_prom_close_date) VALUES ('A' || act_id_seq.nextval ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY set act_category_id=?,act_info=?,act_price=?,act_start_date=?,act_end_date=?,act_apply_open=?,act_apply_close=?,max_people=?,min_people=?,act_already_apply=?,act_name=?,act_status=?,act_discount=?,act_prom_info=?,act_prom_start_date=?,act_prom_close_date=? WHERE act_id=?";
	private static final String FIND_BY_PK = "SELECT * FROM ACTIVITY WHERE act_id=?";
	private static final String GETALL = "SELECT * FROM ACTIVITY ";

	@Override

	public void insert(ActivityVO activitoVo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, activitoVo.getActCategoryId());
			pstmt.setString(2, activitoVo.getActInfo());
			pstmt.setInt(3, activitoVo.getActPrice());
			pstmt.setDate(4, activitoVo.getActStartDate());
			pstmt.setDate(5, activitoVo.getActEndDate());
			pstmt.setDate(6, activitoVo.getActApplyOpen());
			pstmt.setDate(7, activitoVo.getActApplyClose());
			pstmt.setInt(8, activitoVo.getMaxPeople());
			pstmt.setInt(9, activitoVo.getMinPeople());
			pstmt.setInt(10, activitoVo.getActAlreadyApply());
			pstmt.setString(11, activitoVo.getActName());
			pstmt.setInt(12, activitoVo.getActStatus());
			pstmt.setDouble(13, activitoVo.getActDiscount());
			pstmt.setString(14, activitoVo.getActPromInfo());
			pstmt.setDate(15, activitoVo.getActPromStartDate());
			pstmt.setDate(16, activitoVo.getActPromCloseDate());
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
	public void update(ActivityVO activityVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, activityVO.getActCategoryId());
			pstmt.setString(2, activityVO.getActInfo());
			pstmt.setInt(3, activityVO.getActPrice());
			pstmt.setDate(4, activityVO.getActStartDate());
			pstmt.setDate(5, activityVO.getActEndDate());
			pstmt.setDate(6, activityVO.getActApplyOpen());
			pstmt.setDate(7, activityVO.getActApplyClose());
			pstmt.setInt(8, activityVO.getMaxPeople());
			pstmt.setInt(9, activityVO.getMinPeople());
			pstmt.setInt(10, activityVO.getActAlreadyApply());
			pstmt.setString(11, activityVO.getActName());
			pstmt.setInt(12, activityVO.getActStatus());
			pstmt.setDouble(13, activityVO.getActDiscount());
			pstmt.setString(14, activityVO.getActPromInfo());
			pstmt.setDate(15, activityVO.getActPromStartDate());
			pstmt.setDate(16, activityVO.getActPromCloseDate());
			pstmt.setString(17, activityVO.getActId());
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
	public ActivityVO findByPK(String act_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityVO activityVO = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, act_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActId(rs.getString("act_id"));
				activityVO.setActCategoryId(rs.getString("act_category_id"));
				activityVO.setActInfo(rs.getString("act_info"));
				activityVO.setActPrice(rs.getInt("act_price"));
				activityVO.setActStartDate(rs.getDate("act_start_date"));
				activityVO.setActEndDate(rs.getDate("act_end_date"));
				activityVO.setActApplyOpen(rs.getDate("act_apply_open"));
				activityVO.setActApplyClose(rs.getDate("act_apply_close"));
				activityVO.setMaxProple(rs.getInt("max_people"));
				activityVO.setMinPeople(rs.getInt("min_people"));
				activityVO.setActAlreadyApply(rs.getInt("act_already_apply"));
				activityVO.setActName(rs.getString("act_name"));
				activityVO.setActStatus(rs.getInt("act_status"));
				activityVO.setActDiscount(rs.getDouble("act_discount"));
				activityVO.setActPromInfo(rs.getString("act_prom_info"));
				activityVO.setActPromStartDate(rs.getDate("act_prom_start_date"));
				activityVO.setActPromCloseDate(rs.getDate("act_prom_close_date"));
			}
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
		return activityVO;
	}

	@Override
	public List<ActivityVO> getAll() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActId(rs.getString("act_id"));
				activityVO.setActCategoryId(rs.getString("act_category_id"));
				activityVO.setActInfo(rs.getString("act_info"));
				activityVO.setActPrice(rs.getInt("act_price"));
				activityVO.setActStartDate(rs.getDate("act_start_date"));
				activityVO.setActEndDate(rs.getDate("act_end_date"));
				activityVO.setActApplyOpen(rs.getDate("act_apply_open"));
				activityVO.setActApplyClose(rs.getDate("act_apply_close"));
				activityVO.setMaxProple(rs.getInt("max_people"));
				activityVO.setMinPeople(rs.getInt("min_people"));
				activityVO.setActAlreadyApply(rs.getInt("act_already_apply"));
				activityVO.setActName(rs.getString("act_name"));
				activityVO.setActStatus(rs.getInt("act_status"));
				activityVO.setActDiscount(rs.getDouble("act_discount"));
				activityVO.setActPromInfo(rs.getString("act_prom_info"));
				activityVO.setActPromStartDate(rs.getDate("act_prom_start_date"));
				activityVO.setActPromCloseDate(rs.getDate("act_prom_close_date"));
				list.add(activityVO);
			}
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
		return list;
	}

	

	public List<ActivityVO> getAll(Map<String, String[]> map) {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = "select * from activity " + jdbcUtil_CompositeQuery_Activity.get_WhereCondition(map)
					+ "order by act_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActId(rs.getString("act_id"));
				activityVO.setActCategoryId(rs.getString("act_category_id"));
				activityVO.setActInfo(rs.getString("act_info"));
				activityVO.setActPrice(rs.getInt("act_price"));
				activityVO.setActStartDate(rs.getDate("act_start_date"));
				activityVO.setActEndDate(rs.getDate("act_end_date"));
				activityVO.setActApplyOpen(rs.getDate("act_apply_open"));
				activityVO.setActApplyClose(rs.getDate("act_apply_close"));
				activityVO.setMaxProple(rs.getInt("max_people"));
				activityVO.setMinPeople(rs.getInt("min_people"));
				activityVO.setActAlreadyApply(rs.getInt("act_already_apply"));
				activityVO.setActName(rs.getString("act_name"));
				activityVO.setActStatus(rs.getInt("act_status"));
				activityVO.setActDiscount(rs.getDouble("act_discount"));
				activityVO.setActPromInfo(rs.getString("act_prom_info"));
				activityVO.setActPromStartDate(rs.getDate("act_prom_start_date"));
				activityVO.setActPromCloseDate(rs.getDate("act_prom_close_date"));
				list.add(activityVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		ActivityDAO actJDBCDAO = new ActivityDAO();
//		ActivityVO activityVO = new ActivityVO();

		// try insert
//		activityVO.setActCategoryId("ACT_CATEGORY5");
//		activityVO.setActInfo("快來便便DIY");
//		activityVO.setActPrice(3000);
//		activityVO.setActStartDate(java.sql.Date.valueOf("2020-12-25"));
//		activityVO.setActEndDate(java.sql.Date.valueOf("2020-12-26"));
//		activityVO.setActApplyOpen(java.sql.Date.valueOf("2020-12-10"));
//		activityVO.setActApplyClose(java.sql.Date.valueOf("2020-12-20"));
//		activityVO.setMaxPeople(20);
//		activityVO.setMinPeople(5);
//		activityVO.setActAlreadyApply(0);
//		activityVO.setActName("吃便便");
//		activityVO.setActStatus(0);
//		activityVO.setActDiscount(1.0);
//		activityVO.setActPromInfo("別想有任何優惠");
//		activityVO.setActPromStartDate(java.sql.Date.valueOf("2020-12-10"));
//		activityVO.setActPromCloseDate(java.sql.Date.valueOf("2020-12-20"));
//		actJDBCDAO.insert(activityVO);

		// try update
//		activityVO.setActCategoryId("ACT_CATEGORY5");
//		activityVO.setActInfo("快來便便DIY啦~~~~~~~~~~~");
//		activityVO.setActPrice(3000);
//		activityVO.setActStartDate(java.sql.Date.valueOf("2020-12-25"));
//		activityVO.setActEndDate(java.sql.Date.valueOf("2020-12-26"));
//		activityVO.setActApplyOpen(java.sql.Date.valueOf("2020-12-10"));
//		activityVO.setActApplyClose(java.sql.Date.valueOf("2020-12-20"));
//		activityVO.setMaxPeople(20);
//		activityVO.setMinPeople(5);
//		activityVO.setActAlreadyApply(0);
//		activityVO.setActName("吃便便");
//		activityVO.setActStatus(0);
//		activityVO.setActDiscount(1.0);
//		activityVO.setActPromInfo("別想有任何優惠");
//		activityVO.setActPromStartDate(java.sql.Date.valueOf("2020-12-10"));
//		activityVO.setActPromCloseDate(java.sql.Date.valueOf("2020-12-20"));
//		activityVO.setActId("A10011");
//		actJDBCDAO.update(activityVO);

		// findByPK
//		activityVO = actJDBCDAO.findByPK("A10001");
//		System.out.println("act_id = " + activityVO.getActId());
//		System.out.println("act_category_id = " + activityVO.getActCategoryId());
//		System.out.println("act_info = " + activityVO.getActInfo());
//		System.out.println("act_price = " + activityVO.getActPrice());
//		System.out.println("act_start_date = " + activityVO.getActStartDate());
//		System.out.println("act_end_date = " + activityVO.getActEndDate());
//		System.out.println("act_apply_open = " + activityVO.getActApplyOpen());
//		System.out.println("act_apply_close = " + activityVO.getActApplyClose());
//		System.out.println("max_people = " + activityVO.getMaxPeople());
//		System.out.println("min_people = " + activityVO.getMinPeople());
//		System.out.println("act_already_apply = " + activityVO.getActAlreadyApply());
//		System.out.println("act_name = " + activityVO.getActName());
//		System.out.println("act_status = " + activityVO.getActStatus());
//		System.out.println("act_discount = " + activityVO.getActDiscount());
//		System.out.println("act_prom_info = " + activityVO.getActPromInfo());
//		System.out.println("act_prom_start_date = " + activityVO.getActPromStartDate());
//		System.out.println("act_prom_close_date = " + activityVO.getActPromCloseDate());
//		

		// getAll
//		for (ActivityVO activityVO: actJDBCDAO.getAll()) {
//			System.out.println("act_id = " + activityVO.getActId());
//			System.out.println("act_category_id = " + activityVO.getActCategoryId());
//			System.out.println("act_info = " + activityVO.getActInfo());
//			System.out.println("act_price = " + activityVO.getActPrice());
//			System.out.println("act_start_date = " + activityVO.getActStartDate());
//			System.out.println("act_end_date = " + activityVO.getActEndDate());
//			System.out.println("act_apply_open = " + activityVO.getActApplyOpen());
//			System.out.println("act_apply_close = " + activityVO.getActApplyClose());
//			System.out.println("max_people = " + activityVO.getMaxPeople());
//			System.out.println("min_people = " + activityVO.getMinPeople());
//			System.out.println("act_already_apply = " + activityVO.getActAlreadyApply());
//			System.out.println("act_name = " + activityVO.getActName());
//			System.out.println("act_status = " + activityVO.getActStatus());
//			System.out.println("act_discount = " + activityVO.getActDiscount());
//			System.out.println("act_prom_info = " + activityVO.getActPromInfo());
//			System.out.println("act_prom_start_date = " + activityVO.getActPromStartDate());
//			System.out.println("act_prom_close_date = " + activityVO.getActPromCloseDate());
//			System.out.println("------------下一個------------");
//		}

		// getAll
//		for (ActivityVO activityVO: actJDBCDAO.getAll()) {
//			System.out.println("act_id = " + activityVO.getActId());
//			System.out.println("act_category_id = " + activityVO.getActCategoryId());
//			System.out.println("act_info = " + activityVO.getActInfo());
//			System.out.println("act_price = " + activityVO.getActPrice());
//			System.out.println("act_start_date = " + activityVO.getActStartDate());
//			System.out.println("act_end_date = " + activityVO.getActEndDate());
//			System.out.println("act_apply_open = " + activityVO.getActApplyOpen());
//			System.out.println("act_apply_close = " + activityVO.getActApplyClose());
//			System.out.println("max_people = " + activityVO.getMaxPeople());
//			System.out.println("min_people = " + activityVO.getMinPeople());
//			System.out.println("act_already_apply = " + activityVO.getActAlreadyApply());
//			System.out.println("act_name = " + activityVO.getActName());
//			System.out.println("act_status = " + activityVO.getActStatus());
//			System.out.println("act_discount = " + activityVO.getActDiscount());
//			System.out.println("act_prom_info = " + activityVO.getActPromInfo());
//			System.out.println("act_prom_start_date = " + activityVO.getActPromStartDate());
//			System.out.println("act_prom_close_date = " + activityVO.getActPromCloseDate());
//			System.out.println("------------下一個------------");
//		}

		// getSelectDay
//		for (ActivityVO activityVO : actJDBCDAO.getSelectDay(java.sql.Date.valueOf("2020-12-20"),
//				java.sql.Date.valueOf("2020-12-26"))) {
//			System.out.println("act_id = " + activityVO.getActId());
//			System.out.println("act_category_id = " + activityVO.getActCategoryId());
//			System.out.println("act_info = " + activityVO.getActInfo());
//			System.out.println("act_price = " + activityVO.getActPrice());
//			System.out.println("act_start_date = " + activityVO.getActStartDate());
//			System.out.println("act_end_date = " + activityVO.getActEndDate());
//			System.out.println("act_apply_open = " + activityVO.getActApplyOpen());
//			System.out.println("act_apply_close = " + activityVO.getActApplyClose());
//			System.out.println("max_people = " + activityVO.getMaxPeople());
//			System.out.println("min_people = " + activityVO.getMinPeople());
//			System.out.println("act_already_apply = " + activityVO.getActAlreadyApply());
//			System.out.println("act_name = " + activityVO.getActName());
//			System.out.println("act_status = " + activityVO.getActStatus());
//			System.out.println("act_discount = " + activityVO.getActDiscount());
//			System.out.println("act_prom_info = " + activityVO.getActPromInfo());
//			System.out.println("act_prom_start_date = " + activityVO.getActPromStartDate());
//			System.out.println("act_prom_close_date = " + activityVO.getActPromCloseDate());
//			System.out.println("------------下一個------------");
//		}
	}
}
