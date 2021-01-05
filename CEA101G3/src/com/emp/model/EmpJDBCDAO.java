package com.emp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PHIL";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO employee (emp_id, emp_user_id, emp_user_pwd, emp_name, emp_status) VALUES ('E'|| EMPLOYEE_ID_SEQ.NEXTVAL,?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT * FROM employee WHERE emp_id=?";
	private static final String GET_ALL_STMT = "SELECT * FROM employee ORDER BY emp_id";
	private static final String UPDATE = "UPDATE employee SET emp_user_id=?, emp_user_pwd=?, emp_name=?, emp_status=? WHERE emp_id=?";
	private static final String DELETE = "DELETE FROM employee WHERE emp_id=?";
	private static final String GET_USER = "SELECT * FROM EMPLOYEE WHERE EMP_USER_ID = ? AND EMP_USER_PWD = ?";

	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_user_id());
			pstmt.setString(2, empVO.getEmp_user_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setInt(4, empVO.getEmp_status());

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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_user_id());
			pstmt.setString(2, empVO.getEmp_user_pwd());
			pstmt.setString(3, empVO.getEmp_name());
			pstmt.setInt(4, empVO.getEmp_status());
			pstmt.setString(5, empVO.getEmp_id());

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
	public void delete(String emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_id);

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
	public EmpVO findByPrimaryKey(String emp_id) {

		EmpVO empVO = null;
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
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_user_id(rs.getString("emp_user_id"));
				empVO.setEmp_user_pwd(rs.getString("emp_user_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_status(rs.getInt("emp_status"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
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
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_user_id(rs.getString("emp_user_id"));
				empVO.setEmp_user_pwd(rs.getString("emp_user_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				list.add(empVO);
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

	public EmpVO getUser(String emp_user_id, String emp_user_pwd) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_USER);

			pstmt.setString(1, emp_user_id);
			pstmt.setString(2, emp_user_pwd);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_user_id(rs.getString("emp_user_id"));
				empVO.setEmp_user_pwd(rs.getString("emp_user_pwd"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_status(rs.getInt("emp_status"));
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
//				empVO = null;

			e.printStackTrace();
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
		return empVO;
	}

	public static void main(String[] args) {
		EmpJDBCDAO dao = new EmpJDBCDAO();
		EmpVO vo = new EmpVO();

		// 新增
		vo.setEmp_user_id("aaa");
		vo.setEmp_user_pwd("aaa");
		vo.setEmp_name("馬保國");
		vo.setEmp_status(2);
		dao.insert(vo);

		// 修改
//		vo.setEmp_id("E10001");
//		vo.setEmp_user_id("rty");
//		vo.setEmp_user_pwd("asd");
//		vo.setEmp_name("潘麗珍");
//		vo.setEmp_status(2);
//		dao.update(vo);

		// 刪除
//		dao.delete("E10002");

		// 查詢
//		vo = dao.findByPrimaryKey("E10003");
//		System.out.println(vo.getEmp_id());
//		System.out.println(vo.getEmp_user_id());
//		System.out.println(vo.getEmp_user_pwd());
//		System.out.println(vo.getEmp_name());
//		System.out.println(vo.getEmp_status());

//		List<EmpVO> list = dao.getAll();
//		for(EmpVO voo : list) {
//			System.out.print(voo.getEmp_id());
//			System.out.print(voo.getEmp_user_id());
//			System.out.print(voo.getEmp_user_pwd());
//			System.out.print(voo.getEmp_name());
//			System.out.print(voo.getEmp_status());
//			System.out.println();		
//		}

//		vo = dao.getUser("a123451", "a12345");
//		System.out.println(vo);
//		System.out.println(vo.getEmp_id());
//		System.out.println(vo.getEmp_user_id());
//		System.out.println(vo.getEmp_user_pwd());
//		System.out.println(vo.getEmp_name());
//		System.out.println(vo.getEmp_status());
//		

		System.out.println("好ㄌ拉");
	}
}
