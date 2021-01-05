package com.func.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;

public class FuncJDBCDAO implements FuncDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PHIL";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FUNC (func_id, func_name, func_info) VALUES((SELECT  LPAD(?,4,'0') FROM DUAL),?,?)";
	private static final String UPDATE = "UPDATE func SET func_name=?, func_info=? WHERE func_id =?";
	private static final String GET_ONE_STMT = "SELECT * FROM func WHERE func_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM func ORDER BY func_id";
	private static final String DELETE = "DELETE FROM func WHERE func_id=?";

	@Override
	public void insert(FuncVO funcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, funcVO.getFunc_id());
			pstmt.setString(2, funcVO.getFunc_name());
			pstmt.setString(3, funcVO.getFunc_info());

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
	public void update(FuncVO funcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, funcVO.getFunc_name());
			pstmt.setString(2, funcVO.getFunc_info());
			pstmt.setString(3, funcVO.getFunc_id());

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
	public void delete(String func_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, func_id);

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
	public FuncVO findByPrimaryKey(String func_id) {
		FuncVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, func_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				funcVO = new FuncVO();
				funcVO.setFunc_id(rs.getString("func_id"));
				funcVO.setFunc_name(rs.getString("func_name"));
				funcVO.setFunc_info(rs.getString("func_info"));

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
		return funcVO;
	}

	@Override
	public List<FuncVO> getAll() {
		List<FuncVO> list = new ArrayList<FuncVO>();
		FuncVO funcVO = null;
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
				funcVO = new FuncVO();
				funcVO.setFunc_id(rs.getString("func_id"));
				funcVO.setFunc_name(rs.getString("func_name"));
				funcVO.setFunc_info(rs.getString("func_info"));
				list.add(funcVO);
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

		FuncJDBCDAO dao = new FuncJDBCDAO();
		FuncVO vo = new FuncVO();
		List<FuncVO> list = null;
		vo.setFunc_id("13");
		vo.setFunc_name("商城管理");
		vo.setFunc_info("AAAAAAAAAA");
		dao.insert(vo);

//		vo.setFunc_id("3");
//		vo.setFunc_name("員工管理");
//		vo.setFunc_info("BBBBBBBBBB");
//		dao.update(vo);

//		dao.delete("13");

//		vo = dao.findByPrimaryKey("3");
//		System.out.println(vo.getFunc_id());
//		System.out.println(vo.getFunc_name());
//		System.out.println(vo.getFunc_info());

		list = dao.getAll();
		for (FuncVO voo : list) {
			System.out.println(voo.getFunc_id());
			System.out.println(voo.getFunc_name());
			System.out.println(voo.getFunc_info());
		}
	}

}
