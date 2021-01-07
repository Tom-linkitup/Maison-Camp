package com.actPhoto.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActPhotoJDBCDAO implements ActPhotoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ACT_PHOTO (ACT_PHOTO_ID,ACT_ID,CONTENT) VALUES ('APH' || ACT_PHOTO_ID_seq.NEXTVAL, ?, ?)";
	private static final String DELETE = "DELETE FROM ACT_PHOTO where ACT_PHOTO_ID = ?";
	private static final String UPDATE = "UPDATE ACT_PHOTO set ACT_PHOTO_ID=?, ACT_ID=?, CONTENT=?  where ACT_PHOTO_ID = ?";
	private static final String GET_ONE_STMT = "SELECT ACT_PHOTO_ID,ACT_ID,CONTENT FROM ACT_PHOTO where ACT_PHOTO_ID = ?";
	private static final String GET_ALL_STMT = "SELECT ACT_PHOTO_ID,ACT_ID,CONTENT FROM ACT_PHOTO ORDER BY ACT_PHOTO_ID";
	
	private static final String GET_BYACTID_STMT = "SELECT ACT_PHOTO_ID,ACT_ID,CONTENT FROM ACT_PHOTO where ACT_ID = ?";
	
	private static final String GET_CONTENT = "SELECT CONTENT FROM ACT_PHOTO where ACT_PHOTO_ID = ?";
	
	public void insert(ActPhotoVO actPhotoVO) {
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, actPhotoVO.getActId());
			pstmt.setBytes(2, actPhotoVO.getContent());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update(ActPhotoVO actPhotoVO) {
		

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, actPhotoVO.getActPhotoId());
			pstmt.setString(2, actPhotoVO.getActId());
			pstmt.setBytes(3, actPhotoVO.getContent());
			pstmt.setString(4, actPhotoVO.getActPhotoId());
			pstmt.execute();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void deleteByActPhotoId(String actPhotoId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, actPhotoId);
			pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public ActPhotoVO findByActPhotoId(String actPhotoId) {
		
		ActPhotoVO actPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, actPhotoId);
			

		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actPhotoVO = new ActPhotoVO();
				
				actPhotoVO.setActPhotoId(rs.getString("ACT_PHOTO_ID"));
				actPhotoVO.setActId(rs.getString("ACT_ID"));	
				actPhotoVO.setContent(rs.getBytes("CONTENT"));
				
			}
			

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			return actPhotoVO;
	}
	
	
	@Override
	public List<ActPhotoVO> getAll() {
		
		List<ActPhotoVO> list = new ArrayList<ActPhotoVO>();
		ActPhotoVO actPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actPhotoVO = new ActPhotoVO();
				
				actPhotoVO.setActPhotoId(rs.getString("ACT_PHOTO_ID"));
				actPhotoVO.setActId(rs.getString("ACT_ID"));	
				actPhotoVO.setContent(rs.getBytes("CONTENT"));
				
				list.add(actPhotoVO);
			}
			
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
		public List<ActPhotoVO> getByActId(String ACT_ID) {
		
		List<ActPhotoVO> list = new ArrayList<ActPhotoVO>();
		ActPhotoVO actPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(GET_BYACTID_STMT);
			
			pstmt.setString(1, ACT_ID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actPhotoVO = new ActPhotoVO();
				
				actPhotoVO.setActPhotoId(rs.getString("ACT_PHOTO_ID"));
				actPhotoVO.setActId(rs.getString("ACT_ID"));	
				actPhotoVO.setContent(rs.getBytes("CONTENT"));
				
				list.add(actPhotoVO);
			}
			
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	public byte[] getContent(String actPhotoId){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] content = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(GET_CONTENT);
			
			pstmt.setString(1, actPhotoId);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				content = rs.getBytes("CONTENT");
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return content;
	}
	
	
	
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		
		// test insert  OK
		
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("please type picture's file path");
//		String photoPath = sc.next();
//		
//		FileInputStream fis = new FileInputStream(photoPath);
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		
//		byte[]photo = null;
//		
//		try {
//			photo= new byte[bis.available()];
//			System.out.println(bis.available());
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			bis.read(photo);
//			
//			bis.close();
//			fis.close();
//			
//			System.out.println(photo.length);
//		}catch(IOException ie) {
//			ie.printStackTrace();
//		}
//		
//		ActPhotoJDBCDAO dao = new ActPhotoJDBCDAO();
//
//		ActPhotoVO actPhotoVO = new ActPhotoVO();
//		
//		actPhotoVO.setACT_ID("A10001");
//		actPhotoVO.setCONTENT(photo);
//		dao.insert(actPhotoVO);
//
//		System.out.println("OK");
		
		
		
		
		//test update  it work OK
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("please type picture's file path");
//		String photoPath = sc.next();
//		
//		FileInputStream fis = new FileInputStream(photoPath);
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		
//		byte[]photo = null;
//		
//		try {
//			photo= new byte[bis.available()];
//			System.out.println(bis.available());
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			bis.read(photo);
//			
//			bis.close();
//			fis.close();
//			
//			System.out.println(photo.length);
//		}catch(IOException ie) {
//			ie.printStackTrace();
//		}
//		
//		ActPhotoDAO dao = new ActPhotoDAO();
//
//		ActPhotoVO actPhotoVO = new ActPhotoVO();
//		actPhotoVO.setACT_PHOTO_ID("APH11");
//		actPhotoVO.setACT_ID("A10005");
//		actPhotoVO.setCONTENT(photo);
//
//		dao.update(actPhotoVO);
//
//		System.out.println("OK");
		
		
		
		
		//test delete   it work OK
		
//		ActPhotoJDBCDAO dao = new ActPhotoJDBCDAO();
//		
//		dao.deleteByACT_PHOTO_ID("APH11");
//		
//		System.out.println("OK");
		
		
		
		
		
		//test  findByACT_PHOTO_ID  OK
		
//		ActPhotoJDBCDAO dao = new ActPhotoJDBCDAO();
//		
//		ActPhotoVO actPhotoVO = dao.findByACT_PHOTO_ID("APH20");
//		System.out.println(actPhotoVO.getACT_PHOTO_ID());
//		System.out.println(actPhotoVO.getACT_ID());
//		System.out.println(actPhotoVO.getCONTENT().length);
		
		
		
		//test GETALL  OK
		
//		ActPhotoJDBCDAO actPhotoDAO = new ActPhotoJDBCDAO();
//		List<ActPhotoVO> list = actPhotoDAO.getAll();
//		
//		for(ActPhotoVO APVO : list) {
//			System.out.println(APVO.getACT_PHOTO_ID());
//		}
		
		
		//test GETBYACTID
//		ActPhotoJDBCDAO actPhotoDAO = new ActPhotoJDBCDAO();
//		List<ActPhotoVO> list = actPhotoDAO.getByActId("A10001");
//		for(ActPhotoVO APVO : list) {
//			System.out.println(APVO.getACT_PHOTO_ID());
//		}
//		
		
	}




	
	

	
	
		
	
	

}
