package com.repair.model;

import java.util.*;
import java.sql.*;
import com.repair.model.RepairDAO_interface;
import com.repair.model.RepairVO;


public class RepairJDBCDAO implements RepairDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO repair(repair_id,room_id,emp_id,repair_info,status) VALUES ('RE' || Repair_id_seq.NEXTVAL, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT repair_id,room_id,emp_id,repair_info,status FROM repair order by repair_id";
	private static final String GET_ONE_STMT = 
		"SELECT repair_id,room_id,emp_id,repair_info,status FROM repair where repair_id = ?";
	private static final String DELETE = 
		"DELETE FROM repair where repair_id = ?";
	private static final String UPDATE = 
		"UPDATE repair set room_id=?, emp_id=?, repair_info=?,status=? where repair_id = ?";
	
	private static final String status1= 
	"SELECT repair_id,room_id,emp_id,repair_info,status FROM repair where status =any (select  status from repair where status=1)"; 
	
	private static final String status0= 
			"SELECT repair_id,room_id,emp_id,repair_info,status FROM repair where status =any (select  status from repair where status=0)";

	@Override
	public void insert(RepairVO repairVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, repairVO.getRoom_id());
			pstmt.setString(2, repairVO.getEmp_id());
			pstmt.setString(3, repairVO.getRepair_info());
			pstmt.setInt(4, repairVO.getStatus());
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(RepairVO repairVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, repairVO.getRoom_id());
			pstmt.setString(2, repairVO.getEmp_id());
			pstmt.setString(3, repairVO.getRepair_info());
			pstmt.setInt(4, repairVO.getStatus());
			pstmt.setString(5, repairVO.getRepair_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(String repair_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, repair_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public RepairVO findByPrimaryKey(String repair_id) {

		RepairVO repairVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repair_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repairVo 也稱為 Domain objects
				repairVO = new RepairVO();
				repairVO.setRepair_id(rs.getString("repair_id"));
				repairVO.setRoom_id(rs.getString("room_id"));
				repairVO.setEmp_id(rs.getString("emp_id"));
				repairVO.setRepair_info(rs.getString("repair_info"));
				repairVO.setStatus(rs.getInt("status"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return repairVO;
	}

	@Override
	public List<RepairVO> getAll() {
		List<RepairVO> list = new ArrayList<RepairVO>();
		RepairVO repairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repairVO 也稱為 Domain objects
				repairVO = new RepairVO();
				repairVO.setRepair_id(rs.getString("repair_id"));
				repairVO.setRoom_id(rs.getString("room_id"));
				repairVO.setEmp_id(rs.getString("emp_id"));
				repairVO.setRepair_info(rs.getString("repair_info"));
				repairVO.setStatus(rs.getInt("status"));
				list.add(repairVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	@Override
	public List<RepairVO> getStatus1() {
		List<RepairVO> status1list = new ArrayList<RepairVO>();
		RepairVO repairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(status1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repairVO 也稱為 Domain objects
				repairVO = new RepairVO();
				repairVO.setRepair_id(rs.getString("repair_id"));
				repairVO.setRoom_id(rs.getString("room_id"));
				repairVO.setEmp_id(rs.getString("emp_id"));
				repairVO.setRepair_info(rs.getString("repair_info"));
				repairVO.setStatus(rs.getInt("status"));
				status1list.add(repairVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return status1list;
	}

	@Override
	public List<RepairVO> getStatus0() {
		List<RepairVO> status0list = new ArrayList<RepairVO>();
		RepairVO repairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(status0);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repairVO 也稱為 Domain objects
				repairVO = new RepairVO();
				repairVO.setRepair_id(rs.getString("repair_id"));
				repairVO.setRoom_id(rs.getString("room_id"));
				repairVO.setEmp_id(rs.getString("emp_id"));
				repairVO.setRepair_info(rs.getString("repair_info"));
				repairVO.setStatus(rs.getInt("status"));
				status0list.add(repairVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return status0list;
	}
	
	
	
//	public static void main(String[] args) {
//
//		RepairJDBCDAO dao = new RepairJDBCDAO();
//
//		// 新增
//		RepairVO repairVO1 = new RepairVO();
//		repairVO1.setRoom_id("RM10010");
//		repairVO1.setEmp_id("E10009");
//		repairVO1.setRepair_info("馬通不通");
//		dao.insert(repairVO1);

//		// 修改
//		RepairVO repairVO2 = new RepairVO();
//		repairVO2.setRepair_id("RE1");
//		repairVO2.setRoom_id("RM1");
//		repairVO2.setEmp_id("E10002");
//		repairVO2.setRepair_info("原本馬通不通，現在已修繕完成");
//		dao.update(repairVO2);
//
//		// 刪除
//		dao.delete("RE5");
//
//		// 查詢
//		RepairVO repairVO3 = dao.findByPrimaryKey("RE2");
//		System.out.print(repairVO3.getRepair_id() + ",");
//		System.out.print(repairVO3.getRoom_id() + ",");
//		System.out.print(repairVO3.getEmp_id() + ",");
//		System.out.print(repairVO3.getRepair_info() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<RepairVO> list = dao.getAll();
//		for (RepairVO aRepair : list) {
//			System.out.print(aRepair.getRepair_id() + ",");
//			System.out.print(aRepair.getRoom_id() + ",");
//			System.out.print(aRepair.getEmp_id() + ",");
//			System.out.print(aRepair.getRepair_info() + ",");
//			System.out.println();
//		}
//	}
}