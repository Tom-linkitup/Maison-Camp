package com.actCategory.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActCategoryDAO implements ActCategoryDAOInterface {
	
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
	
	private static final String INSERT_STMT = "INSERT INTO Act_Category (act_category_id , act_category_name)  VALUES (?,?)";
	private static final String UPDATE_STMT = "UPDATE Act_Category set act_category_name=? WHERE act_category_id=?";
	private static final String FIND_BY_PK = "SELECT * FROM Act_Category WHERE act_category_id=?";
	private static final String GETALL = "SELECT * FROM Act_Category ";

	@Override
	public void insert(ActCategoryVO act_categoryVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, act_categoryVo.getActCategoryId());
			pstmt.setString(2, act_categoryVo.getActCategoryName());

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
	public void update(ActCategoryVO act_categoryVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, act_categoryVo.getActCategoryName());
			pstmt.setString(2, act_categoryVo.getActCategoryId());
			pstmt.execute();
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
	public ActCategoryVO findByPK(String act_category_id) {
		ActCategoryVO act_CVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, act_category_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_CVO = new ActCategoryVO();
				act_CVO.setActCategoryId(rs.getString("act_category_id"));
				act_CVO.setActCategoryName(rs.getString("act_category_name"));

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
		return act_CVO;

	}

	@Override
	public List<ActCategoryVO> getAll() {
		List<ActCategoryVO> list = new ArrayList<ActCategoryVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ActCategoryVO act_CVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_CVO = new ActCategoryVO();
				act_CVO.setActCategoryId(rs.getString("act_category_id"));
				act_CVO.setActCategoryName(rs.getString("act_category_name"));
				list.add(act_CVO);
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

	public static void main(String[] args) {

		ActCategoryDAO actCategoryJDBCDAO = new ActCategoryDAO();
		ActCategoryVO actCategoryVO = new ActCategoryVO();

//		// try insert
//		actCategoryVO.setActCategoryId("ACT_CATEGORY6");
//		actCategoryVO.setActCategoryName("不知道");
//		actCategoryJDBCDAO.insert(actCategoryVO);
//
//		// try updete
//		actCategoryVO.setActCategoryId("ACT_CATEGORY6");
//		actCategoryVO.setActCategoryName("你好");
//		actCategoryJDBCDAO.update(actCategoryVO);
//
//		// try findByPK
//		actCategoryVO = actCategoryJDBCDAO.findByPK("ACT_CATEGORY5");
//		System.out.println("category_id:" + actCategoryVO.getActCategoryId());
//		System.out.println("category_name:" + actCategoryVO.getActCategoryName());
//
//		// try getAll
//		for (ActCategoryVO a : actCategoryJDBCDAO.getAll()) {
//			System.out.println("id: " + a.getActCategoryId());
//			System.out.println("name: " + a.getActCategoryName());
//			System.out.println("-----------");
//		}

	}

}
