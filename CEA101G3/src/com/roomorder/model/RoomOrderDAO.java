package com.roomorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.roomorderdetail.model.RoomOrderDetailDAO;
import com.roomorderdetail.model.RoomOrderDetailVO;

public class RoomOrderDAO implements RoomOrderDAO_Interface {
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
	
	private static final String Add_Stmt = "INSERT INTO ROOM_ORDER (ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS) VALUES ('RD' || room_order_id_seq.NEXTVAL,?,?,?,?)";
	private static final String Update_Stmt = "UPDATE ROOM_ORDER SET MEM_ID=?, CHECK_IN_DATE=?, CHECK_OUT_DATE=?, STATUS=? WHERE ROOM_ORDER_ID=?";
	private static final String Delete_Stmt = "DELETE FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String Get_One_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String Get_All_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS FROM ROOM_ORDER ORDER BY ROOM_ORDER_ID";
	
	@Override
	public void addRoomOrder(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Add_Stmt);
			pstmt.setString(1, roomOrderVO.getMem_id());
			pstmt.setDate(2, roomOrderVO.getCheck_in_date());
			pstmt.setDate(3, roomOrderVO.getCheck_out_date());
			pstmt.setInt(4, roomOrderVO.getStatus());
			
			int orderAdd = pstmt.executeUpdate();
			System.out.println("新增"+ orderAdd + "筆訂房訂單資料");

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
	public void updateRoomOrder(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Update_Stmt);
			pstmt.setString(1, roomOrderVO.getMem_id());
			pstmt.setDate(2, roomOrderVO.getCheck_in_date());
			pstmt.setDate(3, roomOrderVO.getCheck_out_date());
			pstmt.setInt(4, roomOrderVO.getStatus());
			pstmt.setString(5, roomOrderVO.getRoom_order_id());
			
			int orderUpdate = pstmt.executeUpdate();
			System.out.println("更新"+ orderUpdate + "筆訂房訂單資料");

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
	public void deleteRoomOrder(String room_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Delete_Stmt);
			
			pstmt.setString(1, room_order_id);
			
			int orderDelete = pstmt.executeUpdate();
			System.out.println("刪除"+ orderDelete + "筆訂房訂單資料");

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
	public RoomOrderVO findByRoomOrderId(String room_order_id) {
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_One_Stmt);
			
			pstmt.setString(1, room_order_id);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
				roomOrderVO.setCheck_in_date(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheck_out_date(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setStatus(rs.getInt("STATUS"));			
			}

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
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> getAllRoomOrder() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_Stmt);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
				roomOrderVO.setCheck_in_date(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheck_out_date(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setStatus(rs.getInt("STATUS"));
				list.add(roomOrderVO);
			}		
		}catch (SQLException se) {
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
		return list;
	}

	@Override
	public Set<RoomOrderDetailVO> getDetailsByRoomOrderId(String room_order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithDetails(RoomOrderVO roomOrderVO, List<RoomOrderDetailVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();		
			//設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		//先新增訂單
			String cols[] = {"ROOM_ORDER_ID"};
			pstmt = con.prepareStatement(Add_Stmt, cols);			
			pstmt.setString(1, roomOrderVO.getMem_id());
			pstmt.setDate(2, roomOrderVO.getCheck_in_date());
			pstmt.setDate(3, roomOrderVO.getCheck_out_date());
			pstmt.setInt(4, roomOrderVO.getStatus());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_room_order_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_room_order_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_room_order_id +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//再同時新增訂單明細
			RoomOrderDetailDAO dao = new RoomOrderDetailDAO();
			System.out.println("list.size()-A="+list.size());
			for (RoomOrderDetailVO aRod : list) {
				aRod.setRoom_order_id(next_room_order_id);
				dao.addRoomOrderAndDetail(aRod, con);
			}

			//設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_room_order_id + "時,共有明細" + list.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

}
