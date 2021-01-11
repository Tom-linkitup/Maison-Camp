package com.room_promotion.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Room_promotionDAO implements Room_promotionDAO_interface {
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
			"INSERT INTO room_promotion (room_promotion_id,room_category_id,room_promotion_info,room_discount,room_prom_start_date,room_prom_end_date) VALUES ('RP'||room_promotion_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT room_promotion_id,room_category_id,room_promotion_info,room_discount,to_char(room_prom_start_date,'yyyy-mm-dd')room_prom_start_date,to_char(room_prom_end_date,'yyyy-mm-dd')room_prom_end_date FROM room_promotion order by room_promotion_id";
		private static final String GET_ONE_STMT = 
			"SELECT room_promotion_id,room_category_id,room_promotion_info,room_discount,to_char(room_prom_start_date,'yyyy-mm-dd')room_prom_start_date,to_char(room_prom_end_date,'yyyy-mm-dd')room_prom_end_date FROM room_promotion where room_promotion_id=?";
		private static final String DELETE = 
			"DELETE FROM room_promotion where room_promotion_id = ?";
		private static final String UPDATE = 
			"UPDATE room_promotion set room_category_id=?, room_promotion_info=?, room_discount=?, room_prom_start_date=?, room_prom_end_date=? where room_promotion_id = ?";
		
		private static final String AllPast = 
		"SELECT room_promotion_id,room_category_id,room_promotion_info,room_discount,\r\n" + 
		"to_char(room_prom_start_date,'yyyy-mm-dd')room_prom_start_date,\r\n" + 
		"to_char(room_prom_end_date,'yyyy-mm-dd')room_prom_end_date  from room_promotion\r\n" + 
		"where    to_char(room_prom_start_date,'yyyy-mm-dd') < TO_CHAR(SYSDATE, 'YYYY-MM-DD')\r\n" + 
		"and      to_char(room_prom_end_date,'yyyy-mm-dd') < TO_CHAR(SYSDATE, 'YYYY-MM-DD') ";
				
		private static final String GET_ALL_NOW_STMT = 
		"SELECT room_promotion_id,room_category_id,room_promotion_info,room_discount,\r\n" + 
		"to_char(room_prom_start_date,'yyyy-mm-dd')room_prom_start_date,\r\n" + 
		"to_char(room_prom_end_date,'yyyy-mm-dd')room_prom_end_date  from room_promotion\r\n" + 
		"where    to_char(room_prom_start_date,'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')\r\n" + 
		"and      to_char(room_prom_end_date,'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') order by room_prom_end_date ";
		
		private static final String GET_ALL_FUTURE_STMT = 		
		"SELECT room_promotion_id,room_category_id,room_promotion_info,room_discount,\r\n" + 
		"to_char(room_prom_start_date,'yyyy-mm-dd')room_prom_start_date,\r\n" + 
		"to_char(room_prom_end_date,'yyyy-mm-dd')room_prom_end_date  from room_promotion\r\n" + 
		"where    to_char(room_prom_start_date,'yyyy-mm-dd') > TO_CHAR(SYSDATE, 'YYYY-MM-DD')\r\n" + 
		"and      to_char(room_prom_end_date,'yyyy-mm-dd') > TO_CHAR(SYSDATE, 'YYYY-MM-DD') order by room_prom_start_date ";
		
		
		
		
		
		
		@Override
public void insert(Room_promotionVO room_promotionVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, room_promotionVO.getRoom_category_id());
				pstmt.setString(2, room_promotionVO.getRoom_promotion_info());
				pstmt.setDouble(3, room_promotionVO.getRoom_discount());
				pstmt.setDate(4, room_promotionVO.getRoom_prom_start_date());
				pstmt.setDate(5, room_promotionVO.getRoom_prom_end_date());

				pstmt.executeUpdate();

				// Handle any driver errors
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
		public void update(Room_promotionVO room_promotionVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, room_promotionVO.getRoom_category_id());
				pstmt.setString(2, room_promotionVO.getRoom_promotion_info());
				pstmt.setDouble(3, room_promotionVO.getRoom_discount());
				pstmt.setDate(4, room_promotionVO.getRoom_prom_start_date());
				pstmt.setDate(5, room_promotionVO.getRoom_prom_end_date());
				pstmt.setString(6, room_promotionVO.getRoom_promotion_id());
				pstmt.executeUpdate();

				// Handle any driver errors
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
		public void delete(String room_promotion_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, room_promotion_id);

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
		public Room_promotionVO findByPrimaryKey(String room_promotion_id) {

			Room_promotionVO room_promotionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, room_promotion_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					//room_promotionVo 也稱為 Domain objects
					room_promotionVO = new Room_promotionVO();
					room_promotionVO.setRoom_promotion_id(rs.getString("room_promotion_id"));
					room_promotionVO.setRoom_category_id(rs.getString("room_category_id"));
					room_promotionVO.setRoom_promotion_info(rs.getString("room_promotion_info"));
					room_promotionVO.setRoom_discount(rs.getDouble("room_discount"));
					room_promotionVO.setRoom_prom_start_date(rs.getDate("room_prom_start_date"));
					room_promotionVO.setRoom_prom_end_date(rs.getDate("room_prom_end_date"));
				}

				// Handle any driver errors
			}catch (SQLException se) {
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
			return room_promotionVO;
		}
		
		@Override
		public List<Room_promotionVO> getAll() {
			List<Room_promotionVO> list = new ArrayList<Room_promotionVO>();
			Room_promotionVO room_promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					room_promotionVO = new Room_promotionVO();
					room_promotionVO.setRoom_promotion_id(rs.getString("room_promotion_id"));
					room_promotionVO.setRoom_category_id(rs.getString("room_category_id"));
					room_promotionVO.setRoom_promotion_info(rs.getString("room_promotion_info"));
					room_promotionVO.setRoom_discount(rs.getDouble("room_discount"));
					room_promotionVO.setRoom_prom_start_date(rs.getDate("room_prom_start_date"));
					room_promotionVO.setRoom_prom_end_date(rs.getDate("room_prom_end_date"));
					list.add(room_promotionVO); // Store the row in the list
					
					
				
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
			return list;
		}
		
		@Override
		public List<Room_promotionVO> getAllPast() {
			List<Room_promotionVO> pastlist = new ArrayList<Room_promotionVO>();
			Room_promotionVO room_promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(AllPast);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					room_promotionVO = new Room_promotionVO();
					room_promotionVO.setRoom_promotion_id(rs.getString("room_promotion_id"));
					room_promotionVO.setRoom_category_id(rs.getString("room_category_id"));
					room_promotionVO.setRoom_promotion_info(rs.getString("room_promotion_info"));
					room_promotionVO.setRoom_discount(rs.getDouble("room_discount"));
					room_promotionVO.setRoom_prom_start_date(rs.getDate("room_prom_start_date"));
					room_promotionVO.setRoom_prom_end_date(rs.getDate("room_prom_end_date"));
					pastlist.add(room_promotionVO); // Store the row in the list
					
					
				
				}

				// Handle any driver errors
			}catch (SQLException se) {
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
			return pastlist;
		}
		
		@Override
		public List<Room_promotionVO> getAllNow() {
			List<Room_promotionVO> list = new ArrayList<Room_promotionVO>();
			Room_promotionVO room_promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_NOW_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					room_promotionVO = new Room_promotionVO();
					room_promotionVO.setRoom_promotion_id(rs.getString("room_promotion_id"));
					room_promotionVO.setRoom_category_id(rs.getString("room_category_id"));
					room_promotionVO.setRoom_promotion_info(rs.getString("room_promotion_info"));
					room_promotionVO.setRoom_discount(rs.getDouble("room_discount"));
					room_promotionVO.setRoom_prom_start_date(rs.getDate("room_prom_start_date"));
					room_promotionVO.setRoom_prom_end_date(rs.getDate("room_prom_end_date"));
					list.add(room_promotionVO); // Store the row in the list
					
					
				
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
			return list;
		}
		
		
		
		@Override
		public List<Room_promotionVO> getAllFuture() {
			List<Room_promotionVO> list = new ArrayList<Room_promotionVO>();
			Room_promotionVO room_promotionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_FUTURE_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					room_promotionVO = new Room_promotionVO();
					room_promotionVO.setRoom_promotion_id(rs.getString("room_promotion_id"));
					room_promotionVO.setRoom_category_id(rs.getString("room_category_id"));
					room_promotionVO.setRoom_promotion_info(rs.getString("room_promotion_info"));
					room_promotionVO.setRoom_discount(rs.getDouble("room_discount"));
					room_promotionVO.setRoom_prom_start_date(rs.getDate("room_prom_start_date"));
					room_promotionVO.setRoom_prom_end_date(rs.getDate("room_prom_end_date"));
					list.add(room_promotionVO); // Store the row in the list
					
					
				
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
			return list;
		}
		
		
		
		
		
		
		
		
		public static void main(String[] args) {

		Room_promotionDAO dao = new Room_promotionDAO();
		
//		// 新增
//		Room_promotionVO room_promotionVO1 = new Room_promotionVO();
//		room_promotionVO1.setRoom_category_id("DOUBLE");
//		room_promotionVO1.setRoom_promotion_info("阿就雙人房型促銷");
//		room_promotionVO1.setRoom_discount(new Double(0.3));
//		room_promotionVO1.setRoom_prom_start_date(java.sql.Date.valueOf("2020-12-01"));
//		room_promotionVO1.setRoom_prom_end_date(java.sql.Date.valueOf("2020-12-31"));
//		dao.insert(room_promotionVO1);
//		
//		
////		// 修改
//		Room_promotionVO room_promotionVO2 = new Room_promotionVO();
//		room_promotionVO2.setRoom_promotion_id("RP10001");
//		room_promotionVO2.setRoom_category_id("QUADRUPLE");
//		room_promotionVO2.setRoom_promotion_info("被我改成四人房了吧");
//		room_promotionVO2.setRoom_discount(new Double(0.7));
//		room_promotionVO2.setRoom_prom_start_date(java.sql.Date.valueOf("2020-12-16"));
//		room_promotionVO2.setRoom_prom_end_date(java.sql.Date.valueOf("2020-12-20"));
//		dao.update(room_promotionVO2);		
//		
//		// 刪除		
//		dao.delete("RM4");		
//		
//		// 查詢
//		Room_promotionVO room_promotionVO3 = dao.findByPrimaryKey("RP10002");
//		System.out.print(room_promotionVO3.getRoom_promotion_id() + ",");
//		System.out.print(room_promotionVO3.getRoom_category_id() + ",");
//		System.out.print(room_promotionVO3.getRoom_promotion_info() + ",");
//		System.out.print(room_promotionVO3.getRoom_discount() + ",");
//		System.out.print(room_promotionVO3.getRoom_prom_start_date() + ",");
//		System.out.print(room_promotionVO3.getRoom_prom_end_date() + ",");
//		System.out.println("---------------------");		
//		
//		// 查詢
				List<Room_promotionVO> list = dao.getAllPast();
				for (Room_promotionVO aRoom_promotion : list) {
		System.out.print(aRoom_promotion.getRoom_promotion_id() + ",");
		System.out.print(aRoom_promotion.getRoom_category_id() + ",");
		System.out.print(aRoom_promotion.getRoom_promotion_info() + ",");
		System.out.print(aRoom_promotion.getRoom_discount() + ",");
		System.out.print(aRoom_promotion.getRoom_prom_start_date() + ",");
		System.out.print(aRoom_promotion.getRoom_prom_end_date() + ",");
		System.out.println();
				}		
		}
}