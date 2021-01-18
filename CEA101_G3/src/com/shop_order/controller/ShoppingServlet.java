 package com.shop_order.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.shop_order.model.Item;
import com.shop_order.model.ShopOrderService;
import com.shop_order_detail.model.ShopOrderDetailService;
import com.shop_order_detail.model.ShopOrderDetailVO;

@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		Vector<Item> buylist = (Vector<Item>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if(!action.equals("CHECKOUT")) {
			
			if(action.equals("DELETE")) {
				String itemId = req.getParameter("itemId");
				
				for(int i=0; i < buylist.size(); i++) {
					Item item = buylist.get(i);
					if(item.getItemId().equals(itemId)){
						buylist.remove(i);		
					}
				}
			}
			else if(action.equals("ADD")){
				boolean match = false;

				Item aItem = getItem(req);
				if(buylist==null) {
					buylist = new Vector<Item>();
					buylist.add(aItem);
				}else {
					for(int i=0; i < buylist.size(); i++) {
						Item item = buylist.get(i);
						if(item.getItemId().equals(aItem.getItemId())){
							item.setQuantity(item.getQuantity() + aItem.getQuantity());
							buylist.setElementAt(item, i);
							match = true;			
						}
					}
					if(!match) 
						buylist.add(aItem);
				}
				//ws
				Set<javax.websocket.Session> wsSessions = (Set<javax.websocket.Session>) session.getAttribute("wsSessions");
				if (wsSessions != null && wsSessions.size() > 0) {
					JSONObject data = new JSONObject();
					try {
						data.put("type", "加入購物車");
						data.put("cartItems", buylist.size());
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					wsSessions.forEach(e -> e.getAsyncRemote().sendText(data.toString()));
				}
				//
			}else if(action.equals("quantityChange")) {
				String itemId = req.getParameter("itemId");
				int newVal = new Integer(req.getParameter("newVal"));
				
				
				for(int i=0 ; i < buylist.size(); i++) {
					Item item = buylist.get(i);
					
					if(item.getItemId().equals(itemId)) {
						item.setQuantity(newVal);
					}
				}
			}
			session.setAttribute("shoppingcart",buylist);
//			String url = "/front-end/item/shoppingMall.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
		}
		else if(action.equals("CHECKOUT")) {
			
			float total = 0;
			for(int i=0; i < buylist.size(); i++) {
				Item order = buylist.get(i);
				float price = order.getPrice(); 
				int quantity = order.getQuantity();
				total += (price * quantity);
			}
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front-end/item/checkOut.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
	}

	private Item getItem(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String itemId = req.getParameter("itemId");
		Item item = new Item();

		item.setName(name);
		item.setPrice(new Integer(price).intValue());
		item.setQuantity(new Integer(quantity).intValue());
		item.setItemId(itemId);
		return item;
	}
}
