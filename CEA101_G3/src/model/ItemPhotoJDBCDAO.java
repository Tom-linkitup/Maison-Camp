package com.item_photo.model;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPhotoJDBCDAO implements ItemPhotoDAO_interface {

	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ITEM_PHOTO (item_photo_id,item_id,content) VALUES ('IPH' || ITEM_PHOTO_SEQ.NEXTVAL, ?, ?)";
	private static final String UPDATE = "UPDATE ITEM_PHOTO set item_id=?, content=? where item_photo_id = ?";
	private static final String DELETE = "DELETE FROM ITEM_PHOTO where item_photo_id = ?";
	private static final String GET_ONE_STMT = "SELECT * from ITEM_PHOTO where item_photo_id = ? order by item_photo_id";
	private static final String GET_ALL_STMT = "SELECT * FROM ITEM_PHOTO where item_id = ? order by item_photo_id";

	public void insert(ItemPhotoVO ItemPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ItemPhotoVO.getItemId());
			pstmt.setBytes(2, ItemPhotoVO.getContent());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update(ItemPhotoVO ItemPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ItemPhotoVO.getItemId());
			pstmt.setBytes(2, ItemPhotoVO.getContent());
			pstmt.setString(3, ItemPhotoVO.getItemPhotoId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void delete(String itemPhotoId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, itemPhotoId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public ItemPhotoVO findByPrimaryKey(String itemPhotoId) {

		ItemPhotoVO ItemPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itemPhotoId);

			rs = pstmt.executeQuery();
			



			while (rs.next()) {
				
				ItemPhotoVO = new ItemPhotoVO();
				ItemPhotoVO.setItemPhotoId(rs.getString("item_photo_id"));
				ItemPhotoVO.setItemId(rs.getString("item_id"));
				ItemPhotoVO.setContent(rs.getBytes("content"));
			}
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return ItemPhotoVO;
	}

	public List<ItemPhotoVO> getAll(String item_id) {
		List<ItemPhotoVO> list = new ArrayList<ItemPhotoVO>();
		ItemPhotoVO ItemPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, item_id);
			rs = pstmt.executeQuery();
			
			
			
			while (rs.next()) {
				
				ItemPhotoVO = new ItemPhotoVO();
				ItemPhotoVO.setItemPhotoId(rs.getString("item_photo_id"));
				ItemPhotoVO.setItemId(rs.getString("item_id"));
				ItemPhotoVO.setContent(rs.getBytes("content"));
				list.add(ItemPhotoVO); // Store the row in the list
			}
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) {
		FileInputStream fis;
		byte[] buffer = null ;
		try {
			fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return buffer;
		}
	}
	
	public static void main(String[] args) throws IOException {

		ItemPhotoJDBCDAO dao = new ItemPhotoJDBCDAO();

//		// 新增
		ItemPhotoVO ItemPhotoVO1 = new ItemPhotoVO();

//		byte[] pic = getPictureByteArray("WebContent/item/images/1.jpg");
//		ItemPhotoVO1.setItemId("I10021");
//		ItemPhotoVO1.setContent(pic);
//		dao.insert(ItemPhotoVO1);

//		// 修改
//		ItemPhotoVO ItemPhotoVO2 = new ItemPhotoVO();
//		ItemPhotoVO2.setItemPhotoId("IPH1");
//		ItemPhotoVO2.setItemId("I10023");
//		ItemPhotoVO2.setContent(getPictureByteArray("WebContent/item/images/2.jpg"));
//		dao.update(ItemPhotoVO2);

//		// 刪除
//		dao.delete("IPH10002");	

//		// 查詢
//		ItemPhotoVO ItemPhotoVO3 = dao.findByPrimaryKey("IPH1");
//		System.out.print(ItemPhotoVO3.getItemPhotoId() + ",");
//		System.out.print(ItemPhotoVO3.getItemId() + ",");
//		System.out.print(ItemPhotoVO3.getContent() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<ItemPhotoVO> list = dao.getAll("I10001");
//		for (ItemPhotoVO aItemPhoto : list) {
//			System.out.print(aItemPhoto.getItemPhotoId() + ",");
//			System.out.print(aItemPhoto.getItemId() + ",");
//			System.out.print(aItemPhoto.getContent() + ",");
//			System.out.println();
//		}
		
//		List<ItemPhotoVO> itemPhotoList = dao.getAll("I10001");
//		for(int i = 1; i <= itemPhotoList.size(); i++) {
//			System.out.print(itemPhotoList.get(i-1).getItemPhotoId() + ",");
//			System.out.print(itemPhotoList.get(i-1).getItemId() + ",");
//			readAllPicture(itemPhotoList.get(i-1).getContent(), i);
//		}
		

	}
//	public static void readAllPicture(byte[] bytes, int i) throws IOException {
//		FileOutputStream fos = new FileOutputStream("C:\\CEA101_WebApp\\eclipse_WTP_WorkSpace1\\CEA101G3\\WebContent\\images/con" + i + ".jpg");
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
//		PrintStream ps = new PrintStream(bos);
//		ps.write(bytes);
//		ps.flush();
//		ps.close();
//		bos.close();
//		fos.close();
//	}

}
