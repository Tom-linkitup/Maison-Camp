package com.activityComment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityCommentDAO implements ActivityCommentDAO_interface{
	
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
	
	private static final String INSERT_STMT = "INSERT INTO ACTIVITY_COMMENT (ACT_COMMENT_ID ,ACT_ORDER_ID ,ACT_CATEGORY_ID ,ACT_COMMENT ,CREATE_TIME) VALUES ('AC' || ACTIVITY_COMMENT_ID_seq.NEXTVAL ,?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM ACTIVITY_COMMENT where ACT_COMMENT_ID = ?";
	private static final String UPDATE = "UPDATE ACTIVITY_COMMENT set ACT_ORDER_ID=?, ACT_CATEGORY_ID=?, ACT_COMMENT=?, CREATE_TIME=?  where ACT_COMMENT_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM ACTIVITY_COMMENT where ACT_ORDER_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM ACTIVITY_COMMENT ORDER BY ACT_COMMENT_ID";
	private static final String GETONE_BYCOMMENTID_STMT = "SELECT * FROM ACTIVITY_COMMENT where ACT_COMMENT_ID = ?";
	private static final String GET_BYACTID_STMT = "SELECT * FROM ACTIVITY_COMMENT where ACT_CATEGORY_ID=?";

	

	@Override
	public void insert(ActivityCommentVO activityCommentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityCommentVO.getActOrderId());
			pstmt.setString(2, activityCommentVO.getActCategoryId());
			pstmt.setString(3, activityCommentVO.getActComment());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			pstmt.execute();
			
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
	public void update(ActivityCommentVO activityCommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, activityCommentVO.getActOrderId());
			pstmt.setString(2, activityCommentVO.getActCategoryId());
			pstmt.setString(3, activityCommentVO.getActComment());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(5, activityCommentVO.getActCommentId());
			
			pstmt.execute();
			
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
	public void delete(String activityCommentId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, activityCommentId);
			
			pstmt.execute();
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
	public ActivityCommentVO findActivityCommentId(String activityCommentId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityCommentVO acVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONE_BYCOMMENTID_STMT);
			
			pstmt.setString(1, activityCommentId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				acVO = new ActivityCommentVO();
				acVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				acVO.setActCommentId(rs.getString("ACT_COMMENT_ID"));
				acVO.setActCategoryId(rs.getString("ACT_CATEGORY_ID"));
				acVO.setActComment(rs.getString("ACT_COMMENT"));
				acVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
		
		return acVO;
	}


	@Override
	public ActivityCommentVO getOneByActOrder(String actOrderId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityCommentVO acVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, actOrderId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				acVO = new ActivityCommentVO();
				acVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				acVO.setActCommentId(rs.getString("ACT_COMMENT_ID"));
				acVO.setActCategoryId(rs.getString("ACT_CATEGORY_ID"));
				acVO.setActComment(rs.getString("ACT_COMMENT"));
				acVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
		
		return acVO;
	}

	@Override
	public List<ActivityCommentVO> getAll() {
		
		List<ActivityCommentVO> list = new ArrayList<ActivityCommentVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityCommentVO acVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
		
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				acVO = new ActivityCommentVO();
				acVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				acVO.setActCommentId(rs.getString("ACT_COMMENT_ID"));
				acVO.setActCategoryId(rs.getString("ACT_CATEGORY_ID"));
				acVO.setActComment(rs.getString("ACT_COMMENT"));
				acVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				
				list.add(acVO);
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
			
		return list;
	}
	
	public List<ActivityCommentVO> getByActCategoryId(String actCategoryId){
		
		List<ActivityCommentVO> list = new ArrayList<ActivityCommentVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityCommentVO acVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYACTID_STMT);
			
			pstmt.setString(1, actCategoryId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				acVO = new ActivityCommentVO();
				acVO.setActOrderId(rs.getString("ACT_ORDER_ID"));
				acVO.setActCommentId(rs.getString("ACT_COMMENT_ID"));
				acVO.setActCategoryId(rs.getString("ACT_CATEGORY_ID"));
				acVO.setActComment(rs.getString("ACT_COMMENT"));
				acVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				
				list.add(acVO);
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
			
		return list;
	}
	
	
	
	public static void main(String[] argv) {
		
		ActivityCommentDAO dao = new ActivityCommentDAO();
		
		
		//test insert oK 注意欄位 COMMENT 建成 COMENT
//		
//		ActivityCommentVO acVO1 = new ActivityCommentVO();
//		acVO1.setActId("A10004");
//		acVO1.setActComment("風景豪TTTTTXX漂釀");
//		
//		dao.insert(acVO1);
//		System.out.println("insert OK");
//		
		
		
		//test update OK
//		ActivityCommentVO acVO2 = new ActivityCommentVO();
//		acVO2.setActCommentId("AC10016");
//		acVO2.setActId("A10004");
//		acVO2.setActComment("可惜豬哥多");
//		
//		dao.update(acVO2);
//		System.out.println("update OK");
//		
		
		
		//test delete
//		String s = "AC10003";
//		dao.delete(s);
//		System.out.println("delete OK");
//		
		
		//test findActivityCommentId
		
//		String str = "AC10004";
//		ActivityCommentVO acVO3 = dao.findActivityCommentId(str);
//		System.out.println(acVO3.getActCommentId());
//		System.out.println(acVO3.getActId());
//		System.out.println(acVO3.getActComment());
//		System.out.println(acVO3.getCreateTime());
//		System.out.println("findByPrimaryKey OK");
		
		
		
		//test getALL OK
		
//		List<ActivityCommentVO> ls = null;
//		ls = dao.getAll();
//		for(ActivityCommentVO acVO : ls) {
//			System.out.println(acVO.getActivityCommentId());
//		}
//		System.out.println("getAll OK");
//	}
	
		List<ActivityCommentVO> ls = null;
		ls = dao.getByActCategoryId("ACT_CATEGORY1");
		for(ActivityCommentVO acVO : ls) {
			System.out.println(acVO.getActCommentId());
		}
		
	}

}
