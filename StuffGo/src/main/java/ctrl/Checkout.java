package ctrl;

import java.io.IOException;
import java.io.Writer;
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

		//loops through every cartitem
		for (int i = 0; i < cartSplit.length; i++) {
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
		// counts number of credit cards submitted, to reject every 3rd.
		int creditCounter;
		if (request.getServletContext().getAttribute("creditCounter") != null && (int) request.getServletContext().getAttribute("creditCounter") <= 3) {
			creditCounter = (int) request.getServletContext().getAttribute("creditCounter");
		} else {
			creditCounter = 1;
			request.getServletContext().setAttribute("creditCounter", creditCounter);
		}
		if (request.getParameter("out") != null && request.getParameter("out").equals("count")) {
			creditCounter++;
			request.getServletContext().setAttribute("creditCounter", creditCounter);
		}
		request.setAttribute("Submitted", false);
		// checks if order was submitted
		if (request.getParameter("submit") != null && request.getParameter("submit").equals("true")) {
			Writer resOut = response.getWriter();
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

				if (!date.equals("") && !clientIP.equals("")) {
					
					for (String key : cartItems.keySet()) {
						model.getWebsiteUsageModel().insertRecord(clientIP, date, key, eventTypes.PURCHASE);
						model.getItemPurchasedModel().insertRecord(key, date.substring(3, 5), cartItems.get(key),
								userID);
						int stuff = model.getStoreModel().retreiveItem(key).getQuantity() - cartItems.get(key);
						model.getStoreModel().updateQuantity(key, stuff);
					}
				}
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				resOut.write(e.getMessage());
				e.printStackTrace();
			}
			System.out.println("Order Submitted!");
			// clear cart
			request.getSession().setAttribute("cartItems", "");
			request.setAttribute("Submitted", true);
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
