package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.room.model.RoomVO;

public class MemberJDBCDAO implements MemberDAO_Interface {
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	private static final String Add_Stmt = "INSERT INTO MEMBER (MEM_ID, USER_ID, USER_PWD, NAME, PHONE, NATION, EMAIL, SEXUAL, NOTE, BIRTHDAY, PERSONAL_ID, STATUS, PAYMENT) VALUES('M' || mem_id_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String Update_Stmt = "UPDATE MEMBER SET USER_ID=?, USER_PWD=?, NAME=?, PHONE=?, NATION=?, EMAIL=?, SEXUAL=?, NOTE=?, BIRTHDAY=?, PERSONAL_ID=?, STATUS=?, PAYMENT=? WHERE MEM_ID=?";
	private static final String Update_Front_Stmt = "UPDATE MEMBER SET USER_ID=?, NAME=?, PHONE=?, BIRTHDAY=?, PERSONAL_ID=?, NATION=?, SEXUAL=? WHERE MEM_ID=?";
	private static final String Update_Pwd = "UPDATE MEMBER SET USER_PWD=? WHERE MEM_ID=?";
	private static final String Update_Credit = "UPDATE MEMBER SET PAYMENT=? WHERE MEM_ID=?";
	private static final String Update_Status = "UPDATE MEMBER SET STATUS=? WHERE MEM_ID=?";
	private static final String Get_One_By_Email = "SELECT MEM_ID, USER_ID, USER_PWD, NAME, PHONE, NATION, EMAIL, SEXUAL, NOTE, BIRTHDAY, PERSONAL_ID, STATUS, PAYMENT FROM MEMBER WHERE EMAIL=?";
	private static final String Get_All_Stmt = "SELECT MEM_ID, USER_ID, USER_PWD, NAME, PHONE, NATION, EMAIL, SEXUAL, NOTE, BIRTHDAY, PERSONAL_ID, STATUS, PAYMENT FROM MEMBER ORDER BY MEM_ID";
	private static final String Get_One_Stmt = "SELECT MEM_ID, USER_ID, USER_PWD, NAME, PHONE, NATION, EMAIL, SEXUAL, NOTE, BIRTHDAY, PERSONAL_ID, STATUS, PAYMENT FROM MEMBER WHERE MEM_ID=?";
	private static final String GetUser = "SELECT * FROM MEMBER WHERE EMAIL=? AND USER_PWD=?";
	@Override
	public void addMember(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, memberVO.getUser_id());
			pstmt.setString(2, memberVO.getUser_pwd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getPhone());
			pstmt.setString(5, memberVO.getNation());
			pstmt.setString(6, memberVO.getEmail());
			pstmt.setString(7, memberVO.getSexual());
			pstmt.setString(8, memberVO.getNote());
			pstmt.setDate(9, memberVO.getBirthday());
			pstmt.setString(10, memberVO.getPersonal_id());
			pstmt.setInt(11, memberVO.getStatus());
			pstmt.setString(12, memberVO.getPayment());
			
			int addMem = pstmt.executeUpdate();
			System.out.println("新增"+ addMem + "筆會員資料");
	
		}catch (ClassNotFoundException e) {
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
	public void updateMember(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Stmt);
			
			pstmt.setString(1, memberVO.getUser_id());
			pstmt.setString(2, memberVO.getUser_pwd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getPhone());
			pstmt.setString(5, memberVO.getNation());
			pstmt.setString(6, memberVO.getEmail());
			pstmt.setString(7, memberVO.getSexual());
			pstmt.setString(8, memberVO.getNote());
			pstmt.setDate(9, memberVO.getBirthday());
			pstmt.setString(10, memberVO.getPersonal_id());
			pstmt.setInt(11, memberVO.getStatus());
			pstmt.setString(12, memberVO.getPayment());
			pstmt.setString(13, memberVO.getMem_id());
			
			int updateMem = pstmt.executeUpdate();
			System.out.println("更新"+ updateMem + "筆會員資料");
	
		}catch (ClassNotFoundException e) {
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
	public MemberVO findByMemberId(String mem_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("MEM_ID"));
				memberVO.setUser_id(rs.getString("USER_ID"));
				memberVO.setUser_pwd(rs.getString("USER_PWD"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setNation(rs.getString("NATION"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setSexual(rs.getString("SEXUAL"));
				memberVO.setNote(rs.getString("NOTE"));
				memberVO.setBirthday(rs.getDate("BIRTHDAY"));
				memberVO.setPersonal_id(rs.getString("PERSONAL_ID"));
				memberVO.setStatus(rs.getInt("STATUS"));
				memberVO.setPayment(rs.getString("PAYMENT"));
			}
	
		}catch (ClassNotFoundException e) {
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = new ArrayList<>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("MEM_ID"));
				memberVO.setUser_id(rs.getString("USER_ID"));
				memberVO.setUser_pwd(rs.getString("USER_PWD"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setNation(rs.getString("NATION"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setSexual(rs.getString("SEXUAL"));
				memberVO.setNote(rs.getString("NOTE"));
				memberVO.setBirthday(rs.getDate("BIRTHDAY"));
				memberVO.setPersonal_id(rs.getString("PERSONAL_ID"));
				memberVO.setStatus(rs.getInt("STATUS"));
				memberVO.setPayment(rs.getString("PAYMENT"));
				memList.add(memberVO);
			}
	
		}catch (ClassNotFoundException e) {
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
		return memList;
	}

	@Override
	public MemberVO isUser(String email, String user_pwd) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetUser);
			pstmt.setString(1, email);
			pstmt.setString(2, user_pwd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("MEM_ID"));
				memberVO.setUser_id(rs.getString("USER_ID"));
				memberVO.setUser_pwd(rs.getString("USER_PWD"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setNation(rs.getString("NATION"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setSexual(rs.getString("SEXUAL"));
				memberVO.setNote(rs.getString("NOTE"));
				memberVO.setBirthday(rs.getDate("BIRTHDAY"));
				memberVO.setPersonal_id(rs.getString("PERSONAL_ID"));
				memberVO.setStatus(rs.getInt("STATUS"));
				memberVO.setPayment(rs.getString("PAYMENT"));			
			}
	
		}catch (ClassNotFoundException e) {
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
		return memberVO;
	}

	@Override
	public void updateFrontMember(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Front_Stmt);
			
			pstmt.setString(1, memberVO.getUser_id());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getPhone());
			pstmt.setDate(4, memberVO.getBirthday());
			pstmt.setString(5, memberVO.getPersonal_id());
			pstmt.setString(6, memberVO.getNation());
			pstmt.setString(7, memberVO.getSexual());
			pstmt.setString(8, memberVO.getMem_id());
			
			int updateFrontMem = pstmt.executeUpdate();
			System.out.println("更新"+ updateFrontMem + "筆前台會員資料");
	
		}catch (ClassNotFoundException e) {
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
	public void updatePwd(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Pwd);
			
			pstmt.setString(1, memberVO.getUser_pwd());
			pstmt.setString(2, memberVO.getMem_id());
			
			int updatePwd = pstmt.executeUpdate();
			System.out.println("更新"+ updatePwd + "筆前台會員密碼");
	
		}catch (ClassNotFoundException e) {
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
	public void updateCredit(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Credit);
			
			pstmt.setString(1, memberVO.getPayment());
			pstmt.setString(2, memberVO.getMem_id());
			
			int updateCredit = pstmt.executeUpdate();
			System.out.println("更新"+ updateCredit + "筆前台會員卡號");
	
		}catch (ClassNotFoundException e) {
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
	public void updateStatus(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Status);
			
			pstmt.setInt(1, memberVO.getStatus());
			pstmt.setString(2, memberVO.getMem_id());
			
			int updateStatus = pstmt.executeUpdate();
			System.out.println("驗證"+ updateStatus + "筆前台會員");
	
		}catch (ClassNotFoundException e) {
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
	public MemberVO findByEmail(String email) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_By_Email);
			
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_id(rs.getString("MEM_ID"));
				memberVO.setUser_id(rs.getString("USER_ID"));
				memberVO.setUser_pwd(rs.getString("USER_PWD"));
				memberVO.setName(rs.getString("NAME"));
				memberVO.setPhone(rs.getString("PHONE"));
				memberVO.setNation(rs.getString("NATION"));
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setSexual(rs.getString("SEXUAL"));
				memberVO.setNote(rs.getString("NOTE"));
				memberVO.setBirthday(rs.getDate("BIRTHDAY"));
				memberVO.setPersonal_id(rs.getString("PERSONAL_ID"));
				memberVO.setStatus(rs.getInt("STATUS"));
				memberVO.setPayment(rs.getString("PAYMENT"));
			}
	
		}catch (ClassNotFoundException e) {
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
		return memberVO;
	}
}
