package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemBean;
import model.StoreModel;

/**
 * Servlet implementation class SIS
 */
@WebServlet("/home")
public class Items extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Initialize helper class called SIS and saved it on Servlet Context
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			StoreModel model = new StoreModel();
			this.getServletContext().setAttribute("SModel", model);
		} catch (ClassNotFoundException e) {
			throw new ServletException("Class Not Found" + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Writer resOut = response.getWriter();
		try {
			StoreModel model = (StoreModel) this.getServletContext().getAttribute("SModel");
			Map<String, ItemBean> results = model.retreiveItem();
			resOut.write("hello1");
			
			System.out.println(results);
			// resOut.append(results.get("001").getID());
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
			
		}
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
