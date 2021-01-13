package com.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.roomphoto.model.RoomPhotoVO;

public class RoomJDBCDAO implements RoomDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String Add_Stmt = "INSERT INTO ROOM (ROOM_ID, ROOM_CATEGORY_ID, STATUS) VALUES('RM' || ROOM_ID_seq.NEXTVAL,?,?)";
	private static final String Update_Stmt = "UPDATE ROOM SET ROOM_CATEGORY_ID=?, STATUS=? WHERE ROOM_ID=?";
	private static final String Delete_Stmt = "DELETE FROM ROOM WHERE ROOM_ID=?";
	private static final String Get_All_Stmt = "SELECT ROOM_ID, ROOM_CATEGORY_ID, STATUS FROM ROOM ORDER BY ROOM_ID";
	private static final String Get_By_RCT = "SELECT ROOM_ID, ROOM_CATEGORY_ID, STATUS FROM ROOM WHERE ROOM_CATEGORY_ID=? ORDER BY STATUS";
	private static final String Get_One_Stmt = "SELECT ROOM_ID, ROOM_CATEGORY_ID, STATUS FROM ROOM WHERE ROOM_ID=?";

	@Override
	public void addRoom(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, roomVO.getRoom_category_id());
			pstmt.setInt(2, roomVO.getStatus());
			
			int addRoom = pstmt.executeUpdate();
			System.out.println("新增"+ addRoom + "筆房間資料");
	
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
	public void updateRoom(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Stmt);
			
			pstmt.setString(1, roomVO.getRoom_category_id());
			pstmt.setInt(2, roomVO.getStatus());
			pstmt.setString(3, roomVO.getRoom_id());
			
			int updateRoom = pstmt.executeUpdate();
			System.out.println("更新"+  updateRoom + "筆房間資料");
	
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
	public void deleteRoom(String room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, room_id);
			
			int deleteRoom = pstmt.executeUpdate();
			System.out.println("刪除"+  deleteRoom + "筆房間資料");
	
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
	public RoomVO findByRoomId(String room_id) {
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, room_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoom_id(rs.getString("ROOM_ID"));
				roomVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomVO.setStatus(rs.getInt("STATUS"));
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
		return roomVO;
	}

	@Override
	public List<RoomVO> getByRoomCategoryId(String room_category_id) {
		List<RoomVO> roomList = new ArrayList<>();
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_By_RCT);
			
			pstmt.setString(1, room_category_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoom_id(rs.getString("ROOM_ID"));
				roomVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomVO.setStatus(rs.getInt("STATUS"));
				roomList.add(roomVO);
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
		return roomList;
	}

	@Override
	public List<RoomVO> getAllRoom() {
		List<RoomVO> roomList = new ArrayList<>();
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoom_id(rs.getString("ROOM_ID"));
				roomVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomVO.setStatus(rs.getInt("STATUS"));
				roomList.add(roomVO);
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
		return roomList;
	}

	@Override
	public void updateRmStatus(Integer status, String room_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRmOccupy(Integer occupy, String room_id) {
		// TODO Auto-generated method stub
		
	}

}
