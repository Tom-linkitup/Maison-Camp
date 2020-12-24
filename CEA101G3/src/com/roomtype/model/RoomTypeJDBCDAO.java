package com.roomtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeJDBCDAO implements RoomTypeDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String Add_Stmt = "INSERT INTO ROOM_CATEGORY (ROOM_CATEGORY_ID, ROOM_NAME, ROOM_TYPE, ROOM_PRICE, AREA, ROOM_CATEGORY_STATUS, ROOM_INFO)" 
											+ "VALUES(?,?,?,?,?,?,?)";
	private static final String Update_Stmt = "UPDATE ROOM_CATEGORY SET ROOM_NAME=?, ROOM_TYPE=?, ROOM_PRICE=?, AREA=?, ROOM_CATEGORY_STATUS=?, ROOM_INFO=? WHERE ROOM_CATEGORY_ID=?";
	private static final String Delete_Stmt = "DELETE FROM ROOM_CATEGORY WHERE ROOM_CATEGORY_ID=?";
	private static final String Get_All_Stmt = "SELECT ROOM_CATEGORY_ID, ROOM_NAME, ROOM_TYPE, ROOM_PRICE, AREA, ROOM_CATEGORY_STATUS, ROOM_INFO FROM ROOM_CATEGORY ORDER BY ROOM_CATEGORY_ID";
	private static final String Get_One_Stmt = "SELECT ROOM_CATEGORY_ID, ROOM_NAME, ROOM_TYPE, ROOM_PRICE, AREA, ROOM_CATEGORY_STATUS, ROOM_INFO FROM ROOM_CATEGORY WHERE ROOM_CATEGORY_ID=?";
	@Override
	public void addRoomType(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, roomTypeVO.getRoom_category_id());
			pstmt.setString(2, roomTypeVO.getRoom_name());
			pstmt.setString(3, roomTypeVO.getRoom_type());
			pstmt.setInt(4, roomTypeVO.getRoom_price());
			pstmt.setInt(5, roomTypeVO.getArea());
			pstmt.setInt(6, roomTypeVO.getRoom_category_status());
			pstmt.setString(7, roomTypeVO.getRoom_info());
			
			int roomAdd = pstmt.executeUpdate();
			System.out.println("新增"+ roomAdd + "筆房型資料");
			
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
	public void updateRoomType(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Update_Stmt);
			
			pstmt.setString(1, roomTypeVO.getRoom_name());
			pstmt.setString(2, roomTypeVO.getRoom_type());
			pstmt.setInt(3, roomTypeVO.getRoom_price());
			pstmt.setInt(4, roomTypeVO.getArea());
			pstmt.setInt(5, roomTypeVO.getRoom_category_status());
			pstmt.setString(6, roomTypeVO.getRoom_info());
			
			pstmt.setString(7, roomTypeVO.getRoom_category_id());
			
			int roomUpdate = pstmt.executeUpdate();
			System.out.println("更新"+ roomUpdate + "筆房型資料");
			
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
	public void deleteRoomType(String room_category_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, room_category_id);
			
			int roomUpdate = pstmt.executeUpdate();
			System.out.println("刪除"+ roomUpdate + "筆房型資料");
			
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
	public RoomTypeVO findByRoomCategoryId(String room_category_id) {
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, room_category_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomTypeVO.setRoom_name(rs.getString("ROOM_NAME"));
				roomTypeVO.setRoom_type(rs.getString("ROOM_TYPE"));
				roomTypeVO.setRoom_price(rs.getInt("ROOM_PRICE"));
				roomTypeVO.setArea(rs.getInt("AREA"));
				roomTypeVO.setRoom_category_status(rs.getInt("ROOM_CATEGORY_STATUS"));
				roomTypeVO.setRoom_info(rs.getString("ROOM_INFO"));
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
		return roomTypeVO;
	}
	@Override
	public List<RoomTypeVO> getAllRoomType() {
		List<RoomTypeVO> roomTypeList = new ArrayList<>();
		RoomTypeVO roomTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_All_Stmt);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				roomTypeVO.setRoom_name(rs.getString("ROOM_NAME"));
				roomTypeVO.setRoom_type(rs.getString("ROOM_TYPE"));
				roomTypeVO.setRoom_price(rs.getInt("ROOM_PRICE"));
				roomTypeVO.setArea(rs.getInt("AREA"));
				roomTypeVO.setRoom_category_status(rs.getInt("ROOM_CATEGORY_STATUS"));
				roomTypeVO.setRoom_info(rs.getString("ROOM_INFO"));
				roomTypeList.add(roomTypeVO); // Store the row in the list
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
		return roomTypeList;
	}
	
//	public static void main(String[] args) {
//		RoomTypeJDBCDAO dao = new RoomTypeJDBCDAO();	
//		RoomTypeVO roomType1 = new RoomTypeVO();
		
//		roomType1.setRoom_category_id("TRIPLE");
//		roomType1.setRoom_name("野奢莊園-豪華三床帳");
//		roomType1.setRoom_type("3張單人床");
//		roomType1.setRoom_price(new Integer(14000));
//		roomType1.setArea(new Integer(15));
//		roomType1.setRoom_category_status(new Integer(1));
//		roomType1.setRoom_info("野奢莊園 Glamping，三大床共2帳");
//		dao.addRoomType(roomType1);
//		
//		RoomTypeVO roomType2 = new RoomTypeVO();
//		
//		roomType2.setRoom_category_id("RT02");
//		roomType2.setRoom_name("野奢莊園-豪華兩床帳");
//		roomType2.setRoom_type("2張雙人床");
//		roomType2.setRoom_price(new Integer(12000));
//		roomType2.setArea(new Integer(13));
//		roomType2.setRoom_category_status(new Integer(1));
//		roomType2.setRoom_info("野奢莊園 Glamping，兩大床共16帳");
//		dao.addRoomType(roomType2);
		
//		RoomTypeVO roomType3 = new RoomTypeVO();
//		
//		roomType3.setRoom_category_id("RT03");
//		roomType3.setRoom_name("野奢莊園-豪華四人帳");
//		roomType3.setRoom_type("4張雙人床");
//		roomType3.setRoom_price(new Integer(18000));
//		roomType3.setArea(new Integer(22));
//		roomType3.setRoom_category_status(new Integer(1));
//		roomType3.setRoom_info("野奢莊園 Glamping，四大床共8帳");
//		dao.addRoomType(roomType3);
		
//		RoomTypeVO roomTypeUpdate = new RoomTypeVO();
//		
//		roomTypeUpdate.setRoom_name("野奢莊園-豪華四人帳");
//		roomTypeUpdate.setRoom_type("4張雙人床");
//		roomTypeUpdate.setRoom_price(18000);
//		roomTypeUpdate.setArea(22);
//		roomTypeUpdate.setRoom_category_status(1);
//		roomTypeUpdate.setRoom_info("野奢莊園 Glamping，四大床共8帳");
//		roomTypeUpdate.setRoom_category_id("DOUBLE");
//		dao.updateRoomType(roomTypeUpdate);
		
//		dao.deleteRoomType("RT02");
		
//		RoomTypeVO roomTypeFind = dao.findByRoomCategoryId("RT02");
//		System.out.print(roomTypeFind.getRoom_category_id() + ",");
//		System.out.print(roomTypeFind.getRoom_name() + ",");
//		System.out.print(roomTypeFind.getRoom_type() + ",");
//		System.out.print(roomTypeFind.getArea() + ",");
//		System.out.print(roomTypeFind.getRoom_price() + ",");
//		int roomTypeStatus = roomTypeFind.getRoom_category_status();
//		if(roomTypeStatus == 1) {
//			System.out.print("上架,");		
//		}else {
//			System.out.print("下架,");
//		}
//		System.out.println(roomTypeFind.getRoom_info() + "!");
		
//		List<RoomTypeVO> roomTypeList = dao.getAllRoomType();
//		for(RoomTypeVO amp : roomTypeList) {
//			System.out.print(amp.getRoom_category_id() + ",");
//			System.out.print(amp.getRoom_name() + ",");
//			System.out.print(amp.getRoom_type() + ",");
//			System.out.print(amp.getArea() + ",");
//			System.out.print(amp.getRoom_price() + ",");
//			int roomTypeStatus = amp.getRoom_category_status();
//			if(roomTypeStatus == 1) {
//				System.out.print("上架,");		
//			}else {
//				System.out.print("下架,");
//			}
//			System.out.print(amp.getRoom_info() + "!");
//			System.out.println();
//		}
//	}
}