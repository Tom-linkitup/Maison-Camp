package com.repair.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import com.repair.model.RepairDAO_interface;
import com.repair.model.RepairVO;


public class RepairDAO implements RepairDAO_interface {
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
	
	private static final String INSERT_STMT = 
		"INSERT INTO repair(repair_id,room_id,emp_id,repair_info,status,repair_photo) VALUES ('RE' || Repair_id_seq.NEXTVAL, ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT repair_id,room_id,emp_id,repair_info,status,repair_photo FROM repair order by repair_id desc";
	private static final String GET_ONE_STMT = 
		"SELECT repair_id,room_id,emp_id,repair_info,status,repair_photo FROM repair where repair_id = ?";
	private static final String DELETE = 
		"DELETE FROM repair where repair_id = ?";
	private static final String UPDATE = 
		"UPDATE repair set room_id=?, emp_id=?, repair_info=?,status=?,repair_photo=? where repair_id = ?";
	
	private static final String status1= 
	"SELECT repair_id,room_id,emp_id,repair_info,status,repair_photo FROM repair where status =any (select  status from repair where status=1)"; 
	
	private static final String status0= 
			"SELECT repair_id,room_id,emp_id,repair_info,status,repair_photo FROM repair where status =any (select  status from repair where status=0)";

	@Override
	public void insert(RepairVO repairVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, repairVO.getRoom_id());
			pstmt.setString(2, repairVO.getEmp_id());
			pstmt.setString(3, repairVO.getRepair_info());
			pstmt.setInt(4, repairVO.getStatus());
			pstmt.setBytes(5, repairVO.getRepair_photo());
		
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
	public void update(RepairVO repairVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, repairVO.getRoom_id());
			pstmt.setString(2, repairVO.getEmp_id());
			pstmt.setString(3, repairVO.getRepair_info());
			pstmt.setInt(4, repairVO.getStatus());
			pstmt.setBytes(5, repairVO.getRepair_photo());
			pstmt.setString(6, repairVO.getRepair_id());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
			se.printStackTrace();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, repair_id);

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
	public RepairVO findByPrimaryKey(String repair_id) {

		RepairVO repairVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
				repairVO.setRepair_photo(rs.getBytes("repair_photo"));
				
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

			con = ds.getConnection();
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
				repairVO.setRepair_photo(rs.getBytes("repair_photo"));
				list.add(repairVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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
				repairVO.setRepair_photo(rs.getBytes("repair_photo"));
				status1list.add(repairVO); // Store the row in the list
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

			con = ds.getConnection();
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
				repairVO.setRepair_photo(rs.getBytes("repair_photo"));
				status0list.add(repairVO); // Store the row in the list
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
		return status0list;
	}
	
	
	public static byte[] getPicByteArray() throws IOException{
		File file = new File("/img/repair_photo");
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		fis.close();
		
		return baos.toByteArray();
		
	}
	public static void readPic(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("picture");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

//	}
}