package com.activityOrder.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityOrderJDBCDAO implements ActivityOrderDAO_interfact {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_ORDER (ACT_ORDER_ID, ACT_ID,MEM_ID,NOTE, PEOPLE, ACT_PRICE, PAYMENT, CREATE_TIME, STATUS)"
			+ " VALUES ('AD' || ACT_ORDER_ID_seq.NEXTVAL, ?, ?, ?, ?, ?, ? ,? ,?)";
	private static final String DELETE = "DELETE FROM ACTIVITY_ORDER where ACT_ORDER_ID = ?";
	private static final String UPDATE = "UPDATE ACTIVITY_ORDER set ACT_ORDER_ID=?, ACT_ID=?, MEM_ID=?,NOTE=?, PEOPLE=?, ACT_PRICE=?, PAYMENT=?, CREATE_TIME=?, STATUS=?  where ACT_ORDER_ID = ?";
	private static final String GET_ONE_STMT = "SELECT ACT_ORDER_ID, ACT_ID, MEM_ID, NOTE, PEOPLE, ACT_PRICE, PAYMENT, CREATE_TIME, STATUS FROM ACTIVITY_ORDER where ACT_ORDER_ID = ?";
	private static final String GET_ALL_STMT = "SELECT ACT_ORDER_ID, ACT_ID, MEM_ID, NOTE, PEOPLE, ACT_PRICE, PAYMENT, CREATE_TIME, STATUS FROM ACTIVITY_ORDER ORDER BY ACT_ORDER_ID";

	private static final String GET_BYACTID_STMT = "SELECT ACT_ORDER_ID, ACT_ID, MEM_ID, NOTE, PEOPLE, ACT_PRICE, PAYMENT, CREATE_TIME, STATUS FROM ACTIVITY_ORDER where ACT_ID = ?";
	
	@Override
	public void insert(ActivityOrderVO activityOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, activityOrderVO.getActId());
			pstmt.setString(2, activityOrderVO.getMemId());
			pstmt.setString(3, activityOrderVO.getNote());
			pstmt.setInt(4, activityOrderVO.getPeople());
			pstmt.setInt(5, activityOrderVO.getActPrice());
			pstmt.setString(6, activityOrderVO.getPayment());
			pstmt.setDate(7, activityOrderVO.getCreateTime());
			pstmt.setInt(8, activityOrderVO.getStatus());

			pstmt.execute();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(ActivityOrderVO activityOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, activityOrderVO.getActOrderId());
			pstmt.setString(2, activityOrderVO.getActId());
			pstmt.setString(3, activityOrderVO.getMemId());
			pstmt.setString(4, activityOrderVO.getNote());
			pstmt.setInt(5, activityOrderVO.getPeople());
			pstmt.setInt(6, activityOrderVO.getActPrice());
			pstmt.setString(7, activityOrderVO.getPayment());
			pstmt.setDate(8, activityOrderVO.getCreateTime());
			pstmt.setInt(9, activityOrderVO.getStatus());
			pstmt.setString(10, activityOrderVO.getActOrderId());

			pstmt.executeUpdate();

			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String actOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, actOrderId);
			pstmt.execute();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ActivityOrderVO findByActOrderId(String actOrderId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityOrderVO aoVO = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, actOrderId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				aoVO = new ActivityOrderVO();
				
				aoVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				aoVO.setActId(rs.getString("ACT_ID"));
				aoVO.setMemId(rs.getString("MEM_ID"));
				aoVO.setNote(rs.getString("NOTE"));
				aoVO.setPeople(rs.getInt("PEOPLE"));
				aoVO.setActPrice(rs.getInt("ACT_PRICE"));
				aoVO.setPayment(rs.getString("PAYMENT"));
				aoVO.setCreateTime(rs.getDate("CREATE_TIME"));
				aoVO.setStatus(rs.getInt("STATUS"));
			}
			
			
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally{
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
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
		
		
		
		return aoVO;
	}

	@Override
	public List<ActivityOrderVO> getAll() {
		
		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityOrderVO aoVO = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				aoVO = new ActivityOrderVO();
				
				aoVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				aoVO.setActId(rs.getString("ACT_ID"));
				aoVO.setMemId(rs.getString("MEM_ID"));
				aoVO.setNote(rs.getString("NOTE"));
				aoVO.setPeople(rs.getInt("PEOPLE"));
				aoVO.setActPrice(rs.getInt("ACT_PRICE"));
				aoVO.setPayment(rs.getString("PAYMENT"));
				aoVO.setCreateTime(rs.getDate("CREATE_TIME"));
				aoVO.setStatus(rs.getInt("STATUS"));
				
				list.add(aoVO);
			}
			
			
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally{
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
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
	public List<ActivityOrderVO> findByActId(String actId) {
		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityOrderVO aoVO = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYACTID_STMT);
			
			pstmt.setString(1, actId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				aoVO = new ActivityOrderVO();
				
				aoVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				aoVO.setActId(rs.getString("ACT_ID"));
				aoVO.setMemId(rs.getString("MEM_ID"));
				aoVO.setNote(rs.getString("NOTE"));
				aoVO.setPeople(rs.getInt("PEOPLE"));
				aoVO.setActPrice(rs.getInt("ACT_PRICE"));
				aoVO.setPayment(rs.getString("PAYMENT"));
				aoVO.setCreateTime(rs.getDate("CREATE_TIME"));
				aoVO.setStatus(rs.getInt("STATUS"));
				
				list.add(aoVO);
			}
			
			
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally{
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
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

	public static void main(String[] argv) {

		ActivityOrderJDBCDAO dao = new ActivityOrderJDBCDAO();

//		test insert

//		ActivityOrderVO aoVO1 = new ActivityOrderVO();
//		aoVO1.setActId("A10004");
//		aoVO1.setMemId("M10002");
//		aoVO1.setNote("朝爽得尖刀一百塊");
//		aoVO1.setPeople(10);
//		aoVO1.setActPrice(81000);
//		aoVO1.setPayment("用身體可以嗎");
//		aoVO1.setCreateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//		aoVO1.setStatus(0);
//
//		dao.insert(aoVO1);
//		System.out.println("INSERT OK");

//		test update
		
//		ActivityOrderVO aoVO2 = new ActivityOrderVO();
//		aoVO2.setActId("A10007");
//		aoVO2.setMemId("M10002");
//		aoVO2.setNote("朝爽DER");
//		aoVO2.setPeople(10);
//		aoVO2.setActPrice(81000);
//		aoVO2.setPayment("用身體付款");
//		aoVO2.setCreateTime(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//		aoVO2.setStatus(0);
//		aoVO2.setActOrderId("AD10012");
//		
//		dao.update(aoVO2);
//		System.out.println("UPDATE OK");
//		
//		test delete
		
//		dao.delete("AD10");
//		System.out.println("DELETE OK");
		
		
//		test getONE
		
//		ActivityOrderVO aoVO3 = new ActivityOrderVO();
//		String str = "AD9";
//		aoVO3 = dao.findByPrimaryKey(str);
//		System.out.println(aoVO3.getACT_ORDER_ID());
//		System.out.println(aoVO3.getACT_ID());
//		System.out.println(aoVO3.getMEM_ID());
//		System.out.println(aoVO3.getNOTE());
//		System.out.println(aoVO3.getPEOPLE());
//		System.out.println(aoVO3.getACT_PRICE());
//		System.out.println(aoVO3.getPAYMENT());
//		System.out.println(aoVO3.getCREATE_TIME());
//		System.out.println(aoVO3.getSTATUS());
//		System.out.println("OK");
		
		
//		test getALL
//		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
//		list = dao.getAll();
//		
//		for(ActivityOrderVO aoVO : list) {
//			System.out.println(aoVO.getActOrderId());
//			System.out.println(aoVO.getActId());
//			System.out.println(aoVO.getMemId());
//			System.out.println(aoVO.getNote());
//			System.out.println(aoVO.getPeople());
//			System.out.println(aoVO.getActPrice());
//			System.out.println(aoVO.getPayment());
//			System.out.println(aoVO.getCreateTime());
//			System.out.println(aoVO.getStatus());
//			System.out.println("OK");
//		}
//		System.out.println("test clear");
		
		
		// test OK
//		List<ActivityOrderVO> list = new ArrayList<ActivityOrderVO>();
//		list = dao.findByActId("A10001");
//		
//		for(ActivityOrderVO aoVO : list) {
//			System.out.println(aoVO.getActOrderId());
//			System.out.println(aoVO.getActId());
//			System.out.println(aoVO.getMemId());
//			System.out.println(aoVO.getNote());
//			System.out.println(aoVO.getPeople());
//			System.out.println(aoVO.getActPrice());
//			System.out.println(aoVO.getPayment());
//			System.out.println(aoVO.getCreateTime());
//			System.out.println(aoVO.getStatus());
//			System.out.println("OK");
//		}
//		System.out.println("test clear");
//		
		
		
	}



}
