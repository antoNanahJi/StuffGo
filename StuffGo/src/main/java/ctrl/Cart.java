package ctrl;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet({ "/Cart", "/Cart/*" })
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
		try {
			MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
			Map<String, ItemBean> items = model.getStoreModel().retrieve(null,null,null,null);			
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
		String target = "/cart.jsp";
		if (request.getParameter("checkout") != null) {
			target = "/checkout.jsp";
		}
		request.getSession().setAttribute("name", "John Smith");
		request.getSession().setAttribute("address", "100 Main Street, City, Province, Country, A1A 1A1");
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
