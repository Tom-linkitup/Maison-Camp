package com.roomrsv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.currentroom.model.CurrentRoomService;
import com.member.model.MemberVO;
import com.roomrsv.model.*;
import com.roomtype.model.RoomTypeService;
import com.roomtype.model.RoomTypeVO;

public class RoomRsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private TimerTask rsvCleaner;
//	private Timer schedule;  
    public RoomRsvServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException { //移除過期的預定表
//		rsvCleaner = new TimerTask() {
//    		@Override
//			public void run() {
//				System.out.println(count++);
//			}
//		};
//		
//		schedule = new Timer();
//		LocalDate now = Calendar.getInstance().getTime();
//		now.set(year, month, date);
//		schedule.scheduleAtFixedRate(rsvCleaner, now, 24*60*60*1000);
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String action = req.getParameter("action").trim();

//		if ("insert_n_update_rsv".equals(action)) {  //如果房客預定房型的日期沒有資料，則進入此判斷式
//			try {
//				String rsv_str = req.getParameter("rsv_date").trim();
//				LocalDate rsv_date = LocalDate.parse(rsv_str);
//				RoomRsvService rsvSvc = new RoomRsvService();
//				rsvSvc.insertRsvDate(rsv_date); //新增該天的客房預訂表
//				
//				String rm_type = req.getParameter("rm_type"); //取得該房客訂購的房型
//				RoomRsvVO rsvvo = rsvSvc.getOneByDateNRmType(rsv_date, rm_type); //取得該天的訂房剩餘資訊
//				Integer bk_qty = Integer.valueOf(req.getParameter("bk_qty")); //取得房客訂購房型的數量
//				Integer rmLeft = rsvvo.getRoom_left() - bk_qty; //剩餘數量 - 訂購數量
//				rsvSvc.updateRmLeft(rsv_date, rm_type, rmLeft); //更新該天的房型數量
//				return;
//			} catch (Exception e) {
//				e.printStackTrace(System.err);
//			}
//		}
//
//		if ("update_rsv".equals(action)) {
//			try {
//				String rsv_str = req.getParameter("rsv_date").trim();
//				LocalDate rsv_date = LocalDate.parse(rsv_str);
//				String rm_type = req.getParameter("rm_type"); //取得該房客訂購的房型
//				RoomRsvService rsvSvc = new RoomRsvService();
//				RoomRsvVO rsvvo = rsvSvc.getOneByDateNRmType(rsv_date, rm_type); //取得該天的訂房剩餘資訊
//				Integer bk_qty = Integer.valueOf(req.getParameter("bk_qty")); //取得房客訂購房型的數量
//				Integer rmLeft = rsvvo.getRoom_left() - bk_qty; //剩餘數量 - 訂購數量
//				rsvSvc.updateRmLeft(rsv_date, rm_type, rmLeft); //更新該天的房型數量
//				return;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if ("getall_rsv".equals(action)) {
//			try {
//				int nights = Integer.parseInt(req.getParameter("night"));
//				int adult = Integer.parseInt(req.getParameter("adult"));
//				int currentMonth = Integer.parseInt(req.getParameter("currentMonth"));
//				int currentYear = Integer.parseInt(req.getParameter("currentYear"));
//				RoomRsvService rsvSvc = new RoomRsvService();
//				List<RoomRsvVO> list = rsvSvc.getAll();
//				req.setAttribute("rsvList", list);
//				return;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		if("roomCheck".equals(action)) {
			out = res.getWriter();
			try {
				String date = req.getParameter("date");
				Integer qty = Integer.parseInt(req.getParameter("qty"));
				Integer stay = Integer.parseInt(req.getParameter("stay"));
				String rmType = req.getParameter("rmtype");
				LocalDate rsv_date = LocalDate.parse(date);
				RoomRsvService rsvSvc = new RoomRsvService();
				JSONObject jsonObj = new JSONObject();
				
				Integer rmLeft = rsvSvc.roomCheck(rsv_date, stay, rmType);
				if (rmLeft >= qty) {
					jsonObj.put(rmType, rmLeft);
					jsonObj.put("Zext", rsv_date.plusDays(1L));
				} else if(rmLeft == 0){
					jsonObj.put("Zext", rsv_date.plusDays(stay));
					jsonObj.put("isMam", "true");
				} else {
					jsonObj.put("Zext", rsv_date.plusDays(stay));
					jsonObj.put("isFull", "true");
				}
				out.print(jsonObj);
	
				return;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
		if("booking".equals(action)) {
			try {
				String room_category_id = req.getParameter("rmType");
				String date = req.getParameter("date");
				String stay = req.getParameter("stay");
				Integer qty = Integer.parseInt(req.getParameter("qty"));
				LocalDate rsv_date = LocalDate.parse(date);
				RoomRsvService rsvSvc = new RoomRsvService();
				RoomTypeService rmtypeSvc = new RoomTypeService();
				JSONObject jsonObj = new JSONObject();
				List<RoomRsvVO> rsvvoList = new LinkedList<>();
				List<RoomTypeVO> rmtypeList = rmtypeSvc.getAllRT();
				for (RoomTypeVO rmtypevo : rmtypeList) {
					RoomRsvVO rsvvo = new RoomRsvVO();
					Integer rmLeft = rsvSvc.roomCheck(rsv_date, Integer.parseInt(stay), rmtypevo.getRoom_category_id());
					if (rmLeft >= qty) { //只放有足夠數量的房間
						rsvvo.setRoom_left(rmLeft);
						rsvvo.setRoom_category_id(rmtypevo.getRoom_category_id());
						rsvvo.setRsv_date(rsv_date);
						rsvvoList.add(rsvvo);
					}
				}
				HttpSession session = req.getSession();
				jsonObj.put("stay", stay); //回傳額外訊息
				jsonObj.put("qty", qty);
				jsonObj.put("startDate", date);
				jsonObj.put("leaveDate", rsv_date.plusDays(Integer.parseInt(stay)).toString());
				session.setAttribute("room_category_id", room_category_id);
				session.setAttribute("rsvvoList", rsvvoList);
				session.setAttribute("infoJson", jsonObj);
				res.sendRedirect(req.getContextPath() + "/front-end/order/Order.jsp");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
//		if("addtocart".equals(action)) {
//			out = res.getWriter();
//			try {
//				List<JSONObject> bookingCart = null;
//				bookingCart  = (List<JSONObject>)req.getSession().getAttribute("bookingCart"); //取得購物車內清單
//				if (bookingCart == null) {
//					bookingCart = new ArrayList<>();
//				}
//				String rmtype = req.getParameter("roomType");
//				String stay = req.getParameter("stay");
//				String startDateStr = req.getParameter("startDate");
//				LocalDate startDate = LocalDate.parse(startDateStr);
//				LocalDate leaveDate = startDate.plusDays(Integer.parseInt(stay));
//				JSONObject roomCard = new JSONObject();
//				roomCard.put("startDate", startDateStr);
//				roomCard.put("leaveDate", leaveDate.toString());
//				roomCard.put("stay", req.getParameter("stay"));
//				roomCard.put("guest", req.getParameter("guest"));
//				roomCard.put("subtotal", req.getParameter("subtotal"));
//				roomCard.put("rmtype", rmtype);
//				roomCard.put("roomCardId", rmtype + "-" + startDate + "-" + stay);
//				bookingCart.add(roomCard);
//				req.getSession().setAttribute("bookingCart", bookingCart); //不管是不是會員都存在session，限定時間內結帳，不存資料庫
//				out.print("success");
//				return;
//			} catch (Exception e) {
//				out.print("fail");
//				e.printStackTrace();
//				throw new RuntimeException(e.getMessage());
//			}
//		}
//		
//		if("removefromcart".equals(action)) {
//			out = res.getWriter();
//			try {
//				List<JSONObject> bookingCart  = (List<JSONObject>)req.getSession().getAttribute("bookingCart"); //取得購物車內清單
//				String roomCardId = req.getParameter("roomCardId");
//				JSONObject json = null;
//				for (JSONObject obj: bookingCart) {
//					if (obj.getString("roomCardId").equals(roomCardId)) {
//						json = obj;
//						break;
//					}
//				}
//				bookingCart.remove(json);
//				req.getSession().setAttribute("bookingCart", bookingCart); //不管是不是會員都存在session，限定時間內結帳，不存資料庫
//				out.print("success");
//				return;
//			} catch (Exception e) {
//				out.print("fail");
//				e.printStackTrace();
//				throw new RuntimeException(e.getMessage());
//			}
//		}
	}

}
