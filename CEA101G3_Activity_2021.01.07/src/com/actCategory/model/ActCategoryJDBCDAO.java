package com.actCategory.model;

import java.sql.*;
import java.util.*;

public class ActCategoryJDBCDAO implements ActCategoryDAOInterface {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "CEA101G3";
	public static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO Act_Category (act_category_id , act_category_name)  VALUES (?,?)";
	private static final String UPDATE_STMT = "UPDATE Act_Category set act_category_name=? WHERE act_category_id=?";
	private static final String FIND_BY_PK = "SELECT * FROM Act_Category WHERE act_category_id=?";
	private static final String GETALL = "SELECT * FROM Act_Category ";

	@Override
	public void insert(ActCategoryVO act_categoryVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, act_categoryVo.getActCategoryId());
			pstmt.setString(2, act_categoryVo.getActCategoryName());

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
	public void update(ActCategoryVO act_categoryVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, act_categoryVo.getActCategoryName());
			pstmt.setString(2, act_categoryVo.getActCategoryId());
			pstmt.execute();
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
	public ActCategoryVO findByPK(String act_category_id) {
		ActCategoryVO act_CVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, act_category_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_CVO = new ActCategoryVO();
				act_CVO.setActCategoryId(rs.getString("act_category_id"));
				act_CVO.setActCategoryName(rs.getString("act_category_name"));

			}

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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act_CVO = new ActCategoryVO();
				act_CVO.setActCategoryId(rs.getString("act_category_id"));
				act_CVO.setActCategoryName(rs.getString("act_category_name"));
				list.add(act_CVO);
			}
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
		return list;
	}

	public static void main(String[] args) {

		ActCategoryJDBCDAO actCategoryJDBCDAO = new ActCategoryJDBCDAO();
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
