package ctrl;

import java.io.IOException;
import java.io.Writer;
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
		MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
		ArrayList<ItemBean> items = new ArrayList<ItemBean>();
		Object itemObj = request.getSession().getAttribute("cartItems");
		String itemStrings[] = {};
		if (itemObj != null) {
			itemStrings = itemObj.toString().split(",");
		}
		//loops through every Item added to cart and adds to list
		try {
			for (String i : itemStrings) {
				if (!i.equals("")) {
					ItemBean item = model.getStoreModel().retreiveItem(i.split("=")[0]);
					items.add(item);
				}
			}
			//send list to session scope
			request.getSession().setAttribute("items", items);
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
		//Checks for AJAX call to change item quantity
		if (request.getParameter("out") != null && request.getParameter("out").equals("changeItem")) {
			String newItemString = "";
			Writer resOut = response.getWriter();
			for (String i : itemStrings) {
				String[] iSplit = i.split("=");
				iSplit[1] = iSplit[1] + ",";
				//Change the string that store item id and quantity
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
			
			if (newItemString.equals("")) {
				
				resOut.write("{\"isCartEmpty\":\"" + true + "\"}");
				resOut.flush();
			} else {
				resOut.write("{\"isCartEmpty\":\"" + false + "\"}");
				resOut.flush();
			}
			request.getSession().setAttribute("cartItems", newItemString);
			return;
		}
		String target = "/cart.jsp";
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
