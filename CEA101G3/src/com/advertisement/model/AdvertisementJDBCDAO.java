package com.advertisement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdvertisementJDBCDAO implements AdvertisementDAO_Interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G3";
	String passwd = "123456";
	
	public static final String Add_Stmt = "INSERT INTO ADVERTISEMENT (ADV_ID, EMP_ID, ADV_START_DATE, ADV_CLOSE_DATE, ADV_CONTENT) VALUES ('ADV' || adv_id_seq.NEXTVAL,?,?,?,?)";

	@Override
	public void addAdvertisement(AdvertisementVO advertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Add_Stmt);
			
			pstmt.setString(1, advertisementVO.getEmp_id());
			pstmt.setDate(2, advertisementVO.getAdv_start_date());
			pstmt.setDate(3, advertisementVO.getAdv_close_date());
			pstmt.setString(4, advertisementVO.getAdv_content());
			
			int advertisementAdd = pstmt.executeUpdate();
			System.out.println("新增"+ advertisementAdd + "筆廣告");
			
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
	public void updateAdvertisement(AdvertisementVO advertisementVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdvertisement(String adv_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdvertisementVO findByAdvertisementId(String adv_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdvertisementVO> getAllAdvertisement() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		AdvertisementJDBCDAO dao = new AdvertisementJDBCDAO();
		
		AdvertisementVO ad1 = new AdvertisementVO();
		ad1.setEmp_id("E10004");
		ad1.setAdv_start_date(java.sql.Date.valueOf("2020-10-20"));
		ad1.setAdv_close_date(java.sql.Date.valueOf("2020-11-20"));
		ad1.setAdv_content("陽光空氣花happen，就讓情節充滿感動！");
		dao.addAdvertisement(ad1);
		
	}

}
