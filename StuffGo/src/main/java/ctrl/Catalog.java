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
import model.MainModel;

/**
 * Servlet implementation class Catalog
 * @author Philip Michalowski
 *
 */
@WebServlet("/home")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Catalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Initialize helper class called SIS and saved it on Servlet Context
	@Override
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
			throws ServletException, IOException{
		Writer resOut = response.getWriter();
		try {
			String brand = request.getParameter("brand");
			String type = request.getParameter("type");
			String category = request.getParameter("category");
			String ID = request.getParameter("ID");
			
			MainModel model = (MainModel) this.getServletContext().getAttribute("MainModel");
			Map<String, ItemBean> results = model.getStoreModel().retrieve(brand,type,category,ID);
			response.setContentType("application/json");
			int counter = 0;
			resOut.append("{");
			resOut.append("\"items\" : [");
			for (String key : results.keySet()) {
				resOut.append("{");
				resOut.append("\"name\" : \"" + results.get(key).getName() + "\",");
				resOut.append("\"price\" : \"" + results.get(key).getPrice() + "\",");
				resOut.append("\"brand\" : \"" + results.get(key).getBrand() + "\",");
				resOut.append("\"type\" : \"" + results.get(key).getType1() + "\",");
				resOut.append("\"category\" : \"" + results.get(key).getCategory() + "\",");
				resOut.append("\"image\" : \"" + results.get(key).getImage() + "\",");
				resOut.append("\"ID\" : \"" + results.get(key).getID() + "\"}");
				
				if (results.size() != counter + 1) {
					resOut.append(",");
				}
				counter++;
			}
			resOut.append("]}");
			resOut.flush();
			
		} catch (Exception e) {
			System.out.println("error");
			resOut.write(e.getMessage());;
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
