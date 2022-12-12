package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemBean;
import bean.ItemReviewBean;
import model.MainModel;
import utilities.eventTypes;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		super.init();
		try {

			// SisModel instance save in context attribute
			MainModel model = MainModel.getInstance();
			this.getServletContext().setAttribute("MainModel", model);

		} catch (ClassNotFoundException e) {
			throw new ServletException("Class Not Found!" + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
		if (request.getSession().getAttribute("username") != null) {
			try {
				String bAddress = model.getUserModel()
						.returnBillingAddress(request.getSession().getAttribute("username").toString());
				String sAddress = model.getUserModel()
						.returnShippingAddress(request.getSession().getAttribute("username").toString());
				request.setAttribute("billingAddress", bAddress);
				request.setAttribute("shippingAddress", sAddress);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String cartString = "";
		if (request.getParameter("cart") != null) {
			cartString = request.getParameter("cart").toString();
		}
		Map<String[], ItemBean> items = new HashMap<String[], ItemBean>();
		String cartSplit[] = cartString.split("_");
		Map<String, Integer> cartItems = new HashMap<>();
		for (int i = 0; i < cartSplit.length - 1; i++) {
			String itemSplit[] = cartSplit[i].split("-");
			try {
				
				ItemBean item = model.getStoreModel().retreiveItem(itemSplit[0]);
				cartItems.put(itemSplit[0], Integer.valueOf(itemSplit[1]));
				items.put(itemSplit, item);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(items);
		System.out.println(request.getParameter("cart"));
		// checks if order was submitted
		if (request.getParameter("submit") != null) {
			// add to db
			try {
				String clientIP = "";
				String date = "";
				String userID = (String) request.getSession().getAttribute("username");
				if (request.getSession().getAttribute("clientIP") != null) {
					clientIP = (String) request.getSession().getAttribute("clientIP");
				}
				
				if (request.getSession().getAttribute("eventDate") != null) {
					date = (String) request.getSession().getAttribute("eventDate");
				}
				
				if(!date.equals("") && !clientIP.equals("")) {
					for (String key : cartItems.keySet()) {
						model.getWebsiteUsageModel().insertRecord(clientIP, date, key, eventTypes.PURCHASE);
						model.getItemPurchasedModel().insertRecord(key, date.substring(3, 5), cartItems.get(key), userID);
					}
				}
			} catch (SQLException | NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			System.out.println("Order Submitted!");
			System.out.println(
					"Billing " + request.getParameter("billingName") + " at " + request.getParameter("billingAddress"));
			System.out.println("Shipping to " + request.getParameter("shippingName") + " at "
					+ request.getParameter("shippingAddress"));
		}
		request.setAttribute("items", items);
		String target = "/checkout.jsp";
		request.getRequestDispatcher(target).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
