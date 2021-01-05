package com.currentroom.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class CurrentRoomDAO implements CurrentRoomDAO_Interface{
	
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
	
	private static final String Get_Qty_By_Room_Category_Id = "SELECT ROOM_CURRENT_LEFT FROM CURRENT_ROOM WHERE ROOM_CATEGORY_ID = ?";
	

	@Override
	public Integer getCurrentRoomQtyByRoomCategoryId(String room_category_id) {
		Integer current = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Qty_By_Room_Category_Id);
			pstmt.setString(1, room_category_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				current += rs.getInt("ROOM_CURRENT_LEFT");		
			}
			
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
		return current;
	}
}
