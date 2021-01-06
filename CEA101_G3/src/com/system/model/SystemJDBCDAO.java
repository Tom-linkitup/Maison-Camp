package com.system.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemJDBCDAO implements SystemDAO_interface {
	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO SYSTEM VALUES ( ?, ?, ?)";
	private static final String GET_ONE_STMT = "SELECT * from SYSTEM where sys_Id = ?";
	private static final String GET_ALL_STMT = "SELECT *  FROM SYSTEM order by sys_Id";
	private static final String DELETE = "DELETE FROM SYSTEM where sys_Id = ?";
	private static final String UPDATE = "UPDATE SYSTEM set sysContent=? where sys_Id=?";

	public void insert(SystemVO SystemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, SystemVO.getSysId());
			pstmt.setString(2, SystemVO.getEmpId());
			pstmt.setString(3, SystemVO.getSysContent());
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

	public void update(SystemVO SystemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, SystemVO.getSysContent());
			pstmt.setString(2, SystemVO.getSysId());
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

	public void delete(String sysId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sysId);

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

	public SystemVO findByPrimaryKey(String sysId) {

		SystemVO SystemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sysId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				SystemVO = new SystemVO();
				SystemVO.setSysId(rs.getString("sys_id"));
				SystemVO.setEmpId(rs.getString("emp_id"));
				SystemVO.setSysContent(rs.getString("sys_content"));

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
		return SystemVO;
	}

	public List<SystemVO> getAll() {
		List<SystemVO> list = new ArrayList<SystemVO>();
		SystemVO SystemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SystemVO = new SystemVO();
				SystemVO.setSysId(rs.getString("sys_id"));
				SystemVO.setEmpId(rs.getString("emp_id"));
				SystemVO.setSysContent(rs.getString("sys_content"));
				list.add(SystemVO); // Store the row in the list
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

		SystemJDBCDAO dao = new SystemJDBCDAO();

//		// 新增
		SystemVO SystemVO1 = new SystemVO();
		SystemVO1.setSysId("S001");
		SystemVO1.setEmpId("E10001");
		SystemVO1.setSysContent("哈哈哈");
		dao.insert(SystemVO1);

		// 修改
//		SystemVO SystemVO2 = new SystemVO();
//		SystemVO2.setSysContent("哈哈哈");
//		dao.update(SystemVO);

//		// 刪除
//		dao.delete("S001");

//		// 查詢
//		SystemVO SystemVO3 = dao.findByPrimaryKey("I4");
//		System.out.print(SystemVO3.getSysId() + ",");
//		System.out.print(SystemVO3.getEmpId() + ",");
//		System.out.print(SystemVO3.getSysContent() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<SystemVO> list = dao.getAll();
		for (SystemVO aItem : list) {
			System.out.print(aItem.getSysId() + ",");
			System.out.print(aItem.getEmpId() + ",");
			System.out.print(aItem.getSysContent() + ",");
			System.out.println();
//		}

		}
	}
}
