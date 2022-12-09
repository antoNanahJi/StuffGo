package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MainModel;
import bean.ItemBean;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart() {
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
		ArrayList<ItemBean> items = new ArrayList<ItemBean>();
		Object itemObj = request.getSession().getAttribute("cartItems");
		String itemStrings[] = {};
		if (itemObj != null) {
			itemStrings = itemObj.toString().split(",");
		}
		try {
			for (String i : itemStrings) {
				if (!i.equals("")) {
					ItemBean item = model.getStoreModel().retreiveItem(i.split("=")[0]);
					items.add(item);
				}
			}
			request.setAttribute("items", items);
//			for(String key : items.keySet()) {
//				counter++;
//				request.setAttribute("itemName"+ counter, items.get(key).getBrand());
//				request.setAttribute("itemPrice"+ counter, items.get(key).getPrice());
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (request.getParameter("out") != null && request.getParameter("out").equals("changeItem")) {
			String newItemString = "";
			for (String i : itemStrings) {
				String[] iSplit = i.split("=");
				iSplit[1] = iSplit[1] + ",";
				if (("00" + request.getParameter("changedItemID")).equals(iSplit[0])) {
					if (Integer.parseInt(request.getParameter("changedItemQuantity")) <= 0) {
						i = "";
					} else {
						iSplit[1] = request.getParameter("changedItemQuantity") + ",";
						i = iSplit[0] + '=' + iSplit[1];
					}
				} else {
					i = iSplit[0] + '=' + iSplit[1];
				}
				newItemString += i;
			}
			request.getSession().setAttribute("cartItems", newItemString);
		}
		String target = "/cart.jsp";
//		if (request.getParameter("checkout") != null) {
//			target = "/checkout.jsp";
//		}
//		if(request.getSession().getAttribute("username") != null) {
//			try {
//				MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
//				String bAddress = model.getUserModel().returnBillingAddress(request.getSession().getAttribute("username").toString());
//				String sAddress = model.getUserModel().returnShippingAddress(request.getSession().getAttribute("username").toString());
//				request.setAttribute("billingAddress", bAddress);	
//				request.setAttribute("shippingAddress", sAddress);		
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
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
