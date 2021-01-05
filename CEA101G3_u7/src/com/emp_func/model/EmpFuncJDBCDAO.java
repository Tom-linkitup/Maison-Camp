package com.emp_func.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpFuncJDBCDAO implements EmpFuncDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PHIL";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO emp_func (emp_id, func_id) VALUES (?,?)";
	private static final String GET_ONE_STMT ="SELECT * FROM emp_func WHERE emp_id=? ORDER BY func_id";
	private static final String GET_ONE_FUNC ="SELECT func_id FROM emp_func WHERE emp_id=? ORDER BY func_id";
	private static final String GET_ALL_STMT ="SELECT * FROM emp_func ORDER BY emp_id";
	private static final String DELETE = "DELETE FROM emp_func WHERE emp_id=? AND func_id=?";
	@Override
	public void insert(EmpFuncVO empFuncVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, empFuncVO.getEmp_id());
			pstmt.setString(2, empFuncVO.getFunc_id());
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String emp_id, String func_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id);
			pstmt.setString(2, func_id);
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

	@Override
	public List<EmpFuncVO> findByPrimaryKey(String emp_id) {
		List<EmpFuncVO> list = new ArrayList<EmpFuncVO>();
		EmpFuncVO empFuncVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empFuncVO = new EmpFuncVO();
				empFuncVO.setEmp_id(rs.getString("emp_id"));
				empFuncVO.setFunc_id(rs.getString("func_id"));
				list.add(empFuncVO);
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

	@Override
	public List<EmpFuncVO> getAll() {
		List<EmpFuncVO> list = new ArrayList<EmpFuncVO>();
		EmpFuncVO empFuncVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empFuncVO = new EmpFuncVO();
				empFuncVO.setEmp_id(rs.getString("emp_id"));
				empFuncVO.setFunc_id(rs.getString("func_id"));
				list.add(empFuncVO);
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

	public List<String> findFuncs(String emp_id){
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FUNC);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("func_id"));
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
		EmpFuncJDBCDAO dao = new EmpFuncJDBCDAO();
		EmpFuncVO vo = new EmpFuncVO();
		List<EmpFuncVO> list = null;
//		vo.setEmp_id("E10001");
//		vo.setFunc_id("1");
//		dao.insert(vo);
//		
//		dao.delete("E10001", "1");
//		
//		list = dao.findByPrimaryKey("E10001");
//		
//		for(EmpFuncVO voo : list) {
//			System.out.print(voo.getEmp_id()+",");
//			System.out.println(voo.getFunc_id()); 
//		}
//		
//		list = dao.getAll();
//		for (EmpFuncVO voo : list) {
//			System.out.print(voo.getEmp_id() + ",");
//			System.out.println(voo.getFunc_id());
//		}
//		List<String> funcList = null;
//		funcList = dao.findFuncs("E10001");
//		for(String str : funcList){
//			System.out.println("func_id : " + str);
//		}
		System.out.println("done");
	}

}
