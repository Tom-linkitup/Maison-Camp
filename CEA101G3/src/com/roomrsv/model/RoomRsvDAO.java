package com.roomrsv.model;

import java.util.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.json.*;

import com.currentroom.model.CurrentRoomService;
import com.roomtype.model.*;

public class RoomRsvDAO implements RoomRsvDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String Insert_Rsv_Date = "INSERT INTO ROOM_RESERVATION (RSV_DATE, ROOM_CATEGORY_ID, ROOM_LEFT) VALUES (?, ?, ?)";
	private static final String Update_Rsv = "UPDATE ROOM_RESERVATION SET ROOM_LEFT = ? WHERE RSV_DATE = ? AND ROOM_CATEGORY_ID = ?";
	private static final String Delete_Rsv = "DELETE FROM ROOM_RESERVATION WHERE RSV_DATE = ?";
	private static final String Get_One_By_Date_And_Room_category_id = "SELECT * FROM ROOM_RESERVATION WHERE RSV_DATE = ? AND ROOM_CATEGORY_ID = ?";
	private static final String Get_One_Day_By_Date = "SELECT * FROM ROOM_RESERVATION WHERE RSV_DATE = ?";
	private static final String Get_All = "SELECT * FROM ROOM_RESERVATION ORDER BY RSV_DATE";
	private static final String Get_All_By_Room_Category_id = "SELECT * FROM ROOM_RESERVATION WHERE ROOM_CATEGORY_ID = ? ORDER BY RSV_DATE";
	
	@Override
	public void insert(LocalDate rsv_date, String room_category_id, Connection con) {

		CurrentRoomService curRoomSvc = new CurrentRoomService();
		PreparedStatement pstmt = null;

		try {
				pstmt = con.prepareStatement(Insert_Rsv_Date);
				pstmt.setDate(1, java.sql.Date.valueOf(rsv_date));
				pstmt.setString(2, room_category_id);
				pstmt.setInt(3, curRoomSvc.getCurRoomQtyByRT(room_category_id));
				pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback(); //新增預定表有問題時交易撤回
				} catch (SQLException re){
					throw new RuntimeException("rollback發生錯誤:" + re.getMessage());
				}
			}
			e.printStackTrace();
			throw new RuntimeException("A database error occured:" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(JSONObject orderItem, Connection con) {
		PreparedStatement pstmt = null;
		RoomRsvVO rsvVO = null;
		try {
			Integer stay = orderItem.getInt("stay");
			LocalDate date = LocalDate.parse(orderItem.getString("startDate"));
			String room_category_id = orderItem.getString("room_category_id");
			Integer quantity = orderItem.getInt("quantity");
			pstmt = con.prepareStatement(Update_Rsv);
			for(int i = 0; i < stay; i++) { //訂幾天的房，更新幾天預定表資料
				if(i == 0) {
					date = date.plusDays(i);
					rsvVO = getOneByDateNRmType(date, room_category_id, con);
					if(rsvVO == null) {
						insert(date, room_category_id, con); //如果該日期還沒建立預定表
						rsvVO = getOneByDateNRmType(date, room_category_id, con);
					}				
				}else {
					date = date.plusDays(1L);
					rsvVO = getOneByDateNRmType(date, room_category_id, con);
					if(rsvVO == null) {
						insert(date, room_category_id, con); //如果該日期還沒建立預定表
						rsvVO = getOneByDateNRmType(date, room_category_id, con);
					}
				}
				
				Integer room_left = rsvVO.getRoom_left() - quantity; //預定的日期房間數都要 - 訂購量
				pstmt.setInt(1, room_left);
				pstmt.setDate(2, java.sql.Date.valueOf(date));
				pstmt.setString(3, room_category_id);
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback(); //新增預定表有問題時交易撤回
				} catch (SQLException re){
					throw new RuntimeException("rollback發生錯誤:" + re.getMessage());
				}
			}
			e.printStackTrace();
			throw new RuntimeException("A database error occured:" + e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(LocalDate rsv_date) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Delete_Rsv);
			pstmt.setObject(1, rsv_date);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public RoomRsvVO getOneByDateNRmType(LocalDate rsv_date, String room_category_id, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomRsvVO rsvVO = null;
		try {
			pstmt = con.prepareStatement(Get_One_By_Date_And_Room_category_id);
			pstmt.setDate(1, java.sql.Date.valueOf(rsv_date));
			pstmt.setString(2, room_category_id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				rsvVO = new RoomRsvVO();
				rsvVO.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				rsvVO.setRoom_left(rs.getInt("ROOM_LEFT"));
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rsvVO;
	}
	
	@Override
	public List<RoomRsvVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomRsvVO> roomRsv = new ArrayList<>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomRsvVO rsvVO = new RoomRsvVO();
				rsvVO.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				rsvVO.setRoom_left(rs.getInt("ROOM_LEFT"));
				roomRsv.add(rsvVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomRsv;
	}

	@Override
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsv_date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomRsvVO> roomRsv = new ArrayList<>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_One_Day_By_Date);
			pstmt.setDate(1, java.sql.Date.valueOf(rsv_date));
			rs = pstmt.executeQuery();

			while(rs.next()) {
				RoomRsvVO rsvVO = new RoomRsvVO();
				rsvVO = new RoomRsvVO();
				rsvVO.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				rsvVO.setRoom_left(rs.getInt("ROOM_LEFT"));
				roomRsv.add(rsvVO);
			}
			
			if (roomRsv.size() == 0) {
				RoomTypeService roomTypeSvc = new RoomTypeService();
				List<RoomTypeVO> roomTypeVOList = roomTypeSvc.getAllRT();
				for (RoomTypeVO roomTypeVO : roomTypeVOList) {
					RoomRsvVO rsvVO = new RoomRsvVO();
					rsvVO.setRsv_date(rsv_date);
					rsvVO.setRoom_category_id(roomTypeVO.getRoom_category_id());
					rsvVO.setRoom_left(roomTypeVO.getRoom_guest());
					roomRsv.add(rsvVO);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomRsv;
	}

	@Override
	public List<RoomRsvVO> getAllByRoomCategoryId(String room_category_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomRsvVO> roomRsv = new ArrayList<>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_All_By_Room_Category_id);
			pstmt.setString(1, room_category_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomRsvVO rsvVO = new RoomRsvVO();
				rsvVO.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvVO.setRoom_category_id(rs.getString("ROOM_CATEGORY_ID"));
				rsvVO.setRoom_left(rs.getInt("ROOM_LEFT"));
				roomRsv.add(rsvVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomRsv;
	}
	
	public Integer roomCheck(LocalDate rsv_date, Integer stay, String room_category_id) {
		Connection con = null;
		Integer room_left = null;
		
		try {
			con = ds.getConnection();
			RoomTypeService roomTypeSvc = new RoomTypeService();
			CurrentRoomService curRoomSvc = new CurrentRoomService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRT(room_category_id);
			room_left = curRoomSvc.getCurRoomQtyByRT(roomTypeVO.getRoom_category_id());
			for (int i = 0; i < stay; i++) {
				RoomRsvVO rsvVO = getOneByDateNRmType(rsv_date.plusDays(i), room_category_id, con);
				if (rsvVO == null) {
					continue;
				} else if (rsvVO.getRoom_left() == 0){
					room_left = 0;
					break;
				} else {
					room_left = Math.min(rsvVO.getRoom_left(), room_left); 
				}			
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return room_left;
	}

	@Override
	public void updateWithOrder(JSONObject orderItem, Connection con) {
		PreparedStatement pstmt = null;
		RoomRsvVO rsvVO = null;
		try {
			Integer stay = orderItem.getInt("stay");
			LocalDate date = LocalDate.parse(orderItem.getString("startDate"));
			String room_category_id = orderItem.getString("room_category_id");
			Integer quantity = orderItem.getInt("quantity");
			pstmt = con.prepareStatement(Update_Rsv);
			for(int i = 0; i < stay; i++) { //取消幾天的房，更新幾天預定表資料
				if(i == 0) {
					date = date.plusDays(i);
					rsvVO = getOneByDateNRmType(date, room_category_id, con);				
				}else {
					date = date.plusDays(1L);
					rsvVO = getOneByDateNRmType(date, room_category_id, con);
				}
				
				Integer room_left = rsvVO.getRoom_left() + quantity; //取消的房間數量加回去預定表裡
				pstmt.setInt(1, room_left);
				pstmt.setDate(2, java.sql.Date.valueOf(date));
				pstmt.setString(3, room_category_id);
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback(); //更新預定表有問題時交易撤回
				} catch (SQLException re){
					throw new RuntimeException("rollback發生錯誤:" + re.getMessage());
				}
			}
			e.printStackTrace();
			throw new RuntimeException("A database error occured:" + e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}
}
