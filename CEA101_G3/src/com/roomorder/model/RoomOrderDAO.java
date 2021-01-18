package com.roomorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.roomorderdetail.model.RoomOrderDetailDAO;
import com.roomorderdetail.model.RoomOrderDetailVO;
import com.roomrsv.model.RoomRsvDAO;

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
	private static final String Get_Rods_ByRoid = "SELECT ROOM_ORDER_ID, ROOM_CATEGORY_ID, ROOM_PROMOTION_ID, QUANTITY, ROOM_ORDER_PRICE, ORDER_TIME, NOTE FROM ROOM_ORDER_DETAIL WHERE ROOM_ORDER_ID=? ORDER BY ROOM_ORDER_ID";
	private static final String Get_One_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS FROM ROOM_ORDER WHERE ROOM_ORDER_ID=?";
	private static final String Get_One_By_Mem_Id = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS FROM ROOM_ORDER WHERE MEM_ID = ?";
	private static final String Get_All_Stmt = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS FROM ROOM_ORDER ORDER BY ROOM_ORDER_ID";
	private static final String Cancel_Order_Stmt = "UPDATE ROOM_ORDER SET STATUS=? WHERE ROOM_ORDER_ID=?";
	private static final String Get_Check_In_Order = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS FROM ROOM_ORDER WHERE CHECK_IN_DATE = TRUNC(CURRENT_DATE) ORDER BY ROOM_ORDER_ID";
	private static final String Get_Check_Out_Order = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS, CURRENT_ROOM_ID FROM ROOM_ORDER WHERE CHECK_OUT_DATE = TRUNC(CURRENT_DATE) ORDER BY ROOM_ORDER_ID";
	private static final String Get_Order_By_Status = "SELECT ROOM_ORDER_ID, MEM_ID, CHECK_IN_DATE, CHECK_OUT_DATE, STATUS, CURRENT_ROOM_ID FROM ROOM_ORDER WHERE STATUS=? ORDER BY ROOM_ORDER_ID";
	private static final String Update_Order_Status = "UPDATE ROOM_ORDER SET STATUS=?, CURRENT_ROOM_ID=? WHERE ROOM_ORDER_ID=?";
	
	private static final String GETALLINROOM = "select room_order_id ,mem_id,status from room_order where status=2 ";
	
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
		Set<RoomOrderDetailVO> set = new HashSet<RoomOrderDetailVO>();
		RoomOrderDetailVO rodVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Rods_ByRoid);
			pstmt.setString(1, room_order_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				rodVO = new RoomOrderDetailVO();
				rodVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				rodVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				rodVO.setRoom_promotion_id(rs.getString("ROOM_PROMOTION_ID"));
				rodVO.setQuantity(rs.getInt("QUANTITY"));
				rodVO.setRoom_order_price(rs.getInt("ROOM_ORDER_PRICE"));
				rodVO.setOrder_time(rs.getDate("ORDER_TIME"));
				rodVO.setNote(rs.getString("NOTE"));
				set.add(rodVO); // Store the row in the vector
			}
				
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	@Override
	public void insertWithDetails(RoomOrderVO roomOrderVO, List<RoomOrderDetailVO> list, JSONObject orderItem) {
		
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
			
			//新增預定表
			RoomRsvDAO rsvdao = new RoomRsvDAO();
			rsvdao.update(orderItem, con);

			//設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_room_order_id + "時,共有明細" + list.size() + "筆同時被新增");
			System.out.println("預定表更新成功");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
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

	@Override
	public List<RoomOrderVO> findByMemId(String mem_id) {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_One_By_Mem_Id);
			
			pstmt.setString(1, mem_id);
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
		return list;
	}

	@Override
	public void updateWithRsv(Integer status, String room_order_id, JSONObject orderItem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();		
			//設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		//先更新訂單狀態		
			pstmt = con.prepareStatement(Cancel_Order_Stmt);			
			pstmt.setInt(1, status);
			pstmt.setString(2, room_order_id);
			pstmt.executeUpdate();
			
			//再同時更新預定表
			RoomRsvDAO rsvdao = new RoomRsvDAO();
			rsvdao.updateWithOrder(orderItem, con);

			//設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("取消訂單成功");
			System.out.println("取消訂單，預定表更新成功");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
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

	@Override
	public List<RoomOrderVO> getAllBeforeToday() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Check_In_Order);
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
		return list;
	}

	@Override
	public List<RoomOrderVO> getAllDateOut() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Check_Out_Order);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
				roomOrderVO.setCheck_in_date(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheck_out_date(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setStatus(rs.getInt("STATUS"));	
				roomOrderVO.setCurrent_room_id(rs.getString("CURRENT_ROOM_ID"));
				list.add(roomOrderVO);
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
		return list;
	}

	@Override
	public List<RoomOrderVO> getAllByOrderStatus(Integer status) {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Order_By_Status);
			
			pstmt.setInt(1, status);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
				roomOrderVO.setCheck_in_date(rs.getDate("CHECK_IN_DATE"));
				roomOrderVO.setCheck_out_date(rs.getDate("CHECK_OUT_DATE"));
				roomOrderVO.setStatus(rs.getInt("STATUS"));
				roomOrderVO.setCurrent_room_id(rs.getString("CURRENT_ROOM_ID"));
				list.add(roomOrderVO);
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
		return list;
	}

	@Override
	public void updateOrderStatus(Integer status, String current_room_id, String room_order_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Update_Order_Status);
			pstmt.setInt(1, status);
			pstmt.setString(2, current_room_id);
			pstmt.setString(3, room_order_id);
			
			int orderUpdate = pstmt.executeUpdate();
			System.out.println("更新"+ orderUpdate + "筆訂房訂單狀態");

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
	public List<RoomOrderVO> getAllInRoom() {
		List<RoomOrderVO> list = new ArrayList<>();
		RoomOrderVO roomOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLINROOM);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getString("ROOM_ORDER_ID"));
				roomOrderVO.setMem_id(rs.getString("MEM_ID"));
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

}
