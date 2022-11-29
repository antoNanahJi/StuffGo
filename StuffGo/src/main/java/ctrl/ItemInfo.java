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
 * Servlet implementation class ItemInfo
 */
@WebServlet("/ItemInfo")
public class ItemInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String itemID = "";
		
		if(request.getParameter("ID") != null) {
			itemID = request.getParameter("ID");
		}
		
		try {
			
			StoreModel model = (StoreModel) this.getServletContext().getAttribute("SModel");
			ItemBean itemInfo = model.retreiveItem(itemID);
			
			request.setAttribute("image", itemInfo.getImage());
			request.setAttribute("name", itemInfo.getName());
			request.setAttribute("description", itemInfo.getDescription());
			request.setAttribute("price", itemInfo.getPrice());
			
			
			String target = "/ItemView.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
